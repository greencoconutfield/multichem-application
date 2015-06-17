define(
    ['app'],
    function(app) {
        'use strict';

        var visitReportService = function ($resource) {
            return $resource('app/rest/visitreports/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('VisitReport', ['$resource', visitReportService]);
    }
);
