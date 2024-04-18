package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.List;

public class topic_02_datatypes {
    public static void main(String[] args) {
        // I.kieu du lieu nguyen thuy(primitive)
        // so nguyen: byte short in long(khong co phan thap phan)
        // kich thuoc do rong de luu tru du lieu tu nho den lon
        byte bNumber = 127;

        short s = 12433;

        int iNumber = 4804755;

        long lNumber = 148474527;


        // so thuc: float double
        float fNumber = 6.5f;

        double dNumber = 35.60d;

        // logic: boolean
        boolean status = true;
        status = false;

        //ky tu: char
        char a = 'A';





        // 2.kieu tham chieu(reference)
        // class
        FirefoxDriver driver = new FirefoxDriver();

        // interface
        WebElement firstNameTextbox;
        // string
        String firstName = "automation test";

        //object
        Object people;

        //array
        String[] firstName1 = {"nhung", "pham", "thi"};
        //collection: list/ Set/ queue
        List<WebElement> checkboxesElements = driver.findElements(By.cssSelector(""));

        // map
        HashMap<String,Integer> student = new HashMap<String, Integer>();
    }

}
