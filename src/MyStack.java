import java.util.EmptyStackException;

public class MyStack<T> {
    private Node<T> head;

    public MyStack() {
        this.head = null;
    }

    //thêm phần tử vào đầu Stack
    public void push(T data){
        Node<T> newNode = new Node<T>(data);
        newNode.setNextNode(this.head);
        this.head = newNode;
    }

    //trả về số lượng phần tử trong Stack
    public int length(){
        Node<T> current = this.head;
        if(isEmpty()) return 0;
        int index = 0;
        while (current != null){
            index++;
            current = current.getNextNode();
        }
        return index;
    }

    //kiểm tra Stack rỗn hay không
    public boolean isEmpty(){
        return this.head == null;
    }

    //lấy phần tử đầu ra khỏi Stack,
    //đồng thời xóa phần tử đó
    //trả về null nếu Stack trống
    public T pop(){
        //if(isEmpty()) throw new EmptyStackException();
        if(isEmpty()) return null;
        T x = this.head.getData();
        this.head = this.head.getNextNode();
        return x;
    }
}
