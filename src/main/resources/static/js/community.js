$(function(){
$(".type1").click(function(){
	if($(".type1").hasClass("bg-green")){
              $.toast("已经在此页面", "text");
              return;
	}
          getCommunity(1);
    });
	$(".type2").click(function(){
	if($(".type2").hasClass("bg-green")){
                  $.toast("已经在此页面", "text");
                  return;
    	}
          getCommunity(2);
    });
	$(".type3").click(function(){
	if($(".type3").hasClass("bg-green")){
                  $.toast("已经在此页面", "text");
                  return;
    	}
          getCommunity(3);
    });
	$(".type4").click(function(){
	if($(".type4").hasClass("bg-green")){
                  $.toast("已经在此页面", "text");
                  return;
    	}
          getCommunity(4);
    });

})
function getCommunity(type){
         $.ajax({
                      url: "/forumList/"+type,
                      type: 'GET',
                      success: function (data) {
                      if (data.code == '1000002') {
                            $('#t5').tab({defaultIndex:type-1,activeClass:"bg-green"});
                            initCommunity(data);
                      }else{
                         $.toast("请稍等", "forbidden");
                      }
              }
                  });
}

function initCommunity(data){
var list = data.data;
var html = "";
        $(".lists").empty();
        for (var i=0; i<list.length; i++){
         html = "<a href='/'><li class='weui-news-item'><div class='weui-news-inners'><!--标题--><div class='weui-news-title'>"+list[i].title+"</div><!--副标题--><p class='weui-news-p'>"+list[i].subheading+"</p><div class='weui-news-info'><!--时间--><div class='weui-news-infoitem'>"+list[i].publicationTime+"</div><input type='hidden' value='"+list[i].id+"'></div></div></li></a>";
        $(".lists").append(html);
          }
}