package com.klef.fsad.exam;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Department {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;

String name;
String description;
Date date;
String status;

}