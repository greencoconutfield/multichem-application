define(
    ['app'],
    function(app) {
        'use strict';

        var productService = function ($resource) {
            return $resource('app/rest/products/:id', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                },
                get: {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Product', ['$resource', productService]);
    }
);

