<%-- 
    Document   : reportecl
    Created on : 13-nov-2015, 4:58:38
    Author     : An
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import=" bean.btipoproducto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Reporte Cliente</title>
    </head>
    <body>
        <BODY BGCOLOR="#0096ad">
        <CENTER><H1>CREACION DE REPORTES DE VENTAS POR PRODUCTO</H1></CENTER>
        <form id="rportecliente" method="post" action="../rproductotipo">
            <select   id="tipop" name="tipop"  >
                <p> </p>   
               <option >Seleccionar opcion</option>
                <%
                    ResultSet rst = btipoproducto.getCliente();
                    while (rst.next()) {%>
        
        <option value="<%= rst.getString("idctgTipoProducto")%>"><%= rst.getString("nombre") %></option>

                <%}
                %>

            </select>
                
                <center> <input name="cmdguardar"   type="submit" id="cmdguardar" value="CREAR"> </center>
        </form>

    </body>
</html>
