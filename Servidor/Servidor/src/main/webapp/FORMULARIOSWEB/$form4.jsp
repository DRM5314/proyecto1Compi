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
		<title>Titulo del formulario</title>
	</head>
	<body>
		<form>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Nombre de cliente: " id="cliente"required></textarea>
				<label for="floatingTextarea">Nombre de cliente: </label>
			</div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Nombre del usuario: " id="usuario"></textarea>
				<label for="floatingTextarea">Nombre del usuario: </label>
			</div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Ingrese un comentario: " id="comentario" style="height: 325px;width: 700px" ></textarea>
				<label for="floatingTextarea2">Ingrese un comentario: </label>
			</div>
			<div class="form-floating">
				<textarea class="form-control" placeholder="Ingrese otro comentario: " id="comentario2" style="height: 325px;width: 700px" ></textarea>
				<label for="floatingTextarea2">Ingrese otro comentario: </label>
			</div>
			<div style="text-align: left"><label for="floatingInputValue">Escoja una opcion: * :  </label></div>
			<div style="text-align: left" class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
				<input type="checkbox" class="btn-check" id="op1" autocomplete="off">
				<label class="btn btn-outline-primary" for="op1">op1</label>

				<input type="checkbox" class="btn-check" id="op2" autocomplete="off">
				<label class="btn btn-outline-primary" for="op2">op2</label>

				<input type="checkbox" class="btn-check" id="op3" autocomplete="off">
				<label class="btn btn-outline-primary" for="op3">op3</label>

				<input type="checkbox" class="btn-check" id="op4" autocomplete="off">
				<label class="btn btn-outline-primary" for="op4">op4</label>

			</div>
			<div style="text-align: left"><label for="floatingInputValue">Escoja otra opcion: * :  </label></div>
			<div style="text-align: left" class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
				<input type="checkbox" class="btn-check" id="op1" autocomplete="off">
				<label class="btn btn-outline-primary" for="op1">op1</label>

				<input type="checkbox" class="btn-check" id="op2" autocomplete="off">
				<label class="btn btn-outline-primary" for="op2">op2</label>

			</div>
			<div style="text-align: left"><label for="floatingInputValue">Escoja una opcion: * :  </label></div>
			<div style="text-align: left" class="btn-group" role="group" aria-label="Basic radio toggle button group">
			<input type="radio" class="btn-check" name="-radio1" id="op1" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="op1"> op1 </label>
			<input type="radio" class="btn-check" name="-radio1" id="op2" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="op2"> op2 </label>
			<input type="radio" class="btn-check" name="-radio1" id="op3" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="op3"> op3 </label>
			<input type="radio" class="btn-check" name="-radio1" id="op4" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="op4"> op4 </label>
			</div>			<div style="text-align: center"><label for="floatingInputValue">Escoja otra opcion: * :  </label></div>
			<div style="text-align: center" class="btn-group" role="group" aria-label="Basic radio toggle button group">
			<input type="radio" class="btn-check" name="-radio2" id="op1" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="op1"> op1 </label>
			<input type="radio" class="btn-check" name="-radio2" id="op2" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="op2"> op2 </label>
			</div><div class="input-group mb-3">
  <input type="file" class="form-control" id="inputGroupFile01">
</div>			<img style="text-align: left" src="/WebFormBuilder/logoIntelaf.png" class="rounded mx-auto d-block" alt="La ruta de imagen es/WebFormBuilder/logoIntelaf.png">
			<div style="text-align: center"><label for="floatingInputValue">Se leccione algo perro:  :  </label></div>
			<select style="text-align: center" class="form-select" aria-label="Default select example">
					<option value="asdasd">asdasd</option>
					<option value="sdfdf">sdfdf</option>
					<option value="dsfsdfs">dsfsdfs</option>
					<option value="sdfsdfddfs">sdfsdfddfs</option>
			</select>
			<button type="button" class="btn btn-primary">Enviar datos</button>
		</form>
	</body>
</html>