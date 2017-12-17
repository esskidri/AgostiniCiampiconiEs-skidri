package com.travlendar.travlendarServer.model.clientModel;

import com.travlendar.travlendarServer.model.Policy;

import java.io.Serializable;
import java.util.ArrayList;

public class UserClient implements Serializable{

    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private Integer age;
    private String sex;
    private String fiscal_code;
    private Policy policy;
    private ArrayList<PrivateTransportClient> privateTransportsClient;
    private ArrayList<UserPublicTransportClient> userPublicTransportsClient;

    public UserClient(Long id, String first_name, String last_name, String email, Integer age, String sex,
                      String fiscal_code, Policy policy, ArrayList<PrivateTransportClient> privateTransportsClient,
                      ArrayList<UserPublicTransportClient> userPublicTransportsClient) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.fiscal_code = fiscal_code;
        this.policy = policy;
        this.privateTransportsClient = privateTransportsClient;
        this.userPublicTransportsClient = userPublicTransportsClient;

    }

    public ArrayList<UserPublicTransportClient> getUserPublicTransportsClient() {
        return userPublicTransportsClient;
    }

    public void setUserPublicTransportsClient(ArrayList<UserPublicTransportClient> userPublicTransportsClient) {
        this.userPublicTransportsClient = userPublicTransportsClient;
    }

    public ArrayList<PrivateTransportClient> getPrivateTransportsClient() {
        return privateTransportsClient;
    }

    public void setPrivateTransportsClient(ArrayList<PrivateTransportClient> privateTransportsClient) {
        this.privateTransportsClient = privateTransportsClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFiscal_code() {
        return fiscal_code;
    }

    public void setFiscal_code(String fiscal_code) {
        this.fiscal_code = fiscal_code;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
}
