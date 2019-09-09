//2018-5-22
//正则幼清
//-------------------------
$(function(){
//------------产品详情------------
    //条件选择
    $(".tebtn p").click(function(){
        $(this).siblings().removeClass("on");
        $(this).addClass("on");
    });


//-----------------------选项卡导航-----------------------
    function tabli() {
        var _this =$(".multi-select li")
        var linum = _this.length
        if (linum <= 2) {
            liWidth = "50%";
        }else if(linum == 3){
            liWidth = "33.3%";
        }else if(linum == 4){
            liWidth = "25%";
        }else if(linum >= 5){
            liWidth = (window.innerWidth/4) - 20;
            _this.parent().css("width",linum*liWidth+"px");
        }
        _this.css("width",liWidth);
    };
    new tabli();
    $(window).resize(function () {
        new tabli();
    });
    function leftdd(){
        var leftnum = $(".dis-tab1 .am-active").index();
        var widthdd = $(".dis-tab1 .am-active").width();
        $(".am-tabs").scrollLeft(widthdd*(leftnum));
    }
    leftdd();


    //--------------------------------------------图片获取显示--------------------------------------------
    $("input[type='file']").on("change",function(){
        var _thisId = $(this).attr("id");
        var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
        if (objUrl) {
            $("."+_thisId).attr("src", objUrl) ; //将图片路径存入src中，显示出图片
        }
    });
    //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }


});
