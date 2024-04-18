package javaTester;

import org.testng.annotations.Test;

public class topic_10_settergetter {



    private String fullname; // để ngăn cấm việc truy cập, dùng private viết hàm get set 
    @Test
    public void testGetterSetter(){
        setFullname("nhung");
        getFullname();
        setFullname("pham"); // gán lại giá tri mới
        getFullname(); // trả về 2 giá trị nhung và pham
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }



}
