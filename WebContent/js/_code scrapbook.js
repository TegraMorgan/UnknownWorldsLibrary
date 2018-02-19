/*
 * DELETE THIS FILE AT THE END OF DEVELOPMENT 
 */
/*
 Retrieve the current scroll position, you can use:
 window.pageYOffset

 Store the position, this has two parts, when and where:
 You can chose to store the data, when the window closes, or every time the user scroll, or on a set interval...
 for the "where", depending on your app, you may want to store it, in a cookie, local-storage, on the server-side (if the user needs to log in to read the eBook)...

 Restore the position when the user return, by retrieving the stored data, and scroll to that position using
 window.scrollTo(0, position);

 so the real problem here is, when and where to store the position, and that depends on your application.

 You could always use the onbeforeunload event to check when the window is being closed, and set a cookie with the current position. Than every time the page is loaded just check if that cookie is set. If it is - use the value to redirect the user to the saved position.

 You should understand the concepts in order to make it work. You can read about cookies and how to interact with them in javascript here. The link contains a live example.

 The onunload and onbeforeunload are javascript events which you can use to detect when your page is being closed. You should google what they are and how to use them.

 You could check this stack-overflow question for an answer how to jump to a specific offset and this one to see how to retrive it.
 
I made small jquery plugin that you can include and have this functionallity without cookies etc. Include just before end of body tag.
<script type="text/javascript" src="/js/maintainscroll.jquery.min.js"></script>
</body>
https://github.com/evaldsurtans/maintainscroll.jquery.js
 
 */
/*
 * Helper service which takes a query and matching text and highlights the matching parts.
 * This helper makes use of the Angular $sce (Strict Contextual Escaping) object which allows
 * to add inner HTML into other HTML elements. 
 * See more details in: https://docs.angularjs.org/api/ng/service/$sce
 */
angular.module('helper', []).factory('HELPER', [ '$sce', function($sce) {
  ;
} ]);

/*
 * The response from the server is an object with these properties: .config the object used to generate the request. .data a string, or an object,
 * carrying the response from the server. .headers a function to use to get header information. .status a number defining the HTTP status. .statusText
 * a string defining the HTTP status.
 */
angular.module('myApp', [ 'helper' ]).controller('myController', [ '$scope', '$http', 'HELPER', function($scope, $http, HELPER) {
  // GET REQUEST
  $http.get("http://localhost:8080/ExampleServletv3/customers").success(function(response) {
    $scope.result = response;
  })
  // POST REQUEST
  var values = $("#someform").serialize();
  $('#someform :input').prop("disabled", true);

  $.ajax({
    type : "POST",
    url : '/APP_NAME/SERVLETNAME',
    data : values,
  }).done(function(data, textStatus, jqXHR) {
    alert("just for debuggin - success");
    console.log("name= " + data.name + "; email=" + data.email + "; textStatus= " + textStatus);
    $('#notification_success').show();
  }).always(function(data, textStatus, jqXHR) {
    alert("just for debuggin -  in always");
  }).fail(function(data, textStatus, jqXHR) {
    alert("just for debuggin -  failure");
    $('#notification_failure').show();
    $('#registerSubmit :input').prop("disabled", false);
  });

} ]);
