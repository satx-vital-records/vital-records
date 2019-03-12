package com.satxvitalrecords.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "records")
public class Record {

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false)
  private String first_name;

  @Column(nullable = false)
  private String mid_name;

  @Column(nullable = false)
  private String last_name;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "YY/DD/MM")
  private Date date_of_birth;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "YY/DD/MM")
  private Date date_of_death;

  @Column(nullable = false, length = 10)
  private String sex;

  @Column(nullable = false)
  private String birth_city;

  @Column(nullable = false)
  private String birth_county;

  @Column(nullable = false)
  private String death_city;

  @Column(nullable = false)
  private String death_county;

  @Column(nullable = false)
  private String parent1_first_name;

  @Column(nullable = false)
  private String parent1_mid_name;

  @Column(nullable = false)
  private String parent1_last_name;

  @Column(nullable = false)
  private String parent2_first_name;

  @Column(nullable = false)
  private String parent2_mid_name;

  @Column(nullable = false)
  private String parent2_last_name;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "YY/DD/MM hh:mm:ss")
  private Date date_of_request;


  public Record() {
  }

  public Record(String first_name, String mid_name, String last_name, Date date_of_birth, Date date_of_death, String sex, String birth_city, String birth_county, String death_city, String death_county, String parent1_first_name, String parent1_mid_name, String parent1_last_name, String parent2_first_name, String parent2_mid_name, String parent2_last_name, Date date_of_request) {
    this.first_name = first_name;
    this.mid_name = mid_name;
    this.last_name = last_name;
    this.date_of_birth = date_of_birth;
    this.date_of_death = date_of_death;
    this.sex = sex;
    this.birth_city = birth_city;
    this.birth_county = birth_county;
    this.death_city = death_city;
    this.death_county = death_county;
    this.parent1_first_name = parent1_first_name;
    this.parent1_mid_name = parent1_mid_name;
    this.parent1_last_name = parent1_last_name;
    this.parent2_first_name = parent2_first_name;
    this.parent2_mid_name = parent2_mid_name;
    this.parent2_last_name = parent2_last_name;
    this.date_of_request = date_of_request;
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

  public Date getDate_of_birth() {
    return date_of_birth;
  }

  public void setDate_of_birth(Date date_of_birth) {
    this.date_of_birth = date_of_birth;
  }

  public Date getDate_of_death() {
    return date_of_death;
  }

  public void setDate_of_death(Date date_of_death) {
    this.date_of_death = date_of_death;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBirth_city() {
    return birth_city;
  }

  public void setBirth_city(String birth_city) {
    this.birth_city = birth_city;
  }

  public String getBirth_county() {
    return birth_county;
  }

  public void setBirth_county(String birth_county) {
    this.birth_county = birth_county;
  }

  public String getDeath_city() {
    return death_city;
  }

  public void setDeath_city(String death_city) {
    this.death_city = death_city;
  }

  public String getDeath_county() {
    return death_county;
  }

  public void setDeath_county(String death_county) {
    this.death_county = death_county;
  }

  public String getParent1_first_name() {
    return parent1_first_name;
  }

  public void setParent1_first_name(String parent1_first_name) {
    this.parent1_first_name = parent1_first_name;
  }

  public String getParent1_mid_name() {
    return parent1_mid_name;
  }

  public void setParent1_mid_name(String parent1_mid_name) {
    this.parent1_mid_name = parent1_mid_name;
  }

  public String getParent1_last_name() {
    return parent1_last_name;
  }

  public void setParent1_last_name(String parent1_last_name) {
    this.parent1_last_name = parent1_last_name;
  }

  public String getParent2_first_name() {
    return parent2_first_name;
  }

  public void setParent2_first_name(String parent2_first_name) {
    this.parent2_first_name = parent2_first_name;
  }

  public String getParent2_mid_name() {
    return parent2_mid_name;
  }

  public void setParent2_mid_name(String parent2_mid_name) {
    this.parent2_mid_name = parent2_mid_name;
  }

  public String getParent2_last_name() {
    return parent2_last_name;
  }

  public void setParent2_last_name(String parent2_last_name) {
    this.parent2_last_name = parent2_last_name;
  }

  public Date getDate_of_request() {
    return date_of_request;
  }

  public void setDate_of_request(Date date_of_request) {
    this.date_of_request = date_of_request;
  }
}
