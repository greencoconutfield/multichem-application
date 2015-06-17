define(
    ['app', 'http-auth-interceptor', 'services/auth/SessionService', 'services/auth/AccountService'],
    function(app) {
        'use strict';

        var authSharedService = function ($rootScope, $http, $cookieStore, authService, Session, Account) {
            return {
                login: function (param) {
                    var data ="j_username=" + param.username +"&j_password=" + param.password +"&_spring_security_remember_me=" + param.rememberMe +"&submit=Login";
                    $http.post('app/authentication', data, {
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        ignoreAuthModule: 'ignoreAuthModule'
                    }).success(function (data, status, headers, config) {
                            Account.get(function(data) {
                                Session.create(data.login, data.firstName, data.lastName, data.email, data.roles);
                                $rootScope.account = Session;
                                authService.loginConfirmed(data);
                            });
                        }).error(function (data, status, headers, config) {
                            $rootScope.authenticationError = true;
                            Session.invalidate();
                        });
                },
                valid: function (authorizedRoles) {

//                    $http.get('app/rest/account', {
//                        ignoreAuthModule: 'ignoreAuthModule',
//                        ignoreLoadingBar: true
//                    }).success(function (data, status, headers, config) {
                    if (!Session.login) {
                        $http.get('app/rest/account', {
                            //ignoreAuthModule: 'ignoreAuthModule',
                            ignoreLoadingBar: true
                        }).success(function(data) {
                            Session.create(data.login, data.firstName, data.lastName, data.email, data.roles);
                            $rootScope.account = Session;

                            if (!$rootScope.isAuthorized(authorizedRoles)) {
                                event.preventDefault();
                                // user is not allowed
                                $rootScope.$broadcast("event:auth-notAuthorized");
                            }

                            $rootScope.authenticated = true;

                            // User name in cookieStore.. already logged in.. so broadcast auth-loginConfirmed
//                            if (!$cookieStore.get('username')) {
//                                $rootScope.$broadcast("event:auth-loginConfirmed");
//                            }

                        }).error(function (data, status, headers, config) {
                            $rootScope.authenticated = false;
                            $cookieStore.remove('username');
                        });
                    }
                    else {
                        $rootScope.authenticated = true;
                    }
                },
                isAuthorized: function (authorizedRoles) {
                    if (!angular.isArray(authorizedRoles)) {
                        if (authorizedRoles == '*') {
                            return true;
                        }

                        authorizedRoles = [authorizedRoles];
                    }

                    var isAuthorized = false;
                    angular.forEach(authorizedRoles, function(authorizedRole) {
                        var authorized = (!!Session.login &&
                            Session.userRoles.indexOf(authorizedRole) !== -1);

                        if (authorized || authorizedRole == '*') {
                            isAuthorized = true;
                        }
                    });

                    return isAuthorized;
                },
                logout: function () {
                    $rootScope.authenticationError = false;
                    $rootScope.authenticated = false;
                    $rootScope.account = null;

                    $http.get('app/logout');
                    Session.invalidate();
                    authService.loginCancelled();
                }
            };
        };

        app.getNgModule().factory('AuthenticationSharedService', ['$rootScope', '$http', '$cookieStore', 'authService', 'Session', 'Account', authSharedService]);
    }
);
