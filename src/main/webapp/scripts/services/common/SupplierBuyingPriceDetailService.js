define(
    ['app'],
    function(app) {
        'use strict';

        var supplierBuyingPriceDetailService = function ($resource) {
            return $resource('app/rest/supplierbuyingpricedetails/:id', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                },
                get: {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('SupplierBuyingPriceDetail', ['$resource', supplierBuyingPriceDetailService]);
    }
);

