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
                <MyTags:BuscaPorServico servico="${param.servico}" data="${param.data}"/>
                <c:choose>
                    <c:when test="${not empty AtendimentoPorServico}">
                        <form action="FrontController?controller=AgendarAtendimentoControllerCliente" method="POST">
                            <select name="opcao">
                                <option value="" disabled selected>Choose your option</option>
                                <c:forEach var="opcoes" items="${AtendimentoPorServico}">
                                    <option value="${opcoes.id}&${opcoes.horaInicio}&${opcoes.horaFim}">
                                        <b>Atendente:</b>${opcoes.atendente.nome}</br>
                                        <b>Horário:</b>${opcoes.horaInicio}/${opcoes.horaFim}
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="cliente" value="${user.email}">
                            <input type="hidden" name="data" value="${param.data}">
                            <input type="hidden" name="servico" value="${param.servico}">
                            <button class="btn" type="submit">Agendar</button>
                        </form> 
                    </c:when>
                </c:choose>
            </div>
            <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
            <script type="text/javascript" src="js/initialize.js"></script>
    </body>
</html>