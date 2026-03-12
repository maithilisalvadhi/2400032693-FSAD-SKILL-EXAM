package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.Date;

public class ClientDemo {

public static void main(String[] args) {

SessionFactory sf = new Configuration().configure().buildSessionFactory();
Session s = sf.openSession();
Transaction t = s.beginTransaction();

Department d = new Department();
d.name="CSE";
d.description="Computer Science";
d.date=new Date();
d.status="Active";

s.save(d);
System.out.println("Inserted");

Department d1=s.get(Department.class,1);
s.delete(d1);
System.out.println("Deleted");

t.commit();
s.close();
}
}