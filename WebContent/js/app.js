/**
 * 
 */

(function() {
	var app = angular.module('library', []);
	var errorUsername = false;
	app.controller('LibController',function() {
		this.products = books;
	});
	app.controller('LandingController',function() {
		/* Properties */
		// TODO: don't forget to change these two back
		this.userLogin = false;
		this.userRegister = true;
		
		this.errorUsername = false;
		this.error1 = "";
		this.error2 = "";
		this.authFailureWarningHidden = true;
		this.newUserNameOk = true;
		this.newEmailOk = true;
		this.newAdressOk = true;
		this.newBlockNumberOk = true;
		this.newCityOk = true;
		this.newZIPOk = true;
		this.newPhoneOk = true;
		this.newPassOk = true;
		this.newNickOk = true;
		this.newPhotoOk = true;
		
		this.newCustomer = {};

		this.usernameError = "Username must be unique, can have only English characters and can't be longer than 10 characters";
		this.emailerror = "Please provide a valid email, that has @ character and a domain dot";
		this.adressError = "Address must contain English characters only, and be longer than 3 characters";
		this.blockError = "Block number has to be a number";
		this.cityError = "City name must contain English characters only, and be longer than 3 characters";
		this.zipError = "ZIP has to be exactly seven numbers";
		this.phoneError = "Phone number must be valid 0545-333-22-11 or 046-222-210";
		this.passError = "Password can be at most 8 characters";
		this.nickError = "Nickname must be unique and up to 20 characters";
		this.photoError = "Not recognized as valid URL";

		/* Functions */

		this.toRegister = function() {
			this.userLogin = false;
			this.userRegister = true;
		};
		this.toLogin = function() {
			this.userLogin = true;
			this.userRegister = false;
		};
		/** Register new user */
		this.register = function() {
			/* Input check */
			var passed = true;
			/* Test Username */
			if (!testNewUsername())
				passed = false;
			/* what happens if there is an error */
			this.authFailureWarningHidden = false;
			this.error1 = "";
			this.error2 = "";
		};
	});

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

	function testNewUsername() {

	}
	;

})();