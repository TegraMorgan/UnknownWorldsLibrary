/*
 * How to implement: */

/*
         commsa.call('POST','testServlet',javaScript Object,
         function(data, textStatus, jqXHR) {
         data is javascript object
         textStatus is response status
         jqXHR - XML object
         alert('run when success');
        } ,
         function(data,textStatus, errorThrown) {
         alert('run when fail');
        } ,
        function(data,textStatus, jqXHR) {
         alert('always run');
     });




*/

(function(global) {
  'use strict';
  var services = angular.module('services', []);  
  services.service('comms', [ '$http', function($http) {
  return {
    call : function(method, url, input, onsuccsess, onfail, always) {
      var values = angular.toJson(input);
      $.ajax({
        type : method,
        url : '/BooksForAll/'+url,
        data : values
      }).done(onsuccsess).always(always).fail(onfail);
    },
  sync : function(url, input, onsuccsess, onfail, always) {
    var values = angular.toJson(input);
    $.post({
      url : '/BooksForAll/'+url,
      data : values
    }).done(onsuccsess).always(always).fail(onfail);
  },
  nsync: function(url, input, onsuccsess, onfail) {
    var values = angular.toJson(input);
        $.ajax({
          method : 'POST',
          async : false,
          url : '/BooksForAll/'+url,
          data : values,
          context:document.body,
    }).done(onsuccsess).fail(onfail);
  }
  }
} ]);
})(this.window);

