define(
    ['app'],
    function(app) {
        'use strict';

        var visitReportProductService = function ($resource) {
            return $resource('app/rest/visitreportproducts/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('VisitReportProduct', ['$resource', visitReportProductService]);
    }
);
