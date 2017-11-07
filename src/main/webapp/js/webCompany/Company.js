var companyManager={
			load:function(){
				$.ajax({
					url:path+"/admin/company/getCompanyByCompanyId.do",
					type:"post",
					dataType:"json",
					success:function(result){
						var html = "",
						step2 = $("#config_step2"),step1 = $("#config_step1");

						if(result.state==0){
							if(result.data==null){
								result.data=[];
							}
							else
							{
								html = $("#wechat_config_template").html();

								step1.find("*[dynamic]").html(html);
								
								$.each(result.data,function(key,value){
									$("#wechat_Config").find("#"+key.firstUpperCase()).val(value);
								});
								
								result.data=[result.data];
								
							}


						}else{
							result.data=[];
						}
						if(result.data.length==0){
							result.data.push({name:""});
						}
						var target=step2.find("*[dynamic]")[0];
						$("#wechat_info_template").tmpl(result.data).appendTo(target);
						companyManager.show();
						
					},
					error:function(){
						alert("加载失败!");
					}
				});
			},
			// 保存配置信息
			save: function () {
				var Id = $("#Id").val();
				var Name = $("#Name").val().trim();
				var Code = $("#Code").val().trim();
				var Address = $("#Address").val().trim();
				var Atten = $("#Atten").val();
				var Tel = $("#Tel").val().trim();
				var ManagerName = $("#ManagerName").val().trim();
				var Email = $("#Email").val().trim();
				var Mobile = $("#Mobile").val().trim();
				$.ajax({
					url:path+"/admin/company/update.do",
					type:"post",
					data:{Id:Id,Name:Name,Code:Code,
						Address:Address,Atten:Atten,Tel:Tel,
						ManagerName:ManagerName,Email:Email,Mobile:Mobile},
					dataType:"json",
					success:function(result){
						if(result.state==0){
							window.location.reload();
						}
					},
					error:function(){
						alert("保存失败!");
					}
				});
			},

			// 修改配置信息
			show: function (pid, cer) {
				var html = "",
				step1 = $("#config_step1"),
				step2 = $("#config_step2");

				cer = cer < 1 ? 1 : cer;

				if (pid < 1) {
					step2.hide();
					step1.show();
				}
				else {
					step1.hide();
					step2.show();
				}

			}
		}
//分配菜单js操作对象
var managerMenu = {
    htmlTemplate: '<div id="MenuList" class="model_box">\
                       {$Html$}\
                  </div>',
    htmlTemplate1: '<div class="model-box">\
                        <a onclick="check.openMenu(this,\'{$Id$}\');" href="javascript:void(0);" class="btn">\
                            <img src="{$ImgPath$}" nolazy="0" alt="" tips="open" />\
                        </a>\
                            <input type="checkbox" {$IsChecked$} onclick="check.checkAll(this,\'divChild_{$Id$}\',\'checkAll_{$Id$}\');" value="{$Id$}" id="checkAll_{$Id$}" checkall="{$Index_i$}" />\
                            <label>{$MenuName$}</label>\
                    </div>\
                    <div class="child-model-box" style="display: none;" id="divChild_{$Id$}">\
                        {$Html1$}\
                    </div>',
    htmlTemplate2: '<div class="model-box">\
                        <input type="checkbox" {$IsChecked$} onclick="check.checkAll(this,\'divChild_{$Id$}\',\'checkAll_{$Index_i$}\', \'divChild_{$Index_i$}\');" value="{$Id$}" id="checkAll_{$Id$}" checklist="{$Index_i$}" />\
                        <label>{$MenuName$}</label>\
                    </div>\
                    <div class="child_box" id="divChild_{$Id$}">\
                        <div>\
                            {$Html2$}\
                        </div>\
                    </div>',
    htmlTemplate3: '<div class="model-box">\
                        <input type="checkbox" {$IsChecked$} onclick="check.checkChildBox(\'checkAll_{$Index_j$}\',\'divChild_{$Index_j$}\',\'checkAll_{$Index_i$}\', \'divChild_{$Index_i$}\');" value="{$Id$}" id="checkMenu_{$Id$}"/>\
                   </div>',

	//显示该用户权限，并可设置权限
	showCheckBoxToMenu: function (id, managerId) {
		var htmlContent = "";
		var htmlContent1 = "";
		$.ajax({
		    url: "Suppliers.axd",
			data: {ManagerId:managerId},
			loading: function () {
				tip.show("数据处理中 ...");
			},
			success: function () {
				tip.hide();
				var json = $.parseJSON(arguments[1].text);
				var relate = "," + json[0].jsonRelation + ",";
				json = json[0].jsonMenu;
				$.each(json, function (i) {
				    htmlContent1 += managerMenu.htmlTemplate1
                        .replace(/\{\$Index_i\$\}/g, json[i].Id)
                        .replace(/\{\$Id\$\}/g, json[i].Id)
                        .replace(/\{\$MenuName\$\}/g, json[i].MenuName)
                        .replace(/\{\$ImgPath\$\}/g, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAJtJREFUeNpivHDhwrNnzxhwAykpKYZt27b9xwuACliAaoEsBrwApOjfv39wfsQqdiC5IuwnuiJMk9BEoCZ9+/YtdbsIXDRyNQeQXBTwmZmZGaro79+/yDbCwZ8/f1BMApo/2/MNkA0xD8IGamZiYkIoQjMJKA1hIKyDqICQ01yeI/sXwgApYmFhwRVUQCkQCQz1Q4cO4Y8WgAADAHdzc/QUjmKTAAAAAElFTkSuQmCC")
                        .replace(/\{\$IsChecked\$\}/g, (relate.indexOf("," + json[i].Id + ",") > -1 ? "checked=\"checked\"" : ""));
					var children1 = json[i].children;
					var htmlContent2 = "";
					$.each(children1, function (j) {
					    htmlContent2 += managerMenu.htmlTemplate2
                            .replace(/\{\$Index_i\$\}/g, json[i].Id)
                            .replace(/\{\$Id\$\}/g, children1[j].Id)
                            .replace(/\{\$MenuName\$\}/g, children1[j].MenuName)
                            .replace(/\{\$IsChecked\$\}/g, (relate.indexOf("," + children1[j].Id + ",") > -1 ? "checked=\"checked\"" : ""));
						var children2 = children1[j].children;
						var htmlContent3 = "";
						$.each(children2, function (k) {
						    htmlContent3 += managerMenu.htmlTemplate3
                                .replace(/\{\$Index_i\$\}/g, json[i].Id)
                                .replace(/\{\$Index_j\$\}/g, children1[j].Id)
                                .replace(/\{\$Id\$\}/g, children2[k].Id)
                                .replace(/\{\$MenuName\$\}/g, children2[k].MenuName)
                                .replace(/\{\$IsChecked\$\}/g, (relate.indexOf("," + children2[k].Id + ",") > -1 ? "checked=\"checked\"" : ""));
						});
						htmlContent2 = htmlContent2.replace(/\{\$Html2\$\}/g, htmlContent3);
					});
					htmlContent1 = htmlContent1.replace(/\{\$Html1\$\}/g, htmlContent2);
				});
				htmlContent = storemenu.htmlTemplate.replace(/\{\$Html\$\}/g, htmlContent1);
				var div = document.createElement("div");
				div.innerHTML = htmlContent;

				pub.openDialog({ content: div, width: 500, height: 450, title: "分配菜单", buttons: [{ callback: managerMenu.addRelation, arguments: [id, addurl, "AddMenuRelate", "MenuList", "1"] }] });
			}
		});
	},
	addRelation: function (id, url, type, control, accountid) {
		var dl = this;
		var menus = check.getTreeValue(control);
		if (menus.length == 0 && accountid.length == 0) {
			alert("请选择菜单！");
			return;
		}
		$.ajax({
			url: url,
			dataType: "json",
			data: "{'action':'" + type + "','supplierid':'" + id + "','menus':'" + menus + "', 'type':'" + url + "','accountid':'" + accountid + "'}",
			success: function () {
				if (arguments[1].text > 0) {
					pub.tips("设置成功！", 1.5);
					dl.close();
				} else {
					pub.tips("设置失败！", 1.5);
				}
			}
		});
	},
	//展示用户权限
	showPower: function (supplierId, storeName) {
		var htmlContent = "<div class='power'>";
		$.ajax({
		    url: "Suppliers.axd",
			dataType: "xml",
			data: {ManagerId:managerId},
			success: function () {
				var json = $.parseJSON(arguments[1].text);
				if (json != null && json != undefined) {
					htmlContent += "<div class='title'>供应商&nbsp[<strong class='user_info'>" + storeName + "</strong>]</div>";
					json = json[0].jsonMenu;
					$.each(json, function (i) {
						htmlContent += "<div class='power_box'><div class='line main_01'>" + json[i].MenuName + "</div>";
						var children1 = json[i].children;
						$.each(children1, function (j) {
							htmlContent += "<div class='line main_02'><div class='show_bg'></div><div class='show_info'>" + children1[j].MenuName + "</div></div>";
							var children2 = children1[j].children;
							$.each(children2, function (k) {
								htmlContent += "<div class='line main_03'><div class='show_bg'></div><div class='show_info'>" + children2[k].MenuName + "</div></div>";
							});
						});
						htmlContent += "</div>";
					});
					htmlContent += "</div>";
					var div = document.createElement("div");
					div.innerHTML = htmlContent;
					pub.html({ content: div, width: 1050, height: 450, title: "查看供应商-【" + storeName + "】操作权限", buttons: [] });
				} else {
					pub.tips("此供应商没有任何菜单权限！ ", 3);
				}
			},
			error: function () {
				tip.hide();
				pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
			}
		});
	}
};