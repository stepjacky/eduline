$(function() {

   var vm =  new Vue({
        el: '#input-body',
        data: odata
   });

   $('.action-save').on('click',function () {
       var url = '/chapter/'+(odata['id']?'update':'save');
       if(!odata.name){
           bootbox.alert('名称不能为空');
       }
       postJson(url,odata,function (rst) {
           reloadPage();
       })
   })

  $('.action-edit').on('click',function (e) {
        e.preventDefault();
        var id = $(this).data('id');

        $.getJSON('/chapter/get/'+id)
            .done(function(rst){
                if(!rst) {
                    console.info('data is null');
                    return;
                }
                initData(rst,false);

            })
  })

    $('.action-new').on('click',function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        $.getJSON('/chapter/get/'+id)
            .done(function(rst){
                if(!rst){
                    console.info('data is null');
                    return;
                }
                initData(rst,true);

            })
    })

    $('.action-new-child').on('click',function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        $.getJSON('/chapter/get/'+id)
            .done(function(rst){
                if(!rst){
                    console.info('data is null');
                    return;
                }
                initChild(rst);

            })
    })

    $('.action-remove').on('click',function () {
        var id = $(this).data('id');
        $.get('/chapter/remove/'+id)
            .done(function () {
                reloadPage();
            })
    })

    function initData(rst,isNew){
       if(isNew){
           odata['parent'] = 'root';
           odata['id'] = null;
           odata['name'] ='';
       } else{
           odata['parent'] = rst['parent'];
           odata['id'] = rst['id'];
           odata['name'] =rst['name'];
       }
       odata['textbook'] =rst['textbook'];
       odata['grade'] =rst['grade'];
       odata['course'] =rst['course'];
    }
    function initChild(rst){

        odata['parent'] = rst['id'];
        odata['id'] = null;
        odata['name'] ='';
        odata['textbook'] =rst['textbook'];
        odata['grade'] =rst['grade'];
        odata['course'] =rst['course'];
    }

});

