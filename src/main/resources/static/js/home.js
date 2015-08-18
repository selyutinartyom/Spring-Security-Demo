/**
 * Created by Selutin_AV on 18.08.2015.
 */
angular.module('appHome', [])
    .controller('HomeController', function HomeController($scope) {
        // TODO контроллер
        $scope.todo = $scope.yourName;
    });
