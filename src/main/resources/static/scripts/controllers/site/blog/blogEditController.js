app.controller('blogEditCtrl', function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;

    $scope.editData = () => {
        if ($rootScope.uploadedFile != null && $rootScope.uploadedFile !== "" && $rootScope.uploadedFile !== undefined){
            $scope.data.image = $rootScope.uploadedFile;
        }
        if ($scope.data.title === undefined || $scope.data.title === "" || $scope.data.title == null){
            Swal.fire("please enter title!");
            return;
        }
        if ($scope.data.subtitle === undefined || $scope.data.subtitle === "" || $scope.data.subtitle == null){
            Swal.fire("please enter title!");
            return;
        }
        if ($scope.data.status === undefined || $scope.data.status == null){
            Swal.fire("please set status yes/no!");
            return;
        }
        if ($scope.data.description === undefined || $scope.data.description === "" || $scope.data.description == null){
            Swal.fire("please enter description!");
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image == null || $scope.data.image === ""){
            Swal.fire("please uploaded image file! ");
            return;
        }
        apiHandler.callPut('blog/update', $scope.data, (response) => {
            $scope.changeMenu('blog-list');
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
        apiHandler.callGet('blog/' + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {
        }, true);
    }

    $scope.getData();
});