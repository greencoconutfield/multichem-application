define(
    ['app'],
    function(app) {
        'use strict';

        var benefitLevelService = function ($resource) {
            return $resource('app/rest/benefitlevels/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('BenefitLevel', ['$resource', benefitLevelService]);
    }
);
