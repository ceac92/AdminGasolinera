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
        <BODY BGCOLOR="#0096ad">
         <CENTER><H1>CREACION DE REPORTES DE COMPRA POR DIA</H1></CENTER>
        <form id="rportecliente" method="post" action="../rcompramp">
            
            <CENTER> <h2>Fecha de inicio</h2> <input name="cl" id="cl" type="date" > </center>
            <p> <CENTER> <input name="cmdguardar"   type="submit" id="cmdguardar" value="GUARDAR"> </p>
        </form>

    </body>
</html>
