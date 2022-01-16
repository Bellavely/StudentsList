package com.example.studentslist.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Student {
    final public static String CollectionName="students";
    @PrimaryKey
    @NonNull
    String id="";
    String name="";
    boolean flag;

    public Student() {
        this.name = "";
        this.id = "";
        this.flag = false;
    }

    public Student(String name, String id, boolean flag) {
        this.name = name;
        this.id = id;
        this.flag = flag;
    }

    public static Student create(Map<String, Object> json) {
        String id= (String) json.get("id");
        String name=(String) json.get("name");
        Boolean flag=(Boolean) json.get("flag");
        Student student= new Student(name,id,flag);
        return student;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean isFlag() {
        return flag;
    }

    public Map<String, Object> toJson() {
        Map<String,Object> Json = new HashMap<String,Object>();
        Json.put("id",id);
        Json.put("name",name);
        Json.put("flag",flag);
        return  Json;
    }
}
