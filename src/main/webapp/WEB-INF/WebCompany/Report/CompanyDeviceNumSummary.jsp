<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../Meta.html"></jsp:include>
</head>
<body>
	<!--内容-->
	<div class="content-area">
		<!--面包屑-->
		<div class="content-title">设备总数量概况</div>
		<!--//面包屑-->

		<!--内容盒-->
		<div class="custom-area" id="scroll-box">
			<!--内盒-->
			<div class="custom-box" scrollarea="box">
				<!--滚动用盒层-->
				<div class="custom-scroll" scrollroom="box">

					<!--列表切换-->
					<div class="label-box" label-box="group1">
						<!--按钮-->
						<div class="mt" label-btncom="group1">
							<ul>
								<li label-btn="group1|click|key1" clicks="window.loadData(1);"
									class="select">柱状图</li>
								<!--<li label-btn="group1|click|key1" clicks="window.loadData(2);">折线图</li>-->
								<li label-btn="group1|click|key1" clicks="window.loadData(3);">饼形图</li>
							</ul>
						</div>
						<input type="hidden" id="type" value="1" />
						<!--//按钮-->
						<div class="mc" label-limitarea="group1">
							<div class="for select" label-area="group1|key1">
								<!--扩展按钮层-->
								<div class="extend-button">
									<div class="button expert-close">
										<a href="javascript:void(0);" class="btn"
											onclick="expert(this);">筛选条件</a>
										<!--高级搜索-->
										<div class="expert-list">
											<!--列表-->
											<div class="list">
												<!--表单组件-->
												<div class="custom-table">
													<!--表单内盒-->
													<div class="form">
														<table border="0" cellspacing="0" cellpadding="0"
															id="filterForm">
															<colgroup>
																<col style="width: 100px;" />
																<col style="width: auto;" />
															</colgroup>

															<tbody>
																<tr>
																	<th><p class="name">年月日：</p></th>
																	<td><div class="inputbox">
																			<input type="text" id="StartTime" name="StartTime" value=""
																				
																				onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});">
																		</div>
																		<div class="inputbox">
																			<input type="text" id="EndTime" name="EndTime" value=""
																				
																				onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});">
																		</div>
																		</td>
																</tr>
															
																<tr>
																	<th><p class="name">设备类型：</p></th>
																	<td><div class="selectbox">
																			<select id="DeviceTypeId"><option value="0"
																					selected="selected">全部</option>
																				</select>
																		</div></td>
																</tr>
																<tr>
																	<th><p class="name">单位名称：</p></th>
																	<td><div class="selectbox">
																			<select id="CompanyId"><option value="0"
																					selected="selected">全部</option>
																				</select>
																		</div></td>

																</tr>
															</tbody>
														</table>
													</div>
													<!--//表单内盒-->
												</div>
												<!--//表单组件-->
											</div>
											<!--//列表-->
											<!--按钮-->
											<div class="button">
												<a href="javascript:void(0);" onclick="load(); "
													class="btn">筛选</a>  <a
													href="javascript:void(0);"
													onclick="$('#filterForm').formReset();" class="btn">清空</a>
											</div>
											<!--//按钮-->
										</div>
										<!--//高级搜索-->
									</div>
								</div>
								<!--//扩展按钮层-->
							</div>
						</div>
					</div>
					<!--//列表切换-->

					<!--表格组件-->
					<div class="custom-table">
						<!--表格内盒-->
						<div class="form" id="customChangeTable">
							<table border="0" cellpadding="0" cellspacing="0">
								<colgroup>
									<col style="width: auto;" />
									<col style="width: auto;" />
									<col style="width: auto;" />
									<col style="width: auto;" />
									<col style="width: auto;" />
								</colgroup>

								<thead>
									<tr>
										<td>
											<p class="name center">设备名</p>
										</td>
										<td>
											<p class="name center">设备数量</p>
										</td>
										<td>
											<p class="name center">故障数量</p>
										</td>
										<td>
											<p class="name center">检修数量</p>
										</td>
										<td>
											<p class="name center">未检修数量</p>
										</td>
									</tr>
								</thead>

								<tbody id="pageBody"></tbody>
							</table>
						</div>
						<!--//表格内盒-->
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
</body>
</html>
<jsp:include page="../template/companyDeviceNumSummary.html"></jsp:include>
<script>
function load(){
	var start=$("#StartTime").val(),end=$("#EndTime").val();
	if(start.length==0)start="2017/01/01";
	if(end.length==0) end=new Date().toLocaleDateString();
	var data={
			companyId:$("#CompanyId").val(),
			deviceTypeId:$("#DeviceTypeId").val(),
			startTime:start,
			endTime:end
			};

	$("#pageBody").loadList("bodyListTemplate",data,null,null,'/fire/company/device/getCompanyDeviceNumSummaryList.do')
}
$(function(){
	load();
});
</script>

