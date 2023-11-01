<%-- 
    Document   : viewCart
    Created on : Oct 27, 2023, 9:53:55 AM
    Author     : LE HUU HUY
--%>

<%@page import="java.util.Map"%>
<%@page import="huylh.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <%--<%
            //1. Cust goes to the cart place
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (String key : items.keySet()) {
                %>
                <tr>
                    <td>
                        <%= ++count %>
                    </td>
                    <td>
                        <%= key %>
                    </td>
                    <td>
                        <%= items.get(key) %>
                    </td>
                    <td>
                        <input type="checkbox" name="chkCart" value="ON" />
                    </td>
                </tr>
                <%
                    }//end traverse key to get value
                %>
            </tbody>
        </table>

        <%
                        return;
                    }//items have existed
                }//cart has existed
            }//end cart place must be existed
        %>

        <h2>
            No cart is existed!!!
        </h2>--%>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                <form action="DispatchServlet">
                    <c:forEach var="item" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                                .</td>
                            <td>
                                ${item.key}
                            </td>
                            <td>
                                ${item.value}
                            </td>
                            <td>
                                <input type="checkbox" name="chkItem" value="${item.key}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="bookStore.html">Add More Items to Your Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction"/> 
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty cart}">
        <h2>Cart does not exist!!!</h2>
    </c:if>
</body>
</html>
