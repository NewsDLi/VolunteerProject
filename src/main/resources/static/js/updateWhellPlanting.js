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
                        var ids = $("#id").attr('value');
                        var imgBase64 = $('.img').attr("src");
                        		if ( undefined == imgBase64){
                        			return;
                        		}
                        		var head = imgBase64.indexOf("4")+2;
                        		imgBase64 = imgBase64.substring(head, imgBase64.length-head);
                        		var reg = new RegExp("\\+","g");
                                		imgBase64 = imgBase64.replace(reg, "%2B");

                        var descs = $("#desc").val();
                        var linkAddresss = $("#linkAddress").val();
                        var wheel = {
                            id:ids,
                            pic:imgBase64,
                            description:descs,
                            linkAddress:linkAddresss
                        }
                        $.ajax({
                            type : "post",
                            url : "/updateWhellPlanting",
                            contentType : 'application/json',
                            data:JSON.stringify(wheel),
                            dataType:'json',
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
