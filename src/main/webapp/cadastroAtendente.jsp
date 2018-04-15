<%-- 
    Document   : cadastroAtendente
    Created on : 11/04/2018, 12:05:08
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
        <h2 align="center">Cadastro de Atendente</h2>
        <div class="container">
            <div class="row"> 
                <form action="FrontController?controller=CadastroAtendenteController" method="POST">
                    <h3>nome</h3>
                    <input type="text" name="nome">
                    <h3>Horário de Entrada</h3>
                    <input type="text" name="horaInicio">
                    <h3>Horário de Saída</h3>
                    <input type="text" name="horaFim">
                    <button class="btn" type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/initialize.js"/>
        <script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>
    </body>
</html>
