define(
    ['app'],
    function(app) {
        'use strict';

        var sessionService = function ($cookieStore) {
            this.create = function (login, firstName, lastName, email, userRoles) {
                this.login = login;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.userRoles = userRoles;
                // $cookiestore is used to check if user is authenticated and
                // if not, redirect to login page from module.run method
                $cookieStore.put('username', login);
                console.log('+++ Cookie added..');
            };
            this.invalidate = function () {
                this.login = null;
                this.firstName = null;
                this.lastName = null;
                this.email = null;
                this.userRoles = null;
                $cookieStore.put('username', undefined);
                $cookieStore.remove('username');
                console.log('--- Cookie deleted..');
            };
            return this;
        };

        app.getNgModule().factory('Session', ['$cookieStore', sessionService]);
    }
);
