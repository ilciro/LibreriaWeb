<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>admin page</title>
<link href="css//admin.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1> utente loggato come admin</h1>

<h2> scegli cosa fare</h2>

<h3> report oggetti , gestione oggetti, gestione utenti</h3>

<form action="ReportServlet" method="post">
<table>
<caption>
</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
gestione report
</td>
<td>
<input type="submit" id="buttonR" name="buttonR" value="genera report">
</td>
</tr>
</table>
<br>

<table>
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
gestione raccolta
</td>
<td>
<input type="submit" id="buttonRac" name="buttonRac" value="genera raccolta">
</td>
</tr>
</table>
<br>

<table>
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
gestione utenti
</td>
<td>
<input type="submit" id="buttonUt" name="buttonUt" value="gestione utenti">
</td>
</tr>
</table>

<br>
<table>
<caption>
</caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
<input type="submit" id="buttonLog" name="buttonLog" value="logout">
</td>
</tr>
</table>
</form>

</body>
</html>