//屏幕适配
(function(){
    var currClientWidth,
        fontValue,
        originWidth;
    originWidth = 750;//ui设计稿的宽度，一般750或640
    __resize();
    window.addEventListener('resize', __resize, false);
    function __resize() {
        currClientWidth = document.documentElement.clientWidth;
        if (currClientWidth > 768){
            currClientWidth = 768;
        } 
        if (currClientWidth < 320){
            currClientWidth = 320;
        } 
        fontValue = ((625 * currClientWidth) / originWidth).toFixed(2);
         document.documentElement.style.fontSize = fontValue + '%';
    }
})();
//封装http请求，统一用post请求 同步请求async传布尔值false,否则不传
function fHttpPost(url,data,success,async,fType){
    var ajaxObj = {
        url:url,
        data:data,
        dataType:'json',
        type:'post',
        timeout:8000,
        success:success,
        error:function(xhr,type,errorThrown){
        	fLink(3);
	        if(fType=="company"){
	        	$("#company").css({"display":"none"});
	        }
	        else if(fType=="risk"){
	        	$("#risk").css({"display":"none"});
	        }
	        else
	        {
	            $('.m-content').html('<div class="u-no-box"><img src="../images/no-trend.png"/></div>');
	            $('.riskContent').html('<div class="u-no-box"><img src="../images/no-trend.png"/></div>');
	            $('#app').css({"display":"block"});
	          }
	        }
    }
    if(async===false){
        ajaxObj.async = false;
    }
	$.ajax(ajaxObj)
}
//与原生交互用的方法
function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'https://__bridge_loaded__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
}
//vue过滤器，全局定义
Vue.filter('formatDateTime', function (value) {  
	if(!value){
		return "- -";
	}else{
		var date = new Date(value);  
	    var y = date.getFullYear();    
	    var m = date.getMonth() + 1;    
	    m = m < 10 ? ('0' + m) : m;    
	    var d = date.getDate();    
	    d = d < 10 ? ('0' + d) : d;    
	    var h = date.getHours();  
	    h = h < 10 ? ('0' + h) : h;  
	    var minute = date.getMinutes();  
	    var second = date.getSeconds();  
	    minute = minute < 10 ? ('0' + minute) : minute;    
	    second = second < 10 ? ('0' + second) : second;   
	    return y + '-' + m + '-' + d;
	}
})
//vue过滤器，全局定义 千分符
Vue.filter('NumFormat', function(value) {
    if(!value) return '- -';
    if(isNaN(value)) return value;
    var intPart = Number(value).toFixed(0); //获取整数部分
    var intPartFormat = intPart.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,'); //将整数部分逢三一断

    var floatPart = ""; //预定义小数部分
    var value2Array = value.toString().split(".");

    //=2表示数据有小数位
    if(value2Array.length == 2) {
        floatPart = value2Array[1].toString(); //拿到小数部分
        // if(floatPart.length == 1) { //补0,实际上用不着
        //     return intPartFormat + "." + floatPart + '0';
        // } else {
        //     return intPartFormat + "." + floatPart;
        // }
    }
    return intPartFormat + floatPart;

})
