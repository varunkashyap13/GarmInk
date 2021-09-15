<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="projectJavaClasses.Bean"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account - Garm, Ink</title>
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
<%if(bean.getLoginCorrect() == false) { %>
<div class="accout-page">
    <div class="container">
  
       <div class="row">
         
          <div class="col-2">
          <%if(bean.getCartMessage() != null) { %>
          <h2><%=bean.getCartMessage() %></h2>
          <%} %>
             <img src="images/image1.png" width="100%">
          </div>
          
           <div class="col-2">

              <div class="form-container">
                 <div class="form-btn">
                     <span onclick="login()">Login</span>
                     <span onclick="register()">Register</span>
                     <hr id="indicator">
                 </div>
                 
                 <form id="LoginForm" action="mvc_servlet" method="post">
                	<input type="text" name="username" minlength="8" maxlength="16" placeholder="username" onkeypress="return event.charCode != 32" required>
                   	<input type="password" name="password" minlength="8" maxlength="16" placeholder="Password" onkeypress="return event.charCode != 32" required>
                   	<input class="menu_button" type="submit" value="Login" name="form">
                 </form>
                 
               	 <form id="RegForm" action="mvc_servlet" method="post">
                	<input type="text" name="username" minlength="8" maxlength="16" placeholder="Username" onkeypress="return event.charCode != 32" required>
                    <input type="email" name="email" placeholder="Email" maxlength="50" onkeypress="return event.charCode != 32" required>
                    <input type="password" name="password1" minlength="8" maxlength="16" placeholder="Password" onkeypress="return event.charCode != 32" required>
                    <input type="password" name="password2" minlength="8" maxlength="16" placeholder="Re-enter Password" onkeypress="return event.charCode != 32" required>
                   	<input type="text" name="firstName" maxlength="20" placeholder="First Name" required>
                   	<input type="text" name="lastName" maxlength="20" placeholder="Last Name" required>
                   	<input type="tel" name="phone" pattern="[0-9]{10}" placeholder="Phone Number ##########" required>
		<input type="text" name="address" placeholder="Shipping Address" required>
		<input type="text" name="city" placeholder="City" required>
		<input type="text" name="state" placeholder="State" minlength="2" maxlength="2" size="2" required>
		
		<input type="number" name="postalCode" minlength="5" maxlength="5" size="5" pattern="[0-9]{5}" placeholder="Shipping Zip" required>

		<input type="number" name="billingPostalCode" minlength="5" maxlength="5" size="5" pattern="[0-9]{5}" placeholder="Billing Zip" required>
 	
                   	<input class="menu_button" type="submit" value="Register" name="form">
                 </form>
              </div>
           </div>
       </div>
</div>
</div>
<%} else { %>
	<%if(bean.getLoginCorrect() == true) { %>
	<div class="accout-page">
    <div class="container">
     <%if(bean.getLoginError() != null){ %>
       		<h2><%=bean.getLoginError() %></h2>
      	 <%} %>
		<h2>Account Details</h2>
		
      	 
      	 <div class="form-container">
      	 	<form id="RegFormAccount" action="mvc_servlet" method="post">
                	<input type="text" name="username" value="Username: <%=bean.getUsername() %>" onkeypress="return event.charCode != 32" readonly>
                    <input type="email" name="email" placeholder="Email: <%=bean.getEmail() %>" maxlength="50" onkeypress="return event.charCode != 32" required>
                    <input type="password" name="password1" minlength="8" maxlength="16" placeholder="Password: <%=bean.getPassword() %>" onkeypress="return event.charCode != 32" required>
                    <input type="password" name="password2" minlength="8" maxlength="16" placeholder="Password: <%=bean.getPassword() %>" onkeypress="return event.charCode != 32" required>
                   	<input type="text" name="firstName" maxlength="20" placeholder="FirstName: <%=bean.getFirstName() %>" required>
                   	<input type="text" name="lastName" maxlength="20" placeholder="LastName: <%=bean.getLastName() %>" required>
                   	<input type="tel" name="phone" pattern="[0-9]{10}" placeholder="Phone: <%=bean.getPhone() %>" required>
 
		<input type="text" name="address" placeholder="Address: <%=bean.getAddress() %>" required>
	
		<input type="text" name="city" placeholder="City: <%=bean.getCity() %>" required>
		<input type="text" name="state" minlength="2" maxlength="2" size="2" placeholder="State: <%=bean.getState() %>" required>
		
		<input type="number" name="postalCode" minlength="5" maxlength="5" size="5" pattern="[0-9]{5}" placeholder="Ship. Zip: <%=bean.getShippingZip() %>" required>

	
	
		<input type="number" name="billingPostalCode" minlength="5" maxlength="5" size="5" pattern="[0-9]{5}" placeholder="Bill. Zip: <%=bean.getBillingZip() %>"required>
		
                   	<input class="menu_button" type="submit" value="Update Account" name="form">
                   	<input class="menu_button" type="submit" value="Delete Account" name="form">
                 </form>
      	 
      	 </div>
      	 
      	 
      	 
      	 
		
		</div>
		</div>
		
		
	<%} else { %>
	<div class="accout-page">
    <div class="container">
  
       <div class="row">
         
          <div class="col-2">
                <%if(bean.getLoginError() != null){ %>
       				<h2><%=bean.getLoginError() %></h2>
      			<%} %>
      			
      			<%if(bean.getRegisterError() != null){ %>
       				<h2><%=bean.getRegisterError() %></h2>
      			<%} %>
      			
      			
             <img src="images/image1.png" width="100%">
          </div>
          
           <div class="col-2">

              <div class="form-container">
                 <div class="form-btn">
                     <span onclick="login()">Login</span>
                     <span onclick="register()">Register</span>
                     <hr id="indicator">
                 </div>
                 
                 <form id="LoginForm" action="mvc_servlet" method="post">
                	<input type="text" name="username" minlength="8" maxlength="16" placeholder="username" onkeypress="return event.charCode != 32" required>
                   	<input type="password" name="password" minlength="8" maxlength="16" placeholder="Password" onkeypress="return event.charCode != 32" required>
                   	<input class="menu_button" type="submit" value="Login" name="form">
                 </form>
                 
               	 <form id="RegForm" action="mvc_servlet" method="post">
                	<input type="text" name="username" minlength="8" maxlength="16" placeholder="Username" onkeypress="return event.charCode != 32" required>
                    <input type="email" name="email" placeholder="Email" maxlength="50" onkeypress="return event.charCode != 32" required>
                    <input type="password" name="password1" minlength="8" maxlength="16" placeholder="Password" onkeypress="return event.charCode != 32" required>
                    <input type="password" name="password2" minlength="8" maxlength="16" placeholder="Re-enter Password" onkeypress="return event.charCode != 32" required>
                   	<input type="text" name="firstName" maxlength="20" placeholder="First Name" required>
                   	<input type="text" name="lastName" maxlength="20" placeholder="Last Name" required>
                   	<input type="tel" name="phone" pattern="[0-9]{10}" placeholder="Phone Number ##########" required>
 
		<input type="text" name="address" placeholder="Shipping Address" required>
	
		<input type="text" name="city" placeholder="City" required>
		<input type="text" name="state" minlength="2" maxlength="2" size="2" placeholder="State" required>
		
		
		<input type="number" name="postalCode" minlength="5" maxlength="5" size="5" pattern="[0-9]{5}" placeholder="Shipping Zip" required>

	
	
		<input type="number" name="billingPostalCode" minlength="5" maxlength="5" size="5" pattern="[0-9]{5}" placeholder="Billing Zip"required>
		
                   	<input class="menu_button" type="submit" value="Register" name="form">
                 </form>
              </div>
           </div>
       </div>
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