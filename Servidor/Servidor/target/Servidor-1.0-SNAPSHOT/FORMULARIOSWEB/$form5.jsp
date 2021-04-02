<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style type="text/css">
 html {
            background-color: #010101;
            color: #aaaaaa;
        }

        body {
            color: #aaaaaa;
            margin: 0 auto;
            background-color: #aaaaaa;            
            padding: 0 20px 20px 20px;
            border: 5px solid black;
        }     
        div{
            background-color: #444c7b;
            color: #eaeff5;
        }
        select{
            color: #9ea0ab;
            background-color: #444c7b;
        }
        label {
            margin: 0;
            padding: 20px 0;
            color: #1083D6;
        }
            </style>	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Formulario 1</title>
	</head>
	<body>
		<div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Nombre de cliente: " id="Cliente"></textarea>
				<label for="floatingTextarea">Nombre de cliente: </label>
			</div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Comentario del cliente: " id="Cliente" style="height: 50px;width: 100px" required></textarea>
				<label for="floatingTextarea2">Comentario del cliente: </label>
			</div>
			<div style="text-align: center"><label for="floatingInputValue">Pais cliente: * :  </label></div>
			<div style="text-align: center" class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
				<input type="checkbox" class="btn-check" id="Guatemala" autocomplete="off">
				<label class="btn btn-outline-primary" for="Guatemala">Guatemala</label>

				<input type="checkbox" class="btn-check" id="El Salvador" autocomplete="off">
				<label class="btn btn-outline-primary" for="El Salvador">El Salvador</label>

				<input type="checkbox" class="btn-check" id="Honduras" autocomplete="off">
				<label class="btn btn-outline-primary" for="Honduras">Honduras</label>

				<input type="checkbox" class="btn-check" id="OTRO" autocomplete="off">
				<label class="btn btn-outline-primary" for="OTRO">OTRO</label>

			</div>
			<div style="text-align: center"><label for="floatingInputValue">Sexo cliente: * :  </label></div>
			<div style="text-align: center" class="btn-group" role="group" aria-label="Basic radio toggle button group">
			<input type="radio" class="btn-check" name="$_sexoCliente" id="Masculino" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="Masculino"> Masculino </label>
			<input type="radio" class="btn-check" name="$_sexoCliente" id="Femenino" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="Femenino"> Femenino </label>
			</div><div class="input-group mb-3">
  <input type="file" class="form-control" id="inputGroupFile01">
</div>			<img style="text-align: center" src="user/usuario/fotos" class="rounded mx-auto d-block" alt="La ruta de imagen esuser/usuario/fotos">
			<div style="text-align: center"><label for="floatingInputValue">Departamento vive: * :  </label></div>
			<select style="text-align: center" class="form-select" aria-label="Default select example">
					<option value="xela">xela</option>
					<option value="mazate">mazate</option>
					<option value="escuintla">escuintla</option>
					<option value="OTRO">OTRO</option>
			</select>
			<button type="button" class="btn btn-primary">botonEnvio</button>
		</div>
	</body>
</html>