<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
																			<select name="DeviceId"><option value="-1"
																					selected="selected">全部</option>
																				</select>
																		</div></td>
																</tr>
																<tr>
																	<th><p class="name">单位名称：</p></th>
																	<td><div class="selectbox">
																			<select name="CompanyId"><option value="-1"
																					selected="selected">全部</option>
																				</select>
																		</div></td>

																</tr>
																<tr>
																	<th><p class="name">状态：</p></th>
																	<td><label class="custom-radio checked"><input
																			type="radio" value="" name="Status" checked="checked"><span>全部</span></label><label
																		class="custom-radio"><input type="radio"
																			value="1" name="Status"><span>开启</span></label><label
																		class="custom-radio"><input type="radio"
																			value="0" name="Status"><span>关闭</span></label></td>
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
    function GetData() {
        var type =  parseInt( $("#type").val());
        $.ajax({
            data: "<action>getsalessummary</action><Filter>" + $('#filterForm').getValues("xml") + "</Filter>",
            dataType: "xml",
            loading: function () {
                tip.show("数据加载中 ...");
            },
            success: function () {
                tip.hide();
                $("#table").hide();
                $("#pageBody").html("");
                $("#pageBar").html("");

                // 判断动态刷新框架高度
                if (window.top) {
                    if (window.top.main) {
                        if (typeof (window.top.main.autoSize) == "function") {
                            window.top.main.autoSize(false);
                        }
                    }
                }
x 1].xml ? arguments[1].xml : argum >XV>>X.x.XXxx>fcxents[1].text);
                doc = ECF.parseJSON(doc );
                var option = { type: type, labels: doc[0].labels.split(','), datasets: [] };
                $(doc[0].datasets).each(function (n, i) {
                    var dataset = {};
                    dataset.label = n.label;
                    dataset.data = n.data.split(",");
                    option.datasets.push(dataset);
                });
                $.report.createReport("#ReportBox", option, function () {
                    $("#sdate").html(arguments[0].label);
                    var fkflag = 1;
                    if (arguments[0].label == "商家") {
                        fkflag = 1;
                    } else {
                        fkflag = 2;
                    }
                    $("#table").show();
                    $("#pageBody").loadPage("bodyListTemplate", "PageBarList", "<SortField>a.AddTime</SortField><SortDirect>DESC</SortDirect><Filter>" + $('#filterForm').getValues("xml") + "</Filter><FKFlag>" + fkflag + "</FKFlag>", status, 10);
                });
            },
            error: function () {
                tip.hide();
                pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
            }
        });
    }
</script>
<script type="text/javascript">
    (window.loadData = function (type) {
        $("#type").val(type);
        GetData();
    })(1);
</script>

<!--调用模板JS-->
<script type="text/javascript" charset="utf-8">
    //列表切换
    $.getScript('/Static/Js/changeTableUI.js',function(){},true);
</script>
<!--//调用模板JS-->
