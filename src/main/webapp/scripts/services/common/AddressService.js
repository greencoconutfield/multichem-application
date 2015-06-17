define(
    ['app'],
    function(app) {
        'use strict';

        var addressService = function ($resource) {
            return $resource('app/rest/address/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Address', ['$resource', addressService]);
    }
);
