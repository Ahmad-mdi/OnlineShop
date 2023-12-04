app.controller('navEditCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () =>{
        if ($scope.data.title === undefined || $scope.data.title === "" || $scope.data.title == null){
            Swal.fire("please enter title!");
            return;
        }
        if ($scope.data.link === undefined || $scope.data.link === "" || $scope.data.link == null){
            Swal.fire("please enter link!");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null){
            Swal.fire("please set enable yes/no!");;
            return;
        }
        apiHandler.callPut('nav/update',$scope.data,(response) => {
            $scope.changeMenu('nav-list');
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
        apiHandler.callGet('nav/'+$scope.id,(response) => {
            $scope.data = response.dataList[0];
        },(error) =>{},true);
    }

    $scope.getData();
});