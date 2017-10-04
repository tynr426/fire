var login = {

		login:function(){
			if(!$("#loginForm").formValidate())return;
			var name = $("#UserName").val().trim();
			var pwd = $("#password").val().trim();
			var vCode = $("#verifyCode").val().trim();
			var ok = true;
			if(name==""){
				alert("用户名为空");
				ok=false;
			}
			if(pwd==""){
				alert("密码为空");
				ok=false;
			}
			if(vCode==""){
				alert("验证码为空");
				ok=false;
			}
			if(ok){
				$.ajax({
					url:path+"/admin/login.do",
					type:"post",
					data:{username:name,password:pwd,verifyCode:vCode},
					dataType:"json",
					success:function(result){
						if(result.state==0){
							window.location.href=path+"/admin/main.do";
						}else{	
							alert(result.message);		
						}								

					},
					error:function(){
						alert("登录失败");
					}
				});
			}
		},
		loginOut:function(){
			if(confirm("确认退出吗?")){
				$.ajax({
					url:path+"/user/loginOut.do",
					dataType:"json",
					success:function(result){
						window.location.href="login";
					},
					error:function(){
						alert(arguments);
					}

				});
			}
		}
}