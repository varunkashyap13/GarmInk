/**
 * @author varunkashyap
 * File: mvc_servlet.java
 * Description: Controller servlet
 */

package projectJavaClasses;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.OutputStream;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@WebServlet("/mvc_servlet")
public class mvc_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mvc_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Declare variables
		String productId = "";
		Integer loginCheck = 0;
		String size = "";
		String password = "";
		Integer passwordCheck = 0;
		Integer usernameRegCheck = 0;
		Integer passwordRegCheck = 0;
		String quantity = "";
		String username = "";
		UUID uuid = null;
		Integer formCheck = 0;
		Integer usernameCheck = 0;
		String loginErrorMessage = "";
		String registerErrorMessage = "";
		Integer regFormCheck = 0;
		String password1 = "";
		String password2 = "";
		String firstName = "";
		String lastName = "";
		Integer firstNameCheck = 0;
		Integer lastNameCheck = 0;
		String phone = "";
		String creditCard = "";
		Integer phoneCheck = 0;
		String email = "";
		Integer emailCheck = 0;
		String regEmail = "";
		Integer regEmailCheck = 0;
		String address = "";
		Integer addressCheck = 0;
		String city = "";
		Integer cityCheck = 0;
		Integer stateCheck = 0;
		String state = "";
		Integer shippingZipCheck = 0;
		String shippingZip = "";
		Integer billingZipCheck = 0;
		String billingZip = "";
		String fullAddress = "";
		String regUsername = "";
		Integer regUsernameCheck = 0;
		Integer itemPrice = 0;
		Integer itemQuantity = 0;
		Integer itemSubtotal = 0;
		Integer itemTotal = 0;
		Integer numItems = 0;
		Integer creditCardCheck = 0;
		String completePurchaseMessage = "";
		
		
		if(request.getParameter("form").equals("Home")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
			}
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("form").equals("Products") || request.getParameter("form").equals("Explore Now")) {
			
			ArrayList<ArrayList<String>> outerProduct = new ArrayList<ArrayList<String>>();
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
				//reset error messages and checks
				bean.setProductCheck(null);
				bean.setProductError(null);
			}
			Database database = new Database();
			
			database.setSearchProducts();
			//query database to save product information
			try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
				
				while (result.next()) {
					ArrayList<String> innerProduct = new ArrayList<String>();
					innerProduct.add(result.getString("NAME"));
					innerProduct.add(result.getString("DISPLAY_NAME"));
					innerProduct.add(result.getString("PRICE"));
					innerProduct.add(result.getString("PRODUCT_ID"));
					outerProduct.add(innerProduct);
				}
				
				bean.setProductList(outerProduct);
				bean.setProductCheck(1);
				database.con.close();
			} catch (SQLException e) {
				bean.setProductCheck(0);
				bean.setProductError("Unable to render our products right now.");
			} 
			
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("form").equals("Account")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
				bean.setLoginError(null);
				bean.setRegisterError(null);
				bean.setCartMessage(null);
			}
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("form").equals("Back To All Products")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
			}
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/products.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("form").equals("Back To Home")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
			}
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("form").equals("Remove")) {
			Bean bean = (Bean)session.getAttribute("bean");
			numItems = 0;
			
			//delete item from carts db
			Database database = new Database();
			database.setSearchCarts();
			
			
			try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
				
				statement.executeUpdate("DELETE FROM CARTS "
		                + "WHERE CART_ID ='" + request.getParameter("removeItem") + "';");
				
				database.con.close();
			} catch (SQLException e) {
				bean.setProductDetailError(bean.getProductDetailError() + " Sorry, unable to check for that product at this time.");
			}
			
			if(bean.getLoginCorrect() == false) {
				bean.setCartMessage("You must login first before you can access the cart.");
				session.setAttribute("bean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
				rd.forward(request, response);
			} else {
				if(bean.getLoginCorrect() == true) {
					//query database for cart items by account_id
					database = new Database();
					ArrayList<ArrayList<String>> outerItem = new ArrayList<ArrayList<String>>();
					
					database.setSearchCarts();
					//query database for product information
					try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
						
						while (result.next()) {
							String compareTo = result.getString("ACCOUNT_ID");
							//if account_id matches current user, save items into 
							if (compareTo.equals(bean.getAccountId())) {
								ArrayList<String> innerItem = new ArrayList<String>();
							
								innerItem.add(result.getString("NAME"));
								innerItem.add(result.getString("DISPLAY_NAME"));
								innerItem.add(result.getString("PRICE"));
								innerItem.add(result.getString("SIZE"));
								innerItem.add(result.getString("CART_ID"));
								innerItem.add(result.getString("QUANTITY"));
								innerItem.add(result.getString("CART_ID"));
								
								
								//calculate subtotal price
								itemPrice = Integer.parseInt(result.getString("PRICE"));
								itemQuantity = Integer.parseInt(result.getString("QUANTITY"));
								itemSubtotal = itemPrice * itemQuantity;
								itemTotal += itemSubtotal;
								
								
								innerItem.add(Integer.toString(itemSubtotal));
								outerItem.add(innerItem);
								numItems += Integer.parseInt(result.getString("QUANTITY"));
							}
						}
						bean.setTotalItems(numItems);
						bean.setTotalCost(Integer.toString(itemTotal));
						bean.setItemList(outerItem);
						bean.setItemCheck(1);
						database.con.close();
					} catch (SQLException e) {
						bean.setItemCheck(0);
						bean.setItemError("Unable to render your cart items right now.");
					} 
					
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
					rd.forward(request, response);
					
				} else {
					bean.setCartMessage("You must login first before you can access the cart.");
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				}
			}
			
			
			
		}
		
		
		if(request.getParameter("form").equals("Update")) {
			Bean bean = (Bean)session.getAttribute("bean");
			numItems = 0;
			
			//delete item from carts db
			Database database = new Database();
			database.setSearchCarts();
			
			
			try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
				
				statement.executeUpdate("UPDATE CARTS SET QUANTITY = " + request.getParameter("updateQuantity") + ""
		                + " WHERE CART_ID = '" + request.getParameter("updateId") + "';");
				
				database.con.close();
			} catch (SQLException e) {
				bean.setProductDetailError(bean.getProductDetailError() + " Sorry, unable to check for that product at this time.");
			}
			
			if(bean.getLoginCorrect() == false) {
				bean.setCartMessage("You must login first before you can access the cart.");
				session.setAttribute("bean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
				rd.forward(request, response);
			} else {
				if(bean.getLoginCorrect() == true) {
					//query database for cart items by account_id
					database = new Database();
					ArrayList<ArrayList<String>> outerItem = new ArrayList<ArrayList<String>>();
					
					database.setSearchCarts();
					//query database for product information
					try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
						
						while (result.next()) {
							String compareTo = result.getString("ACCOUNT_ID");
							//if account_id matches current user, save items into 
							if (compareTo.equals(bean.getAccountId())) {
								ArrayList<String> innerItem = new ArrayList<String>();
							
								innerItem.add(result.getString("NAME"));
								innerItem.add(result.getString("DISPLAY_NAME"));
								innerItem.add(result.getString("PRICE"));
								innerItem.add(result.getString("SIZE"));
								innerItem.add(result.getString("CART_ID"));
								innerItem.add(result.getString("QUANTITY"));
								innerItem.add(result.getString("CART_ID"));
								
								
								//calculate subtotal price
								itemPrice = Integer.parseInt(result.getString("PRICE"));
								itemQuantity = Integer.parseInt(result.getString("QUANTITY"));
								itemSubtotal = itemPrice * itemQuantity;
								itemTotal += itemSubtotal;
								
								
								innerItem.add(Integer.toString(itemSubtotal));
								outerItem.add(innerItem);
								numItems += Integer.parseInt(result.getString("QUANTITY"));
							}
						}
						bean.setTotalItems(numItems);
						bean.setTotalCost(Integer.toString(itemTotal));
						bean.setItemList(outerItem);
						bean.setItemCheck(1);
						database.con.close();
					} catch (SQLException e) {
						bean.setItemCheck(0);
						bean.setItemError("Unable to render your cart items right now.");
					} 
					
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
					rd.forward(request, response);
					
				} else {
					bean.setCartMessage("You must login first before you can access the cart.");
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				}
			}
			
			
			
		}
		if(request.getParameter("form").equals("Cart")) {
			numItems = 0;
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
				bean.setCartMessage("You must login first before you can access the cart.");
				session.setAttribute("bean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
				rd.forward(request, response);
			} else {
				bean = (Bean)session.getAttribute("bean");
				if(bean.getLoginCorrect() == false) {
					bean.setCartMessage("You must login first before you can access the cart.");
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				} else {
					if(bean.getLoginCorrect() == true) {
						//query database for cart items by account_id
						Database database = new Database();
						ArrayList<ArrayList<String>> outerItem = new ArrayList<ArrayList<String>>();
						
						database.setSearchCarts();
						//query database for product information
						try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
						
							while (result.next()) {
								String compareTo = result.getString("ACCOUNT_ID");
								//if account_id matches current user, save items into 
								if (compareTo.equals(bean.getAccountId())) {
									ArrayList<String> innerItem = new ArrayList<String>();
								
									innerItem.add(result.getString("NAME"));
									innerItem.add(result.getString("DISPLAY_NAME"));
									innerItem.add(result.getString("PRICE"));
									innerItem.add(result.getString("SIZE"));
									innerItem.add(result.getString("CART_ID"));
									innerItem.add(result.getString("QUANTITY"));
									innerItem.add(result.getString("CART_ID"));
									
									
									//calculate subtotal price
									itemPrice = Integer.parseInt(result.getString("PRICE"));
									itemQuantity = Integer.parseInt(result.getString("QUANTITY"));
									itemSubtotal = itemPrice * itemQuantity;
									itemTotal += itemSubtotal;
									
									
									innerItem.add(Integer.toString(itemSubtotal));
									outerItem.add(innerItem);
									numItems += Integer.parseInt(result.getString("QUANTITY"));
								}
							}
							
							
							
							bean.setTotalItems(numItems);
							bean.setTotalCost(Integer.toString(itemTotal));
							bean.setItemList(outerItem);
							bean.setItemCheck(1);
							database.con.close();
						} catch (SQLException e) {
							bean.setItemCheck(0);
							bean.setItemError("Unable to render your cart items right now.");
						} 
						
						session.setAttribute("bean", bean);
						RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
						rd.forward(request, response);
						
					} else {
						bean.setCartMessage("You must login first before you can access the cart.");
						session.setAttribute("bean", bean);
						RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
						rd.forward(request, response);
					}
				}

			}
		}
		
		if(request.getParameter("form").equals("View Item")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
				
				//reset error messages and checks
				bean.setAddToCartMessage(null);
				bean.setAddToCartCheck(null);
				bean.setProductDetailCheck(null);
				bean.setProductDetailError(null);
			}
			//use productId to retrieve product information from database
			productId = request.getParameter("formProduct");
			
			Database database = new Database();
			
			database.setSearchProducts();
			//query database for product information
			try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
			
				while (result.next()) {
					
					String compareTo = result.getString("PRODUCT_ID");
					if (productId.equals(compareTo)) {
						bean.setProductId(productId);
						
						bean.setName(result.getString("NAME"));
						bean.setPrice(result.getString("PRICE"));
						bean.setDescription(result.getString("DESCRIPTION"));
						bean.setDisplayName(result.getString("DISPLAY_NAME"));
						bean.setProductDetailCheck(1);
						break;
					} else {
						bean.setProductDetailError("Sorry, unable to find that product. Please contact us.");
						bean.setProductDetailCheck(0);
					}
				}
				database.con.close();
			} catch (SQLException e) {
				bean.setProductDetailError(bean.getProductDetailError() + " Sorry, unable to check for that product at this time.");
			}
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/product-details.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("form").equals("Add To Cart")) {
			
			//get product details and validate inputs
			
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				
				bean = new Bean();
				bean.setAddToCartMessage("Sorry, unable to add items to cart. Please login first.");
				session.setAttribute("bean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/product-details.jsp");
				rd.forward(request, response);
			} else {
				bean = (Bean)session.getAttribute("bean");
				if(bean.getLoginCorrect() == false) {
					bean.setAddToCartMessage("Sorry, unable to add items to cart. Please login first.");
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/product-details.jsp");
					rd.forward(request, response);
				} else {
					//user is logged in, they may add items to the cart DB
					Database database = new Database();
					
					//validate input- size
					
					size = request.getParameter("size");
					quantity = request.getParameter("quantity");
					if(!size.isBlank() && !size.isEmpty()) {
						if(size.equals("Small") || size.equals("Medium") || size.equals("Large") || size.equals("XL") || size.equals("XXL")) {
							if(!quantity.isBlank() && !quantity.isEmpty()) {
								try {
									if(Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) < 101) {
										//validation passed. save to DB
										try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
											
											// update registrar database with number registered
											//set CART_ID
											uuid = UUID.randomUUID();
											//add values to CARTS DB
											statement.executeUpdate("INSERT INTO CARTS(CART_ID, PRODUCT_ID, QUANTITY, SIZE, ACCOUNT_ID, DISPLAY_NAME, PRICE, NAME) "
									                + "VALUES('" + uuid + "', '" + request.getParameter("formAddToCart") + "', '" + quantity + "', '" + size + "', '" + bean.getAccountId() + "', '" + request.getParameter("formAddToCartDName") + "', '" + request.getParameter("formAddToCartPrice") + "', '" + request.getParameter("formAddToCartName") + "');");
											
											//addToCartCheck- add after all validation is true
											bean.setAddToCartCheck(1);
											bean.setAddToCartMessage("Successfully added to your cart!");
											
											database.con.close();
											//redirect
											
										} catch (SQLException e) {
											//quantity value is not in range
											bean.setAddToCartCheck(0);
											bean.setAddToCartMessage("Unable to connect to our database at this time. Please try again later.");
										}
									} else {
										//quantity value is not in range
										bean.setAddToCartCheck(0);
										bean.setAddToCartMessage("Invalid quantity selected. For order quantity larger than 100, please contact us.");
									}
								} catch (NumberFormatException e) {
									//quantity is blank
									bean.setAddToCartCheck(0);
									bean.setAddToCartMessage("Invalid quantity selected. For order quantity larger than 100, please contact us.");
								}
							} else {
								//quantity is blank
								bean.setAddToCartCheck(0);
								bean.setAddToCartMessage("Invalid quantity selected. For order quantity larger than 100, please contact us.");
							}
						} else {
							//invalid size selected
							bean.setAddToCartCheck(0);
							bean.setAddToCartMessage("Invalid size selected.");
						}
					} else {
						//addToCartCheck
						bean.setAddToCartCheck(0);
						bean.setAddToCartMessage("Invalid size selected.");
					}
				}
				
				session.setAttribute("bean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/product-details.jsp");
				rd.forward(request, response);
				
			}

		}
		
		if(request.getParameter("form").equals("Login")) {
			
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
				bean.setLoginCorrect(false);
				bean.setLoginError(null);
			}
				username = request.getParameter("username");
				if (!username.isBlank() && !username.isEmpty()) {
					if (username.length() < 8 || username.length() > 16) {
						formCheck = 0;
						usernameCheck = 0;
						loginErrorMessage = "Invalid username length.";
					} else {
						Pattern pattern = Pattern.compile("\\s");
						Matcher matcher = pattern.matcher(username);
						if (matcher.find()) {
							formCheck = 0;
							usernameCheck = 0;
							loginErrorMessage = "No spaces allowed in username.";
						} else {
							// check database for valid login
							Database database = new Database();
							database.setSearchAccounts();

							try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
								
								
								
								if (result.next() == false) {
									// password is correct, but login is not
									formCheck = 0;
									usernameCheck = 0;
									loginErrorMessage = "Username does not exist. Please fix or register.";
								} else {
									result.beforeFirst();
									while (result.next()) {
										String compareTo = result.getString("USERNAME");
										if (username.equals(compareTo)) {
											usernameCheck = 1;
											bean.setUsername(username);
											break;
										} else {
											formCheck = 0;
											usernameCheck = 0;
											loginErrorMessage = "Username/password does not exist. Please fix or register.";
										}
									}
								}
								
								database.con.close();
							} catch (SQLException e) {
								e.printStackTrace();
								formCheck = 0;
								usernameCheck = 0;
								loginErrorMessage = "Unable to verify username. Please contact technical support for help.";
							}
						}
					}
				} else {
					formCheck = 0;
					loginErrorMessage = "Username field cannot be blank.";
				}
				
				if (usernameCheck == 1) {
					// password check
					password = request.getParameter("password");
					if (!password.isBlank() && !password.isEmpty()) {
						if (password.length() < 8 || password.length() > 16) {
							formCheck = 0;
							passwordCheck = 0;
							loginErrorMessage = "Password length is incorrect. Must be between 8 and 16 characters.";
						} else {
							Pattern pattern = Pattern.compile("\\s");
							Matcher matcher = pattern.matcher(password);
							if (matcher.find()) {
								formCheck = 0;
								passwordCheck = 0;
								loginErrorMessage = "Password cannot contain spaces.";

							} else {
								// check database for valid password
								Database database = new Database();
								database.setSearchAccounts();

								try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
								
									
									
									if (result.next() == false) {
										// password is correct, but login is not
										formCheck = 0;
										passwordCheck = 0;
										loginErrorMessage = "Username/Password does not exist. Please fix or register.";
									} else {
										result.beforeFirst();
										while (result.next()) {
											String compareTo = result.getString("PASSWORD");
											if (password.equals(compareTo)) {
												// login and password are correct
												if (usernameCheck == 1) {
													ResultSet validResult = result;
													passwordCheck = 1;
													formCheck = 1;
													
													bean.setAccountId(validResult.getString("ACCOUNT_ID"));
													bean.setUsername(validResult.getString("USERNAME"));
													bean.setAccountId(validResult.getString("ACCOUNT_ID"));
													bean.setPassword(password);
													bean.setFirstName(validResult.getString("FIRST_NAME"));
													bean.setLastName(validResult.getString("LAST_NAME"));
													bean.setPhone(validResult.getString("PHONE"));
													bean.setEmail(validResult.getString("EMAIL"));
													bean.setAddress(validResult.getString("ADDRESS"));
													bean.setCity(validResult.getString("CITY"));
													bean.setState(validResult.getString("STATE"));
													bean.setShippingZip(validResult.getString("SHIPPING_ZIP"));
													bean.setBillingZip(validResult.getString("BILLING_ZIP"));
													bean.setFullAddress(validResult.getString("FULL_ADDRESS"));
													loginErrorMessage = "Login Successfull! Welcome back, " + bean.getFirstName() + "!";
													
													break;
												} else {
													// password is correct, but login is not
													formCheck = 0;
													passwordCheck = 0;
													loginErrorMessage = "Username is incorrect. Please fix or register.";
												}

											} else {
												formCheck = 0;
												passwordCheck = 0;
												loginErrorMessage = "Password is incorrect.";
											}
										}
									}
									
									database.con.close();
								} catch (SQLException e) {
									e.printStackTrace();
									formCheck = 0;
									passwordCheck = 0;
								}
							}
						}
					} else {
						formCheck = 0;
						loginErrorMessage = "Password invalid.";
					}
				}

				// if login correct, then save updated info and forward back to
				// login page and update information
				if (passwordCheck == 1 && usernameCheck == 1 && formCheck == 1) {
					bean.setLoginCorrect(true);
					bean.setLoginError(loginErrorMessage);
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				} else {
					bean.setLoginCorrect(false);
					bean.setLoginError(loginErrorMessage);
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				}
		}
		
		if(request.getParameter("form").equals("Logout")) {
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("/logout.jsp");
				rd.forward(request, response);
		}
		
		
		
		
		if(request.getParameter("form").equals("Register")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
				bean.setLoginCorrect(false);
				bean.setLoginError(null);
			}
			
			
			
			regUsername = request.getParameter("username");
			if (!regUsername.isBlank() && !regUsername.isEmpty()) {
				
				if (regUsername.length() < 8 || regUsername.length() > 16) {
					
					regFormCheck = 0;
					usernameRegCheck = 0;
					registerErrorMessage = "username is invalid length.";
				} else {
					Pattern pattern = Pattern.compile("\\s");
					Matcher matcher = pattern.matcher(regUsername);
					if (matcher.find()) {
						regFormCheck = 0;
						usernameRegCheck = 0;
						registerErrorMessage = "No spaces allowed in username.";
					} else {
						// check database for valid login
						Database database = new Database();
						database.setSearchAccounts();

						try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
							
							if (result.next() == false) {
								bean.setUsername(regUsername);
								usernameRegCheck = 1;
								regFormCheck = 1;
							} else {
								result.beforeFirst();
								while (result.next()) {
									String compareTo = result.getString("USERNAME");
									if (regUsername.equals(compareTo)) {
										usernameRegCheck = 0;
										regFormCheck = 0;
										registerErrorMessage = "username already exists. please select another username.";
										break;
									} else {
										bean.setUsername(regUsername);
										usernameRegCheck = 1;
										regFormCheck = 1;
									}
								}
							}

							database.con.close();
						} catch (SQLException e) {
							e.printStackTrace();
							regFormCheck = 0;
							usernameRegCheck = 0;
							registerErrorMessage = "username entry invalid.";
						}
					}
				}
			} else {
				regFormCheck = 0;
				registerErrorMessage = "username entry invalid. ";
			}
			
			//password check
			password1 = request.getParameter("password1");
			password2 = request.getParameter("password2");

			if ((!password1.isBlank() && !password1.isEmpty()) && (!password2.isBlank() && !password2.isEmpty())) {
				
				if (password1.equals(password2)) {
					passwordRegCheck = 1;
					bean.setPassword(password2);
				} else {
					regFormCheck = 0;
					registerErrorMessage = "Password fields do not match.";
				}
			} else {
				regFormCheck = 0;
				registerErrorMessage = "One of the password fields was left blank.";
			}
			
				// validate firstName
				firstName = request.getParameter("firstName");
				if (!firstName.isBlank() && !firstName.isEmpty()) {
					bean.setFirstName(firstName);
					firstNameCheck = 1;
				} else {
					regFormCheck = 0;
					registerErrorMessage = "First name is invalid.";
				}

				// validate lastName
				lastName = request.getParameter("lastName");
				if (!lastName.isBlank() && !lastName.isEmpty()) {
					bean.setLastName(lastName);
					lastNameCheck = 1;
				} else {
					regFormCheck = 0;
					registerErrorMessage = "Last name is invalid.";
				}
				
				//validate phone
				phone = request.getParameter("phone");
				if (!phone.isEmpty() && !phone.isBlank()) {
					Pattern ptrn = Pattern.compile("[0-9]{10}");
					Matcher match = ptrn.matcher(phone);
					if (match.find()) {
						//phone number if correct
						bean.setPhone(phone);
						phoneCheck = 1;
					} else {
						phoneCheck = 0;
						regFormCheck = 0;
						registerErrorMessage = "Phone number is invalid.";
					}
				} else {
					phoneCheck = 0;
					regFormCheck = 0;
					registerErrorMessage = "Phone number is invalid.";
				}
				
				
				// validate email
				regEmail = request.getParameter("email");
				if (!regEmail.isEmpty() && !regEmail.isBlank()) {
					Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
					Matcher mat = pattern.matcher(regEmail);

					if (mat.matches()) {
						bean.setEmail(regEmail);
						regEmailCheck = 1;
					} else {
						regFormCheck = 0;
						regEmailCheck = 0;
						registerErrorMessage = "Email provided is not valid email address.";
					}
				} else {
					regFormCheck = 0;
					regEmailCheck = 0;
					registerErrorMessage = "Email input not valid.";
				}
				
				//shipping address
				address = request.getParameter("address");
				if (!address.isEmpty() && !address.isBlank()) {
					addressCheck = 1;
					bean.setAddress(address);
				} else {
					regFormCheck = 0;
					addressCheck = 0;
					registerErrorMessage = "Address input invalid.";
				}

				// validate city
				city = request.getParameter("city");
				if (!city.isEmpty() && !city.isBlank()) {
					if (city.matches("^[a-zA-Z]*$")) {
						bean.setCity(city);
						regFormCheck = 1;
						cityCheck = 1;
					} else {
						regFormCheck = 0;
						cityCheck = 0;
						registerErrorMessage = "City must only contain letters.";
					}
				} else {
					regFormCheck = 0;
					cityCheck = 0;
					registerErrorMessage = "City input invalid.";
				}

				// validate state
				state = request.getParameter("state");
				if ((!state.isEmpty() && !state.isBlank())) {
					if (state.length() == 2) {
						stateCheck = 1;
						bean.setState(state);
					} else {
						regFormCheck = 0;
						stateCheck = 0;
						registerErrorMessage = "State must be 2 capital letters.";
					}
				} else {
					regFormCheck = 0;
					stateCheck = 0;
					registerErrorMessage = "State input invalid.";
				}

				// validate postalCode
				shippingZip = request.getParameter("postalCode");
				if (!shippingZip.isEmpty() && !shippingZip.isBlank()) {
					if (shippingZip.length() == 5) {
						try {
							bean.setShippingZip(shippingZip);
							shippingZipCheck = 1;
						} catch (NumberFormatException e) {
							regFormCheck = 0;
							shippingZipCheck = 0;
							registerErrorMessage = "Shipping zip Code must be a number.";
						}
					} else {
						regFormCheck = 0;
						shippingZipCheck = 0;
						registerErrorMessage = "Shipping zip Code must be a 5 digits.";
					}
				} else {
					regFormCheck = 0;
					shippingZipCheck = 0;
					registerErrorMessage = "Shipping zip Code input not valid.";
				}

				if (addressCheck == 1 && cityCheck == 1 && stateCheck == 1 && shippingZipCheck == 1) {
					fullAddress = address + " " + city + ", " + state + " " + shippingZip;
					bean.setFullAddress(fullAddress);
				}
				
				//Billing zip code
				billingZip = request.getParameter("billingPostalCode");
				if (!billingZip.isEmpty() && !billingZip.isBlank()) {
					if (billingZip.length() == 5) {
						try {
							bean.setBillingZip(billingZip);
							billingZipCheck = 1;
						} catch (NumberFormatException e) {
							regFormCheck = 0;
							billingZipCheck = 0;
							registerErrorMessage = "Billing zip Code must be a number.";
						}
					} else {
						regFormCheck = 0;
						billingZipCheck = 0;
						registerErrorMessage = "Billing zip Code must be a 5 digits.";
					}
				} else {
					regFormCheck = 0;
					billingZipCheck = 0;
					registerErrorMessage = "Billing zip Code input not valid.";
				}
				
				
				// check if form is correct
				if (regFormCheck == 1 && usernameRegCheck == 1 && passwordRegCheck ==1 && firstNameCheck ==1 && lastNameCheck ==1 && phoneCheck ==1 && regEmailCheck ==1 && addressCheck ==1 && cityCheck ==1 && stateCheck ==1 && shippingZipCheck ==1 && billingZipCheck ==1) {
					//post to DB
					Database database = new Database();
					database.setSearchAccounts();
					
					uuid = null;
					uuid = UUID.randomUUID();
					bean.setAccountId(uuid.toString());

					try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
						statement.executeUpdate("INSERT INTO ACCOUNTS (ACCOUNT_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, PHONE, EMAIL, ADDRESS, CITY, STATE, SHIPPING_ZIP, FULL_ADDRESS, BILLING_ZIP) VALUES ('"
								+ uuid + "', '" + bean.getUsername() + "', '" + bean.getPassword() + "', '" + bean.getFirstName() + "', '" + bean.getLastName() + "', '" + bean.getPhone() + "', '" + bean.getEmail() + "', '" + bean.getAddress() + "', '" + bean.getCity() + ""
										+ "', ' " + bean.getState() + "', '" + bean.getShippingZip() + "', '" + bean.getFullAddress() + "', '" + bean.getBillingZip() + "'); ");
						database.con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					registerErrorMessage = "Registration Successfull! You may now login.";
					bean.setRegisterError(registerErrorMessage);
					bean.setLoginCorrect(false);
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				} else {
					bean.setRegisterError(registerErrorMessage);
					bean.setLoginCorrect(false);
					session.setAttribute("bean", bean);
					RequestDispatcher rd = request.getRequestDispatcher("/account.jsp");
					rd.forward(request, response);
				}
		}

		
		if(request.getParameter("form").equals("Proceed to Checkout")) {
			Bean bean = null;
			if (session.getAttribute("bean") == null || session.getAttribute("bean").equals("")) {
				bean = new Bean();
			} else {
				bean = (Bean)session.getAttribute("bean");
				bean.setCreditCardCheck(null);
				
			}
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}
		
		
		if(request.getParameter("form").equals("Complete Purchase")) {
			Bean bean = (Bean)session.getAttribute("bean");
			creditCard = request.getParameter("creditCard");
			if (!creditCard.isEmpty() && !creditCard.isBlank()) {
				Pattern ptrn = Pattern.compile("[0-9]{16}");
				Matcher match = ptrn.matcher(creditCard);
				if (match.find()) {
					creditCardCheck = 1;
					
					Database database = new Database();
					database.setSearchCarts();
					try (ResultSet result = database.getResult(); Statement statement = database.con.createStatement();) {
						
						statement.executeUpdate("DELETE FROM CARTS;");
						
						database.con.close();
					} catch (SQLException e) {
					}
				} else {
					creditCardCheck = 0;
					completePurchaseMessage = "Credit card number is invalid.";
				}
			} else {
				creditCardCheck = 0;
				completePurchaseMessage = "No credit card entered.";
			}
			
			bean.setCreditCardCheck(creditCardCheck);
			bean.setCompletePurchaseMessage(completePurchaseMessage);
			session.setAttribute("bean", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
