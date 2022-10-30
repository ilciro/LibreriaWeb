<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>insert user page</title>
</head>
<link rel="stylesheet" href="css//inserimentoNuovoUtente.css">
<body>

<h1>Inserire un nuovo utente</h1>

<form action="InserisciNuovoUtenteServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
inserire ruolo(W,A,E)
</td>
<td>
<label for="ruoloL"></label>
<input type="text" id="ruoloL" name="ruoloL">
</td>
</tr>
<tr>
<td>
nome
</td>
<td>
<label for="nomeL"></label>
<input type="text" id="nomeL" name="nomeL">
</td>
</tr>
<tr>
<td>
cognome
</td>
<td>
<label for="cognomeL"></label>
<input type="text" name="cognomeL" id="cognomeL">
</td>
</tr>
<tr>
<td>
email
</td>
<td>
<label for="emailL"></label>
<input type="text" id="emailL" name="emailL">
</td>
</tr>
<tr>
<td>
password
</td>
<td>
<label for="passL"></label>
<input type="password" id="passL" name="passL">
</td>
</tr>
<tr>
<td>
descrizione
</td>
<td>
<label for="descL"></label>
<input type="text" id="descL" name="descL">
</td>
</tr>
<tr>
<td>
data di nascita (yyyy/mm/dd)
</td>
<td>
<label for="dataL"></label>
<input type="text" id="dataL" name="dataL">
</td>
</tr>
<tr>
<td>
</td>
<td>
<input type="submit" id="buttonA" name="buttonA" value="inserirsci">
</td>
</tr>
</table>
</form>

<p>
${bean1.getE() }
</p>
</body>
</html>