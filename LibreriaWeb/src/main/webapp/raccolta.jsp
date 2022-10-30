<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>raccolta page</title>
<link rel="stylesheet" href="css//raccolta.css">
</head>
<body>

<h1> Benvenuto nella pagina della gestione della raccolta</h1>

<h2> Possibile inserire/modificare/eliminare oggetto</h2>

<form action="ModificaServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<label for="boxL">
libri
</label>
<input type="checkbox" name="boxL" id="boxL" value="libri">
</td>
<td>
<label for="boxG">
giornali
</label>
<input type="checkbox" name="boxG" id="boxG" value="giornali">
</td>
<td>
<label for="boxR">
riviste
</label>
<input type="checkbox" name="boxR" id="boxR" value="riviste">
</td>
<td>
<input type="submit" name="sceltaB" id="sceltaB" >
</td>
<td>
<input type="submit" name="annullaB" id="annullaB" value="indietro">
</td>
</tr>

</table>
</form>

</body>
</html>