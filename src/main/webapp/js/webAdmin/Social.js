/*
	微信对接部分数据管理业务逻辑处理
	social.js 
	version: 2.1
	datetime: 2015/5/18
*/

var wechat = {
    // 微信分组功能
    Group: {
        //获取用户组
        getGroups: function (callback, update) {
            if (!top.social_group_list || update) {
                var data = "<action>getgroups</action><update>" + (update ? 1 : 0) + '</update>';
                wechat.post(data, function (r) {
                    if (r.code == 0) {
                        top.social_group_list = r.group;
                        if (callback) callback(top.social_group_list);
                    } else {
                        top.pub.error(r.msg);
                    }
                }, "Group.aspx");
            } else if (callback) {
                callback(top.social_group_list);
            }
        },
        selectGroup: function (boxid, t, w, h, win) {
            wechat.Group.getGroups(function () {
                var htmlBox = win.ECF("#" + boxid);
                var div = htmlBox[0];

                var items = json.Groups.Group;

                var html = "<table>" + Group.groupHeadTaplate;

                if (items) {
                    if (!ECF.isArray(items) && !Material.isArray(items)) items = [items];

                    ECF.each(items, function () {
                        html += Group.groupTemplate.replace(/\{\$Id\$\}/g, this.Id.text)
                                           .replace(/\{\$Name\$\}/g, this.Name.text)
                                           .replace(/\{\$Count\$\}/g, this.Count.text)
                    });
                }

                html += "</table>";

                htmlBox.html(html);
                div = div.cloneNode(true);
                pub.htmlValue(div, w, h, t, doc, [{ callback: Group.close }]);
            });
        },
        //设置用户组
        setGroups: function (id, name, count, obj, win) {
            var CloudId = win.ECF("#CloudId");
            var CloudName = win.ECF("#CloudName");
            var Count = win.ECF("#Count");

            CloudId.value(id);

            CloudName.value(name);

            Count.value(count);
        },
        //刷新用户组
        updateGroups: function () {
            wechat.Group.getGroups(null, true);
        },
        groupHeadTaplate: "<tr><th></th><th>微信组</th><th>用户数</th></tr>",
        groupTemplate: "<tr><td><input id=\"Id\" name=\"Id\" type=\"radio\" onclick=\"Group.setGroups({$Id$},'{$Name$}',{$Count$},this,window)\"/></td>\
                    <td>{$Name$}</td><td>{$Count$}</td></tr>"
    },

    ReceiveMsg: {
        clearValue: function () {
            ECF("#StartTime").value("");
            ECF("#EndTime").value("");
            ECF("#RadStatus0")[0].checked = true;
            ECF("#MsgTypeKey").value("-1");;
        },
        saveMsg: function () {
            $e.ajax({
                data: '{"action":"savemsg"}',
                dataType: "json",
                loading: function () {
                    tip.show("数据加载中 ...");
                },
                success: function () {
                    tip.hide();
                },
                error: function () {
                    tip.hide();
                    pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
                }
            });
        },
        reply: function () {
            $e.ajax({
                data: '{"action":"reply"}',
                dataType: "json",
                loading: function () {
                    tip.show("数据加载中 ...");
                },
                success: function () {
                    tip.hide();
                },
                error: function () {
                    tip.hide();
                    pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
                }
            });
        },
        show: function (id) {

            $.ajax({
                url: path + "/admin/social/show.do",
                dataType: "xml",
                loading: function () {
                    tip.show("数据加载中 ...");
                },
                success: function () {
                    var json = ECF.parseJSON(arguments[1].text);
                    console.log(pub.iwin());
                    var html = top.ECF("#Messagebox").html();
                    pub.htmlValue(html, 450, 550, "详情", json);
                },
                error: function () {
                    tip.hide();
                    pub.tips("数据处理错误,错误代码: " + arguments[0] + ";错误信息: " + arguments[1], 1.5);
                }
            });
        }
    },

    // 微信基础配置
    set: {

        // 保存配置信息
        save: function (o) {

            $("#wechat_Config").postXml("wechat.set", function () {
                var json = arguments[0][1].json;
                if (json) {
                    if (json.code == "") {
                        pub.top.pub.tips("配置微信基础信息成功;");
                        window.location.reload();
                    }
                    else {
                        pub.top.pub.error(json.msg);
                    }
                }
            }, o, "WechatSet.aspx");
        },

        // 修改配置信息
        show: function (pid, cer) {
            var html = "",
                step1 = $("#config_step1"),
                step2 = $("#config_step2");

            cer = cer < 1 ? 1 : cer;

            if (pid < 1) {
                step2.hide();
                html = $("#wechat_config_template").html();
                step1.find("*[dynamic]").html(html);
                step1.show();
                $e("#Catering").val(cer);
            }
            else {
                step1.hide();
                html = $("#wechat_info_template").html();
                step2.find("*[dynamic]").html(html);
                step2.show();
            }

        }
    },

    // 自定义菜单
    menu: {
        currentNode: null,

        // 初始化自定义菜单
        init: function (json) {

            var html = $e("#menuTemplate").html();
            $e("#menuBox").html(jte(html, json));

            if (json.length > 2) {
                $e("li[class='manibtn']").hide();
            }

            // 处理每个一级菜单下最多只能添加五个二级菜单的限制
            $("#menuBox").find("dl").each(function () {
                var dl = $e(this),
                    nodes = dl.children().length;
                if (nodes >= 5) {
                    dl.find('a[button="add"]').hide();
                }
            });
        },

        // 新添加菜单
        add: function (o, layer, tempid) {
            var $o = $e(o), menuName = "", addElement, parentId = 0, box, len;
            if (layer == 1) {

                box = $o.parent("ul");

                len = 8;

                addElement = function (name, id) {
                    var ul = $o.parent("ul"), lis = ul.children().length;
                    if (lis == 3) {
                        $e("li[class='manibtn']").hide();
                    }

                    if (lis < 4) {
                        var li = document.createElement("li");
                        li.innerHTML = $e("#" + tempid).html().replace(/\{name\}/ig, name).replace(/\{id\}/ig, id);
                        $o.parent("li").before(li);
                    }
                }

            }
            else if (layer == 2) {

                box = $o.parent("dl");

                len = 16;

                parentId = box.attr("menuid");

                addElement = function (name, id) {
                    var dl = $o.parent("dl"), nodes = dl.children().length;
                    if (nodes == 5) {
                        dl.find('a[button="add"]').hide();
                    }

                    if (nodes < 6) {
                        var dd = document.createElement("dd"), $d = $e(dd);
                        $d.html($e("#" + tempid).html().replace(/\{name\}/ig, name).replace(/\{id\}/ig, id));
                        $d.attr("menuid", id);
                        $d.addClass("rm");

                        wechat.menu.edit(dd, layer, '{id}');
                        dl.append(dd);
                    }
                }

            }

            // 提示并输入菜单名称
            pub.confirm("<div class=\"custom-table\" style=\"width:300px;\"><div class=\"form\"><table border='0' cellpadding='0'cellspacing='0'><colgroup><col style=\"width:120px;\"/><col style=\"width:auto;\"/></colgroup><tr><th><p class=\"name\">菜单名称：</p></th><td><div class=\"inputbox\"><input type='text' id='Name' name='Name' /></div></td></tr></table></div></div>",
				function () {

				    var dlg = this;
				    menuName = this.dom.content.find("#Name").value();
				    // 判断菜单长度
				    if (menuName.getLength() > len) {
				        pub.alert("菜单超长了,只能" + len / 2 + "个中文字符或" + len + "个英文字符");
				        return;
				    }

				    if (menuName != "") {
				        frm.post("menu.add",
							"<Name>" + menuName + "</Name><ParentId>" + parentId + "</ParentId><Reorder>" + box.children().length + "</Reorder>",
							"CustomMenu.aspx",
							function () {
							    var res = arguments[1];
							    if (res.text > 0) {
							        top.pub.tips("添加自定义菜单成功");
							        addElement(menuName, res.text);
							        dlg.close();
							    }
							    else {
							        top.pub.error("添加自定义菜单失败");
							    }
							});
				    }

				},
				function () {
				    this.close();
				}, "添加自定义菜单");

        },

        // 菜单编辑
        edit: function (o, layer, id) {
            if (wechat.menu.currentNode != null) {
                wechat.menu.currentNode.removeClass("select");
            }

            var $o = $e(o), data = "", das, ele;
            if (layer == 1) {
                var dl = $o.parent("dl"), nodes = dl.children().length;
                ele = $o.parent("dt");
                if (nodes > 1) {
                    $e("#noAction").show();
                    $e("#actionForm").hide();
                }
                else {
                    data = ele.attr("data");
                }
            }
            else {
                $e("#noAction").hide();
                $e("#actionForm").show();
                ele = $o.parent("dd");

                data = ele.attr("data");
            }

            $e("#menuName").value(ele.find('[rel="name"]').html());
            $e("#menuId").val(id);
            $e("#menuLayer").val(layer);
            ele.addClass("select");
            wechat.menu.currentNode = ele;

            // 有数据需进行赋值才处理
            if (typeof (data) == "string" && data != "") {
                das = data.split(",");

                var menutype = das[0] == "null" ? "" : das[0],
                    link = das[1] == "null" ? "" : das[1],
                    matype = das[2] == "null" ? "" : das[2],
                    maid = das[3] == "null" ? "" : das[3],
                   matxt = das[4] == "null" ? "" : das[4];

                if (menutype == "") menutype = "view";

                $e("#MenuType").value(menutype);
                if (menutype == "click") {
                    pub.tabChange($e('[pannelid=clickPanel]')[0]);

                    msgeditor.set(matype, maid, matxt);
                }
                else if(menutype == "view"){
                    pub.tabChange($e('[pannelid=viewPanel]')[0]);
                    $e("#Link").value(link);
                } else {
                    pub.tabChange($e('[pannelid=locationPanel]')[0]);
                }

            }


            var pos = $o.position();
            $e("i.icon")[0].style.top = pos.top - 60 + "px";
        },

        // 删除菜单
        del: function (o, layer, menuid) {

            var $o = $e(o), delElement = null, title = "";

            // 删除一级菜单
            if (layer == 1) {
                title = "会删除菜单下的所有二级菜单,您确定要删除吗?";
                delElement = function () {
                    var li = $o.parent("li");
                    li.remove();
                    if ($o.parent("ul").children().length < 3) {
                        $e("li[class='manibtn']").show();
                    }
                };
            }
            else { // 删除二级菜单
                title = "您确定要删除该菜单吗?";
                delElement = function () {
                    var dd = $o.parent("dd"), dl = $o.parent("dl");
                    dd.remove();
                    if (dl.children().length < 5) {
                        dl.find('a[button="add"]').hide();
                    }
                };
            }

            // 处理删除操作
            pub.confirm(title, function () {
                var dlg = this;
                wechat.post("<action>menu.del</action><Id>" + menuid + "</Id>", function (r) {
                    if (r.code == 0) {
                        delElement();
                    } else {
                        top.pub.error(r.msg);
                    }
                    dlg.close();
                });

            }, function () {
                this.close();
            });
        },

        // 添加菜单链接
        addLink: function (o) {
            var html = $e("#linkTemplate").html();
            var win = window;
            var input = o;

            pub.confirm(html, function () {
                var $panel = $e('*[panel=current]', this.dom.content[0]);
                var val = "";
                $panel.find("input").each(function () {
                    //var $input = $e(this), type = $input.attr("type");
                    if (this.type == "text" && this.value != '') {
                        //val = $input.value();
                        val = this.value;

                        input.value = val;
                    } else {
                        if (this.checked) {
                            val = this.value;

                            input.value = val;
                        }
                    }
                });

                this.close();
            }, function () { this.close() }, "添加链接", function () {
                if (typeof (customCheck) == 'function') {
                    customCheck(this.dom.content[0]);
                }

                if (typeof (cutTable) == 'function') {
                    cutTable(window.top);
                }
            });
        },

        // 保存菜单
        save: function (o) {

            var name = $e("#menuName").value(),
                layer = $e("#menuLayer").value(),
                len = 8;

            // 判断当前编辑菜单的级数
            if (layer == 2) {
                len = 16; // 二级菜单可以16个字符
            }

            if (name.getLength() > len) {
                pub.alert("菜单超长了,只能" + len / 2 + "个中文字符或" + len + "个英文字符");
                return;
            }

            var id = $e("#menuId").value();

            if (id < 1) {
                pub.alert("没有任何编辑不需保存");
                return;
            }

            var info = "", menuType, link = "", mtype = 0, mid = 0, mtxt = "";

            if ($e("#actionForm").isVisible()) {

                menuType = $e("#MenuType").value();

                info += "<MenuType>" + menuType + "</MenuType>";
                // 事件
                if (menuType == "click") {
                    var msg = msgeditor.get();
                    mtype = msg.type;
                    mid = msg.id;
                    mtxt = msg.content;

                    //console.log([msg]);

                    info += "<MaterialType>" + mtype + "</MaterialType><MaterialId>" + mid + "</MaterialId><MaterialTxt><![CDATA[" + mtxt + "]]></MaterialTxt>";
                }
                else if(menuType == "view"){ // 链接
                    link = $e("#Link").value();
                    if (link == "") {
                        pub.alert("链接必须填写");
                        return;
                    }
                    info += "<Link>" + link + "</Link>";
                }
            }


            var data = "<action>menu.update</action><Name>" + name + "</Name><Id>" + id + "</Id>" + info;

            //console.log(data);

            wechat.post(data, function (r) {

                if (r.code == 0) {
                    top.pub.tips("更新成功");

                    //wechat.menu.reset();

                    if (wechat.menu.currentNode != null) {
                        var box = wechat.menu.currentNode;

                        wechat.menu.currentNode.find('[rel="name"]').html(name);
                        if (wechat.menu.currentNode[0].nodeName.toUpperCase() == "DT") {
                            box = wechat.menu.currentNode.parent("dl");
                        }

                        box.attr("data", menuType + "," + link + "," + mtype + "," + mid + "," + mtxt);
                    }

                }
                else {
                    top.pub.error(r.msg);
                }

            });
        },

        // 发布菜单到接口
        publish: function (o) {
            var $o = $e(o);
            $o.html("正在发布菜单...");
            wechat.post("<action>menu.publish</action>", function (r) {
                $o.html("发布到微信");
                if (r.code == 0) {
                    top.pub.tips("菜单已成功发布到微信平台,预计在二小时内生效!");
                }
                else {
                    top.pub.error("菜单发布到微信平台失败!<BR />" + r.msg);
                }


            });
        },

        // 重置菜单编辑表单
        reset: function () {
            $e("#menuId").value(0);
            $e("#menuName").value("");
            $e("#menuLayer").value("1");
            $e("#Link").value("");
            pub.tabChange($e('li[pannelid="viewPanel"]')[0]);
            msgeditor.reset();
        }
    },

    // 消息回复处理
    reply: {

        // 保存回复信息
        save: function () {
            var data = "<action>reply.save</action>";

            var rtype = $e("#replyType").val();

            var msg = msgeditor.get();

            var msgxml = "<MaterialType>" + msg.type + "</MaterialType><MaterialId>" + msg.id + "</MaterialId><MaterialTxt>" + msg.content + "</MaterialTxt>";

            // 关注回复
            if (rtype == "subscribe") {
                data += "<Name>关注微信自动回复</Name><Flag>_auto_default_subscribe</Flag>" + msgxml;
            }
            else if (rtype == "msg") { // 消息默认回复
                data += "<Name>消息自动回复</Name><Flag>_auto_default_msg</Flag>" + msgxml;
            }
            else {
                var name = $e("#RuleName").val();

                if (name == "") {
                    pub.tips("请填写规则名称!");
                    return;
                }

                var keys = $e("#Keywords").val();

                if (keys == "") {
                    pub.tips("请先添加关键字后再保存!");
                    return;
                }

                data += "<Name>" + name + "</Name><Keywords>" + keys + "</Keywords>" + msgxml;
            }

            $e.ajax({
                dataType: "xml",
                data: data,
                success: function () {
                    var txt = arguments[1].text;
                    if (txt > 0) {
                        pub.tips("保存回复设置成功");
                        if (rtype == "keyword") {
                            $e("#addKeyButton").show();
                            $e("#keywordList").show();
                            $e("#keyword_edit_area").hide();
                            wechat.reply.get("keyword");
                        }
                    }
                    else {
                        pub.tips("保存消息失败");
                    }


                },
                error: function () {

                }
            });
        },

        // 获取回复信息
        get: function (flag) {

            if (flag == "keyword") {
                jte.register("splitKey", wechat.reply.splitKeys);
                $e("#keywordListBody").page("keywordListTemplate", "keywordListPage", "<SortField>Id</SortField>", 10, function () {
                    $e('#keywordListBody').masonry({
                        itemSelector: '*[keyword=list]'
                    });
                });
            }
            else {
                $e("#msgPanel").html($e("#msg_editor_template").html());
                $e.ajax({
                    dataType: "xml",
                    data: "<action>reply..get</action><Flag>_auto_default_" + flag + "</Flag>",
                    success: function () {
                        var json = $e.parseJSON(arguments[1].text);
                        if (json && json[0]) {
                            msgeditor.set(json[0].MaterialType, json[0].MaterialId, json[0].MaterialTxt);
                        }
                    },
                    error: function () {

                    }
                });
            }
        },

        // 根据类型进行切换
        type: function () {

            var $o = $e(this),
             type = $o.attr("type");
            $e('#replyType').val(type);

            if (type == "keyword") {
                $e("#msgPanel").html("");
                $e("#button_area").hide();
            }
            else {
                $e("#button_area").show();
            }

            wechat.reply.get(type);
        },

        // 显示关键字编辑区
        keyword: function (o) {
            $e("#keyword_msg_editor").html($e("#msg_editor_template").html());
            $e("#keyword_edit_area").fadeIn(400);
            $e("#addKeyButton").hide();
            $e("#keywordList").hide();
        },

        // 添加关键字
        addKey: function () {
            var keys = $e("#Keywords").val(),
                key = $e("#keywordTemp").val();
            $e("#Keywords").val(keys + (keys == "" ? "" : ",") + key);
            $e("#keyList").append('<li><p class=\'name rm\'>' + key + '</p><div class=\'for-tools rm\'><span class=\'tips rm\'>未全匹配</span><a href=\'javascript:void(0);\' class=\'btn iconfont icon-bianji\'></a><a href=\'javascript:void(0);\' class=\'btn iconfont icon-shanchu\'></a></div></li>');
            $e("#keywordTemp").val('');
        },

        // 模板引擎展示数据时处理关键字
        splitKeys: function () {
            try {
                if (arguments[0]) {
                    var ks = arguments[0].split(",");
                    //console.log(ks);
                    var ret = "";
                    for (var k = 0; k < ks.length; k++) {
                        ret += '<a class="list rm">' + ks[k] + '</a>';
                    }
                    return ret;
                }

            } catch (e) {
                console.warn(e);
                return "";
            }
        },

        // 编辑关键字
        edit: function () {

        },

        // 删除关键字
        del: function (o, id) {
            pub.confirm("您确定要删除关键字回复规则吗?", function () {
                $e.ajax({
                    dataType: "xml",
                    data: "<action>reply..del</action><Id>" + id + "</Id>",
                    success: function () {
                        var txt = arguments[1].text;
                        if (txt > 0) {
                            pub.tips("删除数据成功");
                        }
                        else {
                            pub.tips("删除数据失败");
                        }
                    }
                });
            }, function () {

            });
        }

    },

    // 素材资源管理
    material: {

        currentBox: null,

        // 添加资源
        add: function (o) {

            var fs = wechat.material.forms, $o = $e(o);

            if (wechat.material.currentBox) {
                if (fs && fs.formValidate()) {
                    wechat.material.currentBox.find("input").val(fs.getValues("xml"));
                }
                else {
                    return;
                }
            }

            var lbox = $e(".left_preview"), inputs = lbox.find("input"), title = "<Title>" + $e("#Title").val() + "</Title>";

            if (inputs.length > 1) {
                title = "<Title>多图文</Title>";
            }

            var data = title + "<MaterialType>3</MaterialType><Articles>";


            inputs.each(function () {
                data += "<Article>" + this.value + "</Article>"
            });



            data += "</Articles>";

            $o.html("保存中...");
            $e.ajax({
                dataType: "xml",
                data: "<action>material.add</action>" + data,
                success: function () {
                    o.innerHTML = "保存";
                    var txt = arguments[1].text;
                    if (txt > 0) {
                        pub.top.pub.tips("添加图文素材成功");
                        window.location.href = "Material.aspx";
                    }
                    else {
                        pub.top.pub.tips("添加图文素材失败");
                    }
                }
            });
        },

        // 添加编辑框
        addBox: function (o) {
            var li = document.createElement("li");
            li.innerHTML = $e("#addMultiBoxTemplate").html();
            $e(o).parent("li").before(li);
        },

        // 删除编辑框
        delBox: function (o) {
            var $o = $e(o),
                lis = $o.parent("ul").children().length;
            console.log(lis);
            if (lis > 3) {
                $o.parent("li").remove();
            }
            else {
                pub.tips("多图文至少需要二条单图文");
            }
        },

        // 图文编辑
        editBox: function (o) {
            var fs = wechat.material.forms;
            if (wechat.material.currentBox) {
                if (fs && fs.formValidate()) {
                    wechat.material.currentBox.find("input").val(fs.getValues("xml"));
                }
            }

            wechat.material.currentBox = $e(o).parent("li");

            var pos = wechat.material.currentBox.position();
            $e("#postionIcon")[0].style.top = pos.top + 40 + "px";

            if (fs) {
                fs.formReset();
            }
        },

        del: function (o, id) {
            if (id > 0) {
                pub.confirm("<div style='margin:10px'>您确定要删除该素材吗?</div>", function () {
                    var dlg = this;
                    $e.ajax({
                        data: "<action>material.del</action><Id>" + id + "</Id>",
                        dataType: "xml",
                        success: function () {

                            var txt = this.responseText;

                            if (txt > 0) {
                                pub.tips("删除素材成功");
                                wechat.material.load();
                                dlg.close();
                            }
                            else {
                                pub.tips("删除素材失败");
                            }
                        }
                    });

                }, function () {
                    this.close();
                });
            }
        },

        // 导入文章
        import: function (o) {
            var win1 = window,
                $o = $e(o),
                li = $o.parent("li");
            pub.open("Social/Article.aspx?type=2", 650, 500, "选择图文", [{
                callback: function () {
                    var win = this.iwindow || window,
                        art = win.$e("#ArticleList").find("li.select");

                    if (art.length < 1) {
                        pub.tips("请选择图文后再确定！", 1.5);
                        return;
                    }

                    var aid = art.attr("articleId"),
                        title = art.find("*[rel=title]").html(),
                        img = art.find("img").attr("src");

                    li.find("*[rel=title]").html(title);
                    li.find("img").attr("src", img);

                    li.find("input").val(aid);

                    if (wechat.material.forms) {
                        wechat.material.forms.formReset();
                    }

                    this.close();
                }, name: '确定', focus: true
            }], function () {
                // 页面加完
            });
        },

        // 显示图稿添加
        show: function (type) {

            pub.open("Social/AddMaterial.aspx?type=" + type, 1060, 600, "添加图文", [{
                callback: function () {

                    this.close();
                }, name: '确定', focus: true
            }], function () {
                // 页面加完
            });

        },

        // 选择图片
        selectImg: function () {
            if (wechat.material.currentBox != null) {
                var win1 = window;
                pub.open("Social/SelectMaterial.aspx?type=2", 600, 400, "选择图片素材", [{
                    callback: function () {
                        var win = this.iwindow || window,
                    srcs = win.ECF('#ImgList').getChkValue("ChkImg");

                        //判断是否选择了图片
                        if (srcs.length == 0) {
                            pub.tips("请选择图片后再确定！", 1.5);
                            return;
                        }

                        //判断选择图片的张数
                        if (srcs.split("|").length > 1) {
                            pub.tips("一次最多只能选择" + 1 + "张图片！", 1.5)
                            return;
                        }

                        var srs = srcs.split(":"), src1;

                        if (srs.length > 1) {
                            src1 = srs[1];

                            win1.$e("#CoverSrc").value(srs[1]);
                            var $img = wechat.material.currentBox.find("img");

                            $img.removeClass("hide");
                            $img.value(srs[1]);
                        }


                        this.close();
                    }, name: '确定', focus: true
                }], function () {
                    // 页面加完
                });
            }

        },

        // 设置标题
        title: function (o) {
            if (wechat.material.currentBox != null) {
                wechat.material.currentBox.find("*[rel=title]").html(o.value);
            }
        },


        // 图文列表加载完后执行方法
        load: function () {

            $e("#ArticleListBody").page("ArticleListTemplate", "ArticleListPage", "<Type>3</Type><SortField>Id</SortField>", 10, function () {
                //瀑布流
                $e('#ArticleListBody').masonry({
                    itemSelector: '*[keyword=list]'
                });
            });

        }


    },

    // 粉丝管理
    follower: {
        //检查checkBox
        checkchk: function (win) {
            var checks = ECF("input[type='checkbox']", ECF("#EGrid1")[0]);
            if (!ECF.isArray(checks) && !Material.isArray(checks) && checks.length < 2) checks = [checks];

            var userIds = "0";

            ECF.each(checks, function () {
                if (ECF(this)[0].checked) {
                    if (userIds.length <= 0) {
                        userIds = ECF(this)[0].getAttribute("relvalue");
                    }
                    else {
                        userIds += "," + ECF(this)[0].getAttribute("relvalue");
                    }
                }
            });

            win.pwindow.ECF("#UserIds").value(userIds);
        },
        //获取用户
        getUser: function (id) {
            pub.open(RootUrlDirectory + '/Social/SelFollower.aspx?groupId=' + id, 700, 500, '选择用户', [{ callback: Group.addUser, arguments: [id] }]);
        },
        // 更新粉丝
        update: function () {
            var dlg = pub.dialog("更新粉丝列表", '<div class="progress" style="border:1px solid #ccc"><div style="width:0; background:red; height:1em"></div></div>', 300, 100);
            var n = "";
            var count = 0;
            function func() {
                wechat.post("<action>update</action><nextOpenId>" + n + "</nextOpenId>", function (r) {
                    if (r && r.code == 1) {
                        count += r.count;
                        var p = dlg.dom.content.find('.progress div').css("width", parseInt(count / count < r.total * 100) + "%")
                        if (p.length == 0) return;
                        if (count < r.total && r.nextOpenId) {
                            n = r.nextOpenId;
                            ECF("#nextOpenId").value(n);
                            func();
                        } else {
                            top.pub.tips("更新粉丝完成");
                        }
                    } else {
                        top.pub.error("更新粉丝失败，" + r.msg);
                    }
                    dlg.close();
                });
            }
            func();
        },
        getSelectedIds: function () {
            var ids = [];
            $e("tbody td input:checkbox:checked").each(function () {
                ids.push(this.value);
            });
            return ids;
        },
        changeGroup: function () {
            var ids = wechat.follower.getSelectedIds();
            if (ids && ids.length) {
                wechat.Group.getGroups(function (gs) {
                    var func = function () {
                        var gi = s.value();
                        var gn = gi > 0 ? s.text() : "";
                        dlg.close();
                        wechat.post("<action>changeGroup</action><groupId>" + gi + "</groupId><followerIds>" + ids.join(",") + "</followerIds>",
                            function (r) {
                                if (r) {
                                    if (r.code == 0) {
                                        var gn = dlg.dom.content.find('select').text();
                                        $e("tbody td input:checkbox:checked").each(function () {
                                            var p = $e(this).parents("tr");
                                            $e($e(p[p.length - 1]).find('td')[4]).find('h6').text(gn);
                                        })
                                    } else {
                                        top.pub.error(r.msg);
                                    }
                                }
                            })
                    }
                    var dlg = pub.dialog("转移粉丝分组", '<div class="forms"><div class="area"><ul><li><p class="name">分组：</p><div class="inputbox"><select></select></div><li></div></div>', 300, 200, [{ callback: func, name: '确认', focus: true }, { callback: function () { dlg.close() }, name: '取消' }]);
                    var s = dlg.dom.content.find('select');
                    for (var i = 0; i < gs.length; i++) {
                        s.append('<option value="' + gs[i].id + '">' + gs[i].name + '</option>');
                    }
                });
            } else {
                top.pub.error("请先选择粉丝");
            }
        },
        getMessages: function (openId) {
            pub.open(RootUrlDirectory + "/Social/Message.aspx?openId=" + openId, 800, 600);
        }
    },

    // 向后台提交数据
    post: function (data, callback, url) {
        url = url || "";
        $e.ajax({
            url: url,
            dataType: "xml",
            data: data,
            success: function (r, t) {
                r = eval('(' + t.text + ')');
                if (typeof (callback) == "function") {
                    callback.apply(this, [r]);
                }
            },
            error: function (c, m) {
                top.pub.error("服务器错误：" + m, 2);
            }
        });
    }
};