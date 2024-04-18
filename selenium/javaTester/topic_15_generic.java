package javaTester;

import java.util.ArrayList;
import java.util.List;

public class topic_15_generic {
    public static void main(String[] args ) {
        // list chỉ chứa dũ liêu string, generic
        // E,T,K,L : đạo diện cho môt kiểu dữ liệu của element
        List<String> students = new ArrayList<String>();
        students.add("nhung");
        students.add("pham");
        students.add("thi");
        students.add("phamm");

        //non-generic
        List addresses = new ArrayList<>();
       addresses.add("nhung"); //string
        addresses.add(15); // int
        addresses.add(true); // boolean
       addresses.add(6.9); // float

    }
}
