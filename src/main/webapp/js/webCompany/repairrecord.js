var repairrecord={
		addrepairrecord:function(obj){
			if(!$("#RepairrecordForm").formValidate())return;
			var DeviceTypeId = $("#DeviceTypeId").val().trim();
			var Model = $("#Model").val().trim();
			var Certificate = $("#Certificate").val().trim();
			var Description = $("#Description").val();
			var IsFinish = $("#IsFinish").val();
			$.ajax({
				url:companypath+"/device/addRepairrecord.do",
				type:"post",
				data:{DeviceTypeId:DeviceTypeId,Model:Model,Certificate:Certificate,Description:Description,IsFinish:IsFinish,AddTime:AddTime},
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
		getRepairrecord:function(id){
			$.ajax({
				url:companypath+"/device/getRepairrecord.do",
				type:"post",
				data:{id:id},
				dataType:"json",
				success:function(result){	
				},
				error:function(){
					alert("获取失败");
				}
			});
		},
		updateFinish:function(){
			var json=arguments[0];
			$("#DeviceTypeId").parent().html(json.DeviceTypeId);
		},
		updateRepairrecord:function(obj){
			if(!$("#RepairrecordForm").formValidate())return;
			var Certificate = $("#Certificate").val().trim();
			var Description = $("#Description").val();
			var IsFinish = $("#IsFinish").val();
			$.ajax({
				url:companypath+"/manager/update.do",
				type:"post",
				data:{Certificate:Certificate,Description:Description,IsFinish:IsFinish},
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
};