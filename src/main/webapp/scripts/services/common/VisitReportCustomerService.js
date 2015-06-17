define(
    ['app'],
    function(app) {
        'use strict';

        var visitReportCustomerService = function ($resource) {
            return $resource('app/rest/visitreportcustomers/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('VisitReportCustomer', ['$resource', visitReportCustomerService]);
    }
);
