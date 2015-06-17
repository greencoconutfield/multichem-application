define(
    ['app'],
    function(app) {
        'use strict';

        var industrySectorService = function ($resource) {
            return $resource('app/rest/industrysectors/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('IndustrySector', ['$resource', industrySectorService]);
    }
);
