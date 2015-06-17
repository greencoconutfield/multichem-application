define(
    ['app'],
    function(app) {
        'use strict';

        var shipmentService = function ($resource) {
            return $resource('app/rest/shipments/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Shipment', ['$resource', shipmentService]);
    }
);
