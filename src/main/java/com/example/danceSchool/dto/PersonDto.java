package com.example.danceSchool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class PersonDto extends BaseDto {
    @JsonProperty(value = "first_name")
    private String firstName;
    @JsonProperty(value = "second_name")
    private String secondName;
    @JsonProperty(value = "last_name")
    private String lastName;
    @JsonProperty(value = "birthday")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthday;
    @JsonProperty(value = "sex")
    private String sex;
    @JsonProperty(value = "e_mail")
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    @JsonProperty(value = "login")
    private String login;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "role")
    private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, lastName, birthday, sex, email, phoneNumber, login, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(firstName, personDto.firstName) &&
                Objects.equals(secondName, personDto.secondName) &&
                Objects.equals(lastName, personDto.lastName) &&
                Objects.equals(birthday, personDto.birthday) &&
                Objects.equals(sex, personDto.sex) &&
                Objects.equals(email, personDto.email) &&
                Objects.equals(phoneNumber, personDto.phoneNumber) &&
                Objects.equals(login, personDto.login) &&
                Objects.equals(password, personDto.password);
    }

    public void setRole(String role) {
        this.role = role;
    }
}
