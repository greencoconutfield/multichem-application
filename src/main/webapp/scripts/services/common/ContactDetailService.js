define(
    ['app'],
    function(app) {
        'use strict';

        var contactDetailService = function ($resource) {
            return $resource('app/rest/contactdetails/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('ContactDetail', ['$resource', contactDetailService]);
    }
);
