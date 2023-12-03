app.controller("panelCtlr", function ($scope, apiHandler, $cookies, $rootScope) {

    $scope.template = "views/dashboard.html";//dashboard
    $scope.templateName = "dashboard";
    $scope.templateGroup = "dashboard";

    $scope.checkAccess = () => {
        let token = $cookies.get("userToken");
        debugger;
        if (token === undefined || token == null || token === "") {
            location.href = "/login";
            return;
        }
        $scope.getUserInfo();
    }
    $scope.getUserInfo = () => {
        apiHandler.callGet('user/getUserInfo', (response) => {
            debugger;
            $rootScope.userInfo = response.dataList[0];
            $scope.user = $rootScope.userInfo;
            console.log($scope.user);
        }, (error) => {

        }, true);
    }
//activation and selected menu:
    $scope.changeMenu = (templateName) => {
        $scope.templateName = templateName;
        $scope.template = $scope.getMenuPrefix(templateName);
        $scope.templateGroup = $scope.getMenuGroup(templateName);
    }
//for show page.html by angularJs:
    $scope.getMenuPrefix = (templateName) => {
        if (templateName === 'dashboard') {
            return 'views/' + templateName + '.html';
        } else if (templateName === 'nav-list') {
            return 'views/site/nav/' + templateName + '.html';
        } else {
            return 'views/dashboard.html';
        }
    }
// for active item by select user:
    $scope.getMenuGroup = (templateName) => {
        if (templateName === 'dashboard') {
            return 'dashboard';
        } else if (templateName === 'nav-list'
            || templateName === 'nav-edit'
            || templateName === 'nav-insert') {
            return 'nav';
        } else {
            return 'dashboard';
        }
    }

    $scope.checkAccess();
});

