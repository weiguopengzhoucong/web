$(document).ready(function(){
	//绑定
	$('.img_con').cycle({ 
	    fx:    'fade', 
	    sync:  true, 
	    delay: -2000,
	    pager : '.page_con'
	});
	$('.sidebar ul').sortable();
	//执行首次加载的代码
	window.onload = function(){
	}
})