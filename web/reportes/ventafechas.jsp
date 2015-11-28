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
        <title>Crear Reporte ventas </title>
    </head>
    <body>
    <CENTER><H1>CREACION DE REPORTES DE VENTAS POR FECHA</H1></CENTER>
        <form id="rporteventas" method="post" action="../rventafechas">
        <BODY BGCOLOR="#0096ad">
        <CENTER> <h2>Fecha de inicio</h2><input name="cl" id="cl" type="date" > </CENTER>
        <CENTER> <h2>Fecha de finalizacion </h2><input name="fechados"  id="fechados" type="date" > </CENTER>
        <p><CENTER> <input name="cmdguardar"   type="submit" id="cmdguardar" value= "CREAR" </CENTER> </p>
        </form>

    </body>
</html>
