define(
    ['app'],
    function(app) {
        'use strict';

        var subIndustrySectorService = function ($resource) {
            return $resource('app/rest/subindustrysectors/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('SubIndustrySector', ['$resource', subIndustrySectorService]);
    }
);
