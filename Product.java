package beans;

public class Product {
	public String name="";
	public int price=0;
	public int quantity=0;
	public int total=0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getprice() {
		return price;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total=total;
	}

}
