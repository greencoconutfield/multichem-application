define(
    ['app', 'services/common/SupplierService', 'services/common/ContactDetailService', 'services/common/ContactPersonService',
        'services/common/AddressService', 'services/common/SupplierUtilService' ],
    function(app) {
        'use strict';

        var supplierCtrl = function ($scope, Supplier, ContactDetail, ContactPerson, Address, SupplierUtil) {

            $scope.suppliers = Supplier.query();
            $scope.showSupplierEdit = false;

            $scope.addNew = function() {
                $scope.clear();
                $scope.showSupplierEdit = true;
            };

            $scope.create = function () {
                Supplier.save($scope.supplier,
                    function () {
                        $scope.suppliers = Supplier.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.supplier = Supplier.get({id: id});
                $scope.showSupplierEdit = true;
            };

            $scope.delete = function (id) {
                $scope.products = SupplierUtil.getAssociatedProducts({id: id});
                $scope.len = $scope.products.length;
                if ($scope.len == 0) {
                    Supplier.delete({id: id},
                        function () {
                            $scope.suppliers = Supplier.query();
                        });
                }
                else{
                    window.alert("Could not delete this supplier. This links to some products");
                }
            };

            $scope.clear = function () {
                $scope.supplier = {
                    id: null,
                    supplierName: null,
                    contactDetail: {
                        id: null,
                        email: null,
                        mobile: null,
                        homePhone: null,
                        fax: null,
                        phoneOther: null,
                        contactPerson: {
                            id: null,
                            title: null,
                            fullName: null,
                            email: null,
                            mobile: null,
                            homePhone: null
                        },
                        address: {
                            id: null,
                            addressType: "Primary",
                            streetNumber: null,
                            streetName: null,
                            buildingName: null,
                            additionalDetails: null,
                            city: null,
                            state: null,
                            country: null,
                            postCode: null
                        }

                    }

                };
                $scope.showSupplierEdit = false;
            };
        };

        app.getNgModule().register.controller('SupplierController', ['$scope', 'Supplier', 'ContactDetail', 'ContactPerson', 'Address','SupplierUtil', supplierCtrl]);
    }
);

