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
