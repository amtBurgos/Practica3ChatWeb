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
	}
%>

<html class="htmlCenter">
<head>
<title>Chat Room</title>
<!--  Hoja de stilos css. -->
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script type="text/javascript" src="./js/funciones.js"></script>
<script>
	//Forzar el refresco del Iframe que muestra los mensajes
	setInterval(refreshIframe, 5000); // establece el tiempo a 5 seg.
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
		<div id="divGeneral">
			<div id="divMensaje" class="divIzquierda">
				<div>
					<p>Mensaje:</p>
					<textarea rows="4" cols="50" name="mensajeEnviar" autofocus></textarea>
				</div>

				<div class="divAbajo">
					<input type="reset" value="Borrar"> <input type="submit"
						value="Enviar">
					<%
						String mensaje = request.getParameter("mensajeEnviar");
						String usuarioToBan = request.getParameter("usuarioToBan");
						String banAction = request.getParameter("banAcction");

						if (mensaje != null && !mensaje.equals("") && !(banAction != null && usuarioToBan != null)) {
							System.out.println(">" + client + ": " + mensaje);
							server.enviarMensaje(mensaje, client);
						} else if (banAction != null && usuarioToBan != null) {
							if (banAction.equals("desbanear")) {
								server.unban(client, usuarioToBan);
							} else {
								server.ban(client, usuarioToBan);
							}
						}

						application.setAttribute("servidor", server);
					%>
				</div>
			</div>
			<div id="divUsuario" class="divDerecha">
				<p style="padding-top: 16;">Usuario:</p>
				<input type="text" name="usuarioToBan"> <br> <input
					type="radio" name="banAcction" value="banear">Bloquear<br>
				<input type="radio" name="banAcction" value="desbanear">Desbloquear<br>
				<br> <a href="logout.jsp" class="logout">Logout</a>
			</div>
		</div>
		<br>
		<div class="divMessageOutput">
			<p>Chat:</p>
			<iframe id="messageOutput" width="572" height="350"
				src="mensajes.jsp"></iframe>
		</div>
	</form>

</body>
</html>