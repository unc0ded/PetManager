package com.unc0ded.petmanagerapp;

public class Pet {
    String name,type;
    Integer age;

    public Pet(String name, String type, Integer age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
