define(
    ['app', 'services/auth/AuthenticationSharedService'],
    function(app) {
        'use strict';

        var loginCtrl = function ($scope, AuthenticationSharedService) {
            $scope.rememberMe = true;
            $scope.login = function () {
                AuthenticationSharedService.login({
                    username: $scope.username,
                    password: $scope.password,
                    rememberMe: $scope.rememberMe
                })
            }
        };

        app.getNgModule().controller('LoginController', ['$scope', 'AuthenticationSharedService', loginCtrl]);
    }
);
