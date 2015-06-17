define(
    ['app'],
    function(app) {
        'use strict';

        var adminCtrl = function ($scope) {
        };

        app.getNgModule().controller('AdminController', ['$scope', adminCtrl]);
    }
);
