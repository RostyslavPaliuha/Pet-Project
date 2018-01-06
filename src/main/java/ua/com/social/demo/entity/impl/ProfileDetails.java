package ua.com.social.demo.entity.impl;

import ua.com.social.demo.entity.DomainObject;

public class ProfileDetails implements DomainObject {
    private Integer profileDetailsId;
    private String firstName;
    private String lastName;
    private Sex sex;
    private Integer age;
    private Integer profileId;

    public ProfileDetails( String firstName, String lastName, Sex sex, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }
}
