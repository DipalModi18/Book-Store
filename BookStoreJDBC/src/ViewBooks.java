import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewBooks extends JPanel implements ActionListener
{
	static JLabel jlInsertBook, jlBookId, jlBookname, jlAuthor, jlPrice, jlGenre, jlAvailable, jlPublication;
	static JTextField jtBookId, jtBookname, jtPrice, jtGenre, jtAvailable, jtPublication;
	static JTextField jtAuthor;
	//JButton jbInsert;
	static JButton jbNext, jbPrevious;//jbCancel
	static Book book;
	static Font f1,f2;
	static ArrayList<Book> books;
	static int currentBook;
	
	ViewBooks()
	{
		setLayout(null);
		Book.initializeJDBC();
		
		f1=new Font("Arial",Font.BOLD,35);
		f2=new Font("Arial",Font.BOLD,20);
		Color col=new Color(240, 220, 240);
		this.setBackground(col);
		
		jlInsertBook = new JLabel("Book Details");
		jlInsertBook.setFont(f1);
		jlBookId = new JLabel("BOOK ID :");
		jlBookId.setFont(f2);
		jlBookname=new JLabel("BOOK TITLE :");
		jlBookname.setFont(f2);
		jlAuthor=new JLabel("AUTHOR :");
		jlAuthor.setFont(f2);
		jlPrice=new JLabel("PRICE :");
		jlPrice.setFont(f2);
		jlGenre=new JLabel("GENRE :");
		jlGenre.setFont(f2);
		jlAvailable=new JLabel("AVAILABLE :");
		jlAvailable.setFont(f2);
		jlPublication=new JLabel("PUBLICATION :");
		jlPublication.setFont(f2);
		jtBookId=new JTextField();
		jtBookname=new JTextField();
		jtPrice=new JTextField();
		jtGenre=new JTextField();
		jtAvailable=new JTextField();
		jtPublication=new JTextField();
		jtAuthor=new JTextField();
		jbNext= new JButton("NEXT");
		jbPrevious = new JButton("PREVIOUS");
		jbNext.setActionCommand("NEXT");
		jbPrevious.setActionCommand("PREVIOUS");
		
		jlInsertBook.setBounds(300, 0, 400, 50);
		jlBookId.setBounds(20, 100, 160, 30);
		jtBookId.setBounds(200, 100, 160, 30);
		jlBookname.setBounds(420, 100, 160, 30);
		jtBookname.setBounds(600, 100, 160, 30);
		jlPrice.setBounds(20, 150, 160, 30);
		jtPrice.setBounds(200, 150, 160, 30);
		jlGenre.setBounds(420, 150, 160, 30);
		jtGenre.setBounds(600, 150, 160, 30);
		jlAvailable.setBounds(20, 200, 160, 30);
		jtAvailable.setBounds(200, 200, 160, 30);
		jlPublication.setBounds(420, 200, 160, 30);
		jtPublication.setBounds(600, 200, 160, 30);
		jlAuthor.setBounds(20, 250, 160, 30);
		jtAuthor.setBounds(200, 250, 160, 30);
		jbPrevious.setBounds(100, 300, 100, 20);
		jbNext.setBounds(600, 300, 100, 20);
		books=new ArrayList<Book>();
		books= Book.booksRecords();
		jbNext.setEnabled(false);
		jbPrevious.setEnabled(false);
		//Book b=new Book();
		
		add(jlInsertBook); 
		add(jlBookId); add(jtBookId); add(jlBookname); add(jtBookname);
		add(jlPrice); add(jtPrice); add(jlGenre); add(jtGenre);
		add(jlAvailable); add(jtAvailable); add(jlPublication); add(jtPublication);
		add(jlAuthor); add(jtAuthor); 
		add(jbPrevious); add(jbNext);
		
		//showBookKth(0);
		currentBook=0;
		//jbInsert.addActionListener(this);
		//jbCancel.addActionListener(this);
		jbPrevious.addActionListener(this);
		jbNext.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		String button=ae.getActionCommand();
		System.out.println("BUTTON :"+button);
		
		if(button.equals("CANCEL"))
		{
			jtBookId.setText("");
			jtBookname.setText("");
			jtPrice.setText("");
			jtGenre.setText("");
			jtAvailable.setText("");
			jtPublication.setText("");
			jtAuthor.setText("");
			
			jbNext.setEnabled(false);
			jbPrevious.setEnabled(false);
		}
		else if(button.equals("NEXT"))
		{
			System.out.println(" ARRAY LIST SIZE :"+books.size()+" currentBook:"+currentBook);
			if((currentBook+1)<books.size())
			{
				showBookKth(currentBook+1);
			}
		}
		else if(button.equals("PREVIOUS"))
		{
			System.out.println(" ARRAY LIST SIZE :"+books.size()+" currentBook:"+currentBook);
			if((currentBook-1)>=0)
			{
				showBookKth(currentBook-1);
			}
		}
		
	}
	
	public static void InsertDetails()
	{
		System.out.println("\n In ViewBooks->InsertDetails method before validation");
		if(validateData())
		{
			System.out.println("\n In ViewBooks->InsertDetails method validating");
			String author=jtAuthor.getText();
			book=new Book(jtBookId.getText(),jtBookname.getText(),author,Integer.parseInt(jtPrice.getText()),jtGenre.getText(),Integer.parseInt(jtAvailable.getText()),jtPublication.getText());
			books.add(book);
			System.out.println("In ViewBooks->InsertDetails method: storing book(bookid:"+book.bookId+", bookname:"+book.bookName);
			boolean success=Book.storeBookDetails(book);
			if(success)
			{
				LabelClass lc=new LabelClass("Hurray!",new String("Data Saved Successfully!!"));
				lc.setVisible(true);
				lc.setLocation(400, 300);
				lc.setSize(300, 150);
			}
		}	
	}
	
	public static void InsertDetails(Book book)
	{
		System.out.println("\n In ViewBooks->InsertDetails method before validation");
		if(validateData(book))
		{
			books.add(book);
			boolean success=Book.storeBookDetails(book);
			if(success)
			{
				LabelClass lc=new LabelClass("Hurray!",new String("Data Saved Successfully!!"));
				lc.setVisible(true);
				lc.setLocation(400, 300);
				lc.setSize(300, 150);
			}
		}	
	}
	
	public static void SearchDetails()
	{
		if(jtBookId.getText().equals("") && jtBookname.getText().equals("") && jtPublication.getText().equals("") && jtAuthor.getText().equals("") && jtPrice.getText().equals(""))
		{
			books=Book.booksRecords();
			if(books.size()>0)
			{
				showBookKth(0);
				if(books.size()>1){
				jbNext.setEnabled(true);
				jbPrevious.setEnabled(true);}
			}
			return;
		}
		books=Book.booksRecords();
		if(!jtBookId.getText().equals(""))
		{
			if(books.size()>0)
			{
				Iterator<Book> i=books.iterator();
				int index=0;
				while(i.hasNext())
				{
					Book bb=i.next();
					if(!bb.bookId.equals(jtBookId.getText()))
					{
						System.out.println("\n Removing :  bb:"+bb.bookId+"-jtBookId:"+jtBookId.getText());
						i.remove();
						index++;
					}
				}
			}
		}
		if(!jtBookname.getText().equals(""))
		{
			if(books.size()>0)
			{
				Iterator<Book> i=books.iterator();
				int index=0;
				while(i.hasNext())
				{
					Book bb=i.next();
					if(!bb.bookName.equals(jtBookname.getText()))
					{
						System.out.println("\n Removing :  bb:"+bb.bookName+"-jtBookname:"+jtBookname.getText());
						i.remove();
						//books.remove(bb);
						index++;
					}
				}
			}
		}
		if(!jtPublication.getText().equals(""))
		{
			if(books.size()>0)
			{
				Iterator<Book> i=books.iterator();
				int index=0;
				while(i.hasNext())
				{
					Book bb=i.next();
					if(!bb.publication.equals(jtPublication.getText()))
					{
						System.out.println("\n Removing :  bb:"+bb.publication+"-jtPublication:"+jtPublication.getText());
						i.remove();
						//books.remove(bb);
						index++;
					}
				}
			}
		}
		if(!jtPrice.getText().equals(""))
		{
			int price=Integer.parseInt(jtPrice.getText());
			if(books.size()>0)
			{
				Iterator<Book> i=books.iterator();
				int index=0;
				while(i.hasNext())
				{
					Book bb=i.next();
					if(bb.price!=price)
					{
						System.out.println("\n Removing : bb:"+bb.genre+"-jtPublication:"+jtGenre.getText());
						i.remove();
						index++;
					}
				}
			}
		}
		
		if(books.size()>0)
		{
			showBookKth(0);
			if(books.size()>1){
				jbNext.setEnabled(true);
				jbPrevious.setEnabled(true);}
		}
	}
	
	public static void editDetails()
	{
		String author=jtAuthor.getText();
		try{
		book=new Book(jtBookId.getText(),jtBookname.getText(),author,Integer.parseInt(jtPrice.getText()),jtGenre.getText(),Integer.parseInt(jtAvailable.getText()),jtPublication.getText());
		}
		catch(Exception e)
		{
			LabelClass lc=new LabelClass("Error!",new String("Error while finding Corresponding Record!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
		
		Book.editBookDetails(book);
		Iterator<Book> i=books.iterator();
		int index=0;
		boolean found=false;
		System.out.println("\n In ViewBooks->editDetails");
		while(i.hasNext())
		{
			Book bb=i.next();
			System.out.println("\n Iterator already exists:"+bb.bookId+" "+bb.bookName+" "+bb.price);
			System.out.println("\n Iterator new object:"+book.bookId+" "+book.bookName+" "+book.price);
			if(bb.bookId.equals(book.bookId))
			{
				found=true;
				bb=book;//giving reference of new edited book details to bb
				System.out.println("\n Removing book :"+bb.bookId);
				System.out.println("\n Adding book :"+book.bookId);
				books.set(index, book);
				boolean success=true;
				if(success)
				{
					LabelClass lc=new LabelClass("Hurray!",new String("Data Saved Successfully!!"));
					lc.setVisible(true);
					lc.setLocation(400, 300);
					lc.setSize(300, 150);
				}
			}
			index++;
		}
		if(found==false || book.bookId.equals(""))
		{
			LabelClass lc=new LabelClass("Error!",new String("Data Not Found!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
	}
	
	public static boolean validateData()
	{
		System.out.println("\n In ViewBooks->validateData method");
		String msg="";
		boolean validated=false;
		
			if(jtBookId.getText().isEmpty())
			{
				msg=msg+"Enter BookID - ";
			}
			if(jtBookname.getText().isEmpty())
			{
				msg=msg+"Enter Bookname - ";
			}
			try
			{
				if(!jtPrice.getText().isEmpty())
				{
					int price= Integer.parseInt(jtPrice.getText());
				}
			}
			catch(Exception e)
			{
				msg=msg+ "Enter a Valid Price - ";
			}
			try
			{
				if(!jtAvailable.getText().isEmpty())
				{
					int available= Integer.parseInt(jtAvailable.getText());
				}	
			}
			catch(Exception e)
			{
				msg=msg+ "Enter a Valid Available No - ";
			}
		
			if(msg.equals(""))
			{
				return true;
			}
			else
			{
				LabelClass lc=new LabelClass("Error",new String(msg));
				lc.setVisible(true);
				lc.setLocation(400, 300);
				lc.setSize(300, 150);
				return false;
			}
	}
	
	
	
	
	public static boolean validateData(Book book)
	{
		System.out.println("\n In ViewBooks->validateData method");
		String msg="";
		boolean validated=false;
		
			if(book.bookId.isEmpty() || book.bookId=="")
			{
				msg=msg+"Enter BookID - ";
			}
			if(book.bookName.isEmpty())
			{
				msg=msg+"Enter Bookname - ";
			}
			try
			{
				if(book.price>=0)
				{
					int price= book.price;
				}
			}
			catch(Exception e)
			{
				msg=msg+ "Enter a Valid Price - ";
			}
			try
			{
				if(book.available>=0)
				{
					int available= book.available;
				}	
			}
			catch(Exception e)
			{
				msg=msg+ "Enter a Valid Available No - ";
			}
		
			if(msg.equals(""))
			{
				return true;
			}
			else
			{
				LabelClass lc=new LabelClass("Error",new String(msg));
				lc.setVisible(true);
				lc.setLocation(400, 300);
				lc.setSize(300, 150);
				return false;
			}
	}
	
	
	public static void showBookKth(int k)
	{
		if(!books.isEmpty() && k<books.size())
		{
			currentBook=k;
			jtBookId.setText(books.get(k).bookId);
			jtBookname.setText(books.get(k).bookName);
			jtPrice.setText((new Integer(books.get(k).price)).toString());
			jtGenre.setText(books.get(k).genre);
			jtAvailable.setText((new Integer(books.get(k).available)).toString());
			jtPublication.setText(books.get(k).publication);
			if(books.get(k).author!=null || books.get(k).author!="")
			{
				jtAuthor.setText(books.get(k).author);
			}
		}
	}
	
	public static void deleteDetails()
	{
		if(jtBookId.getText().equals(""))
		{
			LabelClass lc=new LabelClass("Error",new String("No BookID Entered for Deletion"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
			return;
		}
		System.out.println("\n Going to delete :"+jtBookId.getText());
		boolean success=Book.deleteRecord(jtBookId.getText());
		clearDetails();
		if(success)
		{
			LabelClass lc=new LabelClass("Hurray!",new String("Record Deleted Successfully!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
		else 
		{
			LabelClass lc=new LabelClass("Error!",new String("No Record Found with this BookID"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
	}
	
	
	public static void clearDetails()
	{
		jtBookId.setText("");
		jtBookname.setText("");
		jtPrice.setText("");
		jtGenre.setText("");
		jtAvailable.setText("");
		jtPublication.setText("");
		jtAuthor.setText("");
		jbNext.setEnabled(false);
		jbPrevious.setEnabled(false);
	}
	
	public static void updatePrice()
	{
		boolean success=Book.increasePrices();
		if(success)
		{
			LabelClass lc=new LabelClass("Hurray!!",new String("Data saved successfully!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
	}	

}
