define(['angular'], function (angular) {
'use strict';

    /* Services */

    var yukamAppDirectives = angular.module('yukamApp.directives', []);

    yukamAppDirectives
        .directive('activeMenu', ['$translate', '$locale', 'tmhDynamicLocale', function($translate, $locale, tmhDynamicLocale) {
            return {
                restrict: 'A',
                link: function(scope, element, attrs, controller) {
                    var language = attrs.activeMenu;

                    scope.$watch(function() {
                        return $translate.use();
                    }, function(selectedLanguage) {
                        if (language === selectedLanguage) {
                            tmhDynamicLocale.set(language);
                            element.addClass('active');
                        } else {
                            element.removeClass('active');
                        }
                    });
                }
            };
        }])
        .directive('activeLink', ['$location', function(location) {
            return {
                restrict: 'A',
                link: function(scope, element, attrs, controller) {
                    var clazz = attrs.activeLink;
                    var path = attrs.href;
                    path = path.substring(1); //hack because path does bot return including hashbang
                    scope.location = location;
                    scope.$watch('location.path()', function(newPath) {
                        if (path === newPath) {
                            element.addClass(clazz);
                        } else {
                            element.removeClass(clazz);
                        }
                    });
                }
            };
        }])
        .directive('contactWidget', function() { //TODO KKS: Refactor this into a separate module...
            var editorTemplate =
                '<div><div class="form-group col-md-3">' +
                    '<label>First Name *</label>' +
                    '<input type="text" class="form-control" name="firstName" ng-model="contact.firstName" ng-minlength=1 ng-maxlength=50 required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Last Name *</label>' +
                    '<input type="text" class="form-control" name="lastName" ng-model="contact.lastName" ng-minlength=1 ng-maxlength=50 required>' +
                '</div>' +
                '<div class="form-group col-md-6">' +
                    '<label>Email *</label>' +
                    '<input type="text" class="form-control" name="email" ng-model="contact.email" ng-minlength=1 ng-maxlength=100 required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Mobile</label>' +
                    '<input type="text" class="form-control" name="mobile" ng-model="contact.mobile" ng-maxlength=20>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Home Phone</label>' +
                    '<input type="text" class="form-control" name="homePhone" ng-model="contact.homePhone" ng-maxlength=20>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>fax</label>' +
                    '<input type="text" class="form-control" name="fax" ng-model="contact.fax" ng-maxlength=20>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Phone Other</label>' +
                    '<input type="text" class="form-control" name="phoneOther" ng-model="contact.phoneOther" ng-maxlength=20>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Building Name</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].buildingName">' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Street Number</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].streetNumber" required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Street Name</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].streetName" required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Additional Details</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].additionalDetails">' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>City</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].city" required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>State</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].state" required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Country</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].country" required>' +
                '</div>' +
                '<div class="form-group col-md-3">' +
                    '<label>Post Code</label>' +
                    '<input type="text" class="form-control" name="test" ng-model="contact.addresses[0].postCode" required>' +
                '</div></div>';
            return {
                replace: true,
                restrict: 'E',
                template: editorTemplate,
                scope: {
                    contact: "=contact"
                },
                controller: function($scope) {
                    console.log($scope.contact);
                }
            }
        })
        .directive('angucomplete', function ($parse, $http, $sce, $timeout) {
            // https://github.com/darylrowland/angucomplete
            return {
                restrict: 'EA',
                scope: {
                    "id": "@id",
                    "placeholder": "@placeholder",
                    "selectedObject": "=selectedobject",
                    "url": "@url",
                    "dataField": "@datafield",
                    "titleField": "@titlefield",
                    "descriptionField": "@descriptionfield",
                    "imageField": "@imagefield",
                    "imageUri": "@imageuri",
                    "inputClass": "@inputclass",
                    "userPause": "@pause",
                    "localData": "=localdata",
                    "searchFields": "@searchfields",
                    "minLengthUser": "@minlength",
                    "matchClass": "@matchclass"
                },
                template: '<div class="angucomplete-holder"><input id="{{id}}_value" ng-model="searchStr" type="text" placeholder="{{placeholder}}" class="{{inputClass}}" onmouseup="this.select();" ng-focus="resetHideResults()" ng-blur="hideResults()" /><div id="{{id}}_dropdown" class="angucomplete-dropdown" ng-if="showDropdown"><div class="angucomplete-searching" ng-show="searching">Searching...</div><div class="angucomplete-searching" ng-show="!searching && (!results || results.length == 0)">No results found</div><div class="angucomplete-row" ng-repeat="result in results" ng-click="selectResult(result)" ng-mouseover="hoverRow()" ng-class="{\'angucomplete-selected-row\': $index == currentIndex}"><div ng-if="imageField" class="angucomplete-image-holder"><img ng-if="result.image && result.image != \'\'" ng-src="{{result.image}}" class="angucomplete-image"/><div ng-if="!result.image && result.image != \'\'" class="angucomplete-image-default"></div></div><div class="angucomplete-title" ng-if="matchClass" ng-bind-html="result.title"></div><div class="angucomplete-title" ng-if="!matchClass">{{ result.title }}</div><div ng-if="result.description && result.description != \'\'" class="angucomplete-description">{{result.description}}</div></div></div></div>',

                link: function($scope, elem, attrs) {
                    $scope.lastSearchTerm = null;
                    $scope.currentIndex = null;
                    $scope.justChanged = false;
                    $scope.searchTimer = null;
                    $scope.hideTimer = null;
                    $scope.searching = false;
                    $scope.pause = 500;
                    $scope.minLength = 3;
                    $scope.searchStr = null;

                    if ($scope.minLengthUser && $scope.minLengthUser != "") {
                        $scope.minLength = $scope.minLengthUser;
                    }

                    if ($scope.userPause) {
                        $scope.pause = $scope.userPause;
                    }

                    var isNewSearchNeeded = function(newTerm, oldTerm) {
                        return newTerm.length >= $scope.minLength && newTerm != oldTerm
                    }

                    $scope.processResults = function(responseData, str) {
                        if (responseData && responseData.length > 0) {
                            $scope.results = [];

                            var titleFields = [];
                            if ($scope.titleField && $scope.titleField != "") {
                                titleFields = $scope.titleField.split(",");
                            }

                            for (var i = 0; i < responseData.length; i++) {
                                // Get title variables
                                var titleCode = [];

                                for (var t = 0; t < titleFields.length; t++) {
                                    titleCode.push(responseData[i][titleFields[t]]);
                                }

                                var description = "";
                                if ($scope.descriptionField) {
                                    description = responseData[i][$scope.descriptionField];
                                }

                                var imageUri = "";
                                if ($scope.imageUri) {
                                    imageUri = $scope.imageUri;
                                }

                                var image = "";
                                if ($scope.imageField) {
                                    image = imageUri + responseData[i][$scope.imageField];
                                }

                                var text = titleCode.join(' ');
                                if ($scope.matchClass) {
                                    var re = new RegExp(str, 'i');
                                    var strPart = text.match(re)[0];
                                    text = $sce.trustAsHtml(text.replace(re, '<span class="'+ $scope.matchClass +'">'+ strPart +'</span>'));
                                }

                                var resultRow = {
                                    title: text,
                                    description: description,
                                    image: image,
                                    originalObject: responseData[i]
                                }

                                $scope.results[$scope.results.length] = resultRow;
                            }


                        } else {
                            $scope.results = [];
                        }
                    }

                    $scope.searchTimerComplete = function(str) {
                        // Begin the search

                        if (str.length >= $scope.minLength) {
                            if ($scope.localData) {
                                var searchFields = $scope.searchFields.split(",");

                                var matches = [];

                                for (var i = 0; i < $scope.localData.length; i++) {
                                    var match = false;

                                    for (var s = 0; s < searchFields.length; s++) {
                                        match = match || (typeof $scope.localData[i][searchFields[s]] === 'string' && typeof str === 'string' && $scope.localData[i][searchFields[s]].toLowerCase().indexOf(str.toLowerCase()) >= 0);
                                    }

                                    if (match) {
                                        matches[matches.length] = $scope.localData[i];
                                    }
                                }

                                $scope.searching = false;
                                $scope.processResults(matches, str);

                            } else {
                                $http.get($scope.url + str, {}).
                                    success(function(responseData, status, headers, config) {
                                        $scope.searching = false;
                                        $scope.processResults((($scope.dataField) ? responseData[$scope.dataField] : responseData ), str);
                                    }).
                                    error(function(data, status, headers, config) {
                                        console.log("error");
                                    });
                            }
                        }
                    }

                    $scope.hideResults = function() {
                        $scope.hideTimer = $timeout(function() {
                            $scope.showDropdown = false;
                        }, $scope.pause);
                    };

                    $scope.resetHideResults = function() {
                        if($scope.hideTimer) {
                            $timeout.cancel($scope.hideTimer);
                        };
                    };

                    $scope.hoverRow = function(index) {
                        $scope.currentIndex = index;
                    }

                    $scope.keyPressed = function(event) {
                        if (!(event.which == 38 || event.which == 40 || event.which == 13)) {
                            if (!$scope.searchStr || $scope.searchStr == "") {
                                $scope.showDropdown = false;
                                $scope.lastSearchTerm = null
                            } else if (isNewSearchNeeded($scope.searchStr, $scope.lastSearchTerm)) {
                                $scope.lastSearchTerm = $scope.searchStr
                                $scope.showDropdown = true;
                                $scope.currentIndex = -1;
                                $scope.results = [];

                                if ($scope.searchTimer) {
                                    $timeout.cancel($scope.searchTimer);
                                }

                                $scope.searching = true;

                                $scope.searchTimer = $timeout(function() {
                                    $scope.searchTimerComplete($scope.searchStr);
                                }, $scope.pause);
                            }
                        } else {
                            event.preventDefault();
                        }
                    }

                    $scope.selectResult = function(result) {
                        if ($scope.matchClass) {
                            result.title = result.title.toString().replace(/(<([^>]+)>)/ig, '');
                        }
                        $scope.searchStr = $scope.lastSearchTerm = result.title;
                        $scope.selectedObject = result;
                        $scope.showDropdown = false;
                        $scope.results = [];
                        //$scope.$apply();
                    }

                    var inputField = elem.find('input');

                    inputField.on('keyup', $scope.keyPressed);

                    elem.on("keyup", function (event) {
                        if(event.which === 40) {
                            if ($scope.results && ($scope.currentIndex + 1) < $scope.results.length) {
                                $scope.currentIndex ++;
                                $scope.$apply();
                                event.preventDefault;
                                event.stopPropagation();
                            }

                            $scope.$apply();
                        } else if(event.which == 38) {
                            if ($scope.currentIndex >= 1) {
                                $scope.currentIndex --;
                                $scope.$apply();
                                event.preventDefault;
                                event.stopPropagation();
                            }

                        } else if (event.which == 13) {
                            if ($scope.results && $scope.currentIndex >= 0 && $scope.currentIndex < $scope.results.length) {
                                $scope.selectResult($scope.results[$scope.currentIndex]);
                                $scope.$apply();
                                event.preventDefault;
                                event.stopPropagation();
                            } else {
                                $scope.results = [];
                                $scope.$apply();
                                event.preventDefault;
                                event.stopPropagation();
                            }

                        } else if (event.which == 27) {
                            $scope.results = [];
                            $scope.showDropdown = false;
                            $scope.$apply();
                        } else if (event.which == 8) {
                            $scope.selectedObject = null;
                            $scope.$apply();
                        }
                    });

                }
            };
        })
        .directive('passwordStrengthBar', function() {
            return {
                replace: true,
                restrict: 'E',
                template: '<div id="strength">' +
                          '<small translate="global.messages.validate.newpassword.strength">Password strength:</small>' +
                          '<ul id="strengthBar">' +
                            '<li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li>' +
                          '</ul>' +
                        '</div>',
                link: function(scope, iElement, attr) {
                    var strength = {
                        colors: ['#F00', '#F90', '#FF0', '#9F0', '#0F0'],
                        mesureStrength: function (p) {

                            var _force = 0;
                            var _regex = /[$-/:-?{-~!"^_`\[\]]/g; // "

                            var _lowerLetters = /[a-z]+/.test(p);
                            var _upperLetters = /[A-Z]+/.test(p);
                            var _numbers = /[0-9]+/.test(p);
                            var _symbols = _regex.test(p);

                            var _flags = [_lowerLetters, _upperLetters, _numbers, _symbols];
                            var _passedMatches = $.grep(_flags, function (el) { return el === true; }).length;

                            _force += 2 * p.length + ((p.length >= 10) ? 1 : 0);
                            _force += _passedMatches * 10;

                            // penality (short password)
                            _force = (p.length <= 6) ? Math.min(_force, 10) : _force;

                            // penality (poor variety of characters)
                            _force = (_passedMatches == 1) ? Math.min(_force, 10) : _force;
                            _force = (_passedMatches == 2) ? Math.min(_force, 20) : _force;
                            _force = (_passedMatches == 3) ? Math.min(_force, 40) : _force;

                            return _force;

                        },
                        getColor: function (s) {

                            var idx = 0;
                            if (s <= 10) { idx = 0; }
                            else if (s <= 20) { idx = 1; }
                            else if (s <= 30) { idx = 2; }
                            else if (s <= 40) { idx = 3; }
                            else { idx = 4; }

                            return { idx: idx + 1, col: this.colors[idx] };
                        }
                    };
                    scope.$watch(attr.passwordToCheck, function(password) {
                        if (password) {
                            var c = strength.getColor(strength.mesureStrength(password));
                            iElement.removeClass('ng-hide');
                            iElement.find('ul').children('li')
                                .css({ "background": "#DDD" })
                                .slice(0, c.idx)
                                .css({ "background": c.col });
                        }
                    });
                }
            }
        })
        .directive('ngConfirmClick', [function() {
            return {
                priority: 1,
                link: function(scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.ngClick;
                    attr.ngClick = "";
                    element.bind('click', function(event) {
                        if (window.confirm(msg)) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
        }
    ]);

    return yukamAppDirectives;
});
