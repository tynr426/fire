var admin = {
		updatePassword:function(obj){
			var oldPwd = $("#OldPwd").val();
			var newPwd = $("#NewPwd").val();
			var pwd = $("#PassWord").val();
			if(oldPwd==newPwd){
				alert("密码不能与原密码相同");
				return;
			}
			if(newPwd!=pwd){
				alert("两次密码输入不一致");
				return;
			}
			$.ajax({
				url:path+"/admin/updatePwd.do",
				type:"post",
				data:{oldPwd:oldPwd,pwd:pwd},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("修改成功,下次登录生效");
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