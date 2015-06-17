define(
    ['app', 'services/common/VisitReportProductService', 'services/common/SubIndustrySectorService', 'services/common/ProductService', 'services/common/VisitReportSupplierService'
    ,'services/common/ProductSectorService'],
    function(app) {
        'use strict';

        var visitReportProductCtrl = function ($scope, VisitReportProduct, SubIndustrySector, Product, VisitReportSupplier, ProductSector) {

            $scope.visitReportProducts = VisitReportProduct.query();
            $scope.subIndustrySectors = SubIndustrySector.query();
            $scope.suppliers = VisitReportSupplier.query();
            $scope.productSectors = ProductSector.query();
            $scope.products = Product.query();
            $scope.showProductEdit = false;
            $scope.isLinked = false;

            $scope.checkLinkToProduct = function(){
                if(!$scope.isLinked){
                    $scope.visitReportProduct.product = null;
                }

            };

            $scope.addNew = function() {
                $scope.clear();
                $scope.showProductEdit = true;
            };

            $scope.create = function () {
                VisitReportProduct.save($scope.visitReportProduct,
                    function () {
                        $scope.visitReportProducts = VisitReportProduct.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.visitReportProduct = VisitReportProduct.get({id: id});
                $scope.showProductEdit = true;
            };

            $scope.delete = function (id) {
                VisitReportProduct.delete({id: id},
                    function () {
                        $scope.visitReportProducts = VisitReportProduct.query();
                    });
            };
            $scope.clear = function () {
                $scope.visitReportProduct = {
                    id: null,
                    productName: null,
                    productSector: null,
                    supplier: null,
                    type: null,
                    subIndustrySector: null,
                    product: null,
                    description: null
                }

            };
            $scope.showProductEdit = false;
        };

        app.getNgModule().register.controller('VisitReportProductController', ['$scope', 'VisitReportProduct', 'SubIndustrySector', 'Product', 'VisitReportSupplier','ProductSector', visitReportProductCtrl]);
    }
);

