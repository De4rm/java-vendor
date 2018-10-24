import java.io.*;


//CLASS FILEMANAGER OPEN FILE, READ DATA, WRITE FILE
public class FileManager{
	
	static String file_name = "/home/de4rm/Documents/Java/file.txt";
	static String line = null;
	static FileReader fileReader;
	static BufferedReader buff_read = null;
	static String[][] data = new String[6][];
	static FieldsData[] datas = new FieldsData[6];
	
	FileManager(){
		try {
			fileReader = new FileReader(file_name);
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
				for(int j=0;j<data[i].length;j++) {
					FieldsData fd = new FieldsData();
					fd.name = data[i][0];
					fd.price = Float.valueOf(data[i][1]);
					fd.quantity = Integer.valueOf(data[i][2]);
					datas[i] = fd;
				}
				line = buff_read.readLine();
				i++;
			}
		}
		
	}

	public void write_file() throws FileNotFoundException {
		
		PrintWriter out = new PrintWriter(file_name);
		
		for(int i=0;i<datas.length;i++) {
			FieldsData current_data = datas[i];
			out.println(current_data.name+","+current_data.price+","+current_data.quantity);
		}
	}

}
