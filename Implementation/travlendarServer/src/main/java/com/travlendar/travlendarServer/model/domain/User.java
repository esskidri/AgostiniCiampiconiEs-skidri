package com.travlendar.travlendarServer.model.domain;


import com.travlendar.travlendarServer.logic.modelInterface.MeanOfTransportLogic;
import com.travlendar.travlendarServer.logic.modelInterface.UserLogic;
import com.travlendar.travlendarServer.model.Policy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractEntity implements UserLogic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

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
    @Enumerated(EnumType.STRING)
    private Policy policy;

    @ManyToMany(mappedBy = "users")
    private List<PrivateTransport> privateTransportList;

    @OneToMany(mappedBy = "user")
    private List<FreeTime> freeTimes;

    @OneToMany(mappedBy = "user")
    private List<UserPublicTransport> UserPublicTransports;

    @OneToMany(mappedBy = "user")
    private List<UserOrder> userOrders;

    @OneToMany(mappedBy = "user")
    private List<Event> events;


    public User() { }

    public User(String first_name, String last_name, String email, Integer age, String sex, String fiscal_code, Policy policy) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.fiscal_code = fiscal_code;
        this.policy = policy;
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
        return UserPublicTransports;
    }

    public void setUserpublicTransports(List<UserPublicTransport> userpublicTransports) {
        UserPublicTransports = userpublicTransports;
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


    @Override
    public List<MeanOfTransportLogic> getMeanPreferences() {
        int index;
        List<MeanOfTransportLogic> means = new ArrayList<>();

        //sort the user order
        List<UserOrder> us=this.userOrders;
        Collections.sort(us,(UserOrder u1, UserOrder u2) ->{
            return ((Integer)u1.getOrder()).compareTo(u2.getOrder());
        });


        //foreach order wired with the user, if the record is consistent (only one transport field is !null)
        //add the mean in the list with the correct index
        for(UserOrder u:us){
            if(!(u.getPrivateTransport()!=null && u.getPublicTransport()!=null)) {
                if (u.getPrivateTransport() != null) {
                    MeanOfTransportLogic m = u.getPrivateTransport();
                    means.add(m);
                } else if (u.getPublicTransport() != null) {
                    MeanOfTransportLogic m = u.getPublicTransport();
                    means.add(m);
                }
            }
        }


        return  means;
    }

    public PrivateTransport getPrivateTransportById(long id){
        for (PrivateTransport privateTransport: this.getPrivateTransportList()) {
           if(privateTransport.getId() == id) return privateTransport;
        }
        return null;
    }

}