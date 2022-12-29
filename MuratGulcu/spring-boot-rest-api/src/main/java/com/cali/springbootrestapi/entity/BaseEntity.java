package com.cali.springbootrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity implements Serializable {
    private Date createdDate;
    private String createdBy;
    private Date updateDate;
    private String updatedBy;

}
