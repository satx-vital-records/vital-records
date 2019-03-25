package com.satxvitalrecords.models;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

//  @OneToOne
//  private Application application;

  @Id
  @Column
  private long id;

  @Column
  private String description;

  public Status(){}


  public Status(long id) {
    this.id = id;
  }

  public Status(long id, String description) {
    this.id = id;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
