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
	<form method="post" action="main?acao=login">
		<div class="erroDiv" style="display: ${msgErro != null ? 'block' : 'none'}">
			${msgErro != null ? msgErro : ''}
		</div>
		<fieldset id="fieldset_Login">
			<legend class="legend">Login do Sistema</legend>
			<div class="campo">
				<div class="label">
					<label for="Login">Login</label>
				</div>
				<input type="text" id="Login" name="login" maxlength="15" value="${param.login}"/>
			</div>

			<div class="campo">
				<div class="label">
					<label for="senha">Senha</label>
				</div>
				<input type="password" id="senha" name="senha" maxlength="15" value="${param.senha}" />
			</div>

			<div class="campo">
				<input type="submit" value="Logar" />
			</div>
			<div class="campo">
				<a href="">Esqueci a senha</a>
			</div>
		</fieldset>
	</form>
</body>
</html>