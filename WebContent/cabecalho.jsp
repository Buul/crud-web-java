<div class="cabecalho">
	<div class="logo">
		<a href="index.jsp"> <img alt="Logo Java EE"
			src="img/javaee-642x276.png" />
		</a>
	</div>
	<ul>
		<li><a href="main?acao=index" class="${param.acao eq 'index' ? 'selected': ''}">Home</a></li>
		<li><a href="main?acao=cadastros" class="${param.acao eq 'cadastros' ? 'selected': ''}" >Cadastros</a></li>
		<li><a href="main?acao=consultas" class="${param.acao eq 'consultas' ? 'selected': ''}">Consultas</a></li>
		<li><a href="main?acao=logout" class="${param.acao eq 'logout' ? 'selected': ''}">Sair</a></li>
	</ul>
	<div class="boasVindas">
		Bem-vindo(a), <b style="color: gray">${sessionScope.usuario.usuario}</b>!
	</div>
</div>