package com.viteats.vitetats1.util;

import com.viteats.vitetats1.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataUtilTest {

    @BeforeEach
    public void setUp() {
        DataUtil.resetData();
    }

    @Test
    public void testLoginSuccess() {
        User user = DataUtil.login("admin", "admin");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void testLoginFailure() {
        User user = DataUtil.login("admin", "wrongpassword");
        assertNull(user);
    }

    @Test
    public void testAddUser() {
        int initialSize = DataUtil.getUsers().size();
        User newUser = new Customer("newUser", "password", 100.0);
        DataUtil.addUser(newUser);
        assertEquals(initialSize + 1, DataUtil.getUsers().size());
        assertNotNull(DataUtil.login("newUser", "password"));
    }

    @Test
    public void testAddOrder() {
        List<String> items = List.of("Pizza", "Burger");
        Order order = new Order("customer", items, 21.98, "Dine In", "Credit Card");
        DataUtil.addOrder(order);
        List<Order> orders = DataUtil.getOrders();
        assertEquals(1, orders.size());
        assertEquals("customer", orders.get(0).getUsername());
    }

    @Test
    public void testGetOrdersForDelivery() {
        List<String> items = List.of("Pizza");
        Order dineInOrder = new Order("customer", items, 12.99, "Dine In", "Credit Card");
        Order deliveryOrder = new Order("customer", items, 12.99, "Delivery", "Credit Card");
        DataUtil.addOrder(dineInOrder);
        DataUtil.addOrder(deliveryOrder);
        List<Order> deliveryOrders = DataUtil.getOrdersForDelivery();
        assertEquals(1, deliveryOrders.size());
        assertEquals("Delivery", deliveryOrders.get(0).getModeOfEating());
    }
}