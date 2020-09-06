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
import java.util.Iterator;

//Note : Not creating new objectoutputstream every time writing to the file , so whenever storeBookDetails called , book will be written with same header
// Creating New objectinputstream everytime, as it exausts in one booksRecords method call

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
		ofos=new FileOutputStream(file);  //add true in arguments for append mode
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
	
	public static void initialLoad()
	{
		try
		{
			ArrayList<Book> al=booksRecords();
			ofos=new FileOutputStream(file);
			ooos=new ObjectOutputStream(ofos);
			
			Iterator iterator=al.iterator();
			while(iterator.hasNext())
			{
				Book bb=(Book)iterator.next();
				ooos.writeObject(bb);
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public static boolean storeBookDetails(Book book)
	{
		boolean success=false;
		System.out.println("\n In Book->storeBookDetails");
		
		try
		{
			ooos.writeObject(book);
			success=true;
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
			ArrayList<Book> allBooks=Book.booksRecords();
			Iterator<Book> i=allBooks.iterator();
			int index=0;
			while(i.hasNext())
			{
				Book bb=i.next();
				if(bb.bookId.equals(book.bookId))
				{
					//bb=book;
					allBooks.remove(index);
					allBooks.add(book);
					System.out.println("Book changed to :"+bb.bookName);
				}
				index++;
			}
			ofos=new FileOutputStream(file);// not in append mode so auto clearing
			ooos=new ObjectOutputStream(ofos);
			// now write all objects
			Iterator<Book> ii=allBooks.iterator();
			while(ii.hasNext())
			{
				Book bb=ii.next();
				System.out.println("\n Writing Book : "+bb.bookId+" "+bb.bookName);
				ooos.writeObject(bb);
			}
			success=true;
			
			
		}
		catch(Exception e)
		{
			//LabelClass lc=new LabelClass("Error",new String("Error while saving Data!!"));
			//lc.setVisible(true);
			//lc.setLocation(400, 300);
			//lc.setSize(300, 150);
			System.out.println("\n Error while editing data");
			e.printStackTrace();
			success=false;
		}
		
		return success;
	}
	
	public static ArrayList<Book> booksRecords()
	{
		ArrayList<Book> books =new ArrayList<Book>();
		
		try
		{
			ofis=new FileInputStream(file);
			oois=new ObjectInputStream(ofis);
			System.out.println("\n In Book->bookRecords method");
			while(true)
			{
				try 
				{
					Book b;
					b = (Book)oois.readObject();
					System.out.println("\n Reading Book:"+b.bookId);
					books.add(b);
				} 
				catch(EOFException e)
				{
					System.out.println("\n  EOFException in Book->booksRecords");
					oois.close(); ofis.close();
					//try{ofis.close(); oois.close(); 
					//break; } catch(Exception ee){}
					break;
				}
				catch(FileNotFoundException e)
				{
					System.out.println("\n  FileNotFoundException in Book->booksRecords");
					oois.close(); ofis.close();
					//ofis.close(); oois.close(); 
					break;
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.out.println("\n IOException in Book->booksRecords");
					e.printStackTrace();
					//oois.close(); ofis.close();
					//ofis.close(); oois.close(); 
					break;
				}
				catch(Exception e)
				{
					System.out.println("\n Exception in Book->booksRecords");
					e.printStackTrace();
					oois.close(); ofis.close();
					//ofis.close(); oois.close(); 
					break;
				}
			}
		}
		catch(Exception io)
		{
			
		}
		return books;
	}
	
	
	public static boolean deleteRecord(String bookId)
	{
		boolean success=false;
		try
		{
			ArrayList<Book> allBooks=Book.booksRecords();
			Iterator<Book> i=allBooks.iterator();
			int index=0;
			int indexToRemove=0;
			while(i.hasNext())
			{
				Book bb=i.next();
				if(bb.bookId.equals(bookId))
				{
					System.out.println("Removing Book :"+bookId);
					//allBooks.remove(index); throws concurrent modification exception as trying to change arraylist while iterating over it
					indexToRemove=index;
				}
				index++;
			}
			allBooks.remove(indexToRemove);
			ofos=new FileOutputStream(file);// not in append mode so auto clearing
			ooos=new ObjectOutputStream(ofos);
			// now write all objects
			Iterator<Book> ii=allBooks.iterator();
			while(ii.hasNext())
			{
				Book bb=ii.next();
				System.out.println("\n Writing Book : "+bb.bookId+" "+bb.bookName);
				ooos.writeObject(bb);
			}
			success=true;
			
		}
		catch(Exception e)
		{
			System.out.println("\n Exception in Book->deleteRecord");
			e.printStackTrace();
		}
		return success;
	}
}
