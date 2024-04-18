package javaTester;

public class topic_14_construtor {
    // là 1 hàm cùng tên với class
    // k có kiểu dữ liệu trả về
    // có nhiêu constructor cũng đc
    public topic_14_construtor (String name) {
        System.out.println(name);
    }

    public static void main(String [] args ) {
        topic_14_construtor topic = new topic_14_construtor("automation");
    }
}
