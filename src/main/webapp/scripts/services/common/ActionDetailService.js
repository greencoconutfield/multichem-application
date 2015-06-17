define(
    ['app'],
    function(app) {
        'use strict';

        var actionDetailService = function ($resource) {
            return $resource('app/rest/actiondetails/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('ActionDetail', ['$resource', actionDetailService]);
    }
);
