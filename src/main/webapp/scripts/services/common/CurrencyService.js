define(
    ['app'],
    function(app) {
        'use strict';

        var currencyService = function ($resource) {
            return $resource('app/rest/currencies/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Currency', ['$resource', currencyService]);
    }
);
