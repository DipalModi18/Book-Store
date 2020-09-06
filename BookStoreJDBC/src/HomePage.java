import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class HomePage extends JFrame implements ActionListener
{
	JButton jbInsertBook;
	JButton jbSearchBook;
	JButton jbEditBook;
	JButton jbDelete;
	JButton jbClear;
	Font f;
	JPanel jpMenu, jpCrud;
	String currentFrame;
	
	JLabel jlInsertBook, jlBookId, jlBookname, jlAuthor, jlPrice, jlGenre, jlAvailable, jlPublication;
	JTextField jtBookId, jtBookname, jtPrice, jtGenre, jtAvailable, jtPublication;
	JTextField[] jtAuthor=new JTextField[3];
	JButton jbInsert, jbCancel, jbUpdatePrice;
	Book book;
	Font f1,f2;
	Container c;
	ViewBooks vb;
	
		HomePage()
		{
			super("MY BOOKSTORE");
			setLayout(null);
			c=this.getContentPane();
			c.setPreferredSize(new Dimension(800,700));
			
			f=new Font("Arial",Font.BOLD,35);
			createJpMenu();
			createJpCrud();
			currentFrame="";
			
			add(jpMenu); pack();
			add(jpCrud); pack();
			jbInsertBook.addActionListener(this);
			jbSearchBook.addActionListener(this);
			jbEditBook.addActionListener(this);
			jbDelete.addActionListener(this);
			jbClear.addActionListener(this);
			jbUpdatePrice.addActionListener(this);
		}

		public void createJpMenu()
		{
			jpMenu = new JPanel();
			jpMenu.setLayout(null);
			jpMenu.setBounds(0, 0, 800, 30);
			jpMenu.setBackground(Color.DARK_GRAY);
			//jlWelcome=new JLabel("Welcome to the Book-Store");
			jbInsertBook=new JButton("INSERT");
			jbSearchBook=new JButton("SEARCH");
			jbEditBook = new JButton("EDIT");
			jbDelete=new JButton("DELETE");
			jbClear=new JButton("CLEAR");
			jbUpdatePrice=new JButton("UPDATE PRICE");
			
			//jlWelcome.setBounds(180, 20, 600, 50);
			//jlWelcome.setFont(f);
			jbInsertBook.setBounds(25, 5, 100, 20);
			jbSearchBook.setBounds(150, 5, 100, 20);
			jbEditBook.setBounds(275, 5, 100, 20);
			jbDelete.setBounds(400, 5, 100, 20);
			jbClear.setBounds(525, 5, 100, 20);
			jbUpdatePrice.setBounds(650, 5, 120, 20);
			
			jbInsertBook.setActionCommand("INSERT");
			jbSearchBook.setActionCommand("SEARCH");
			jbEditBook.setActionCommand("EDIT");
			jbDelete.setActionCommand("DELETE");
			jbClear.setActionCommand("CLEAR");
			jbUpdatePrice.setActionCommand("UPDATE PRICE");
			
			//jpMenu.add(jlWelcome);
			jpMenu.add(jbInsertBook);
			jpMenu.add(jbSearchBook);
			jpMenu.add(jbEditBook);
			jpMenu.add(jbDelete);
			jpMenu.add(jbClear);
			jpMenu.add(jbUpdatePrice);
		}
		
		
		public void createJpCrud()
		{
			jpCrud= new JPanel();
			jpCrud.setLayout(null);
			jpCrud.setBounds(0, 0, 800, 500);
			Color col=new Color(240, 220, 240);
			jpCrud.setBackground(col);
			jpCrud.removeAll();
			jpCrud.revalidate();
			//jpCrud.doLayout();
			vb=new ViewBooks();
			vb.setBounds(0, 100, 800, 600);
			jpCrud.add(vb); 
			jpCrud.repaint();
			vb.setVisible(true);
			pack();				
		}
		
		
		public void actionPerformed(ActionEvent ae) 
		{
			// TODO Auto-generated method stub
			String button=ae.getActionCommand();
			System.out.println("\n Button Pressed:"+button);
			if(button.equals("INSERT"))
			{
				System.out.println("\n In HomePage->Insert action command");
				vb.InsertDetails();		
				vb.clearDetails();
			}
			else if(button.equals("SEARCH"))
			{
				vb.SearchDetails();
			}
			else if(button.equals("EDIT"))
			{
				vb.editDetails();
			}
			else if(button.equals("DELETE"))
			{
				vb.deleteDetails();
			}
			else if(button.equals("CLEAR"))
			{
				vb.clearDetails();
			}
			else if(button.equals("UPDATE PRICE"))
			{
				vb.updatePrice();
			}
		}

}
