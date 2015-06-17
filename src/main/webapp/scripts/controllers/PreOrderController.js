define(
    ['../app', '../services/PreOrderService', 'services/common/CustomerService', 'services/common/EmployeeService'],
    function(app) {
        'use strict';

        var preOrderCtrl = function ($scope, $state, $stateParams, PreOrder, Customer, Employee) {

            if ($stateParams.preOrderId) {
                $scope.preOrder = PreOrder.get({id: $stateParams.preOrderId});
            }
            else {
                $scope.preOrders = PreOrder.query();
            }

            $scope.customers = Customer.query();
            $scope.employees = Employee.query();
            $scope.showPreOrderEdit = false;

            $scope.addNew = function() {
                $scope.clear();
                $scope.showPreOrderEdit = true;
            };

            $scope.create = function () {
                PreOrder.save($scope.preOrder,
                    function () {
                        $state.go('app.main.preOrder');
                        $scope.clear();
                    });
            };

            $scope.edit = function(id) {
                $state.go('app.main.preOrderEdit', {'preOrderId': id});
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.preOrder = PreOrder.get({id: id});
                $scope.showPreOrderEdit = true;
            };

            $scope.delete = function (id) {
                PreOrder.delete({id: id},
                    function () {
                        //$scope.preOrders = PreOrder.query();
                    });
            };

            $scope.clear = function () {
                $scope.preOrder = {
                    id: null,
                    description: null,
                    status: null,
                    expiryDate: null
                };
                $scope.showPreOrderEdit = false;
            };

            $scope.statusList = [
                {id: 1, status: 'Active'},
                {id: 2, status: 'Draft'},
                {id: 3, status: 'Open'},
                {id: 4, status: 'Closed'}
            ];

            $scope.addCustomer = function() {
                $scope.preOrder.customer = $scope.selectedCustomer;
            };

            $scope.addEmployee = function() {
                $scope.preOrder.salesPerson = $scope.selectedEmployee;
            };

            $scope.$watch('preOrder.customer', function() {
                if (!($scope.preOrder.customer && $scope.preOrder.customer.id)) {
                    $scope.preOrder.customer = undefined;
                }
            });
            $scope.$watch('preOrder.salesPerson', function() {
                if (!($scope.preOrder.salesPerson && $scope.preOrder.salesPerson.id)) {
                    $scope.preOrder.salesPerson = undefined;
                }
            });
        };

        app.getNgModule().register.controller('PreOrderController', ['$scope', '$state', '$stateParams', 'PreOrder', 'Customer', 'Employee', preOrderCtrl]);
    }
);

