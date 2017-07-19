$(function () {
   $('.action-remove').on('click',function () {
      var id = $(this).data('id');
      var that = this;
       $.post('/textbook/remove/'+id).done(function (rst) {
          console.log(rst);
           $(that).parents('tr').remove();
       })

   })
})