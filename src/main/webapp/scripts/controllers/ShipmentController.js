define(
    ['app', 'services/ShipmentService', 'services/common/SupplierService', 'services/common/CustomerService'],
    function(app) {
        'use strict';

        var shipmentCtrl = function ($scope, Shipment, Supplier, Customer) {

            $scope.shipments = Shipment.query();
            $scope.suppliers = Supplier.query();
            $scope.customers = Customer.query();
            $scope.selectedCustomer = null;
            $scope.statuses = ['New', 'Open', 'In Progress', 'Dispatched', 'Arrived', 'Closed'];

            $scope.showShipmentEdit = false;


            $scope.addNew = function() {
                $scope.clear();
                $scope.showShipmentEdit = true;
            };

            $scope.create = function () {
                Shipment.save($scope.shipment,
                    function () {
                        $scope.shipments = Shipment.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.shipment = Shipment.get({id: id});
                $scope.showShipmentEdit = true;
            };

            $scope.delete = function (id) {
                Shipment.delete({id: id},
                    function () {
                        $scope.shipments = Shipment.query();
                    });
            };

            $scope.addCustomer = function() {
                if ($scope.selectedCustomer != null) {
                    addIfNotFound($scope.selectedCustomer, $scope.shipment.customers);
                    $scope.selectedCustomer = null;
                }
            };

            $scope.removeCustomer = function(customer) {
                $scope.shipment.customers.remove(customer);
            };

            Array.prototype.remove = function(customer) {

                var idx = this.map(function(x) {return x.id; }).indexOf(customer.id);
                console.log(idx);
                if (idx != -1) {
                    return this.splice(idx, 1);
                }
                return false;
            };

            function addIfNotFound(customer, arr) {
                if (customer instanceof Customer) {
                    var found = arr.some(function (el) {
                        return el.id === customer.id;
                    });
                    if (!found) {
                        arr.push(customer);
                    }
                }
            };

            $scope.clear = function () {
                $scope.shipment = {
                    id: null,
                    description: null,
                    customers: [],
                    supplier: null,
                    createdByUser: null,
                    dispatchedFrom: null,
                    arrivalLocation: null,
                    status: null,
                    dispatchedDate: null,
                    arrivalDate: null
                };
                $scope.showShipmentEdit = false;
            };
        };

        app.getNgModule().register.controller('ShipmentController', ['$scope', 'Shipment', 'Supplier', 'Customer', shipmentCtrl]);
    }
);