<h1>Formulario Inscripcion</h1>

<form action="registro" method="post">

	<input type="text" name="nombre" required placeholder="Tu Nombre" />
	<br>
	<input id="inputEmail" type="email" name="email" required placeholder="Tu Email" />
	<br>
	<input type="password" name="pass" required placeholder="Contrase�a" />
	<br>
	<input type="password" name="repass" required placeholder="Repite Contrase�a" />

	<br>
	<input type="submit" value="Crear">

</form>

<script src="https://code.jquery.com/jquery-3.1.0.min.js"   integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="   crossorigin="anonymous"></script>
<script>

	//Nuestro JavaScript para realizar llamada Ajax
	$(function() {
	    console.log( "ready!" );
	    
	    //detectar click en input email
	    $( "#inputEmail" ).keyup(function( event ) {
	    	//console.log( "tecla pulsada" );
	    	//obtener valor del input
	    	var emailValue = $(this).val();
	    	console.debug(emailValue);
	    		    	
	    	//lamada Ajax al servidor
	    	// CUIDADO llamada asincrona
	    	$.ajax({	    		
	    		  method: "GET",
	    		  url: "checkemail",
	    		  data: { email: emailValue }
	    	
	    		}).done(function( data ) {
	    			if ( data.encontrado ){
	    		    	console.debug("Existe email");
	    		    	$("#inputEmail").css('color', 'green');
	    			}else{
	    				console.debug("NO Existe email");
	    				$("#inputEmail").css('color', 'red');
	    			}	
	    		});
	    	
	    	//Las siguientes lineas se procesan seguidas
	    	
	    });
	    
	    
	    
	});
	
	
	
</script>







