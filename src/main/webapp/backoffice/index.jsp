<h1>Estamos DENTRO del BACKOFFICE</h1>

<%
	//Gestion de Mensajes
	if ( null != request.getAttribute("mensaje") ){
		out.print("<hr><p>");		
		out.print(request.getAttribute("mensaje"));
		out.print("</p><hr>");
	}
%>

