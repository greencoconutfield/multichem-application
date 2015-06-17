define(
    ['app', 'services/auth/SessionsService'],
    function(app) {
        'use strict';

        var sessionsCtrl = function ($scope, Sessions) {
            $scope.success = null;
            $scope.error = null;
            $scope.sessions = Sessions.get();
            $scope.invalidate = function (series) {
                Sessions.delete({series: encodeURIComponent(series)},
                    function (value, responseHeaders) {
                        $scope.error = null;
                        $scope.success = "OK";
                        $scope.sessions = Sessions.get();
                    },
                    function (httpResponse) {
                        $scope.success = null;
                        $scope.error = "ERROR";
                    });
            };
        };

        app.getNgModule().register.controller('SessionsController', ['$scope', 'Sessions', sessionsCtrl]);
    }
);
