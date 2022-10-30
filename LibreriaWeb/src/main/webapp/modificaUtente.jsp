<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>modif user page</title>
</head>
<link rel="stylesheet" href="css//modificaUtente.css">
<body>

<h1>Modifica utente</h1>


<table>
<caption>
</caption>
<tr>
<th scope="col">
ruolo
</th>
<th>
nome
</th>
<th>
cognome
</th>
<th>
email
</th>
<th>
descrizione
</th>
<th>
dataNascita
</th>
</tr>
<c:forEach items="#{bean3.listaDb}" var="lista">

<tr>


<td>${ lista.getR() }</td>
<td>${ lista.getNome() }</td>
<td>${ lista.getCognome() }</td>
<td>${ lista.getEmail() }</td>
<td>${ lista.getDescrizione()}</td>
<td>${ lista.getDataDiNascita() }</td>


</tr>

</c:forEach>

</table>

<form action="ModificaUtenteFinaleServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<c:forEach items="#{bean3.listaDb}" var="lista">
<tr>
<td>
ruolo (U,W,E,A)
</td>
<td>
<label for="ruoloL"></label>
<input type="text" id="ruoloL" name="ruoloL" value="${lista.getR() }">
</td>
</tr>
<tr>
<td>
nome
</td>
<td>
<label for="nomeL"></label>
<input type="text" id="nomeL" name="nomeL" value="${lista.getNome() }">
</td>
</tr>
<tr>
<td>
cognome
</td>
<td>
<label for="cognomeL"></label>
<input type="text" id="cognomeL" name="cognomeL" value="${lista.getCognome() }">
</td>
</tr>
<tr>
<td>
email
</td>
<td>
<label for="mailL"></label>
<input type="text" id="mailL" name="mailL" value="${lista.getEmail() }">
</td>
</tr>
<tr>
<td>
password
</td>
<td>
<label for="passL"></label>
<input type="password" id="passL" name="passL" value ="${lista.getPassword() }">
</td>
</tr>
<tr>
<td>
descrizione
</td>
<td>
<label for="descL"></label>
<input type="text" id="descL" name="descL" value="${lista.getDescrizione() }">
</td>
</tr>
<td>
data nascita (yyyy/mm/dd)
</td>
<td>
<label for="dataL"></label>
<input type="text" id="dataL" name="dataL" value="${lista.getDataDiNascita()}">
</c:forEach>

<tr>
<td>
<input type="submit" id="generaB" name="generaB" value="genera">
</td>
</tr>
<tr>
<td>
<input type="submit" id="modB" name="modB" value="modifica">
</td>
</tr>
<tr>
<td>
<input type="submit" id="indB" name="indB" value="indietro">
</td>
</tr>
</table>









</form>


<p>
${bean1.getE() }
</p>

</body>
</html>