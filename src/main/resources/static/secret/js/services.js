/**
 * Created by Selutin_AV on 12.10.2015.
 */
'use strict';

/* Services */

var userServices = angular.module('userServices', ['ngResource']);

userServices.factory('User', ['$resource',
    function ($resource) {
        return $resource('/users/:userId', {userId: '@Id'}, {
            query: {method: 'GET', params: {userId: '@Id'}, isArray: true}
        });
    }]);