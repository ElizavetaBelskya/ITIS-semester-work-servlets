package ru.kpfu.itis.belskaya.entities;
public class Student extends User {



    public Student(int id, String name, String email, String passwordHash, String city, String phone) {
        super(id, name, email, passwordHash, city, phone);
    }

    public Student(String name, String email, String city, String phone) {
        super(name, email, city, phone);
    }

    public Student(String name, String email, String passwordHash, String city, String phone) {
        super(name, email, passwordHash, city, phone);
    }
}
