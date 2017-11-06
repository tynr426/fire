<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理</title>
<jsp:include page="Meta.html"></jsp:include>
<script type="text/javascript" src="/fire/js/webCompany/iframe.js">
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
						title="退出" onclick="loginCM.loginOut();"></a></li>
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
					id="contentIframe" src="/fire/company/manager/toManager.do" class="iframe" width="100%"
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
                        "Id": 1, "ParentId": 0, "MenuName": "基础信息", "CssClass": "sys", "children": [
                            {
                                "Id": 101, "ParentId": 1, "MenuName": "基础信息", "children": [
                                    { "Id": 10101, "ParentId": 101, "MenuName": "管理员列表", "Link": "/fire/company/manager/toManager.do" },
                                    { "Id": 10102, "ParentId": 101, "MenuName": "公司信息", "Link": "/fire/admin/company/toCompany.do" }
                                ]
                            }
                         ],
                        "Id": 2, "ParentId": 0, "MenuName": "管理信息", "CssClass": "product", "children": [
                            {
                                     "Id": 201, "ParentId": 2, "MenuName": "管理信息", "children": [
                                    { "Id": 20101, "ParentId": 201, "MenuName": "设备列表", "Link": "/fire/company/device/device.do" },
                                    { "Id": 20102, "ParentId": 201, "MenuName": "检查报告", "Link": "/fire/company/device/device.do" },
                                    { "Id": 20103, "ParentId": 201, "MenuName": "任务列表", "Link": "/fire/company/assignment/toAssignment.do" }
                                ]
                            }
                         ],
                        "Id": 3, "ParentId": 0, "MenuName": "统计信息", "CssClass": "m-report", "children": [
                            {
                                "Id": 301, "ParentId": 3, "MenuName": "统计信息", "children": [
                                    { "Id": 30101, "ParentId": 301, "MenuName": "管理员列表", "Link": "/fire/company/manager/toManager.do" },
                                    { "Id": 30102, "ParentId": 301, "MenuName": "公司信息", "Link": "/fire/admin/company/toCompany.do" }
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