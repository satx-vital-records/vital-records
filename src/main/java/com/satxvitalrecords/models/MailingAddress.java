package com.satxvitalrecords.models;

import javax.persistence.*;

@Entity
@Table(name= "mailing_address")
public class MailingAddress {

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false)
  private String first_name;

  @Column(nullable = false)
  private String last_name;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String street_2;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String zip;

  public MailingAddress() {
  }

  public MailingAddress(String first_name, String last_name, String street, String street_2, String city, String state, String zip) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.street = street;
    this.street_2 = street_2;
    this.city = city;
    this.state = state;
    this.zip = zip;
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

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreet_2() {
    return street_2;
  }

  public void setStreet_2(String street_2) {
    this.street_2 = street_2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }
}
