define(
    ['app'],
    function(app) {
        'use strict';

        var productSectorService = function ($resource) {
            return $resource('app/rest/productsectors/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('ProductSector', ['$resource', productSectorService]);
    }
);
