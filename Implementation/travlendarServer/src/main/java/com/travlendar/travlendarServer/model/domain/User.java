package com.travlendar.travlendarServer.model.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="email")
    private String email;

    @Column(name="age")
    private Integer age;

    @Column(name="sex")
    private String sex;

    @Column(name="codice_fiscale")
    private String fiscal_code;

    @Column(name="policy")
    private String policy;

    @ManyToMany(mappedBy = "users")
    private List<PrivateTransport> privateTransportList;

    @OneToMany(mappedBy = "user")
    private List<FreeTime> freeTimes;

    @OneToMany(mappedBy = "user")
    private List<UserPublicTransport> UserpublicTransports;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> userOrders;

    @OneToMany(mappedBy = "user")
    private List<Event> events;


    public User() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public List<PrivateTransport> getPrivateTransportList() {
        return privateTransportList;
    }

    public void setPrivateTransportList(List<PrivateTransport> privateTransportList) {
        this.privateTransportList = privateTransportList;
    }

    public List<FreeTime> getFreeTimes() {
        return freeTimes;
    }

    public void setFreeTimes(List<FreeTime> freeTimes) {
        this.freeTimes = freeTimes;
    }

    public List<UserPublicTransport> getUserpublicTransports() {
        return UserpublicTransports;
    }

    public void setUserpublicTransports(List<UserPublicTransport> userpublicTransports) {
        UserpublicTransports = userpublicTransports;
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}