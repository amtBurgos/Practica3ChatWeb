<%@page import="java.util.List"%>
<%@page import="es.ubu.lsi.util.ChatClase"%>
<%
	String client = (String) session.getAttribute("client");
	ChatClase server = (ChatClase) application.getAttribute("server");
	List<String> messages = server.getMensajes(client);
	for (String message : messages) {
		out.print("<p>" + message + "</p>");
	}
%>