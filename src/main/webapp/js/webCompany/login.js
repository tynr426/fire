var loginCM = {

		login:function(obj){
			if(!$("#loginForm").formValidate())return;
			
			var name = $("#UserName").val().trim();
			var pwd = $("#password").val().trim();
			var vCode = $("#verifyCode").val().trim();
			var code = $("#Code").val().trim();
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
			if(code==""){
				alert("代码为空");
				ok=false;
			}
			if(ok){
				$.ajax({
					url:path+"/company/login.do",
					type:"post",
					data:{username:name,password:pwd,verifyCode:vCode,code:code},
					dataType:"json",
					success:function(result){
						if(result.state==0){
							window.location.href=path+"/company/main.do";
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
					url:path+"/company/loginOut.do",
					dataType:"json",
					success:function(result){
						window.location.href="toLogin.do";
					},
					error:function(){
						alert(arguments);
					}

				});
			}
		}
}