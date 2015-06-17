'use strict';

/* Main Module configuring requireJS */

require.config({
    baseUrl: 'scripts',
    waitSeconds : 400, // make sure it is enough to load all scripts.

    deps: [
        'app' // This is the javascript file where the application is configured.
    ],

    shim : {
        'angular' : {
            exports : 'angular',
            deps: ['jquery']
        },
        'angularAMD': {
            deps: ['angular']
        },
        'ngload': {
            deps: ['angularAMD']
        },
        'modernizr': {
            exports: 'Modernizr'
        },
        'uiRouter' : {
            deps : ['angular']
        },
        'angularResource' : {
            deps : ['angular']
        },
        'angularCookies' : {
            deps : ['angular']
        },
        'angularSanitize' : {
            deps : ['angular']
        },
        'angularBootstrap': {
            deps : ['angular']
        },
        'angularTranslate' : {
            deps : ['angular', 'angularCookies']
        },
        'angularTranslateStorageCookie' : {
            deps : ['angular', 'angularTranslate']
        },
        'angularTranslateLoaderStaticFiles' : {
            deps : ['angular', 'angularTranslate']
        },
        'angularLoadingBar' : {
            deps : ['angular']
        },
        'angularBreadcrumb' : {
            deps : ['angular']
        },
        'trNgGrid' : {
            deps : ['angular']
        },
        'tmhDynamicLocale' : {
            deps : ['angular']
        },
        'bootstrap-affix' :  {
            deps : ['jquery']
        },
        'bootstrap-alert' :  {
            deps : ['bootstrap-affix']
        },
        'bootstrap-dropdown' :  {
            deps : ['bootstrap-alert']
        },
        'bootstrap-tooltip' :  {
            deps : ['bootstrap-dropdown']
        },
        'bootstrap-modal' :  {
            deps : ['bootstrap-tooltip']
        },
        'bootstrap-transition' :  {
            deps : ['bootstrap-modal']
        },
        'bootstrap-button' :  {
            deps : ['bootstrap-transition']
        },
        'bootstrap-popover' :  {
            deps : ['bootstrap-button']
        },
        'bootstrap-carousel' :  {
            deps : ['bootstrap-popover']
        },
        'bootstrap-scrollspy' :  {
            deps : ['bootstrap-carousel']
        },
        'bootstrap-collapse' :  {
            deps : ['bootstrap-scrollspy']
        },
        'bootstrap-tab' :  {
            deps : ['bootstrap-collapse']
        },
        'bootstrap-typeahead' :  {
            deps : ['bootstrap-tab']
        },
        'xeditable' : {
            deps : ['angular']
        },
        'acute.select' : {
            deps : ['angular']
        },
        'theme' : {
            exports : 'Theme',
            deps : ['jquery']
        }
    },
    paths : {
        'jquery'                : '../bower_components/jquery/dist/jquery',
        'angular'               : '../bower_components/angular/angular',
        'angularAMD'            : '../bower_components/angularAMD/angularAMD',
        'ngload'                : '../bower_components/angularAMD/ngload.min',
        'modernizr'             : '../bower_components/modernizr/modernizr',
        'uiRouter'              : '../bower_components/angular-ui-router/release/angular-ui-router',
        'angularResource'       : '../bower_components/angular-resource/angular-resource',
        'angularCookies'        : '../bower_components/angular-cookies/angular-cookies',
        'angularSanitize'       : '../bower_components/angular-sanitize/angular-sanitize',
        'angularTranslate'      : '../bower_components/angular-translate/angular-translate',
        'angularBootstrap'      : '../bower_components/angular-bootstrap/ui-bootstrap-tpls',
        'angularTranslateStorageCookie'     : '../bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie',
        'angularTranslateLoaderStaticFiles' : '../bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files',
        'angularLoadingBar'     : '../bower_components/angular-loading-bar/build/loading-bar',
        'angularBreadcrumb'     : '../bower_components/angular-breadcrumb/release/angular-breadcrumb',
        'tmhDynamicLocale'      : '../bower_components/angular-dynamic-locale/src/tmhDinamicLocale',
        'trNgGrid'              : '../bower_components/trNgGrid/release/trNgGrid.min',
        'domReady'              : '../bower_components/requirejs-domready/domReady',
        'bootstrap-affix'       : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/affix',
        'bootstrap-alert'       : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/alert',
        'bootstrap-dropdown'    : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/dropdown',
        'bootstrap-tooltip'     : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/tooltip',
        'bootstrap-modal'       : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/modal',
        'bootstrap-transition'  : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/transition',
        'bootstrap-button'      : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/button',
        'bootstrap-popover'     : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/popover',
        'bootstrap-carousel'    : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/carousel',
        'bootstrap-scrollspy'   : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/scrollspy',
        'bootstrap-collapse'    : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/collapse',
        'bootstrap-tab'         : '../bower_components/bootstrap-sass/vendor/assets/javascripts/bootstrap/tab',
        'xeditable'             : '../bower_components/angular-xeditable/dist/js/xeditable',
        'acute.select'                 : '../external/acute.select/acute.select',
        'ui.bootstrap'          : '../bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
        'theme'                 : 'adminLTE'
    }
});

require([
        'domReady',
        'app',
        'theme', //load the adminLTE theme js file
        'http-auth-interceptor',
        'truncate',
        'utils',
        'directives',
        'controllers/MainController',
        'controllers/MenuController',
        'controllers/admin/AdminController',
        'controllers/auth/LoginController',
        'controllers/common/LanguageController'
    ],
    function (domReady, app) {
        // Bootstrap application
        app.bootstrap(domReady);
    }
);