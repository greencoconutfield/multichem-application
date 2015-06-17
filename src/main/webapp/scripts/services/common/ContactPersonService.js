define(
    ['app'],
    function(app) {
        'use strict';

        var contactPersonService = function ($resource) {
            return $resource('app/rest/contactpersons/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('ContactPerson', ['$resource', contactPersonService]);
    }
);
