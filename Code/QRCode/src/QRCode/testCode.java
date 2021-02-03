
package QRCode;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Event;
class testCode extends JFrame

{ 
	JPanel thePanel = new JPanel();
    JLabel label1 = new JLabel("Tell me something");
    JButton button = new JButton("button");
    JTextArea textArea1 = new JTextArea(15,20);
    JTextField dataToEncode = new JTextField(" ");
    int clicked;

	public static void main(String[] args) 
	{ 
	new testCode();
} 
	
	public testCode() {
	
		
		this.setSize(400, 400);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		JScrollPane scrollbar1 = new JScrollPane(textArea1);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1.setText("New Text");

        label1.setToolTipText("Doesn't do anything");
        
        thePanel.add(label1);
        thePanel.add(dataToEncode);
		
		thePanel.add(scrollbar1);
		thePanel.add(button);
		this.add(thePanel);
		this.setVisible(true);
		
		ListenForButton listenerB = new ListenForButton();
		
		button.addActionListener(listenerB);
		

	}                      



private class ListenForButton implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
	
			clicked++;
			System.out.println(textArea1.getText());
			
		}
		
	}
	
}
}