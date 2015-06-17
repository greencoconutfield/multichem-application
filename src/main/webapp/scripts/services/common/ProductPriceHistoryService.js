define(
    ['app'],
    function(app) {
        'use strict';

        var productPriceHistoryService = function ($resource) {
            return $resource('app/rest/productpricehistories/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                }
            });
        };

        app.getNgModule().register.factory('ProductPriceHistory', ['$resource', productPriceHistoryService]);
    }
);
