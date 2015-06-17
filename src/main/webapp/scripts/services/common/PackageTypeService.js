define(
    ['app'],
    function(app) {
        'use strict';

        var packageTypeService = function ($resource) {
            return $resource('app/rest/packagetypes/:id', {}, {
                query: {
                    method: 'GET',
                    isArray: true
                },
                get: {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('PackageType', ['$resource', packageTypeService]);
    }
);

