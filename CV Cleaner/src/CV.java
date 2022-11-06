package com.bham.pij.assignments.candidates;

import java.util.ArrayList;


public class CV {

    public String surname;
    public String firstName;
    public String address;
    public String qualification;
    public String position;
    public String experience;
    public String positionTwo;
    public String experienceTwo;
    public String eMail;

    public int positionCount;
    public int experienceCount;

    public CV() {
        this.experienceCount = 0;
        this.positionCount = 0;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(String positionTwo) {
        this.positionTwo = positionTwo;
    }

    public String getExperienceTwo() {
        return experienceTwo;
    }

    public void setExperienceTwo(String experienceTwo) {
        this.experienceTwo = experienceTwo;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void addToCv(String[] splitLine) {

        int positionCount = this.positionCount;
        int experienceCount = this.experienceCount;

        switch (splitLine[0]){
            case ("Surname"):
                this.setSurname(splitLine[1]);
                break;
            case ("First Name"):
            case ("FirstName"):
                this.setFirstName(splitLine[1]);
                break;
            case ("Address"):
                this.setAddress(splitLine[1]);
                break;
            case ("Qualification"):
                this.setQualification(splitLine[1]);
                break;
            case ("Position"):
                if (positionCount == 0){
                    this.setPosition(splitLine[1]);
                    this.positionCount++;
                }
                if (positionCount == 1){
                    this.setPositionTwo(splitLine[1]);
                    this.positionCount++;
                }
                break;
            case ("Experience"):
                if (experienceCount == 0){
                    this.setExperience(splitLine[1]);
                    this.experienceCount++;
                }
                if (experienceCount == 1){
                    this.setExperienceTwo(splitLine[1]);
                    this.experienceCount++;
                }
                break;
            case ("eMail"):
                this.seteMail(splitLine[1]);
                break;
        }
    }


}
