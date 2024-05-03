package com.capstone.aiahssweettreat;

public class cakeaddtocartmodel {

    private String cakename;
    private String cakeimage;
    private String cakeprice;
    private String useremail;
    private String cakesize;
    private String frostings;
    private String decorations;
    private String fillings;
    private String specialInstructions;

    public cakeaddtocartmodel() {
    }

    public cakeaddtocartmodel(String cakename, String cakeimage, String cakeprice, String useremail,String cakesize, String frostings,String decorations,String fillings,String specialInstructions) {
        this.cakename = cakename;
        this.cakeimage = cakeimage;
        this.cakeprice = cakeprice;
        this.useremail = useremail;
        this.cakesize = cakesize;
        this.frostings = frostings;
        this.decorations = decorations;
        this.fillings = fillings;
        this.specialInstructions = specialInstructions;
    }

    public String getCakeimage() {
        return cakeimage;
    }

    public void setCakeimage(String cakeimage) {
        this.cakeimage = cakeimage;
    }

    public String getCakeprice() {
        return cakeprice;
    }

    public String getCakesize() {
        return cakesize;
    }

    public void setCakesize(String cakesize) {
        this.cakesize = cakesize;
    }

    public void setCakeprice(String cakeprice) {
        this.cakeprice = cakeprice;
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


    public String getFrostings() {
        return frostings;
    }

    public void setFrostings(String frostings) {
        this.frostings = frostings;
    }

    public String getDecorations() {
        return decorations;
    }

    public void setDecorations(String decorations) {
        this.decorations = decorations;
    }

    public String getFillings() {
        return fillings;
    }

    public void setFillings(String fillings) {
        this.fillings = fillings;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
