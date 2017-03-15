/**
 * 
 */
$(document).ready(function($){
	
	$(".navi a[href*='masters']").attr("class", "sele");
	
	/*$(".menu .quic ul").hover(
		function(){
			$(this).parent().css({"width": "10em"});
			$(this).find("a").css({"text-align": "left"})
			$(this).find("a").each(function(i, item){
				
				$(item).html($(item).attr("data-text-large"));
				
			});
		},
		function(){
			$(this).parent().css({"width": "4.5em"});
			$(this).find("a").css({"text-align": "center"})
			$(this).find("a").each(function(i, item){
				
				$(item).html($(item).attr("data-text-mini"));
				
			});
		}
	);*/
	
	$(".menu .quic ul li a").each(function(i, item){
		
		$(item).html($(item).attr("data-text-mini"));
		
	});
	
});