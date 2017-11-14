var checkdevice={
			detail:function(id){
				$.ajax({
					url:companypath+"/checkdevice/getCheckDevice.do",
					type:"post",
					dataType:"json",
					data:{id:id},
					success:function(result){
						if(result.state==0){
							var div = document.createElement("div");
							div.innerHTML = $("#CheckDeviceFormTemplate").html();
						
							$(div).find("var,img").each(function(){
								var name=$(this).attr("id");
								if(this.tagName=="IMG"){
									$(this).src=checkdevice.getJsonValue(result.data,name);
								}
								else{
									$(this).html(checkdevice.getJsonValue(result.data,name));
								}
							});
							
							pub.openDialog({ content: div, width: 500, height: 450, title: "分配菜单", callback: null, arguments: [] } );
						}
						
					},
					error:function(){
						alert("加载失败!");
					}
				});
			},
			getJsonValue:function(json,name){
				var v="";
				$.each(json,function(key,value){
					if(key.firstUpperCase()==name){
						v= value;
						if(name=="SeverityLevel"){
							switch(v){
							case 1:v="一般"; break;
							case 2:v="扬中"; break;
							}
						}
						return;
					}
					
				});
				return v;
			}
}