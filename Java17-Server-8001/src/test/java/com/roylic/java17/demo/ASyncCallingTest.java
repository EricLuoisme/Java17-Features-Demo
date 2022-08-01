package com.roylic.java17.demo;

import com.roylic.java17.demo.service.AsyncTask;
import com.roylic.java17.demo.service.ConcreteTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ASyncCallingTest {

    @Autowired
    private ConcreteTask concreteTask;

    @Autowired
    private AsyncTask asyncTask;


    @Test
    public void syncCallingTest() throws Exception {
        concreteTask.doTaskOne();
        concreteTask.doTaskTwo();
        concreteTask.doTaskThree();
    }

    @Test
    public void asyncCallingTest() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }
}
