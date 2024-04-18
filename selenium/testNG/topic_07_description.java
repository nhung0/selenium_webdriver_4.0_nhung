package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_07_description {
    @BeforeClass
    public void before() {
        System.out.println("before class");
    }

    @Test
    public void priority_1_testsearchate() {

    }

    @Test(description = "search date")
    // hiển thị tên test trong log/report html

    public void testsearchfilling() {

    }

    @Test
    public void testsearchproduct() {
    }


    @AfterClass(alwaysRun = true) // bắt buộc phải chạy
    public void after() {
        System.out.println("after class");
    }
}
