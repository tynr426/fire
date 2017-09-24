var user={	
		
		addUser:function(obj){
			var username = $("#UserName").val().trim();
			var password = $("#Password").val().trim();
			var nickname = $("#NickName").val().trim();
			var email = $("#Email").val().trim();
			var mobile = $("#Mobile").val().trim();
			var status = $("input[name=Status]:checked").val();
			$.ajax({
				url:path+"/user/regist.do",
				type:"post",
				data:{userName:username,password:password,nickName:nickname,email:email,mobile:mobile,status:status},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert(result.data+"您已注册成功");
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
		getUser:function(Id){
			$.ajax({
				url:path+"/user/getUser.do",
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
		//修改用户信息
		updateUser:function(obj){
			var id = $("#AccountFormTemplate").find("#Id").val().trim();
			console.log(id);
			var nickname = $("#AccountFormTemplate").find("#NickName").val().trim();
			var email = $("#AccountFormTemplate").find("#Email").val().trim();
			var mobile = $("#AccountFormTemplate").find("#Mobile").val().trim();
			var status = $("#AccountFormTemplate").find("input[name=Status]:checked").val();
			$.ajax({
				url:path+"/user/update.do",
				type:"post",
				data:{Id:id,nickName:nickname,email:email,mobile:mobile,status:status},
				dataType:"json",
				success:function(data){
					if(data.state==0){
						load();
						$(obj).dialog('close');	
					}
				},
				error:function(){
					alert("修改失败");
				}
			});
		}		
}