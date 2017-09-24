var deviceTypeAll={
		appJson:null,
		addDeviceType:function(obj){
			var Name = $("#Name").val().trim();
			var UseTime = $("#UseTime").val();
			$.ajax({
				url:"/fire/deviceType/addDeviceType.do",
				type:"post",
				data:{Name:Name,UseTime:UseTime},
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
		getDeviceType:function(Id){
			$.ajax({
				url:path+"/deviceType/getDeviceType.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(result){					
					if(data.state==0){
						deviceType.appJson=result.data;
						user.openDialog(deviceType.appJson);
					}
				},
				error:function(){
					alert("获取失败");
				}
			});
		},
		updateDeviceType:function(obj){
			var id = $("#DeviceParameterForm").find("#Id").val();
			var Name = $("#Name").val().trim();
			var UseTime = $("#UseTime").val().trim();
			$.ajax({
				url:path+"/deviceType/update.do",
				type:"post",
				data:{Id:id,Name:Name,UseTime:UseTime},
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
		}

}