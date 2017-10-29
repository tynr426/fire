<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
	<meta charset="utf-8" />
	<jsp:include page="../Meta.html"></jsp:include>
</head>
<body>

<!--内容-->
    <div class="content-area">
        <!--内容盒-->
        <div class="custom-area" id="scroll-box">
            <!--滚动用盒层-->
            <div class="custom-scroll" scrollroom="box">
                <!--图片库-->
                <div class="album">
                    <!--工具条-->
                    <div class="tool">
                        <!--左侧操作区域-->
                        <div class="operate">
                            <!--添加图片-->
                            <a href="javascript:void(0);" class="add-pic" id="upload">
                                <i class="iconfont icon-jia"></i>
                                <em>图片</em>
                            </a>
                            <!--//添加图片-->

                            <!--搜索-->
                            <div class="search">
                                <input type="text" id="Keyword" name="Keyword" value="" placeholder="您可搜索关键字图片" />
                                <a href="javascript:void(0);" class="btn" onclick="album.search();"></a>
                            </div>
                            <!--//搜索-->

                            <!--扩展按钮-->
                            <div class="extend-btn">
                                <ul>
                                    <li>
                                        <a href="javascript:void(0);" onclick="album.batchDel();" class="btn">删除</a>
                                    </li>
                                    <li >
                                        <a href="javascript:void(0);" onclick="album.batchMove();" class="btn">移动</a>
                                    </li>
                                </ul>
                            </div>
                            <!--//扩展按钮-->
                        </div>
                        <!--//左侧操作区域-->

                    </div>
                    <!--//工具条-->

                    <!--信息区域-->
                    <div class="message">
                        <!--面包屑-->
                        <div class="map">
                            <input type="hidden" id="Module" name="Module" value="<$var module />" />
                            <input type="hidden" id="IsPrdImg" name="IsPrdImg" value="<$var isprdimg />" />
                            <input type="hidden" id="FkId" name="FkId" value="<$var fkid />" />
                            <input type="hidden" id="FkFlag" name="FkFlag" value="<$var fkflag />" />
                            <input type="hidden" id="ParentId" name="ParentId" value="0" />
                            <input type="hidden" id="Layer" name="Layer" value="1" />
                            <a href="javascript:void(0);" class="btn" onclick="album.goBack();">返回上一级</a>
                            <a href="javascript:void(0);" class="btn" onclick="album.loadData(0);">所有文件</a>
                            <div class="map-link" id="Navigate"></div>
                        </div>
                        <!--//面包屑-->

                        <!--批量操作-->
                        <div class="batch">
                            <!--自定义复选按钮-->
                            <input type="checkbox" id="IsShowAlbum" name="" checked="checked" value="" onclick="album.loadData();" />
                            <label for="IsShowAlbum">是否显示相册</label>
                            <!--//自定义复选按钮-->
                        </div>
                        <!--//批量操作-->
                    </div>
                    <!--//信息区域-->

                    <!--所有内容-->
                    <div class="content">
                        <ul>
                            <li class="add">
                                <div class="addbtn fbg" onclick="if(ECF('#Layer').value() == '3'){top.pub.tips('新建相册最多两级！');return;};frm.add({ title: '新建相册', action:'insert', formId: 'AlbumFormTemplate', completed: album.showAlbum, completes: [''], width: 600, height: 180 });"></div>
                                <p class="name">添加相册</p>
                            </li>
                            <ol id="DataFrom"></ol>
                        </ul>
                    </div>
                    <!--//所有内容-->

                    <!--分页条-->
                    <di class="page">
                        <div class="page-box" id="PageBar">
                            <!--<div class="info">共<em id="ItemCount">0</em>条数据,当前第<em class="rm">1/1</em>页,每页<em class="rm">20</em>条记录</div>
                            <a class="first" href="javascript:;">首页</a>
                            <span class="now">1</span>
                            <a class="end" href="javascript:;" page="1">尾页</a>
                            <input type="text" id="#pageBody_IndexPage">
                            <a class="return">跳转</a>-->
                        </div>
                    </div>
                    <!--//分页条-->

                    <!--查看放大图-->
                    <div class="check-pic" style="display: none;" id="check-pic">
                        <div class="pic">
                            <img nolazy="0" alt="" />
                        </div>

                        <a href="javascript:void(0);" onclick="checkPic.close();" class="btn"></a>
                    </div>
                    <!--//查看放大图-->
                </div>
                <!--//图片库-->
            </div>
            <!--//滚动用盒层-->
        </div>
        <!--//内容盒-->
    </div>
    <!--//内容-->

</body>
</html>
<jsp:include page="../template/qrList.html"></jsp:include>
<script type="text/javascript">
$(function(){
	deviceQR.loadQRList();
 });
</script>