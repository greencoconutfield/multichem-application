define(
    ['app', 'services/common/EmployeeService'],
    function(app) {
        'use strict';

        var employeeCtrl = function ($scope, Employee) {

            $scope.employees = Employee.query();
            $scope.showEmployeeEdit = false;

            $scope.addNew = function() {
                $scope.clear();
                $scope.showEmployeeEdit = true;
            };

            $scope.create = function () {
                Employee.save($scope.employee,
                    function () {
                        $scope.employees = Employee.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.employee = Employee.get({id: id});
                $scope.showEmployeeEdit = true;
            };

            $scope.delete = function (id) {
                Employee.delete({id: id},
                        function () {
                            $scope.employees = Employee.query();
                        });
            };
            $scope.clear = function () {
                $scope.employee = {
                    id: null,
                    fullName: null,
                    phone: null,
                    email: null,
                    status: null,
                    joinDate: null,
                    leaveDate: null
                    }

                };
                $scope.showEmployeeEdit = false;
            };

        app.getNgModule().register.controller('EmployeeController', ['$scope', 'Employee', employeeCtrl]);
    }
);

