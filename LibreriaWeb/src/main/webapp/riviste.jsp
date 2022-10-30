<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css//CssFile.css">

<title>Pagina riviste</title>
</head>
<body>

<h1> Elenco oggetti prenseti nel db</h1>

<table>
<caption>
elenco riviste
</caption>
<tr>
<th scope="col">
titolo
</th>
<th>
tipologia
</th>
<th>
autore
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
disp
</th>
<th>
prezzo
</th>
<th>
copie rimanenti
</th>
<th>
id
</th>
</tr>
<c:forEach items="#{bean.listaR }" var="lista">



<tr>

<td>${lista.getTitolo() }</td>
<td>${lista.getTipologia() }</td>
<td>${lista.getAutore() }</td>
<td>${lista.getLingua() }</td>
<td>${lista.getEditore() }</td>
<td>${lista.getDataPubb() }</td>
<td>${lista.getDisp() }</td>
<td>${lista.getPrezzo() }</td>
<td>${lista.getCopieRim() }</td>
<td>${lista.getId() }</td>
</tr>


</c:forEach>

</table>

<br>
<form action="AcquistaServlet" method="post">
<table>
<caption></caption>
<tr>
<th scope="col">
</th>
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
<form action="AcquistaServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<button type="submit" name="buttonG" value="buttonG">
genera lista
</button>
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