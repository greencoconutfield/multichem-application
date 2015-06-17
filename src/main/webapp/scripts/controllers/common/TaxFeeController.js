define(
    ['app', 'services/common/DefaultTaxDetailService'],
    function(app) {
        'use strict';

        var taxFeeCtrl = function ($scope, DefaultTaxDetail) {

            $scope.defaultTaxDetails = DefaultTaxDetail.query();
            $scope.showDefaultTaxDetailEdit = false;

            $scope.create = function () {
                DefaultTaxDetail.save($scope.defaultTaxDetail,
                    function () {
                        $scope.defaultTaxDetails = DefaultTaxDetail.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.defaultTaxDetail = DefaultTaxDetail.get({id: id});
                $scope.showDefaultTaxDetailEdit = true;
            };

            $scope.delete = function (id) {
                DefaultTaxDetail.delete({id: id},
                    function () {
                        $scope.dedaulTaxDetails = DefaultTaxDetail.query();
                    });
            };
            $scope.clear = function () {
                $scope.defaultTaxDetail = {
                    id: null,
                    region: null,
                    localClearance: null,
                    supplierInlandCost: null,
                    freightCost: null,
                    insurance: null,
                    importTax: null,
                    adminCharge: null,
                    ttFee: null,
                    extraFee1: null,
                    extraFee2: null,
                    extraFee3: null
                }
                $scope.showDefaultTaxDetailEdit = false;

            };
        };

        app.getNgModule().register.controller('TaxFeeController', ['$scope', 'DefaultTaxDetail', taxFeeCtrl]);
    }
);

