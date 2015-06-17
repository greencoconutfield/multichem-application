define(
    ['app'],
    function(app) {
        'use strict';

        var metricsService = function ($resource) {
            return $resource('metrics/metrics', {}, {
                'get': { method: 'GET'}
            });
        };

        app.getNgModule().register.factory('MetricsService', ['$resource', metricsService]);
    }
);
