define(
    ['app'],
    function(app) {
        'use strict';

        var languageCtrl = function ($scope, $translate) {
            $scope.changeLanguage = function (languageKey) {
                $translate.use(languageKey);
            };
        };

        app.getNgModule().controller('LanguageController', ['$scope', '$translate', languageCtrl]);
    }
);
