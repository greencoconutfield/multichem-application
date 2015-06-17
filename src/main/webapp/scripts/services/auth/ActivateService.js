define(
    ['app'],
    function(app) {
        'use strict';

        var activateService = function ($resource) {
            return $resource('app/rest/activate', {}, {
                'get': { method: 'GET', params: {}, isArray: false}
            });
        };

        app.getNgModule().register.factory('Activate', ['$resource', activateService]);
    }
);
