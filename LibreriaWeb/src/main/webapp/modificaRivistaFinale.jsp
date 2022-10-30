<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>pagine per modificare rivista</title>
<link href="css//modifR.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>inserire i campi da modificare</h1>



<table>
<caption>Riepilogo rivista</caption>
<tr>
<th>
titolo
</th>
<th>
tipo
</th>
<th>
autore
</th>
<th>
lingua
</th>
<th>
editore
</th>
<th>
descrizione
</th>
<th>
DataPubblicazione
</th>
<th>
Disponibilita
</th>
<th>
prezzo
</th>
<th>
Copie Rimanenti
</th>
<th>
idProdotto
</th>
</tr>

<c:forEach items="#{bean.listaR}" var="lista">

<tr>

<td>${ lista.getTitolo() }</td>
<td>${ lista.getTipologia() }</td>
<td>${ lista.getAutore() }</td>
<td>${ lista.getLingua() }</td>
<td>${ lista.getEditore() }</td>
<td>${ lista.getDescrizione() }</td>
<td>${ lista.getDataPubb()}</td>
<td>${ lista.getDisp()}</td>
<td>${ lista.getPrezzo() }</td>
<td>${ lista.getCopieRim() }</td>
<td>${ lista.getId()}</td>
</tr>
</c:forEach>
</table>

<br>
<br>
<form action="ModificaRivistaServletFinale" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<c:forEach items="#{bean.listaR}" var="lista">

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
tipo
</td>
<td>
<label for="tipoA"></label>
<input type="text" id="tipoA" name="tipoA" value="${lista.getTipologia() }">
</td>
</tr>
<tr>
<td>
autore
</td>
<td>
<label for="autA"></label>
<input type="text" id="autA" name="autA" value="${lista.getAutore()}">
</td>
</tr>
<tr>
<td>
lingua
</td>
<td>
<label for="lA"></label>
<input type="text" id="lA" name="lA" value="${lista.getLingua() }">
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
descrizione
</td>
<td>
<label for="descA"></label>
<input type="text" id="descA" name="descA" value="${lista.getDescrizione() }">
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
disp (0->no 1->si)
</td>
<td>
<label for="dispA"></label>
<input type="text" id="dispA" name="dispA" value="${lista.getDisp() }">
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
<tr>
<td>
copie rimanenti
</td>
<td>
<label for="copA"></label>
<input type="text" id="copA" name="copA" value="${lista.getCopieRim() }">
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