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
                    <div class="input-field">
                        <h3>Nome do Serviço</h3>
                        <input name="nome" type="text">
                    </div>
                    <div class="input-field">
                        <select name="tempoMedio">
                            <option value="" disabled selected>Choose your option</option>
                            <option value="30">30 min</option>
                            <option value="60">60 min</option>
                        </select>
                        <label>Tempo Médio</label>
                    </div>
                    <div class="input-field">
                        <h3>preço</h3>
                        <input name="preco" type="text">
                    </div>
                    <button class="btn" type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('select').formSelect();
            });
        </script>
    </body>
</html>
