define(
    ['app'],
    function(app) {
        'use strict';

        var logsService = function ($resource) {
            return $resource('app/rest/logs', {}, {
                'findAll': { method: 'GET', isArray: true},
                'changeLevel':  { method: 'PUT'}
            });
        };

        app.getNgModule().register.factory('LogsService', ['$resource', logsService]);
    }
);
