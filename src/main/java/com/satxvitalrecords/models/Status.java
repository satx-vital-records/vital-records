package com.satxvitalrecords.models;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

  @Id
  @Column
  private long id;

  @Column
  private String description;

}
