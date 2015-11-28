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
        <title>Crear Reporte Compras </title>
    </head>
    <body>
         <CENTER><H1>CREACION DE REPORTES DE COMPRA POR FECHA</H1></CENTER>
         <BODY BGCOLOR="#0096ad">
        <form id="rportecliente" method="post" action="../rcomprafechas">

           <CENTER> <h2>Fecha de inicio</h2> <input name="cl" id="cl" type="date" > </CENTER>
           <CENTER> <h2>Fecha de finalizacion </h2> <input name="fechados" id="fechados" type="date" > </center>
           <p> <CENTER> <input name="cmdguardar"   type="submit" id="cmdguardar" value="CREAR"> </center> </p>
        </form>

    </body>
</html>
