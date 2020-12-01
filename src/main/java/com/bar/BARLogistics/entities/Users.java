package com.bar.BARLogistics.entities;


import javax.persistence.*;

@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String username;

    @Column (name= "password")
    private String password;

    @Column (name = "first_name")
    private String first_name;

    @Column (name = "last_name")
    private String last_name;

    @Column (name = "address")
    private String address;

    @Column (name = "order_in_process")
    private Integer order_in_process;

    @Column (name = "order_id")
    private Integer order_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public Integer getOrder_in_process() {
        return order_in_process;
    }

    public void setOrder_in_process(Integer order_in_process) {
        this.order_in_process = order_in_process;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }


}
