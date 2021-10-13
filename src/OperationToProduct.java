import java.io.*;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Scanner;

public class OperationToProduct {
    public final String ERROR_MESSAGE = "Entered incorrect. Please re-enter! ";
    public String STYLE_LINE = "-----------------------------------------------------";
    public String FILE_OUTPUT = "console_output.txt";
    public Scanner sn = new Scanner(System.in);
    //public FileWriter out = null;

    /*
    public int index(Product p, MyList<Product> myList){
        for (int i = 0; i < myList.length(); i++) {
            if(myList.get(i).getData().equals(p)){
                return i;
            }
        }
        return -1;
    }

     */

    //tạo product với id, title, quality, price nhập từ bàn phím
    public Product createProduct(){
        System.out.print("- Enter product id: ");
        String id = sn.nextLine();
        System.out.print("- Enter product title: ");
        String title = sn.nextLine();
        System.out.print("- Enter quality: ");
        int quality = scannerInputInteger();
        System.out.print("- Enter price: ");
        double price = scannerInputDouble();

        return new Product(id, title, quality, price);
    }

    //đọc tất cả sản phẩm từ file, lưu vào danh sách(thêm vào cuối danh sách)
    public void getAllItemsFromFile(String fileName, MyList<Product> myList){
        String result = "";
        String[] str = new String[4];
        myList.clear();
        int index = 0;
        try {
            FileReader fReader = new FileReader(fileName);
            int c;
            while((c = fReader.read()) != -1){
                if(c == 32){    //khoang trang
                    str[index] = result;
                    index++;
                    result = "";
                }else if(c == 10){    //xuong dong => luu vao danh sach
                    //System.out.println(str[0] + " " + str[1] + " " + str[2] + " " + str[3]);
                    Product p = new Product(str[0], str[1], Integer.parseInt(str[2]), Double.parseDouble(str[3]));
                    myList.insertToTail(p);
                    index = 0;
                    result = "";
                }else {
                    result += (char) c;
                }

            }
            fReader.close();
            System.out.println("- Successfully load!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //đọc tất cả sản phẩm từ file, lưu vào Stack
    //hiển thị các phần tử có trong Stack
    public void getAllItemsFromFile(String fileName, MyStack<Product> myStack){
        String result = "";
        String output = "Save to Stack\n";
        String[] str = new String[4];
        int index = 0;
        try {
            FileReader fReader = new FileReader(fileName);
            int c;
            while((c = fReader.read()) != -1){
                if(c == 32){    //khoang trang
                    str[index] = result;
                    index++;
                    result = "";
                }else if(c == 10){    //xuong dong => luu vao danh sach
                    //System.out.println(str[0] + " " + str[1] + " " + str[2] + " " + str[3]);
                    Product p = new Product(str[0], str[1], Integer.parseInt(str[2]), Double.parseDouble(str[3]));
                    myStack.push(p);
                    index = 0;
                    result = "";
                }else {
                    result += (char) c;
                }

            }
            fReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //hiển thị các phần tử có trong Stack
        headerTable();
        Product pCurrent = null;
        while((pCurrent = myStack.pop()) != null){
            System.out.println(pCurrent);
            output += pCurrent + "\n";
        }
        System.out.println(STYLE_LINE);
        //ghi vao file output
        writeToFile(FILE_OUTPUT, output);
    }

    //đọc tất cả sản phẩm từ file, lưu vào Queue
    //hiển thị các phần tử có trong Queue
    public void getAllItemsFromFile(String fileName, MyQueue<Product> myQueue){
        String result = "";
        String output = "Save to Queue\n";
        String[] str = new String[4];
        int index = 0;
        try {
            FileReader fReader = new FileReader(fileName);
            int c;
            while((c = fReader.read()) != -1){
                if(c == 32){    //khoang trang
                    str[index] = result;
                    index++;
                    result = "";
                }else if(c == 10){    //xuong dong => luu vao danh sach
                    //System.out.println(str[0] + " " + str[1] + " " + str[2] + " " + str[3]);
                    Product p = new Product(str[0], str[1], Integer.parseInt(str[2]), Double.parseDouble(str[3]));
                    myQueue.enQueue(p);
                    index = 0;
                    result = "";
                }else {
                    result += (char) c;
                }

            }
            fReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //hiển thị sản phẩm có trong Queue
        headerTable();
        Product pCurrent = null;
        while((pCurrent  = myQueue.poll()) != null){
            System.out.println(pCurrent);
            output += pCurrent + "\n";
        }
        System.out.println(STYLE_LINE);
        //ghi vao file output
        writeToFile(FILE_OUTPUT, output);
    }

    //lưu tất cả sản phầm trong danh sách vào file
    public String writeAllItemsToFile(String fileName, MyList<Product> myList){
        FileWriter out = null;
        String msg;
        try{
            out = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(out);

            int length = myList.length();
            for(int i = 0; i < length; i++){
                Product p = myList.get(i).getData();
                printWriter.print(p.string());
                printWriter.print((char)10);
            }
            printWriter.close();
            out.close();
            msg = "Successfully save!";
        }catch (IOException e){
            e.printStackTrace();
            msg = "Fail save!";
        }
        return msg;
    }

    //lưu thông tin vào file
    public void writeToFile(String fileName, String str) {
        FileWriter out;
        try {
            out = new FileWriter(fileName, true);   //Giữ nội dung đang tồn tại và thêm nội dung mới vào cuối file
            PrintWriter printWriter = new PrintWriter(out);
            printWriter.print(str);
            printWriter.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //thêm sản phầm mới vào cuối danh sách
    public void addLast(MyList<Product> myList){
        String out = "- Input element:\n";
        boolean answer;
        do {
            Product p = createProduct();
            //System.out.println(p.toString());
            myList.insertToTail(p);
            out += p + "\n";
            //System.out.println(myList.length());

            System.out.print("Do you want continue (y/yes) or other key to close? ");
            answer = question();
        }while (answer);

        writeToFile(FILE_OUTPUT, out);
    }

    //hiển thị thông tin tất cả sản phẩm trong danh sách
    public void displayAll(MyList<Product> myList){
        headerTable();
        if(!myList.isEmpty()) {
            //myList.traverse();
            String out = myList.traverse(myList.getHead());
            System.out.print(out);
            writeToFile(FILE_OUTPUT, "Show all element in LinkedList\n" + out);
        }else{
            System.out.println("Linked List is null!");
        }
        System.out.println(STYLE_LINE);
    }

    //tìm kiếm sản phẩm trong danh sách theo id
    public void searchById(MyList<Product> myList){
        System.out.print("- Enter the ID you want to search: ");

        String id = sn.nextLine();
        String out = "";
        headerTable();
        Product p = new Product(id);
        p = myList.findById(p);
        if(p != null){
            //System.out.println(p);
            out += p;
        }else{
            out = "-1";

        }
        System.out.println(out);
        System.out.println(STYLE_LINE);

        writeToFile(FILE_OUTPUT, "- Search by id = " + id + "\n" + out + "\n");

        //result = myList.findById(p).toString();
        /*
        for(int i = 0; i < myList.length(); i++){
            //System.out.println(myList.get(i).getData());
            Product p = myList.get(i).getData();
            if(p.getId().contains(id)){
                result = p.toString();
            }
        }

         */


    }

    //xóa sản phẩm trong danh sách theo id
    public void deleteById(MyList<Product> myList){
        System.out.print("- Enter the ID you want to delete: ");
        String id = sn.nextLine();
        String out;
        Product pDelete = new Product(id);
        pDelete = myList.findById(pDelete);
        if(pDelete != null){
            if(myList.deleteElement(pDelete)) {
                out = "- Successfully delete product with ID: " + id + "\n" + pDelete;
            }else{
                out = "- Can not delete product with ID: " + id;
            }
        }else{
            out = "- Do not find product with ID: " + id;
        }
        System.out.println(out);
        writeToFile(FILE_OUTPUT, out + "\n");
    }

    //đổi hệ đếm cơ số 10 sang hệ đếm nhị phân dùng đệ quy
    public String covertToBinary(int i){
        if(i == 0) return "";
        return covertToBinary(i/2) + i%2;
        //System.out.print(i%2);
    }

    //hiển thị kết quả số lượng của phần tử đầu tiên
    //sang hệ đếm nhị phân
    public void showBinary(MyList<Product> myList){
        headerTable();
        if(!myList.isEmpty()){
            //Product p = myList.get(0).getData();
            Product p = myList.getHead().getData();
            System.out.println(p);
            System.out.println(STYLE_LINE);
            String str = "- Quality: " + p.getQuality() + " => " + covertToBinary(p.getQuality());
            writeToFile("console_output.txt", "- Covert to binary: " + str + "\n");
            System.out.println(str);

            //System.out.println();
        }else{
            System.out.println("Linked List is null!");
        }
    }

    private void headerTable() {
        Formatter formatter = new Formatter();
        System.out.println(STYLE_LINE);
        System.out.println(formatter.format("|%6s|%17s|%10s|%15s|", "ID", "Title", "Quality", "Price"));
        System.out.println(STYLE_LINE);
    }

    /* kiem tra nhap tu ban phim kieu Integer */
    public int scannerInputInteger(){
        int rec = 0;
        boolean checkInput = false;

        do{
            String scannerInput = sn.nextLine();
            try{
                rec = Integer.valueOf(scannerInput);//chuyển tử kiểu String sang Interger
                checkInput = true;
            }catch (Exception exception){
                System.out.print(ERROR_MESSAGE);
                checkInput = false;
            }
        }
        while (!checkInput);

        return rec;
    }

    //kiem tra nhap tu ban phim kieu int
    public int checkScannerInt(String mes){
        boolean check;
        int number = 0;
        do{
            System.out.print(mes);
            try {
                number = Integer.parseInt(sn.nextLine());
                check = true;
            }catch (Exception ex){
                check = false;
            }
        }while(!check);
        return number;
    }

    /* kiem tra nhap tu ban phim kieu Integer */
    public double scannerInputDouble(){
        double rec = 0.0;
        boolean checkInput = false;

        do{
            String scannerInput = sn.nextLine();
            try{
                rec = Double.valueOf(scannerInput);//chuyển tử kiểu String sang Double
                checkInput = true;
            }catch (Exception exception){
                System.out.print(ERROR_MESSAGE);
                checkInput = false;
            }
        }
        while (!checkInput);

        return rec;
    }

    /* phương thức tạo câu hỏi, hỏi người dùng muốn tiếp tục */
    public boolean question(){
        //System.out.print("Do you want to play again? ");
        String question = sn.nextLine();
        question = question.trim().toLowerCase();
        if(question.equals("y") || question.equals("yes")){
            return true;
        }
        return false;
    }
}
