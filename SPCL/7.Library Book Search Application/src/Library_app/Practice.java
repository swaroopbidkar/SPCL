package Library_app;

import java.util.*;

class p{
	LinkedList<info> al= new LinkedList<>();
	
	
	
	public p(int id,String name ) {
		info i =new info(); 
		al.add(i);
		i.name=name;
		i.id=id;
	}

	void disp() {
		Iterator it = al.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
}

	class info{
		int id;
		String name;
		@Override
		public String toString() {
			return "id=" + id + ", name=" + name;
		}
		
		
	}
}

public class Practice {

	public static void main(String[] args) {
		 
		 new p(1,"Shubham").disp();
		 

	}

}
