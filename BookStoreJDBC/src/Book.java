import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Book implements Serializable
{
	public String bookId;
	public String bookName;
	public String author;
	public int price;
	public String genre;
	public int available;
	public String publication;
	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	
	
	Book(String bookId, String bookName, String author, int price, String genre, int available, String publication)
	{
		this.bookId=bookId;
		this.bookName=bookName;
		this.author=author;
		this.price=price;
		this.genre=genre;
		this.available=available;
		this.publication=publication;
	}
	
	Book()
	{
		ViewBooks.InsertDetails((new Book("1","abc","aaa",300,"fiction",4,"xyz")));
		ViewBooks.InsertDetails((new Book("2","qqq","aa",300,"study",4,"xxx")));
		ViewBooks.InsertDetails((new Book("3","www","aab",300,"study",4,"yyy")));
	}
	
	public static void initializeJDBC()
	{
		try 
		{
			System.out.println("Initializing JDBC");
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","");
			st=con.createStatement();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.out.println("\n Error while initializing JDBC: In Book->initializeJDBC method");
			e.printStackTrace();
		}
	}
	
	public static boolean storeBookDetails(Book book)
	{
		boolean success=false;
		System.out.println("\n In Book->storeBookDetails");
		
		try
		{
			if(st==null)
			{
				System.out.println("Statement st is null");
			}
			int rowsAffected= st.executeUpdate("insert into BooksRecords values('"+book.bookId+"','"+book.bookName+"','"+book.author+"',"+book.price+",'"+book.genre+"',"+book.available+",'"+book.publication+"');");
			if(rowsAffected==1)
			{
				success=true;
			}
			System.out.println("\n String Book obj :"+book.bookId+" name:"+book.bookName);
			
		}
		catch(Exception e)
		{
			System.out.println("\n Exception while Book->storeBookDetails");
			e.printStackTrace();
			LabelClass lc=new LabelClass("Error",new String("Error while saving Data!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
		return success;
	}
	
	public static boolean editBookDetails(Book book)
	{
		boolean success=false;
		
		try
		{
			//update booksrecords set bookname='xyz' WHERE bookId='2';
			int rowsAffected=st.executeUpdate("update BooksRecords set bookName='"+book.bookName+"', author='"+book.author+"', price="+book.price+", genre='"+book.genre+"', available="+book.available+", publication='"+book.publication+"' WHERE bookId='"+book.bookId+"'");
			if(rowsAffected==1)
			{
				success=true;
			}
			else if(rowsAffected==0)
			{
				LabelClass lc=new LabelClass("Error",new String("Corresponding record not found!!"));
				lc.setVisible(true);
				lc.setLocation(400, 300);
				lc.setSize(300, 150);
			}
		}
		catch(Exception e)
		{
			LabelClass lc=new LabelClass("Error",new String("Error while saving Data!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
		
		return success;
	}
	
	public static ArrayList<Book> booksRecords()
	{
		ArrayList<Book> books =new ArrayList<Book>();
		
		try
		{
			/*ofis=new FileInputStream(file);
			oois=new ObjectInputStream(ofis);*/
			rs=st.executeQuery("select * from BooksRecords");
			System.out.println("\n In Book->bookRecords method");
			while(rs.next())
			{
				try 
				{
					Book b=new Book(rs.getString("bookId"),rs.getString("bookName"),rs.getString("author"),rs.getInt("price"),rs.getString("genre"),rs.getInt("available"),rs.getString("publication"));
					books.add(b);
					System.out.println("\n Reading Book:"+b.bookId);
				} 
				catch(Exception e)
				{
					System.out.println("\n Exception in Book->booksRecords");
					e.printStackTrace();
					break;
				}
			}
		}
		catch(Exception io)
		{
			
		}
		return books;
	}
	
	public static ArrayList<Book> searchBook(Book book)
	{
		try
		{
		ArrayList<Book> books=new ArrayList<Book>();
			//write search code
		
			//Globals.file=new File("Books.dat");
			//Globals.ofis=new FileInputStream(Globals.file);
			//Globals.oois=new ObjectInputStream(Globals.ofis);
			System.out.println("\n In Book->searchBook method");
			/*while(true)
			{
				try 
				{
					Book b;
					b = (Book)oois.readObject();
					System.out.println(b);
					if(book.bookId=="")
					{
						
					}
					else
					{
						if(book.bookName=="")
						{
							if(book.bookId.equals(b.bookId)){books.add(b);}
						}
						else if(book.bookId.equals(b.bookId) && book.bookName.equals(b.bookName))
						{
							books.add(b);
						}
					}
					
				} 
				catch(EOFException e)
				{
					try{//ofis.close(); oois.close(); 
					break;} catch(Exception ee){}
				}
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			return books;
		}
		catch(Exception e)
		{
			return new ArrayList<Book>();
		}
		
	}
	
	
	public static boolean deleteRecord(String bookId)
	{
		boolean success=false;
		try
		{
			int rowsAffected=st.executeUpdate("delete from BooksRecords where bookId='"+bookId+"'");
			success=true;
		}
		catch(Exception e)
		{
			System.out.println("Eror while deleting record");
			e.printStackTrace();
		}
		return success;
	}
	
	
	public static boolean increasePrices()
	{
		boolean success=false;
		/*CREATE PROCEDURE increasePrice()
			update booksrecords set price=price*1.1 where price>=500;
			update booksrecords set price=price*1.05 where price<500;*/
		try
		{
			CallableStatement cstmt=con.prepareCall("{call increasePrice()}");
			cstmt.executeUpdate();
			success=true;
		}
		catch(Exception e)
		{
			LabelClass lc=new LabelClass("Error",new String("Error while Calling procedure!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}
		return success;
	}
}
