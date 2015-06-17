define(
    ['app', 'services/auth/AccountService'],
    function(app) {
        'use strict';

        var settingsCtrl = function ($scope, Account) {
            $scope.success = null;
            $scope.error = null;
            $scope.settingsAccount = Account.get();

            $scope.save = function () {
                Account.save($scope.settingsAccount,
                    function (value, responseHeaders) {
                        $scope.error = null;
                        $scope.success = 'OK';
                        $scope.settingsAccount = Account.get();
                    },
                    function (httpResponse) {
                        $scope.success = null;
                        $scope.error = "ERROR";
                    });
            };
        };

        app.getNgModule().register.controller('SettingsController', ['$scope', 'Account', settingsCtrl]);
    }
);
