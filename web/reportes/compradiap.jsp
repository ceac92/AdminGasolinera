<%-- 
    Document   : reportecl
    Created on : 13-nov-2015, 4:58:38
    Author     : An
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Reporte Cliente</title>
    </head>
    <body>
        <form id="rportecliente" method="post" action="../rcompramp">
            
            <input name="cl" id="cl" type="date" >
            <input name="cmdguardar"   type="submit" id="cmdguardar" value="guardar">
        </form>

    </body>
</html>
