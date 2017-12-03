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
								<li label-btn="group1|click|key1" onclick="GetData(1)"
									class="select">柱状图</li>
								<li label-btn="group1|click|key1" onclick="GetData(2);">折线图</li>
								<li label-btn="group1|click|key1" onclick="GetData(3)">饼形图</li>
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
																	<th><p class="name">年份：</p></th>
																	<td><div class="inputbox">
																			<input type="text" id="Year" name="Year" value="2017"
																				placeholder="年份"
																				onclick="WdatePicker({ dateFmt: 'yyyy'});">
																		</div></td>
																</tr>
															
																<tr>
																	<th><p class="name">设备类型：</p></th>
																	<td><div class="selectbox">
																			<select name="DeviceTypeId" id="DeviceTypeId"><option value="0"
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
												<a href="javascript:void(0);" onclick="GetData(); "
													class="btn">筛选</a> <a href="javascript:void(0);"
													onclick="GetData(); " class="btn">全部</a> <a
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

					<!--报表-->
					<div class="report martop">
						<p class="name">图形报表</p>
						<!--图表数据-->
						<div class="report-data" id="ReportBox"></div>
						<!--//图表数据-->
					</div>
					<!--//报表-->

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

<script src="/fire/Static/Js/jquery.report.js" type="text/javascript"></script>
<script type="text/javascript">
    function GetData(type) {
    	$('[label-btn="group1|click|key1"]').removeClass("select");
    	$('[label-btn="group1|click|key1"]').eq(type-1).addClass("select");
    	var year=$("#Year").val();
    	if(year=="")year=2017;
    	var data={
    			deviceTypeId:$("#DeviceTypeId").val(),
    			year:year
    			};
    	$.ajax({
    		url: '/fire/company/assignment/getAssignmentSummaryList.do',
    		data:data,
    		type:"post",
    		dataType: "json",
    		success:function(result){
    			if(result.state==0){
    				 var option = { type: type, labels: result.data.labels, datasets: [] };
    				
    			        $(result.data.dataSet).each(function (i, n) {
    			        	
    			            var dataset = {};
    			            dataset.label = n.label;
    			            dataset.data = n.data;
    			            option.datasets.push(dataset);
    			        });
    			        $.report.createReport("#ReportBox", option, function () {
    			            
    			            var fkflag = 1;
    			            if (arguments[0].label == "商家") {
    			                fkflag = 1;
    			            } else {
    			                fkflag = 2;
    			            }
    			        });
    			}
    		}
    	});
    }
    
 $(function(){
	 GetData(1);
 })
 </script>

<!--调用模板JS-->
<script type="text/javascript" charset="utf-8">
    //列表切换
    $.getScript('/fire/Static/Js/changeTableUI.js',function(){},true);
</script>
<!--//调用模板JS-->
