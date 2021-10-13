import java.util.Formatter;
import java.util.Scanner;

public class ASM2_main {


    public static void main(String[] args) {
        MyList<Product> myList = new MyList<>();
        MyQueue<Product> myQueue = new MyQueue<>();
        MyStack<Product> myStack = new MyStack<>();

        OperationToProduct opProduct = new OperationToProduct();
        int choose;

        while(true){
            System.out.println("\n+--------------------MENU----------------------+");
            System.out.println("|       1. Load data from file and display.    |");
            System.out.println("|       2. Input and add to the end.           |");
            System.out.println("|       3. Display data.                       |");
            System.out.println("|       4. Save product list to file.          |");
            System.out.println("|       5. Search by ID.                       |");
            System.out.println("|       6. Delete by ID.                       |");
            System.out.println("|       7. Sort by ID.                         |");
            System.out.println("|       8. Convert to Binary.                  |");
            System.out.println("|       9. Load to stack and display.          |");
            System.out.println("|       10. Load to queue and display.         |");
            System.out.println("|       0. Exit                                |");
            System.out.println("+----------------------------------------------+");
            //System.out.print("Choose one of the options(0->10): ");
            //choose = opProduct.scannerInputInteger();
            choose = opProduct.checkScannerInt("Choose one of the options(0->10): ");

            if(choose == 0){
                System.out.println("Exit.");
                break;
            }

            switch (choose){
                case 1: //đọc dữ liệu có sẵn từ file và lưu vào danh sách móc nối; hiển thị danh sách
                    System.out.println("1. Load data from to file and display.");
                    opProduct.getAllItemsFromFile("data.txt", myList);
                    break;
                case 2: //nhập và thêm một sản phẩm vào cuối của danh sách móc nối
                    System.out.println("2. Input and add to the end.");
                    opProduct.addLast(myList);
                    break;
                case 3: //hiển thị thông tin của các sản phẩm trong danh sách móc nối
                    System.out.println("3. Display data");
                    opProduct.displayAll(myList);
                    break;
                case 4: //luu danh sách móc nối các sản phầm vào file
                    System.out.println("4. Save product list to file.");
                    System.out.println(opProduct.writeAllItemsToFile("data.txt", myList));
                    break;
                case 5: //tìm kiếm thông tin sản phẩm theo ID
                    System.out.println("5. Search product by ID.");
                    opProduct.searchById(myList);
                    break;
                case 6: //xóa sản phẩm trong danh sách theo ID
                    System.out.println("6. Delete product by ID.");
                    opProduct.deleteById(myList);
                    break;
                case 7: //sắp xếp các sản phẩm trong danh sách moc nối theo ID
                    System.out.println("7. Sort product by ID.");
                    //opProduct.sortById(myList);
                    //myList.sortById();
                    opProduct.writeToFile(opProduct.FILE_OUTPUT, "Sort Element:\n");
                    myList.sortById(myList.getHead());
                    opProduct.displayAll(myList);
                    break;
                case 8: //biểu diễn số lượng sản phẩm(đang ở hệ đếm 10) của phần tử đầu tiên trong Linked List
                        //sang hệ đếm nhị phân bằng đệ quy
                    System.out.println("8. Convert to Binary.");
                    opProduct.showBinary(myList);
                    break;
                case 9: //đọc dữ liệu từ file chứa các sản phẩm rồi lưu vào stack.
                        // Hiển thị ra màn hình các sản phẩm có trong stack
                    System.out.println("9. Load data from file to stack.");
                    opProduct.getAllItemsFromFile("data.txt", myStack);
                    break;
                case 10: //đọc dữ liệu từ file chứa các sản phẩm rồi lưu vào queue.
                        //hiển thị ra màn hình các sản phẩm có trong queue
                    System.out.println("10. Load data from file to queue.");
                    opProduct.getAllItemsFromFile("data.txt", myQueue);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
