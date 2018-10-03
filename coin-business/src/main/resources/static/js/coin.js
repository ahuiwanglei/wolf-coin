/**
 * Created by admin on 2017/3/30.
 */
angular.module('app', ['ngMaterial']);
angular.module("app").controller(
    'marketlistCtr',
    function ($scope, $http, $rootScope, $interval,$mdToast,$mdDialog) {

        $scope.init = function () {
            $scope.queryList();
        };

        $scope.list = [];

        $scope.queryList = function () {
            $http({
                method: "POST",
                url: "/api/coinweight?symbol=pst",
                data: {},
                timeout: 20000
            }).then(function (result) {
                if (result.data.resultStatus != 0) {
                    $scope.tip(result.data.msg);
                } else {

                }
            }, function (error) {
                $scope.tip("网络错误");
            });
        };


        $interval($scope.queryList, 20000);


        $scope.tip = function (msg) {
            $mdToast.show (
                $mdToast.simple()
                    .textContent(msg)
                    .hideDelay(3000).position("bottom right")
            );
        }

    });