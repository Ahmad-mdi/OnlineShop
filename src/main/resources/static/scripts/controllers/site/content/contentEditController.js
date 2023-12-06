app.controller('contentEditCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () =>{
        if ($scope.data.key === undefined || $scope.data.key === "" || $scope.data.key == null){
            Swal.fire("please enter key!");
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value === "" || $scope.data.value == null){
            Swal.fire("please enter value!");
            return;
        }
        apiHandler.callPut('content/update',$scope.data,(response) => {
            $scope.changeMenu('content-list');
            Swal.fire({
                title: "success",
                text: "your data updated successfully",
                icon: "success"
            });
        },(error) => {

        },true)
    }

    //send data for edit and get data:
    $scope.getData = () =>{
        apiHandler.callGet('content/'+$scope.id,(response) => {
            $scope.data = response.dataList[0];
        },(error) =>{},true);
    }

    $scope.getData();
});