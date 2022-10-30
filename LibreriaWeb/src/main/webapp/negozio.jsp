<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en-it">
<head>
<meta charset="ISO-8859-1">
<title>Elenco negozi</title>
<link rel="stylesheet" href="css//negozio.css">

</head>
<body>



<h1>Elenco negozi</h1>



<table>
<caption> elenco negozi</caption>
<tr>
<th scope="col">
nome
</th>
<th>
indirizzo
</th>
</tr>
<c:forEach items="#{bean.negozi}" var="lista">

<tr>


<td>${ lista.getNome() }</td>
<td>${ lista.getVia() }</td>
<td>${lista.getIsValid() }</td>
<td>${lista.getIsOpen() }</td>
</tr>
</c:forEach>

</table>

<br>

<form action="NegozioServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<input type="submit" id="buttonG" name="buttonG" value="genera lista">
</td>
</tr>

</table>
</form>

<br>
<form action="RitiroNegozioServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
inserire negozio
</td>
<td>
<label for="negS" ></label>
<input type="text" id="negS" name="negS"> 
</td>
<td>
<input type="submit" id="confermaS" name="confermaS" >
</td>
</tr>
</table>
</form>





</body>
</html>