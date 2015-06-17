/* App Module */
define([
        'angularAMD',
        'jquery',
//        'angularRoute',
        'uiRouter',
        'angularResource',
        'angularCookies',
        'angularSanitize',
        'angularBootstrap',
        'angularTranslate',
        'angularTranslateStorageCookie',
        'angularTranslateLoaderStaticFiles',
        'angularLoadingBar',
        'angularBreadcrumb',
        'tmhDynamicLocale',
        'trNgGrid',
        'bootstrap-affix',
        'bootstrap-alert',
        'bootstrap-dropdown',
        'bootstrap-tooltip',
        'bootstrap-modal',
        'bootstrap-transition',
        'bootstrap-button',
        'bootstrap-popover',
        'bootstrap-carousel',
        'bootstrap-scrollspy',
        'bootstrap-collapse',
        'bootstrap-tab',
        'xeditable',
        'acute.select',
        'ui.bootstrap'//TODO check if all these dependencies are required.
    ],
    function (angularAMD) {
        'use strict';

        var app = angular.module('hexachemApp',
                    [
                        'http-auth-interceptor',
                        'ngLocale',
                        'tmh.dynamicLocale',
                        'ngResource',
                        'ui.router',
                        'ngCookies',
                        'ui.bootstrap',
                        'angular-loading-bar',
                        'cfp.loadingBar',
                        'ncy-angular-breadcrumb',
                        'yukamAppUtils',
                        'pascalprecht.translate',
                        'truncate',
                        'yukamApp.directives',
                        'trNgGrid',
                        'xeditable',
                        'acute.select',
                        'ui.bootstrap'
                    ]
                )
            .run(['$rootScope', '$state', '$location', '$http', '$cookieStore', 'AuthenticationSharedService', 'Session', 'USER_ROLES', 'cfpLoadingBar',
                function($rootScope, $state, $location, $http, $cookieStore, AuthenticationSharedService, Session, USER_ROLES, cfpLoadingBar) {
                    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
                        $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
                        $rootScope.userRoles = USER_ROLES;
                        if ((toState.name == 'app.load' && $cookieStore.get('username'))) {
                            event.preventDefault();
                            $state.go('app.main.home');
                        }
                        else if (!($location.path() == "/login" && !$cookieStore.get('username'))) {
                            if (!toState.access) {
                                AuthenticationSharedService.valid('INVALID');
                            }
                            else {
                                AuthenticationSharedService.valid(toState.access.authorizedRoles);
                            }
                        }
                    },
                        function(editableOptions) {
                            editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
                        });


                    $rootScope.$on('$stateChangeError',
                        function(event, toState, toParams, fromState, fromParams, error) {
                            $rootScope.errorMessage = error;
                            console.log(error.message);
                            $state.go('app.main.error');
                        }
                    );

                    // Call when the the client is confirmed
                    $rootScope.$on('event:auth-loginConfirmed', function(data) {
                        $rootScope.authenticated = true;
                        if ($location.path() === "/login") {
                            $state.go('app.main.home');
                        }
                    });

//                    $rootScope.$on('$locationChangeStart', function (event, next) {
//                        $rootScope.isAuthorized = AuthenticationSharedService.isAuthorized;
//                        // $cookieStore is used instead of using AuthenticationSharedService.isAuthenticated
//                        // since refresh or bookmark or browser back doesn't set isAuthenticated properly
//                        if (!$cookieStore.get('username') && $location.path() !== "/register" &&
//                            $location.path() !== "/activate") {
//                            //$state.go('app.login');
//                        }
//                    });

                    // Call when the 401 response is returned by the server
                    $rootScope.$on('event:auth-loginRequired', function(rejection) {
                        Session.invalidate();
                        $rootScope.authenticated = false;
                        if ($location.path() !== "/register" &&
                            $location.path() !== "/activate") {
                            $state.go('app.login');
                        }
                    });

                    // Call when the 403 response is returned by the server
                    $rootScope.$on('event:auth-notAuthorized', function(rejection) {
                        $rootScope.errorMessage = 'errors.403';
                        $state.go('app.main.error');
                    });

                    // Call when the user logs out
                    $rootScope.$on('event:auth-loginCancelled', function() {
                        $state.go('app.login');
                    });
                }])
            .config(function($breadcrumbProvider) {
                $breadcrumbProvider.setOptions({
                    prefixStateName: 'app.main.home'
                });
            })
            .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', '$translateProvider',  'tmhDynamicLocaleProvider', 'USER_ROLES',
                function ($stateProvider, $urlRouterProvider, $httpProvider, $translateProvider, tmhDynamicLocaleProvider, USER_ROLES) {
                    //$urlRouterProvider.when("", "/main");
                    //$urlRouterProvider.when("/", "/main");
                    $urlRouterProvider.otherwise("/load");

                    $stateProvider
                        .state('app', {
                            abstract: true,
                            template: "<ui-view/>",
                            data: {
                                ncyBreadcrumbLabel: 'Home'
                            }
                        })
                        .state('app.load',
                        angularAMD.route({
                            url: '/load',
                            templateUrl: 'views/load.html',
                            controller: 'LoginController',
                            controllerUrl: 'controllers/auth/LoginController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbSkip: true
                            }
                        }))
                        .state('app.login',
                            angularAMD.route({
                                url: '/login',
                                templateUrl: 'views/login.html',
                                controller: 'LoginController',
                                controllerUrl: 'controllers/auth/LoginController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbSkip: true
                                }
                            }))
                        .state('app.main',
                            {
                                abstract: true,
                                templateUrl: 'views/main.html',
                                data: {
                                    ncyBreadcrumbSkip: true
                                }
                            })
                        .state('app.main.home',
                            {
                                url: '/main',
                                templateUrl: 'views/home.html',
                                controller: 'MainController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbSkip: true
                                }
                            })
                        .state('app.main.register',
                            angularAMD.route({
                                url : '/register',
                                templateUrl: 'views/register.html',
                                controller: 'RegisterController',
                                controllerUrl: 'controllers/auth/RegisterController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Register'
                                }
                            }))
                        .state('app.main.activate',
                            angularAMD.route({
                                url: '/activate',
                                templateUrl: 'views/activate.html',
                                controller: 'ActivationController',
                                controllerUrl: 'controllers/auth/ActivationController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Activate'
                                }
                            }))
                        .state('app.main.error',
                            angularAMD.route({
                                url: '/error',
                                templateUrl: 'views/error.html',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Error'
                                }
                            }))
                        .state('app.main.settings',
                            angularAMD.route({
                                url: '/settings',
                                templateUrl: 'views/settings.html',
                                controller: 'SettingsController',
                                controllerUrl: 'controllers/auth/SettingsController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Settings'
                                }
                            }))
                        .state('app.main.password',
                            angularAMD.route({
                                url: '/password',
                                templateUrl: 'views/password.html',
                                controller: 'PasswordController',
                                controllerUrl: 'controllers/auth/PasswordController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Password'
                                }
                            }))
                        .state('app.main.sessions',
                            angularAMD.route({
                                url: '/sessions',
                                templateUrl: 'views/sessions.html',
                                controller: 'SessionsController',
                                controllerUrl: 'controllers/auth/SessionsController',
//                                resolve:{
//                                    resolvedSessions:['Sessions', function (Sessions) {
//                                        return Sessions.get();
//                                    }]
//                                },
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Sessions'
                                }
                            }))
                        .state('app.main.logout',
                            angularAMD.route({
                                url: '/logout',
                                templateUrl: 'views/login.html',
                                controller: 'LogoutController',
                                controllerUrl: 'controllers/auth/LogoutController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbSkip: true
                                }
                            }))
                        .state('app.main.shipment',
                            angularAMD.route({
                                url: '/shipment',
                                templateUrl: 'views/shipments.html',
                                controller: 'ShipmentController',
                                controllerUrl: 'controllers/ShipmentController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Shipment'
                                }
                            }));

                    $stateProvider
                        .state('app.main.visitReport', {
                            abstract: true,
                            url: '/visitReport',
                            template: "<ui-view/>",
                            data: {
                                ncyBreadcrumbLabel: 'Config'
                            }
                        })
                        .state('app.main.visitReport.visitReport',
                        angularAMD.route({
                            url: '/visitReport',
                            templateUrl: 'views/visitReport.html',
                            controller: 'VisitReportController',
                            controllerUrl: 'controllers/common/VisitReportController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Visit report'
                            }
                        }))

                        .state('app.main.visitReport.productSector',
                        angularAMD.route({
                            url: '/productSector',
                            templateUrl: 'views/productSector.html',
                            controller: 'ProductSectorController',
                            controllerUrl: 'controllers/common/ProductSectorController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Product Sector'
                            }
                        }))

                        .state('app.main.visitReport.visitReportSupplier',
                        angularAMD.route({
                            url: '/visitReportSupplier',
                            templateUrl: 'views/visitReportSupplier.html',
                            controller: 'VisitReportSupplierController',
                            controllerUrl: 'controllers/common/VisitReportSupplierController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Supplier'
                            }
                        }))

                        .state('app.main.visitReport.visitReportCustomer',
                        angularAMD.route({
                            url: '/visitReportCustomer',
                            templateUrl: 'views/visitReportCustomer.html',
                            controller: 'VisitReportCustomerController',
                            controllerUrl: 'controllers/common/VisitReportCustomerController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Customer'
                            }
                        }))

                        .state('app.main.visitReport.product',
                        angularAMD.route({
                            url: '/product',
                            templateUrl: 'views/visitReportProduct.html',
                            controller: 'VisitReportProductController',
                            controllerUrl: 'controllers/common/VisitReportProductController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Product'
                            }
                        }));

                    $stateProvider
                        .state('app.main.config', {
                            abstract: true,
                            url: '/config',
                            template: "<ui-view/>",
                            data: {
                                ncyBreadcrumbLabel: 'Config'
                            }
                        })
                        .state('app.main.config.industrySector',
                            angularAMD.route({
                                url: '/industrySector',
                                templateUrl: 'views/industrysectors.html',
                                controller: 'IndustrySectorController',
                                controllerUrl: 'controllers/common/IndustrySectorController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
//                                resolve:{
//                                    resolvedIndustrySector: ['IndustrySector', function (IndustrySector) {
//                                        return IndustrySector.query();
//                                    }]
//                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Industry Sector'
                                }
                            }))
                        .state('app.main.config.quotation',
                        angularAMD.route({
                            url: '/quotation',
                            templateUrl: 'views/quotation.html',
                            controller: 'QuotationController',
                            controllerUrl: 'controllers/common/QuotationController',
//                                resolve:{
//                                    resolvedProduct: ['Product', function (Product) {
//                                        return Product.query();
//                                    }]
//                                },
                            access: {
                                authorizedRoles: [USER_ROLES.admin]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Quotations'
                            }
                        }))
                        .state('app.main.config.product',
                            angularAMD.route({
                                url: '/product',
                                templateUrl: 'views/product.html',
                                controller: 'ProductController',
                                controllerUrl: 'controllers/common/ProductController',
//                                resolve:{
//                                    resolvedProduct: ['Product', function (Product) {
//                                        return Product.query();
//                                    }]
//                                },
                                access: {
                                    authorizedRoles: [USER_ROLES.admin]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Products'
                                }
                            }))
                        .state('app.main.config.contact',
                            angularAMD.route({
                                url: '/contact',
                                templateUrl: 'views/contacts.html',
                                controller: 'ContactController',
                                controllerUrl: 'controllers/common/ContactController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Contact'
                                }
                            }))


                        .state('app.main.config.employee',
                        angularAMD.route({
                            url: '/employee',
                            templateUrl: 'views/employee.html',
                            controller: 'EmployeeController',
                            controllerUrl: 'controllers/common/EmployeeController',
                            access: {
                                authorizedRoles: [USER_ROLES.all]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'Employee'
                            }
                        }))

                        .state('app.main.config.customer',
                            angularAMD.route({
                                url: '/customer',
                                templateUrl: 'views/customers.html',
                                controller: 'CustomerController',
                                controllerUrl: 'controllers/common/CustomerController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Customer'
                                }
                            }))
                        .state('app.main.config.supplier',
                            angularAMD.route({
                                url: '/supplier',
                                templateUrl: 'views/suppliers.html',
                                controller: 'SupplierController',
                                controllerUrl: 'controllers/common/SupplierController',
                                access: {
                                    authorizedRoles: [USER_ROLES.all]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Supplier'
                                }
                            }))

                    .state('app.main.config.taxFee',
                        angularAMD.route({
                            url: '/taxFee',
                            templateUrl: 'views/taxFee.html',
                            controller: 'TaxFeeController',
                            controllerUrl: 'controllers/common/TaxFeeController',
                            access: {
                                authorizedRoles: [USER_ROLES.admin]
                            },
                            data: {
                                ncyBreadcrumbLabel: 'TaxFee'
                            }
                        }));
                    $stateProvider
                        .state('app.main.admin', {
                                abstract: true,
                                url: '/admin',
                                template: "<ui-view/>",
                                data: {
                                    ncyBreadcrumbLabel: 'Admin'
                                }
                            })
                        .state('app.main.admin.metrics',
                            angularAMD.route({
                                url: '/metrics',
                                templateUrl: 'views/metrics.html',
                                controller: 'MetricsController',
                                controllerUrl: 'controllers/admin/MetricsController',
                                access: {
                                    authorizedRoles: [USER_ROLES.admin]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Metrics'
                                }
                            }))
                        .state('app.main.admin.logs',
                            angularAMD.route({
                                url: '/logs',
                                templateUrl: 'views/logs.html',
                                controller: 'LogsController',
                                controllerUrl: 'controllers/admin/LogsController',
//                                resolve:{
//                                    resolvedLogs:['LogsService', function (LogsService) {
//                                        return LogsService.findAll();
//                                    }]
//                                },
                                access: {
                                    authorizedRoles: [USER_ROLES.admin]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Logs'
                                }
                            }))
                        .state('app.main.admin.audits',
                            angularAMD.route({
                                url: '/audits',
                                templateUrl: 'views/audits.html',
                                controller: 'AuditsController',
                                controllerUrl: 'controllers/admin/AuditsController',
                                access: {
                                    authorizedRoles: [USER_ROLES.admin]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Audits'
                                }
                            }))
                        .state('app.main.admin.docs',
                            angularAMD.route({
                                url: '/docs',
                                templateUrl: 'views/docs.html',
                                access: {
                                    authorizedRoles: [USER_ROLES.admin]
                                },
                                data: {
                                    ncyBreadcrumbLabel: 'Docs'
                                }
                            }));

                    // Initialize angular-translate
                    $translateProvider.useStaticFilesLoader({
                        prefix: 'i18n/',
                        suffix: '.json'
                    });

                    $translateProvider.preferredLanguage('en');

                    $translateProvider.useCookieStorage();

                    tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js')
                    tmhDynamicLocaleProvider.useCookieStorage('NG_TRANSLATE_LANG_KEY');

                }])
            .constant('USER_ROLES', {
                    all: '*',
                    admin: 'ROLE_ADMIN',
                    user: 'ROLE_USER'
                });

        // Kickstart application
        function bootstrap(domReady){
            domReady(function (document) {
                angularAMD.bootstrap(app, true, document);
            });
        }

        return {
            bootstrap: bootstrap,
            getNgModule: function(){ return app; }
        };

    });

