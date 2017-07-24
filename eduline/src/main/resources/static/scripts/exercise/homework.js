$(function(){
    var odata = {
        'exercises':exercises
    };
    var vm = new Vue({
        'el':'#tlabe-exercises',
        'data':odata
    });
    $('.action-textbook-change').on('change',function () {
        var opt = $('option:selected',this);
        redirectTo(opt.data('url'));
    }); 
    
    $('.action-next-step').on('click',function () {

    })

    $('a').on('click',function (e) {
        //e.preventDefault();
        var href =  $(this).attr('href')
        $(this).attr('href',href+'&exercises='+odata['exercises'].join(','));
        return true;
    })

})