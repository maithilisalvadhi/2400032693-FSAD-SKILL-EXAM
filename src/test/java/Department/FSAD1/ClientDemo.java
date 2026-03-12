package Department.FSAD1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration().configure();

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Insert Department");
        System.out.println("2. Delete Department");
        System.out.println("3. Update Department");
        System.out.println("4. View Department by ID");

        System.out.print("Enter Choice: ");
        int choice = sc.nextInt();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            if(choice == 1)
            {
                System.out.print("Enter Name: ");
                String name = sc.next();

                System.out.print("Enter Description: ");
                String desc = sc.next();

                System.out.print("Enter Status: ");
                String status = sc.next();

                Department d = new Department(name, desc, new Date(), status);

                session.save(d);

                System.out.println("Department Inserted Successfully");
            }

            else if(choice == 2)
            {
                System.out.print("Enter Department ID to Delete: ");
                int id = sc.nextInt();

                Department d = session.get(Department.class, id);

                if(d != null)
                {
                    session.delete(d);
                    System.out.println("Department Deleted Successfully");
                }
                else
                {
                    System.out.println("Department Not Found");
                }
            }

            else if(choice == 3)
            {
                System.out.print("Enter Department ID to Update: ");
                int id = sc.nextInt();

                Department d = session.get(Department.class, id);

                if(d != null)
                {
                    System.out.print("Enter New Name: ");
                    String name = sc.next();

                    System.out.print("Enter New Description: ");
                    String desc = sc.next();

                    System.out.print("Enter New Status: ");
                    String status = sc.next();

                    d.setName(name);
                    d.setDescription(desc);
                    d.setStatus(status);
                    d.setDate(new Date());

                    session.update(d);

                    System.out.println("Department Updated Successfully");
                }
                else
                {
                    System.out.println("Department Not Found");
                }
            }

            else if(choice == 4)
            {
                System.out.print("Enter Department ID: ");
                int id = sc.nextInt();

                Department d = session.get(Department.class, id);

                if(d != null)
                {
                    System.out.println("Department Details:");
                    System.out.println("ID: " + d.getId());
                    System.out.println("Name: " + d.getName());
                    System.out.println("Description: " + d.getDescription());
                    System.out.println("Date: " + d.getDate());
                    System.out.println("Status: " + d.getStatus());
                }
                else
                {
                    System.out.println("Department Not Found");
                }
            }

            tx.commit();
        }

        catch(Exception e)
        {
            if(tx != null)
                tx.rollback();

            e.printStackTrace();
        }

        session.close();
        factory.close();
    }
}