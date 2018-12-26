<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数</title>
<!-- 加载Query文件-->
<script src="https://code.jquery.com/jquery-3.2.0.js"></script>
<!--
        此处插入JavaScript脚本
-->
<script type="text/javascript">
/*
 * 转化json视图
 */
	$(document).ready(function () {
	    $("#commit").click(function() {
	    	var str = $("form").serialize();
	    	//提交表单
	    	$.post({
	            url: "../role/showRoleJsonInfo.do",
	            //将form数据序列化，传递给后台，则将数据以roleName=xxx&&note=xxx传递
	            data: $("form").serialize(),
	            //成功后的方法
	            success: function (result) {
	            }
	        });
	    });
	});
</script>
</head>
<body>
	<form id="form">
		<table>
			<tr>
				<td>角色名称</td>
                <td><input id="roleName" name="roleName" value="" /></td>
				<!--
				<td><input id="role_name" name="role_name" value="" /></td>
				-->
			</tr>
			<tr>
				<td>备注</td>
				<td><input id="note" name="note" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><input type="commit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>