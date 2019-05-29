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
 });

function saveForum(input,pid){
        var msg = input;
        var communityId = $("#hidden").val();
        var userid = $("#userId").val();
        var username = $("#userName").val();
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