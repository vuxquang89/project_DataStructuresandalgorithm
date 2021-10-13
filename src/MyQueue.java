import java.util.EmptyStackException;

public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    public MyQueue() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    //chèn phần tử vào trong Queue
    public void enQueue(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }else if(this.tail != null) {
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
    }

    //loại bỏ phần tử đầu trong Queue
    //trả về null nếu Queue rỗng
    //deQueue()
    public T poll(){
        if(isEmpty()) return null;
        T data = this.head.getData();
        this.head = this.head.getNextNode();
        if(isEmpty()) this.tail = null;
        return data;
    }

    //lấy phần tử ở đàu Queue mà không xóa phần tử này
    //trả về null nếu Queue rỗng
    public T peek(){
        //if(isEmpty()) throw new EmptyStackException();
        if(isEmpty()) return null;
        return this.head.getData();
    }

    //đếm số phần tử trong Queue
    public int length(){
        Node<T> current = this.head;
        if(isEmpty()) return 0;
        int index = 0;
        while(current != null){
            index++;
            current = current.getNextNode();
        }
        return index;
    }
}
