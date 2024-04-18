package search;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class order_01_search {
    @BeforeClass(alwaysRun = true)
    public void before() {
        System.out.println("before class");
    }

    @Test(groups = "search")
    public void testsearchate() {

    }

    @Test(groups = "search")

    public void testsearchfilling() {

    }

    @Test(groups = "search")
    public void testsearchproduct() {
    }
    @AfterClass(alwaysRun = true) // bắt buộc phải chạy
    public void after() {
        System.out.println("after class");
    }
}
