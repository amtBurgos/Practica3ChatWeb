<%@page import="es.ubu.lsi.util.ChatClase"%>
<%@page import="java.lang.String"%>

<jsp:useBean id="server" scope="application"
	class="es.ubu.lsi.util.ChatClase" />
<jsp:useBean id="client" scope="session" class="java.lang.String" />

<%
	if (request.getParameter("nickName") != null) {
		client = request.getParameter("nickName");
		server.registraUsuario(client);
		session.setAttribute("client", client);
	} else if (client.equals("")) {
%>
<jsp:forward page="index.html" />
<%
	} else {
		client = (String) session.getAttribute("client");
		server = (ChatClase) application.getAttribute("server");
	}
%>

<html>
<head>
<title>Chat Room</title>
<script>
	//Forzar el refresco del Iframe que muestra los mensajes
	setInterval(refreshIframe, 5000); // establece el tiempo a 5 seg.
	function refreshIframe() { // recarga el iframe de la página
		frames[0].location.reload(true);
	}
</script>
</head>
<body>

	<h1>
		Chat Room -
		<%
		out.print(client);
	%>
	</h1>

	<form method="post" action="enviarMensaje.jsp">
		<div>
			<p>Mensaje:</p>
			<textarea rows="4" cols="50" name="mensajeEnviar"></textarea>

			<p>Usuario:</p>
			<input type="text" name="nicknameToBan"><br> <input
				type="radio" name="banAcction" value="ban">Bloquear<br>
			<input type="radio" name="banAcction" value="unban">Desbloquear<br>

			<input type="submit" value="Enviar">

			<%
				String mensaje = request.getParameter("mensajeEnviar");
				server.enviarMensaje(mensaje, client);
				//System.out.println("enviando mensaje: " + mensaje);

				application.setAttribute("servidor", server);
			%>

			<input type="reset" value="Borrar"> <a href="logout.jsp">Logout</a>
		</div>

		<p>Chat:</p>
		<iframe id="messageOutput" width="500" height="350" src="mensajes.jsp"></iframe>

	</form>


</body>
</html>