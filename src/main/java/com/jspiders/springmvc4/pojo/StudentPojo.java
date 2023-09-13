package com.jspiders.springmvc4.pojo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class StudentPojo {
       @Id
       @GeneratedValue (strategy = GenerationType.IDENTITY)
       private int id;
       private String name;
       private String email;
       private long contact;
       private String address;
}
