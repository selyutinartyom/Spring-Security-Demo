/**
 * Created by Selutin_AV on 12.10.2015.
 */
'use strict';

/* Controllers */

var userControllers = angular.module('userControllers', []);

userControllers.

    controller('UserListController', ['$scope', 'User',
        function ($scope, User) {
            $scope.users = User.query();
        }]).

    controller('UserDetailController', ['$scope', '$routeParams', 'User',
        function ($scope, $routeParams, User) {
            $scope.user = User.get({userId: $routeParams.userId}, function(user) {
                $scope.role = user.roles[0];
            });
        }]);

