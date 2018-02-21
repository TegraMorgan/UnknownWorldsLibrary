(function(global) {
    'use strict';
    admod.controller('mainController', ['$rootScope', '$scope', '$http', 'comms', '$location', function($rootScope, $scope, $http, comms, $location) {
        /* Properties */
        $rootScope.raceCond = 200;

        /* set watch for ng-include */
        $rootScope.secondView = 'pages/admin/alogin.html';
        $scope.$watch(function() {
            return $rootScope.secondView;
        }, function(newValue, oldValue) {
            $scope.includeView = newValue;
        });

        /* navigation menu variables */
        $rootScope.navmenu = 'pages/admin/adMenu.html';
        $rootScope.menuEnabled = false;

        resetErrors(this);

        this.admin = {};

        /* TODO remove all this section later */
        /*
     * $rootScope.secondView = 'pages/checkout.html';
     * 
     * this.newCustomer.username = "TestName"; this.newCustomer.password = "123";
     */
        /* END OF SECTION TO BE REMOVED */

        /* set controller variables */

        /* login variables */
        this.wrongLoginData = false;

        /* Functions */

        /* navigarion menu toggle */
        this.menutoggle = function() {
            $rootScope.menuEnabled = $rootScope.menuEnabled == true ? false : true;
        }

        this.navToLogin = function() {
            // ? do i need it?
            $rootScope.secondView = 'pages/login.html'
        }
        ;

        this.navToBooks = function() {
            // TODO not implemented
            $rootScope.secondView = 'pages/admin/books.html';
            $('#btnMybooks').removeClass().addClass('btn navbar-btn btn-default active');
            $('#btnUsers').removeClass().addClass('btn navbar-btn btn-default');
        }

        this.navToUsers = function() {
            // TODO not implemented
            console.log('starting navigation function');
            var cnt = this;
            comms.sync('GetAllUsersServlet', null, function(data, textStatus, jqXHR) {
                $rootScope.users = data.customers;
                $rootScope.result = data.result;
                console.log('got response from server');
                console.log($rootScope.users);
                console.log($rootScope.result);
                console.log('toggling console');
                cnt.menutoggle();
                console.log('changing page');
                $rootScope.secondView = 'pages/admin/users.html';
                console.log($rootScope.includeView);
                $('#btnUsers').removeClass().addClass('btn navbar-btn btn-default active');
                $('#btnMybooks').removeClass().addClass('btn navbar-btn btn-default');
                $scope.$apply();
            }, function(data, textStatus, errorThrown) {
                console.log(data);
                console.log(textStatus);
            }, null);
        }

        this.adlogin = function() {
            console.log('login function fired');
            console.log(this);
            console.log(this.admin);
            if (!this.admin.login || !this.admin.password || this.admin.login.length <= 0 || this.admin.password.length <= 0) {
                this.wrongLoginData = true;
                this.wrongLoginMessage = "These fields cannot be empty";
            } else if (!testOldUsername(this.admin.login) || !testNewPassword(this.admin.password)) {
                this.wrongLoginData = true;
                this.wrongLoginMessage = "Please recheck your input";
            } else {
                this.wrongLoginData = false;
                var ctr = this;
                var adm = JSON.stringify(this.admin);
                console.log(adm);
                console.log('Sending request to server');
                comms.sync('AdminLogIn', this.admin, function(data, textStatus, jqXHR) {
                    $rootScope.admin = data.admin;
                    console.log('Got response from server');
                    console.log($rootScope.admin);
                    ctr.navToUsers();
                }, function(data, textStatus, errorThrown) {
                    console.log('login failed');
                    ctr.wrongLoginData = true;
                    ctr.wrongLoginMessage = "Incorrect credentials";
                }, null);

            }
        }
        ;
        // mainController
    }
    ]).controller('usersController', ['$rootScope', '$scope', '$http', 'comms', '$location', function($rootScope, $scope, $http, comms, $location) {
        var ad = $rootScope.admin;
        this.users = $rootScope.users;

    }
    ]);
    // usersController
}
)();
