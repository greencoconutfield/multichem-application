define(['angularAMD'], function (angularAMD) {
    'use strict';

    /**
     * BOOTSTRAP ANGULAR
     * Replicating what would normally take place in app.js
     */
    var app_name = "test-app",
        app = angular.module(app_name, []);

    // Add property for unit test
    app.__appname = app_name;
//    app.__origAngular = window.angular;
//    app.__preServiceResult = preService;
//
//    // Define route for unit test
//    app.config(function ($routeProvider) {
//        $routeProvider.when("/controllerFn", angularAMD.route({
//            utestParam: "controllerFn",
//            template: "<div>{{message}}</div>",
//            controllerUrl: "test/unit/lib/controllerFn"
//        }));
//    });

    /*
     var elem = document.body;
     angularAMD.bootstrap(app, elem);
     */

    angularAMD.bootstrap(app);

    return {
        getNgModule: function(){ return app; }
    };

});