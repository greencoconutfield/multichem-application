define(
    ['app'],
    function(app) {
        'use strict';

        var passwordService = function ($resource) {
            return $resource('app/rest/account/change_password', {}, {
            });
        };

        app.getNgModule().register.factory('Password', ['$resource', passwordService]);
    }
);
