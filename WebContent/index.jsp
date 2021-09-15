<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Garm, Ink</title>
    <link rel="stylesheet" href="styles/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
   
   <div class="header">
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
           <div class="row">
                <div class="col-2">

                    <h1>Let Your Wardrobe Be<br>As Unique As You Are!</h1>
                    <p>Look and feel great at an affordable price.</p>
                    
                    <form action="mvc_servlet" method="post">
                    	<input class="menu_button" type="submit" value="Explore Now" name="form">
                   	</form>
                </div>
                <div class="col-2">
                    <img src="images/image1.png">
                </div>
               
           </div>
           
       </div>
       </div>

<!----------testimonial--------------->   
  <h2 class="title">Testimonials</h2>
   <div class="testimonial">
       <div class="small-container">
       <div class="row">
           <div class="col-3">
                    <i class="fa fa-quote-left"></i>
                      <p>Their prints are far superior than others I have seen. I ordered some t-shirts and afters months of washing, they look as new as when I got them. Great quality!</p>
                      <div class="rating">
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                       </div>
                       <img src="images/user-1.png">
                       <h3>Melinda Smith</h3>
           </div>
            <div class="col-3">
                    <i class="fa fa-quote-left"></i>
                      <p>I love their designs! So unique and different from the competition. The customer service team at Garm, Ink is incredible as well. They will go out of their way to help you with anything!</p>
                      <div class="rating">
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                       </div>
                       <img src="images/user-2.png">
                       <h3>Adam Johnson</h3>
           </div>
            <div class="col-3">
                    <i class="fa fa-quote-left"></i>
                      <p>Such a wide variety of products, and they are all great quality! They make for great gifts...while you're at it, buy yourself a couple too!</p>
                      <div class="rating">
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                           <i class="fa fa-star"></i>
                       </div>
                       <img src="images/user-3.png">
                       <h3>Janet Clark</h3>
           </div>
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



</body>
</html>
