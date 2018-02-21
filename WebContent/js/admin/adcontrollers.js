(function(global) {
  'use strict';
  admod.controller('mainController', [ '$rootScope', '$scope', '$http', 'comms', '$location', function($rootScope, $scope, $http, comms, $location) {
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
      $rootScope.$apply;
    }

    this.navToLogin = function() {
      // ? do i need it?
      $rootScope.secondView = 'pages/login.html'
    };

    this.navToBooks = function() {
      // TODO not implemented
      $rootScope.secondView = 'pages/admin/books.html';
      $('#btnStore').removeClass().addClass('btn navbar-btn btn-default active');
      $('#btnMyBooks').removeClass().addClass('btn navbar-btn btn-default');
    }

    this.navToUsers = function() {
      // TODO not implemented
      $rootScope.secondView = 'pages/admin/users.html';
      $('#btnMyBooks').removeClass().addClass('btn navbar-btn btn-default active');
      $('#btnStore').removeClass().addClass('btn navbar-btn btn-default');
    }

    this.login = function() {
      if (!this.admin.login || !this.admin.password || this.admin.login.length <= 0 || this.admin.password.length <= 0) {
        this.wrongLoginData = true;
        this.wrongLoginMessage = "These fields cannot be empty";
      }
      else if (!testOldUsername(this.admin.login) || !testNewPassword(this.admin.password)) {
        this.wrongLoginData = true;
        this.wrongLoginMessage = "Please recheck your input";
      }
      else {
        this.wrongLoginData = false;
        var ctr = this;
        comms.call('POST', '/AdminLogIn', this.admin, function(data, textStatus, jqXHR) {
          if (data.result == "fail") {
            alert(data.result);
            ctr.wrongLoginData = true;
            ctr.wrongLoginMessage = "Incorrect credentials";
          }
          else {
            $rootScope.user = data.customer;
            ctr.menutoggle();
            ctr.navToStore();
          }
          $rootScope.$apply();
        }, function(data, textStatus, errorThrown) {
          alert('Server error. Please try again');
        }, null);

      }
    };
  } ]);// controller
})();
