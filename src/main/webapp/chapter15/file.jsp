<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>文件上传</title>
    </head>
    <body>
        <form method="post" action="../file/upload.do" enctype="multipart/form-data" >
            <input type="file" name="file" value="请选择上传的文件"/>
            <input type="submit" value="提交"/>     
        </form>
    </body>
</html>