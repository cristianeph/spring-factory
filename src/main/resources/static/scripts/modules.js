/**
 * 
 */
$(document).ready(function($){
	$(".navi a[href*='modulos']").attr("class", "sele");
	$(".menu .quic ul li a").each(function(i, item){
		$(item).html($(item).attr("data-text-mini"));
	});
});