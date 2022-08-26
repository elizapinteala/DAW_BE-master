package com.awbd.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 8000)
    private byte[] picByte;

    @Column(name = "entityName")
    private String entityName;

    public ImageEntity(Long id, String name, String type, byte[] picByte, String entityName) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.entityName = entityName;
    }

    public ImageEntity(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public ImageEntity(String name, String type, byte[] picByte, String entityName) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.entityName = entityName;
    }

    public ImageEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}

