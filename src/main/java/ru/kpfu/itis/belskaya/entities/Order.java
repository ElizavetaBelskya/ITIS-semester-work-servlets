package ru.kpfu.itis.belskaya.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private int id;
    private String subject;
    private int authorId;
    private String description;
    private Boolean tutorGender = null;
    private float minRating = 0;
    private LocalDateTime creationDate;
    private Integer tutorId;
    private Boolean online;
    private int price;

    public Order(String subject, int authorId, String description, Boolean tutorGender, float minRating, Boolean online, int price) {
        this.subject = subject;
        this.authorId = authorId;
        this.description = description;
        this.tutorGender = tutorGender;
        this.minRating = minRating;
        this.online = online;
        this.creationDate = LocalDateTime.now();
        this.price = price;
    }

    public Boolean getTutorGender() {
        return tutorGender;
    }

    public void setTutorGender(Boolean tutorGender) {
        this.tutorGender = tutorGender;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Order(int id, String subject, int authorId, String description, Boolean tutorGender, float minRating, LocalDateTime creationDate, Integer tutorId, Boolean online, int price) {
        this.id = id;
        this.subject = subject;
        this.authorId = authorId;
        this.description = description;
        this.tutorGender = tutorGender;
        this.minRating = minRating;
        this.creationDate = creationDate;
        this.tutorId = tutorId;
        this.online = online;
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return authorId == order.authorId && tutorGender == order.tutorGender && Float.compare(order.minRating, minRating) == 0 && tutorId == order.tutorId && online == order.online && Objects.equals(subject, order.subject) && Objects.equals(description, order.description) && Objects.equals(creationDate, order.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, authorId, description, tutorGender, minRating, creationDate, tutorId, online);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isTutorGender() {
        return tutorGender;
    }

    public void setTutorGender(boolean tutorGender) {
        this.tutorGender = tutorGender;
    }

    public float getMinRating() {
        return minRating;
    }

    public void setMinRating(float minRating) {
        this.minRating = minRating;
    }

}
