package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

import java.io.File;

public class topic_03_system_info {
    public static void main(String arg ) {
        //lấy ra đường dấn tương đối tai thư mục hiện tại
        String projectPath = System.getProperty("user.dir");
        String fileName = "cảnh.jpg";
        System.out.println(projectPath +"\\uploadFiles\\" + fileName);


        String osName = System.getProperty("os.name");
        Keys keys;
        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        Keys cmdtrl = Platform.getCurrent().is(Platform.WINDOWS)? Keys.CONTROL: Keys.COMMAND;


        //tạo đường dẫn cho files ảnh
//        String character = Platform.getCurrent().is(Platform.WINDOWS)? "\\" : "/";
        String character = File.separator;
        String quaName = "quá.jpg";
        String quaFile = projectPath + File.separator +"uploadFiles" + File.separator + quaName;
        System.out.println(quaFile);



    }
}
