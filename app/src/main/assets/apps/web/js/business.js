//判断操作系统
var u = navigator.userAgent;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
var DATA={};
var lang='zh';
function ready(){
    jQuery.i18n.properties({
        name:'strings',
        path:'../i18n/',
        mode:'map',
        language:lang,
        callback:function(){
            var arr = $("[lang]");
            for (var i = 0; i < arr.length; i++) {
                var tempID = $(arr[i]).attr('lang');
                $('[lang="'+tempID+'"]').text($.i18n.prop(tempID));
            }
        }
    });
}
//判断操作系统并接受对应原生传过来得参数
if(isIOS){
	setupWebViewJavascriptBridge(function(bridge) {
		bridge.registerHandler('PostWebMessageAction', function(data, responseCallback) { //ios调web
			setData(data);
			responseCallback('1');
		});
		bridge.registerHandler('PostResultMessageAction', function(data, responseCallback) { //ios调web
			getResultData(data);
			responseCallback('1');
		});
		bridge.registerHandler('PostReloadMessageAction', function(data, responseCallback) { //ios调web
			getComData(data);
			responseCallback('1');
		});
		bridge.registerHandler('PostFindMessageAction', function(data, responseCallback) { //ios调web
            setInputVal(data.name,data.id);
			responseCallback('1');
		});
		bridge.registerHandler('PostFilterResultMessageAction', function(data, responseCallback) { //ios调web
            getSelectValue(data);
			responseCallback('1');
		});
	});
}else if(isAndroid){
	
}

//关注企业时点确定，对原生传过来得参数进行解析
function getResultData(data){
	//安卓和ios传回来的参数类型不同，所以统一操作
	if(isAndroid){
//		data=JSON.parse(data);
	}
	DATA = {
		"token": data.token,
		"deviceId": data.deviceId,
		"language":data.language,
		"osType":data.osType,
		"version": data.version,
		"host":data.host,
		"timeStamp": Math.round(new Date().getTime() / 1000) * 1000
	};
	
	
	getFollowType(DATA,data);
}

//相关企业
function getComData(data){
	//安卓和ios传回来的参数类型不同，所以统一操作
	if(isAndroid){
//		data=JSON.parse(data);
	}
	DATA = {
		"token": data.token,
		"deviceId": data.deviceId,
		"language":data.language,
		"osType":data.osType,
		"version": data.version,
		"host":data.host,
		"timeStamp": Math.round(new Date().getTime() / 1000) * 1000
	};
	getFollowCpy(DATA,data);
}

//相关企业 取消关注弹框
function isCancleOrFollow(data){
	//安卓和ios传回来的参数类型不同，所以统一操作
	if(isAndroid){
//		data=JSON.parse(data);
	}
	DATA = {
		"token": data.token,
		"deviceId": data.deviceId,
		"language":data.language,
		"osType":data.osType,
		"version": data.version,
		"host":data.host,
		"timeStamp": Math.round(new Date().getTime() / 1000) * 1000
	};
	CancleOrFollow(DATA,data);
}

function getSelectValue(data){
	//安卓和ios传回来的参数类型不同，所以统一操作
	if(isAndroid){
//		data=JSON.parse(data);
	}
	getSelectValueFun(data);
}

//跳转页面，并传参数给原生
function fLink(action,href,title,para){
	var data={
		"action":action,
		"destPage":href,
		"title":title,
		"data":para
	}
	if(isIOS){
		setupWebViewJavascriptBridge(function(bridge) {
			bridge.callHandler('PostIOSMessageAction', data, function(response) { 
			
			})
		})
	}else if(isAndroid){
	    data=JSON.stringify(data);
		window.AndroidWebView.handlerJsMessage(data);
	}
}
//对原生传过来得参数进行解析
function setData(data){
	//安卓和ios传回来的参数类型不同，所以统一操作
	if(isAndroid){
//		data=JSON.parse(data);
	}
	DATA = {
		"token": data.token,
		"deviceId": data.deviceId,
		"language":data.language,
		"osType":data.osType,
		"version": data.version,
		"host":data.host,
		"timeStamp": Math.round(new Date().getTime() / 1000) * 1000
	};
	
	//语言切换
	if(DATA.language=='1'){
		lang='zh';
	}else if(DATA.language=='4'){
		lang='en';
	}else if(DATA.language=='2'){
		lang='zh-tw';
	}else if(DATA.language=='3'){
		lang='zh-hk';
	}
	fBusiness(DATA,data);
    //切换语言
    ready();

}

//相关风险跳转
function hrefLink(rowKey,s3){
	var title="";
	switch(s3){
		case "010101": //裁判文书-民事 
		title="裁判文书-民事";
		fLink(1,"JUDGEMENTS_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010102": //裁判文书-刑事  
		title="裁判文书-刑事";
		fLink(1,"JUDGEMENTS_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010103": //裁判文书-行政 
		title="裁判文书-行政";
		fLink(1,"JUDGEMENTS_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010104": //裁判文书-赔偿 
		title="裁判文书-赔偿";
		fLink(1,"JUDGEMENTS_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010105": //裁判文书-执行  
		title="裁判文书-执行";
		fLink(1,"JUDGEMENTS_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010106": //裁判文书-其他 
		title="裁判文书-其他";
		fLink(1,"JUDGEMENTS_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010201": //开庭公告  
		title="开庭公告";
		fLink(1,"NOTICE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010202": //送达公告  
		title="送达公告";
		fLink(1,"NOTICE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010301": //被执行人信息 
		title="被执行人信息";
		fLink(1,"EXECUTED_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010401": //失信信息 
		title="失信信息";
		fLink(1,"DISHONESTY_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010501": //拍卖公告 
		title="拍卖公告";
		fLink(1,"JUDGEMENT_SALE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010601": //司法股权冻结信息 
		title="司法股权冻结信息";
		fLink(1,"STOCK_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "010602": //司法股权变更信息 
		title="司法股权变更信息";
		fLink(1,"STOCK_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "020101": //动产抵押登记信息 
		title="动产抵押登记信息";
		fLink(1,"MORTGAGE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "020102": //股权出质登记信息 
		title="股权出质登记信息";
		fLink(1,"MORTGAGE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "030101": //简易注销信息 
		title="股权出质登记信息";
		fLink(1,"COMPANY_NOTICE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "030102": //上市公司公告 
		title="股权出质登记信息";
		fLink(1,"COMPANY_NOTICE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "030103": //公司公告行政处罚信息 
		title="股权出质登记信息";
		fLink(1,"COMPANY_NOTICE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "030104": //知识产权出质登记 
		title="知识产权出质登记";
		fLink(1,"COMPANY_NOTICE_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040101": //经营异常信息  
		title="经营异常信息 ";
		fLink(1,"SAMPLING_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040102": //抽查检查信息  
		title="抽查检查信息 ";
		fLink(1,"SAMPLING_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040103": //严重违法信息  
		title="严重违法信息 ";
		fLink(1,"SAMPLING_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040104": //工商抽查行政处罚信息  
		title="工商抽查行政处罚信息 ";
		fLink(1,"SAMPLING_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040201": //欠税公告  
		title="欠税公告 ";
		fLink(1,"TAX_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040202": //重大税收违法案件公告  
		title="重大税收违法案件公告 ";
		fLink(1,"TAX_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040301": //食品抽检公告  
		title="食品抽检公告 ";
		fLink(1,"FOOD_DRUG_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040302": //医疗器械质量公告  
		title="医疗器械质量公告 ";
		fLink(1,"FOOD_DRUG_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040303": //药品质量公告   
		title="药品质量公告 ";
		fLink(1,"FOOD_DRUG_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "04034": //食药监行政处罚信息   
		title="食药监行政处罚信息 ";
		fLink(1,"FOOD_DRUG_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040305": //食药监飞行检查信息   
		title="食药监飞行检查信息 ";
		fLink(1,"FOOD_DRUG_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040306": //食药监产品召回信息   
		title="食药监产品召回信息 ";
		fLink(1,"FOOD_DRUG_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040401": //产品质量监督抽查信息   
		title="产品质量监督抽查信息 ";
		fLink(1,"QC_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040402": //进口不合格产品信息   
		title="进口不合格产品信息 ";
		fLink(1,"QC_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040403": //进出口食品安全风险预警    
		title="进出口食品安全风险预警  ";
		fLink(1,"QC_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040404": //进出口食品化妆品风险预警    
		title="进出口食品化妆品风险预警  ";
		fLink(1,"QC_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040405": //质监行政处罚信息    
		title="质监行政处罚信息  ";
		fLink(1,"QC_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040406": //质监产品召回信息    
		title="质监产品召回信息  ";
		fLink(1,"QC_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040601": //环保局环境违法信息    
		title="环保局环境违法信息  ";
		fLink(1,"ENVIRONMENT_INFO_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040602": //环保局行政处罚信息     
		title="环保局行政处罚信息   ";
		fLink(1,"ENVIRONMENT_INFO_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "040801": //发改委行政处罚信息     
		title="发改委行政处罚信息   ";
		fLink(1,"NDRC_INFO_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "041401": //消防局不良记录     
		title="消防局不良记录   ";
		fLink(1,"FIRE_STATION_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
		case "041402": //消防局抽查信息     
		title="消防局抽查信息   ";
		fLink(1,"FIRE_STATION_detail.html",title,{"rowkey":rowKey,"s3":s3});
		break;
	}
}




