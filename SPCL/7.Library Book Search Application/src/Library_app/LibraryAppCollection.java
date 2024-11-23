package Library_app;

import java.util.*;

class AppOperations{
	
	ArrayList<Library> al = new ArrayList<>(); //library to store books.
	 
	Scanner sc = new Scanner(System.in);
	int numberbooks;//to keep count how much books are available in library
	

	public void choice() { //used to choose what user want to do
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
	
		}
		System.out.println("Enter correct option !");
	       choice();
	}
	
	
	public void addNewBook() { // used to added books in liabrary
		
		System.out.println("Enter the ID");
		int tempId=sc.nextInt();
		
		System.out.println("Enter the Book Name");
		String tempN=sc.next();
		
		
		System.out.println("Enter the Auther Name");
		String tempA=sc.next();
		
		System.out.println("Enter the price");
		double tempP=sc.nextDouble();
		boolean res= verify(tempN,tempId); // verification of book name and id
		
		if(res) {
			
			Library l=new Library();
			al.add(l);
			l.Name=tempN;
			l.ID = tempId;
			l.Author=tempA;
			l.Price=tempP;
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
	
	public boolean verify(String tempN,int tempId){ //to check that whethere the a book is available with same name and id
		Iterator<Library> it= al.iterator();
		while(it.hasNext()){
			Library a = it.next();
			if(tempN.equals(a.Name) || tempId == a.ID)
				return false;
		}
		return true;
	}
	
	public void searchBook() {// options to choose how to find book
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
		choice();
	}
	
	public void searchByName(String name){ //Used to search book by book name
		Iterator<Library> it= al.iterator();
		 
		while(it.hasNext()) {
			Library a = it.next();
			if(name.equals(a.Name)) {
				System.out.println(a);
				return;
			}
		}
		System.out.println("The book for which you are searching is not found");
		choice();  
	}
	
	public void searchById(int id) { //Used to search book by book id
		Iterator<Library> it= al.iterator();
		 	 
		while(it.hasNext()) {
			Library a = it.next();
			if(id == a.ID) {
				System.out.println(a);
				return;
			}
		}
        System.out.println("The book for which you are searching is not found");
		choice();
	}
	
	
	
	public void searchByAuthor(String author) { //Used to search book by author name
		Iterator<Library> it= al.iterator();
		boolean status =true;
		while(it.hasNext()) {
			Library a = it.next(); 
			if(author.equals(a.Author)) {
				System.out.println(a);
				status=false;
			} 
		}
		if(status) {
			System.out.println("The book for which you are searching is not found");
			choice();
		}
	}
	
	
	
	public void listOfBook() { //Gives the books present in library
		
        if(numberbooks==0) {
			System.out.println("List is empty\nPlease add your first book");
			addNewBook();
		}
        else {
        	for(Library a:al) {
        		System.out.println(a);
        	}
			//System.out.println(al);
		}
       choice();
	}
	
	class Library{
		int ID;
		String Name;
		String Author;
		double Price;
		
 
		@Override
		public String toString() {
			return "ID=" + ID + ", Name=" + Name + ", Author=" + Author + ", Price=" + Price+"\n";
		}
	}	
}



public class LibraryAppCollection { //Main class to start execution
	
	public static void main(String[] args) {
		 
		new AppOperations().choice();

	}

}
