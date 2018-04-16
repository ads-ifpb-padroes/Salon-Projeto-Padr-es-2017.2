<%-- 
    Document   : avaliacao
    Created on : 15/04/2018, 15:08:22
    Author     : Ricarte
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
        <h2 align="center">Avaliação de atendimento</h2>
        <div class="container">
            <div class="row"> 
                <form action="FrontController?controller=AvaliarController" method="POST">
                    <h3>Tempo de Espera</h3>
                    <input type="number" min="1" max="10" name="tempoAtendimento" required>
                    <h3>Qualidade do Atendimento</h3>
                    <input type="number" min="1" max="10" name="qualidadeAtendimento" required>
                    <h3>Ambiente do Estabelecimento</h3>
                    <input type="number" min="1" max="10" name="qualidadeEstabelecimento" required>
                    <h3>Qualidade no Serviço Prestado</h3>
                    <input type="number" min="1" max="10" name="qualidadeServico" required>
                    <input type="hidden" name="atendimento" value="${param.atendimento}">
                    <button class="btn" type="submit">Avaliar</button>
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

