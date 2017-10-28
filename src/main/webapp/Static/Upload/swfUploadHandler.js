/*��ʼ����ʼ*/

/*
	�ļ�ͼƬ�ϴ�js����
	file: swfUploadHander.js
	Version: 1.1.0
	Author: xp
	Modefiy: 2012-02-15:����ͼƬԤ�����������,preview_width,preview_height,class_name(xp)
*/
// ͼƬ������
if (typeof (imageDomain) == "undefined")
    imageDomain = '';

//�����ϴ��Ĵ�����
var uploadHandler = function (config){
    //alert(uploadHandler.fn.init);
    swfCurrentObj: null;
	return new uploadHandler.fn.init(config);
}

//����鿴��ͼ
function upload_Preview_Image (id,src,o){
	var oimg = document.getElementById(id + "_max_pic_img");
	oimg.src = src;
};

uploadHandler.fn = uploadHandler.prototype = {
    version: "1.0.0 beta",
    handler: null,
    //��ʼ����װflash
    init: function (config) {
        var self = this,
            dfs = this.defaults();
        //��Ĭ��������ӵ�config��
        for (var _ in dfs) { if (config[_] === undefined) config[_] = dfs[_]; }
        this.config = config;
        uploadHandler[config.id + "_Config"] = config;
        this.swf = new SWFUpload(config);
        uploadHandler.swfCurrentObj = this.swf;
        var hid = document.getElementById(config.id);
        if (hid) hid.value = "";
        return this;
    },
    //��ʼ���ļ�ѡ��Ի���
    fileDialogStart: function () {
    },
    //�����ļ�����
    fileQueued: function () {
        var imgSrc = document.getElementById(this.settings.id).value;
        var length = imgSrc.split(";").length;
        //����ϴ�ͼƬ����������ô�С
        if (length == parseInt(this.settings.file_upload_limit) && imgSrc.length > 0) {
            //ȡ���ϴ�
            this.cancelUpload(null, false);
            alert("���ֻ���ϴ�" + this.settings.file_upload_limit + "��ͼƬ����ɾ������ͼƬ�������");
            return;
        }
        $("#Delete_Div").show();
        var arg = arguments[0],
			tid = uploadHandler.getId(arg.id);
        var tbox = document.getElementById(tid + "_thumbs_box");

        //��ֹ��ʾ�ϴ��ɹ��������ͼ By Zkai 2015-07-06
        return;
        if (tbox) {
            tbox.style.display = this.settings.file_upload_limit == 1 ? "none":"block";
            tbox.style.zIndex = 1982;
            var thumb = document.createElement("div");
            thumb.id = 'thumbs_inner_' + arg.id;
            thumb.className = "thumbs_inner";
            thumb.innerHTML = uploadHandler["thumbHtml"]
			.replace("{$img$}", arg.name + "���ϴ��ļ�...")
			.replace("{$method$}", "")
			.replace("{$view$}", "")
			.replace("{$delete$}", "");
            tbox.appendChild(thumb);
        }
    },
    //�ļ����д���
    fileQueueError: function (file, errorCode, message) {
        try {
            var errorMsg = "";
            switch (errorCode) {
                case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                    if (message > 0) {
                        errorMsg = "��ֻ��ͬʱ�ϴ�" + message + "���ļ�!";
                    } else {
                        errorMsg = "�����ټ����ϴ�";
                    }
                    break;
                case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                    errorMsg = "�������ϴ�0�ֽڵ��ļ�";
                    break;
                case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                    errorMsg = "�ļ����ɴ���" + this.settings.file_size_limit;
                    break;
                case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                default:
                    alert(message);
                    break;
            }
            if (errorMsg !== "") {
                alert(errorMsg);
                return;
            }
            //addImage("images/" + imageName);
        } catch (ex) {
            this.debug(ex);
        }
    },
    //��ѡ��ͼƬ�Ի������ʱ����
    fileDialogComplete: function (numFilesSelected, numFilesQueued) {
        try {
            if (numFilesSelected > 0) {
                this.startUpload();
                //�����ύ��ť
                //document.getElementById(this.customSettings.submitBtnId).disabled = true;
            }
        } catch (ex) {
            this.debug(ex);
        }
    },
    //�ϴ����ȴ���
    uploadProgress: function (file, bytesLoaded) {
        var arg = arguments[0];
        try {
            var percent = Math.ceil((bytesLoaded / file.size) * 100);
            var thumb = document.getElementById('thumbs_inner_' + arg.id);
            thumb.style.display = "block";
            if (percent === 100) {
                if (thumb) thumb.innerHTML = uploadHandler["thumbHtml"].replace("{$img$}", "���ϴ����").replace("{$method$}", "");
            } else {
                if (thumb) thumb.innerHTML = uploadHandler["thumbHtml"].replace("{$img$}", "���ϴ�: " + percent + "%").replace("{$method$}", ""); 			//progress.setStatus("�����ϴ�...");
            }
        } catch (ex) {
            this.debug(ex);
        }
    },
    //һ��ͼƬ�ϴ��ɹ�����
    uploadSuccess: function (file, serverData) {
        var thumb = document.getElementById('thumbs_inner_' + file.id),
			tid = uploadHandler.getId(file.id);

        var prev = document.getElementById(tid + "_max_pic_img");
        var n = prev.parentNode;
        if (n.style && n.style.display == "none") {
            n.style.display = "";
        }
        n.style.display = "block";
        serverData = (serverData.substr(0, 1) == "/" ? "" : "/") + serverData
        if (prev) prev.src = imageDomain + serverData;

        var hid = document.getElementById(tid);
        if (hid) hid.value = hid.value + (hid.value.length > 0 ? ";" : "") + serverData;
        if (thumb) thumb.innerHTML = uploadHandler["thumbHtml"]
		.replace("{$img$}", "<img src=\"" + imageDomain +  serverData + "\" alt=\"" + file.name + "\" />")
		.replace("{$method$}", "upload_Preview_Image('" + tid + "','" + serverData + "',this);return false;")
		.replace("{$view$}", "uploadHandler.view(\'" + serverData + "\',\'" + file.id + "\',this);")
		.replace("{$delete$}", "uploadHandler.deleteFile(\'" + serverData + "\',\'" + file.id + "\',this,'');")
		.replace("{$deltitle$}", "ɾ��ͼƬ");
    },
    //�ϴ�ȫ�����ʱ����
    uploadComplete: function (file) {
        //alert(this.config);
    },
    //ȡ���ϴ�
    cancelUpload: function () {
    },
    //�ϴ�����ʱ����
    uploadError: function (file, errorCode, message) {
        //alert("error" + message +"||"+ errorCode);
        var imageName = "error.gif";
        var progress;
        try {
            switch (message) {
                case "417":
                    alert("�������������ϴ��˸�ʽ���ļ�");
                    break;
                case "500":
                    alert("����������");
                    break;
                case "501":
                    alert("����ͼƬ��������2M");
                    break;
                case "405":
                    alert("�ϴ��ļ�ʱ������д��");
                    break;
                default:
                    break;
            } 
            delThumb(file.id);
            switch (errorCode) {
                case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED: //���ϴ���ȡ��
                    try {

                    }
                    catch (ex1) {
                        this.debug(ex1);
                    }
                    break;
                case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED: //���ϴ���ֹͣ
                    try {
                        progress = GetFileProgressObject(this.customSettings, file, this.customSettings.upload_target);
                        progress.setCancelled();
                        progress.setStatus("��ֹͣ");
                        progress.toggleCancel(true);
                    }
                    catch (ex2) {
                        this.debug(ex2);
                    }
                case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:  //�������ϴ����ļ�
                    imageName = "uploadlimit.gif";
                    break;
                default:
                    //alert(message);
                    break;
            }
        } catch (ex3) {
            this.debug(ex3);
        }
        //ɾ���Ѽ����������ʾ
        function delThumb(fid) {
            var th = document.getElementById("thumbs_inner_" + fid),
				tid = uploadHandler.getId(fid),
				p = th.parentNode;
            p.removeChild(th);
        }
    },
    //�շ���
    empty: function () {
    },
    // ����post����
    setPostParams: function (param) {
        if(typeof(param) == "object"){
            this.swf.setPostParams(param);
        }
    },
    //��ʼ��һЩ����html
    _init: (function () {
        uploadHandler["thumbHtml"] = '\
            <div class="picbox rm">\
			    <a href="javascript:void(0);" class="rm" onclick="{$method$}">\
			        {$img$}\
                </a>\
			</div>\
			<div class="thumb_btn_bar rm">\
			    <a href="javascript:void(0);" onclick="{$view$}" title="�鿴ͼƬ·��"><span class="thumb_btn_view"></span></a>\
			    <a href="javascript:void(0);" onclick="{$delete$}" title="{$deltitle$}"><span  class="thumb_btn_delete"></span></a>\
		    </div>';
    })(),
    //�ϴ������Ĭ�����ò���
    defaults: (function () {
        return {
            id: "swfUpload",
            upload_url: "/Upload.axd",
            file_dialog_start_handler: this.fileDialogStart, 				//�򿪶Ի���
            file_queued_handler: this.fileQueued, 						//ѡ���ļ���
            file_dialog_complete_handler: this.fileDialogComplete, //�Ի������
            file_queue_error_handler: this.fileQueueError, 		//�ϴ����г���Ļص�
            upload_progress_handler: this.uploadProgress,
            upload_error_handler: this.uploadError,
            upload_success_handler: this.uploadSuccess,
            upload_complete_handler: this.uploadComplete,
            call_back: null, //function(){alert("this");}
            call_back_args: [], 	//�����ϴ���ɺ�ص�����
            class_name: "upload_box", //�ϴ��ؼ���������ʽ�滻
            preview_hide: false, //�Ƿ�����ͼƬԤ��
            default_preview: path+"/Static/Images/error.png", //Ĭ�ϵ�Ԥ��ͼƬ
            max_picture: "", 	//������ʼ��ͼƬ
            preview_width: 200, 	//Ԥ��ͼƬ���
            preview_height: 200, //Ԥ��ͼƬ�߶�
            debug: false				//��������
        }
    })
};

//����ʵ��idȡ����ǰ�����id
uploadHandler.getId = function (id) {
    var tid = id.substr(0, id.length - 4);
    if (tid.substr(tid.length - 1) == "_") {
        tid = tid.substr(0, tid.length - 1);
    }
    return tid;
}

//ִ��ͼƬ��ɾ������
uploadHandler.deleteFile = function () {
    if (!confirm("��ȷ��Ҫɾ����ͼƬ��?")) return;
    var fname = arguments[0],
		fid = arguments[1],
		o = arguments[2],
        functName = arguments[3];

    var th = document.getElementById("thumbs_inner_" + fid);
    if (th == null) {
        return;
    }
    $.ajax({
        url: "/upload.axd",
        dataType: "xml",
        data: '<action>deleteImg</action><filename>' + fname + '</filename>',
        success: function () {
            if (arguments[1].text == 200) {
                var tid = uploadHandler.getId(fid),
				oimg = document.getElementById(tid + "_max_pic_img"),
				p = th.parentNode,
				hid = document.getElementById(tid),
				c = uploadHandler[tid + "_Config"];

                p.removeChild(th);

                if (hid) {  //���������������е�ֵ
                    var val = hid.value + ";";
                    val = val.replace(fname + ";", "");
                    val = val.substr(0, val.length - 1);
                    hid.value = val;

                    if (val == "" && c) {
                        var p = oimg.parentNode;
                        if (p && p.style && c.preview_hide) {
                            p.style.display = "none";
                        }
                        //alert(c.default_preview);
                        if (c.default_preview) {
                            oimg.src = c.default_preview;
                        }
                    }
                }

                if (oimg.src.indexOf(fname) > -1) {

                    oimg.src = "";
                    if (p.childNodes.length > 0) {
                        var m = $("img", p)[0];
                        if (m) m.parentNode.click();
                    }
                }
                $("#Delete_Div").hide();
                alert("ɾ��ͼƬ�ɹ���");

                if (document.getElementById(tid + '_thumbs_box').childNodes.length == 3) {
                    oimg.parentNode.style.display = "none";
                }

                //ִ�лص�����
                //eval(functName + "('" + fname + "',true)");
            }
            else {
                alert("ɾ��ͼƬʧ�ܣ�");
            }
        }
    });
}

//ɾ��ͼƬ
uploadHandler.deleteImg = function (id, functName, delDiv) {
    if (!confirm("��ȷ��Ҫɾ����ͼƬ��?")) return;
    var imgSrc = document.getElementById(id).value;
    var oimg = document.getElementById(id + "_max_pic_img");
    $.ajax({
        url: "/upload.axd",
        dataType: "xml",
        data: '<action>deleteImg</action><filename>' + imgSrc + '</filename>',
        success: function () {
            alert(arguments[1].text);
            if (arguments[2].text == 200) {
               
                oimg.src = "default.jpg";
                delDiv.parentNode.style.display = "none";
            }
            else {
            }           
        },
        error: function () {
            alert("�������: " + arguments[0] + ";������Ϣ: " + arguments[1]);
        }
    });

    window.location.reload();
}

//ɾ��ͼƬ
uploadHandler.delImg = function (src) {
    var isSucceed = false;
    $.ajax({
        url: "/upload.axd",
        dataType: "xml",
        data: '<action>deleteImg</action><filename>' + src + '</filename>',
        async: true,
        success: function () {
            if (arguments[1].text == 200) {
                isSucceed = true;
            }
            else {
                isSucceed = false;
            }
        }
    });

    return isSucceed;
}

//ɾ��һ�Ż��߶���ͼƬ
uploadHandler.deleteFiles = function (val) {
    $.ajax({
        url: "/upload.axd",
        dataType: "xml",
        data: '<action>deleteImg</action><filename>' + val + '</filename>',
        success: function () {
            if (arguments[1].text == 200) {
                //alert("�ɹ���");
            }
            else {
            }
        }
    });
}


//�鿴ͼƬԴ�ļ���ַ
uploadHandler.view = function(){
	alert(arguments[0]);
}

uploadHandler.fn.init.prototype = uploadHandler.fn;

//���ϴ��ؼ�������jsһ��ʽ��������Ҫ�õ��ϴ���λ��
var uploads = function (container, config) {
    return new uploads.fn.init(container, config);
}

//�����ϴ����ܺ���
uploads.fn = uploads.prototype = {
    //����ĳ�ʼ������
    init: function (container, config) {
        //alert(document.getElementById(config.id).value);
        var self = this,
			dfs = this.defaults(),
			id = config.id || container;
        if (typeof (container) == "string") {
            container = document.getElementById(container);
        }
        if (!container) {
            //alert("�Ҳ��������ϴ��ؼ�������");
            return;
        }
        //��Ĭ��������ӵ�config��
        for (var _ in dfs) { if (config[_] === undefined) config[_] = dfs[_]; }
        //alert(config.default_preview);
        container.innerHTML = uploads["innerUploads"]
		.replace(/\{\$id\$\}/g, config.id)
		.replace(/\{\$classname\$\}/g, config.class_name)
        .replace(/\{\$message\$\}/g, typeof (config.message) == "undefined" ? "" : config.message)
		.replace(/\{\$src\$\}/g, config.default_preview == undefined ? path+"/Static/Images/error.png" : config.default_preview)//�滻Ĭ��Ԥ��ͼƬ
		.replace(/\{\$pwidth\$\}/g, config.preview_width)//�滻Ĭ��Ԥ��ͼƬ���
		.replace(/\{\$pheight\$\}/g, config.preview_height)//�滻Ĭ��Ԥ��ͼƬ�߶�
		.replace(/\{\$display\$\}/g, (config.preview_hide ? " style=\"display:none\"" : ""))
        .replace(/\{\$isDisplay\$\}/g, (parseInt(config.file_upload_limit) > 1 ? " style=\"display:block;\"" : " style=\"display:none;\""))
        .replace(/\{\$isDelete\$\}/g, (parseInt(config.file_upload_limit) == 1 ? "<div id=\"Delete_Div\" class=\"max_pic_delete\"  onclick=\"uploadHandler.deleteImg(\'" + config.id + "\', '" + config.functname + "', this);\" title=\"ɾ��ͼƬ\"></div>" : ""));
        config.button_placeholder_id = config.id + "_Buttons";
        return new uploadHandler(config);
    },
    //��ʼ������
    _init: (function () {
        uploads["innerUploads"] = '<script language="javascript">\
			function prev_Thumb_Image(src,o){\
				var oimg = document.getElementById("{$id$}_max_pic_img");\
				oimg.src = src;\
			}\
			</script>\
			<div id="{$id$}_box" class="{$classname$} rm">\
			<!--�ϴ���ť-->\
			<div class="buttons rm">\
			<input type="hidden" id="{$id$}" value="" />\
			<span id="{$id$}_Buttons"></span>\
            <span class="tips rm">{$message$}</span>\
			</div>\
			<!--��ͼԤ��-->\
			<div class="max_pic_box" style="display:none;" >{$isDelete$}<img id="{$id$}_max_pic_img" src="{$src$}" alt="����Ԥ��" onload="javascript:$(this).scaleZoom({$pwidth$},{$pheight$});" nolazy="0" /></div>\
			<!--����ͼ-->\
            <div class="clear"></div>\
			<div id="{$id$}_thumbs_box" class="thumbs_box" {$isDisplay$}></div>\
		</div>';
    })(),
    defaults: function () {
        return {
            id: "swfUpload"
        };
    }
}

//���ϴ��ؼ�����ֵ
uploads.setValue = function (id, val) {
    //console.log(val);
    if(typeof(val)!='string') return;

    val = val.replace(" ", "");
    if (val.length == 0) {
        $("#Delete_Div").hide();
        return;
    }
    var c = uploadHandler[id + "_Config"];

    var vs = val.split(';'),
		box = document.getElementById(id + '_box');
    hid = document.getElementById(id);
    if (hid) {
        hid.value = val;
    }
    if (box) {	//�ж���ʾͼƬ�������Ƿ����
        var max_box = $(".max_pic_box", box),
			thmbs_box = $(".thumbs_box", box);
        max_box.css("display", "none");
        for (var i = 0; i < vs.length; i++) {	//�Զ��ֵ����ѭ�����ô���
            if (vs[i] != "") {
                //�ж�ͼƬ·����һ���ַ��Ƿ�Ϊ��б��
                vs[i] = (vs[i].substr(0, 1) == "/" ? "" : "/") + vs[i];
                if (i == 0) {
                    $("#" + id + "_max_pic_img", box).attr("src", vs[i]);
                }
                var preSrc = (imageDomain == "" ? "http://" + window.location.host : imageDomain);

                if (vs.length == 1) {
                    //�ж�ͼƬ�Ƿ����
                    var Img = new Image;
                    Img.src = preSrc + vs[i];

                    Img.onload = function () {
                        i = i - 1;
                        if (i == 0) {
                            if (c.max_picture == "")
                                $("#" + id + "_max_pic_img", box).attr("src", preSrc + vs[i]);
                        }
                        var tbox = thmbs_box[0];
                        if (tbox) {
                            var thumb = document.createElement("div");
                            var tid = id + "_0_" + i;
                            thumb.id = 'thumbs_inner_' + tid;
                            thumb.className = "thumbs_inner";
                            thumb.innerHTML = uploadHandler["thumbHtml"]
					        .replace("{$img$}", "<img  src=\"" + preSrc + vs[i] + "\" alt=\"" + vs[i] + "\" />")
					        .replace("{$method$}", "upload_Preview_Image('" + id + "','" + vs[i] + "',this);return false;")
					        .replace("{$view$}", "uploadHandler.view(\'" + vs[i] + "\',\'" + tid + "\',this);")
					        .replace("{$delete$}", "uploadHandler.deleteFile(\'" + vs[i] + "\',\'" + tid + "\',this);")
					        .replace("{$deltitle$}", "ɾ��ͼƬ");
                            //tbox.appendChild(thumb);
                        }
                    }
                    Img.onerror = function () {
                        i = i - 1;
                        var hid = document.getElementById(id);
                        //���������������е�ֵ
                        if (hid) {
                            var val = hid.value + ";";
                            val = val.replace(vs[i] + ";", "");
                            val = val.substr(0, val.length - 1);
                            hid.value = val;
                        }
                        $("#" + id + "_max_pic_img", box).attr("src", "");
                        $("#" + id + "_max_pic_img", box).attr("alt", "");
                        $("#Delete_Div").hide();
                    }
                }
                else {
                    var src =  vs[i];
                    //�ж�ͼƬ�Ƿ����
                    var Img = new Image;
                    
                    Img.src = preSrc + src;

                    Img.onload = function () {
                        src = this.src;
                        $("#" + id + "_max_pic_img", box).attr("src", preSrc + src);
                        var tbox = thmbs_box[0];
                        if (tbox) {
                            var thumb = document.createElement("div");
                            var tid = id + "_up_" + i;
                            thumb.id = 'thumbs_inner_' + tid;
                            thumb.className = "thumbs_inner";
                            thumb.innerHTML = uploadHandler["thumbHtml"]
					                        .replace("{$img$}", "<img src=\"" + preSrc + src + "\" alt=\"" + src + "\" />")
					                        .replace("{$method$}", "upload_Preview_Image('" + id + "','" + src + "',this);return false;")
					                        .replace("{$view$}", "uploadHandler.view(\'" + src + "\',\'" + tid + "\',this);")
					                        .replace("{$delete$}", "uploadHandler.deleteFile(\'" + src + "\',\'" + tid + "\',this," + c.funtname + ");")
					                        .replace("{$deltitle$}", "ɾ��ͼƬ");
                            tbox.appendChild(thumb);
                        }
                    }
                    Img.onerror = function () {
                    }
                }
            }
        }
    }
}

//�ϴ���������Ϣ
uploads.config = function(){
	//alert('');
}

uploads.fn.init.prototype = uploads.fn;
//window.unload = function () {
//}

//ͼƬ���������� 
function DrawImage(ImgID, w, h) {
    var flag = false;
    var image = new Image();
    var iwidth = w || ImgID.width;  //��������ͼƬ��� 
    var iheight = h || ImgID.height;  //��������ͼƬ�߶�
    //����ú�����img�ж�û���ÿ�͸�
    iwidth = iwidth == 0 ? 200 : iwidth;
    iheight = iheight == 0 ? 150 : iheight;

    image.src = ImgID.src;

    if (image.width > 0 && image.height > 0) {
        flag = true;
        if (image.width / image.height >= iwidth / iheight) {
            if (image.width > iwidth) {
                ImgID.width = iwidth;
                ImgID.height = (image.height * iwidth) / image.width;
            } else {
                ImgID.width = image.width;
                ImgID.height = image.height;
            }
        }
        else {
            if (image.height > iheight) {
                ImgID.height = iheight;
                ImgID.width = (image.width * iheight) / image.height;
            } else {
                ImgID.width = image.width;
                ImgID.height = image.height;
            }
        }
    }
}

function delShopLogo(imgScr, isSucceed) {
    if (isSucceed) {
        //alert(imgScr);
    }
}