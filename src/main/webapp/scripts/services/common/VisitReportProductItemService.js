define(
    ['app'],
    function(app) {
        'use strict';

        var visitReportProductItemService = function ($resource) {
            return $resource('app/rest/visitreportproductitems/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('VisitReportProductItem', ['$resource', visitReportProductItemService]);
    }
);
