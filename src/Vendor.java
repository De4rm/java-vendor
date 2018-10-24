import java.io.IOException;



public class Vendor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		FileManager fileManager = new FileManager();
		try {
			fileManager.read_file();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu.make_interface();
	}

}


//JAVA FIELD

class FieldsData{
	String name;
	Float price;
	int quantity;
}






