define(
    ['app', 'services/common/VisitReportService', 'services/common/ProductService', 'services/common/ActionDetailService',
        'services/common/PurposeDetailService', 'services/common/VisitReportProductItemService', 'services/common/EmployeeService',
        'services/common/VisitReportCustomerService', 'services/common/VisitReportProductService', 'services/common/UnitService', 'services/common/SubIndustrySectorService',
        'services/common/ProductSectorService', 'services/common/VisitReportSupplierService', 'services/util/angularModalService'],
    function(app) {
        'use strict';

        var visitReportCtrl = function ($scope, VisitReport, Product, ActionDetail, PurposeDetail, VisitReportProductItem, Employee, VisitReportCustomer,
                                        VisitReportProduct, Unit, SubIndustrySector, ProductSector, VisitReportSupplier, ModalService) {

            $scope.visitReports = VisitReport.query();
            $scope.visitReportProducts = VisitReportProduct.query();
            $scope.productSectors = ProductSector.query();
            $scope.employees = Employee.query();
            $scope.suppliers = VisitReportSupplier.query();
            $scope.customers = VisitReportCustomer.query();
            $scope.products = Product.query();
            $scope.units = Unit.query();
            $scope.subIndustrySectors = SubIndustrySector.query();
            $scope.showVisitReportEdit = false;
            $scope.showVisitReportList = true;
            $scope.showMarketSurvey = false;
            $scope.statuses = ["First visit", "Existing customer", "Still trying"];
            $scope.actionStatuses = ["Done", "In Progress", "Suspend"];
            $scope.selectedProductSector;

            $scope.listAllVisitReports = function(){
                $scope.showVisitReportEdit = false;
                $scope.showVisitReportList = true;
                $scope.showMarketSurvey = false;
            };

            $scope.showMarketSurveyDetail = function(){
                $scope.showVisitReportEdit = false;
                $scope.showVisitReportList = false;
                $scope.showMarketSurvey = true;

                $scope.allProducts = [];
                for(var i= 0, len1 =  $scope.visitReports.length; i < len1; i+=1){
                    for(var j= 0, len2 =  $scope.visitReports[i].visitReportProductItems.length; j < len2; j+=1) {
                        $scope.allProducts.push({product: $scope.visitReports[i].visitReportProductItems[j],customer: $scope.visitReports[i].customer.customerName});
                    }
                }
                $scope.allActions = [];
                for(var i= 0, len1 =  $scope.visitReports.length; i < len1; i+=1){
                    for(var j= 0, len2 =  $scope.visitReports[i].actions.length; j < len2; j+=1) {
                        $scope.allActions.push($scope.visitReports[i].actions[j]);
                    }
                }

            };

            $scope.addNew = function() {
                $scope.clear();
                $scope.showVisitReportEdit = true;
                $scope.showVisitReportList = false;
                $scope.showMarketSurvey = false;

                ModalService.showModal({
                    templateUrl: 'visitReportProduct.html',
                    controller: "VisitReportProductController"
                }).then(function(modal) {
                    modal.element.modal();
                    modal.close.then(function(result) {
                        $scope.message = "You said " + result;
                    });
                });
            };

            $scope.create = function (){
            //    $scope.visitReports = VisitReport.query();
                VisitReport.save($scope.visitReport,
                    function () {
                        $scope.visitReports = VisitReport.query();
                        $scope.clear();
                        $scope.showVisitReportEdit = false;
                        $scope.showVisitReportList = true;
                        $scope.showMarketSurvey = false;
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.visitReport = VisitReport.get({id: id});
                $scope.showVisitReportEdit = true;
                $scope.showVisitReportList = false;
                $scope.showMarketSurvey = false;
            };

            $scope.delete = function (id) {
                VisitReport.delete({id: id},
                    function () {
                        $scope.visitReports = VisitReport.query();
                    });
            };
            $scope.addPurpose = function() {
                $scope.insertedPurpose = {
                    id: null,
                    detail: null

                };
                $scope.visitReport.purposes.push($scope.insertedPurpose);
            };
            $scope.addAction = function() {
                $scope.insertedAction = {
                    id: null,
                    employee: null,
                    status: null,
                    deadLine: null,
                    detail: null

                };
                $scope.visitReport.actions.push($scope.insertedAction);
            };

            $scope.removeAction = function(index){
                $scope.visitReport.actions.splice(index,1);
            }

            $scope.addProduct = function() {
                $scope.insertedProduct = {
                    id: null,
                    visitReportProduct: null,
                    quantity: null,
                    unit: null,
                    unitPrice: null,
                    notes: null

                };
                $scope.visitReport.visitReportProductItems.push($scope.insertedProduct);

            };

            $scope.removeProduct = function(index){
                $scope.visitReport.visitReportProductItems.splice(index,1);
                return null;
            }

            function getById(arr, id){
                for(var d= 0, len = arr.length; d < len; d+=1){
                    if(arr[d].id == id) {
                        return arr[d];
                    }
                }
                return null;

            };
            //Show product
            $scope.showProductName = function(id) {
                return getById($scope.products, id).productName;
            };
            $scope.showSubIndustrySector = function(id) {
                return getById($scope.products, id).subIndustrySector.description;
            };
            $scope.showPackageSize = function(id) {
                return getById($scope.products, id).packageSize;
            };
            $scope.showUnit = function(id) {
                return getById($scope.products, id).unit.name;
            };
            $scope.showPackageType = function(id) {
                return getById($scope.products, id).packageType.name;
            };

            //Show employee
            $scope.showEmployeeName = function(id) {
                return getById($scope.employees, id).fullName;
            };
            $scope.clear = function () {
                $scope.visitReport = {
                    id: null,
                    customer: null,
                    employee: null,
                    contactPerson: null,
                    customerOverview: null,
                    facingProblem: null,
                    date: null,
                    visitReportProductItems: [],
                    purposes: [],
                    actions: []
                }
                };
            $scope.showVisitReportEdit = false;
            $scope.showVisitReportList = true;
            $scope.showMarketSurvey = false;
            };

        app.getNgModule().register.controller('VisitReportController', ['$scope', 'VisitReport', 'Product', 'ActionDetail', 'PurposeDetail',
            'VisitReportProductItem', 'Employee', 'VisitReportCustomer', 'VisitReportProduct', 'Unit', 'SubIndustrySector', 'ProductSector', 'VisitReportSupplier', 'ModalService', visitReportCtrl]);
    }
);

