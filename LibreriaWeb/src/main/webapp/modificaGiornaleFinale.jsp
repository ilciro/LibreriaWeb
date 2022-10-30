<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>pagine per modificare giornale</title>
<link href="css//modifG.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>inserire i campi da modificare</h1>



<table>
<caption>Riepilogo Giornale</caption>
<tr>
<th>
Titolo
</th>
<th>
Tipologia
</th>
<th>
Lingua
</th>
<th>
Editore
</th>
<th>
Data Pubblicazione
</th>
<th>
Copie Rimanenti
</th>
<th>
Disponibilita
</th>
<th>
Prezzo
</th>
<th>
idProdotto
</th>
</tr>

<c:forEach items="#{bean.miaListaG}" var="lista">

<tr>

<td>${ lista.getTitolo() }</td>
<td>${ lista.getTipologia() }</td>
<td>${ lista.getLingua() }</td>
<td>${ lista.getEditore() }</td>
<td>${ lista.getDataPubb()}</td>
<td>${ lista.getCopieRimanenti() }</td>
<td>${ lista.getDisponibilita() }</td>
<td>${ lista.getPrezzo() }</td>
<td>${ lista.getId()}</td>
</tr>
</c:forEach>
</table>

<br>
<br>
<form action="ModificaGiornaleServletFinale" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<c:forEach items="#{bean.miaListaG}" var="lista">

<tr>
<td>
titolo
</td>
<td>
<label for="titoloA"></label>
<input type="text" id="titoloA" name="titoloA" value="${lista.getTitolo() }">
</td>
</tr>
<tr>
<td>
tipologia
</td>
<td>
<label for="tipA"></label>
<input type="text" id="tipA" name="tipA" value="${lista.getTipologia() }">
</td>
</tr>
<tr>
<td>
lingua
</td>
<td>
<label for="linguaA"></label>
<input type="text" id="linguaA" name="linguaA "value="${lista.getLingua()}">
</td>
</tr>
<tr>
<td>
editore
</td>
<td>
<label for="edA"></label>
<input type="text" id="edA" name="edA" value="${lista.getEditore() }">
</td>
</tr>
<tr>
<td>
data pubblicazione (yyyy/mm/dd) USARE IL '/'
</td>
<td>
<label for="dataA"></label>
<input type="text" id="dataA" name="dataA" value="${lista.getDataPubb() }">
</td>
</tr>
<tr>
<td>
copie rim
</td>
<td>
<label for="copieA"></label>
<input type="text" id="copieA" name="copieA" value="${lista.getCopieRimanenti() }">
</td>
</tr>
<tr>
<td>
disp (0->no 1->si)
</td>
<td>
<label for="dispA"></label>
<input type="text" id="dispA" name="dispA" value="${lista.getDisponibilita() }">
</td>
</tr>
<tr>
<td>
prezzo
</td>
<td>
<label for="pA"></label>
<input type="text" id="pA" name="pA" value="${lista.getPrezzo() }">
</td>
</tr>
</c:forEach>
</table>


<br>


<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<input type="submit" name="buttonGenera" id="buttonGenera" value="genera">
</td>
</tr>
</table>
<br>
<br>


<br>
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<input type="submit" id="modifB" name="modifB" value="modifica">
</td>
</tr>
</table>
<br>
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<input type="submit" id="indietroB" name="indietroB" value="indietro">
</td>
</tr>
</table>


</form>

<p>
${bean1.getE() }
</p>
</body>
</html>