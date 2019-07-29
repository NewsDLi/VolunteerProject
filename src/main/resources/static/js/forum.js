  $(function () {
 $(document).on("click", "#sd3", function() {
                $.prompt({
                    title: '留言',
                    text: '内容文案',
                    input: '请输入',
                    empty: false, // 是否允许为空
                    onOK: function (input) {
                        saveForum(input);
                    },
                    onCancel: function () {
                        //点击取消
                    }
                });
            });
             $(document).on("click", "#fand", function(e) {
             var pid = $(this).find("input").val();
                            $.prompt({
                                title: '留言',
                                text: '内容文案',
                                input: '请输入',
                                empty: false, // 是否允许为空
                                onOK: function (input) {
                                    saveForum(input,pid);
                                },
                                onCancel: function () {
                                    //点击取消
                                }
                            });
                        });
 });

function saveForum(input,pid){
        var msg = input;
        var communityId = $("#hidden").val();

        var userid = $("#userId").val();
       if (typeof(userid) == "undefined"){
            $.toast("请微信登陆后留言", "forbidden");
            return;
       }
        var username = $("#userName").val();
       if (typeof(username) == "undefined"){
            $.toast("请微信登陆后留言", "forbidden");
            return;
       }
        if (typeof(pid) == "undefined"){
            pid = 0;
        }
        var articleMessageBoard = {
            communityArticlesId:communityId,
            message:msg,
            userId:userid,
            userName:username,
            pid:pid
        }
         $.ajax({
                      url: "/forum/save",
                      type: 'post',
                      dataType:"json",
                      contentType:'application/json;charset=utf-8',
                      data:JSON.stringify(articleMessageBoard),
                      success: function (data) {
                      if (data.code == '1000002') {
                          window.location.reload();
                      }else{
                         $.toast("请稍等", "forbidden");
                      }
              }
                  });
}
  function fungetone(uid){
    var  usort;
      if($(".icons").hasClass("top")){
          usort = 0;
      }else{
          usort = 2;
      }
      $.ajax({
          url: "/forum/top",
          type: 'get',
          contentType:'application/json;charset=utf-8',
          data:{
              id:uid,
              sort:usort
          },
          success: function (data) {
              if (data.code == '1000002') {
              $(".icons").addClass("top");
                  $.toast("操作成功", "text");
                  window.location.href=document.referrer;
              }else{
                  $.toast("置顶失败", "forbidden");
              }
          }
      });
  }
  function fungetDelete(uid){
      $.ajax({
          url: "/forum/delete",
          type: 'get',
          contentType:'application/json;charset=utf-8',
          data:{
              id:uid
          },
          success: function (data) {
              if (data.code == '1000002') {
              $(".icon").addClass("top");
                  $.toast("删除成功", "text");
                  window.location.href=document.referrer;
              }else{
                  $.toast("删除失败", "forbidden");
              }
          }
      });
  }
  function forumCheck(uid,communityId,usort){
      $.ajax({
          url: "/forum/checktop",
          type: 'get',
          contentType:'application/json;charset=utf-8',
          data:{
              id:uid,
              communityArticleId:communityId,
              sort : usort
          },
          success: function (data) {
              if (data.code == '1000002') {
                  $(".iconc").addClass("top");
                  $.toast("操作成功", "text");
                  window.location.reload()
              }else{
                  $.toast("置顶失败", "forbidden");
              }
          }
      });
  }