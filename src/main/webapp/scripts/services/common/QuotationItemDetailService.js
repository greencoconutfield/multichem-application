define(
    ['app'],
    function(app) {
        'use strict';

        var quotationItemDetailService = function ($resource) {
            return $resource('app/rest/quotationitemdetails/:id', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                },
                get: {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('QuotationItemDetail', ['$resource', quotationItemDetailService]);
    }
);

