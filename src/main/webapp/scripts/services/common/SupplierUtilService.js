define(
    ['app'],
    function(app) {
        'use strict';

        var supplierUtilService = function ($resource) {
            return $resource('app/rest/supplierutil/:id', {}, {
                'getAssociatedProducts': {
                    method: 'GET',
                    isArray: true}
            });
        };

        app.getNgModule().register.factory('SupplierUtil', ['$resource', supplierUtilService]);
    }
);
