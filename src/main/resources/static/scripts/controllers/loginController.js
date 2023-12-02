app.controller("loginCtlr", function ($scope, apiHandler, $cookies) {
    $scope.user = {};
    $scope.doLogin = () => {
        apiHandler.callPost(
            'user/login',
            $scope.user,
            (response) => {
                let token = response.dataList[0].token ;
                if(token == null || token == ""){
                    alert("invalid token");
                    return;
                }
                $cookies.put("userToken",token);
                location.href = "/panel";
            }, (error) => {
            });
    }
});