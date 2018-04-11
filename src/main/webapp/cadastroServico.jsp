<%-- 
    Document   : cadastroServiço
    Created on : 11/04/2018, 12:19:54
    Author     : alexalins
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2 align="center">Cadastro de Serviço</h2>
        <div class="container">
            <div class="row"> 
                <form action="FrontController?controller=CadastroServicoController" method="POST">
                    <input type="text" name="nome" value="${param.nome}">
                    <input type="time" name="tempoMedio" value="${param.tempoMedio}">
                    <input type="number" name="preco" value="${param.preco}">
                    <button class="btn" type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
    </body>
</html>
