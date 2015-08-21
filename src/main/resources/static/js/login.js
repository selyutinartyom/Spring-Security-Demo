/**
 * Created by Selutin_AV on 19.08.2015.
 */
'use strict';

var app =
    angular
        .module('appLogin', [/*'ngCookies'*/]);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.xsrfCookieName = '_csrf';
    $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
}]);

app.factory('AuthenticationService', ['$http', /*'$cookieStore', '$rootScope',*/
    function ($http/*, $cookieStore, $rootScope*/) {
        var service = {};

        // methods declaration
        service.Login = Login;

        return service;

        function Login(data, callback) {
            /* Dummy authentication for testing, uses $timeout to simulate api call
             ----------------------------------------------*/
            //$timeout(function(){
            //    var response = { success: username === 'admin' && password === '1' };
            //    if(!response.success) {
            //        response.message = 'Username or password is incorrect';
            //    }
            //    callback(response);
            //}, 1000);

            /* Use this for real authentication
             ----------------------------------------------*/
            //$http.post('/login', data)
            //    .success(function (response) {
            //        callback(response);
            //    });

            $http({
                method: 'POST',
                url: '/login',
                headers: {
                    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
                    'Cache-Control': 'max-age=0',
                    'Upgrade-Insecure-Request': 1,
                    //'X-CSRF-TOKEN' : token,
                    'Content-Type': 'application/x-www-form-urlencoded'
                    //'Content-Type': 'application/json'
                },
                data: data
            })
                .success(function (response) {
                    callback(response);
                });

        }
    }]);

app.controller('LoginController', ['$scope', '$http', '$location', 'AuthenticationService',
    function ($scope, $http, $location, AuthenticationService) {

        $scope.vm = {};
        $scope.login = function (token) {
            $scope.dataLoading = true;

            var data = 'username=' + $scope.vm.username + '&'
                + 'password=' + $scope.vm.password + '&'
                + '_csrf=' + token;

            AuthenticationService.Login(data, function (response) {
                if (response.success) {
                    $location.path('/');
                    $http.get();
                } else {
                    $scope.error = response.message;
                    $scope.dataLoading = false;
                }
            });

            //$http({
            //    method: 'POST',
            //    url: '/login',
            //    headers: {
            //        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
            //        'Cache-Control': 'max-age=0',
            //        'Upgrade-Insecure-Request': 1,
            //        //'X-CSRF-TOKEN' : token,
            //        'Content-Type': 'application/x-www-form-urlencoded'
            //        //'Content-Type': 'application/json'
            //    },
            //    data: data
            //})
            // TODO
            //    .success(function (data, status, headers, config) {
            //        //$location.path('/');
            //        alert('status = ' + status + ' headers  = ' + headers + ' config = ' + config);
            //    })
            //    .error(function (data, status, headers, config) {
            //        $scope.error = response.message;
            //        $scope.dataLoading = false;
            //    });

        };


        // reset login status
        //AuthenticationService.ClearCredentials();

    }]);
