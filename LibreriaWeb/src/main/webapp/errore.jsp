<%@ page language="java" contentType="text/html; charset=ISO-8859-1 " pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<link rel="stylesheet" href="css//paginaErrore.css">

<meta charset="ISO-8859-1">
<title></title>
</head>
<body>

<h1> Benvenuti nella pagina di errore</h1>

<table>
<caption>
Riepilogo errore
</caption>

<tr>
<th scope="col">
errore generato
</th>
</tr>
<tr>
<td>
<label for="labelE"></label>
<input type="text" id="labelE" value="${bean1.getE()}">
</td>
<td>
<img alt="source not found" src="immagini/computer-icons-error-triangle.png" width=auto  height=100 >
</td>
</tr>
<tr>
<td>
<a href="index.html">
<button type="submit" id="buttonA" >
homePage
</button>
</a>
</table>


</body>
</html>