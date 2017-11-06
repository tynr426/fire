var deviceQR={
		loadDeviceType:function(obj){
			$.ajax({
				url:path+"/deviceType/findAll.do",
				type:"post",
				dataType:"json",
				success:function(result){
					var str = "";
					if(result.state==0){
						$.each(result.data,function(i,item){
							str+="<option value='"+item.id+"'>"+item.name+"</option>";
						});
						$("#DeviceTypeId").append(str);
					}
				},
				error:function(){
					alert("加载失败");
				}
			});
		},
		reset:function(){
			$("#filterForm").find("input[type=text]").each(function(){
				$(this).val("");
			});
			$("#filterForm").find("select").each(function(){
				$(this).val("");
			});
		},
		search:function(){
			var model = $("#Model").val();
			var deviceTypeId = $("#DeviceTypeId").val();
			var config={
						url:path+"/deviceQR/search.do",
			  			pageSize:10,
			  			pageIndex:1,
			  			barSize:3,
			  			pageBarId:"PageBarList",
			  			templateId:"bodyListTemplate",
			  			container:"pageBody"
			  			};
			  config.data={model:model,deviceTypeId:deviceTypeId}
			  var pageInfo=new ecPage.fn._init(config);
		}


}


