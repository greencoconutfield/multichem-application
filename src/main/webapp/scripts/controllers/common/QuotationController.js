define(
    ['app', 'services/common/QuotationService', 'services/common/CustomerService', 'services/common/ProductService',
        'services/common/QuotationItemDetailService', 'services/common/ProductPriceHistoryService'],
    function(app) {
        'use strict';

        var quotationCtrl = function ($scope, Quotation,Customer, Product, QuotationItemDetail,ProductPriceHistory ) {

            $scope.quotations = Quotation.query();
            $scope.showQuotationEdit = false;
            $scope.products = Product.query();
            $scope.customers = Customer.query();
            $scope.selectedQuotationItemDetail = null;
            $scope.lastSellingPrices = ['104 - Quo1', '105 - Quo2', '106 - Quo3'];
            $scope.lastReferencePrices = ['100 - Jan', '101 - Feb', '102 - Mar'];
            $scope.currentRatio = 1.3;
            $scope.currentTotal = 0;
            $scope.currentCurrency = "USD";
            $scope.companies = ["Multichem VN Ltd","Hexachem VN Ltd", "Octalub VN Ltd"];
            $scope.notes = ["The above price is without 10% VAT \nDelivery condition: door to door delivery\nLead time: \nPayment term: 30 days from the day receiving goods"];
            $scope.selectedCompany;

            $('#previewQuotation').modal('hide');

            $scope.preview = function() {
                $scope.getTotal();
                $('#previewQuotation').modal('show');


            };

            $scope.cancelPrint = function() {
                $('#previewQuotation').modal('hide');


            };

            $scope.orderQuantityChange = function(orderQuantity) {
                if(orderQuantity < 100){
                    $scope.currentRatio = 1.6;
                }
                else if(orderQuantity < 400){
                    $scope.currentRatio = 1.5;
                }
                else if(orderQuantity < 800){
                    $scope.currentRatio = 1.4;
                }
                else{
                        $scope.currentRatio = 1.3;
                }

            };

            $scope.refresh = function(id) {
                $scope.quotation.customer = Customer.get({id: id});
                $scope.showQuotationEdit = true;

            };

            $scope.refreshProductDetail = function(id) {
                $scope.selectedQuotationItemDetail.product = Product.get({id: id});
                $scope.selectedQuotationItemDetail.unitPrice = $scope.selectedQuotationItemDetail.product.unitPrice
                $scope.selectedQuotationItemDetail.packageQuantity = 0;
                $scope.selectedQuotationItemDetail.unitPrice = 0;
                $scope.currentProductPriceHistories = ProductPriceHistory.query({customerId: null, productId: id});
                $scope.currentCustomerProductPriceHistories = ProductPriceHistory.query({customerId: $scope.quotation.customer.id, productId: id});
                $scope.showQuotationEdit = true;

            };
            $scope.addQuotationItemDetail = function() {
                if ($scope.selectedQuotationItemDetail != null && $scope.selectedQuotationItemDetail.packageQuantity != null) {
                    $scope.quotation.quotationItemDetails.push($scope.selectedQuotationItemDetail);
                    $scope.currentCurrency = $scope.selectedQuotationItemDetail.product.price.currency.name;
                    $scope.selectedQuotationItemDetail = null;
                }
            };

            $scope.updateQuotationDetail = function (id) {
                $scope.selectedQuotationItemDetail = getById($scope.quotation.quotationItemDetails, id);
            };

            $scope.deleteQuotationDetail = function (id) {
                $scope.deleteQuotationItemDetail = getById($scope.quotation.quotationItemDetails, id);
                $scope.deleteIndex = $scope.quotation.quotationItemDetails.indexOf($scope.deleteQuotationItemDetail);
                $scope.quotation.quotationItemDetails.splice($scope.deleteIndex, 1);
            };

            $scope.getTotal = function(){
                var total =0;
                for(var i= 0, len = $scope.quotation.quotationItemDetails.length; i < len; i++){
                    total = total + ($scope.quotation.quotationItemDetails[i].unitPrice * $scope.quotation.quotationItemDetails[i].quantity);
                }
                $scope.currentTotal = total;
            }
            function getById(arr, id){
                for(var d= 0, len = arr.length; d < len; d+=1){
                    if(arr[d].id == id) {
                        return arr[d];
                    }
                }
                return null;

            };

            function addIfNotFound(quotationItemDetail, arr) {
                if (quotationItemDetail instanceof QuotationItemDetail) {
                    var found = arr.some(function (el) {
                        return el.id === quotationItemDetail.id;
                    });
                    if (!found) {
                        arr.push(quotationItemDetail);

                    }
                }
            };

            $scope.printContent = function (el){
                var restorepage = document.body.innerHTML;
                var printcontent = document.getElementById(el).innerHTML;
                document.body.innerHTML = printcontent;
                $('#previewQuotation').modal('hide');
                window.print();
                document.body.innerHTML = restorepage;


            }

            $scope.addNew = function() {
                $scope.clear();
                $scope.quotation.description = $scope.notes.toString();
                $scope.showQuotationEdit = true;
            };

            $scope.switchView = function() {
                $scope.showQuotationEdit = false;
            };

            $scope.create = function () {
                Quotation.save($scope.quotation,
                    function () {
                        $scope.quotations = Quotation.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.quotation = Quotation.get({id: id});
                $scope.showQuotationEdit = true;
            };

            $scope.delete = function (id) {
                Quotation.delete({id: id},
                    function () {
                        $scope.quotations = Quotation.query();
                    });
            };

            $scope.clear = function () {
                $scope.quotation = {
                    id: null,
                    quotationName: null,
                    company: null,
                    quotationType: null,
                    description: null,
                    customer: null,
                    unit: null,
                    unitType: null,
                    unitPrice: null,
                    quotationItemDetails: []
                };
                $scope.selectedQuotationItemDetail = {
                    id: null,
                    product: null,
                    unitPrice: null,
                    pricingType: null,
                    packageQuantity: null,
                    leadTime: null
                };
                $scope.showQuotationEdit = false;
            };

            $scope.clearProductDetail = function(){
                $scope.selectedQuotationItemDetail = {
                    id: null,
                    product: null,
                    unitPrice: null,
                    pricingType: null,
                    quantity: null
                };
            }
        };

        app.getNgModule().register.controller('QuotationController', ['$scope', 'Quotation', 'Customer', 'Product',
            'QuotationItemDetail', 'ProductPriceHistory', quotationCtrl]);
    }
);
