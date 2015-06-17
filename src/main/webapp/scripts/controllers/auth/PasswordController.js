define(
    ['app', 'services/auth/PasswordService'],
    function(app) {
        'use strict';

        var passwordCtrl = function ($scope, Password) {
            $scope.success = null;
            $scope.error = null;
            $scope.doNotMatch = null;
            $scope.changePassword = function () {
                if ($scope.password != $scope.confirmPassword) {
                    $scope.doNotMatch = "ERROR";
                } else {
                    $scope.doNotMatch = null;
                    Password.save($scope.password,
                        function (value, responseHeaders) {
                            $scope.error = null;
                            $scope.success = 'OK';
                        },
                        function (httpResponse) {
                            $scope.success = null;
                            $scope.error = "ERROR";
                        });
                }
            };
        };

        app.getNgModule().register.controller('PasswordController', ['$scope', 'Password', passwordCtrl]);
    }
);
