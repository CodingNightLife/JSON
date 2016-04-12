package com.example.t31054.json;

public class Company {
    String companyID;
    String comapnyName;
    String companyOwner;
    String companyStartDate;
    String companyDescription;
    String companyDepartments;

    Company(){

    }
    Company(String companyID,String comapnyName,String companyOwner,String companyStartDate,String companyDescription,String companyDepartments) {
    this.companyID=companyID;
        this.comapnyName=comapnyName;
        this.companyOwner=companyOwner;
        this.companyDepartments=companyDepartments;
        this.companyDescription=companyDescription;
        this.companyStartDate=companyStartDate;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }


    public String getComapnyName() {
        return comapnyName;
    }

    public void setComapnyName(String comapnyName) {
        this.comapnyName = comapnyName;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyDepartments() {
        return companyDepartments;
    }

    public void setCompanyDepartments(String companyDepartments) {
        this.companyDepartments = companyDepartments;
    }




}

