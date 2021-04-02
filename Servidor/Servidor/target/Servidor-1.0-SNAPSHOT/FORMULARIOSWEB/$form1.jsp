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
		<title>Formulario para encuesta 1</title>
	</head>
	<body>
		<div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Nombre de cliente " id="Cliente"required></textarea>
				<label for="floatingTextarea">Nombre de cliente </label>
			</div>
		</div>
	</body>
</html>