define(
    ['app'],
    function(app) {
        'use strict';

        var contactService = function ($resource) {
            return $resource('app/rest/contacts/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Contact', ['$resource', contactService]);
    }
);
