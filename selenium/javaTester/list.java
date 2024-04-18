package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class list {
 @Test
    public void testList() {
     //arrayList: truy xuất dữ liệu  (query)
     //linkedList: thêm sửa xóa dữ liệu
     List<String> students = new ArrayList<String>();
     students.add("phạm thị nhung");
     students.add("phạm thị an");
     students.add("phạm thị ba");


     System.out.println(students.size()); //in ra số lượng học sinh
     System.out.println(students.get(1)); //lấy ra index thứ mấy
     System.out.println(students.size() - 1); // lấy ra cái index cuối cùng

 }

}
