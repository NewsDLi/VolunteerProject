$(function(){
            $('#uploaderInput').change(function(e){
                var imgBox = e.target;
                uploadImg($('#uploaderInput'), imgBox);
            });
            $('.updateWhellPl').click(function(){
                $.confirm({
                    title: '添加',
                    text: '确认添加？',
                    onOK: function () {
                        var formData = new FormData();
                        var ids = $("#id").attr('value');
                        var file = $('#uploaderInput').get(0).files[0];
                        var descs = $("#desc").val();
                        var linkAddresss = $("#linkAddress").val();
                        formData.append("pics", file);
                        formData.append("id", ids);
                        formData.append("description", descs);
                        formData.append("linkAddress", linkAddresss);
                        $.ajax({
                            url : "/updateWhellPlanting",
                             data: formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            type: 'POST',
                            success : function(data) {
                                if (data.message == 'success') {
                                    $.toast("操作成功");
                                    setTimeout(function(){
                                        location.href="/mypage";
                                    },2000);
                                }else {
                                    $.toast("操作失败");
                                }
                            }
                        });
                    },
                    onCancel: function () {
                        $.toast("操作失败");
                        return;
                    }
                });


            });
        });

         function uploadImg(element, tag) {
                    var file = tag.files[0];
                    var imgSrc;
                    if (!/image\/\w+/.test(file.type)) {
                        alert("看清楚，这个需要图片！");
                        return false;
                    }
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function() {
                        console.log(this.result);
                        imgSrc = this.result;
                        $('.img').attr("src", imgSrc);
                    };
                }
