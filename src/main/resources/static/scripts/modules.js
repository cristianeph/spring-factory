/**
 * 
 */
$(document).ready(function($){
	$(".navi a[href*='modules']").attr("class", "sele");
	$(".menu .quic ul li a").each(function(i, item){
		$(item).html($(item).attr("data-text-mini"));
	});
});