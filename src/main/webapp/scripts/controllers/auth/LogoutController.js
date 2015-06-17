define(
    ['app', 'services/auth/AuthenticationSharedService'],
    function(app) {
        'use strict';

        var logoutCtrl = function ($location, AuthenticationSharedService, cfpLoadingBar) {
            AuthenticationSharedService.logout();
            cfpLoadingBar.complete();
        };

        app.getNgModule().register.controller('LogoutController', ['$location', 'AuthenticationSharedService', 'cfpLoadingBar', logoutCtrl]);
    }
);
