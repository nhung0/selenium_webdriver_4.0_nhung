package testNG;

import org.testng.annotations.*;

public class topic_02_annotations {
    @BeforeClass
    public  void before() {
        System.out.println("before class");
    }
    @AfterClass
    public  void after() {
        System.out.println("after class");
    }

    @BeforeGroups
    public  void beforeGroups() {
        System.out.println("before groups");
    }

    @AfterGroups
    public  void afterGroups() {
        System.out.println("after groups");
    }

    @BeforeMethod
    public  void beforeMethod() {
        System.out.println("before method");
    }

    @BeforeTest
    public  void beforeTest() {
        System.out.println("before test");
    }

    @AfterTest
    public  void afterTest() {
        System.out.println("after test");
    }
    @Test
    public  void test() {
        System.out.println("test");
    }
    @AfterMethod
    public  void afterMethod() {
        System.out.println("after method");
    }

    @AfterSuite
    public  void afterSuite() {
        System.out.println("after suite");
    }
    @AfterClass
    public  void afterClass() {
        System.out.println("after class");
    }
    @Test
    public  void test1() {
        System.out.println("test1");
    }

    @BeforeSuite
    public  void beforeSuite() {
        System.out.println("before suite");
    }
}
