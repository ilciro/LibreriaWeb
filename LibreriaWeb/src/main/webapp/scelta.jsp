<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %><!DOCTYPE html>
<html lang=en>
<head>
<meta charset="ISO-8859-1">
<title>Benvenuti nella schermata di scelta del pagamento</title>
<link rel="stylesheet" href="css//CssFile.css">
</head>
<body>
<h1> Riepilogo Transazione ... </h1>

<h2> Scegliere come pagare</h2>

<h3> Flaggare "negozio" per ritirare in negozio </h3>


<table>
<caption>Riepilogo pagamento</caption>

<tr>
<th scope="col">
importo 
</th>
<th scope="col">
 euro
</th>
<tr>

<td>
<label for="pagL">
importo
</label>
</td>
<td> 
${bean.getSpesaT() }
</td>
</tr>
</table>


<br>

<form action="ContantiServlet" method="get">
<table>
<caption>Pagamento cash</caption>
<tr>
<th scope="col"></th>
<tr>
<td>
<input type="submit" name="buttonCash" value="scegli">
</td>
<td>
<img alt="source not found" src="immagini/kisspng-euro-banknotes-money.jpg" width=100  height=100 >
</td>
</tr>
<tr>
<td>
<label for="negozioC"></label>
<input type="checkbox" id="negozioC" name="negozioC">
ritiro in negozio
</td>
<td>
<img alt="source not found" src="immagini/lovepik-shop-png-image_400246866_wh1200.png" width=100  height=100 >
</td>
</tr>
</table>
</form>

<br>

<form action="CCServlet" method="get">
<table>
<caption>Pagamento cc</caption>
<tr>
<th scope="col"></th>
<tr>

<td>
<input type="submit" name="buttonCC" value="scegli">
</td>
<td>
<img alt="source not found" src="immagini/c1143dcff9ad70b625b1b4b32bbc7a4e.jpg" width=100  height=100 >
</td>
</tr>
<tr>
<td>
<label for="negozioC1"></label>
<input type="checkbox" id="negozioC1" name="negozioC1">
ritiro in negozio
</td>
<td>
<img alt="source not found" src="immagini/lovepik-shop-png-image_400246866_wh1200.png" width=100  height=100 >
</td>
</tr>
</table>
</form>

<br>
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<a href="index.html">
<button>
indietro
</button>
</a>

</table>



</body>
</html>