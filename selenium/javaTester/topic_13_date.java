package javaTester;

import java.util.Date;

public class topic_13_date {
    public static void main() {
        System.out.println(getDatetimeout());
    }
    public static String getDatetimeout() {
        Date date = new Date();
        return date.toString();
    }
}
