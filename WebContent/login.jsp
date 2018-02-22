<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/global.css" />
<title>Login</title>
</head>
<body>
	<form method="post" >
		<fieldset id="fieldset_Login">
		<legend>Login do Sistema</legend>
			<div class="campo">
				<label for="Login"></label>
				 <input type="text" id="Login" name="Login" maxlength="15" />
			</div>

			<div class="campo">
				<label for="senha"></label> 
				<input type="password" id="senha" name="senha"  maxlength="15" />
			</div>

			<div class="campo">
				<input type="submit" value="Logar" />
			</div>
		</fieldset>
	</form>
</body>
</html>