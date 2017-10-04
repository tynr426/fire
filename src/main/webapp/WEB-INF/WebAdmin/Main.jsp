<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理</title>
<jsp:include page="Meta.html"></jsp:include>
<script type="text/javascript" src="/fire/js/iframe.js">
</script>

</head>

<body>
	<!--外框-->
	<div class="wrap">
		<!--头部-->
		<div class="heads" init="head">
			<!--Logo-->
			<div class="logo">
				<a href="#"> FIRE </a>
			</div>
			<!--//Logo-->
			<!--副LOGO-->
			<div class="s-logo">
				<a href="javascript:void(0);"> 消防平台管理中心 </a>
			</div>
			<!--//副LOGO-->
			<!--工具-->
			<div class="operate-head">
				<ul>
					<!--<li>
                        <a href="<$Var Site.SiteDomain/>" target="_blank" class="btn site" title="查看首页"></a>
                    </li>-->
					<li><a href="javascript:void(0);" class="btn admin">admin</a>

						<!--扩展显示-->
						<div class="extend">
							<ol>
								<li><a href="javascript:void(0);"
									onclick="frm.update({ title: '管理员信息完善', formId: 'AccountFormTemplate', id: '<$var AdminId/>', width: '500px', height: 'auto', completed: pub.top.user.updateCompleted, action: 'update', url: '/webadmin/Passport/User.html' });">完善信息</a>
								</li>
								<li><a href="javascript:void(0);"
									onclick='pub.html({ content: ECF("#PasswordTemplate").html(), width: 500, height: 300, title: "修改密码", buttons: [{ focus: true, name: "确认", callback: user.updPwd }] })'>修改密码</a>
								</li>
							</ol>
						</div> <!--//扩展显示--></li>
					<li><a href="javascript:void(0);" class="btn clean"
						title="清理缓存" onclick="main.clearCache();"></a></li>
					<li><a href="javascript:void(0);" class="btn main" title="主界面"
						onclick="frame.go('SysIndex.html');"></a></li>
					<li><a href="javascript:void(0);" class="btn loginout"
						title="退出" onclick="login.loginOut();"></a></li>
				</ul>
			</div>
			<!--//工具-->
		</div>
		<!--//头部-->
		<!--体部-->
		<div class="bodys" init="body" salemenu="box">
			<!--左侧菜单-->
			<div class="menu menu-close" salemenu="menu" iscroll="box">
				<div class="list iscroll-wrapper">
					<ul id="topMenu"></ul>
				</div>

				<!--展开收缩-->
				<div class="menu-btn">
					<i class="btn" salemenu="sliderbar"></i>
				</div>
				<!--//展开收缩-->
			</div>
			<!--//左侧菜单-->



			<!--右侧内容-->
			<div class="content-box">
				<iframe title="" salemenu="area" name="contentIframe"
					id="contentIframe" src="/fire/admin/company/Company.do" class="iframe" width="100%"
					height="100%" frameborder="0" scrolling="no"></iframe>
			</div>
			<!--//右侧内容-->
		</div>
		<!--//体部-->
		
	</div>
	<!--//外框-->

</body>
</html>
<jsp:include  page="template/menu.html"></jsp:include>

<script type="text/javascript">
                var menuList = [
                    {
                        "Id": 17, "ParentId": 0, "MenuName": "基础信息", "CssClass": "sys", "children": [
                            {
                                "Id": 1701, "ParentId": 17, "MenuName": "基础信息", "children": [
                                    { "Id": 170106, "ParentId": 1701, "MenuName": "单位管理", "Link": "/fire/admin/company/Company.do" },
                                    { "Id": 170107, "ParentId": 1701, "MenuName": "设备类型", "Link": "/fire/deviceType/DeviceType.do" }
                                ]
                            }
                         ]
                    }
                ];

                $(function () {
                    var template = $('#topMenuTemplate').html();
                   
                    $.tmpl( template, menuList).appendTo("#topMenu");
                   
                    menuAction();
                });
                
            </script>