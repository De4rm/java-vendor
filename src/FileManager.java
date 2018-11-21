import java.io.*;
import java.util.*;

public class FileManager {
	static String filePath;
	static String line = null;
	static FileReader fileReader;
	static BufferedReader buff_read = null;
	static String[][] data = new String[6][];
	static Product[] datas = new Product[6];
	
	FileManager(String path){
		filePath = path;
		try {
			fileReader = new FileReader(filePath);
			buff_read = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void read_file() throws IOException {
		if (buff_read != null) {
			line = buff_read.readLine();
			int i = 0;
			while(line != null) {
				data[i] = line.split(",");
				
				Product fd = new Product(data[i][0], Float.valueOf(data[i][1]), 
							Integer.valueOf(data[i][2]));
				datas[i] = fd;
				
				line = buff_read.readLine();
				i++;
			}
		}
		
	}
	
	public static MoneyT[] readMoney() throws IOException {
		MoneyT[] rs = new MoneyT[5];
		if (buff_read != null) {
			line = buff_read.readLine();
			
			int i = 0;
			while(line != null) {
				String[] spt = line.split(",");
				if ( spt[1] != null ) {
					
					String name = spt[0];
					int quantity = Integer.valueOf(spt[1]);
					
					MoneyT m = new MoneyT(name, quantity);
					rs[i] = m;					
				}
				line = buff_read.readLine();
				i++;
			}
		}
		
		return rs;
		
	}

	public void write_file() throws FileNotFoundException {
		
		PrintWriter out = new PrintWriter(filePath);
		
		for(int i=0;i<datas.length;i++) {
			Product current_data = datas[i];
			out.println(current_data.name+","+current_data.price+","+current_data.quantity);
		}
	}
	


}
