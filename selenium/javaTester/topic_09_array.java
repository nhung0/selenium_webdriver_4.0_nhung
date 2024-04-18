package javaTester;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

import javax.swing.plaf.synth.SynthTableUI;

public class topic_09_array {
    int[] studentage = {13, 7, 99};
    static String[] studentname = {"nhung", "pham"};

    public static void main(String[] args) {
        String[] studentaddress = new String[5];

        studentaddress[0] = "đặng ngọc anh";
        studentaddress[1] = "nhung pham";
        studentaddress[2] = "abc";

        System.out.println(studentname[1]);

        topic_09_array topic = new topic_09_array();
        System.out.println(topic.studentage[0]);

        for (int i=0; i<studentname.length; i++){
            System.out.println(studentname[i]);
        }


    }
}
