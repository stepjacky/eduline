$(function(){

    $('.action-textbook-change').on('change',function () {
        var opt = $('option:selected',this);
        redirectTo('/resource/listresource?textbook='+opt.val());
    })
})