package model;

import javax.persistence.*;

@Entity
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    public Student() {}
    public Student(String name, int age) { this.name=name; this.age=age; }

    @Override
    public String toString() {
        return "ID: "+id+", Name: "+name+", Age: "+age;
    }

    // getters & setters
}
