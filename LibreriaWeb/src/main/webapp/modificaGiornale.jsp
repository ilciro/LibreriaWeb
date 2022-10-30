<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>gestione giornale page</title>
</head>
<link rel="stylesheet" href="css//modificaGiornale.css">

<body>

<h1> Possibile modificare un singolo giornale</h1>

<form action="ModificaGiornaleServlet" method="post">
<table>
<caption>
elenco giornali
</caption>
<tr>
<th scope="col">
titolo
</th>
<th>
tipologia
</th>
<th>
editore
</th>
<th>
data pubblicazione
</th>
<th>
disponibilita
</th>
<th>
prezzo
</th>
<th>
id prodotto
</th>
</tr>
<c:forEach items="#{bean.miaListaG }" var="lista">

<tr>


<td>${ lista.getTitolo() }</td>
<td>${ lista.getTipologia() }</td>
<td>${ lista.getEditore() }</td>
<td>${ lista.getDataPubb() }</td>
<td>${ lista.getDisponibilita()}</td>
<td>${ lista.getPrezzo() }</td>
<td>${ lista.getId()}</td>

</tr>
</c:forEach>

</table>

<br>
<br>


<table>
<caption></caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<input type="submit" name="generaB" id="generaB" value="genera lista">
</td>
</tr>
</table>
</form>


<br>
<form action="ModificaGiornaleServlet" method="post" >
<table class="modifica" >
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
<input type="submit" name="buttonA" id="buttonA" value="aggiungi">
</td>
</tr>
<tr>
<td>
<input type="submit" name="buttonM" id="buttonM" value="modifica">
</td>
<td>
<label for="idL"></label>
<input type="text" id="idL" name="idL">
</td>
</tr>
<tr>
<td>
<input type="submit" name="buttonCanc" id="buttonCanc" value="cancella">
</td>
<td>
<label for="cancL"></label>
<input type="text" id="cancL" name="cancL">
</td>
</tr>
<tr>
<td>
<input type="submit" value="indietro" id="buttonB" name="buttonB">
</td>
</tr>
</table>
</form>

<p>
${bean2.getE() }
</p>
</body>
</html>