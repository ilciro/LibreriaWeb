<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title> Giornale singolo</title>
</head>
<link rel="stylesheet" href="css//CssFile.css">
<body>
<h1> Giornale Singolo</h1>

<table>
<caption>
</caption>
<tr>
<th scope="col">
titolo
</th>
<th>
tipologia
</th>
<th>
lingua
</th>
<th>
editore
</th>
<th>
data pubblicazione
</th>
<th>
copie rimanenti
</th>
<th>
disponibilita
</th>
<th>
prezzo
</th>
<th>
id
</th>
</tr>


<c:forEach items="#{bean.miaListaG }" var="lista">



<tr>

<td>${lista.getTitolo() }</td>
<td>${lista.getTipologia() }</td>
<td>${lista.getLingua() }</td>
<td>${lista.getEditore() }</td>
<td>${lista.getDataPubb() }</td>
<td>${lista.getCopieRimanenti() }</td>
<td>${lista.getDisponibilita() }</td>
<td>${lista.getPrezzo() }</td>
<td>${lista.getId() }</td>
</tr>

</c:forEach>


</table>

<br>
<form action="AcquistaServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<label for="idL" ></label>
</td>
<td>
<button type="submit" name="buttonI" value="buttonI">Inserire nr oggetto scelto</button>
<input type="text" name="idL" id="idL">
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

<tr>
<td>
<a href="index.html">
<button type="submit" id="buttonI">
home page
</button>
</a>

</td>
</tr>

</table>


</body>
</html>