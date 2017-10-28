<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
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

</head>
<body>


	<!--内容-->
	<div class="content-area">
		<!--面包屑-->
		<div class="content-title">消防设备类型</div>
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
                                                                    <th><p class="name">供应商名称：</p></th>
                                                                    <td>
                                                                        <div class="inputbox">
                                                                            <input type="text" name="Name" value="" placeholder="供应商名称" />
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th><p class="name">所属行业：</p></th>
                                                                    <td>
                                                                        <div class="selectbox">
                                                                            <select name="Industry" id="Industry">
                                                                                <option selected="selected" value="">全部</option>
                                                                                <$loop id="EnumsList" datasourceid="EnumsList">
                                                                                    <option value="<$var enumslist.id />"><$var enumslist.name /></option>
                                                                                </$loop>
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
                                                <a href="javascript:void(0);" onclick="$e('#filterForm').filter();" class="btn">筛选</a>
                                                <a href="javascript:void(0);" onclick="frm.loadAllData();" class="btn">全部</a>
                                                <a href="javascript:void(0);" onclick="$e('#filterForm').formReset();" class="btn">清空</a>
                                            </div>
                                        </div>
                                    </div>

									<div class="button">
										<a href="javascript:void(0);"
											onclick="frm.add({ title: '添加消防设备类型', formId: 'DeviceTypeFormTemplate', callback: deviceType.addDeviceType, width: 700, height: 300,finish:deviceType.finishUploadBtn });"
											class="btn">查询设备</a>
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
									<col style="width: auto;" />
									<col style="width: auto;" />
									<col style="width: 100px;" />
									<col style="width: 100px;" />
								</colgroup>

								<thead>
									<tr>
										<td>
											<p class="name center">设备名</p>
										</td>
										<td>
											<p class="name center">使用时间</p>
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
<jsp:include page="../template/deviceType.html"></jsp:include>
<script language="javascript" type="text/javascript">
 $(function(){
	 load();
  }); 
 function load(){
	  var config={
				url:path+"/deviceType/show.do",
	  			pageSize:10,
	  			pageIndex:1,
	  			barSize:3,
	  			pageBarId:"PageBarList",
	  			templateId:"bodyListTemplate",
	  			container:"pageBody"
	  			};
	  var pageInfo=new ecPage.fn._init(config);
 }
</script>
