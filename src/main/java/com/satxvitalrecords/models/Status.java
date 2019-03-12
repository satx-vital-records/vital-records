package com.satxvitalrecords.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status {

  @Column(nullable = false)
  private long status_id;

  @Column
  private long status_desc;

}
