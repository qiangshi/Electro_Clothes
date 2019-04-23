function getFollowType(DATA,data){
	DATA.anyCpyNo=anyCpyNo;//2e13808f78f578bb3e9a07c222028f5e
	DATA.attentionType =data.attentionType;//010106
	fHttpPost(hostUrl+'user/attention/add',DATA,function(res){
		if(res.code=='200'){
			currentDom.removeClass("attention").addClass("cancleAttention");
			if(language==4){
				currentDom.html("cancel");
			}else if(language==1)
			{
				currentDom.html("取消关注");
			}else
			{
				currentDom.html("取消關注");
			}
		}
	});
}

function getFollowCpy(DATA,data){
	DATA.rowkey =data.rowkey;//2e13808f78f578bb3e9a07c222028f5e
	DATA.s3=data.s3;//010106
	parmter=DATA;
	hostUrl=data.host;
	reloadCpy(DATA)
}

function reloadCpy(DATA){
	var url=DATA.host;
	fHttpPost(url+'risk/relation/company',DATA,function(res){
		if(res.code=='200' && res.data.length>0){
			$("#company").removeClass("none");
			$("#company ul")[0].innerHTML="";
			var liHtml="";
			var followTxt="关注";
			var cancleTxt="取消关注";
			var enClass="";
			var androidClass="";
			if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios

			}else
			{
				androidClass="an";
			}
			if(language==4){
				followTxt="follow";
				cancleTxt="cancle";
				enClass="en";
			}else if(language==1){
				followTxt="关注";
				cancleTxt="取消关注";
			}else
			{
				followTxt="關注";
				cancleTxt="取消關注";
			}
			for(var i=0;i<res.data.length>0;i++){
				if(res.data[i].attTypeCode!=undefined){//取消

					liHtml+='<li>'+
						'<span class="sp1" anyCpyNo="'+res.data[i].anyCpyNo+'" cpyName="'+res.data[i].cpyName+'" hasSubscribe="'+res.data[i].hasSubscribe+'" >'+res.data[i].cpyName+'</span></li>';
				}else //关注
				{

					liHtml+='<li>'+
						'<span class="sp1" anyCpyNo="'+res.data[i].anyCpyNo+'" cpyName="'+res.data[i].cpyName+'" hasSubscribe="'+res.data[i].hasSubscribe+'" >'+res.data[i].cpyName+'</span></li>'
				}
			}
			$("#company ul")[0].innerHTML=liHtml;
			$("#company ul li .iconBg").click(function(){
				anyCpyNo=$(this)[0].getAttribute("anyCpyNo");
				var anyNo=$(this)[0].getAttribute("anyCpyNo");
				var m=$(this)[0].getAttribute("monitor");
				var dom=$(this);
				currentDom=dom;
				att(anyNo,m,dom,DATA);
			});
			$("#company ul li .sp1").click(function(){
				var cpname=$(this)[0].getAttribute("cpyName");
				var anyNo=$(this)[0].getAttribute("anyCpyNo");
				fLink(1,1,"企业概览",{"cpyName":cpname.replace(/\n/g,''),"anyCpyNo":anyNo});
			});



		}
	},false,"company");
}

function getFollowCom(DATA){
//				DATA.rowkey=rowkey;
//				DATA.s3=s3;
		fHttpPost(hostUrl+'risk/relation/company',DATA,function(res){
		if(res.code=='200' && res.data.length>0){
			var androidClass="";
			if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios

			}else
			{
				androidClass="an";
			}
			$("#company").removeClass("none");
			var className="iconBg cancleAttention "+androidClass;
			var classNameAtt="iconBg attention "+androidClass;
			if(language==4){
				className="iconBg cancleAttention en "+androidClass;
			}

			var app3=new Vue({
				el: '#company',
				data:{res:res.data,className:className,classNameAtt:classNameAtt},
				methods: {
					follow:function(anyNo,m,e){
						currentDom = $(e.target);
						if(currentDom.attr("class").indexOf("cancleAttention")>-1){//取消
							if(m==false||m=="false"){
								if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
									var message="";
									if(language==4){
										message=confirm("Did you cancel your attention!");
									}else if(language==1)
									{
										message=confirm("是否取消关注！");
									}else{
										message=confirm("是否取消關注！");
									}

									if(message==true){
										parmter.anyCpyNo = anyNo;
										anyCpyNo=anyNo;
										fHttpPost(hostUrl+'user/attention/remove',parmter,function(res){
											if(res.code=="200"){
												var currentDom = $(e.target);
												currentDom.removeClass("cancleAttention").addClass("attention");
												if(language==4){
													currentDom.html("follow");
												}else if(language==1)
												{
													currentDom.html("关注");
												}else
												{
													currentDom.html("關注");
												}

											}
										});
									}
								}else
								{
									anyCpyNo=anyNo;
									if(language==4){
										fLink(4,"","","Did you cancel your attention!");
									}else if(language==1)
									{
										fLink(4,"","","是否取消关注！");
									}else
									{
										fLink(4,"","","是否取消關注！");
									}

								}
							}else{
								if(language==4){
									if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
									alert("You have monitored the company, please cancel monitoring first and unfollow!");
									}else
									{
										fLink(5,"","","You have monitored the company, please cancel monitoring first and unfollow!");
									}
								}else if(language==1){
									if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
										alert("您已监控该企业，请先取消监控，在取消关注！");
									}else
									{
										fLink(5,"","","您已监控该企业，请先取消监控，在取消关注！");
									}
								}else
								{
									if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
										alert("您已監控該企業，請先取消監控，在取消關注！");
									}else
									{
										fLink(5,"","","您已監控該企業，請先取消監控，在取消關注！");
									}
								}

							}
						}
						else {
							parmter.anyCpyNo = anyNo;
							anyCpyNo=anyNo;
							monitor=monitor;
							fLink(6);
						}
					},
					getAlert:function(anyNo,monitor,e,dom){
						parmter.anyCpyNo = anyNo;
						anyCpyNo=anyNo;
						monitor=monitor;
						fLink(6);
					}
					,hrefHome:function(cpname,anyNo,e){
						fLink(1,1,"企业概览",{"cpyName":cpname.replace(/\n/g,''),"anyCpyNo":anyNo});
					}
				}
			});
			if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios

			}else
			{
				$(".relatedEnterprises ul li span.iconBg").addClass("an");
			}
		}
		fLink(3);
	},false,"company");
}
			
function CancleOrFollow(DATA,data){

	DATA.anyCpyNo = anyCpyNo;
	//anyCpyNo=anyNo;
	if(data.yes){
		fHttpPost(hostUrl+'user/attention/remove',DATA,function(res){
			if(res.code=="200"){
				//var currentDom = $(e.target);
				currentDom.removeClass("cancleAttention").addClass("attention");
				if(language==4){
					currentDom.html("follow");
				}else if(language==1)
				{
					currentDom.html("关注");
				}else
				{
					currentDom.html("關注");
				}

			}
		});
	}
}
		
function att(anyNo,m,currentDom,DATA){

if(currentDom.attr("class").indexOf("cancleAttention")>-1){//取消
	if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {

		if(m==false||m=="false"){
			var message="";
			if(language==4){
				message=confirm("Did you cancel your attention!");
			}else if(language==1)
			{
				message=confirm("是否取消关注！");
			}else{
				message=confirm("是否取消關注！");
			}
			if(message==true){
				DATA.anyCpyNo = anyNo;
				anyCpyNo=anyNo;
				fHttpPost(DATA.host+'user/attention/remove',DATA,function(res){
					if(res.code=="200"){
						//var currentDom = $(e.target);
						currentDom.removeClass("cancleAttention").addClass("attention");
						if(language==4){
							currentDom.html("follow");
						}else if(language==1)
						{
							currentDom.html("关注");
						}else
						{
							currentDom.html("關注");
						}

					}
				});
			}
		}else{
				if(language==4){
					if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
						alert("You have monitored the company, please cancel monitoring first and unfollow!");
					}else
					{
						fLink(5,"","","You have monitored the company, please cancel monitoring first and unfollow!");
					}
				}else if(language==1){
					if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
						alert("您已监控该企业，请先取消监控，在取消关注！");
					}else
					{
						fLink(5,"","","您已监控该企业，请先取消监控，在取消关注！");
					}
				}else
				{
					if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
						alert("您已監控該企業，請先取消監控，在取消關注！");
					}else
					{
						fLink(5,"","","您已監控該企業，請先取消監控，在取消關注！");
					}
				}
			}
		}else
		{
			if(m==false||m=="false"){
				if(language==4){
					fLink(4,"","","Did you cancel your attention!");
				}else if(language==1)
				{
					fLink(4,"","","是否取消关注！");
				}else
				{
					fLink(4,"","","是否取消關注！");
				}
			}else
			{
				if(language==4){
					if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
						alert("You have monitored the company, please cancel monitoring first and unfollow!");
					}else
					{
						fLink(5,"","","You have monitored the company, please cancel monitoring first and unfollow!");
					}
				}else if(language==1){
					if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
						alert("您已监控该企业，请先取消监控，在取消关注！");
					}else
					{
						fLink(5,"","","您已监控该企业，请先取消监控，在取消关注！");
					}
				}else
				{
					if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {//ios
						alert("您已監控該企業，請先取消監控，在取消關注！");
					}else
					{
						fLink(5,"","","您已監控該企業，請先取消監控，在取消關注！");
					}
				}
			}
		}
	}
	else {
		fLink(6);
	}
}

