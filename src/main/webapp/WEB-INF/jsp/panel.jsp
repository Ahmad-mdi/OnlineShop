<!DOCTYPE html>
<html lang="en-US">
<head>
    <title>Online Shop App | Panel</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link href="libs/bootstrap-4.5.2-dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="libs/jquery-3.5.1.min.js"></script>
    <script src="libs/bootstrap-4.5.2-dist/js/bootstrap.min.js"></script>
    <script src="libs/angular.min.js"></script>
    <script src="libs/angular-cookies.js"></script>
    <script src="libs/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <link href="libs/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet"/>
    <link href="libs/sweetalert2/dist/sweetalert2.min.css" rel="stylesheet"/>
    <link href="libs/textAngular-1.5.16/dist/textAngular.css" rel="stylesheet"/>
    <script src="libs/textAngular-1.5.16/dist/textAngular-rangy.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular-sanitize.min.js"></script>
    <script src="libs/textAngular-1.5.16/dist/textAngular.min.js"></script>
    <script src="scripts/app.js"></script>
    <script src="scripts/services/ApiHandler.js"></script>
    <script src="scripts/directives/fileModel.js"></script>
    <script src="scripts/controllers/util/uploadFileController.js"></script>
    <script src="scripts/controllers/util/getFilesCtrl.js"></script>
    <script src="scripts/controllers/PanelController.js"></script>
    <script src="scripts/controllers/site/nav/navListController.js"></script>
    <script src="scripts/controllers/site/nav/navAddController.js"></script>
    <script src="scripts/controllers/site/nav/navEditController.js"></script>
    <script src="scripts/controllers/site/content/contentListController.js"></script>
    <script src="scripts/controllers/site/content/contentAddController.js"></script>
    <script src="scripts/controllers/site/content/contentEditController.js"></script>
    <script src="scripts/controllers/site/slider/sliderListController.js"></script>
    <script src="scripts/controllers/site/slider/sliderAddController.js"></script>
    <script src="scripts/controllers/site/slider/sliderEditController.js"></script>
    <link rel="stylesheet" href="styles/panel.css">

</head>
<body ng-app="onlineShopApp">

<div class="container-fluid" ng-controller="panelCtlr">
    <div class="col p-0">
        <div class="panel-header">
            <a href="/logout" class="btn btn-danger btn-sm">Logout</a>
        </div>
    </div>
    <div class="row">
        <div class="col-2 p-0">
            <div class="side-nav">
                <div class="text-center p-3">
                    <img src="images/useravatar.webp" width="100" alt="avatar">
                    <br>
                    <span>{{user.fullName}}</span> <!--call by angular api-->
                </div>
                <ul>
                    <li ng-class="{'side-nav-active':templateGroup == 'dashboard'}">
                        <a href="#" ng-click="changeMenu('dashboard')">
                            <i class="fa fa-solar-panel"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup == 'nav'}">
                        <a href="#" ng-click="changeMenu('nav-list')">
                            <i class="fa fa-link"></i>
                            <span>Navigation</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup == 'content'}">
                        <a href="#" ng-click="changeMenu('content-list')">
                            <i class="fa fa-file-pdf"></i>
                            <span>Content</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup == 'slider'}">
                        <a href="#" ng-click="changeMenu('slider-list')">
                            <i class="fa fa-photo-video"></i>
                            <span>Sliders</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-newspaper"></i>
                            <span>Blog</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-cubes"></i>
                            <span>Products</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-users"></i>
                            <span>Users</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-shopping-bag"></i>
                            <span>Customers</span>
                        </a>
                    </li>
                    <li ng-class="{'side-nav-active':templateGroup == 'uploader'}">
                        <a href="#" ng-click="changeMenu('uploader')">
                            <i class="fa fa-file-import"></i>
                            <span>File Manager</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col p-0">
            <div class="main-container" ng-include="template">

            </div>
        </div>
    </div>
</div>

</body>
</html>