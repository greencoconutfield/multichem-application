define(
    ['app'],
    function(app) {
        'use strict';

        var purposeDetailService = function ($resource) {
            return $resource('app/rest/purposedetails/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('PurposeDetail', ['$resource', purposeDetailService]);
    }
);
