define(
    ['app'],
    function(app) {
        'use strict';

        var pricingTypeService = function ($resource) {
            return $resource('app/rest/pricingtypes/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('PricingType', ['$resource', pricingTypeService]);
    }
);
