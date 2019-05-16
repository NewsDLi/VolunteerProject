
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
    $(".weui-btn_primary").click(function(){
        var html = $(".note-editable").html();
        if(isNull(html)) $.toast("内容不能为空", "forbidden");
        var zheng = $("#zheng").val();
        if(isNull(zheng)) $.toast("请填写标题", "forbidden");
        var fu = $("#fu").val();
        var type = $("#job").attr("data-values");
        if(isNull(type)) $.toast("请选择发布类型", "forbidden");
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
