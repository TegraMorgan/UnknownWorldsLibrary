/**
 * 
 */

(function() {
	var app = angular.module('library', []);

	app.controller('LibController', function() {
		this.products = books;

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