package com.satxvitalrecords.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Application> apps;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<MailingAddress> mailingAddresses;

  @Id
  @GeneratedValue
  private long id;

  @Column
  private String first_name;

  @Column
  private String last_name;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(length = 2)
  private int role;

  @Column(nullable = false, unique = true)
  private String phone_num;

  public User() { }

  public User(String first_name, String last_name, String username, String email, String password, int role, String phone_num) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.phone_num = phone_num;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  public String getPhone_num() {
    return phone_num;
  }

  public void setPhone_num(String phone_num) {
    this.phone_num = phone_num;
  }
}
