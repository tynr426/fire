var manager={
		addManager:function(obj){
			if(!$("#ManagerForm").formValidate())return;
			
			var UserName = $("#UserName").val().trim();
			var Password = $("#Password").val().trim();
			var Name = $("#Name").val().trim();
			var Email = $("#Email").val();
			var Mobile = $("#Mobile").val();
			var Position = $("#Position").val();
			$.ajax({
				url:companypath+"/manager/add.do",
				type:"post",
				data:{UserName:UserName,Password:Password,Name:Name,Email:Email,Mobile:Mobile,Position:Position},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("添加成功");
						$(obj).dialog('close');
						load();
					}else{	
						alert(result.message);		
					}								

				}
			});
		},
		getManager:function(Id){
			$.ajax({
				url:companypath+"/manager/getManager.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(result){					
					if(data.state==0){
						user.openDialog(result.data);
					}
				}
			});
		},
		updateFinish:function(){
			var json=arguments[0];
			$("#UserName").parent().html(json.userName);
			$("#Password").val("");
		},
		updateManager:function(obj){
			if(!$("#ManagerForm").formValidate())return;
			var id = $("#ManagerForm").find("#Id").val();
			var Password = $("#Password").val().trim();
			var Name = $("#Name").val().trim();
			var Email = $("#Email").val().trim();
			var Mobile = $("#Mobile").val().trim();
			var Position = $("#Position").val().trim();
			$.ajax({
				url:companypath+"/manager/update.do",
				type:"post",
				data:{Id:id,Password:Password,Name:Name,Email:Email,Mobile:Mobile,Position:Position},
					dataType:"json",
					success:function(result){
						if(result.state==0){
							alert("您已修改成功");
							load();
							$(obj).dialog('close');
						}else{	
							alert(result.message);			
						}								

					}
			});
		},
		updateIndexManager:function(obj){
			if(!$("#ManagerForm").formValidate())return;
			var id = $("#AccountForm").find("#Id").val();
			var Name = $("#Name").val().trim();
			var Email = $("#Email").val().trim();
			var Mobile = $("#Mobile").val().trim();
			$.ajax({
				url:companypath+"/manager/update.do",
				type:"post",
				data:{Id:id,Name:Name,Email:Email,Mobile:Mobile},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("修改成功,下次登录生效");
						$(obj).dialog('close');
					}else{	
						alert(result.message);			
					}								
					
				}
			});
		},
		updatePassword:function(obj){
			var oldPwd = $("#OldPwd").val();
			var newPwd = $("#NewPwd").val();
			var pwd = $("#PassWord").val();
			if(oldPwd==newPwd){
				alert("密码不能与原密码相同");
				return;
			}
			if(newPwd!=pwd){
				alert("两次密码输入不一致");
				return;
			}
			$.ajax({
				url:companypath+"/manager/updatePwd.do",
				type:"post",
				data:{oldPwd:oldPwd,pwd:pwd},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("修改成功,下次登录生效");
						$(obj).dialog('close');
					}else{	
						alert(result.message);			
					}								
					
				}
			});
		},
		getManagerList:function(fn){
			$.ajax({
				url:companypath+"/manager/getManagerList.do",
				type:"post",
				async:false,
				dataType:"json",
				success:function(result){
					if(result.state==0){
						if(typeof(fn)=="function") fn(result.data);
					}else{	
						alert(result.message);			
					}								
					
				}
			});
		}

};
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
    					<label>{$MenuName$}</label>\
                   </div>',

	//显示该用户权限，并可设置权限
	showCheckBoxToMenu: function (companyId, managerId) {
		var htmlContent = "";
		var htmlContent1 = "";
		$.ajax({
		    url: companypath+"/menu/getMenuList.do",
			loading: function () {
				tip.show("数据处理中 ...");
			},
			success: function (result) {
				tip.hide();
				var json = result.data;
				
				var relate = ","+managerMenu.getMenuIds(companyId, managerId)+",";
				$.each(json, function (i,item) {
				    htmlContent1 += managerMenu.htmlTemplate1
                        .replace(/\{\$Index_i\$\}/g, item.id)
                        .replace(/\{\$Id\$\}/g, item.id)
                        .replace(/\{\$MenuName\$\}/g, item.menuName)
                        .replace(/\{\$ImgPath\$\}/g, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAJtJREFUeNpivHDhwrNnzxhwAykpKYZt27b9xwuACliAaoEsBrwApOjfv39wfsQqdiC5IuwnuiJMk9BEoCZ9+/YtdbsIXDRyNQeQXBTwmZmZGaro79+/yDbCwZ8/f1BMApo/2/MNkA0xD8IGamZiYkIoQjMJKA1hIKyDqICQ01yeI/sXwgApYmFhwRVUQCkQCQz1Q4cO4Y8WgAADAHdzc/QUjmKTAAAAAElFTkSuQmCC")
                        .replace(/\{\$IsChecked\$\}/g, (relate.indexOf("," + item.id + ",") > -1 ? "checked=\"checked\"" : ""));
					var children1 = item.list;
					var htmlContent2 = "";
					$.each(children1, function (j,jitem) {
					    htmlContent2 += managerMenu.htmlTemplate2
                            .replace(/\{\$Index_i\$\}/g, item.id)
                            .replace(/\{\$Id\$\}/g, jitem.id)
                            .replace(/\{\$MenuName\$\}/g, jitem.menuName)
                            .replace(/\{\$IsChecked\$\}/g, (relate.indexOf("," + jitem.id + ",") > -1 ? "checked=\"checked\"" : ""));
						var children2 = jitem.list;
						var htmlContent3 = "";
						$.each(children2, function (k,kitem) {
						    htmlContent3 += managerMenu.htmlTemplate3
                                .replace(/\{\$Index_i\$\}/g, item.id)
                                .replace(/\{\$Index_j\$\}/g, jitem.id)
                                .replace(/\{\$Id\$\}/g, kitem.id)
                                .replace(/\{\$MenuName\$\}/g, kitem.menuName)
                                .replace(/\{\$IsChecked\$\}/g, (relate.indexOf("," + kitem.id + ",") > -1 ? "checked=\"checked\"" : ""));
						});
						htmlContent2 = htmlContent2.replace(/\{\$Html2\$\}/g, htmlContent3);
					});
					htmlContent1 = htmlContent1.replace(/\{\$Html1\$\}/g, htmlContent2);
				});
				htmlContent = managerMenu.htmlTemplate.replace(/\{\$Html\$\}/g, htmlContent1);
				var div = document.createElement("div");
				div.innerHTML = htmlContent;

				pub.openDialog({ content: div, width: 500, height: 450, title: "分配菜单", callback: managerMenu.addRelation, arguments: [companyId,managerId, "MenuList"] } );
			}
		});
	},
	getMenuRelationList:function(companyId, managerId){
		var menuList=[];
		$.ajax({
			 url: companypath+"/menu/getMenuRelationList.do",
			 dataType: "json",
			 async:false,
			 data: {companyId:companyId,managerId:managerId},
			 success:function(result){
				 if(result.state==0){
					 menuList=result.data;			
				 }
				 
			 }
		});
		return menuList;
	},
	getMenuIds:function(companyId, managerId){
		var menuIds="";
		$.ajax({
			 url: companypath+"/menu/getMenuRelation.do",
			 dataType: "json",
			 async:false,
			 data: {companyId:companyId,managerId:managerId},
			 success:function(result){
				 if(result.state==0&&result.data!=null){
					 menuIds=result.data.menuIds;			
				 }
				 
			 }
		});
		return menuIds;
	},
	addRelation: function (companyId, managerId,control) {
		var dl = this;
		var menus = check.getTreeValue(control);
		if (menus.length == 0) {
			alert("请选择菜单！");
			return;
		}
		$.ajax({
			url: companypath+"/menu/save.do",
			dataType: "json",
			data: {companyId:companyId,managerId:managerId,menuIds:menus},
			success: function (result) {
				if (result.state==0) {
					pub.tips("设置成功！", 1.5);
					$(dl).dialog('close');
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
		    url: companypath+"/menu/getMenuRelationList.do",
			dataType: "xml",
			data: {managerId:managerId},
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
	
