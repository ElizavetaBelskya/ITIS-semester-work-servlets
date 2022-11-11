package ru.kpfu.itis.belskaya.entities;

import java.util.Objects;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private String passwordHash;
    private String city;

    private String phone;

    public User(String name, String email, String passwordHash, String city, String phone) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.city = city;
        this.phone = phone;
    }

    public User(String name, String email, String city, String phone) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.phone = phone;
    }



    public User(int id, String name, String email, String passwordHash, String city, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.city = city;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && email.equals(user.email) && passwordHash.equals(user.passwordHash) && city.equals(user.city) && phone.equals(user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, passwordHash, city, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
