import java.io.*;



public class VendorMachine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.make_interface();

	}

}

//Class Item describe fields that need an item
class Item{
	String name;
	float price;
	int quantity;
}


//Class product inherit all attributes from 
//class Item
class Product extends Item{
	
	//constructor of class Product with inherited
	//attributes
	Product(String name, Float price, int quantity){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
}

class MoneyT{
	String moneyT;
	int quantity;
	
	MoneyT(String m, int q){
		moneyT = m;
		quantity = q;
	}
}

