<%-- 
    Document   : cadastroAgenda
    Created on : 14/04/2018, 21:37:59
    Author     : ThigoYure
--%>

<%@taglib prefix="MyTags" uri="/tlds/MyTags" %>
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
        <div class="container">
            <div class="row">
                <div class="col s12">
                    <form action="FrontController?controller=CriarAgendaController" method="POST">
                        <select name="atendente">
                            <option value="" disabled selected>Choose your option</option>
                            <MyTags:BuscaAtendentes/>
                            <c:choose>
                                <c:when test="${empty Atendentes}">
                                    <h2>Não existem Atendentes cadastrados.</h2>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="atendente" items="${Atendentes}">
                                        <option value="${atendente.nome}">${atendente.nome}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <label>Atendente</label>
                        <select name="servico">
                            <option value="" disabled selected>Choose your option</option>
                            <MyTags:BuscaServicos/>
                            <c:choose>
                                <c:when test="${empty Servicos}">
                                    <h2>Não existem serviços cadastrados.</h2>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="servico" items="${Servicos}">
                                        <option value="${servico.nome}">
                                        <b>Serviço:</b>${servico.nome}
                                        </option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <label>Serviço</label>
                        <button class="btn" type="submit">Pesquisar</button>
                    </form>
                </div>
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
