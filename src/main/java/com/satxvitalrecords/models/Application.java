package com.satxvitalrecords.models;

import org.springframework.format.annotation.DateTimeFormat;
import sun.plugin.dom.core.Text;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "applications")
public class Application {

  @OneToOne
  private Status status;

  @OneToOne
  private Record record;

  @ManyToOne
  @JoinColumn(name= "user_id")
  private User user;


  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false)
  private String first_name;

  @Column
  private String mid_name;

  @Column(nullable = false)
  private String last_name;

  @Column(nullable = false)
  private String street;

  @Column
  private String street2;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String zip;

  @Column
  private String record_relationship;

  @Column
  private String purpose;

  @Column
  private String record_type;

  @Column
  private String form_type;

  @Column
  private String contact_type;

  @Column
  private String identification_img;

  @Column
  private String form_img;

  @Column
  private Text comments;

  @Column
  @DateTimeFormat(pattern = "YY/DD/MM hh:mm:ss")
  private Date comment_dateTime;

  public Application() {
  }

  public Application(String first_name, String mid_name, String last_name, String street, String street2, String city, String state, String zip) {
    this.first_name = first_name;
    this.mid_name = mid_name;
    this.last_name = last_name;
    this.street = street;
    this.street2 = street2;
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

  public String getMid_name() {
    return mid_name;
  }

  public void setMid_name(String mid_name) {
    this.mid_name = mid_name;
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

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
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

  public String getRecord_relationship() {
    return record_relationship;
  }

  public void setRecord_relationship(String record_relationship) {
    this.record_relationship = record_relationship;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getRecord_type() {
    return record_type;
  }

  public void setRecord_type(String record_type) {
    this.record_type = record_type;
  }

  public String getForm_type() {
    return form_type;
  }

  public void setForm_type(String form_type) {
    this.form_type = form_type;
  }

  public String getContact_type() {
    return contact_type;
  }

  public void setContact_type(String contact_type) {
    this.contact_type = contact_type;
  }

  public String getIdentification_img() {
    return identification_img;
  }

  public void setIdentification_img(String identification_img) {
    this.identification_img = identification_img;
  }

  public String getForm_img() {
    return form_img;
  }

  public void setForm_img(String form_img) {
    this.form_img = form_img;
  }

  public Text getComments() {
    return comments;
  }

  public void setComments(Text comments) {
    this.comments = comments;
  }

  public Date getComment_dateTime() {
    return comment_dateTime;
  }

  public void setComment_dateTime(Date comment_dateTime) {
    this.comment_dateTime = comment_dateTime;
  }
}
