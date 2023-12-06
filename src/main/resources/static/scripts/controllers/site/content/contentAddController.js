app.controller('contentAddCtrl',['$scope','apiHandler','textAngularManager', function ($scope, apiHandler) {
    $scope.data = {};

    $scope.addData = () =>{
        if ($scope.data.key === undefined || $scope.data.key === "" || $scope.data.key == null){
            Swal.fire("please enter key!");
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value === "" || $scope.data.value == null){
            Swal.fire("please enter value!");
            return;
        }
        apiHandler.callPost('content/add',$scope.data,(response) => {
            $scope.changeMenu('content-list');//after set switch in content list page
            Swal.fire({
                title: "success",
                text: "your data insert successfully",
                icon: "success"
            });
        },(error) => {

        },true)
    }
}]);