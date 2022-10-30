<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>insert new rivista page</title>
</head>
<link rel="stylesheet" href="css//aggiungiLibro.css">

<body>
<h1>Inserire una nuova rivista</h1>
<form action ="InserisciRivistaServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col">
elemento
</th>
<th>
valore
</th>
</tr>
<tr>
<td>
titolo
</td>
<td>
<label for="titoloL"></label>
<input type="text" id="titoloL" name="titoloL">
</td>
</tr>
<tr>
<td>
tipologia
</td>
<td>
<label for="catR"></label>
<textarea  cols=25 id="catR" name="catR">
${bean.getListaCategorie()}
</textarea>
</td>
<td>
<input type="submit" id="generaL" name="generaL" value="prendi lista">
</td>
<td>
<label for="catS"></label>
<input type="text" id="catS" name="catS">
</td>
</tr>
<tr>
<td>
autore
</td>
<td>
<label for="autL"></label>
<input type="text" id="autL" name="autL">
</td>
</tr>
<tr>
<td>
lingua
</td>
<td>
<label for="linguaL"></label>
<input type="text" id="linguaL" name="linguaL">
</td>
</tr>
<tr>
<td>
editore
</td>
<td>
<label for="editoreL"></label>
<input type="text" id="editoreL" name="editoreL">
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
data pubb (yyyy/mm/dd)
</td>
<td>
<label for="dataL"></label>
<input type="text" id="dataL" name="dataL">
</td>
</tr>
<tr>
<td>
disponibilita (flaggare per renderlo disponibile)
</td>
<td>
<label for="checkL">
</label>
<input type="checkbox" id="checkL" name="checkL">
</td>
</tr>
<tr>
<td>
prezzo
</td>
<td>
<label for="prezzoL"></label>
<input type="text" id="prezzoL" name="prezzoL">
</td>
</tr>
<tr>
<td>
copie rimanenti
</td>
<td>
<label for="copieL"></label>
<input type="text" id="copieL" name="copieL">
</td>
</tr>
</table>
<br>
<br>
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
<tr>
<td>
<input type="submit" id="confermaB" name="confermaB" value="conferma">
</td>
</tr>
<tr>
<td>
<input type="submit" id="annullaB" name="annullaB" value="indietro">
</td>
</tr>
</table>
</form>
<p>
${ bean1.getE()}
</p>

</body>
</html>