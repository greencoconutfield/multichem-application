define(
    ['app'],
    function(app) {
        'use strict';

        var threadDumbService = function ($http) {
            return {
                dump: function() {
                    var promise = $http.get('dump').then(function(response){
                        return response.data;
                    });
                    return promise;
                }
            };
        }

        app.getNgModule().register.factory('ThreadDumpService', ['$http', threadDumbService]);
    }
);
