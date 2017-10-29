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
              

                    <!--所有内容-->
                    <div class="content">
                        <ul>
                            <ol id="DataFrom"></ol>
                        </ul>
                    </div>
                    <!--//所有内容-->

                    <!--分页条-->
                    <di class="page">
                        <div class="page-box" id="PageBar">
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
	//window.print();
 });
</script>