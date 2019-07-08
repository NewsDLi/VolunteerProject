
  $(document).ready(function() {
       $('#summernote').summernote({
  height: 350,
  minHeight: 300,
  maxHeight: null,
  focus: true,
 disableDragAndDrop: true,
  lang: 'zh-CN',
    fontNames: ['微软雅黑'],
     toolbar: [
     ['color', ['color']],
    ['style', ['bold', 'italic', 'underline']],
    ['fontsize', ['fontsize']],
    ['para', ['ul', 'ol', 'paragraph']],
    ['height', ['height']],
    ['picture',['picture']],
    ['table',['table']],
    ['style',['style']],
    ['paragraph',['paragraph']]
  ],callbacks: {
         onImageUpload: function(files,editor,$editable) {
                 sendFile(this,files[0], editor,$editable);
         }
       }
});
    });


    function sendFile(obj,file, editor,$editable) {
    var  filename=false;
    try{
        filename=file['name'];
    }catch(e){
        filename=false;
    }
    if(!filename){
        $(".note-alarm").remove();
    }
    //防止重复提交
    var formData = new FormData();
    formData.append("file", file);
    //formData.append("key",filename);//唯一参数性
    $.ajax({
        url: "/uploadEditorImg",//路径是你控制器中上传图片的方法
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
        success: function (data) {
        $(obj).summernote('insertImage',data);
//            $(obj).summernote('pasteHTML',"<img src="+data.path+" />");
        }
    });


}
    $(".fanhui").click(function(){
        history.go(-1);
    });
    $(".baocun").click(function(){
        var html = $(".note-editable").html();
        if(isNull(html)){
         $.toast("内容不能为空", "forbidden");
         return;
        }
        var zheng = $("#zheng").val();
        if(zheng==null||zheng==undefined||zheng==""){
         $.toast("请填写标题", "forbidden");
         return;
        }
        var fu = $("#fu").val();
        var type = $("#job").attr("data-values");
        if(null==type){
         $.toast("请选择发布类型", "forbidden");
         return;
        }
        $.ajax({
              url: "/uploadEditor",
              data: {
                  'heading':fu,
                  'type':type,
                  'title':zheng,
                  'data':html
      },
              type: 'POST',
              success: function (data) {
              if(data == 1){

                    if(type == 1){
                        window.location.replace("/community.htm?type=1");
                        return;
                    }else if (type == 2) {
                        window.location.replace("/community.htm?type=2");
                        return;
                    }else if (type == 3) {
                        window.location.replace("/community.htm?type=3");
                        return;
                    }else if (type == 4) {
                        window.location.replace("/community.htm?type=4");
                        return;
                    }

                  history.back(-1);
              }else{
                $.toast("禁止操作", "forbidden");
              }
      }
          });
    });

    function isNull(str){
        var regu = "^[ ]+$";
        var re = new RegExp(regu);
        return re.test(str);
        };
