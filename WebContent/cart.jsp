<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="projectJavaClasses.Bean"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart - Garm, Ink</title>
    <link rel="stylesheet" href="styles/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<% Bean bean = (Bean)session.getAttribute("bean");%>

       <div class="container">
          <div class="navbar">
              <div class="logo">
                  <img src="images/logo.png" width="160px">
              </div>
              <nav>
               <ul id="MenuItems">
                 <li>
                   	<form action="mvc_servlet" method="post">
                   		<input class="menu_button" type="submit" value="Home" name="form">
                   	</form>
                   	</li>
                   	<li>
                   	<form action="mvc_servlet" method="post">
                   		<input class="menu_button" type="submit" value="Products" name="form">
                   	</form>
                   	</li>
                   	<li>
                   	<form action="mvc_servlet" method="post">
                   		<input class="menu_button" type="submit" value="Account" name="form">
                   	</form>
                   	</li>
                   	<li>
                   	<form action="mvc_servlet" method="post">
                   		<input class="menu_button" type="submit" value="Cart" name="form">
                   		<!-- <a href="cart.html"><div class="cart"></div></a> -->
                   	</form>
                   	</li>

               </ul>
              </nav>
          </div>
          
       </div>
       
 <%if(bean.getItemCheck() == 1) { %>   
 <div class="small-container cart-page">
   
   <table>
       <tr>
           <th>Product</th>
           <th>Quantity</th>
           <th>Subtotal</th>
       </tr>
<%ArrayList<ArrayList<String>> outerItem = new ArrayList<ArrayList<String>>(); %>
   <%outerItem = bean.getItemList(); %>
<%for (int i = 0; i < outerItem.size(); i++) {%>
            <%for (int j = 0; j < outerItem.get(i).size(); j++) {%>
            	<% if (j == 0) {%>
            	<tr>
           			<td>
               			<div class="cart-info">
                   			<img src="images/<%=outerItem.get(i).get(j)%>.png">
                   		<div>
            	
            	<% } else if (j == 1) { %>
            	<p><%=outerItem.get(i).get(j)%></p>
            	
            	<% } else if (j == 2) { %>
            	<small>Price: $<%=outerItem.get(i).get(j)%></small><br>
            	
            	<% } else if (j == 3) { %>
            	<small>Size: <%=outerItem.get(i).get(j)%></small>
            	
            	<% } else if (j == 4) { %>
            	<form action="mvc_servlet" method="post">
            				<input type="hidden" name="removeItem" value="<%=outerItem.get(i).get(j)%>">
                   			<input class="menu_button" type="submit" value="Remove" name="form">
                   	   </form>
                   </div>
               </div>
           </td>
           <td>
           <% } else if (j == 5) { %>
          		<form class="quantity_size" action="mvc_servlet" method="post">
           			<input type="number" name="updateQuantity" size="100" maxLength="100" value="<%=outerItem.get(i).get(j)%>">
           			<% } else if (j == 6) { %>
           			<input type="hidden" name="updateId" value="<%=outerItem.get(i).get(j)%>">
                	<input class="menu_button" type="submit" value="Update" name="form">
                </form>
           </td>
           <% } else if (j == 7) { %>
            	<td>$<%=outerItem.get(i).get(j)%></td>
       		</tr>
            	<%} %>
   			<%} %>
<%} %>
       
   </table>
    
    <div class="total-price">
        <table>
            <tr>
                <td>Total</td>
                <td>$<%=bean.getTotalCost() %></td>
            </tr>
        </table>
    </div>
    <div class="total-price">
       <form action="mvc_servlet" method="post">
            <input class="menu_button" type="submit" value="Proceed to Checkout" name="form">
       </form>
       
       
    </div>
    
</div>
 <%} else { %>
 <div class="small-container cart-page">
 <h2><%=bean.getItemError() %></h2>
 </div>
 <%} %>


<!----------Footer---------------> 

<div class="footer">
    <div class="container">
        <div class="row">
            <div class="footer-col1">
               <h3>Download Our App</h3>
                <p>Download App for Android and ios mobile phone.</p>
                <div class="app-logo">
                    <img src="images/play-store.png">
                    <img src="images/app-store.png">
                </div>
            </div>
            <div class="footer-col2">
               <img src="images/logo-white.png" width="160px">
            </div>
            <div class="footer-col3">
               <h3>Useful Links</h3>
                <ul>
                    <li>Coupons</li>
                    <li>Blog Post</li>
                    <li>Return Policy</li>
                    <li>Join Affiliate</li>
                </ul>
            </div>
            <div class="footer-col4">
               <h3>Follow Us</h3>
                <ul>
                    <li>Facebook</li>
                    <li>Twitter<li>
                    <li>Instagram</li>
                    <li>YouTube</li>
                </ul>
            </div>
        </div>
        <hr>
        </div>
    
</div>



</body>
</html>