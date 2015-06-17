define(
    ['app', 'services/common/ProductSectorService', 'services/common/SubIndustrySectorService'],
    function(app) {
        'use strict';

        var productSectorCtrl = function ($scope, ProductSector, SubIndustrySector) {

            $scope.productSectors = ProductSector.query();
            $scope.subIndustrySectors = SubIndustrySector.query();
            $scope.showProductSectorEdit = false;

            $scope.addNew = function () {
                $scope.clear();
                $scope.showProductSectorEdit = true;
            };

            $scope.create = function () {
                ProductSector.save($scope.productSector,
                    function () {
                        $scope.productSectors = ProductSector.query();
                        $scope.clear();
                    });
            };

            $scope.update = function (id) {
                $scope.clear();
                $scope.productSector = ProductSector.get({id: id});
                $scope.showProductSectorEdit = true;
            };

            $scope.delete = function (id) {
                ProductSector.delete({id: id},
                    function () {
                        $scope.productSectors = ProductSector.query();
                    });
            };
            $scope.clear = function () {
                 $scope.productSector = {
                        id: null,
                        name: null,
                        description: null,
                        subIndustrySector: null
            };
                $scope.showProductSectorEdit = false;
            };
        };

        app.getNgModule().register.controller('ProductSectorController', ['$scope', 'ProductSector','SubIndustrySector', productSectorCtrl]);
    }
);

