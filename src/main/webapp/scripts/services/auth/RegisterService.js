define(
    ['app'],
    function(app) {
        'use strict';

        var registerService = function ($resource) {
            return $resource('app/rest/register', {}, {
            });
        };

        app.getNgModule().register.factory('Register', ['$resource', registerService]);
    }
);
