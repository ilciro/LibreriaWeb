<%@ page language="java" contentType="text/html; charset=ISO-8859-1 " pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang="en-it">
<head>
<meta charset="ISO-8859-1">
<title>user page</title>
<link rel="stylesheet" href="css//utenti.css">
</head>
<body>
<h1> Benvenuto nella gestione utente</h1>
<h2> possibile aggiungere/modificare/cancellare utente</h2>
<form action="UsersServlet" method="post">
<table class="table1">
<caption>
tabella1
</caption>
<tr>
<th scope="col">
id
</th>
<th>
idRuolo
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
desc
</th>
<th>
data nascita
</th>
</tr>


<c:forEach items="#{bean.listaDb}" var="lista">

<tr>

<td>${ lista.getId() }</td>
<td>${ lista.getR() }</td>
<td>${ lista.getNome() }</td>
<td>${ lista.getCognome() }</td>
<td>${ lista.getEmail() }</td>
<td>${ lista.getDescrizione() }</td>
<td>${ lista.getDataDiNascita()}</td>



</tr>
</c:forEach>

</table>

<table class="table1">
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
<input type="submit" id="buttonG" name="buttonG" value="genera lista">
</td>
</tr>
</table>

<table class="table2">
<caption>
tabella2
</caption>
<tr>
<th scope="col">
comando
</th>
<th> 
leggere id
</th>
</tr>
<tr>
<td>
<input type="submit" id="aggiungB" name="aggiungiB" value="aggiungi">
</td>
</tr>
<tr>
<td>
<input type="submit" id="modifB" name="modifB" value="modifica">
</td>
</tr>
<tr>
<td>
<input type="submit" id="cancB" name="cancB" value="cancella">
</td>
<td>
<label for="idU"></label>
<input type="text" id="idU" name="idU" value=0>
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
${ bean1.getE()}
</p>
</body>
</html>