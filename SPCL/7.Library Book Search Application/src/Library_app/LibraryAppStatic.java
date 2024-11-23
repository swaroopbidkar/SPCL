package Library_app;

import java.util.Scanner;

class BookApp {

   static Book b[] = new Book[25];//Since,I'm using Array which is fixed in size right? That's why I've created a Array of size "25" as of now!
   static int count = 0, i = 0,tempId ; //Here,"count" is used to keep count of number of Book objects created anf "i" holds index value which is used to "Traverse the Array of Book Objects"!
/*******/   static String tempA_Name,tempName ;
/*These variables we are using repeatedly so we can make it as static*/
   static Scanner scan = new Scanner(System.in);

    static void menu() {
        System.out.println("Press 1 For adding new Book");
        System.out.println("Press 2 Search for Books");
        System.out.println("Press 3 List of Books");
        System.out.println("Press 4 To Exit");
        System.out.println("Dear user,Enter your choice:");

        switch (scan.nextInt()) {
            case 1:
                addBook();
                break;

            case 2:
                searchBook();
                break;

            case 3:
                listBook();
                break;

            case 4:
                System.out.println("Thank you for Visiting!");
                System.exit(0);
        }
        menu();//Each time after finishing the operations like addBook();,searchBook();,listBook(); W.K.T control get returned back from called method to its
    } //Caller method right? Like wise,here also the control returns this method(menu();) and try to exit this method and there I've invoked "menu();" to display it again!

    //For Adding Books!
    static void addBook() {
        while (true) {
            System.out.println("Enter the Book ID: ");
            tempId = scan.nextInt();
            scan.nextLine();
            System.out.println("Enter the Book name: ");
            tempName = scan.nextLine();
            System.out.println("Enter the Author Name: ");
            tempA_Name = scan.nextLine();
            System.out.println("Enter the Book Price: ");
            int tempPrice = scan.nextInt();
            boolean res = verify(tempId,tempName);
                if(res == false){
                    System.out.println("Sorry Book Id or Name is Already Taken!\nPlease try to add Books with unique ID or Name: ");
                    addBook();//If there,the user is asked to add books with Unique book details...
                }
                else {
                    b[i] = new Book(); //After successful verification the Book object created & populated into Array of Book Objects with the below details.
                    b[i].id     = tempId;
                    b[i].name   = tempName;
                    b[i].author = tempA_Name;
                    b[i].price  = tempPrice;
                    System.out.println("Book added Successfully!");
                    count++;
                    i++; //After Successfully adding a Book after required verification ,"i" is incremented to point and made to point towards the next index of Array!
                    System.out.println("Press 'C' to continue adding of Books:\nOr Press any key to return to Main menu: ");

                    if (scan.next().equalsIgnoreCase("C")) {
                        addBook();
                    } else {
                       menu(); //Loop breaks & Control returns to where it's came from (i.e menu();method)...
                    }
                }
            }
        }
    static boolean verify(int tempId,String tempName) {//This logic is used to verify whether the user entered details are already there or Not...
        int county = 0;
/****** count>0 */ 
        for (int j=0;j<count ;j++) {//This loop is used to traverse entire Array of Book Object and Compares the user entered Book-id and Name with the every Book objects-id or Name-
            if ((tempId == b[j].id || tempName.equals(b[j].name))) { //present in Array of Book Objects...If it finds any matching which means user entered details
            }                                                                    //are already exists right?...
            else
            {
                //"county" is not incremented for the first book...
                    county++; //And after the first book "count" is incremented right? So,then for the 2nd Book onwards each time when verify(); invoked and each time when user entered
                             //details is not found in my "Array of Book Object" "county" is incremented...
            }
        }
        if (count == county) {//This means that,if even after traversing the entire "Array of Book objects" then the value of "county" is same as the num of Book objects created
            return true;//in Array.So,what it means?User entered details are Unique right(details which Doesn't exist in entire Array of Book Objects).Then,true is returned..
        } else {
            return false;//If Not unique obviously "false" is going to return right?
        }
    }


    static void searchBook() {
        if (count == 0) { //which means If No books were added! then the user is asked to add his First book!
            System.out.println("Sorry Books not Found!");
            System.out.println("Please add your First Book: \n");
            addBook();
        } else {
            System.out.println("Choose your option: ");
            System.out.println("1)Search by Name");
            System.out.println("2)Search by Author");
            System.out.println("3)Search by ID");
            int choice = scan.nextInt();
            scan.nextLine(); //This consumes new line character(i.e "Enter").
            switch (choice) {
                case 1:
                    System.out.println("Enter the Book name: ");
                    byName(scan.nextLine());
                    break;

                case 2:
                    System.out.println("Enter the Author name: ");
                    byAuthor(scan.nextLine());
                    break;


                case 3:
                    System.out.println("Enter the Book-ID: ");
                    byId(scan.nextInt());
                    break;

                default:
                    System.out.println("Please try to provide valid Input: ");
                    searchBook();
            }

            System.out.println("Press 'C' to continue Searching of Books:\nOr Press any key to return to Main menu: ");
            if (scan.next().equalsIgnoreCase("C")) {
                searchBook();
            }
            else {
                menu();//Each time after finishing the search operations byName(); or byAuthor(); or byId(); W.K.T control get returned back from called method to its
            }//Caller method right? Like wise,here also the control returns this method(searchBook();) and try to exit this method and there I've invoked "menu();" to display it again!
        }
    }

    //Method to Search for Books by B-Name.
    static void byName(String bName) {//"bName"- user entered Book name to search for books with the same details...
        int county = 0;
        for (int j = 0; j < count; j++) {  //L.variable "j" used to traverse the entire array of Book objects that were created!
            if (bName.equalsIgnoreCase(b[j].name)) {
                System.out.println("Book Name :" + b[j].name + "   ID :" + b[j].id + "   Author :" + b[j].author + "   Price :" + b[j].price + "Rs/-.\n");
            } else {
                county++;//So,each time when user entered book doesn't match with the "current Array of Book object's - Book name" the "county" is incremented and if in case -
                if (count == county) { //value of my "count"  and "county" variable is same, that means even after traversing the total number of "Book-objects" that were created inside
                    System.out.println("Sorry Book name was not Found\n");  //my "Array of Book-objects" the user entered "Book name" was not found!
                }
            }
        }
    }

    //Method to Search for Books by Author-Name.
    static void byAuthor(String aName) {//"aName"- user entered Author name to search for books with the same details...
        int county = 0;
        for (int j = 0; j < count; j++) {  //L.variable "j" used to traverse the entire array of Book objects that were created!
            if (aName.equalsIgnoreCase(b[j].author)) {
                System.out.println("Author :" + b[j].author + "   Book Name :" + b[j].name + "   ID :" + b[j].id + "   Price :" + b[j].price + "Rs/-.\n");
            } else {
                county++;//So,each time when user entered book doesn't match with the "current Array of Book object's - Author name" the "county" is incremented and if in case -
                if (count == county) { //value of my "count"  and "county" variable is same, that means even after traversing the total number of "Book-objects" that were created inside
                    System.out.println("Sorry Book name was not Found\n");  //my "Array of Book-objects" the user entered "Author name" was not found!
                }
            }
        }
    }

    //Method to Search for Books by Book-ID.
    static void byId(int bId) {//"aId"- user entered Book-Id to search for books with the same details...
        int county = 0;
        for (int j = 0; j < count; j++) {  //L.variable "j" used to traverse the entire array of Book objects that were created!
            if (bId == b[j].id) {
                System.out.println("ID :"+ b[j].id +"   Book Name :" + b[j].name +"   Author :" + b[j].author +"   Price :" + b[j].price + "Rs/-.\n");
            } else {
                county++;//So,each time when user entered book doesn't match with the "current Array of Book object's - Book-Id" the "county" is incremented and if in case -
                if (count == county) { //value of my "count"  and "county" variable is same, that means even after traversing the total number of "Book-objects" that were created inside
                    System.out.println("Sorry Book name was not Found\n");  //my "Array of Book-objects" the user entered "Book-Id" was not found!
                }
            }
        }
    }

    //Method which displays List of Books

    static void listBook(){
        if (count == 0) { //which means If No books were added! then the user is asked to add his First book!
            System.out.println("Sorry Books not Found!");
            System.out.println("Please add your First Book: \n");
            addBook();
        }
        else{
            for(int j=0;j<count;j++){
                System.out.println("ID :"+ b[j].id +"   Book Name :" + b[j].name +"   Author :" + b[j].author +"   Price :" + b[j].price + "Rs/-.\n");
            }
        }
    }
    static class Book {
        String name, author;
        int id, price;
    }
}


class LibraryAppStatic{ // main class from where execution starts
    public static void main(String[] args) {
        System.out.println("Welcome to Virtual-Book-Store!");
        BookApp.menu();
    }
}



