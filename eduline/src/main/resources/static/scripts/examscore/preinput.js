$(function(){
	$('.action-year-score').on('click',function (event) {
		event.preventDefault();
		loadUrl($(this).attr('href'));
		return false;
    })
});