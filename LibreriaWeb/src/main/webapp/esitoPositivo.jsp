<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>esito postivo</title>
</head>
<link href="css//esitoPositivo.css" rel="stylesheet" type="text/css">

<body>

<h1> ecco i dati</h1>

<table>
<caption>
Riepilogo
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
sig. ${bean.getNome()} hai effetuato il pagamento
</td>
<td>
di euro ${bean1.getSpesaT()}
<td>
</tr>
<tr>
<td>
titolo libro:
</td>
<td>
${bean2.getTitolo() }
</td>
</tr>
</table>

<br>
<br>

<form action="DownloadServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<input type="submit" id="buttonD" value="download">
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
<th scope="col"></th>
</tr>
<tr>
<td>
<a href="annullaOrdine.jsp">
<button >
annulla ordine
</button>
</a>
</td>
</tr>
</table>


</body>
</html>