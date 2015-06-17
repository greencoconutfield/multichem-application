define(
    ['app'],
    function(app) {
        'use strict';

        var customerService = function ($resource) {
            return $resource('app/rest/customers/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Customer', ['$resource', customerService]);
    }
);
