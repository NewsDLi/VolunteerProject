$(function(){
   	if( window.location.pathname.indexOf("homepage") >= 0) {
       $(".homepage").addClass("weui-bar__item_on")
    }
    var s=window.location.pathname.indexOf("mypage")
     var ss  = window.location.pathname.indexOf("login.json")
   	if( window.location.pathname.indexOf("mypage") >= 0 || window.location.pathname.indexOf("login.json") >= 0 	) {
       $(".mine").addClass("weui-bar__item_on")
    }

   	if( window.location.pathname.indexOf("community") >= 0 ) {
       $(".community").addClass("weui-bar__item_on")
    }
    $('.homepage').click(function(){
		// TODO
		location.href = "/homepage.htm";
	})
	$('.mine').click(function(){
		location.href = "/mypage";
	})
	$('.community').click(function(){
		location.href = "/community.htm";
	})
})