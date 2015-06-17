define(
    ['app'],
    function(app) {
        'use strict';

        var supplierService = function ($resource) {
            return $resource('app/rest/suppliers/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Supplier', ['$resource', supplierService]);
    }
);
