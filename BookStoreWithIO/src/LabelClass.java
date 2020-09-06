import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class LabelClass extends JFrame implements ActionListener
{
	String name;
	JTextArea jtextarea; 
	JButton ok;
	
	LabelClass(String name,String msg)
	{
		super(name);
		this.name=name;
		this.jtextarea=new JTextArea();
		jtextarea.setText(msg);
		jtextarea.setEditable(false);
		this.setLayout(null);
		
		this.jtextarea.setBounds(10, 10, 250, 50);
		ok=new JButton("OK");
		ok.setBounds(100, 70, 60, 30);
		add(this.jtextarea); add(ok);
		
		ok.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		String lab=arg0.getActionCommand();
			if(lab.equals("OK"))
			{
				this.setVisible(false);
				dispose();
			}
		
	}

}
