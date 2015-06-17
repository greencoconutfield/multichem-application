define(
    ['app', 'services/auth/ActivateService'],
    function(app) {
        'use strict';

        var activationCtrl = function ($scope, $routeParams, Activate) {
            Activate.get({key: $routeParams.key},
                function (value, responseHeaders) {
                    $scope.error = null;
                    $scope.success = 'OK';
                },
                function (httpResponse) {
                    $scope.success = null;
                    $scope.error = "ERROR";
                });
        };

        app.getNgModule().register.controller('ActivationController', ['$scope', '$routeParams', 'Activate', activationCtrl]);
    }
);
