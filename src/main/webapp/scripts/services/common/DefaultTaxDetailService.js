define(
    ['app'],
    function(app) {
        'use strict';

        var defaultTaxDetailService = function ($resource) {
            return $resource('app/rest/defaulttaxdetails/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('DefaultTaxDetail', ['$resource', defaultTaxDetailService]);
    }
);
