var deviceType={
		appJson:null,
		editorTypeChange: function (obj) {
			switch (obj.value) {
			case "select":
			case "texts":
				$("#ul_body").html("");
				deviceType.addRule();
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
			deviceType.delNode(node);
		},

		//删除对象
		delNode: function (node) {
			node = deviceType.getTr(node);
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
			var Description = $("#Description").val().trim();
			var EditorType = $("#EditorType").val().trim();
			var Unit = $("#Unit").val().trim();
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
				url:path+"/device/add.do",
				type:"post",
				data:{Description:Description,EditorType:EditorType,Unit:Unit,Candidate:candidate},
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
		getDeviceTypeParameter:function(Id){
			$.ajax({
				url:path+"/device/getDeviceTypeParameter.do",
				type:"post",
				data:{Id:Id},
				dataType:"json",
				success:function(result){					
					if(data.state==0){
						deviceType.appJson=result.data;
						user.openDialog(deviceType.appJson);
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
			var id = $("#DeviceParameterForm").find("#Id").val();
			var Description = $("#Description").val().trim();
			var EditorType = $("#EditorType").val().trim();
			var Unit = $("#Unit").val().trim();
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
				url:path+"/device/update.do",
				type:"post",
				data:{Id:id,Description:Description,
					EditorType:EditorType,Unit:Unit,Candidate:candidate},
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
		}

}