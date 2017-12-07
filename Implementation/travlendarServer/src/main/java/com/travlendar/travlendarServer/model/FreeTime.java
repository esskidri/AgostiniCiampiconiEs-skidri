package com.travlendar.travlendarServer.model;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "free_time")
public class FreeTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name="duration")
    private int duration;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;
    public User getUser() {
        return this.user;
    }

    public FreeTime(){}

}