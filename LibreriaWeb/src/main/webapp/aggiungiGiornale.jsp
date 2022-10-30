<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>insert new book page</title>
</head>
<link rel="stylesheet" href="css//aggiungiGiornale.css">

<body>
<h1>Inserire un nuovo giornale</h1>
<form action ="InserisciGiornaleServlet" method="post">
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
<label for="titoloG"></label>
<input type="text" id="titoloG" name="titoloG">
</td>
</tr>
<tr>
<td>
tipologia
</td>
<td>
<label for="tipoG"></label>
<input type="text" id="tipoG" name="tipoG">
</td>
</tr>
<tr>
<td>
lingua
</td>
<td>
<label for="linguaG"></label>
<input type="text" id="linguaG" name="linguaG">
</td>
</tr>
<tr>
<td>
editore
</td>
<td>
<label for="editoreG"></label>
<input type="text" id="editoreG" name="editoreG">
</td>
</tr>
<tr>
<td>
data pubb (yyyy/mm/dd)
</td>
<td>
<label for="dataG"></label>
<input type="text" id="dataG" name="dataG">
</td>
</tr>
<tr>
<td>
copie rimanenti
</td>
<td>
<label for="copieG"></label>
<input type="text" id="copieG" name="copieG"> 
</td>
</tr>
<tr>
<td>
disponibilita (0->no 1->si)
</td>
<td>
<label for="dispG"></label>
<input type="text" id="dispG" name="dispG">
</td>
</tr>
<tr>
<td>
prezzo
</td>
<td>
<label for="prezzoG"></label>
<input type="text" id="prezzoG" name="prezzoG">
</td>
</tr>
</table>
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