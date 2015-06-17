define(
    ['app'],
    function(app) {
        'use strict';

        var unitService = function ($resource) {
            return $resource('app/rest/units/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Unit', ['$resource', unitService]);
    }
);
