<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<jsp:include page="Meta.html"></jsp:include>

<script type="text/javascript">
	$(function(){
		validateCode.loadValidate($("#VerifyCode")[0]);
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		           loginCM.login(ev);
		     }
		}
	});
</script>
</head>

<body>
	<!--外框-->
	<div class="login-wrap">
		<!--悬浮LOGO-->
		<div class="float-logo">
			<!--<img src="~/Images/logo.png" alt="" />-->
		</div>
		<!--//悬浮LOGO-->
		<!--登录体部-->
		<div class="login-body">
			<!--输入控件-->
			<div class="login-input">
				<!--半透明层-->
				<div class="opacity-panel"></div>
				<!--//半透明层-->
				<!--LOGO-->
				<div class="logo">FIRE</div>
				<!--//LOGO-->
				<!--店铺名称-->
				<p class="title">公司客户端</p>
				<!--//店铺名称-->
				<!--控件层-->
				<div class="login-text">
					<ul id="loginForm">
						<li class="user-name"><i class="icon name-icon"></i>
							<div class="inputbox">
								<input id="Code" type="text" validate="isnull" value=""
									name="Code" placeholder="公司代码" error="代码格式不正确" maxlength="16" />
						</div></li>
						<li class="user-name"><i class="icon name-icon"></i>
							<div class="inputbox">
								<input type="text" id="UserName" name="UserName" maxlength="12" value=""
									placeholder="用户名" error="用户名格式不正确！" validate="isnull|username" />
							
							</div></li>
						<li class="user-password"><i class="icon pass-icon"></i>
							<div class="inputbox">
								<input id="password" type="password" validate="isnull" value=""
									name="password" placeholder="密码" error="密码格式不正确" maxlength="16" />
							</div> </li>
						<li class="user-code">
							<div class="inputbox">
								<input id="verifyCode" name="verifycode" validate="isnull"
									placeholder="验证码" type="text" value="" maxlength="4" /> 
							</div>
							<div class="code" title="点击刷新">
								<img title="点击刷新图片" id="VerifyCode"
									onclick="validateCode.loadValidate(this)"
									nolazy="0">
							</div>
						</li>
					</ul>
				</div>
				<!--//控件层-->
				<!--免登录-->
				<div class="login-remember">
					<input type="checkbox" name="Days" id="Days" checked="checked"
						value="1" /> <label>一天内免登录</label>
				</div>
				<!--//免登录-->
				<!--按钮-->
				<div class="button">

					
					<a onclick="loginCM.login(this);" class="btn">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</a>
				</div>

				<!--//按钮-->
			</div>
			<!--//输入控件-->
		</div>
		<!--//登录体部-->

		<!--底部-->
		<div class="login-footer">
			<!--内容-->
			<div class="area w">
			
				Copyright &copy; 2017-2018 <a
					href="http://www.baidu.com" target="_blank">某某公司</a>&nbsp;&nbsp;售后电话：400-6878-366
			</div>
			<!--//内容-->
		</div>
		<!--//底部-->
	</div>
	<!--//外框-->
</body>
</html>
<script type="text/javascript">
	$("#verifyCode").focus();
</script>