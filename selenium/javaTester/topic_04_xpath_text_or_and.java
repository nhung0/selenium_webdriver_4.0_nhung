package javaTester;

public class topic_04_xpath_text_or_and {
    public static void main(String[] args) {
        boolean stta;
        boolean sttb;
        // and: cả 2 điều kiện đều đúng thì kết quả mới đúng, còn lại là sai


        stta = true;
        sttb = false;
        System.out.println("kết quả = " + (stta && sttb));

        //  or: ngược lại, cả 2 điều kiện sai mới sai, còn lại là đúng

        stta = true;
        sttb = false;
        System.out.println("kết quả = " + (stta || sttb));

    }
    // vd: trong xpath có 2 type là email thì ta lấy thêm 1 attribute là input nữa
    //input[@id='email'and @type='email']

    // dùng or thì ra nhiều cái hơn mặc dù điều kiện @id bị sai ta vẫn sẽ tìm đc element chứa chữ nhung
    //h5[text()= 'nhung' or @id = 'seve']


    // not: lấy phủ định, lấy thẻ chứa tên id là tên nhưng không có style là nhung

    //vd: //div[not(@style = 'nhung')]/div[@id='ten']

    // inside parent: cùng cấp cung cha, nếu khác cha thì gom nó lại bằng cách cho cú pháp xpath vào ngoặc. topic 15, 1h50p

    //position

    //vd: //li[1] hoặc //li[position() = 1]


    //last() : luôn muốn lấy ra cái cuối cùng mà không quan tâm toàn bộ là bao nhiêu

    //vd: //li[last()] => tự động ra cái thẻ li cuối cùng

    // axes xpath: hay còn gọi là cách tìm kiếm thông qua giàn buộc các node, lấy cái cố định để tìm các không cố định
    //vd: //div[@class='shopee_itemcard__name_container line-clamp-2 break-words max-h-10 text-[0.875rem] leading-5']/parent::div/following-sibling::div[@style='min-height: 20px;']/div/div/div/span[contains(.,'71.200')]

    // xpath function


    // difference between xpath và css
    //driver.get("http://live.techpanda.org/index.php/customer/account/login/");
    //xpath
    // //ul[@class ='form-list']/input[@id='email']  -direct child
    // //ul[@class = 'form-list']//input[@id='email'] -sub child
    // //ul[@class = 'form-list']  -class
    // //input[@name='login[user-name]' -attribute
    // //input[@id= 'email' and @name='user-name'] -multiple attribute, kết hợp nhiều điều kiện
    // //div[contains(@class, 'new-user')] - attribute contains chứa giá trị gì
    // //form[starts-with(@id, 'login')] -starts-with bắt đầu bằng giá trị gì
    // //ul[@class='form-list']/li[1]   - index vị trí thứ mấy trong cùng một node cha
    // //ul[@class='form-list']/li[last()]  - last child
    // //a[@title = 'Samsung Galaxy']/following::div/h2/following-sibling::div[1]   -lấy bất kì em nào muốn
    // //a[@title = 'Samsung Galaxy']/following::div/h2/following-sibling::div     -lấy toàn bộ em
    // //input[@id= 'email' or @name='user-name']  - or attribute. chọn 1 trong 2 điêu kiện
    // //input[not(@id='email')]                  -not attribute, lấy ra các id không phải là email
    // //input[@id='email'] | //input[@id='password']   - 2 xpath

    //xpath text: text()= text của thẻ đó thôi, k lấy thẻ con, text nằm ở trên cùng hàng với chính node(tagname) đó- không có nằm trong child node - không phải dang nested text, text có thể là index ở đầu giữa/cuối đều lấy đc so với các thẻ con khác, lấy text tuyệt đối là text không có khoảng trắng/ xuống dòng/ tab ở đầu hoăc cuối chuỗi
    //Contains(Text(),''): text nó nằm trên chính node đó, dạng nested text nhưng text phải nằm ở đầu tiên, nếu text nằm trực tiếp trong child node thì sẽ không lấy đươc, text	này có khoảng trắng/ xuống dòng/ tab ở đầu hoặc cuối text vẫn work được(đương đối), không dùng Contains(Text(='') mà dùng Contains(Text(),'')
    // contains(.,''): text nằm ở đâu cũng lấy đc
    // contains(string(),''): text nằm ở đâu cũng lấy đc, giống . ở trên
    // nối chuỗi: concat()  vd: //span[text()=concat('Hello "john". what', "'s happened")







    //css

    // ul[class ='form-list']>input[id='email']  -direct child
    // ul[class ='form-list'] input[id='email'] hoặc ul[class ='form-list'] input#email hoặc ul[class ='form-list'] #email  -sub child
    // ul[class = 'form-list']  hoặc ul.form-list hoặc .form-list hoặc div.col-1.new-users hoặc div.new-users(khi trong 1 class có khoảng trắng)  -class
    // input[name='login[user-name]' -attribute
    // input[id= 'email'][name='user-name'] -multiple attribute
    // div[class* = 'new-user'] -attribute contains chứa giá trị gì
    //  form[id^='login'] -starts-with bắt đầu bằng giá trị gì
    // div[class$='new-user'] -end-with kết thúc bằng giá trị gì
    // ul[class='form-list']>li:nth-child(1) -index vị trí thứ mấy trong cùng một node cha
    // ul[class='form-list']>li:first-child  - first child
    // ul[class='form-list']>li:last-child  - last child
    // a[title = 'Samsung Galaxy']~div>h2~div   -lấy toàn bộ thẻ em dưới nó ngang hàng (dùng dấu ngã để lấy toàn bộ)
    // a[title = 'Samsung Galaxy']~div>h2+div    - lấy thẻ em liền hề h2 và lấy đc 1 thôi
    // input[id= 'email'],[name='user-name']    - or attribute, both of it
    // input:not([@id='email']) hoặc input:not(#email)   hoặc input:not(.user)     -not attribute, lấy ra các id không phải là email
    // *                  - chọn tất


}
