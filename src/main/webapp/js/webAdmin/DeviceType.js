var deviceType={
		addDeviceType:function(obj){
			if(!$("#DeviceTypeForm").formValidate())return;
			var Name = $("#Name").val().trim();
			var UseTime = $("#UseTime").val();
			var VirtualPath = $("#VirtualPath").val();
			$.ajax({
				url:"/fire/deviceType/addDeviceType.do",
				type:"post",
				data:{Name:Name,UseTime:UseTime,VirtualPath:VirtualPath},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("添加成功");
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
		getDeviceType:function(Id){
			$.ajax({
				url:path+"/deviceType/getDeviceType.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(result){					
					if(data.state==0){
						user.openDialog(result.data);
					}
				},
				error:function(){
					alert("获取失败");
				}
			});
		},
		updateDeviceType:function(obj){
			if(!$("#DeviceTypeForm").formValidate())return;
			var id = $("#DeviceTypeForm").find("#Id").val();
			var Name = $("#Name").val().trim();
			var UseTime = $("#UseTime").val().trim();
			var VirtualPath = $("#VirtualPath").val().trim();
			$.ajax({
				url:path+"/deviceType/update.do",
				type:"post",
				data:{Id:id,Name:Name,UseTime:UseTime,VirtualPath:VirtualPath},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("您已修改成功");
						load();
						$(obj).dialog('close');
					}else{	
						alert(result.message);			
					}								

				},
				error:function(){
					alert("修改失败");
				}
			});
		},
		openDialog:function(id){
			if($("#dialogForm").length==0){
				$("body").append("	<div id='dialogForm' style='display: none'></div>");
			}

			var opt={
					resizable: true,
					width: 450,
					height: 200,
					modal: true,
					title:"二维码"
			};

			// 添加按钮
			opt.buttons = {
					"确定":function(){
						deviceType.createQR(this,id);
					},
					"取消":function(){
						$(this).dialog('close');
					}
			};
			opt.Cancle =function(){
				$(this).dialog('close');
			};
			$("#dialogForm").html($("#CreateQRTemplate").html());

			$("#dialogForm").dialog(opt).dialog("open");
		},
		createQR:function(obj,id){
			var number=$("#QRNumber").val();
			$.ajax({
				url:path+"/deviceType/createQR.do",
				type:"post",
				data:{id:id,number:number},
				dataType:"json",
				success:function(result){
					if(result.state==0){
						alert("您已生成成功");
						$(obj).dialog('close');
						window.open(path+"/deviceQR/toQRList.do?batch="+result.data);

					}else{	
						alert(result.message);			
					}								

				},
				error:function(){
					alert("生成失败");
				}
			});
		},
		finishUploadBtn: function () {
			var json=arguments[0];
			if(json!=undefined&&json.virtualPath!=undefined){
				$("[name=VirtualPath]").attr("src", path+json.virtualPath);
			}
			console.log(arguments[0]);
			var upObject = uploads('uploadDemo', {
				id: 'uploadDemo',
				preview_hide: true,
				upload_url: path+"/upload.do",
				upload_success_handler: deviceType.callBackUpdateFilePath,
				file_size_limit: 12000,
				post_params: {
					module: "Web"
				},
				call_back: function () {
				}
			})
		},
		callBackUpdateFilePath: function (file, serverData, responseReceived) {
			debugger;
			console.log(serverData);
			if (file.filestatus == -4) {
				var json= $.parseJSON(serverData);
				$("#VirtualPath").val(json.data);

				$("[name=VirtualPath]").attr("src", path+json.data);
			}
			file = this.unescapeFilePostParams(file);
		}
}

var deviceTypeParameter={
		deviceTypeId:0,
		editorTypeDesc:function(key){
			console.log(key)
		},
		openParameterDialog:function(deviceTypeId){
			if(deviceTypeId!=undefined){
				deviceTypeParameter.deviceTypeId=deviceTypeId;
			}
			var opt={
					resizable: true,
					width: 950,
					height: 450,
					modal: true,
					title:"设备参数"
			};
			var config={
					url:adminpath+"/dtp/show.do",
					pageSize:30,
					pageIndex:1,
					barSize:3,
					templateId:"DeviceParameterListTemplate",
					container:"parameterBody",
					data:{deviceTypeId:deviceTypeParameter.deviceTypeId}
			};
			var pageInfo=new ecPage.fn._init(config);

			$("#dialogopen").dialog(opt).dialog("open");
		},
		editorTypeChange: function (obj) {
			switch (obj.value) {
			case "select":
			case "checkbox":
			case "texts":
				$("#ul_body").html("");
				deviceTypeParameter.addRule();
				$("#tr_Candidate").show();
				break;
			default:
				$("#tr_Candidate").hide();
			$("#ul_body").html("");
			break;
			}
		},

		//添加一条规则
		addRule: function () {
			var arr=[
			         {
			        	 Field:"",
			        	 Value:"",
			        	 EditorType:$("#EditorType").val()
			         }
			         ];
			$("#rowTemplate").tmpl(arr).appendTo("#ul_body");

		},

		//删除规则
		delRule: function (node) {
			deviceTypeParameter.delNode(node);
		},

		//删除对象
		delNode: function (node) {
			node = deviceTypeParameter.getTr(node);
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

		addDeviceTypeParameter:function(obj){
			if(!$("#DeviceParameterForm").formValidate())return;
			var Description = $("#Description").val().trim();
			var EditorType = $("#EditorType").val().trim();
			var Required = $("#Required").val();
			var Unit = $("#Unit").val().trim();
			var Reorder = $("#Reorder").val().trim();
			var candidate="";
			var array=[];
			if(EditorType!="texts"){
				$("#ul_body tr").each(function(i,item){
					array.push("{\"Field\":\""+$(this).find("#Field").val()+"\",\"Value\":\""+$(this).find("#Value").val()+"\"}");
				}); 
			}else{
				$("#ul_body tr").each(function(i,item){
					array.push("{\"Field\":\""+$(this).find("#Field").val()+"\"}");
				});		
			}

			candidate="["+array.join(",")+"]";
			$.ajax({
				url:adminpath+"/dtp/add.do",
				type:"post",
				data:{Description:Description,EditorType:EditorType,
					Unit:Unit,Candidate:candidate,Reorder:Reorder,
					Required:Required,DeviceTypeId:deviceTypeParameter.deviceTypeId},
					dataType:"json",
					success:function(result){
						if(result.state==0){
							alert("添加成功");
							$(obj).dialog('close');
							deviceTypeParameter.openParameterDialog();
						}else{	
							alert(result.message);		
						}								

					},
					error:function(){
						alert("添加失败");
					}
			});
		},
		getDeviceTypeParameter:function(Id){
			$.ajax({
				url:adminpath+"/dtp/getDeviceTypeParameter.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(result){					
					if(data.state==0){
						user.openDialog(result.data);
					}
				},
				error:function(){
					alert("获取失败");
				}
			});
		},
		updateFinish:function(){
			var doc = arguments[0];
			var f =$.parseJSON(doc.candidate);
			if(f==undefined || f.length==0) return;
			$.each(f,function(i,item){
				item.EditorType=doc.editorType;
			});
			switch (doc.editorType) {
			case "select":
			case "texts":
				$("#tr_Candidate").show();
				console.log(f);
				var html = $("#rowTemplate").tmpl(f).appendTo("#ul_body");

				break;
			}
		},
		updateDeviceTypeParameter:function(obj){
			if(!$("#DeviceParameterForm").formValidate())return;
			var id = $("#DeviceParameterForm").find("#Id").val();
			var Description = $("#Description").val().trim();
			var EditorType = $("#EditorType").val().trim();
			var Required = $("#Required").val();
			var Unit = $("#Unit").val().trim();
			var Reorder = $("#Reorder").val().trim();
			var candidate=""; 
			var array=[];
			if(EditorType!="texts"){
				$("#ul_body tr").each(function(i,item){
					array.push("{\"Field\":\""+$(this).find("#Field").val()+"\",\"Value\":\""+$(this).find("#Value").val()+"\"}");
				}); 
			}else{
				$("#ul_body tr").each(function(i,item){
					array.push("{\"Field\":\""+$(this).find("#Field").val()+"\"}");
				});	
			}

			candidate="["+array.join(",")+"]";
			$.ajax({
				url:adminpath+"/dtp/update.do",
				type:"post",
				data:{Id:id,Description:Description,
					EditorType:EditorType,Unit:Unit,
					Candidate:candidate,Reorder:Reorder,Required:Required,
					DeviceTypeId:deviceTypeParameter.deviceTypeId},
					dataType:"json",
					success:function(result){
						if(result.state==0){
							alert("您已修改成功");
							$(obj).dialog('close');
							deviceTypeParameter.openParameterDialog();
						}else{	
							alert(result.message);			
						}								

					},
					error:function(){
						alert("修改失败");
					}
			});
		}
}

var deviceQR={
		loadQRList:function(){
			var batch=$.getUrlParam("batch");
			$.ajax({
				url:path+"/deviceQR/showQRList.do",
				type:"post",
				data:{batch:batch},
				asyne:false,
				dataType:"json",
				success:function(result){
					if(result.state==0){
						var list=result.data;
						$.each(list,function(i,item){
							item.qrvirtural=path+item.qrvirtural;
						});
						$("#deviceQRbodyListTemplate").tmpl(list).appendTo("#DataFrom");
						
					}else{	
						alert(result.message);			
					}								

				},
				error:function(){
					alert("修改失败");
				}
			});

		}
}