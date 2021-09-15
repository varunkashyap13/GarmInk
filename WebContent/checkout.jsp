<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="projectJavaClasses.Bean"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Garm, Ink</title>
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
       
<% if(bean.getCreditCardCheck() == null) {%>   
<div class="accout-page">
    <div class="container">
    <h2>Purchase Details</h2>
    <div class="form-container-checkout">
    <h4>Number of items: <%=bean.getTotalItems() %></h4>
    <h4>Total Price: $<%=bean.getTotalCost() %></h4>
    <h4>Shipping: 3-5 business days (FREE)</h4>
    <br>
    <h2>Payment Details</h2>
    
    <h4>Billing Zip: <%=bean.getBillingZip() %></h4>
    <h4>Credit Card Info:</h4>
    <form action="mvc_servlet" method="post">
   		 <input type="number" name="creditCard" minlength="16" maxlength="16" placeholder="Credit Card Number" onkeypress="return event.charCode != 32" required>
         <input class="menu_button" type="submit" value="Complete Purchase" name="form">
    </form>
    </div>
     </div>
</div>
<%} else { %>  
<%if(bean.getCreditCardCheck() == 0) {%>  
<div class="accout-page">
    <div class="container">
    <h2>Purchase Details</h2>
    <%if(bean.getCompletePurchaseMessage() != null) { %>
    	<h4><%=bean.getCompletePurchaseMessage() %></h4>
    <%} %>
    <div class="form-container-checkout">
    <h4>Number of items: <%=bean.getTotalItems() %></h4>
    <h4>Total Price: $<%=bean.getTotalCost() %></h4>
    <h4>Shipping: 3-5 business days (FREE)</h4>
    <br>
    <h2>Payment Details</h2>
    
    <h4>Billing Zip: <%=bean.getBillingZip() %></h4>
    <h4>Credit Card Info:</h4>
    <form action="mvc_servlet" method="post">
   		 <input type="number" name="creditCard" minlength="16" maxlength="16" placeholder="Credit Card Number" onkeypress="return event.charCode != 32" required>
         <input class="menu_button" type="submit" value="Complete Purchase" name="form">
    </form>
    </div>
     </div>
</div>
<%} else {%>   
<div class="accout-page">
    <div class="container">
    <h2>Thank you for your purchase!</h2>
     </div>
</div>
<%} %> 
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

<!------------------- form toggle ----------->
    <script>
        var LoginForm = document.getElementById("LoginForm");
        var RegForm = document.getElementById("RegForm");
        var Indicator = document.getElementById("indicator");
        
        function register(){
                RegForm.style.transform = "translateX(0px)";
                LoginForm.style.transform = "translateX(0px)";
                Indicator.style.transform = "translateX(100px)"
            };
        function login(){
                RegForm.style.transform = "translateX(300px)";
                LoginForm.style.transform = "translateX(300px)";
                Indicator.style.transform = "translateX(0px)"
            };
    </script>
    
    
    
</body>
</html>