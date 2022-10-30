<!DOCTYPE html>
<html lang=en/it>
<head>
<meta charset="ISO-8859-1">
<title>Info per pagamento in contanti</title>
<link href="css//contanti.css" rel="stylesheet" type="text/css">

</head>
<body>

<h1> Inserire i dati...</h1>

<form action="ServletPagamentoContanti" method="post">

<table>
<caption>Inserire anagrafica utente</caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
nome
</td>
<td>
<label for="nomeU"></label>
<input type="text" id="nomeU" name="nomeU">
</tr>
<tr>
<td>
cognome
</td>
<td>
<label for="cognomeU"></label>
<input type="text" id="cognomeU" name="cognomeU">
</td>
</tr>
<tr>
<td>
via
</td>
<td>
<label for="viaU"></label>
<input type="text" id="viaU" name="viaU">
</td>
</tr>
<tr>
<td>
comunicazioni
</td>
<td>
<label for="comunicaU"></label>
<input type="text" id="comunicaU" name="comunicaU">
</td>
</tr>
<tr>
<td></td>
<td>
<button type="submit" name="buttonProc" value="buttonProc">
procedi
</button>
</td>
</tr>
</table>
</form>

<br>
<br>
<table>
<caption></caption>
<tr>
<th scope="col"></th>
</tr>
<tr>
<td>
<a href="index.html">
<button>
indietro
</button>
</a>

</td>
</tr>
</table>



</body>
</html>