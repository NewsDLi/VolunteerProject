 $(document).ready(function(){
        // 初始化内容
        init();

    });

    function init() {
            $.post("/function.json",
            function(data,status){
              alert("数据：" + data + "\n状态：" + status);
            });
    }