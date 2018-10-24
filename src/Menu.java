import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


//CLASS MENU CREATE APP INTERFACE
public class Menu{
	
	static JFrame frame = new JFrame(); 
	static JButton putMoneyBtn = new JButton("Run");
	static JButton rechange = new JButton("Fill Back");
	static JTextField moneyField = new JTextField(6);
	static JLabel lblInfo = new JLabel("Enter Money:");
	static FileManager fManager = new FileManager();
	static Float moneyIn=(float) 0;
	static int d = 0;
	
	public static void make_interface(){
		
		try {
			fManager.read_file();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		frame.setTitle("Vendor Machine");

		frame.setLayout(new BorderLayout());

		frame.setSize(new Dimension(800, 650));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		
		
		JPanel resettingPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//Add button "Fill Back"
		
		
		JLabel moneyInLbl = new JLabel("");
		JLabel lblInfo = new JLabel("Enter Money:");
				
		
		//EXAMPLE OF IMAGE BUTTONS
		//BufferedImage buttonIcon = ImageIO.read(new File("buttonIconPath"));
		//button = new JButton(new ImageIcon(buttonIcon));
		
		
		
		
		c.gridheight = 2;
		c.gridheight = 2;
		c.ipady = 60;
		
		c.weightx = 0.1;
		c.weighty = 0.1;
		
		
		String coord = "024";
		
		for(int y=0; y<2; y++) {
			for(int x=0; x<3; x++) {
				c.gridx = Integer.valueOf(String.valueOf(coord.charAt(x)));
				c.gridy = Integer.valueOf(String.valueOf(coord.charAt(y)));
				
				JButton vendingbtn = new JButton(fManager.datas[d].name);
	
				vendingbtn.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) {
						  FieldsData row = findData(e);
						  float price = row.price;
						  if(price<moneyIn) {
							  //JOptionPane.showMessageDialog(null, "thank you for using java");
							  moneyIn -= price;
							  row.quantity--;
							  System.out.println(row.quantity);
							  System.out.println(moneyIn);
						  } else {
							  System.out.println(row.quantity);
						  }
						  
						  } 
						} );
				
				resettingPanel.add(vendingbtn, c);
				d++;
				
			}
		}
		
		
		
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 4;
		resettingPanel.add(lblInfo, c);
		
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 4;
		resettingPanel.add(moneyField, c);
		
		c.gridx = 4;
		c.gridy = 4;
		putMoneyBtn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  moneyIn = Float.valueOf(moneyField.getText());
				  } 
				} );
		resettingPanel.add(putMoneyBtn, c);
		
		frame.getContentPane().add(BorderLayout.CENTER,resettingPanel);
		
		frame.setVisible(true);
		

	}
	
	
	private static FieldsData findData(ActionEvent e) {
		
		for(int i=0; i<fManager.datas.length; i++) {
			if (fManager.datas[i].name == e.getActionCommand()) {
				return fManager.datas[i];
			}
		}
		
		return null;
		
	}
	
}
