define(
    ['app', 'services/admin/LogsService'],
    function(app) {
        'use strict';

        var logsCtrl = function ($scope, LogsService) {
            $scope.loggers = LogsService.findAll();

            $scope.changeLevel = function (name, level) {
                LogsService.changeLevel({name: name, level: level}, function () {
                    $scope.loggers = LogsService.findAll();
                });
            }
        };

        app.getNgModule().register.controller('LogsController', ['$scope', 'LogsService', logsCtrl]);
    }
);
