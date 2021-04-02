<%-- 
    Document   : inicioUsuario
    Created on : 28/03/2021, 12:38:08 AM
    Author     : david
--%>
<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        .sinDatos{
            text-align: center;
        }
    </style>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion links</title>
    </head>
    <body>
        <h1 style="text-align: center">Bienvenido ${usuario}</h1>
        <a href="${pageContext.request.contextPath}/salida">
            <button>
                Cerrar Sesion
            </button>
        </a>
        <j:if test="${!empty(sinFormularios)}">
            <div class="alert alert-warning" role="alert">                
                <h3 class="sinDatos">${sinFormularios}</h3>
            </div>            
        </j:if>
        <j:if test="${!empty(formularios)}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Fecha creacion</th>
                        <th scope="col">Tema</th>
                    </tr>
                </thead>
                <j:forEach var="f" items="${formularios}" varStatus="contador">                
                    <tbody>
                        <tr>
                            <th scope="row">${contador.count}</th>
                            <td>${f.id}</td>                            
                            <td>${f.nombre}</td>
                            <td>${f.fechaCreacion}</td>
                            <td>${f.tema}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/generadorLink?id=${f.id}">
                                    <button type="button" class="btn btn-dark">Obtener link</button>
                                </a>
                                <a href="${pageContext.request.contextPath}/exportar?id=${f.id}">
                                    <button type="button" class="btn btn-dark">
                                        Exportar
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </tbody>                
                </j:forEach>
            </table>
        </j:if>
        <j:if test="${!empty(idExportado)}">
            <div style="text-align: center"class="alert alert-success" role="alert">
                Formulario exportado con exito ${idExportado} 
                <a href="${pageContext.request.contextPath}/Formulario/${idExportado}" download="${idExportado}">Descargue aqui</a>
            </div>                
        </j:if>


        <j:if test="${!empty(link)}">
            <div class="alert alert-success" role="alert">
                El link lo puede encontrar en:  <a href="${link}" class="alert-link">${link}</a> puede compartirlo ahora!.
            </div>
        </j:if>        
    </body>
</html>
