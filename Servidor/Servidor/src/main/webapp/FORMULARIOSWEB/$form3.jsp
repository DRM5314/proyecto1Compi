<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style type="text/css">
html {
	background-color: #FBFBFE;
	color: #697477
	}
	body {

		margin: 0 auto;
		background-color: #FBFBFE;
		padding: 0 20px 20px 20px;
		border: 5px solid black;
	}
	div{
		background-color: #d7d497;
		color: #76afd7;
	}
	label {
		margin: 0;
		padding: 20px 0;
		color: #070707;
	}
    </style>	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Formulario Modificado para encuesta 1</title>
	</head>
	<body>
		<div>
			<div style="text-align: right"><label for="floatingInputValue">Pais de Origen * :  </label></div>
			<div style="text-align: right" class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
				<input type="checkbox" class="btn-check" id="Guatemala" autocomplete="off">
				<label class="btn btn-outline-primary" for="Guatemala">Guatemala</label>

				<input type="checkbox" class="btn-check" id="El Salvador" autocomplete="off">
				<label class="btn btn-outline-primary" for="El Salvador">El Salvador</label>

				<input type="checkbox" class="btn-check" id="Honduras" autocomplete="off">
				<label class="btn btn-outline-primary" for="Honduras">Honduras</label>

				<input type="checkbox" class="btn-check" id="OTRO" autocomplete="off">
				<label class="btn btn-outline-primary" for="OTRO">OTRO</label>

			</div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Nombre de cliente es requerid0, :(*)" id="Cliente"required></textarea>
				<label for="floatingTextarea">Nombre de cliente es requerid0, :(*)</label>
			</div>
		</div>
	</body>
</html>