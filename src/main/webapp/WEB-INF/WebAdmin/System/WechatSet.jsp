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
        <div class="content-title">基础授权</div>
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
                                <!--提示区域-->
                                <div class="tips">用需要绑定企业公众账号登录微信公众平台<a href="http://mp.weixin.qq.com">http://mp.weixin.qq.com</a></div>
                                <!--//提示区域-->
                                <!--输入区域-->
                                <div class="operate" dynamic="true">

                                </div>
                                <!--//输入区域-->
                            </div>
                            <!--//第一步-->
                            <!--第二步-->
                            <div class="line s2" id="config_step2">
                                <!--提示区域-->
                                <div class="tips">选择开发者中心，点击服务器配置右侧按钮-修改配置</div>
                                <!--//提示区域-->
                                <!--反馈的Token信息-->
                                <div class="operate">
                                    <div class="custom-table" id="wechat_Config">
                                        <div class="form">
                                            <table id="MailForm" border="0" cellpadding="0" cellspacing="0">
                                                <colgroup>
                                                    <col style="width:120px;" />
                                                    <col style="width:auto;" />
                                                </colgroup>

                                                <tbody>
                                                    <tr>
                                                        <th>
                                                            <p class="name">
                                                                URL：
                                                            </p>
                                                        </th>
                                                        <td>
                                                            <div class="string">
                                                                <$var sitedomain />/Wechat.axd
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            <p class="name">
                                                                token：
                                                            </p>
                                                        </th>
                                                        <td>
                                                            <div class="string">
                                                                <$var account.token>IfNull:</$var>
                                                            </div>

                                                            <span class="tips">(token区分大小写，请正确填写token）</span>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            <p class="name">
                                                                EncodingAESKey：
                                                            </p>
                                                        </th>
                                                        <td>
                                                            <div class="string">
                                                                <$var account.encodingaeskey>IfNull:</$var>
                                                            </div>

                                                            <span class="tips">(EncodingAESKey区分大小写，消息加解密密钥,在配置时请选择兼容模式）</span>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!--//反馈的Token信息-->
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
<jsp:include  page="../template/wechatSet.html"></jsp:include>

<script language="javascript" type="text/javascript">
    wechat.set.show();
</script>