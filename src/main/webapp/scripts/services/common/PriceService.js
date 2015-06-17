define(
    ['app'],
    function(app) {
        'use strict';

        var priceService = function ($resource) {
            return $resource('app/rest/prices/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Price', ['$resource', priceService]);
    }
);
