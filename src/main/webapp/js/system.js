
/**
 * aus system js file
 * author: shaipe
 */

"use strict";

var app = {
    /*添加菜单*/
    addCompleted: function (parentId) {
        console.log(parentId);
        top.$("#ApplicationInsertForm").setValues({
            PrimaryId: parentId,
            PrimaryApp: 0
        });
    },
    appJson:null,
    loadApplication:function(){
		$.ajax({
			url:path+"/app/findApplication.do",
    		type:"post",
			dataType:"json",
			success:function(result){
				if(result.state==0){
					app.appJson=result.data;
				}							
			},
			error:function(){
				alert("加载失败");
			}
		});
	},
    save: function () {

    },
    insert:function(obj){
    	var Name = $("#Name").val(); 
    	var CodeMark = $("#CodeMark").val(); 
    	var System = $("#System").val(); 
    	var OSVersion = $("#OSVersion").val(); 
    	var Company = $("#Company").val();
    	var Language = $("#Language").val(); 
		var AloneMenu = $("#AloneMenu").val(); 
		var SchemaMode = $("#SchemaMode").val(); 
    	$.ajax({
    		url:path+"/app/add.do",
    		type:"post",
			data:{Name:Name,CodeMark:CodeMark,System:System,OSVersion:OSVersion,Company:Company,Language:Language,AloneMenu:AloneMenu,SchemaMode:SchemaMode},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					alert("您已添加成功");
					$(obj).dialog('close');
					load();
				}else{	
					alert(result.message);		
				}								

			},
			error:function(){
				alert("添加失败");
			}
    	});
    },
    updateConfig:function(obj){
    	var id = $("#FormTemplate").find("#Id").val();
    	var moduleId=$("#FormTemplate").find("#ModuleId").val();
    	var description = $("#Description").val(); 
    	var keyWord = $("#KeyWord").val(); 
    	var fkway = $("#FKWay").val(); 
    	var editorType = $("#EditorType").val(); 
    	var dataType = $("#DataType").val(); 
    	var isShow = $("#IsShow").val(); 
    	var required = $("#Required").val(); 
    	var flag = $("#Flag").val(); 
    	var reorder = $("#Reorder").val(); 
    	var keyValue = $("#KeyValue").val(); 
    	var example = $("#Example").val(); 
    	var dataValidate = $("#DataValidate").val(); 
    	var errorTip = $("#ErrorTip").val(); 
    	var groupName=$("#FormTemplate").find("#GroupName").val();
    	var candidate="";
    	var array=[];
    	$("#ul_body tr").each(function(i,item){
    		array.push("{\"Field\":\""+$(this).find("#Field").val()+"\",\"Value\":\""+$(this).find("#Value").val()+"\"}");
    	});  	
    	candidate="["+array.join(",")+"]";	
    	console.log(candidate);
    	$.ajax({
    		url:path+"/conf/updateConfig.do",
    		type:"post",
			data:{Id:id,Description:description,
				ModuleId:moduleId,KeyWord:keyWord,
				FKWay:fkway,EditorType:editorType,
				DataType:dataType,IsShow:isShow,
				Required:required,Flag:flag,
				Reorder:reorder,KeyValue:keyValue,
				Example:example,DataValidate:dataValidate,
				ErrorTip:errorTip,GroupName:groupName,
				Candidate:candidate},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					alert("您已修改成功");
					$(obj).dialog('close');
					config.filter();
				}else{	
					alert(result.message);			
				}								

			},
			error:function(){
				alert("修改失败");
			}
    	});
    },
    update:function(obj){
    	var Id = $("#Id").val();
    	var Name = $("#Name").val(); 
    	var CodeMark = $("#CodeMark").val(); 
    	var System = $("#System").val(); 
    	var OSVersion = $("#OSVersion").val(); 
    	var Company = $("#Company").val();
    	var Language = $("#Language").val(); 
		var AloneMenu = $("#AloneMenu").val(); 
		var SchemaMode = $("#SchemaMode").val();
    	$.ajax({
    		url:path+"/app/update.do",
    		type:"post",
			data:{Id:Id,Name:Name,CodeMark:CodeMark,System:System,OSVersion:OSVersion,Company:Company,Language:Language,AloneMenu:AloneMenu,SchemaMode:SchemaMode},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					alert("您已修改成功");
					$(obj).dialog('close');
					load();
				}else{	
					alert(result.message);		
				}								

			},
			error:function(){
				alert("添加失败");
			}
    	});
    }
}

var sys = {
    sss: function () {
    }
};

/*菜单*/
var menu = {
    roleId: 0,
    loadRole:function(){
    	$.ajax({
    		url:path+"/role/list.do",
    		dataType:"json",
    		async:false,
    		success:function(result){
    			if(result.state==0){
    				$("#RoleTemplate").tmpl(result.data).appendTo("#GroupList");
    			}
    		},
    		error:function(){
    			alert("加载失败");
    		}
    	});
    },
    loadMenu:function(roleId,obj){
    	menu.roleId = roleId;
    	$("#GroupList li").each(function(i,item){
    		$(this).removeClass("select");
    	});
    	$(obj).addClass("select");
    	$.ajax({
    		url:path+"/menu/list.do",
    		data:{roleId:roleId},
    		success:function(result){
    			$("#MenuList").html("");
    			menu.menuArray=[];
    			menu.recurrence(result.data,0);
    			for(var i=0;i<menu.menuArray.length;i++){  				
    				$("#MenuList").append(menu.menuArray[i]);
    			}
    		},
    		error:function(){
    			alert("加载失败");
    		}
    	});
    },
    menuArray:[],
    recurrence:function(data,index){
    	$.each(data,function(i,item){
    		item.index=index+"_"+item.id;
    		item.parentIndex=index;
    		menu.menuArray.push($("#ListTemplate").tmpl(item));
			if(item.list.length>0){
			   menu.recurrence(item.list,index+"_"+item.id);	
			}
		});
    },
    addMenu:function(obj){
    	var fkway = $("#FKWay").val();
    	var menuName = $("#MenuName").val();
    	var cssClass = $("#CssClass").val();
    	var link = $("#Link").val();
    	var target = $("#Target").val();
    	var roleId =menu.roleId;
    	var parentId = $("#ParentId").val();
    	var layer = $("#Layer").val();
    	var reorder = $("#Reorder").val();
    	$.ajax({
    		url:path+"/menu/add.do",
    		type:"post",
			data:{FKWay:fkway,MenuName:menuName,
				CssClass:cssClass,Link:link,Target:target,
				ParentId:parentId,RoleId:roleId,Layer:layer,
				Reorder:reorder},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					alert("您已添加成功");
					$(obj).dialog('close');
					menu.loadMenu(roleId,$("#GroupList li[class='select']"));
				}else{	
					alert(result.message);		
				}								

			},
			error:function(){
				alert("添加失败");
			}
    	});
    },
    updateMenu:function(obj){
    	var id = $("#Id").val();
    	var fkway = $("#FKWay").val();
    	var menuName = $("#MenuName").val();
    	var cssClass = $("#CssClass").val();
    	var link = $("#Link").val();
    	var target = $("#Target").val();
    	var roleId =menu.roleId;
    	var parentId = $("#ParentId").val();
    	var layer = $("#Layer").val();
    	var reorder = $("#Reorder").val();
    	$.ajax({
    		url:path+"/menu/updateMenu.do",
    		type:"post",
			data:{Id:id,FKWay:fkway,MenuName:menuName,
				CssClass:cssClass,Link:link,Target:target,
				ParentId:parentId,RoleId:roleId,Layer:layer,
				Reorder:reorder},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					alert("您已修改成功");
					$(obj).dialog('close');
					menu.loadMenu(roleId,$("#GroupList li[class='select']"));
				}else{	
					alert(result.message);		
				}								

			},
			error:function(){
				alert("修改失败");
			}
    	});
    },
    updateRealMenuIds:function(){
    	$.ajax({
    		url:path+"/menu/batch.do",
    		type:"post",
			data:{roleId:menu.roleId},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					alert("您已同步成功");
					menu.loadMenu(menu.roleId,$("#GroupList li[class='select']"));
				}else{	
					alert(result.message);		
				}								

			},
			error:function(){
				alert("同步失败");
			}
    	})
    },
    move:function(obj){
    	var realMenuId = $("#RealMenuId").val();
    	var roleId = $("#RoleId").val();
    	var parentId = $("#ParentId").val();
    	var containSelf = $("#ContainSelf").val();
    	$.ajax({
    		url:path+"/menu/move.do",
            dataType: "json",
            async: false,
            data: {realMenuId:realMenuId,roleId:roleId,parentId: parentId,containSelf:containSelf},
            success: function (result) {
               if(result.state==0){
            	   alert("移动成功");
            	   $(obj).dialog('close'); 
            	   menu.loadMenu(menu.roleId,$("#GroupList li[class='select']")); 
               }
            },
            error: function () {
                alert("移动失败");
            } 		
    	});
    },
    addFinish:function(parentName,parentId,layer){
		if(app.appJson!=null){
			var str = "";
			$.each(app.appJson,function(key,item){
				str+="<option value='"+item.id+"'>"+item.name+"</option>";
			});
			$("#AppId").append(str);		
		}
		if(parentName!=undefined){
			$("#ParentName").html(parentName);
		}
		if(parentId!=undefined){
			$("#ParentId").val(parentId);
		}
		if(layer!=undefined){
			$("#Layer").val((parseInt(layer)+1));		
		}
		
	},

    /*移动*/
    moveCompleted: function (id, realMenuId, layer, MenuName, MoveObject, parentId) {
    	$("#MoveForm").find("#Id").val(id);
    	$("#MoveForm").find("#MenuName").val(MenuName);
    	$("#MoveForm").find("#RealMenuId").val(realMenuId);
    	$("#MoveForm").find("#RoleId").val(menu.roleId);
    	$("#MoveForm").find("#ParentId").val(parentId);
    	$("#MoveForm").find("#ContainSelf").val(MoveObject);
    	$.ajax({
    		url:path+"/menu/getParentName.do",
            dataType: "json",
            async: false,
            data: {layer:layer,roleId:menu.roleId,parentId:parentId},
            success: function (result) {
                if(result.state==0){
                if (MoveObject == 1) {
                    $("#ParentId").append("<option value='0'>置顶</option>");
                }
                $.each(result.data, function (i, item) {
                    $("#ParentId").append("<option value=" + item.realMenuId + ">" + item.menuName + "</option>")
                    if(item.list!=null){
                    	$.each(item.list, function (j, jitem) {
                            $("#ParentId").append("<option value=" + jitem.realMenuId + ">---|" + jitem.menuName + "</option>")
                        })
                    }
               
                });
               }
            },
            error: function () {
                alert("获取失败");
            }
        });
    },


}

/*配置*/
var config = {	
		moduleJson:null,
		loadModule:function(){
			$.ajax({
				url:path+"/module/findModule.do",
	    		type:"post",
				dataType:"json",
				success:function(result){
					if(result.state==0){
						config.moduleJson=result.data;
						var str = "";
						$.each(result.data,function(key,item){
							str+="<option value='"+item.id+"'>"+item.name+"</option>";
						});
						$("#ModuleId").append(str);
					}							
				},
				error:function(){
					alert("加载失败");
				}
			});
		},
		addConfig:function(obj){
			var moduleId=$("#FormTemplate").find("#ModuleId").val();
	    	var description = $("#Description").val(); 
	    	var keyWord = $("#KeyWord").val(); 
	    	var fkway = $("#FKWay").val(); 
	    	var editorType = $("#EditorType").val(); 
	    	var dataType = $("#DataType").val(); 
	    	var isShow = $("#IsShow").val(); 
	    	var required = $("#Required").val(); 
	    	var flag = $("#Flag").val(); 
	    	var reorder = $("#Reorder").val(); 
	    	var keyValue = $("#KeyValue").val(); 
	    	var example = $("#Example").val(); 
	    	var dataValidate = $("#DataValidate").val(); 
	    	var errorTip = $("#ErrorTip").val(); 
	    	var groupName=$("#FormTemplate").find("#GroupName").val();
	    	var candidate="";
	    	var array=[];
	    	$("#ul_body tr").each(function(i,item){
	    		array.push("{\"Field\":\""+$(this).find("#Field").val()+"\",\"Value\":\""+$(this).find("#Value").val()+"\"}");
	    	});
	    	
	    	candidate="["+array.join(",")+"]";
	    	
	    	console.log(candidate);
			$.ajax({
	    		url:path+"/conf/add.do",
	    		type:"post",
				data:{Description:description,
					ModuleId:moduleId,KeyWord:keyWord,
					FKWay:fkway,EditorType:editorType,
					DataType:dataType,IsShow:isShow,
					Required:required,Flag:flag,
					Reorder:reorder,KeyValue:keyValue,
					Example:example,DataValidate:dataValidate,
					ErrorTip:errorTip,GroupName:groupName,
					Candidate:candidate},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("您已添加成功");
						$(obj).dialog("close");
						config.filter();
					}else{	
						alert(result.message);		
					}								

				},
				error:function(){
					alert("添加失败");
				}
	    	});
		},
		addFinish:function(doc){
			if(app.appJson!=null){
				var str = "";
				$.each(app.appJson,function(key,item){
					str+="<option value='"+item.id+"'>"+item.name+"</option>";
				});
				$("#AppId").append(str);
				
			}
			if(config.moduleJson!=null){			
				str = "";
				$.each(config.moduleJson,function(key,item){
					str+="<option value='"+item.id+"'>"+item.name+"</option>";
				});
				$("#FormTemplate").find("#ModuleId").append(str);
			}
			if(dict.validateRegExp!=undefined){
				str = "";
				$.each(dict.validateRegExp,function(key,value){
					str+="<option value='"+key+"'>"+value+"</option>";
				});
				$("#DataValidate").append(str);
			}
			if(doc==undefined){
				return;
			}
	        var f = eval(doc.candidate);
	        switch (doc.editorType) {
	            case "select":
	            case "radio":
	            case "checkbox":
	                $("#tr_Candidate").show();
	                $("#rowTemplate").tmpl(f).appendTo("#ul_body");
	                
	                break;
	        }
		},	
	parseDesc:function(data){
		if(data==1){
			return "仅零售";
		}else
		if(data==2){
			return "仅批发";
		}else
		if(data==3){
			return "供应链";
		}
	},
	filter:function(){
		var keyWord = $("#Keyword").val();
		var groupName = $("#GroupName").val();
		var moduleId = $("#ModuleId").val();
		var data = {keyWord:keyWord,groupName:groupName,moduleId:moduleId};
		  var config={
					url:path+"/conf/showConfigPage.do",
		  			pageSize:9,
		  			pageIndex:1,
		  			barSize:3,
		  			pageBarId:"PageBarList",
		  			templateId:"bodyListTemplate",
		  			container:"pageBody",
		  			data:data
		  			};
		  var pageInfo=new ecPage.fn._init(config);
		
	},
    editorTypeChange: function (obj) {
        switch (obj.value) {
            case "select":
            case "texts":
                config.addRule();
                $("#tr_Candidate").show();
                break;
            default:
                $("#tr_Candidate").hide();
                $("#ul_body").html("");
                break;
        }
    },
    reset:function(){
    	$("#Keyword").val("");
		$("#GroupName").val("");
		var moduleId = $("#ModuleId").get(0).selectedIndex=0;
    },
    //行模版
    rowTemplate: '',

    //添加一条规则
    addRule: function () {
        $("#ul_body").append(pub.iwin().$("#rowTemplate").html());

    },

    //删除规则
    delRule: function (node) {
        config.delNode(node);
    },

    //删除对象
    delNode: function (node) {
        node = config.getTr(node);
        $(node).remove();
    },

    //获得tr对象
    getTr: function (node) {
        while (node.parentNode) {
            if (node.tagName.toLowerCase() == "tr") {
                break;
            }
            else {
                node = node.parentNode;
            }
        }
        return node;
    },

    updateLoadCompleted: function () {
        var data = arguments[0];
        var doc = $.parseJSON(data);
        var f = doc[0].Candidate;
        switch (doc[0].EditorType) {
            case "select":
            case "radio":
            case "checkbox":
                $("#tr_Candidate").show();
                var html = pub.iwin().$("#rowTemplate").html();
                $("#ul_body").append(jte(html, f));
                break;
        }
        config.loadCompleted();
        $("#DataValidate").val(doc[0].DataValidate);
    },

    loadCompleted: function () {

        $.each(dict.validateRegExp, function (i, item) {
            $("#DataValidate").append("<option value='" + i + "'>" + item + "</option>");
        });

    },
   
    /*获取数据*/
    getData: function () {
        var str = "";
        var arr = new Array();
        $("#ul_body tr").each(function (i) {
            arr.push($(this).getValues("JSON"));

        });
        return '[' + arr.join(',') + ']';
    },

    /*加载规则设置*/
    loadRebate: function () {

        ECF.ajax({
            dataType: "xml",
            async: false,
            data: "<action>loaddata</action>",
            loading: function () {
                tip.show("数据处理中 ...");
            },
            success: function () {
                tip.hide();
                var json = ECF.parseJSON(arguments[1].text);
                $("#ul_body").html(jte($("#rebateTemplate").html(), json));
            },
            error: function () {
                tip.hide();
                pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
            }
        });
        config.rowTemplate = $("#rebateRowTemplate").html();
    },
    //公用 隐藏显示文字和编辑框
    //wordId文字区域id
    //editId编辑框区域id
    //isEdit是否编辑状态 true=隐藏文字显示编辑框;false=显示文字隐藏编辑框
    //value 非编辑状态下，赋值给文字
    showSettleInput: function (wordId, editId, isEdit, value) {
        if (isEdit) {
            $("#" + wordId).hide();
            $("#" + editId).show();
            $("#" + editId).find("input").focus();
        } else {
            value = value || $("#" + wordId).html();
            $("#" + wordId).html(value);
            $("#" + wordId).show();
            $("#" + editId).hide();
        }
    }
}

var configScope = {
    openScope: function (configId, desc, keyword) {
        ECF.ajax({
            dataType: "xml",
            async: false,
            data: "<action>get.configscope</action><Id>" + configId + "</Id>",
            success: function () {
                tip.hide();

                var json = arguments[1].text;

                var html = ECF("#ConfigScopeFormTemplate").html();

                pub.html({
                    content: html, width: 600, height: 400, title: "修改密码",
                    buttons: [{ focus: true, name: "确认", callback: top.configScope.saveConfig, arguments: [configId] }],
                    callback: configScope.updateCompleted,
                    completes: [configId, desc, keyword, json]
                });

            },
            error: function () {
                tip.hide();
                pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
            }
        });


    },

    updateCompleted: function (configId, desc, keyword, data) {
        var json = ECF.parseJSON(data);
        var OpMode = [];
        var fkway = 3;
        var dic = new Array();

        $.each(json, function (i, item) {

            if ($.inArray(item.OpMode, OpMode) == -1) {
                OpMode.push(item.OpMode);
            }

            fkway = item.FkWay;
        });
        var doc = {
            ConfigId: configId,
            Description: desc,
            KeyWord: keyword,
            FKWay: fkway
        };



        if ($.inArray(0, OpMode) == -1) {
            json.push({ FKFlag: 0, OpMode: 0, Hide: 1 });
        }
        else {
            doc.OpMode1 = 0;
        }
        if ($.inArray(1, OpMode) == -1) {
            $.each(dict.fkflag, function (i, item) {
                json.push({ FKFlag: i, OpMode: 1, Hide: 1 })
            });
        } else {
            doc.OpMode2 = 1;
        }
        top.$("#ConfigScopeForm").setValues(doc);
        top.$("#ul_body").html(jte($("#rowConfigScopeTemplate").html(), json));
    },

    /*保存配置*/
    saveConfig: function (configId) {
        var dlg = this;
        var fs1 = $("#ConfigScopeForm");
        if (!fs1.formValidate()) return;

        var data = configScope.getData(configId);
        if (data.length == 0) {
            pub.tips("请选择！");
            return;
        }
        $.ajax({
            dataType: "xml",
            url: "/WebAdmin/System/Config.aspx",
            data: "<action>save.scope</action><Id>" + configId + "</Id><scopes>" + data + "</scopes>",

            success: function (data) {
                tip.hide();
                if (arguments[1].text > 0) {

                    pub.tips("保存成功！", 3, function () {
                        pub.iwin().loadData();
                    });
                    dlg.close();
                }
                else {
                    pub.error("保存失败,请重试", 3);
                }
            }
        });
    },
    /*获取数据*/
    getData: function (configId) {
        var str = "";
        var fkway = $("#FKWay").value();
        $("#ul_body tr").each(function () {
            var st = $(this).attr("style");
            if (st == undefined || st.indexOf("none") == -1)
                str += "<scope><FKWay>" + fkway + "</FKWay><ConfigId>" + configId + "</ConfigId>" + $(this).getValues("xml") + "</scope>";

        });
        return str;
    },
    opModeChange: function (obj) {

        if (obj.value == "1") {
            if (obj.checked) {
                $(".Saas").show();
            }
            else {
                $(".Saas").hide();
            }
        }
        if (obj.value == "0") {
            if (obj.checked) {
                $(".Pass").show();
            }
            else {
                $(".Pass").hide();
            }
        }
    }
}

/*产品*/
var product = {

    /*open config*/
    openConfig: function (productId, appId, appName, fkway) {
        pub.open('/webadmin/System/ProductConfig.aspx?ProductId=' + productId + '&AppId=' + appId + "&FKWay=" + fkway, 900, 600, '设置【' + appName + '】基础配置', [
           {
               callback: productConfig.insert, name: '保存', focus: true, arguments: [productId, appId]
           },
             {
                 callback: productConfig.scanConfig, name: '预览', focus: true, arguments: [productId, appId,appName,fkway]
             }
        ]);
    },
    /*open config*/
    openMenu: function (productId, appId,appName) {
        pub.open('/webadmin/System/ProductMenu.aspx?ProductId=' + productId + '&AppId=' + appId, 900, 600, '设置【' + appName + '】菜单权限', [
            {
                callback: productMenu.addCustomerMenu, name: '保存', focus: true, arguments: [productId, appId]
            },
             {
                 callback: productMenu.scanMenu, name: '预览', focus: true, arguments: [productId, appId,appName]
             }
        ]);
    }
}

/*产品菜单*/
var productMenu = {

    /*当前选项卡的角色*/
    roleId: 0,

    /*显示该用户权限，并可设置权限*/
    showCheckBoxToMenu: function (productId, appid, role) {
        var htmlContent = "";
        var htmlContent1 = "";
        ECF.ajax({
            dataType: "xml",
            data: "<action>get.menu.outer.list</action><ProductId>" + productId + "</ProductId><AppId>" + appid + "</AppId><FKFlag>" + role + "</FKFlag>",
            loading: function () {
                tip.show("数据处理中 ...");
            },
            success: function () {
                tip.hide();
                var json = ECF.parseJSON(arguments[1].text);

                json = json[0].jsonMenu;
                ECF.each(json, function (i) {
                    htmlContent1 += $("#FirstLayerMenuTemplate").html()
                    .replace(/\{\$Id\$\}/g, json[i].Id)
                    .replace(/\{\$MenuName\$\}/g, json[i].MenuName)
                    .replace(/\{\$ImgPath\$\}/g, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAJtJREFUeNpivHDhwrNnzxhwAykpKYZt27b9xwuACliAaoEsBrwApOjfv39wfsQqdiC5IuwnuiJMk9BEoCZ9+/YtdbsIXDRyNQeQXBTwmZmZGaro79+/yDbCwZ8/f1BMApo/2/MNkA0xD8IGamZiYkIoQjMJKA1hIKyDqICQ01yeI/sXwgApYmFhwRVUQCkQCQz1Q4cO4Y8WgAADAHdzc/QUjmKTAAAAAElFTkSuQmCC")
                    .replace(/\{\$IsChecked\$\}/g, json[i].CheckStatus > 0 ? "Checked" : "");
                    var children1 = json[i].children;
                    var htmlContent2 = "";
                    ECF.each(children1, function (j) {
                        htmlContent2 += $("#SecondLayerMenuTemplate").html()
                        .replace(/\{\$Index_i\$\}/g, json[i].Id)
                        .replace(/\{\$Id\$\}/g, children1[j].Id)
                        .replace(/\{\$MenuName\$\}/g, children1[j].MenuName)
                        .replace(/\{\$IsChecked\$\}/g, (children1[j].CheckStatus > 0 ? "Checked" : ""));
                        var children2 = children1[j].children;
                        var htmlContent3 = "";
                        ECF.each(children2, function (k) {
                            htmlContent3 += $("#ThirdLayerMenuTemplate").html()
                            .replace(/\{\$Index_i\$\}/g, json[i].Id)
                            .replace(/\{\$Index_j\$\}/g, children1[j].Id)
                            .replace(/\{\$Id\$\}/g, children2[k].Id)
                            .replace(/\{\$MenuName\$\}/g, children2[k].MenuName)
                            .replace(/\{\$IsChecked\$\}/g, (children2[k].CheckStatus > 0 ? "Checked" : ""));
                        });
                        htmlContent2 = htmlContent2.replace(/\{\$Html2\$\}/g, htmlContent3);
                    });
                    htmlContent1 = htmlContent1.replace(/\{\$Html1\$\}/g, htmlContent2);
                });
                document.getElementById("MenuList").innerHTML = htmlContent1;

                if (typeof ($().customCheck) == 'function') {
                    $("#MenuList").customCheck();
                }
            },
            error: function () {
                tip.hide();
                pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
            }
        });
    },

    /*添加客户自定义菜单*/
    addCustomerMenu: function (productId, appid) {
        var win = this.iwindow || window;
 
        var checkboxs = this.iwindow.$("input[type=checkbox][name=menu]:checked");

        var xml = "";

        for (var i = 0; i < checkboxs.length; i++) {
            xml += "<ProductMenu>";
            xml += "<ProductId>" + productId + "</ProductId><AppId>" + appid + "</AppId><FKFlag>" + win.productMenu.roleId + "</FKFlag>";
            if ($(checkboxs[i]).parent("div").find("#IsUpdated").val() == "1") {
                xml += "<MenuName>" + $(checkboxs[i]).parent("div").find("#MenuName span").html() + "</MenuName>";
            }
            xml += "<MenuId>" + $(checkboxs[i]).val() + "</MenuId><Value>" + $(checkboxs[i]).parent("tr").find(".settle-price").html() + "</Value>";
            xml += "</ProductMenu>";
        }
        if (xml.length == 0) {
            pub.alert("请选择菜单！");
            return;
        }
        ECF.ajax({
            url: '/webadmin/System/ProductMenu.aspx',
            data: "<action>add.CustomerMenu</action><ProductId>" + productId + "</ProductId><AppId>" + appid + "</AppId><FKFlag>" + win.productMenu.roleId + "</FKFlag><Menus>" + xml + "</Menus>",
            success: function () {
                pub.tips("设置成功！", 1.5);
            }
        });
    },

    /*预览*/
    scanMenu: function (productId, appId, appName) {
        pub.open('/webadmin/System/ScanProductMenu.aspx?ProductId=' + productId + '&AppId=' + appId, 900, 600, '预览【' + appName + '】菜单权限', [
           
            {
                callback: productMenu.downSql, name: '下载', focus: true, arguments: [productId, appId]
            }
        ]);

    },

    /*downSql*/
    downSql: function (productId, appid) {
       
        var dl = this;
        var win = this.iwindow || window;
        win.tip.show("正在生成中 ...");
        ECF.ajax({
            url: '/webadmin/System/ProductMenu.aspx',
            data: "<action>create.down.menu</action><AppId>" + appid + "</AppId><ProductId>" + productId + "</ProductId>",
            success: function () {
                win.tip.hide();
                dl.close();
                var json = ECF.parseJSON(arguments[1].text);

                if (json.status == "0") {
                    pub.tips(json.result, 3);
                }
                else {
                    window.location.href = "../../" + json.result;
                }
            },
            error: function () {
                win.tip.hide();
                tip.hide();
                pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
            }
        });
    },

    /*打开菜单对话框*/
    openMenuDialog: function (id, menuName) {
        pub.dialog('修改菜单', $("#UpdateMenuNameFormTemplate").html(), 600, 500,
            [{
                callback: productMenu.updateMenu,
                arguments: [id]
            }],
            productMenu.updateMenuCompleted, [menuName]);
    },

    /*修改菜单*/
    updateMenu: function (id) {
        var name = top.$("#MenuName").val();
        var menuName = top.$("#ReMenuName").val();
        if (menuName.length == 0 || name == menuName) {
            this.close();
            return;
        }
        $("#" + id).find("#MenuName span").html(menuName);
        $("#" + id).find("#IsUpdated").val(1);
        this.close();
    },

    /*弹出框完成后调用*/
    updateMenuCompleted: function (name) {

        top.$("#UpdateMenuNameForm").setValues({
            MenuName: name,
            ReMenuName: name
        });
    }
}

/*产品配置*/
var productConfig = {

    /*insert*/
    insert: function (productId, appId) {
        var dl = this;
        var checkboxs = this.iwindow.$("input[type=checkbox][name=Id]:checked");

        var xml = "";

        for (var i = 0; i < checkboxs.length; i++) {

            xml += "<ProductConfig><ProductId>" + productId + "</ProductId><PrimaryAppId>" + appId + "</PrimaryAppId><ConfigId>" + $(checkboxs[i]).val() + "</ConfigId><Value>" + $(checkboxs[i]).parent("tr").find(".settle-price").html() + "</Value></ProductConfig>";
        }
        ECF.ajax({
            url: '/webadmin/System/ProductConfig.aspx',
            data: "<action>insert</action><ConfigIds>" + xml + "</ConfigIds><ProductId>" + productId + "</ProductId><AppId>" + appId + "</AppId>",
            success: function () {
                pub.tips("设置成功！", 1.5);
            }
        });
    },

    /*load app config*/
    loadConfig: function (productId, appId, fkway) {

        $("#pageBody").loadList("bodyListTemplate",
        "<ProductId>" + productId + "</ProductId><AppId>" + appId + "</AppId><FKWay>" + fkway + "</FKWay>",
        "loaddata", null, null, "/webadmin/System/ProductConfig.aspx");
    },

    /*load app config*/
    loadAppConfig: function (productId, appId,fkway) {

        $("#pageBody").loadList("bodyListTemplate",
        "<ProductId>" + productId + "</ProductId><AppId>" + appId + "</AppId><FKWay>"+fkway+"</FKWay>",
        "loaddata.app", null, function () {
            if (typeof $().customCheck === 'function') {

                $(document).customCheck();
            }
        }, "/webadmin/System/ProductConfig.aspx");
    },

    /*预览*/
    scanConfig: function (productId, appId,appName,fkway) {
        pub.open('/webadmin/System/ScanProductConfig.aspx?ProductId=' + productId + '&AppId=' + appId+"&FKWay="+fkway, 900, 600, '预览【' + appName + '】基础配置', [
            
            {
                callback: productConfig.downSql, name: '下载', focus: true, arguments: [appId, productId,fkway]
            }
        ]);

    },

    /*downSql语句*/
    downSql: function (appid, productId,fkway) {
        var dl = this;
        var win = this.iwindow || window;
        win.tip.show("正中同步中 ...");
        ECF.ajax({
            url: '/webadmin/System/ProductConfig.aspx',
            data: "<action>create.down.config</action><AppId>" + appid + "</AppId><ProductId>" + productId + "</ProductId><FKWay>" + fkway + "</FKWay>",
            success: function () {
                win.tip.hide();
                dl.close();
                var json = ECF.parseJSON(arguments[1].text);

                if (json.status == "0") {
                    pub.tips(json.result, 3);
                }
                else {
                    window.location.href = "../../" + json.result;
                }

            },
            error: function () {
                win.tip.hide();
                tip.hide();
                pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
            }
        });
    }
}