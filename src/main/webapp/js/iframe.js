var frame = {

    currentLink: null,

    currentLinkBox: null,

    // 椤甸潰璺宠浆
    //鑿滃崟鍦板潃璺宠浆
    go: function (link, o, target) {

        if (target == "_blank") {
            window.open(link);
        }
        var o = $(o);
        var ifr = $("#contentIframe");

        if (frame.currentLinkBox) {
            frame.currentLinkBox.removeClass("select");
        }

        if (o.length > 0) {
            frame.currentLinkBox = o.parent("li");
            o.parent("li").addClass("select");
        }

        var body = $("body");
        // 鍒ゆ柇鏄惁涓篿e鎴杅ireforx娴忚鍣�
//        if (ECF.browsers.msie || ECF.browsers.firefox) {
//            body == $('body,html');
//        }
        body.animate({ scrollTop: 0 }, 500);

        window.scrollTo(0, 0);

        if (link != "" && link != 'undefined') {
            //缁欓摼鎺ュ湴鍧�鍔犱笂闅忔満鏁颁互杈惧埌鍒锋柊鐨勬晥鏋�
            link = link + (link.indexOf("?") > 0 ? '&' : '?') + 'rnd=' + Math.random().toString();

            //alert(link);
            ifr[0].src =  link;

            ifr[0].onload = function () {

                // 澶勭悊鑷姩閫傚簲楂樺害
                frame.dynSize("contentIframe");

                ifr[0].onload = null;

                setTimeout(function () {

                }, 0);
            };
        }
    },

    // iframe鑷姩閫傚簲楂樺害
    dynSize: function (iframeId, noInit) {
        return;
        var browserVersion = window.navigator.userAgent.toUpperCase();

        var iframe = document.getElementById(iframeId);
        var bHeight = 0;

        if (!noInit) {
            iframe.style.height = "auto";
        }

        if (browserVersion.indexOf("CHROME") == -1 && browserVersion.indexOf("SAFARI") == -1)
            bHeight = iframe.contentWindow.document.body.scrollHeight;

        var dHeight = 0;
        if (browserVersion.indexOf("FIREFOX") != -1)
            dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
        else if (browserVersion.indexOf("MSIE") == -1 && browserVersion.indexOf("OPERA") == -1)
            dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        else {
            bHeight = bHeight + 3;
        }

        var height = Math.max(bHeight, dHeight);

        if (height < 530) height = 530;

        iframe.style.height = height + "px";

    },

    // 鑷姩璁＄畻iframe鐨勯珮搴�
    autoSize: function (init) {
        if (init == undefined) {
            init = true;
        }
        frame.dynSize("contentIframe", init);
    },

    // 鍔犺浇椤甸潰鑿滃崟
    loadMenu: function () {
        console.log(11);
        $("#MenuList").loadList("MenuTemplate", null, "menu.list", null, null, "/Store.axd");
    }

};

//鑿滃崟
var menuAction = function () {
    var my = {
        //涓讳綋妗嗘灦
        obj: null,
        //涓讳綋妗嗘灦闂悎鐘舵��
        objActive: '',
        //鑿滃崟妗嗘灦
        menu: null,
        //鑿滃崟妗嗘灦闂悎鐘舵��
        menuActive: '',
        //鍖哄煙鍐呭
        area: null,
        //瀛愰泦鍒楄〃
        list: null,
        //瀛愰泦鍒楄〃闀垮害
        max: 0,
        //瀛愰泦鍒楄〃鍗曚釜楂樺害
        listHeight: 0,
        //鏄剧ず灞�
        showPanel: null,
        //鏁翠釜鏄剧ず灞傚垪琛�
        showList: null,
        //灞曞紑鏀剁缉瀵硅薄
        targetBtn: null,
        //cookie瀵硅薄
        cookieid: 'webadmin_menu_target',
        //涓讳綋鍏抽棴鏍峰紡
        objClose: 'box-close',
        //涓讳綋灞曞紑鏍峰紡
        objOpen: 'box-open',
        //鏄剧ず灞傚叧闂牱寮�
        menuClose: 'menu-close',
        //鏄剧ず灞傚睍寮�鏍峰紡
        menuOpen: 'menu-open',
        //缂撳瓨涓存椂灞曞紑鐨勫垪琛�
        showListNow: null,
        //缂撳瓨鍦╟ookie鐨処Frame鍊�
        cookielinkid: 'webadmin_menu_url',
        //鍒楄〃灞曞紑&鍏抽棴閫熷害(鍗曚綅:姣)
        speed: 200,
        //璁板綍涓�绾х洰褰曞綋鍓嶇偣鍑诲璞＄殑缂撳瓨
        level_1_btn_cache: null,
        //璁板綍浜岀骇鐩綍褰撳墠鐐瑰嚮瀵硅薄鐨勭紦瀛�
        level_2_btn_cache: null
    };

    if (my.obj == null) {
        my.obj = $('*[salemenu=box]');
    }

    if (my.menu == null) {
        my.menu = $('*[salemenu=menu]');
    }

    if (my.area == null) {
        my.area = $('*[salemenu=area]');
    }

    //鍒ゅ畾涓绘鏋剁殑瀛樺湪
    if (my.obj == null && my.menu == null && my.area == null) return;

    //鏍规嵁cookie鑾峰彇灞曞紑鍏抽棴鐘舵��
    var cookie = getCookie(my.cookieid);//$.cookie.get(my.cookieid);
    if (cookie==null||typeof (cookie) == 'undefined') {
        my.obj.removeClass(my.objClose).addClass(my.objOpen);
        my.menu.removeClass(my.menuClose).addClass(my.menuOpen);
        my.objActive = my.objOpen;
        my.menuActive = my.menuOpen;
        addCookie(my.cookieid, my.menuOpen);
    } else {
        if (cookie == my.menuOpen) {
            my.obj.removeClass(my.objClose).addClass(my.objOpen);
            my.menu.removeClass(my.menuClose).addClass(my.menuOpen);
            my.objActive = my.objOpen;
            my.menuActive = my.menuOpen;
        } else {
            my.obj.removeClass(my.objOpen).addClass(my.objClose);
            my.menu.removeClass(my.menuOpen).addClass(my.menuClose);
            my.objActive = my.objClose;
            my.menuActive = my.menuClose;
        }
    }

    //cookie鑾峰彇褰撳墠鐐瑰嚮鍚庣殑瀛樺偍鍊�
    //var cookielink = $.cookie.get(my.cookielinkid);
    //if (typeof (cookielink) == 'undefined') {
    //    var iFrame = $('#contentIframe');
    //    if (iFrame.length <= 0) return;
    //    var sr = iFrame.attr('src').toString();

    //    $.cookie.set(my.cookielinkid, sr);
    //}

    //鐐瑰嚮鍚庢洿鎹rl鍊�
    var targetUrl = function (sr) {
        //$.cookie.set(my.cookielinkid, sr.match(/\([^\)]+\)/g)[0].replace('(\'', '').replace('\')', ''));
    	addCookie(my.cookielinkid, sr);
    };

    //妫�鏌ヨ彍鍗曟爮鐨勯棴鍚堟儏鍐�
    var targetObj = function () {
        if (my.obj.hasClass(my.objOpen) && my.menu.hasClass(my.menuOpen)) {
            my.obj.removeClass(my.objOpen).addClass(my.objClose);
            my.menu.removeClass(my.menuOpen).addClass(my.menuClose);
            my.objActive = my.objClose;
            my.menuActive = my.menuClose;
            $.cookie.set(my.cookieid, my.menuClose);
        } else {
            my.obj.removeClass(my.objClose).addClass(my.objOpen);
            my.menu.removeClass(my.menuClose).addClass(my.menuOpen);
            my.objActive = my.objOpen;
            my.menuActive = my.menuOpen;
            $.cookie.set(my.cookieid, my.menuOpen);
        }

        //鍒锋柊婊氬姩缁勪欢
        if ($.iscroll) {
            $.iscroll.refresh();
        }
    };

    //鑾峰彇瀛愰泦鍒楄〃
    if (my.list == null) {
        my.list = $('*[salemenu=list]', my.menu[0]);
        //鑾峰彇瀛愰泦鍒楄〃鎬婚暱搴�
        my.max = my.list.length;
        // var liSize = $(my.list[0]).size();
        // console.log(liSize);
        //瀛愰泦鍒楄〃楂樺害
        my.listHeight = $(my.list[0]).height();
    }

    //鑾峰彇鏄剧ず灞�
    if (my.showPanel == null) {
        my.showPanel = $('*[salemenu=showbox]');
    }

    //鑾峰彇鏄剧ず灞傛墍鏈夊垪琛�
    if (my.showList == null) {
        my.showList = $('*[salemenu=showlist]');
    }

    //鑾峰彇灞曞紑鏀剁缉瀵硅薄
    if (my.targetBtn == null) {
        my.targetBtn = $('*[salemenu=sliderbar]');
    }

    //闂悎鍚岀骇鍏冪礌
    var removeStyle = function () {
        var i = my.max;
        while (--i >= 0) {
            var _this = my.list[i];
            //鍏堝洖鍒板師鏉ョ殑楂樺害
            $(_this).animate({
                'height': my.listHeight + 'px'
            }, 300, function () {
                $(_this).removeClass('select');

                //鍒锋柊婊氬姩缁勪欢
                if ($.iscroll) {
                    $.iscroll.refresh();
                }
            });
        }
    };

    //鐐瑰嚮瀵硅薄鍔犱笂閫変腑
    var addStyle = function (obj) {
        var show = $('*[salemenu=showbox]', obj);
        var show_hei = parseInt(show[0].getAttribute('salemenu_height'));

        $(obj).addClass('select');

        $(obj).animate({
            'height': Number(show_hei + my.listHeight) + 'px'
        }, 300, function () {
            //鍒锋柊婊氬姩缁勪欢
            if ($.iscroll) {
                $.iscroll.refresh();
            }
        });
    };

    //鍙栨秷鎵�鏈夋樉绀哄瓙闆嗙殑鏍峰紡
    var removeShowListStyle = function () {
        var max = my.showList.length;
        var i = max;

        while (--i >= 0) {
            $(my.showList[i]).removeClass('select');
        }
    };

    //鍐掓场鎵剧埗绾�
    var findParent = function (obj, name) {
        var _obj = obj;
        while (_obj) {
            if (_obj.hasAttribute('salemenu') && _obj.getAttribute('salemenu') == name) {
                return _obj;
            }
            _obj = _obj.parentNode;
        }
    };

    //鏄剧ず灞傚垵濮嬪寲
    var showListInit = function () {
        //涓�绾х洰褰曞垵濮嬪寲
        if (my.list.length <= 0) { return; }

        my.list.each(function () {
        	
            var $this = $(this),
            height = $this.height();
            $this.attr('normal_height', height + 'px');

            //浜岀骇鐩綍鍒濆鍖�
            if (my.showPanel.length <= 0) { return; }

            if (my.objActive != 'box-close' && my.menuActive != 'menu-close') {
                my.showPanel.each(function () {
                    //鑾峰彇浜岀骇鐩綍
                    var showList = $('*[salemenu=showlist]', this);
                    var showListLength = showList.length;

                    //鑾峰彇骞惰绠楀厓绱犵殑楂樺害
                    var showListHeight = Number($(showList[0]).height() * showListLength);

                    //璁剧疆浜岀骇鐩綍楂樺害
                    $(this).attr('normal_height', showListHeight);

                    //鑾峰彇涓夌骇鐩綍
                    showList.each(function () {
                        //璁剧疆灏哄
                        $(this).attr('normal_height', $(this).height());

                        var level = $('*[salemenu-level=box]', this);

                        level.each(function () {
                            var level_list = $('*[salemenu-level-list=list]', this);
                            if (level_list.length > 0) {
                                //璁＄畻涓夌骇鐩綍楂樺害
                                var level_height = Number(70 * level_list.length);
                              
                                //璁剧疆鐩綍楂樺害
                                $(this).attr('normal_height', level_height);
                            }
                        });
                    });
                });
            }
        });
    };
    showListInit();

    //缁戝畾瀛愰泦浜嬩欢
    var bindList = function () {
        //涓�绾х洰褰曠偣鍑讳簨浠�
        if (my.list.length <= 0) { return; }

        my.list.each(function () {
            var level_1_btn = $('a.name', this);

            var levle_1_box = this;

            //涓�绾х洰褰曠偣鍑讳簨浠�
            if (level_1_btn.length > 0) {
                level_1_btn.bind('click', function () {
                    //鍙湁鍦ㄥ睍寮�鐘舵�佷笅鎵嶈兘琚偣鍑�
                    if (my.objActive == 'box-open' && my.menuActive == 'menu-open') {
                        //濡傛灉娌℃湁灞曞紑灏辫缃睍寮�鐘舵��,鍚﹀垯瑕佽缃叧闂姸鎬�
                        if ($(levle_1_box).hasClass('select')) {
                            //鍏抽棴

                            //鍥炴敹閫変腑鏍峰紡
                            $(levle_1_box).removeClass('select');
                        } else {
                            //灞曞紑

                            //鍒ゆ柇鏄笉鏄偣鍑绘柊鐨勬寜閽�
                            if (my.level_1_btn_cache === null) {
                                my.level_1_btn_cache = this;

                                $(levle_1_box).addClass('select');
                            } else if (my.level_1_btn_cache !== null && my.level_1_btn_cache !== this) {
                                my.level_1_btn_cache = this;

                                //鍙栨秷鍚岀骇閫変腑
                                my.list.each(function () {
                                    if ($(this).hasClass('select')) {
                                        //鍥炴敹閫変腑鏍峰紡
                                        $(this).removeClass('select');
                                    }
                                });

                                $(levle_1_box).addClass('select');
                            } else if (my.level_1_btn_cache !== null && my.level_1_btn_cache === this) {
                                $(levle_1_box).addClass('select');
                            }
                        }

                        //iScroll鍒锋柊
                        if ($.iscroll) {
                            $.iscroll.refresh();
                        }
                    }
                });
            }
        });

        //浜岀骇鐩綍鐐瑰嚮浜嬩欢
        var level_2_btn = $('a.level-2-btn');

        if (level_2_btn.length > 0) {
            level_2_btn.each(function () {
                $(this).bind('click', function () {
                    var par = this.parentNode.parentNode;

                    //濡傛灉娌℃湁灞曞紑灏辫缃睍寮�鐘舵��,鍚﹀垯瑕佽缃叧闂姸鎬�
                    if ($(par).hasClass('select')) {
                        //鍏抽棴

                        //鍥炴敹閫変腑鏍峰紡
                        $(par).removeClass('select');
                    } else {
                        //灞曞紑

                        //鍒ゆ柇鏄笉鏄偣鍑绘柊鐨勬寜閽�
                        if (my.level_2_btn_cache === null) {
                            my.level_2_btn_cache = par;

                            $(par).addClass('select');
                        } else if (my.level_2_btn_cache !== null && my.level_2_btn_cache !== par) {
                            my.level_2_btn_cache = par;

                            //鍙栨秷鍚岀骇閫変腑
                            $('*[salemenu=showlist]').each(function () {
                                //濡傛灉鐖剁骇鍏冪礌鍙湁涓�涓瓙闆�,閭ｄ箞灏变笉鍙栨秷閫変腑浜�
                                if($('*[salemenu=showlist]' ,this.parentNode).length > 1){
                                    if ($(this).hasClass('select')) {
                                        //鍥炴敹閫変腑鏍峰紡
                                        $(this).removeClass('select');
                                    }
                                }
                            });

                            $(par).addClass('select');
                        } else if (my.level_2_btn_cache !== null && my.level_2_btn_cache === par) {
                            $(par).addClass('select');
                        }
                    }

                    //鍒锋柊婊氬姩缁勪欢
                    if ($.iscroll) {
                        $.iscroll.refresh();
                    }
                });
            });
        }

        //涓夌骇鐩綍鐐瑰嚮浜嬩欢
        var level_3_btn = $('a.level-3-btn');

        if (level_3_btn.length > 0) {
            level_3_btn.each(function () {
                $(this).bind('click', function () {
                    var par = this.parentNode;

                    var level_list = $('*[salemenu-level-list=list]');
                    level_list.each(function () {
                        if ($(this).hasClass('select')) {
                            $(this).removeClass('select');
                        }
                    });

                    $(par).addClass('select');

                    //鑾峰彇鐐瑰嚮鐨勫��
                    var sr = $(this).attr('savename');

                    //璁板綍鍊�
                    if (typeof (sr) == 'string' && sr != '') {
                        targetUrl(sr);
                    }
                });
            });
        }
    };
    bindList();

    //鑾峰彇IFrame瀵硅薄鐨勫湴鍧�
    var getiFrame = function () {
        var sr =getCookie(my.cookielinkid);

        if (typeof (sr) == 'undefined') return;

        var links = '';

        var obj = null;

        var list = $('a.level-3-btn');
        list.each(function () {
            var str = this.getAttribute('savename');
            if (sr === str) {
                obj = this;

                var clink = this.href.toString().match(/\([^\)]+\)/g);

                if (clink != null) {
                    links = clink[0].replace('(\'', '').replace('\')', '');
                }
            }
        });

        //鐖剁骇
        var listParent = $(obj).parent();

        //鎵惧埌鏄剧ず灞�
        var showSpan = findParent(obj, 'showlist');

        //鎵惧埌鏍圭骇
        var grandParent = findParent(obj, 'list');

        //鍏ㄩ儴鍔犱笂閫変腑鏍囪
        $(listParent).addClass('select');

        $(showSpan).addClass('select');

        $(grandParent).addClass('select');

        my.level_1_btn_cache = grandParent;

        my.level_2_btn_cache = showSpan;
        
        //鍒锋柊婊氬姩缁勪欢
        if ($.iscroll) {
            $.iscroll.refresh();
        }

        frame.go(links);
    };
    getiFrame();
};

var cssStyle={
		 //杩斿洖闇�瑕佽幏鍙朇SS灏哄鏍峰紡鐨勯珮搴�
        cssHeight: function () {
            if (this.length < 1) return 0;
            var obj = this[0];
            var size = ['height', 'margin-top', 'margin-bottom', 'padding-top', 'padding-bottom', 'border-top-width', 'border-bottom-width'];
            var i = size.length;
            var o = 0;
            while (--i >= 0) {
                o = o + getCssSize(obj, size[i]);
            };
            return o;
        },

        //鑾峰彇CSS鏍峰紡涓殑灏哄鍊�
        cssSize: function (obj, style) {
            var si = 0;
            var _obj = [];
            if (obj.style[style]) {
                var regx = /([\S]+)px/ig;
                var sizer = regx.exec(obj.style[style])[0];
                if (!sizer) {
                    sizer = '0px';
                };
            } else if (obj.currentStyle) {
                try {
                    var regx = /([\S]+)px/ig;
                    var sizer = regx.exec(obj.currentStyle[style])[0];
                    if (!sizer) {
                        sizer = '0px';
                    };
                } catch (e) {
                    sizer = '0px';
                };
            } else if (window.getComputedStyle) {
                try {
                    propprop = style.replace(/([A-Z])/g, "-$1");
                    propprop = style.toLowerCase();
                    var sizer = document.defaultView.getComputedStyle(obj, null);
                    if (sizer) {
                        var regx = /([\S]+)px/ig;
                        sizer = regx.exec(sizer.getPropertyValue(style))[0];
                    } else {
                        sizer = '0px';
                    };
                } catch (e) {
                    sizer = '0px';
                };
            };
            if (sizer === 'auto' || sizer === '0px') {
                si = 0;
            } else {
                si = Number(sizer.replace('px', ''));
            };
            return si;
        },
        //杩斿洖闇�瑕佽幏鍙朇SS灏哄鏍峰紡鐨勫搴�
        cssWidthSize: function (obj) {
            if (!tools.check(obj)) return;
            var _obj = $e(obj);
            var size = ['width', 'margin-left', 'margin-right', 'padding-left', 'padding-right', 'border-left-width', 'border-right-width'];
            var i = size.length;
            var o = 0;
            while (--i >= 0) {
                o = o + tools.cssSize(_obj[0], size[i]);
            };
            return o;
        },
        //杩斿洖闇�瑕佽幏鍙朇SS灏哄鏍峰紡鐨勯珮搴�
        cssHeightSize: function (obj) {
            if (!tools.check(obj)) return;
            var _obj = $e(obj);
            var size = ['height', 'margin-top', 'margin-bottom', 'padding-top', 'padding-bottom', 'border-top-width', 'border-bottom-width'];
            var i = size.length;
            var o = 0;
            while (--i >= 0) {
                o = o + tools.cssSize(_obj[0], size[i]);
            };
            return o;
        }
}
