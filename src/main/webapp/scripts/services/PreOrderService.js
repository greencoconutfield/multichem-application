define(
    ['../app'],
    function(app) {
        'use strict';

        var preOrderService = function ($resource) {
            return $resource('app/rest/preorders/:id', {}, {
                'query': {
                    method: 'GET',
                    isArray: true
                },
                'get': {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('PreOrder', ['$resource', preOrderService]);
    }
);
