define(
    ['app', 'services/common/ProductService', 'services/common/IndustrySectorService', 'services/common/BenefitLevelService', 'services/common/PricingTypeService', 'services/common/PackageTypeService', 'services/common/UnitService' , 'services/common/SupplierService', 'services/common/SupplierBuyingPriceDetailService'],
    function(app) {
        'use strict';

        var productCtrl = function ($scope, Product, IndustrySector, BenefitLevel, PricingType, PackageType, Unit, Supplier, SupplierBuyingPriceDetail) {

            $scope.products = Product.query();
            $scope.showProductEdit = false;
            $scope.industrySectors = IndustrySector.query();
            $scope.benefitLevels = BenefitLevel.query();
            $scope.pricingTypes = PricingType.query();
            $scope.packageTypes = PackageType.query();
            $scope.units = Unit.query();
            $scope.suppliers = Supplier.query();
            $scope.supplierBuyingDetails = SupplierBuyingPriceDetail.query();

            $scope.addNew = function() {
                $scope.clear();
                $scope.showProductEdit = true;
            };

            $scope.create = function () {
                Product.save($scope.product,
                    function () {
                        $scope.products = Product.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.product = Product.get({id: id});
                $scope.showProductEdit = true;
            };

            $scope.delete = function (id) {
                Product.delete({id: id},
                    function () {
                        $scope.products = Product.query();
                    });
            };

            $scope.clear = function () {
                $scope.product = {
                    id: null,
                    productName: null,
                    productType: null,
                    description: null,
                    unitPrice: null,
                    unit: null,
                    packageType: null,
                    industrySector: null,
                    benefitLevel: null,
                    pricingType: null,
                    supplierBuyingPriceDetails: []

                };
                $scope.showProductEdit = false;
            };
        };

        app.getNgModule().register.controller('ProductController', ['$scope', 'Product', 'IndustrySector', 'BenefitLevel', 'PricingType', 'PackageType', 'Unit', 'Supplier', 'SupplierBuyingPriceDetail', productCtrl]);
    }
);
