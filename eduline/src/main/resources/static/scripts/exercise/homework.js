$(function(){
    var odata = {
        'exercise':exercise,
        'course':0,
    };
    var vm = new Vue({
        'el':'#tlabe-exercises',
        'data':odata
    });
    $('.action-textbook-change').on('change',function () {
        var opt = $('option:selected',this);
        loadUrl(opt.data('url'));
    }); 


    $('a').on('click',function (e) {
        var href =  $(this).attr('href')
        var cp = href.indexOf('?')>0?'&':'?';
        $(this).attr('href',href+cp+'exercise='+odata['exercise'].join(','));
        return true;
    })


})