import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class Menu {
	
	static JFrame frame = new JFrame(); 
	static JButton putMoneyBtn = new JButton("Run");
	static JButton rechange = new JButton("Fill Back");
	static JTextField moneyField = new JTextField(6);
	static JLabel lblInfo = new JLabel("Enter Money:");
	static FileManager fManager;
	static int d = 0;
	static Money money; 
	
	Menu(){
		fManager = new FileManager("/home/de4rm/Documents/Java/file.txt");
		try {
			fManager.read_file();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		money = new Money("/home/de4rm/Documents/Java/money.txt");
	}
	

	public static void make_interface(){
		
				
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
		
		
		addVendingButtons(c, resettingPanel);
		
		
		
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
				  Float monedIn = Float.valueOf(moneyField.getText());
				  if (money.CheckMoned(monedIn)) {
					 money.insertMoney(monedIn);
				  } else {
					  JOptionPane.showMessageDialog(null, "No Moned with this value.");
				  }
				  } 
				} );
		resettingPanel.add(putMoneyBtn, c);
		
		frame.getContentPane().add(BorderLayout.CENTER,resettingPanel);
		
		frame.setVisible(true);
		

	}
	
	
	private static Product findData(ActionEvent e) {
		int index = Integer.parseInt(e.getActionCommand());
				
		return fManager.datas[index];		
	}
	
	
	private static void addVendingButtons(GridBagConstraints c, JPanel resettingPanel){
		String coord = "024";
		
		for(int y=0; y<2; y++) {
			for(int x=0; x<3; x++) {
				c.gridx = Integer.valueOf(String.valueOf(coord.charAt(x)));
				c.gridy = Integer.valueOf(String.valueOf(coord.charAt(y)));
				
				JButton vendBtn = new JButton(fManager.datas[d].name);
				
				vendBtn.setActionCommand(String.valueOf(d));
				
				vendBtn.addActionListener(new ActionListener() { 
					  public void actionPerformed(ActionEvent e) {
						  
						  Product row = findData(e);
						 //System.out.println(row.name);
						  float price = row.price;
						  if(price<money.moneyIn) {
							 JOptionPane.showMessageDialog(null, "thank you for using java");
							 money.moneyIn -= price;
							 row.quantity--;
							 JOptionPane.showMessageDialog(null, money.getMoneyOut());
						  } else {
							  JOptionPane.showMessageDialog(null, "Not enough money.");
						  }
						  
						  } 
						} );
				
				resettingPanel.add(vendBtn, c);
				d++;
				
			}
		}
	}

}
