<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../Meta.html"></jsp:include>

</head>
<body>
    <!--内容-->
    <div class="content-area">
        <!--面包屑-->
        <div class="content-title">详情</div>
        <!--//面包屑-->
        <!--内容盒-->
        <div class="custom-area" id="scroll-box">
            <!--内盒-->
            <div class="custom-box" scrollarea="box">
                <!--滚动用盒层-->
                <div class="custom-scroll" scrollroom="box" lazyscroll="box">
                    <!--修改配置的交互-->
                    <div class="weixinroom">
                        <!--步骤-->
                        <div class="step">
                            <!--第一步-->
                            <div class="line s1" id="config_step1">
                                <!--输入区域-->
                                <div class="operate" dynamic="true">

                                </div>
                                <!--//输入区域-->
                            </div>
                            <!--//第一步-->
                            <!--第二步-->
                            <div class="line s2" id="config_step2">
                                <!--输入区域-->
                                <div class="operate" dynamic="true">

                                </div>
                                <!--//输入区域-->
                            </div>
                            <!--//第二步-->
                        </div>
                        <!--//步骤-->
                    </div>
                    <!--//修改配置的交互-->
                </div>
                <!--//滚动用盒层-->
            </div>
            <!--//内盒-->
        </div>
        <!--//内容盒-->
    </div>
    <!--//内容-->
</body>
</html>
<jsp:include  page="../template/Company.html"></jsp:include>

<script language="javascript" type="text/javascript">
	companyManager.load(1);
</script>