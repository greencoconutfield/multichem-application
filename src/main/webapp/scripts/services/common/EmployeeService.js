define(
    ['app'],
    function(app) {
        'use strict';

        var employeeService = function ($resource) {
            return $resource('app/rest/employees/:id', {}, {
                query:  {
                    method: 'GET',
                    isArray: true
                },
                get:    {
                    method: 'GET'
                }
            });
        };

        app.getNgModule().register.factory('Employee', ['$resource', employeeService]);
    }
);
