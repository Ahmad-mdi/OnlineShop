app.controller('sliderEditCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        if ($rootScope.uploadedFile != null && $rootScope.uploadedFile !== "" && $rootScope.uploadedFile !== undefined){
            $scope.data.image = $rootScope.uploadedFile;
        }
        if ($scope.data.title === undefined || $scope.data.title === "" || $scope.data.title == null) {
            Swal.fire("please enter title!");
            return;
        }
        if ($scope.data.link === undefined || $scope.data.link === "" || $scope.data.link == null) {
            Swal.fire("please enter link!");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null) {
            Swal.fire("please set enable yes/no!");
            ;
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image == null || $scope.data.image === ""){
            Swal.fire("please uploaded image file! ");
            return;
        }
        apiHandler.callPut('slider/update', $scope.data, (response) => {
            $scope.changeMenu('slider-list');
            Swal.fire({
                title: "success",
                text: "your data updated successfully",
                icon: "success"
            });
        }, (error) => {

        }, true)
    }

    //send data for edit and get data:
    $scope.getData = () => {
        apiHandler.callGet('slider/' + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {
        }, true);
    }

    $scope.getData();
});