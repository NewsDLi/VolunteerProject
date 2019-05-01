 $(document).ready(function(){
        // 初始化内容
        init();

    });

    function init() {
        var userInfo = $.session.get('login_phone');
        $(document).ready(function(){
            $.post("/function.json",
            {
              roleId:userInfo.roleId
            },
            function(data,status){
              alert("数据：" + data + "\n状态：" + status);
            });
          });
    }