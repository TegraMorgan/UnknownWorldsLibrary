/**
 * 
 */

(function() {
  var app = angular.module('library', []);
  var errorUsername = false;

  app.controller('LibController', function() {
    this.products = books;
  });

  app.controller('LandingController', [ '$rootScope', '$scope', function($rootScope, $scope) {
    /* Properties */
    resetErrors(this);

    this.newCustomer = {};

    /* TODO remove all this section this later */
    $('#loginForm').fadeOut(1, function() {$('#registrationForm').fadeIn("fast");});

    this.newCustomer.username = "TestName";
    this.newCustomer.email = "Test@mail.com";
    this.newCustomer.blockNum = 12;
    this.newCustomer.city = "Haifa";
    /* END OF SECTION TO BE REMOVED */

    this.usernameError = "Username must be unique, can have only English characters and numbers and can't be longer than 10 characters";
    this.emailerror = "Please provide a valid email, that has @ character and a domain dot";
    this.adressError = "Address must contain English characters only, and be longer than 3 characters";
    this.blockError = "Block number has to be a number and can't be empty";
    this.cityError = "City name must contain English characters only, and be longer than 3 characters";
    this.zipError = "ZIP has to be exactly seven numbers";
    this.phoneError = "Phone number must be valid, for example 0545-333-22-11 or 046-222-210";
    this.passError = "Password can be at most 8 characters";
    this.nickError = "Nickname must be unique and up to 20 characters";
    this.photoError = "Not recognized as valid URL";

    /* Functions */

    this.toRegister = function() {
      $('#loginForm').fadeOut("fast", function() {
        $('#registrationForm').fadeIn("fast");
      });
    };
    this.toLogin = function() {
      $('#registrationForm').fadeOut("fast", function() {
        $('#loginForm').fadeIn("fast");
      });
    };
    this.disableButton = function() {
      if ($scope.registerForm.$valid)
        return '';
      else return "disabled='disabled'";
    };

    /** Register new user */
    this.register = function() {
      resetErrors(this);

      /* Input check */
      var passed = true;
      /* Test Username */
      if (this.newCustomer.username == undefined || !testNewUsername(this.newCustomer.username)) {
        passed = false;
        this.newUserNameOk = false;
      }

      /* Test email */
      if (this.newCustomer.email == undefined || !testNewEmail(this.newCustomer.email)) {
        passed = false;
        this.newEmailOk = false;
      }

      /* Test Street name */
      if (this.newCustomer.address == undefined || !testNewStreet(this.newCustomer.address)) {
        passed = false;
        this.newAdressOk = false;
      }

      /* Test block number */
      if (this.newCustomer.blockNum == undefined || !testNewBNum(this.newCustomer.blockNum)) {
        passed = false;
        this.newBlockNumberOk = false;
      }

      /* Test City */
      if (this.newCustomer.city == undefined || !testNewStreet(this.newCustomer.city)) {
        passed = false;
        this.newCityOk = false;
      }

      /* Test ZIP */
      if (this.newCustomer.zip == undefined || !testNewZip(this.newCustomer.zip)) {
        passed = false;
        this.zipError = false;
      }
      
      /* Test Phone */

      /* Test Password */

      /* Test Nickname */

      /* Test Description */

      /* Test photo */

      if (passed == true) {
        alert('user passed all the tests reseting fields');
        this.newCustomer = {};
      }
    };
  } ]);

  var books = [

  {
    name : '48 rules of power',
    price : 12.3,
    description : 'Some description',
    canRead : true,
    canBuy : false,
    reviews : [ {
      author : "user3",
      body : "Some",
    }, {
      author : "user4",
      body : "Review",
    } ]
  }, {
    name : '12 rules to meaningfull life',
    price : 17.1,
    description : 'Some description',
    canRead : true,
    canBuy : true,
    imageURL : "",
    reviews : [ {
      author : "user1",
      body : "Some review",
    }, {
      author : "user2",
      body : "Some review",
    } ]
  } ];

})();
function resetErrors(obj) {
  obj.newUserNameOk = true;
  obj.newEmailOk = true;
  obj.newAdressOk = true;
  obj.newBlockNumberOk = true;
  obj.newCityOk = true;
  obj.newZIPOk = true;
  obj.newPhoneOk = true;
  obj.newPassOk = true;
  obj.newNickOk = true;
  obj.newPhotoOk = true;
}

function testNewUsername(str) {
  var len = str.length;
  if (len > 10) return false;
  var regex1 = /^[a-zA-Z0-9]+$/;
  if (!regex1.test(str)) return false;
  /* TODO test for duplicate user in database */
  return true;
}

function testNewEmail(str) {
  var parts = str.split('@');
  if (parts[1] == null || parts[1].len <= 0) {
    return false;
  }
  return true;
}
function testNewStreet(str) {
  var len = str.length;
  if (len < 4) return false;
  var regex1 = /^[a-zA-Z]+$/;
  if (!regex1.test(str)) {
    return false;
  }
  return true;
}

function testNewBNum(num) {
  var regex1 = /^[0-9]+$/;
  if (!regex1.test(num)) {
    return false;
  }
  return true;
}

function testNewZip(num) {
  var regex1 = /^[0-9]+$/;
  if (!regex.test(num)) {
    return false;
  }
  if (num.length != 7) return false;
  return true;
}