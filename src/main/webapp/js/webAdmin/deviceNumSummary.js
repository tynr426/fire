var deviceNumSummary={
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
				}
			});
		},
		loadCompanyName:function(obj){
			$.ajax({
				url:adminpath+"/company/showCompany.do",
				type:"post",
				dataType:"json",
				success:function(result){
					var str = "";
					if(result.state==0){
						$.each(result.data,function(i,item){
							str+="<option value='"+item.id+"'>"+item.name+"</option>";
						});
						$("#CompanyId").append(str);
					}
				}
			});
		},
		loadUnitproperties:function(){
			var enumarr=[];
			$.post(path+"/categoryenum/getEnumList.do",{enumType:"UnitPropertiesEnum"},function(){
				var json=arguments[0];
				if(json.state==0){
					var enumList=json.data;
					$.each(enumList,function(i,item){
						enumarr.push("<option value='"+item.enumValue+"'>"+item.enumDesc+"</option>")
					});
					$("#Unitproperties").append(enumarr.join());
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
		}


}


