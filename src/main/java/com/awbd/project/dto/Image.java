package com.awbd.project.dto;

public class Image {

    private String name;

    private String type;

    private byte[] picByte;

    private String entityName;

    public Image(String name, String type, byte[] picByte, String entityName) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.entityName = entityName;
    }

    public Image() {
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


}
