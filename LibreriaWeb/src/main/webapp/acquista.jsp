<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html lang=en>
<head>
<link rel="stylesheet" href="css//CssFile.css">




<meta charset="ISO-8859-1">
<title>Acquista Page</title>
</head>
<body>
<h1>Ecco il riepilogo prima di acquistare</h1>



<table>
<caption>Riepilogo libro scelto</caption>
<tr>
<th>
Nome
</th>
<th>
Costo
</th>
<th>
Disponibilita
</th>
</tr>
<tr>


<c:choose>
<c:when test="${bean.getDisponibilita()==1 }">
<link rel="stylesheet" href="css//tableRight.css">
</c:when>
<c:when test="${bean.getDisponibilita()==-1 }">
<link rel="stylesheet" href="css//tableError.css">
</c:when>
</c:choose>

<td> ${bean.getTitolo()}
</td>
<td>${bean.getPrezzo() }
</td>
<td>${bean.getDisponibilita()}
</td>



</tr>

</table>

<br>
<form action="SceltaServlet" method="post" >
<table>
<caption > Riepilogo quantita</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
Leggere quantita :
<label for="quantitaL" ></label>
<input type="text" name="quantitaL" id="quantitaL" />
</td>
<td>
<button type="submit" name ="buttonP" value="buttonP"> Procedi</button>
</td>
</tr>
<tr>

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
<a href="index.html">
<button>
annulla
</button></a>

</table>




</body>
</html>