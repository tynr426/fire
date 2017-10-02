var device={
		deviceTypeList:null,
		addDevice:function(obj){
			var DeviceTypeId = $("#DeviceTypeId").val().trim();
			var Manufacturer = $("#Manufacturer").val().trim();
			var Model = $("#Model").val().trim();
			var Spec = $("#Spec").val().trim();
			var Buildings = $("#Buildings").val();
			var Floor = $("#Floor").val();
			var Position = $("#Position").val();
			var Passageway = $("#Passageway").val();
			var AddTime = $("#AddTime").val();
			var Detail = $("#Detail").val().trim();
			var devicejson={DeviceTypeId:DeviceTypeId,Manufacturer:Manufacturer,
					Model:Model,Spec:Spec,Buildings:Buildings,Floor:Floor,
					Position:Position,Passageway:Passageway,AddTime:AddTime,
					Detail:Detail};

			var arrValue=[];
			if(device.deviceTypeList!=null){
				$.each(device.deviceTypeList,function(i,item){
					if(item.id==DeviceTypeId){	
						for(var i=0;i<item.list.length;i++){
							var child=item.list[i];
							var multipleValue=[];
							if(child.editorType=="texts"||child.editorType=="checkbox"){

								$.each(child.candidate,function(j,jitem){
									p_id="parameter_"+child.id+"_"+j;
									multipleValue.push("'"+p_id+"':'"+$("#"+p_id).val()+"'");

								});	
							}
							else{
								p_id="parameter_"+child.id;
								multipleValue.push("'"+p_id+"':'"+$("#"+p_id).val()+"'");

							}
							arrValue.push({
								DeviceTypeId:DeviceTypeId,
								ParameterId:child.id,
								Description:child.Description,
								Value:"{"+multipleValue.join(',')+"}"
							});	
						}
					}

				});

			}
			for(var index=0;index<arrValue.length;index++){
				devicejson['list[' + index +'].DeviceTypeId']=arrValue[index].DeviceTypeId;
				devicejson['list[' + index +'].ParameterId']=arrValue[index].ParameterId;
				devicejson['list[' + index +'].Value']=arrValue[index].Value;
			}
			$.ajax({
				url:path+"/device/add.do",
				type:"post",
				data:devicejson,
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("添加成功");
						$(obj).dialog('close');
						load();
					}else{	
						alert(result.message);		
					}								

				},
				error:function(){
					alert("添加失败");
				}
			});
		},
		getDevice:function(Id){
			$.ajax({
				url:path+"/manager/getManager.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(result){					
					if(data.state==0){
						user.openDialog(result.data);
					}
				},
				error:function(){
					alert("获取失败");
				}
			});
		},
		deviceFinish:function(){
			var arrFloor=[],arrPo=[],arrPassage=[];
			for(var i=-2;i<34;i++){
				arrFloor.push("<option value='"+i+"'>"+i+"</option>");
			}
			$("#Floor").append(arrFloor.join(''));

			$.each(dict.position,function(i,item){
				arrPo.push("<option value='"+i+"'>"+item+"</option>");
			});
			$("#Position").append(arrPo.join(""));
			$.each(dict.passageway,function(i,item){
				arrPassage.push("<option value='"+i+"'>"+item+"</option>");
			});
			$("#Passageway").append(arrPassage.join(""));
			device.getDeviceType();
			var deviceType=[];
			if(device.deviceTypeList!=null){
				$.each(device.deviceTypeList,function(i,item){
					deviceType.push("<option value='"+item.id+"'>"+item.name+"</option>");
				});

				$("#DeviceTypeId").append(deviceType.join());
			}

			//var json=arguments[0];
		},
		//获得所有的设备类型
		getDeviceType:function(){
			$.ajax({
				url:path+"/deviceType/findAll.do",
				async:false,
				type:"post",
				dataType:"json",
				success:function(result){
					if(result.state==0){
						device.deviceTypeList=result.data;
					}
				}

			});
		},
		/*选择设备类型是触发的事件*/
		deviceTypeChange:function(obj){
			if(obj.value!=""){
				if(device.deviceTypeList!=null){
					$.each(device.deviceTypeList,function(i,item){
						if(item.id==obj.value){
							var str="";
							var template=$("#DeviceTypeParameterFormTemplate").html();
							for(var i=0;i<item.list.length;i+=2){
								str+="<tr>";
								if(item.list.length%2!=0){
									str+="<tr colspan='3'>";
								}
								for(var j=0;j<2&&i+j<item.list.length;j++){
									var child=item.list[i+j];
									if(child.editorType=="texts"||child.editorType=="select"){
										child.candidate=$.parseJSON(child.candidate);
									}
									str+=$("#DeviceTypeParameterFormTemplate").tmpl(child).html();

								}
								str+="</tr>";
							}
							$("#tbDevice").append(str);
						}
					});

				}
			}
		},
		updateDevice:function(obj){
			var id = $("#ManagerForm").find("#Id").val();
			var Password = $("#Password").val().trim();
			var Name = $("#Name").val().trim();
			var Email = $("#Email").val().trim();
			var Mobile = $("#Mobile").val().trim();
			var Position = $("#Position").val().trim();
			$.ajax({
				url:path+"/device/update.do",
				type:"post",
				data:{Id:id,Password:Password,Name:Name,Email:Email,Mobile:Mobile,Position:Position},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("您已修改成功");
						load();
						$(obj).dialog('close');
					}else{	
						alert(result.message);			
					}								

				},
				error:function(){
					alert("修改失败");
				}
			});
		},


}


