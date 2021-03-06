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
                <MyTags:BuscaServicosAtendente atendente="${param.atendente}"/>
                <c:choose>
                    <c:when test="${empty HorariosPorAtendente}">
                        <h2>Não existem serviços cadastrados para esse atendente ou não foi selecionado nenhum serviço ainda.</h2>
                    </c:when>
                    <c:otherwise>
                        <form action="agendamentoDeHorarioAdminAtendente.jsp?atendente=${param.atendente}&data=${param.data}" method="POST">
                            <select name="servico">
                                <option value="" disabled selected>Choose your option</option>
                                <c:forEach var="servicos" items="${HorariosPorAtendente}">
                                    <option value="${servicos.nome}">
                                        ${servicos.nome}
                                    </option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="data" value="${param.data}">
                            <input type="hidden" name="atendente" value="${param.atendente}">
                            <button class="btn" type="submit">Pesquisar</button>
                        </form>
                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty param.atendente and not empty param.servico}">
                    <MyTags:BuscaPorAtendente atendente="${param.atendente}" data="${param.data}" servico="${param.servico}"/>
                    <c:choose>
                        <c:when test="${not empty AtendimentoPorAtendente}">
                            <form action="FrontController?controller=AgendarAtendimentoControllerAdmin" method="POST">
                                <select name="opcao">
                                    <option value="" disabled selected>Choose your option</option>    
                                <c:forEach var="opcoes" items="${AtendimentoPorAtendente}">
                                    <option value="${opcoes.id}&${opcoes.horaInicio}&${opcoes.horaFim}">
                                        <b>Horário:</b>${opcoes.horaInicio}/${opcoes.horaFim}
                                    </option>
                                </c:forEach>
                                </select>
                                <div class="input-field col s12">
                                    <select name="cliente">
                                        <option value="" disabled selected>Choose your option</option>
                                        <MyTags:BuscaUsuarios/>
                                        <c:choose>
                                            <c:when test="${empty Usuarios}">
                                                <h2>Não existem usuários cadastrados.</h2>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach var="usuario" items="${Usuarios}">
                                                    <option value="${usuario.email}">${usuario.nome}</option>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                    <label>Cliente</label>
                                </div>
                                <input type="hidden" name="data" value="${param.data}">
                                <input type="hidden" name="servico" value="${param.servico}">
                                <button class="btn" type="submit">Agendar</button>
                            </form> 
                        </c:when>
                    </c:choose> 
                </c:if>

            </div>
        </div>
            <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
            <script type="text/javascript" src="js/initialize.js"></script>
    </body>
</html>