package com.roylic.java17.demo;

import com.roylic.java17.demo.service.ConcreteTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncCallingTest {

    @Autowired
    private ConcreteTask concreteTask;


    @Test
    public void syncCallingTest() throws Exception {
        concreteTask.doTaskOne();
        concreteTask.doTaskTwo();
        concreteTask.doTaskThree();
    }
}
