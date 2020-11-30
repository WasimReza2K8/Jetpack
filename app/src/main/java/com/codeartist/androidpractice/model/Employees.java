package com.codeartist.androidpractice.model;

public class Employees {
    private String name;
    private String email;
    private int age;
    private String photo;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Override
    public String toString() {
        return "Employees [name=" + name +", email = "+ email+ ", age= "+age +", photo="+photo+"]";
    }
}
