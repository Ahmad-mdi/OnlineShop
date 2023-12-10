app.controller('blogAddCtrl', function ($scope, apiHandler,$rootScope) {
    $scope.data = {};

    $scope.addData = () =>{
        $scope.data.image = $rootScope.uploadedFile;
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
        apiHandler.callPost('blog/add',$scope.data,(response) => {
            $scope.changeMenu('blog-list');//after set switch in blog list page
            Swal.fire({
                title: "success",
                text: "your data insert successfully",
                icon: "success"
            });
        },(error) => {

        },true)
    }
});