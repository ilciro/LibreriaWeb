<%@ page import="bean.LibroBean" %>
<%@ page import="java.util.*" %>
<%@ page import="raccolta.Libro" %>
<%@ page import="database.LibroDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en-it">
<head>
<meta charset="ISO-8859-1">
<title>Riepilogo Libro</title>
<link href="css//CssFile.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Ecco il riepilogo del libro scelto</h1>

<table>
<caption>Riepilogo libro</caption>
<tr>
<th>
Titolo
</th>
<th>
Numero pagine
</th>
<th>
Codice Isbn
</th>
<th>
Editore
</th>
<th>
Autore
</th>
<th>
Lingua
</th>
<th>
Categoria
</th>
<th>
DataPubblicazione
</th>
<th>
Recensione
</th>
<th>
CopieVendute
</th>
<th>
Descrizione
</th>
<th>
Disponibilita
</th>
<th>
Prezzo
</th>
<th>
Copie Rimanenti
</th>
<th>
idProdotto
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