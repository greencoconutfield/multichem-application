define(['app', 'angularMocks', 'loginController'], function(testApp, angularMocks) {

    describe('Controllers Tests ', function () {
        'use strict';

        beforeEach(module(testApp.getNgModule().__appname));

        describe('SettingsController', function () {
            var $q, $scope, mockAccountService, queryDeferred;

            var mockAccountService = {
                save: function() {}
            };

            beforeEach(inject(function($rootScope, $controller) {
                $scope = $rootScope.$new();
                $controller('SettingsController',{$scope:$scope, Account:mockAccountService});
            }));

            it('should save account', function () {
                //GIVEN
                $scope.settingsAccount = {firstName: "John", lastName: "Doe"};

                //SET SPY
                spyOn(mockAccountService, 'save');

                //WHEN
                $scope.save();

                //THEN
                expect(mockAccountService.save).toHaveBeenCalled();
                expect(mockAccountService.save).toHaveBeenCalledWith({firstName: "John", lastName: "Doe"}, jasmine.any(Function), jasmine.any(Function));

                //SIMULATE SUCCESS CALLBACK CALL FROM SERVICE
                mockAccountService.save.calls.mostRecent().args[1]();
                expect($scope.error).toBeNull();
                expect($scope.success).toBe('OK');
            });
        });
    });
});
