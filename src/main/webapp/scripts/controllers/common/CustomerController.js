define(
    ['app', 'services/common/CustomerService', 'services/common/ContactDetailService', 'services/common/ContactPersonService',
        'services/common/AddressService','services/common/IndustrySectorService'],
    function(app) {
        'use strict';

        var customerCtrl = function ($scope, Customer, ContactDetail, ContactPerson, Address, IndustrySector) {

            $scope.customers = Customer.query();
            $scope.industrySectors = IndustrySector.query();
            $scope.showCustomerEdit = false;

            $scope.addNew = function() {
                $scope.clear();
                $scope.showCustomerEdit = true;
            };

            $scope.create = function () {
                Customer.save($scope.customer,
                    function () {
                        $scope.customers = Customer.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.customer = Customer.get({id: id});
                $scope.showCustomerEdit = true;
            };

            $scope.delete = function (id) {
                Customer.delete({id: id},
                    function () {
                        $scope.customers = Customer.query();
                    });
            };

            $scope.clear = function () {
                $scope.customer = {
                    id: null,
                    customerName: null,
                    industrySector: null,
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
                $scope.showCustomerEdit = false;
            };
        };

        app.getNgModule().register.controller('CustomerController', ['$scope', 'Customer', 'ContactDetail', 'ContactPerson', 'Address', 'IndustrySector', customerCtrl]);
    }
);

