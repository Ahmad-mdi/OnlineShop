app.controller('navAddCtrl', function ($scope, apiHandler) {
    $scope.data = {};

    $scope.addData = () =>{
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
        apiHandler.callPost('nav/add',$scope.data,(response) => {
            $scope.changeMenu('nav-list');//after set switch in nav list page
            Swal.fire({
                title: "success",
                text: "your data insert successfully",
                icon: "success"
            });
        },(error) => {

        },true)
    }
});