/**
 * http://usejsdoc.org/
 */
(function(global) {
  'use strict';
  
  var app = angular.module('library', []);

  app.controller('LibController', function() {
    this.products = books;
  });

  var books = [

  {
    name : '48 laws of power',
    price : 12.3,
    description : 'Some description',
    canRead : true,
    canBuy : false,
    imageURL : "http://patrickbetdavid.com/wp-content/uploads/2015/04/48lawspower.jpg",
    reviews : [ {
      author : "user3",
      body : "Some",
    }, {
      author : "user4",
      body : "Review",
    } ]
  }, {
    name : '12 rules for life',
    price : 17.1,
    description : 'Some description',
    canRead : true,
    canBuy : true,
    imageURL : "https://dynamic.indigoimages.ca/books/0345816021.jpg?width=400&quality=100&lang=en",
    reviews : [ {
      author : "user1",
      body : "Some review",
    }, {
      author : "user2",
      body : "Some review",
    } ]
  } ];

  app.controller('LandingController', [ '$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {
    /* Properties */
    resetErrors(this);

    this.newCustomer = {};

    /* TODO remove all this section this later */
/*
    this.newCustomer.username = "TestName";
    this.newCustomer.email = "Test@mail.com";
    this.newCustomer.street = "Galil";
    this.newCustomer.blockNum = 12;
    this.newCustomer.city = "Haifa";
    this.newCustomer.zip = 1234567;
    this.newCustomer.phone = '046-222-210';
    this.newCustomer.password = "123";
    this.newCustomer.password2 = "123";
    this.newCustomer.nickname = "Megapihar";
    this.newCustomer.description = "Desc";
    this.newCustomer.photo = "http://www.goolag.com";
*/
    /* END OF SECTION TO BE REMOVED */

    /* set navigation variables */
    $scope.navLogin = false;
    $scope.navRegister = false;
    $scope.navLibrary = true;
    // more to come

    /* set controller variables */

    /* login variables */
    this.wrongLoginData = false;

    /* Register variables */
    var fieldEmpty = "This is a reqired field";
    var usernameError = "Username must be unique, can have only English characters and numbers and can't be longer than 10 characters";
    var emailerror = "Please provide a valid email, that has @ character and a domain dot";
    var streetError = "Address must contain English characters only, and be longer than 3 characters";
    var blockError = "Block number has to be a number and can't be empty";
    var cityError = "City name must contain English characters only, and be longer than 3 characters";
    var zipError = "ZIP has to be exactly seven numbers";
    var phoneError = "Phone number must be valid, for example 0545-333-22-11 or 046-222-210";
    var passError = "Password can be at most 8 characters and can't be empty";
    var pass2Error = "Passwords don't match";
    var nickError = "Nickname must be unique and up to 20 characters";
    var photoError = "Not recognized as valid URL";

    this.usernameError = "Username must be unique, can have only English characters and numbers and can't be longer than 10 characters";
    this.emailerror = "Please provide a valid email, that has @ character and a domain dot";
    this.streetError = "Address must contain English characters only, and be longer than 3 characters";
    this.blockError = "Block number has to be a number and can't be empty";
    this.cityError = "City name must contain English characters only, and be longer than 3 characters";
    this.zipError = "ZIP has to be exactly seven numbers";
    this.phoneError = "Phone number must be valid, for example 0545-333-22-11 or 046-222-210";
    this.passError = "Password can be at most 8 characters and can't be empty";
    this.pass2Error = "Passwords don't match";
    this.nickError = "Nickname must be unique and up to 20 characters";
    this.descError = "No more than 50 characters please";
    this.photoError = "Not recognized as valid URL";

    /* Functions */

    this.navToRegister = function() {
      $scope.navLogin = false;
      $scope.navRegister = true;
    };

    this.navToLogin = function() {
      $scope.navRegister = false;
      $scope.navLogin = true;
    };

    this.login = function() {
      if (!this.oldCustomer.username || !this.oldCustomer.password || this.oldCustomer.username.length <= 0 || this.oldCustomer.password.length <= 0) {
        this.wrongLoginData = true;
        this.wrongLoginMessage = "These fields cannot be empty";
      }
      else if (!testOldUsername(this.oldCustomer.username) || !testNewPassword(this.oldCustomer.password)) {
        this.wrongLoginData = true;
        this.wrongLoginMessage = "Please recheck your input";
      }
      else {
        this.wrongLoginData = false;
        /* TODO check the server for correct information */
      }
    };

    /** Register new user */
    this.register = function() {
      resetErrors(this);

      /* Input check */
      var passed = true;
      /* Test Username */
      if (this.newCustomer.username == undefined) {
        this.usernameError = fieldEmpty;
        passed = false;
        this.newUserNameOk = false;
      }
      else if (!testNewUsername(this.newCustomer.username)) {
        this.usernameError = usernameError;
        passed = false;
        this.newUserNameOk = false;
      }

      /* Test email */
      if (this.newCustomer.email == undefined) {
        this.emailerror = fieldEmpty;
        passed = false;
        this.newEmailOk = false;
      }
      else if (!testNewEmail(this.newCustomer.email)) {
        this.emailerror = emailerror;
        passed = false;
        this.newEmailOk = false;
      }

      /* Test Street name */
      if (this.newCustomer.street == undefined) {
        this.streetError = fieldEmpty;
        passed = false;
        this.newStreetOk = false;
      }
      else if (!testNewStreet(this.newCustomer.street)) {
        this.streetError = streetError;
        passed = false;
        this.newStreetOk = false;
      }

      /* Test block number */
      if (this.newCustomer.blockNum == undefined) {
        this.blockError = fieldEmpty;
        passed = false;
        this.newBlockNumberOk = false;
      }
      else if (!testNewBNum(this.newCustomer.blockNum)) {
        this.blockError = blockError;
        passed = false;
        this.newBlockNumberOk = false;
      }

      /* Test City */
      if (this.newCustomer.city == undefined) {
        this.cityError = fieldEmpty;
        passed = false;
        this.newCityOk = false;
      }
      else if (!testNewStreet(this.newCustomer.city)) {
        this.cityError = cityError;
        passed = false;
        this.newCityOk = false;
      }

      /* Test ZIP */
      if (this.newCustomer.zip == undefined) {
        this.zipError = fieldEmpty;
        passed = false;
        this.newZIPOk = false;
      }
      else if (!testNewZip(this.newCustomer.zip)) {
        this.zipError = zipError;
        passed = false;
        this.newZIPOk = false;
      }

      /* Test Phone */
      if (this.newCustomer.phone == undefined) {
        this.phoneError = fieldEmpty;
        passed = false;
        this.newPhoneOk = false;
      }
      else if (!testNewPhone(this.newCustomer.phone)) {
        this.phoneError = phoneError;
        passed = false;
        this.newPhoneOk = false;
      }
      else {
        this.newCustomer.phone = reformatPhone(this.newCustomer.phone);
      }

      /* Test Password */
      if (this.newCustomer.password == undefined) {
        this.passError = fieldEmpty;
        passed = false;
        this.newPassOk = false;
      }
      else if (!testNewPassword(this.newCustomer.password)) {
        this.passError = passError;
        passed = false;
        this.newPassOk = false;
      }
      else
      /* Test repeat password */
      if (this.newCustomer.password2 == undefined) {
        this.pass2Error = fieldEmpty;
        passed = false;
        this.newPass2Ok = false;
      }
      else if (this.newCustomer.password2 != this.newCustomer.password) {
        this.pass2Error = pass2Error;
        passed = false;
        this.newPass2Ok = false;
      }

      /* Test Nickname */
      if (this.newCustomer.nickname == undefined) {
        this.nickError = fieldEmpty;
        passed = false;
        this.newNickOk = false;
      }
      else if (!testNewNick(this.newCustomer.nickname)) {
        this.nickError = nickError;
        passed = false;
        this.newNickOk = false;
      }

      /* Test Description */
      if (this.newCustomer.description != undefined && !testNewDescription(this.newCustomer.description)) {
        passed = false;
        this.newDescOk = false;
      }

      /* Test photo */
      if (this.newCustomer.photo != undefined && !testNewPhoto(this.newCustomer.photo)) {
        passed = false;
        this.newPhotoOk = false;
      }

      if (passed == true) {
        /* send this.newCustomer to server */
        alert('user passed all the tests reseting fields');
        this.newCustomer = {};
      }
    };
  } ]);
})();
