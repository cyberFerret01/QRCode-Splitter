
package QRCode;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;

class testCode extends JFrame

{ 

	public static void main(String[] args) 
	{ 
	new testCode();
} 
	
	public testCode() {
		
		JPanel thePanel = new JPanel();
        JLabel label1 = new JLabel("Tell me something");
        JButton button = new JButton("button");
        JTextField dataToEncode = new JTextField(" ");
		
		this.setSize(400, 400);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1.setText("New Text");

        label1.setToolTipText("Doesn't do anything");
        
        thePanel.add(label1);
        thePanel.add(dataToEncode);
		
		
		thePanel.add(button);
		this.add(thePanel);
		this.setVisible(true);
		

	}                      

}