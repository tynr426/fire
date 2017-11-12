/*����checkboxѡ�����*/
var check = {
    checkAll: function (control, id, parentDivId, selfId) {
        var childBox;
        try {
            childBox = document.getElementById(id).getElementsByTagName("input");
        } catch (e) {
            return;
        }

        var count = childBox.length;
        var parentCheckAll = document.getElementById(parentDivId);

        if (control.checked) {
            parentCheckAll.checked = true;

            //��ǰ��ѡ��״̬
            for (var i = 0; i < count; i++) {
                childBox[i].checked = true;
            }
        } else {
            //������input��ѡ��
            parentCheckAll.checked = false;

            //��ǰδ��ѡ��״̬
            for (var i = 0; i < count; i++) {
                childBox[i].checked = false;
            }

            if (document.getElementById(selfId)) {
                var selfBox = document.getElementById(selfId).getElementsByTagName("input");
                for (var i = 0; i < selfBox.length; i++) {
                    if (selfBox[i].checked) {
                        parentCheckAll.checked = true;
                        break;
                    } else {
                        //������input��ѡ��
                        parentCheckAll.checked = false;
                    }
                }
            }
        }

        //�Զ��嵥ѡ��ѡ
        if (typeof ($().customCheck) == 'function') {
            $("#MenuList").customCheck();
        }
    },

    checkChildBox: function (controlid, divid, parentDivId, selfId) {
        var childBox = document.getElementById(divid).getElementsByTagName("input");
        var count = childBox.length - 1;
        var temp = 0;
        var checkAll = document.getElementById(controlid);
        if (divid != "AccountList" && divid != "PointList") {
            var parentCheckAll = document.getElementById(parentDivId);
            for (var i = 0; i < count + 1; i++) {
                if (childBox[i].checked) {
                    temp += 1;
                }
            }
            if (temp > 0) {
                checkAll.checked = true;
                parentCheckAll.checked = true;
                //����һ����ʽ
                //alert("����һ����ʽ");
                //�Ƴ���ʽ
                if (temp == count) {
                    //alert("�Ƴ���ʽ");
                }
            } else {
                checkAll.checked = false;
            }

            if (document.getElementById(selfId)) {
                var selfBox = document.getElementById(selfId).getElementsByTagName("input");
                for (var i = 0; i < selfBox.length; i++) {
                    if (selfBox[i].checked) {
                        parentCheckAll.checked = true;
                        break;
                    } else {
                        //����㽫����input��ѡ��
                        parentCheckAll.checked = false;
                    }
                }
            }
        } else {
            for (var i = 1; i < count + 1; i++) {
                if (childBox[i].checked) {
                    temp += 1;
                }
            }
            if (temp == count) {
                checkAll.checked = true;
            } else {
                checkAll.checked = false;
            }
        }

        //�Զ��嵥ѡ��ѡ
        if (typeof ($().customCheck) == 'function') {
            $("#MenuList").customCheck();
        }
    },

    //��ȡ��ѡ���ı����ֵ
    getValue: function (control) {
        var checkBox = $("#" + control)[0].getElementsByTagName("input");
        var val = "";
        var index;
        if (control != "AccountList" && control != "PointList") {
            index = 0;
        } else {
            index = 1;
        }
        for (var i = index; i < checkBox.length; i++) {
            if (checkBox[i].checked) {
                val += checkBox[i].value + ",";
            }
        }

        if (val.length == 0) {
            return val;
        } else {
            return val.substring(0, val.length - 1);
        }
    },
    /*��ȡ��ѡ����ı��򣨰����뿪��յģ�*/
    getTreeValue: function (control) {
        var container = document.getElementById(control);
        if (container == null || container == undefined) {
            container = top.document.getElementById(control);
        }
        var checkBox = container.getElementsByTagName("input");
        var val = "";
        var index;
        if (control != "AccountList" && control != "PointList") {
            index = 0;
        }
        else {
            index = 1;
        }
        for (var i = index; i < checkBox.length; i++) {
            if (checkBox[i].checked || checkBox[i].parentNode.className.indexOf("half-checked") > -1) {
                val += checkBox[i].value + ",";
            }
        }

        if (val.length == 0) {
            return val;
        }
        else {
            return val.substring(0, val.length - 1);
        }
    },
    //�˵�չ��������
    openMenu: function (control, id) {
        var div = $("#divChild_" + id);
        if (control.innerHTML.indexOf("open") > -1) {
            control.innerHTML = '<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAItJREFUeNpivHDhwrNnzxhwAykpKYZt27b9xwuACliAaiEcrMYwMjICSZCif//+EVYEVMHCwoKp4tevX0xMTAiTgGTocnR1iwI+Qxggib9//2K168+fPwhFQJM+fPgw2/MNmiKgZhTrcBnGzMyMUARxFiaAiIMUAb2GKwggvmYBhvqhQ4fwRwtAgAEALOhk9G+G4QcAAAAASUVORK5CYII=\" alt=\"\" nolazy=\"0\" tips=\"close\" />';
            div.show();
        } else {
            control.innerHTML = '<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAJtJREFUeNpivHDhwrNnzxhwAykpKYZt27b9xwuACliAaoEsBrwApOjfv39wfsQqdiC5IuwnuiJMk9BEoCZ9+/YtdbsIXDRyNQeQXBTwmZmZGaro79+/yDbCwZ8/f1BMApo/2/MNkA0xD8IGamZiYkIoQjMJKA1hIKyDqICQ01yeI/sXwgApYmFhwRVUQCkQCQz1Q4cO4Y8WgAADAHdzc/QUjmKTAAAAAElFTkSuQmCC\" nolazy=\"0\" alt=\"\" tips=\"open\" />';
            div.hide();
        }
    }
};
