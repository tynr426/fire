﻿<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备类型总表</title>
<jsp:include page="../Meta.html"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="/fire/Static/Upload/swfupload.css"/>
<script type="text/javascript" src="/fire/Static/Upload/swfupload.js"></script>
<script type="text/javascript"
	src="/fire/Static/Upload/swfUploadHandler.js"></script>
<script type="text/javascript"
	src="/fire/js/webAdmin/deviceQR.js"></script>
</head>
<body>


	<!--内容-->
	<div class="content-area">
		<!--面包屑-->
		<div class="content-title">二维码列表</div>
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
  									<div class="button expert-close">
                                        <a href="javascript:void(0);" onclick="expert(this);" class="btn">高级查询</a>

                                        <div class="expert-list">
                                            <div class="list">
                                                <div class="custom-table">
                                                    <div class="form">
                                                        <table border="0" cellspacing="0" cellpadding="0">
                                                            <colgroup>
                                                                <col style="width: 100px;" />
                                                                <col style="width: auto;" />
                                                            </colgroup>

                                                            <tbody id="filterForm">
                                                                <tr>
                                                                    <th><p class="name">型号：</p></th>
                                                                    <td>
                                                                        <div class="inputbox">
                                                                            <input type="text" id="Model" name="Model" value="" placeholder="设备型号" />
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th><p class="name">设备类型：</p></th>
                                                                    <td>
                                                                        <div class="selectbox">
                                                                            <select name="DeviceTypeId" id="DeviceTypeId" >
                                                                                <option selected="selected" value="">选择设备</option>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="button">
                                                <a href="javascript:void(0);" onclick="deviceQR.search();" class="btn">筛选</a>
                                                <a href="javascript:void(0);" onclick="deviceQR.reset();" class="btn">清空</a>
                                            </div>
                                        </div>
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
									<col style="width: 280px;" />
									<col style="width: auto;" />
									<col style="width: 150px;" />
									<col style="width: auto;" />
									<col style="width: 65px;" />
								</colgroup>

								<thead>
									<tr>
										<td>
											<p class="name center">二维码编号</p>
										</td>
										<td>
											<p class="name center">设备名</p>
										</td>
										<td>
											<p class="name center">使用时间</p>
										</td>
										<td>
											<p class="name center">型号</p>
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
	<div id="dialogopen" style="display: none">
		<div class="label-box mc for">
			<div class=" extend-button">


				<div class="button">
					<a href="javascript:void(0);"
						onclick="frm.add({ title: '添加设备参数', formId: 'DeviceParameterFormTemplate', callback: deviceTypeParameter.addDeviceTypeParameter, width: 800, height: 400 });"
						class="btn">添加设备参数</a>
				</div>


			</div>
		</div>
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
						<col style="width: 100px;" />
						<col style="width: 100px;" />
					</colgroup>

					<thead>
						<tr>
							<td>
								<p class="name center">字段描述</p>
							</td>
							<td>
								<p class="name center">编辑类型</p>
							</td>
							<td>
								<p class="name center">设备参数单位(m,mm...)</p>
							</td>
							<td>
								<p class="name center">排序</p>
							</td>
							<td>
								<p class="name center">状态</p>
							</td>
							<td>
								<p class="name">操作</p>
							</td>
						</tr>
					</thead>

					<tbody id="parameterBody"></tbody>
				</table>
			</div>
			<!--//表格内盒-->
		</div>
		<!--//表格组件-->
	</div>
</body>
</html>
<jsp:include page="../template/deviceQR.html"></jsp:include>
<script language="javascript" type="text/javascript">
 $(function(){
	 deviceQR.search();
	 deviceQR.loadDeviceType();
  });
</script>
