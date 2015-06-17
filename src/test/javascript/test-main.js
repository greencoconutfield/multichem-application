(function (window, require) {
    'use strict';
    var file, requireModules;
    requireModules = [];

    for (file in window.__karma__.files) {
        if (window.__karma__.files.hasOwnProperty(file)) {
            if (file.substring(file.length - 7, file.length) === 'Spec.js') {
                console.log('Added file to testing.. ' + file);
                requireModules.push(file);
            }
        }
    }

    requireModules.push('app');
    requireModules.push('angularMocks');

    console.log('requireModules');
    dump(requireModules);

    require({
        // Karma serves files under /base, which is the basePath from your config file
        // Since the requirejs config in init.js base dir is scripts, configure the test base dir to /base/app/scripts
        baseUrl: '/base/src/main/webapp/scripts',

        //TODO verify if all these shim and paths required in tests..

        shim : {
            'angular' : {
                exports : 'angular',
                deps: ['jquery']
            },
            'app' : {
                deps: ['angular']
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
            'angularMocks' : {
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
            'angularMocks'          : '../bower_components/angular-mocks/angular-mocks',
            'angularResource'       : '../bower_components/angular-resource/angular-resource',
            'angularCookies'        : '../bower_components/angular-cookies/angular-cookies',
            'angularSanitize'       : '../bower_components/angular-sanitize/angular-sanitize',
            'angularTranslate'      : '../bower_components/angular-translate/angular-translate',
            'angularBootstrap'      : '../bower_components/angular-bootstrap/ui-bootstrap',
            'angularTranslateStorageCookie'     : '../bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie',
            'angularTranslateLoaderStaticFiles' : '../bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files',
            'angularLoadingBar'     : '../bower_components/angular-loading-bar/build/loading-bar',
            'angularBreadcrumb'     : '../bower_components/angular-breadcrumb/release/angular-breadcrumb',
            'tmhDynamicLocale'      : '../bower_components/angular-dynamic-locale/src/tmhDinamicLocale',
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
            'loginController'       : 'controllers/auth/LoginController',
            'passwordController'    : 'controllers/auth/PasswordController',
            'theme'                 : 'adminLTE',
            'app'                   : '../../../test/javascript/test-app'
        }
    }, requireModules, function () {
        window.__karma__.start();
    }, function (err) {
        var failedModules = err.requireModules;
        console.log('err', err.message);
        console.log('err', err.stack);
        console.log('err', err);

        if (failedModules && failedModules[0]) {
            throw new Error('The module cannot be loaded: ' + failedModules);
        } else {
            throw new Error('Unknown error:' + err);
        }
    });
}(window, require));
