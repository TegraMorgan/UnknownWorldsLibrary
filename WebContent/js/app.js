/**
 * 
 */

(function() {
	var app = angular.module('library', []);
	var errorUsername = false;
	app.controller('LibController', function() {
		this.products = books;

	});

	app
			.controller(
					'LandingController',
					function() {
						/* Properties */
						/* don't forget to change these two back */
						this.userLogin = false;
						this.userRegister = true;
						this.errorUsername = false;
						this.error1 = "";
						this.error2 = "";
						this.authFailureWarningHidden = true;
						this.usernameError = "Username must be unique, can have only English characters and can't be longer than 10 characters";
						this.emailerror = "Please provide a valid email, that has @ character and a domain dot";
						this.adressError = "AEnglish characters only, longer than 3 characters";

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

							/* what happens if there is an error */
							this.authFailureWarningHidden = false;
							this.error1 = "";
							this.error2 = "";
						};
						this.register();
					});

	var books = [

	{
		name : '48 rules of power',
		price : 12.3,
		description : 'Some description',
		canRead : true,
		canBuy : false,
	}, {
		name : '12 rules to meaningfull life',
		price : 17.1,
		description : 'Some description',
		canRead : true,
		canBuy : true,
	} ];

})();