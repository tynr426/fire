<%@page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <jsp:include page="../Meta.html"></jsp:include>
</head>
<body>
    <p>Socket信息</p> 
    <div id="socketList">

    </div>
    <script type="text/javascript">
/*         $e(function () {
            $e.ajax({
                data: "<action>sockets</action>",
                dataType: "xml",
                success: function () {
                    console.log(arguments[1].text);
                    $e("#socketList").html(arguments[1].text);
                }
            });
        }); */
    </script>
</body>
</html>