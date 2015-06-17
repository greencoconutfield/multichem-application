define(
    ['app'],
    function(app) {
        'use strict';

        var accountService = function ($resource) {
            return $resource('app/rest/account', {}, {
                query: {
                    method: 'GET',
                    ignoreLoadingBar: true
                }
            });
        };

        app.getNgModule().factory('Account', ['$resource', accountService]);
    }
);
