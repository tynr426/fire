<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理员</title>
<jsp:include page="/Meta.html"></jsp:include>
</head>
<body>


	<!--内容-->
	<div class="content-area">
		<!--面包屑-->
		<div class="content-title">管理员</div>
		<!--//面包屑-->
		<!--内容盒-->
		<div class="custom-area" id="scroll-box">
			<!--内盒-->
			<div class="custom-box" scrollarea="box">
				<!--滚动用盒层-->
				<div class="custom-scroll" scrollroom="box" lazyscroll="box">
					<div class="label-box" label-box="group1">


						<div class="mc" label-limitarea="group1">
							<div class="for select" label-area="group1|key1">
								<div class="extend-button">


									<div class="button">
										<a href="javascript:void(0);"
											onclick="frm.add({ title: '添加单位', formId: 'CompanyFormTemplate', callback: company.addCompany, width: 800, height: 400, finish: $.areas,finishArgs:['Province','City','Area'] });"
											class="btn">添加单位</a>
									</div>


								</div>
							</div>


						</div>
					</div>

					<!--表格组件-->
					<div class="custom-table">
						<!--表格内盒-->
						<div class="form" id="customChangeTable">
							<table border="0" cellpadding="0" cellspacing="0">
								<colgroup>
									<col style="width: 25px;" />
									<col style="width: auto;" />
									<col style="width: 150px;" />
									<col style="width: 150px;" />
									<col style="width: 150px;" />
									<col style="width: 150px;" />
									<col style="width: 61px;" />
									<col style="width: 61px;" />
								</colgroup>

								<thead>
									<tr>
										<td>
											<!--自定义复选按钮--> <input type="checkbox" value="" name=""
											checkall="1"> <!--//自定义复选按钮-->
										</td>
										<td>
											<p class="name center">logo</p>
										</td>
										<td>
											<p class="name center">单位名称</p>
										</td>
										<td>
											<p class="name center">单位代码</p>
										</td>
										<td>
											<p class="name center">序列号</p>
										</td>
										<td>
											<p class="name center">联系方式</p>
										</td>
										<td>
											<p class="name center">状态</p>
										</td>
										<td>
											<p class="name">操作</p>
										</td>
									</tr>
								</thead>

								<tbody id="pageBody"></tbody>
							</table>
						</div>
						<!--//表格内盒-->
						<!--通用分页-->
						<div class="page" id="PageBarList"></div>
						<!--//通用分页-->
					</div>
					<!--//表格组件-->
				</div>
				<!--//滚动用盒层-->
			</div>
			<!--//内盒-->
		</div>
		<!--//内容盒-->
	</div>
	<!--//内容-->
	<div id="AccountFormTemplate" style="display: none">
       
    </div>      
</body>
</html>
<jsp:include  page="../template/company.html"></jsp:include>
<script language="javascript" type="text/javascript">
 $(function(){
	 load();
  }); 
 function load(){
	  var config={
				url:path+"/company/showCompanyPage.do",
	  			pageSize:3,
	  			pageIndex:1,
	  			barSize:3,
	  			pageBarId:"PageBarList",
	  			templateId:"bodyListTemplate",
	  			container:"pageBody"
	  			};
	  var pageInfo=new ecPage.fn._init(config);
 }
</script>
