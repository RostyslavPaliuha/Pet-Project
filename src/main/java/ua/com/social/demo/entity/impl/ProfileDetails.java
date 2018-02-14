package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

import java.time.LocalDate;

public class ProfileDetails implements DomainObject {
    private Integer profileDetailsId;
    private String firstName;
    private String lastName;
    private Sex sex;
    private LocalDate birthDay;
    private String country;
    private Integer profileId;
    private Post avatar;

    public enum Sex {
        male, female
    }

    public ProfileDetails(String firstName, String lastName, Sex sex, LocalDate birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    public ProfileDetails() {
    }

    public Integer getProfileDetailsId() {
        return profileDetailsId;
    }

    public void setProfileDetailsId(Integer profileDetailsId) {
        this.profileDetailsId = profileDetailsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex.name();
    }

    public void setSex(String sex) {
        this.sex = Sex.valueOf(sex);
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Post getAvatar() {
        return avatar;
    }

    public void setAvatar(Post avatar) {
        this.avatar = avatar;
    }
}
