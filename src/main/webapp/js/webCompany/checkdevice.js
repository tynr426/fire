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
							var config={ 
									content: div, 
									width: 700, 
									height: 500, 
									title: "指派任务", 
									callback: checkdevice.assignment, 
									arguments: [id] 
							} ;
							if(result.data.status==2){
								config.isView=true;
							}
							pub.openDialog(config);
							var arr=[];
							manager.getManagerList(function(data){
								$.each(data,function(i,item){
									arr.push('<option value="'+item.id+'">'+item.name+'</option>')
								});
								$("#ToManagerId").append(arr.join(""));
							});
							if(result.data.status==2){
								checkdevice.getAssigment(id);
							}
						}
						
					}
				});
			},
			getAssigment:function(checkId){
				$.ajax({
					url:companypath+"/assignment/getAssignmentByCheckId.do",
					type:"post",
					dataType:"json",
					data:{checkId:checkId},
					success:function(result){
						$("#ToManagerId").val(result.data.toManagerId);
						$("#PredictTime").val(result.data.predictTime);
						$("#Remark").val(result.data.assignmentRemark);
					}		
				});
			},
			getJsonValue:function(json,name){
				var v="";
				$.each(json,function(key,value){
					if(key.firstUpperCase()==name){
						v= value;
						return;
					}
					
				});
				return v;
			},
			assignment:function(id){
				if(!$("#CheckDeviceForm").formValidate())return;
				var obj=this;
				var ToManagerId=$("#ToManagerId").val();
				var PredictTime=$("#PredictTime").val();
				var Remark=$("#Remark").val();
				$.ajax({
					url:companypath+"/assignment/save.do",
					type:"post",
					dataType:"json",
					data:{CheckId:id,ToManagerId:ToManagerId,
						PredictTime:PredictTime,Remark:Remark},
					success:function(result){
						if(result.state==0){
							alert("操作成功!");
							$(obj).dialog('close');
						}
					}
				});
			}
}