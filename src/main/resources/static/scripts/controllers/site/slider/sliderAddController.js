app.controller('sliderAddCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};

    $scope.addData = () =>{
        $scope.data.image = $rootScope.uploadedFile;
        if ($scope.data.title === undefined || $scope.data.title === "" || $scope.data.title == null){
            Swal.fire("please enter title!");
            return;
        }
        if ($scope.data.link === undefined || $scope.data.link === "" || $scope.data.link == null){
            Swal.fire("please enter link!");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable == null){
            Swal.fire("please set enable yes/no!");
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image == null || $scope.data.image === ""){
            Swal.fire("please uploaded image file! ");
            return;
        }
        apiHandler.callPost('slider/add',$scope.data,(response) => {
            $scope.changeMenu('slider-list');//after set switch in slider list page
            Swal.fire({
                title: "success",
                text: "your data insert successfully",
                icon: "success"
            });
        },(error) => {

        },true)
    }
});