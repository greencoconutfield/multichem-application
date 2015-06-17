define(
    ['app'],
    function(app) {
        'use strict';

        var auditsService = function ($http) {
            return {
                findAll: function() {
                    var promise = $http.get('app/rest/audits/all').then(function (response) {
                        return response.data;
                    });
                    return promise;
                },
                findByDates: function(fromDate, toDate) {
                    var promise = $http.get('app/rest/audits/byDates', {params: {fromDate: fromDate, toDate: toDate}}).then(function (response) {
                        return response.data;
                    });
                    return promise;
                }
            }
        };

        app.getNgModule().register.factory('AuditsService', ['$http', auditsService]);
    }
);
