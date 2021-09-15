<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="projectJavaClasses.Bean"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Details - Garm, Ink</title>
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
      <%if(bean.getAddToCartMessage() != null){ %>
       	<h2><%=bean.getAddToCartMessage() %></h2>
      <%} %>
       
 <%if(bean.getProductDetailCheck() == null) { %>   
 <div class="small-container single-product">
    <div class="row">
    <h2>Sorry, unable to pull up products at this time.</h2>
    </div>
    </div>
 <%} else { %>
 <%if(bean.getProductDetailCheck() == 1) {%>   
	<div class="small-container single-product">
    <div class="row">
        <div class="col-2">
            <img src="images/<%=bean.getName()%>.png" width="100%" id="ProductImg">
            
        </div>
        <div class="col-2">
            <h1><%=bean.getDisplayName()%></h1>
            <h4>$<%=bean.getPrice()%>.00</h4>
            
            <form action="mvc_servlet" method="post">
            <label for="size">Select Size:</label>
               <select name="size" id="size" required>
               	<option value="Small">Small</option>
               	<option value="Medium">Medium</option>
               	<option value="Large">Large</option>
               	<option value="XXL">XL</option>
               	<option value="XXL">XXL</option>
           	   </select>
            	<input type="number" value="1" name="quantity" min="1" max="100" required>
            	<input type="hidden" name="formAddToCart" value="<%=bean.getProductId()%>">
            	<input type="hidden" name="formAddToCartDName" value="<%=bean.getDisplayName()%>">
            	<input type="hidden" name="formAddToCartName" value="<%=bean.getName()%>">
            	<input type="hidden" name="formAddToCartPrice" value="<%=bean.getPrice()%>">
                <input id="add_to_cart" type="submit" value="Add To Cart" name="form">
            </form>
            
            <h3>PRODUCT DETAILS <i class="fa fa-indent"></i></h3>
            <br>
            <p><%=bean.getDescription()%></p>
            <form action="mvc_servlet" method="post">
            	<input id="back" type="submit" value="Back To All Products" name="form">
            </form>
        </div>
    </div>
</div>
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
 <%} else{%>
   
	<h2><%=bean.getProductDetailError() %></h2>
<%} %>
<%} %>
 


<script>
    
//-------------Produc Gallery------------
    
    
    var ProductImg = document.getElementById("ProductImg");
    
    var SmallImg = document.getElementsByClassName("small-img");
       
 
        SmallImg[0].onclick = function()
        {
            ProductImg.src = SmallImg[0].src;
        }
        SmallImg[1].onclick = function()
        {
            ProductImg.src = SmallImg[1].src;
        
        }
        SmallImg[2].onclick = function()
        {
            ProductImg.src = SmallImg[2].src;
        
        }
        SmallImg[3].onclick = function()
        {
            ProductImg.src = SmallImg[3].src;
        
        }

  //-------------End Produc Gallery------------  
    
</script>



</body>
</html>