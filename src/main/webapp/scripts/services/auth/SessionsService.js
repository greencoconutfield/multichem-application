define(
    ['app'],
    function(app) {
        'use strict';

        var sessionsService = function ($resource) {
            return $resource('app/rest/account/sessions/:series', {}, {
                'get': { method: 'GET', isArray: true}
            });
        };

        app.getNgModule().register.factory('Sessions', ['$resource', sessionsService]);
    }
);
