package ru.kpfu.itis.belskaya.entities;

import java.util.List;
import java.util.Objects;

public class Tutor extends User {
    private float rating;
    private boolean gender;

    public boolean isWorkingOnline() {
        return isWorkingOnline;
    }

    public void setWorkingOnline(boolean workingOnline) {
        isWorkingOnline = workingOnline;
    }

    private boolean isWorkingOnline;
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tutor)) return false;
        if (!super.equals(o)) return false;
        Tutor tutor = (Tutor) o;
        return Float.compare(tutor.rating, rating) == 0 && gender == tutor.gender && isWorkingOnline == tutor.isWorkingOnline && Objects.equals(subjects, tutor.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rating, gender, isWorkingOnline, subjects);
    }

    private List<String> subjects;

    public Tutor(int id, String name, String email, String passwordHash, String city, String phone, float rating, boolean gender, boolean isWorkingOnline, List<String> subjects) {
        super(id, name, email, passwordHash, city, phone);
        this.rating = rating;
        this.gender = gender;
        this.isWorkingOnline = isWorkingOnline;
        this.subjects = subjects;
    }

    public Tutor(String name, String email, String passwordHash, String city, boolean isWorkingOnline, boolean gender, List<String> subjects, String phone) {
        super(name, email, passwordHash, city, phone);
        this.isWorkingOnline = isWorkingOnline;
        this.gender = gender;
        this.subjects = subjects;
        rating = 0f;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "rating=" + rating +
                ", gender=" + gender +
                ", isWorkingOnline=" + isWorkingOnline +
                ", subjects=" + subjects +
                '}';
    }
}
