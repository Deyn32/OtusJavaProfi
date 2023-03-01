package ru.otus.model;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;
import ru.otus.exception.FailedTestException;
import ru.otus.exception.SuccessTestException;

public class DemoClassTest {

    private final DemoClass dc = new DemoClass();

    @Before
    public void setUp() {
        dc.setX(5);
        dc.setY(7);
    }

    @Test
    public void sumTest() throws FailedTestException, SuccessTestException {
        int result = dc.sum();
        if(result != 12) {
            throw new FailedTestException();
        }
        else {
            throw new SuccessTestException();
        }
    }

    @Test
    public void areaTest() throws FailedTestException, SuccessTestException {
        int result = dc.area();
        if(result != 35) {
            throw new FailedTestException();
        }
        else {
            throw new SuccessTestException();
        }
    }

    @Test
    public void minusTest() throws FailedTestException, SuccessTestException {
        int result = dc.minus();
        if(result != 2) {
            throw new FailedTestException();
        }
        else {
            throw new SuccessTestException();
        }
    }

    @After
    public void setDown() {
        dc.setX(0);
        dc.setY(0);
    }
}
