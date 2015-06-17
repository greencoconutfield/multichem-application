define(
    ['app', 'services/common/AddressService'],
    function(app) {
        'use strict';

        var addressCtrl = function ($scope, Address) {

            $scope.addresss = Address.query();

            $scope.create = function () {
                Address.save($scope.address,
                    function () {
                        $scope.addresss = Address.query();
                        $('#saveAddressModal').modal('hide');
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.address = Address.get({id: id});
                $('#saveAddressModal').modal('show');
            };

            $scope.delete = function (id) {
                Address.delete({id: id},
                    function () {
                        $scope.addresss = Address.query();
                    });
            };

            $scope.clear = function () {
                $scope.address = {
                    id: null,
                    addressType: null,
                    streetNumber: null,
                    streetName: null,
                    buildingName: null,
                    additionalDetails: null,
                    city: null,
                    state: null,
                    country: null,
                    postCode: null
                };
            };
        };

        app.getNgModule().register.controller('AddressController', ['$scope', 'Address', addressCtrl]);
    }
);
