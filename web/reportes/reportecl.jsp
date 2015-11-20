<%-- 
    Document   : reportecl
    Created on : 13-nov-2015, 4:58:38
    Author     : An
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import=" bean.bcliente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Reporte Cliente</title>
    </head>
    <body>
        <form id="rportecliente" method="post" action="../clrepor">
            <select   id="valorr" name="valorr"  >
                <option >Seleccionar opcion</option>
                <%
                    ResultSet rst = bcliente.getCliente();
                    while (rst.next()) {%>

                <option value="<%= rst.getString("idcliente")%>"><%= rst.getString("primer_nombre") %></option>

                <%}
                %>

            </select>
                <input name="cl" id="cl"  >
            <input name="cmdguardar"   type="submit" id="cmdguardar" value="guardar">
        </form>

    </body>
</html>
