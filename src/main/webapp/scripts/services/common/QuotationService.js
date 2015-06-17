define(
    ['app'],
    function(app) {
        'use strict';

        var quotationService = function ($resource) {
            return $resource('app/rest/quotations/:id', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                },
                get: {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Quotation', ['$resource', quotationService]);
    }
);

