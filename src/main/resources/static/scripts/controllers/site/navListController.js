app.controller('navListCtrl', function ($scope, apiHandler) {

    $scope.query = {
        pageSize: 10,
        pageNumber: 0
    };

    $scope.dataList = [];
    $scope.getDataList = () => {
        let url = 'nav/getAll?pageSize=' + $scope.query.pageSize + '&pageNumber=' + $scope.query.pageNumber;
        apiHandler.callGet(url, (response) => {
            $scope.dataList = response.dataList;
        }, (error) => {

        }, true);
    }
    $scope.getDataList();
});