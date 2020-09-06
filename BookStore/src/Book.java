import java.awt.Label;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
	public static File file;
	public static FileOutputStream ofos;
	public static ObjectOutputStream ooos;
	public static FileInputStream ofis;
	public static ObjectInputStream oois;
	
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
		
	}
	
	
	public static void initializeIO()
	{
		try
		{
		file=new File("Books.dat");
		ofos=new FileOutputStream(file,true);
		ooos=new ObjectOutputStream(ofos);
		ofis=new FileInputStream(file);
		oois=new ObjectInputStream(ofis);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void closeIO()
	{
		try
		{
			ofos.close();
			ooos.close();
			ofis.close();
			oois.close();
		}
		catch(IOException e)
		{
			
		}
	}
	
	public static boolean storeBookDetails(Book book)
	{
		boolean success=false;
		System.out.println("\n In Book->storeBookDetails");
		/*
		try
		{
			ooos.writeObject(book);
			//ofos.close();
			//ooos.close();
			System.out.println("\n String Book obj :"+book.bookId+" name:"+book.bookName);
		}
		catch(Exception e)
		{
			System.out.println("\n Exception while Book->storeBookDetails");
			LabelClass lc=new LabelClass("Error",new String("Error while saving Data!!"));
			lc.setVisible(true);
			lc.setLocation(400, 300);
			lc.setSize(300, 150);
		}*/
		return success;
	}
	
	public static boolean editBookDetails(Book book)
	{
		boolean success=false;
		
		try
		{
			
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
			System.out.println("\n In Book->bookRecords method");
			/*while(true)
			{
				try 
				{
					if(!(file.length()==0)|| file.exists())
					{
					Book b;
					b = (Book)oois.readObject();
					System.out.println(b);
					books.add(b);
					}
					else
					{
						//ofis.close(); oois.close(); 
						break;
					}
				} 
				catch(EOFException e)
				{
					System.out.println("\n  EOFException in Book->booksRecords");
					try{//ofis.close(); oois.close(); 
					break; } catch(Exception ee){}
				}
				catch(FileNotFoundException e)
				{
					System.out.println("\n  FileNotFoundException in Book->booksRecords");
					//ofis.close(); oois.close(); 
					break;
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
					System.out.println("\n IOException in Book->booksRecords");
					//ofis.close(); oois.close(); 
					break;
				}
				catch(Exception e)
				{
					System.out.println("\n Exception in Book->booksRecords");
					e.printStackTrace();
					//ofis.close(); oois.close(); 
					break;
				}
			}*/
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
}
