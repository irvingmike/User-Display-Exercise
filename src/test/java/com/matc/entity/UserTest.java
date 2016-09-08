package com.matc.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aaron on 9/8/16.
 */
public class UserTest {

    User user;

    @Before
    public void setup() {
        user = new User("firstname", "lastname", "10", "2000-01-01");
    }

    @Test
    public void getAge() throws Exception {
        //Verify that getAge returns correct value
        // User born 2000-01-01, expected value: 16
        assertEquals(16, user.getAge("2000-01-01"));
        assertEquals(43, user.getAge("1972-11-26"));
    }

}