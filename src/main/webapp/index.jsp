<%@page import="java.util.Date"%>
<%
	Date fecha = new Date();
	out.print( fecha );
%>


<%
	//Gestion de Mensajes
	if ( null != request.getAttribute("mensaje") ){
		out.print("<p style=\"color:red;\">");		
		out.print(request.getAttribute("mensaje"));
		out.print("</p>");
	}
%>

<form action="login" method="post">
	
	<input type="text" name="nombre" placeholder="Tu Nombre" required>
	<br>
	
	<input type="password" name="pass" placeholder="Contraseña" required>
	<br>

	<input type="submit" value="Login">

</form>

<br>


<a href="formularioInscripcion.jsp">Crear nuevo usuario</a>