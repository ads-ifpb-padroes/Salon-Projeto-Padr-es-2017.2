<%-- 
    Document   : index
    Created on : 04/03/2018, 23:32:13
    Author     : ThigoYure
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
        <h2 align="center">Login</h2>
        <div class="container">
            <div class="row"> 
                <form action="FrontController?controller=LoginController" method="POST" enctype="multipart/form-data">
                    <h3>Email</h3>
                    <input type="email" name="email" required>
                    <h3>Senha</h3>
                    <input type="password" name="senha" required>
                    <button class="btn" type="submit">Entrar</button>
                </form>
                <h3>Não tem conta ainda? Clique <a href="cadastroCliente.jsp">aqui</a></h3>
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
