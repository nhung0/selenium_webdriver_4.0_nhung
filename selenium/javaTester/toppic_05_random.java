package javaTester;

import java.util.Random;

public class toppic_05_random {
    public class topic05_random {
        public static void main(String[] args) {
            //utilities = tiện ích
            //Data type: class, interface, string, float....
            Random rand = new Random();
            System.out.println(rand.nextFloat());
            System.out.println(rand.nextInt(999999));
            System.out.println(rand.nextDouble());
            System.out.println("auto" + rand.nextInt(9999) + "@gmail.com");
        }
    }

    public static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(99999);
    }

}
