package com.bar.BARLogistics.entities;

import org.hibernate.annotations.Type;
import org.hibernate.type.BinaryType;

import javax.persistence.*;

@Entity
@Table(name="pictures")
public class Pictures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "img")
    private byte[] img;

    public Pictures() {
    }

    public Pictures(String path, String type, byte[] img) {
        this.path = path;
        this.type = type;
        this.img = img;
    }

    public Pictures(String path, String type) {
        this.path = path;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
