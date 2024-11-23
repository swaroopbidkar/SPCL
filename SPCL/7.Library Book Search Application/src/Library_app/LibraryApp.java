package Library_app;
import java.util.*;


class Library{
	int ID;
	String Name;
	String Author;
	double Price;
}


public class LibraryApp {
	
	Library []Books = new Library[25];
	Scanner sc = new Scanner(System.in);
	int numberbooks,i,count;
	
	
	public boolean verify(String tempN,int tempId){
		i=0;
		while(i<numberbooks) {
			if(tempN.equals(Books[i].Name) || tempId == Books[i].ID)
				return false;
			i++;
		}
		
		return true;
	}
	
	public void addNewBook() {
		
		System.out.println("Enter the ID");
		int tempId=sc.nextInt();
		
		System.out.println("Enter the Book Name");
		String tempN=sc.next();
		
		
		System.out.println("Enter the Auther Name");
		String tempA=sc.next();
		
		System.out.println("Enter the price");
		double tempP=sc.nextDouble();
		boolean res= verify(tempN,tempId);
		
		if(res) {
			Books[numberbooks]= new Library();
			Books[numberbooks].Name=tempN;
			Books[numberbooks].ID = tempId;
			Books[numberbooks].Author=tempA;
			Books[numberbooks].Price=tempP;
			numberbooks++;
			System.out.println("Successfully added\n");
		}
		else {
			System.out.println("A book with same name or Id already exits.\nIf you want to try again with another name and Id press any key or press 'N' to return main menu\n");
			if(sc.next().equalsIgnoreCase("N")) 
				choice();
			else {
				addNewBook();
			}
		}
		choice();
			
	}
	
	public void searchBook() {
		System.out.println("1: Search by Name\n2: Search by ID\n3: Search by Author");
		switch(sc.nextInt()) {
			
		case 1: System.out.println("Enter the Name");
			searchByName(sc.next());
			break;
			
		case 2: System.out.println("Enter the Id");
			searchById(sc.nextInt());
			break;
			
		case 3: System.out.println("Enter the Author");
			searchByAuthor(sc.next());
			break;
			
		default:System.out.println("Select correct option ");
		    searchBook();
		
		}
		
	}
	
	public void searchByName(String name){
		i=0;
		while(i<numberbooks) {
			if(name.equals( Books[i].Name)) {
				System.out.println("ID: "+Books[i].ID+" Name: "+Books[i].Name+
						" Author: "+Books[i].Author+" Price: "+Books[i].Price);
				break;
			}
			i++;
		}
		if(numberbooks == i) {
			System.out.println("The book for which you are searching is not found");
		} 
	}
	
	public void searchById(int id) { 
		while(i<numberbooks) {
			if(id == Books[i].ID) {
				System.out.println("ID: "+Books[i].ID+" Name: "+Books[i].Name+
						" Author: "+Books[i].Author+" Price: "+Books[i].Price);
				break;
			}
			i++;
		}
		if(numberbooks == i) {
			System.out.println("The book for which you are searching is not found");
		} 
		choice();
	}
	
	
	
	public void searchByAuthor(String author) {
		i=0;
		while(i<numberbooks) {
			if(author.equals( Books[i].Author)) {
				System.out.println("ID: "+Books[i].ID+" Name: "+Books[i].Name+
						" Author: "+Books[i].Author+" Price: "+Books[i].Price);
				break;
			}
			i++;
		}
		if(numberbooks == i) {
			System.out.println("The book for which you are searching is not found");
		} 
		choice();
	}
	
	
	
	public void listOfBook() {
		
        if(numberbooks==0) {
			System.out.println("List is empty\nPlease add your first book");
			addNewBook();
		}
        else if(count != numberbooks){
			System.out.println("ID: "+Books[count].ID+" Name: "+Books[count].Name+
					" Author: "+Books[count].Author+" Price: "+Books[count].Price);
			count++;
			listOfBook();
		}
        else {
        	choice();
        }
         
	}
	
	
	public void choice() {
		System.out.println("Enter the choice !\n1: For adding new book"
				+ "\n2: Search for book.\n3: List of book.\n4: Exit");
		switch(sc.nextInt()) {
		
		case 1: addNewBook();
			break;
			
		case 2: searchBook();
			break;
			
		case 3: listOfBook();
			break;
			
		case 4: System.exit(0);
			break;
			
		default:System.out.println("Enter correct option !");
		       choice();
		}
		
	}

	public static void main(String[] args) {
		
		LibraryApp la = new LibraryApp();
		la.choice();

	}
}
