define(['app', 'angularMocks', 'passwordController'], function(testApp, angularMocks, PasswordController) {

    describe('Controllers Tests ', function () {
        'use strict';

        beforeEach(module(testApp.getNgModule().__appname));

        describe('PasswordController', function(){
            var $scope, $q, mockPasswordService, queryDeferred;


            beforeEach(inject(function(_$q_, $rootScope, $controller) {
                $q = _$q_;
                $scope = $rootScope.$new();
                mockPasswordService = {
                    save: function() {
                        queryDeferred = $q.defer();
                        return {$promise: queryDeferred.promise};
                    }
                }

                $controller('PasswordController',{$scope:$scope, Password:mockPasswordService});
            }));

            it('should show error if passwords do not match', function(){
                //GIVEN
                $scope.password = 'password1';
                $scope.confirmPassword = 'password2';
                //WHEN
                $scope.changePassword();
                //THEN
                expect($scope.doNotMatch).toBe('ERROR');

            });
            it('should call Service and set OK on Success', function(){
                //GIVEN
                var pass = 'myPassword';
                $scope.password = pass;
                $scope.confirmPassword = pass;
                //SET SPY
                spyOn(mockPasswordService, 'save');

                //WHEN
                $scope.changePassword();

                //THEN
                expect(mockPasswordService.save).toHaveBeenCalled();
                expect(mockPasswordService.save).toHaveBeenCalledWith(pass, jasmine.any(Function), jasmine.any(Function));
                //SIMULATE SUCCESS CALLBACK CALL FROM SERVICE
                mockPasswordService.save.calls.mostRecent().args[1]();
                expect($scope.error).toBeNull();
                expect($scope.success).toBe('OK');
            });
        });
    });
});
