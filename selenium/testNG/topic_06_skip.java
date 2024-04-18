package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_06_skip {
    @BeforeClass
    public void before() {
        System.out.println("before class");
    }

    @Test
    public void priority_1_testsearchate() {  // nên dufngn vi nó sẽ chạy theo thứ tự alphabeta

    }

    @Test(enabled = false) // k chạy testcase này  hoặc xóa @test thì nó sẽ không chạy

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
