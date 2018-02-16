(function(global) {
  'use strict';
  var services = angular.module('services', []);  
  services.service('commsa', [ '$http', function($http) {
  return {
    call : function(method, url, input, onsuccsess, onfail, always) {
      var values = angular.toJson(input);
      $.ajax({
        type : method,
        url : url,
        data : values
      }).done(onsuccsess).always(always).fail(onfail);
    }
  }
} ]);
})(this.window);

