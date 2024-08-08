import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class customercontroller {

	public static void main(String[] args) {
		try (Scanner src = new Scanner(System.in)) {
			int ch;
			do {
				displaymenue();
				System.out.println("Enter your choice");
				ch = Integer.parseInt(src.nextLine());
				switch (ch) {
				case 1:
					insertion();
					break;
				case 2:
					delete();
					break;
				case 3:
					update();
					break;
				case 4:
					getall();
					break;
				case 5:
					getbyid();
					break;
				case 6:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid operation");
					break;
				}
			} while (ch > 0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    //fetch data from id
	private static void getbyid() {
		// TODO Auto-generated method stub
			Scanner scr=new Scanner(System.in);
			StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
			Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
			SessionFactory sf=md.buildSessionFactory();
			Session s=sf.openSession();
			System.out.println("enter id :");
			int id=scr.nextInt();	
			Transaction t=s.beginTransaction();
			customer cus=s.get(customer.class, id);
			if(cus != null) {
				System.out.println("------------------------------------------");
				System.out.println("id :"+cus.getId());
				System.out.println("Name :"+cus.getName());
				System.out.println("Email :"+cus.getEmail());
				System.out.println("Password :"+cus.getPassword());
				System.out.println("Phonenumber :"+cus.getPhonenumber());
				System.out.println("------------------------------------------");
			}
			else {
				System.out.println("data not found");
			}
			t.commit();		
		}

    //fetch all data
	private static void getall() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<customer> cus=s.createQuery("from customer",customer.class).list();	
		t.commit();
		for(customer c : cus) {
			System.out.println("id : "+c.getId());
			System.out.println("name : "+c.getName());
			System.out.println("email : "+c.getEmail());
			System.out.println("password : "+c.getPassword());
			System.out.println("phonenumber : "+c.getPhonenumber());		
		}
	}

	//update data through id
	@SuppressWarnings("deprecation")
	private static void update() {
		// TODO Auto-generated method stub
		Scanner scr= new Scanner(System.in);
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata mt = new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory sf = mt.buildSessionFactory();
    	Session s = sf.openSession();
    	System.out.println("enter id");
    	int id = scr.nextInt();
    	Transaction t = s.beginTransaction();
    	customer cus = s.get(customer.class, id);
    	if(cus!=null) {
    		System.out.println("enter new name :");
    		String name = scr.next();
    		System.out.println("enter new email");
    		String email =scr.next();
    		System.out.println("enter new password");
    		String password = scr.next();
    		System.out.println("enter new phonenumber");
    	    Long phonenumber = scr.nextLong();
    		cus.setName(name);
    		cus.setEmail(email);
    		cus.setPassword(password);
    		cus.setPhonenumber(phonenumber);
    		s.update(cus);
    		t.commit();
    		System.out.println("data successfully updated");
    		}else {
    		System.out.println("data not updated");
    	}	
	}
	
	//delete data from id
	@SuppressWarnings("deprecation")
	private static void delete() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        System.out.println("enter student id :");
		int id=sc.nextInt();
		Transaction t = s.beginTransaction();
		customer cus=s.get(customer.class, id);
		s.delete(cus);
		t.commit();
		System.out.println("sucessfully deleted");
	}

	//insert data
	private static void insertion() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in); 
	    String name = getInput(scanner, "Enter name: ");
	    String email = getInput(scanner, "Enter email: ");
	    String password = getInput(scanner, "Enter password: ");
	    String phoneNumberStr = getInput(scanner, "Enter phone number: ");
	    long phoneNumber = Long.parseLong(phoneNumberStr);
	    scanner.close();
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        customer c = new customer();
        c.setName(name);
        c.setEmail(email);
        c.setPassword(password);
        c.setPhonenumber(phoneNumber);
        s.save(c);
        t.commit();   
        System.out.println("Successfully Inserted");
        s.close();
        sf.close();
    }
    private static String getInput(Scanner scanner, String data) {
        System.out.print(data);
        return scanner.nextLine();
    }	

	private static void displaymenue() {
		// TODO Auto-generated method stub
		System.out.println("____________________");
		System.out.println("\t1.insertion");
		System.out.println("\t2.delete");
		System.out.println("\t3.update");
		System.out.println("\t4.getall");
		System.out.println("\t5.getbyid");
		System.out.println("____________________");
	}
}
