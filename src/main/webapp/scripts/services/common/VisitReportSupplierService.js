define(
    ['app'],
    function(app) {
        'use strict';

        var visitReportSupplierService = function ($resource) {
            return $resource('app/rest/visitreportsuppliers/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('VisitReportSupplier', ['$resource', visitReportSupplierService]);
    }
);
