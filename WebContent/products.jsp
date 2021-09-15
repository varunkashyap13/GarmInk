<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="projectJavaClasses.Bean"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products - Garm, Ink</title>
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
       
<%if(bean.getProductCheck() == 1) { %>  
<div class="small-container">
   <div class="row row-2">
       <h2>All Products</h2>
       <form action="mvc_servlet" method="post">
            <input type="submit" value="Back To Home" name="form">
       </form>
   </div>
   <%ArrayList<ArrayList<String>> outerProduct = new ArrayList<ArrayList<String>>(); %>
   <%outerProduct = bean.getProductList(); %>
 
<%int k = 1; %>
   <%for (int i = 0; i < outerProduct.size(); i++) {%>
            <%for (int j = 0; j < outerProduct.get(i).size(); j++) {%>
              <% if (j == 0) {%>
              	<%if (k % 4 == 1) {%>
              		<div class="row">
              	<%} %>
               		<div class="col-4">
               			<img src="images/<%=outerProduct.get(i).get(j)%>.png">
              <%} else if (j == 1) { %>
              			<h4><%=outerProduct.get(i).get(j)%></h4>
              <%} else if (j == 2) {%>
              			<p>$<%=outerProduct.get(i).get(j)%></p>
              <%} else if (j == 3) {%>
              			<form action="mvc_servlet" method="post">
              				<input type="hidden" name="formProduct" value="<%=outerProduct.get(i).get(j)%>">
                   			<input class="menu_button" type="submit" value="View Item" name="form">
                   		</form>
                   	</div> 
                   	<%if (k % 4 == 0) {%>
                   	</div>
                   	<%} %>
              <% }%>
              
           <% }%>
           <% k++; %>
        <%}%>
         <%if (k % 4 == 1 || k % 4 == 2 || k % 4 == 3) {%>
                   	</div>
         <%} %>          
   </div> 
<%} else { %>  
<div class="small-container">
   <div class="row row-2">
       <h2><%=bean.getProductError() %></h2>
   </div> 
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