package com.capstone.aiahssweettreat;

public class cakeordermodel {

    private String cakename;
    private String useremail;
    private String size;
    private String decoration;
    private String fillings;
    private String frosting;
    private String specialInstruction;
    private String price;

    public cakeordermodel() {
    }

    public cakeordermodel(String cakename, String useremail,String size, String decoration, String fillings, String frosting, String specialInstruction, String price) {
        this.cakename = cakename;
        this.useremail = useremail;
        this.size = size;
        this.decoration = decoration;
        this.fillings = fillings;
        this.frosting = frosting;
        this.specialInstruction = specialInstruction;
        this.price = price;
    }

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getFillings() {
        return fillings;
    }

    public void setFillings(String fillings) {
        this.fillings = fillings;
    }

    public String getFrosting() {
        return frosting;
    }

    public void setFrosting(String frosting) {
        this.frosting = frosting;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
