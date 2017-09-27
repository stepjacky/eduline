$(function(){

    $(document.body).on('click',
        'ul.layui-nav li.layui-nav-item dl.layui-nav-child dd a,ul.layui-nav li.layui-nav-item a,span.list-types a,.action-href-nav',function (event) {

        event.preventDefault();
        var url = $(this).attr('link');
        if(!url){
            url = $(this).attr('href');
        }
        if(!url){
            layer.alert('不存在有效链接')
        }else{
            loadUrl(url);
        }

        return false;
    });

});

