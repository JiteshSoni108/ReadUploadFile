package com.fileread.filereaddemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="file_details")
@Getter
@Setter
public class FileDataDetails {
    @Id
    @Column(name="FILE_ID")
    private String fileID;
    @Column(name="NAME")
    private String name;
    @Column(name="DESCRIPTIONS")
    private String descriptions;
    @Column(name="UPDATED_TIME")
    private String updatedTime;
}
