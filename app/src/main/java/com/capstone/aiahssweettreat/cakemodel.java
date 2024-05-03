package com.capstone.aiahssweettreat;

public class cakemodel {
    private String cakename;
    private String cakeimage;
    private String cakeprice;
    private String cakedescription;
    private String cakecaloriecount;
    private String cakesugarcount;
    private String frosting1;
    private String frosting2;
    private String frosting3;
    private String decoration1;
    private String decoration2;
    private String decoration3;
    private String filling1;
    private String filling2;
    private String filling3;

    public cakemodel() {
    }

    public cakemodel(String cakename, String cakeimage, String cakeprice, String cakedescription,
                     String cakecaloriecount, String cakesugarcount, String frosting1, String frosting2,
                     String frosting3, String decoration1, String decoration2, String decoration3,
                     String filling1, String filling2, String filling3) {
        this.cakename = cakename;
        this.cakeimage = cakeimage;
        this.cakeprice = cakeprice;
        this.cakedescription = cakedescription;
        this.cakecaloriecount = cakecaloriecount;
        this.cakesugarcount = cakesugarcount;
        this.frosting1 = frosting1;
        this.frosting2 = frosting2;
        this.frosting3 = frosting3;
        this.decoration1 = decoration1;
        this.decoration2 = decoration2;
        this.decoration3 = decoration3;
        this.filling1 = filling1;
        this.filling2 = filling2;
        this.filling3 = filling3;
    }

    // Getters and setters for all fields

    public String getCakename() {
        return cakename;
    }

    public void setCakename(String cakename) {
        this.cakename = cakename;
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

    public void setCakeprice(String cakeprice) {
        this.cakeprice = cakeprice;
    }

    public String getCakedescription() {
        return cakedescription;
    }

    public void setCakedescription(String cakedescription) {
        this.cakedescription = cakedescription;
    }

    public String getCakecaloriecount() {
        return cakecaloriecount;
    }

    public void setCakecaloriecount(String cakecaloriecount) {
        this.cakecaloriecount = cakecaloriecount;
    }

    public String getCakesugarcount() {
        return cakesugarcount;
    }

    public void setCakesugarcount(String cakesugarcount) {
        this.cakesugarcount = cakesugarcount;
    }

    public String getFrosting1() {
        return frosting1;
    }

    public void setFrosting1(String frosting1) {
        this.frosting1 = frosting1;
    }

    public String getFrosting2() {
        return frosting2;
    }

    public void setFrosting2(String frosting2) {
        this.frosting2 = frosting2;
    }

    public String getFrosting3() {
        return frosting3;
    }

    public void setFrosting3(String frosting3) {
        this.frosting3 = frosting3;
    }

    public String getDecoration1() {
        return decoration1;
    }

    public void setDecoration1(String decoration1) {
        this.decoration1 = decoration1;
    }

    public String getDecoration2() {
        return decoration2;
    }

    public void setDecoration2(String decoration2) {
        this.decoration2 = decoration2;
    }

    public String getDecoration3() {
        return decoration3;
    }

    public void setDecoration3(String decoration3) {
        this.decoration3 = decoration3;
    }

    public String getFilling1() {
        return filling1;
    }

    public void setFilling1(String filling1) {
        this.filling1 = filling1;
    }

    public String getFilling2() {
        return filling2;
    }

    public void setFilling2(String filling2) {
        this.filling2 = filling2;
    }

    public String getFilling3() {
        return filling3;
    }

    public void setFilling3(String filling3) {
        this.filling3 = filling3;
    }
}
