var company={	

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
			$.ajax({
				url:adminpath+"/company/addCompany.do",
				type:"post",
				data:{Name:Name,Code:Code,Province:Province,City:City,
					Area:Area,Address:Address,Tel:Tel,Atten:Atten,
					UserName:UserName,Password:Password},
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
				},
				error:function(){
					alert("获取失败");
				}
			});
		},
		updateFinish:function(){
			console.log(arguments);
			var json=arguments[0];
			$.areas('Province','City','Area',function(){
				$("#Province").val(json.province);
				$("#Province").change();
				$("#City").val(json.city);
				$("#City").change();
				$("#Area").val(json.area);
			});
			$("#UserName").parent().html(json.userName);
			$("#Code").parent().html(json.code);
		},
		//修改用户信息
		updateCompany:function(obj){
			var Id = $("#CompanyForm").find("#Id").val().trim();
			console.log(Id);
			var Name = $("#Name").val().trim();
			var Province = $("#Province").val().trim();
			var City = $("#City").val().trim();
			var Area = $("#Area").val();
			var Address = $("#Address").val();
			var Tel = $("#Tel").val();
			var Atten = $("#Atten").val();
			var Password = $("#Password").val();
			$.ajax({
				url:adminpath+"/company/update.do",
				type:"post",
				data:{Id:Id,Name:Name,Province:Province,City:City,Area:Area,
					Address:Address,Tel:Tel,Atten:Atten,Password:Password},
				dataType:"json",
				success:function(data){
					if(data.state==0){
						load();
						$(obj).dialog('close');	
						alert("修改成功");
					}
				},
				error:function(){
					alert("修改失败");
				}
			});
		}		
}