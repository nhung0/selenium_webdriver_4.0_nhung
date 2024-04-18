package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class topic_01_sample {

    // unit test cho hàm getRandomNumber
    @Test
    public void testRandom() {
        topic_01_sample sample = new topic_01_sample();
        int randomNumber = sample.getRandomNumber();
        Assert.assertTrue(randomNumber >= 0 && randomNumber <1000000 );




    }

    // component
    private int getRandomNumber() {
        return new Random().nextInt(100000);
    }

}
