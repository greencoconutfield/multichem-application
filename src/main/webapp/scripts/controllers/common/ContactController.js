define(
    ['app', 'services/common/ContactService'],
    function(app) {
        'use strict';

        var contactCtrl = function ($scope, Contact) {

            $scope.contacts = Contact.query();
            $scope.showContactEdit = false;

            $scope.addNew = function() {
                $scope.clear();
                $scope.showContactEdit = true;
            };

            $scope.create = function () {
                Contact.save($scope.contact,
                    function () {
                        $scope.contacts = Contact.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.contact = Contact.get({id: id});
                $scope.showContactEdit = true;
            };

            $scope.delete = function (id) {
                Contact.delete({id: id},
                    function () {
                        $scope.contacts = Contact.query();
                    });
            };

            $scope.clear = function () {
                $scope.contact = {
                    id: null,
                    firstName: null,
                    middleName: null,
                    lastName: null,
                    email: null,
                    mobile: null,
                    homePhone: null,
                    fax: null,
                    phoneOther: null,
                    addresses: [
                        {
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
                    ]
                };
                $scope.showContactEdit = false;
            };
        };

        app.getNgModule().register.controller('ContactController', ['$scope', 'Contact', contactCtrl]);
    }
);