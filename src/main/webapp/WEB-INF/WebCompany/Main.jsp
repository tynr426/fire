<%@page import="fire.common.entity.CompanyResult"%>
<%@page import="fire.web.utils.Company"%>
<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理</title>
<jsp:include page="Meta.html"></jsp:include>
<script type="text/javascript" src="/fire/js/webCompany/iframe.js">
	
</script>
</head>
<%
	CompanyResult company = Company.getCompany();
%>
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
									onclick="frm.update({ title: '管理员信息完善', formId: 'AccountBoxTemplate', id: '<%=company.getManagerId()%>', width: '500px', height: 'auto',	 url:'/company/manager/getManager.do',callback: manager.updateIndexManager,finish:manager.updateFinish });">完善信息</a>
								</li>
								<li><a href="javascript:void(0);"
									onclick="frm.add({ title: '修改密码', formId: 'PasswordBoxTemplate', callback: manager.updatePassword, width: 400, height: 280 })">修改密码</a>
								</li>
							</ol>
						</div> <!--//扩展显示--></li>
					<li><a href="javascript:void(0);" class="btn clean"
						title="清理缓存" onclick="main.clearCache();"></a></li>
					<li><a href="javascript:frame.go('/fire/company/device/toCompanyDeviceNumSummary.do');" class="btn main" title="主界面"></a></li>
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
					id="contentIframe" src="/fire/company/manager/toManager.do"
					class="iframe" width="100%" height="100%" frameborder="0"
					scrolling="no"></iframe>
			</div>
			<!--//右侧内容-->
		</div>
		<!--//体部-->

	</div>
	<!--//外框-->
	
</body>
</html>


<script type="text/javascript">
	$(function() {
		var template = $('#topMenuTemplate').html();
		var menuList = managerMenu.getMenuRelationList(
<%=company.getId()%>,<%=company.getManagerId()%>);
                    $.tmpl( template, menuList).appendTo("#topMenu");
                    menuAction();
                });
                function getCurrentUserId(){
                	return '<%=company.getUserId()%>';
                }
</script>
<jsp:include page="template/menu.html"></jsp:include>