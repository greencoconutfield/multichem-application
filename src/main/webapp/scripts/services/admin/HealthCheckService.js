define(
    ['app'],
    function(app) {
        'use strict';

        var healthCheckService = function ($rootScope, $http) {
            return {
                check: function() {
                    var promise = $http.get('health').then(function(response){
                        return response.data;
                    });
                    return promise;
                }
            };
        };

        app.getNgModule().register.factory('HealthCheckService', ['$rootScope', '$http', healthCheckService]);
    }
);
