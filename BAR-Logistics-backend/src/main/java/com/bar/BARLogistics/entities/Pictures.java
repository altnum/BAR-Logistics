package com.bar.BARLogistics.entities;

import org.hibernate.annotations.Type;

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

    @Lob //large object type
    @Column(name = "img")
    private byte[] img;

    public Pictures() {
    }

    public Pictures(Integer id, String path, String type, byte[] img) {
        this.id = id;
        this.path = path;
        this.type = type;
        this.img = img;
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
