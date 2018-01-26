var company={	
		enumList:[],
		init:function(){
			$.post(path+"/categoryenum/getEnumList.do",{enumType:"UnitPropertiesEnum"},function(){
				var json=arguments[0];
				if(json.state==0){
					company.enumList=json.data;
				}
			});
		},
		addCompany:function(obj){
			if(!$("#CompanyForm").formValidate())return;
			var Name = $("#Name").val().trim();
			var Code = $("#Code").val().trim();
			var Province = $("#Province").val().trim();
			var City = $("#City").val().trim();
			var Area = $("#Area").val().trim();
			var Address = $("#Address").val().trim();
			var Tel = $("#Tel").val().trim();
			var Atten = $("#Atten").val().trim();
			var UserName = $("#UserName").val().trim();
			var Password = $("#Password").val().trim();
			var Unitproperties = $("#Unitproperties").val();
			var Buildingtype = $("#Buildingtype").val();
			var Isimport = $("#Isimport").val();
			$.ajax({
				url:adminpath+"/company/addCompany.do",
				type:"post",
				data:{Name:Name,Code:Code,Province:Province,City:City,
					Area:Area,Address:Address,Tel:Tel,Atten:Atten,
					UserName:UserName,Password:Password,Unitproperties:Unitproperties,Buildingtype:Buildingtype,
					Isimport:Isimport},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("添加成功");
						$(obj).dialog('close');
						load();
					}else{	
						alert(result.message);		
					}								

				}
			});
		},
		//获取用户信息
		getComany:function(Id){
			$.ajax({
				url:adminpath+"/company/getCompany.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(data){					
					if(data.state==0){
						user.openDialog(data.data);
					}
				}
			});
		},
		updateFinish:function(){
			console.log(arguments);
			var json=arguments[0];
			var enumarr=[];
			$.each(company.enumList,function(i,item){
				enumarr.push("<option value='"+item.enumValue+"'>"+item.enumDesc+"</option>")
			});
			$("#Unitproperties").html(enumarr.join());
			if(json=="add"){
				$.areas('Province','City','Area');
				return ;
			}
			
			$.areas('Province','City','Area',function(){
				if(json.province!=null&&json.province!=""){
				$("#Province").val(json.province);
				$("#Province").change();
				$("#City").val(json.city);
				$("#City").change();
				$("#Area").val(json.area);
				}
			});
			$("#UserName").parent().html(json.userName);
			$("#Code").parent().html(json.code);			
		},
		//修改用户信息
		updateCompany:function(obj){
			if(!$("#CompanyForm").formValidate())return;
			var Id = $("#CompanyForm").find("#Id").val().trim();
			console.log(Id);
			var Name = $("#Name").val().trim();
			var Province = $("#Province").val();
			var City = $("#City").val();
			var Area = $("#Area").val();
			var Address = $("#Address").val();
			var Tel = $("#Tel").val();
			var Atten = $("#Atten").val();
			var Password = $("#Password").val();
			var Unitproperties = $("#Unitproperties").val();
			var Buildingtype = $("#Buildingtype").val();
			var Isimport = $("#Isimport").val();
			$.ajax({
				url:adminpath+"/company/update.do",
				type:"post",
				data:{Id:Id,Name:Name,Province:Province,City:City,Area:Area,
					Address:Address,Tel:Tel,Atten:Atten,Password:Password,Unitproperties:Unitproperties,Buildingtype:Buildingtype,
					Isimport:Isimport},
				dataType:"json",
				success:function(data){
					if(data.state==0){
						load();
						$(obj).dialog('close');	
						alert("修改成功");
					}
				}
			});
		}		
}