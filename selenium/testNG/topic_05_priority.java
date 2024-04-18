package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_05_priority {
    @BeforeClass
    public void before() {
        System.out.println("before class");
    }

    @Test(priority = 1) // muốn run theo thứ tự, k dùng vì lâu
    public void priority_1_testsearchate() {  // nên dufngn vi nó sẽ chạy theo thứ tự alphabeta

    }

    @Test(priority = 2)

    public void testsearchfilling() {

    }

    @Test(priority = 3)
    public void testsearchproduct() {
    }


    @AfterClass(alwaysRun = true) // bắt buộc phải chạy
    public void after() {
        System.out.println("after class");
    }
}
