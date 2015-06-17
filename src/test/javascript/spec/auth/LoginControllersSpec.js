define(['app', 'angularMocks', 'loginController'], function(testApp, angularMocks, LoginController) {

    describe('Controllers Tests ', function () {
        'use strict';

        beforeEach(module(testApp.getNgModule().__appname));

        describe('LoginController', function () {
            var scope, service;

            var mockedService = {
                login: function() {}
            };

            beforeEach(inject(function ($rootScope, $controller) {
                scope = $rootScope.$new();
                $controller('LoginController', {$scope: scope, AuthenticationSharedService: mockedService});
            }));

            it('should set remember Me', function () {
                expect(scope.rememberMe).toBeTruthy();
            });
        });
    });
});
