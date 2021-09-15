/**
 * @author varunkashyap
 * File: Bean.java
 * Description: Model bean
 */
package projectJavaClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bean implements Serializable{
	public Bean() {
	}
	
	private ArrayList<ArrayList<String>> productList;
	private ArrayList<ArrayList<String>> itemList;
	private ArrayList<ArrayList<String>> cartItems;
	private String productId;
	private String name;
	private String price;
	private String description;
	private String displayName;
	private String accountId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String billingZip;
	private String productError;
	private Integer productCheck;
	private String productDetailError;
	private Integer productDetailCheck;
	private String addToCartMessage;
	private Integer addToCartCheck;
	private String loginError;
	private String registerError;
	private boolean loginCorrect;
	private String address;
	private String city;
	private String state;
	private String shippingZip;
	private String fullAddress;
	private String cartMessage;
	private Integer itemCheck;
	private String itemError;
	private String totalCost;
	private String cartId;
	private Integer totalItems;
	private Integer creditCardCheck;
	private String completePurchaseMessage;
	
	
	public Integer getCreditCardCheck() {
		return creditCardCheck;
	}

	public void setCreditCardCheck(Integer creditCardCheck) {
		this.creditCardCheck = creditCardCheck;
	}

	public String getCompletePurchaseMessage() {
		return completePurchaseMessage;
	}

	public void setCompletePurchaseMessage(String completePurchaseMessage) {
		this.completePurchaseMessage = completePurchaseMessage;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getItemError() {
		return itemError;
	}

	public void setItemError(String itemError) {
		this.itemError = itemError;
	}

	public Integer getItemCheck() {
		return itemCheck;
	}

	public void setItemCheck(Integer itemCheck) {
		this.itemCheck = itemCheck;
	}

	public ArrayList<ArrayList<String>> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<ArrayList<String>> itemList) {
		this.itemList = itemList;
	}

	public String getCartMessage() {
		return cartMessage;
	}

	public void setCartMessage(String cartMessage) {
		this.cartMessage = cartMessage;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getRegisterError() {
		return registerError;
	}

	public void setRegisterError(String registerError) {
		this.registerError = registerError;
	}

	public boolean getLoginCorrect() {
		return loginCorrect;
	}

	public void setLoginCorrect(boolean loginCorrect) {
		this.loginCorrect = loginCorrect;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

	public Integer getAddToCartCheck() {
		return addToCartCheck;
	}

	public void setAddToCartCheck(Integer addToCartCheck) {
		this.addToCartCheck = addToCartCheck;
	}

	public String getAddToCartMessage() {
		return addToCartMessage;
	}

	public void setAddToCartMessage(String addToCartMessage) {
		this.addToCartMessage = addToCartMessage;
	}

	public ArrayList<ArrayList<String>> getCartItems() {
		return cartItems;
	}

	public void setCartItems(ArrayList<ArrayList<String>> cartItems) {
		this.cartItems = cartItems;
	}

	public Integer getProductCheck() {
		return productCheck;
	}

	public void setProductCheck(Integer productCheck) {
		this.productCheck = productCheck;
	}

	public String getProductError() {
		return productError;
	}

	public void setProductError(String productError) {
		this.productError = productError;
	}

	public Integer getProductDetailCheck() {
		return productDetailCheck;
	}

	public void setProductDetailCheck(Integer productDetailCheck) {
		this.productDetailCheck = productDetailCheck;
	}

	public String getProductDetailError() {
		return productDetailError;
	}

	public void setProductDetailError(String productDetailError) {
		this.productDetailError = productDetailError;
	}

	public ArrayList<ArrayList<String>> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ArrayList<String>> productList) {
		this.productList = productList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBillingZip() {
		return billingZip;
	}

	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getShippingZip() {
		return shippingZip;
	}

	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
	}


	
}
