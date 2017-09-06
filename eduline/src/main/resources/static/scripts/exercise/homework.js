$(function(){



    var odata = {
        'exercise':exercise,
        'course':0,
    };
    var vm = new Vue({
        'el':'#table-exercises',
        'data':odata
    });

    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init({});
    dataAdmin.addPagerListener();
    $('.action-textbook-change').on('change',function () {
        var opt = $('option:selected',this);
        loadUrl(opt.data('url'));
    }); 


    $('.action-next-step').on('click',function (event) {
        event.preventDefault();

        var href =  $(this).attr('href')
        var cp = href.indexOf('?')>0?'&':'?';
        var url = href+cp+'exercise='+odata['exercise'];
        if(odata['exercise'].length<=0){
            layer.alert('请先选择习题!');
            return false;
        }
        loadUrl(url);
        return false;
    })


});