<%-- 
    Document   : Avaliacoes
    Created on : 15/04/2018, 20:08:26
    Author     : Ricarte
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
                    <ul class="tabs">
                        <li class="tab col s6"><a class="active" href="#AvaliacaoTotal">Avaliações totais</a></li>
                        <li class="tab col s6"><a href="#AvaliacaoAtendente">Avaliações Por Atendente</a></li></li>
                    </ul>
                </div>
                <div id="AvaliacaoTotal" class="col s12">
                    <MyTags:BuscaAvaliacao/>
                    <c:choose>
                        <c:when test="${empty tempAtendTot and empty qualiAtendTot and empty qualiAmbiTot and empty qualiServTot}">
                            Não existem avaliações computadas.
                        </c:when>
                        <c:otherwise>
                            <ul class="collection">
                                <li class="colletion-item">
                                    Tempo de Atendimento: ${tempAtendTot}</br>
                                    Qualidade de Atendimento: ${qualiAtendTot}</br>
                                    Qualidade do Ambiente: ${qualiAmbiTot}</br>
                                    Qualidade do Serviço: ${qualiServTot}</br>
                                    <div class="divider"></div>
                                </li>
                            </ul> 
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="AvaliacaoAtendente" class="col s12">
                    <form action="Avaliacoes.jsp">
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
                        <label>Busca por atendente</label>
                        <button class="btn" type="submit">Pesquisar</button>
                    </form>
                    <c:choose>
                        <c:when test="${not empty param.atendente}">
                            <MyTags:BuscaAvaliacaoAtendente atendente="${param.atendente}"/>
                            <ul class="collection">
                                <li class="colletion-item">
                                    Tempo de Atendimento: ${tempAtend}</br>
                                    Qualidade de Atendimento: ${qualiAtend}</br>
                                    Qualidade do Ambiente: ${qualiAmbi}</br>
                                    Qualidade do Serviço: ${qualiServ}</br>
                                    <div class="divider"></div>
                                </li>
                            </ul> 
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script type="text/javascript" src="js/initialize.js"></script>
    </body>
</html>
