<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>schermata per annullare ordine</title>
</head>
<link rel="stylesheet" href="css//annullaOrdine.css">

<body>
<h1>
benvenuto nella schermata per annullare ordine
</h1>


<form action="MostraPagamentoServlet" method="post">
<table>
<caption></caption>
<tr>
<th scope="col"></th>
<tr>
<tr>
<td>
<label for="uL" ></label>
<button type="submit" name="buttonI" value="buttonI">Leggere pagamenti di utente :</button>
<input type="text" name="uL" id="uL">
</td>
</tr>
</table>
</form>


<br>
<br>
<table>
<caption>
</caption>
<tr>
<th scope="col">
nome
</th>
<th scope="col">
id operazione
</th>
<th scope="col">
spesa totale
</th>
<th scope="col">
</th>

<c:forEach items="#{bean1.pagamentiList}" var="lista">
<tr>
<td>${lista.getNomeUtente()}</td>
<td>${lista.getId() }</td>
<td>${lista.getAmmontare() }</td>
</tr>
</c:forEach>
</table>

<br>
<h1> Scegli pagamento da annullare</h1>

<form action="CancellaPagamentoServlet" method="post">
<table>
<caption></caption>
<tr>
<th scope="col"></th>
<tr>
<tr>
<td>
<label for="idCanc" ></label>
<button type="submit" name="buttonCanc" value="buttonCanc">Leggere id pagamento da cancellare :</button>
<input type="text" name="idCanc" id="idCanc">
</td>
</tr>
</table>
</form>






</body>
</html>