
	
	
	
	
$(document).ready(function(){
//	$.ajax({
//		type:"get",
//		url:"http://192.168.1.97:8090/data.json",
//		async:false,
//		dataType:"json",
//		success:function(data){
//			dataResult=data;
//			nods1=dataResult.nodes;
//			edges1=dataResult.edges;
//			pathload=dataResult.paths;
//			
//		},error:function(e){
//			
//		}
//		
//	});
//	
	
	
});

//换行
function lineBreak(strLabel){
	var str=strLabel;
	if(str.length>12){
		str=strLabel.substr(0,9)+"...";
	}
	if(str.length>4){
		var len=(parseInt(strLabel.length/4)==parseFloat(strLabel.length/4))?parseInt(strLabel.length/4)-1:(parseInt(strLabel.length/4));
		for(var i=0;i<len;i++){
			if(i==0){
				str=str.insert(4,"\n");
			}else
			{
				var j=str.length;
				str=str.insert((i+1)*4+i,"\n");
			}
		}
		return str;
	}
	else
	{
		return strLabel;
	}
}
 String.prototype.insert = function (index, item) {
    var temp = [];
    for (var i = 0; i < this.length; i++) {
        temp.push(this[i]);
    }
    temp.splice(index, 0, item);
    return temp.join("")
};
//路径
function reloadData(path,nodes,edges){
	var nodeList=nodes;
	var edgesList=edges;
	var a=path; //pathload[0];//[1,2,3,4];
   for (var i=0;i<nodeList.length;i++) {
  	var markIndex=0;
  	
  	for (var j=0;j<a.length;j++) {
  		
  		if(nodeList[i].id==a[j] ){
  			markIndex+=1;  			
  		}
  	}
  	if(markIndex==1){//证明是高亮的对象
  		nodeList[i].width=4;
  		if(nodeList[i].id==a[0]||nodeList[i].id==a[a.length-1]){
  			nodeList[i].color='#fd704f';
  		}else
  		{  			
  			nodeList[i].color='#78aaee';  			
  		}  		
  	}
  	else
  	{ 		
  		nodeList[i].width=1;
  		nodeList[i].color='#c9ddf8';
  		nodeList[i].background='#c9ddf8';
  	}
  	
  }
  
  for (var i=0;i<edgesList.length;i++) {
  	var markIndex=0;
  	for (var j=0;j<a.length;j++) {
  		
  		if(edgesList[i].from==a[j] ){
  			markIndex+=1;  			
  		}else if(edges[i].to==a[j])
  		{
  			markIndex+=1;
  		}
  	}
  	if(markIndex==2){//证明是高亮的对象
  		edgesList[i].width=2;
  		edgesList[i].font={color:'#3c3c3c'};
  		edgesList[i].color={color:'#78aaee'};
  	}
  	else
  	{
  		edgesList[i].width=1;
  		edgesList[i].font={color:'#b1b1b1'};
  		edgesList[i].color={color:'#c9ddf8'};
  	}
  }
  return {n:nodeList,e:edgesList};
}

function reashData(nodes,edges){
	var options = {
        nodes: {
          shape: 'dot',
          scaling:{
            label: {
            min:4,
            max:12
            }
          },font: {//字体配置
                color: '#fff',//颜色
                size: 14, // 大小，单位px
                face: 'arial',//字体
                //background: 'none',//背景
                align: 'center',//位置left right center
          },
        },
        edges: {
      		font: {
        		size: 14,
        		align:'bottom'
      		},
      		selfReferenceSize: 30,
      		chosen:{
                edge: function(values, id, selected, hovering){
                    values.toArrowType = 'arrow';
                    values.color=values.color;
                    values.size += 2;
                    values.width += 2;
                }
    	}
      }
      };
            	
        //创建一个数组
  	var nodes = new vis.DataSet(nodes);
	var edges = new vis.DataSet(edges);
  // create a network
  	var container = document.getElementById('mynetwork');
  	var data = {
    	nodes: nodes,
   	 	edges: edges
  	};
  	var network = new vis.Network(container, data, options);
}

//清理数据
function clearData(nodeList,edgeList){
	for(var i=0;i<nodeList.length;i++){
		nodeList[i].width=1;
  		nodeList[i].color='#78aaee';
  		nodeList[i].background='#78aaee';
	}
	for(var i=0;i<nodeList.length;i++){
		edgeList[i].width=1;
  		edgeList[i].color='#78aaee';
  		edgeList[i].background='#78aaee';
	}
	 return {n:nodeList,e:edgeList};
}