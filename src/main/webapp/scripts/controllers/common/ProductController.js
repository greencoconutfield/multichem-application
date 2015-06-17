
define(
    ['app', 'services/common/ProductService', 'services/common/SubIndustrySectorService','services/common/PricingTypeService',
        'services/common/PackageTypeService', 'services/common/UnitService' , 'services/common/SupplierService',
        'services/common/SupplierBuyingPriceDetailService', 'services/common/CurrencyService', 'services/common/PriceService',
        'services/common/ProductPriceHistoryService', 'services/common/DefaultTaxDetailService'],
    function(app) {
        'use strict';

        var productCtrl = function ($scope, Product, SubIndustrySector, PricingType, PackageType, Unit,
                                    Supplier, SupplierBuyingPriceDetail, Currency,Price, ProductPriceHistory, DefaultTaxDetail) {

            $scope.products = Product.query();
            $scope.defaultTaxDetails = DefaultTaxDetail.query();
            $scope.selectedDefaultTaxDetail = null;
            $scope.showProductEdit = false;
            $scope.showSupplierEdit = false;
            $scope.subIndustrySectors = SubIndustrySector.query({exceptRoot: false});
            $scope.currencies = Currency.query();
            $scope.pricingTypes = PricingType.query();
            $scope.packageTypes = PackageType.query();
            $scope.units = Unit.query();
            $scope.suppliers = Supplier.query();
            $scope.supplierBuyingDetails = SupplierBuyingPriceDetail.query();
            $scope.selectedSupplierBuyingPriceDetail = null;
            $scope.currentProductPriceHistories = [];
            $scope.productPriceHistory = null;
            $scope.productsInCurrentPage = [];
            $scope.taxAndFee = 0;
            $scope.isFOB = false;
            $scope.isCIF = false;
            $scope.isEXWORK = false;
            $scope.itemPerPage = 15;
            $scope.currentPage = 1;
            $scope.today = new Date();

            $scope.getDate = function (dateString){
                var a=  new Date(dateString);
                return a;
            }

            function getProductsInCurrentPage(){
                var i =0;
                while(i < $scope.products.length){
                    if((i < ($scope.itemPerPage * $scope.currentPage)) && (i > ($scope.itemPerPage * ($scope.currentPage-1))-1)){
                        $scope.productsInCurrentPage.push($scope.products.get(i));
                    }
                    i++;
                }

            }

            $scope.reloadDefaultTaxDetail = function(id){
                $scope.selectedDefaultTaxDetail = getById($scope.defaultTaxDetails, id);
                $scope.priceTypeChange($scope.product.price.pricingType.id);
            };

            // Monitor Supplier list
            //var myVar = setInterval(function () {$scope.priceTypeChange($scope.product.price.pricingType.id);}, 1000);
            $scope.priceTypeChange = function(id) {
                if(id==1){
                    $scope.isEXWORK = true;
                    $scope.isCIF = false;
                    $scope.isFOB = false;
                    $scope.product.taxDetail.localClearance = $scope.selectedDefaultTaxDetail.localClearance;
                    $scope.product.taxDetail.freightCost = $scope.selectedDefaultTaxDetail.freightCost;
                    $scope.product.taxDetail.supplierInlandCost =  $scope.selectedDefaultTaxDetail.supplierInlandCost;
                    $scope.product.taxDetail.insurance = $scope.selectedDefaultTaxDetail.insurance;
                    $scope.product.taxDetail.importTax = $scope.selectedDefaultTaxDetail.importTax;
                    $scope.product.taxDetail.adminCharge =  $scope.selectedDefaultTaxDetail.adminCharge;
                    $scope.product.taxDetail.ttFee = $scope.selectedDefaultTaxDetail.ttFee;

//                    $scope.product.taxDetail.localClearance = $scope.product.taxDetail.localClearance>0?$scope.product.taxDetail.localClearance:$scope.defaultTaxDetail.localClearance;
//                    $scope.product.taxDetail.freightCost = $scope.product.taxDetail.freightCost>0?$scope.product.taxDetail.freightCost:$scope.defaultTaxDetail.freightCost;
//                    $scope.product.taxDetail.supplierInlandCost = $scope.product.taxDetail.supplierInlandCost>0?$scope.product.taxDetail.supplierInlandCost: $scope.defaultTaxDetail.supplierInlandCost;
//                    $scope.product.taxDetail.insurance = $scope.product.taxDetail.insurance>0?$scope.product.taxDetail.insurance:$scope.defaultTaxDetail.insurance;
//                    $scope.product.taxDetail.importTax = $scope.product.taxDetail.importTax>0?$scope.product.taxDetail.importTax:$scope.defaultTaxDetail.importTax;
//                    $scope.product.taxDetail.adminCharge = $scope.product.taxDetail.adminCharge>0?$scope.product.taxDetail.adminCharg: $scope.defaultTaxDetail.adminCharge;
//                    $scope.product.taxDetail.ttFee = $scope.product.taxDetail.ttFee>0?$scope.product.taxDetail.ttFee:$scope.defaultTaxDetail.ttFee;
                }
                else if(id==2){
                    $scope.isEXWORK = false;
                    $scope.isCIF = false;
                    $scope.isFOB = true;
                    $scope.product.taxDetail.freightCost =0;
                    $scope.product.taxDetail.supplierInlandCost =  $scope.selectedDefaultTaxDetail.supplierInlandCost;
                    $scope.product.taxDetail.insurance = $scope.selectedDefaultTaxDetail.insurance;
                    $scope.product.taxDetail.importTax = $scope.selectedDefaultTaxDetail.importTax;
                    $scope.product.taxDetail.localClearance = $scope.selectedDefaultTaxDetail.localClearance;
                    $scope.product.taxDetail.adminCharge =  $scope.selectedDefaultTaxDetail.adminCharge;
                    $scope.product.taxDetail.ttFee = $scope.selectedDefaultTaxDetail.ttFee;

                }else if(id==3){
                    $scope.isEXWORK = false;
                    $scope.isCIF = true;
                    $scope.isFOB = true;
                    $scope.product.taxDetail.freightCost =0;
                    $scope.product.taxDetail.supplierInlandCost =0;
                    $scope.product.taxDetail.insurance =0;
                    $scope.product.taxDetail.importTax = $scope.selectedDefaultTaxDetail.importTax;
                    $scope.product.taxDetail.localClearance = $scope.selectedDefaultTaxDetail.localClearance;
                    $scope.product.taxDetail.adminCharge =  $scope.selectedDefaultTaxDetail.adminCharge;
                    $scope.product.taxDetail.ttFee = $scope.selectedDefaultTaxDetail.ttFee;
                }

            };

            function getTaxAndFee(){
                if("EXWORK".localeCompare($scope.product.price.pricingType.name)){
                   var  taxAndFee = $scope.product.taxDetail.importTax;
                }
                else if("FOB".localeCompare($scope.product.price.pricingType.name)){

                }else if("CIF".localeCompare($scope.product.price.pricingType.name)){

                }
                return taxAndFee;
            }

//            $scope.getTaxAndFee = function () {
//                if ($scope.selectedSupplierBuyingPriceDetail != null) {
//                    $scope.product.supplierBuyingPriceDetails = [];
//                    $scope.product.supplierBuyingPriceDetails.push($scope.selectedSupplierBuyingPriceDetail);
//                }
//            };

            $scope.listProducts = function() {
                $scope.showProductEdit = false;
            };


            function getById(arr, id){
                for(var d= 0, len = arr.length; d < len; d+=1){
                    if(arr[d].id == id) {
                        return arr[d];
                    }
                }
                return null;

            };

            $scope.addNew = function() {
                $scope.clear();
                $scope.showProductEdit = true;

            };

            $scope.create = function () {
                var now = new Date();
                $scope.product.price.lastUpdate = now.getTime();
                Product.save($scope.product,
                    function () {
                        $scope.products = Product.query();
                        $scope.clear();
                    });
            };

            $scope.saveProduct = function (product) {
                var now = new Date();
                product.price.lastUpdate = now.getTime();
                Product.save(product,
                    function () {
                        $scope.products = Product.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.product = Product.get({id: id});
                $scope.currentProductPriceHistories = ProductPriceHistory.query({customerId: null, productId: id});
                $scope.showProductEdit = true;

                $scope.priceTypeChange(getById($scope.products, id).price.pricingType.id);
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
                    productType: 'A',
                    description: null,
                    unit: null,
                    packageType: null,
                    minimumOrder: null,
                    subIndustrySector: null,
                    price: null,
                    leadTime: null,
                    supplier: null,
                    taxDetail: {
                        id: null,
                        insurance: null,
                        freightCost: null,
                        localClearance: null,
                        importTax: null,
                        adminCharge: null,
                        ttFee: null,
                        extraFee1: null,
                        extraFee2: null,
                        extraFee3: null
                    }
                    }
                $scope.currentProductPriceHistories = [];
                $scope.showProductEdit = false;
            };

            $scope.currentPage = 1;
            $scope.totalItems = function() {
                if ($scope.products) { // TODO KKS: is this the right way?
                    if ($scope.products.$resolved) {
                        console.log($scope.products.length);
                        return $scope.products.length;
                    }
                    else {
                        $scope.products.$promise.then(function() {
                            console.log($scope.products.length + "..");
                            return $scope.products.length;
                        });
                    }
                }
                else {
                    return 0;
                }
            }
        };
        app.getNgModule().register.controller('ProductController', ['$scope', 'Product', 'SubIndustrySector', 'PricingType',
            'PackageType', 'Unit', 'Supplier', 'SupplierBuyingPriceDetail', 'Currency', "Price", 'ProductPriceHistory', 'DefaultTaxDetail', productCtrl]);
    }
);
