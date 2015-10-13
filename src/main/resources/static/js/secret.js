/**
 * Created by Selutin_AV on 12.10.2015.
 */
'use strict';

/* App Module */

var secretApp = angular.module('secretApp', [
    'ngRoute',
    'userControllers',
    'userServices'
]);

secretApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/users', {
                templateUrl: 'secret/partials/user-list.html',
                controller: 'UserListController'
            }).
            when('/users/:userId', {
                templateUrl: 'secret/partials/user-detail.html',
                controller: 'UserDetailController'
            }).
            otherwise({
                redirectTo: '/users'
            });
    }]);

// Можно всё выполнять в одном без injection с помощью точки ".", например:

//'use strict';
//
//angular.module('secretApp', [
//    'ngRoute',
//    'ngResource'
//]).
//
//    config(['$routeProvider',
//        function ($routeProvider) {
//            $routeProvider.
//                when('/users', {
//                    templateUrl: 'secret/partials/user-list.html',
//                    controller: 'UserListController'
//                }).
//                //when('/users/:userId', {
//                //    templateUrl: 'user-detail.html',
//                //    controller: 'UserDetailController'
//                //}).
//                otherwise({
//                    redirectTo: '/users'
//                });
//        }]).
//
//    factory('User', ['$resource',
//        function ($resource) {
//            return $resource('/users/:userId', {userId: '@Id'}, {
//                query: {method: 'GET', params: {userId: '@Id'}, isArray: true}
//            });
//        }]).
//
//    controller('UserListController', ['$scope', 'User',
//        function ($scope, User) {
//            $scope.users = User.query();
//        }]);
