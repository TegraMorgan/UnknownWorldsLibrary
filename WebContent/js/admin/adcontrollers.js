(function(global) {
  'use strict';
  admod.controller('mainController', [ '$rootScope', '$scope', '$http', 'comms', '$location', function($rootScope, $scope, $http, comms, $location) {
    /* Properties */
    $rootScope.raceCond = 200;
    var ctr = this;
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

    $rootScope.navToDetailsUser = function(unik) {
      console.log('navigating to user page using nickname');
      console.log('sending to server ' + unik);
      comms.sync('DetailedUserByNick', unik, function(data, textStatus, jqXHR) {
        $rootScope.us = data.user;
        console.log($rootScope.secondView);
        $rootScope.secondView = 'pages/admin/userDetails.html';
        $('#btnUsers').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $('#btnMybooks').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $('#btnReviews').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $scope.$apply();
      }, function(data, textStatus, errorThrown) {
        console.log(textStatus);
      }, null);

    } // end of navToDetailsUser

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
    };

    this.navToBooks = function() {
      var cnt = this;
      $('#myNavBar').collapse('hide');
      comms.sync('GetBookList', null, function(data, textStatus, jqXHR) {
        // success
        $rootScope.books = data.books;
        /*- -*/
        $scope.books.forEach(function(el) {
          /* if there are zero likes disable popover */
          if (el.likescount == 0) {
            $('#btnLike' + el.bid).popover('destroy');
          }
          /* dim all the buttons */
          if (el.reviewCount == 0) {
            $('#btnRev' + el.bid).addClass('disabled');
          }
          var alllikes = "<ul class=\"list-unstyled text-info\">";
          el.likescount = el.likes.length;
          if (el.likes.length != 0) {
            el.likes.forEach(function(li) {
              alllikes = alllikes + "<li>";
              alllikes = alllikes + "<button class=\"userDetails-";
              alllikes = alllikes + li + "\">" + li + "</button></li>";
            }); // foreach
          } // if
          alllikes = alllikes + "</ul>"
          el.likesstring = alllikes;
          el.reviewCount = el.reviews.length;
        });
        /* */
        $rootScope.secondView = 'pages/admin/adstore.html';
        $('#btnMybooks').removeClass().addClass('btn btn-xs navbar-btn btn-default active');
        $('#btnUsers').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $('#btnReviews').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $scope.$apply();
      }, function(data, textStatus, errorThrown) {
        console.log('Could not receive books from the server');
        // fail
      }, null);
    }

    $rootScope.navToUsers2 = function() {
      ctr.navToUsers();
    }

    this.navToUsers = function() {
      var cnt = this;
      $('#myNavBar').collapse('hide');
      comms.sync('GetAllUsersServlet', null, function(data, textStatus, jqXHR) {
        $rootScope.users = data.customers;
        $rootScope.result = data.result;
        $rootScope.secondView = 'pages/admin/users.html';
        $('#btnUsers').removeClass().addClass('btn btn-xs navbar-btn btn-default active');
        $('#btnMybooks').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $('#btnReviews').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $scope.$apply();
      }, function(data, textStatus, errorThrown) {
        console.log(textStatus);
      }, null);
    }

    this.adlogin = function() {
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
        var adm = JSON.stringify(this.admin);
        comms.sync('AdminLogIn', this.admin, function(data, textStatus, jqXHR) {
          $rootScope.admin = data.admin;
          ctr.menutoggle();
          ctr.navToUsers();
        }, function(data, textStatus, errorThrown) {
          ctr.wrongLoginData = true;
          ctr.wrongLoginMessage = "Incorrect credentials";
        }, null);

      }
    }; // admin login

    $rootScope.BooksFilter = function(subbstr) {
      return function(book) {
        if (typeof subbstr !== "undefined" && subbstr !== null) {
          if (subbstr.length == 0) return book;
          var nam = book.name.toLowerCase();
          var auth = book.author.toLowerCase();
          var subs = subbstr.toLowerCase();
          if (nam.includes(subs) || auth.includes(subs)) {
            return book;
          }
        }
        else {
          return book;
        }
      };
    }; // book filter

    this.navToDetails = function(uid) {
      // $rootScope.us=uid;
      var ctr = this;
      comms.sync('DetailedUser', uid, function(data, textStatus, jqXHR) {
        $rootScope.us = data.user;
        $rootScope.secondView = 'pages/admin/userDetails.html';
        $('#btnUsers').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $('#btnMybooks').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $('#btnReviews').removeClass().addClass('btn btn-xs navbar-btn btn-default');
        $scope.$apply();
      }, function(data, textStatus, errorThrown) {
        console.log(textStatus);
      }, null);
    };

    // mainController
  } ]).controller('usersController', [ '$rootScope', '$scope', '$http', 'comms', '$location', function($rootScope, $scope, $http, comms, $location) {

    var ctr = this;

    this.navToUsers3 = function() {
      $rootScope.navToUsers2();
    };

    /* DeleteUser modal manipulation */

    $('#deleteUser').on('show.bs.modal', function(event) {
      var button = $(event.relatedTarget); // button that triggered the event
      var uId = button.data('user'); // get user id from the button
      var delUs = $rootScope.users.find(function(usr) {
        return usr.uid == uId;
      });
      var mo = $(this);
      mo.find('.modal-title').text('Warning! Deleting ' + delUs.nickname + ' cannot be undone!');
      var btn = mo.find('.modal-footer #btndeleteUser')[0];
      btn.removeAttribute("data-user");
      btn.setAttribute("data-user", uId);
      console.log('setting uid as ' + uId);

    }); // end modal manipulation

    /* Delete user button pressed */

    $rootScope.deleteUser = function() {
      var btn = $('#btndeleteUser')[0];
      var uid = btn.getAttribute('data-user');
      comms.sync('/removeCustomer', uid, function(data, textStatus, jqXHR) {
        $('#deleteUser').modal('hide');
        ctr.navToUsers3();
      }, function(data, textStatus, errorThrown) {
        console.log(textStatus);
      }, null);
    }
    /* end delete user buton */

  } // usersController
  ]).controller(
      'booksController',
      [
          '$rootScope',
          '$scope',
          '$http',
          '$compile',
          'comms',
          '$location',
          function($rootScope, $scope, $http, $compile, comms, $location) {

            var ctr = this;
            this.books = $rootScope.books;

            $scope.test = function() {
              alert('a');
            };
            angular.element(document).ready(function() {

            }); // end document ready

            $scope.$watch(function() {
              return $scope.searchBook;
            }, function(newValue, oldValue) {
              setTimeout(function() {
                $('[data-toggle="popover"]').popover();
                $('.mypop').on(
                    "mouseover",
                    function() {
                      $(this).popover('show');
                      var a = $(this);
                      if ($scope.users.length != 0) {
                        $scope.users.forEach(function(el) {
                          var getel = $('.userDetails-' + el.nickname);
                          if (getel.length != 0) {
                            var but = "<button class=\"btn btn-xs btn-default userDetails-" + el.nickname + "\" type=\"button\" ng-click=\"navToDetailsUser('" + el.nickname + "')\">" + el.nickname
                                + "</button></li>";
                            var angbut = $compile(angular.element(but))($scope);
                            $('.userDetails-' + el.nickname).replaceWith(angbut);
                          }
                        }); // end foreach user
                      }// end if length != 0

                      setTimeout(function() {
                        a.popover('hide')
                      }, 30000);
                    });
                $scope.books.forEach(function(el) {
                  /* if there are zero likes disable popover */
                  if (el.likescount == 0) {
                    $('#btnLike' + el.bid).popover('destroy');
                  }
                  /* dim all the buttons */
                  if (el.reviewCount == 0) {
                    $('#btnRev' + el.bid).addClass('disabled');
                  }
                  var alllikes = "<ul class=\"list-unstyled text-info\">";
                  el.likescount = el.likes.length;
                  if (el.likes.length != 0) {
                    el.likes.forEach(function(li) {
                      alllikes = alllikes + "<li>" + li + "</li>";
                    }); // foreach
                  } // if
                  alllikes = alllikes + "</ul>"
                  el.likesstring = alllikes;
                  el.reviewCount = el.reviews.length;

                  // $('#btnLike' + el.bid)[0].onmouseover = function() {$('#btnLike' + el.bid)[0].popover();};// btnLike{{product.bid}}

                });// forEach products
              } // refresh function
              , $rootScope.raceCond);
            });
            // booksController
          } ]).controller('detailsController', [ '$rootScope', '$scope', '$http', 'comms', '$location', function($rootScope, $scope, $http, comms, $location) {

    var ctr = this;
    this.navToUsers4 = function() {
      $rootScope.navToUsers2();
    };
    /* Delete user button pressed */

    $rootScope.deleteUser = function(uid) {
      comms.sync('/removeCustomer', uid, function(data, textStatus, jqXHR) {
        $('#deleteUserD').modal('hide');

      }, function(data, textStatus, errorThrown) {
        console.log(textStatus);
      }, null);
    }

    $('#deleteUserD').on('hidden.bs.modal', function() {
      ctr.navToUsers4();
    });

    /* end delete user buton */

  } ]); // detailsController

})();
