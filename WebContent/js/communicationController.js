/**
 * 
 */




/*
* Helper service which takes a query and matching text and highlights the matching parts.
* This helper makes use of the Angular $sce (Strict Contextual Escaping) object which allows
* to add inner HTML into other HTML elements. 
* See more details in: https://docs.angularjs.org/api/ng/service/$sce
*/
angular.module('helper',[])
  .factory('HELPER', ['$sce', function($sce){;}]);

  
  /*
   * The response from the server is an object with these properties:
   * .config the object used to generate the request. 
   * .data a string, or an object, carrying the response from the server. 
   * .headers a function to use to get header information. 
   * .status a number defining the HTTP status. 
   * .statusText a string defining the HTTP status.
   */
angular.module('myApp', [ 'helper' ])
.controller('myController', [ '$scope', '$http', 'HELPER', function($scope, $http, HELPER) {
  $http.get("http://localhost:8080/ExampleServletv3/customers").success(function(response) {
    $scope.result = response;
  })
  $http.get("wrongfilename.htm").then(function(response) {
    // First function handles success
    $scope.content = response.data;
  }, function(response) {
    // Second function handles error
    $scope.content = "Something went wrong";
  });

} ]);

