 $(document).ready(function(){
        // 初始化内容
        init();

    });

    function init() {
            $.post("/function.json",
            function(data,status){
             if(isEmptyObject(data)){
             var a = basisUrl("url");
                window.location.href = basisUrl("url")+"/index";
             }
            });
    }

    function basisUrl(name){
       $.i18n.properties({
           name:'switch',
           path:'cofig/',
           mode:'Map',
           language:'pt_BR',
           async: true,
           callback: function() {
               $.i18n.prop(name);
           }
       });
    }

    function isEmptyObject(obj){

         for(var key in obj){
              return false
         };
         return true
    };
