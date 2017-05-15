
<%
	if (request.getParameter("nickName").isEmpty() == true) {
%>

<%@ include file="index.html"%>

<%
	} else {
%>

<jsp:forward page="salaChat.jsp" />

<%
	}
%>