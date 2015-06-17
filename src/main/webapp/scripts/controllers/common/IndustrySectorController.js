define(
    ['app', 'services/common/IndustrySectorService', 'services/common/SubIndustrySectorService'],
    function(app) {
        'use strict';

        var industrySectorCtrl = function ($scope, IndustrySector, SubIndustrySector) {

            $scope.industrysectors = IndustrySector.query();
            $scope.subIndustrySectors = SubIndustrySector.query({exceptRoot: true});
            $scope.currentSubSector = null;
            $scope.showIndustrySectorEdit = false;

            $('#saveSubIndustrySectorModal').modal('hide');

//            $scope.refreshParentSector = function(id) {
//                //$scope.parentSector = IndustrySector.get({id: id});
//                $scope.industrysector.parent = getById($scope.industrysectors, id);
//
//            };

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
                $scope.showIndustrySectorEdit = true;
            };

            $scope.addSubSector = function(industrysector) {
                $scope.clear();
                $scope.industrysector = industrysector;
                $('#saveSubIndustrySectorModal').modal('show');

            };

            $scope.create = function () {
                $scope.industrysector.description = $scope.industrysector.name;
                IndustrySector.save($scope.industrysector,
                    function () {
                        $scope.industrysectors = IndustrySector.query();

                        $scope.clear();
                    });
            };

            $scope.createSubSector = function (id){
                $scope.industrysector = getById($scope.industrysectors, id);
                $scope.currentSubSector.parent = $scope.industrysector;
                $scope.currentSubSector.description = $scope.industrysector.name + " / " +  $scope.currentSubSector.name;
                SubIndustrySector.save($scope.currentSubSector,
                    function () {
                        $scope.subIndustrySectors = SubIndustrySector.query({exceptRoot: true});
                        $('#saveSubIndustrySectorModal').modal('hide');
                        $scope.clear();
                    });


            };

            $scope.deleteSubSector = function (id) {
                $scope.id = id;
                SubIndustrySector.delete({id: id},
                    function () {
                        $scope.subIndustrySectors = SubIndustrySector.query({exceptRoot: true});
                });
            };

            $scope.updateSubSector = function (id) {
                $scope.currentSubSector = SubIndustrySector.get({id: id});
                $('#saveSubIndustrySectorModal').modal('show');
            };


            $scope.update = function (id) {
                $scope.industrysector = IndustrySector.get({id: id});
               // $('#saveIndustrySectorModal').modal('show');
                $scope.showIndustrySectorEdit = true;
            };

            $scope.delete = function (id) {
                IndustrySector.delete({id: id},
                    function () {
                        $scope.industrysectors = IndustrySector.query();
                    });
            };

            $scope.clear = function () {
                $scope.industrysector = {id: null, name: null, description: null};
                $scope.currentSubSector = {id: null, parent: null, name: null, description: null};

                $scope.showIndustrySectorEdit = false;
            };

            $scope.currentPage = 1;
            $scope.totalItems = function() {
                if ($scope.industrysectors) { // TODO KKS: is this the right way?
                    if ($scope.industrysectors.$resolved) {
                        console.log($scope.industrysectors.length);
                        return $scope.industrysectors.length;
                    }
                    else {
                        $scope.industrysectors.$promise.then(function() {
                            console.log($scope.industrysectors.length + "..");
                            return $scope.industrysectors.length;
                        });
                    }

                }
                else {
                    return 0;
                }

            }




        };

        app.getNgModule().register.controller('IndustrySectorController', ['$scope', 'IndustrySector', 'SubIndustrySector', industrySectorCtrl]);
    }
);
