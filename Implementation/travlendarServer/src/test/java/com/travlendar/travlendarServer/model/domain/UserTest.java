package com.travlendar.travlendarServer.model.domain;

import com.travlendar.travlendarServer.model.EnumGreenLevel;
import com.travlendar.travlendarServer.model.MeanType;
import com.travlendar.travlendarServer.model.Policy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public static User createUser(){
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);

        List<PrivateTransport> privateTransports = new ArrayList<>();
        Green greenHigh = new Green();
        Green greenMedium = new Green();
        Green greenLow = new Green();

        greenHigh.setLevel(EnumGreenLevel.HIGH);
        greenMedium.setLevel(EnumGreenLevel.MEDIUM);
        greenLow.setLevel(EnumGreenLevel.LOW);

        PrivateTransport privateTransport1 = new PrivateTransport();
        privateTransport1.setGreen(greenHigh);
        privateTransport1.setMeanType(MeanType.BIKE);
        privateTransport1.setUsers(users);

        PrivateTransport privateTransport2 = new PrivateTransport();
        privateTransport2.setGreen(greenLow);
        privateTransport2.setMeanType(MeanType.CAR);
        privateTransport2.setUsers(users);

        PrivateTransport privateTransport3 = new PrivateTransport();
        privateTransport3.setGreen(greenMedium);
        privateTransport3.setMeanType(MeanType.ELECTRIC_CAR);
        privateTransport3.setUsers(users);

        PublicTransport publicTransport = new PublicTransport();
        publicTransport.setGreen(greenMedium);
        publicTransport.setType(MeanType.BUS);
        List<UserPublicTransport> userPublicTransports = new ArrayList<>();
        UserPublicTransport userPublicTransport = new UserPublicTransport();
        userPublicTransport.setPublicTransport(publicTransport);
        userPublicTransport.setUser(user);
        userPublicTransports.add(userPublicTransport);
        publicTransport.setUserpublicTransports(userPublicTransports);

        privateTransports.add(privateTransport1);
        privateTransports.add(privateTransport2);
        privateTransports.add(privateTransport3);

        List<UserOrder> userOrders = new ArrayList<>();
        UserOrder userOrder1 = new UserOrder();
        UserOrder userOrder2 = new UserOrder();
        UserOrder userOrder3 = new UserOrder();
        UserOrder userOrder4 = new UserOrder();

        userOrder1.setOrder(1);
        userOrder2.setOrder(2);
        userOrder3.setOrder(3);
        userOrder4.setOrder(4);
        userOrder1.setPrivateTransport(privateTransport2); // GREEN LOW
        userOrder2.setPublicTransport(publicTransport); // GREEN MEDIUM
        userOrder3.setPrivateTransport(privateTransport3); // GREEN MEDIUM
        userOrder4.setPrivateTransport(privateTransport1); //GREEN HIGH

        userOrders.add(userOrder1);
        userOrders.add(userOrder2);
        userOrders.add(userOrder3);
        userOrders.add(userOrder4);

        user.setPolicy(Policy.GREEN);
        user.setPrivateTransportList(privateTransports);
        user.setUserOrders(userOrders);

        return user;
    }
}