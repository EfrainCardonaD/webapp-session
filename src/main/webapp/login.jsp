<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Formulado login</title>
</head>
<body>

<h1>Inicio Sesion</h1>
<form action="/webapp-session2/login" method="post">
<div>
  <label for="username">Username: </label>
</div>
<div>
  <input type="text" name="username" id="username">
</div>
<div>
  <label for="password">Contraseña: </label>
</div>
<div>
  <input type="password" name="password" id="password">
</div>
<div>
  <input type="submit" value="Login">
</div>
</form>
</body>
</html>