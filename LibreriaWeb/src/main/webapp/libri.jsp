<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang=en>
<head>
<link rel="stylesheet" href="css//CssFile.css">
<meta charset="ISO-8859-1">
<title>Pagina libri</title>
</head>
<body>

<h1> Elenco oggetti prenseti nel db</h1>

<table>
<caption>Riepilogo libro</caption>
<tr>
<th>
titolo
</th>
<th>
numPagine
</th>
<th>
codice isbn
</th>
<th>
editore
</th>
<th>
autore
</th>
<th>
lingua
</th>
<th>
categoria
</th>
<th>
data pubblicazione
</th>
<th>
recensione
</th>
<th>
numero copie
</th>
<th>
descrizione
</th>
<th>
disponibilità
</th>
<th>
prezzo
</th>
<th>
copie
</th>
<th>
id prodotto
</th>
</tr>



<c:forEach items="#{bean.miaLista}" var="lista">

<tr>


<td>${ lista.getTitolo() }</td>
<td>${ lista.getNumeroPagine() }</td>
<td>${ lista.getCodIsbn() }</td>
<td>${ lista.getEditore() }</td>
<td>${ lista.getAutore() }</td>
<td>${ lista.getLingua() }</td>
<td>${ lista.getCategoria()}</td>
<td>${ lista.getDataPubb()}</td>
<td>${ lista.getRecensione() }</td>
<td>${ lista.getNrCopie() }</td>
<td>${ lista.getDesc() }</td>
<td>${ lista.getDisponibilita() }</td>
<td>${ lista.getPrezzo() }</td>
<td>${ lista.getNrCopie() }</td>
<td>${ lista.getId()}</td>

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