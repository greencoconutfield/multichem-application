define(
    ['app', 'services/common/VisitReportSupplierService', 'services/common/ContactDetailService', 'services/common/ContactPersonService',
        'services/common/AddressService', 'services/common/SupplierUtilService' ],
    function(app) {
        'use strict';

        var visitReportSupplierCtrl = function ($scope, VisitReportSupplier, ContactDetail, ContactPerson, Address, SupplierUtil) {

            $scope.suppliers = VisitReportSupplier.query();
            $scope.showSupplierEdit = false;

            $scope.addNew = function() {
                $scope.clear();
                $scope.showSupplierEdit = true;
            };

            $scope.create = function () {
                VisitReportSupplier.save($scope.supplier,
                    function () {
                        $scope.suppliers = VisitReportSupplier.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.supplier = VisitReportSupplier.get({id: id});
                $scope.showSupplierEdit = true;
            };

            $scope.delete = function (id) {
                VisitReportSupplier.delete({id: id},
                        function () {
                            $scope.suppliers = VisitReportSupplier.query();
                        });
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

        app.getNgModule().register.controller('VisitReportSupplierController', ['$scope', 'VisitReportSupplier', 'ContactDetail', 'ContactPerson', 'Address','SupplierUtil', visitReportSupplierCtrl]);
    }
);

