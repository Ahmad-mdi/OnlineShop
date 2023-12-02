app.controller("panelCtlr", function ($scope, apiHandler, $cookies) {
    $scope.checkAccess = () => {
        let token = $cookies.get("userToken");
        if (token === undefined || token == null || token === ""){
            location.href = "/login";
        }
    }
    $scope.checkAccess();
});