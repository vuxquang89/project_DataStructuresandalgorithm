public class MyList<T> {
    private Node<T> head;
    private Node<T> tail;

    public MyList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    //trả về số phần tử có trong danh sách
    //trả về 0 nếu danh ssách rỗng
    public int length(){
        Node<T> current = head;
        if(isEmpty()) return 0;

        int index = 0;
        while (current != null){
            index++;
            current = current.getNextNode();
        }
        return index;
    }

    //trả về phần tử đầu trong danh sách
    //trả về null nếu danh sách rỗng
    public Node<T> getHead(){
        if(isEmpty()) return null;
        return this.head;
    }

    //thêm phần tử vào đầu danh sách
    public void insertToHead(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }else{
            newNode.setNextNode(head);
            this.head = newNode;
        }
    }

    //thêm phần tử vào cuối danh sách
    public void insertToTail(T data){
        Node<T> newNode = new Node<T>(data);
        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }else if(this.tail != null){
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }


    }

    //trả về vị trí của đối tượng trong danh sách
    public int get(T data){
        Node<T> current = this.head;
        int index = -1;
        if(!isEmpty() && current.getData().equals(data)){
            return 0;
        }
        while(current != null && !current.getData().equals(data)){
            index++;
            current = current.getNextNode();
        }
        if(current == null)
            return -1;
        return index;
    }

    //trả về phần tử tại vị trí index
    public Node<T> get(int index){
        Node<T> current = this.head;
        int i = 0;
        while(current != null && i != index){
            current = current.getNextNode();
            i++;
        }
        if(i == index && current != null)
            return current;
        return null;
    }

    //thêm phần tử vào sau vị trí bất kỳ
    public void insertAfterPosition(int position, T data){
        Node<T> newNode = new Node<T>(data);
        Node<T> current = get(position);
        if(current != null){
            newNode.setNextNode(current.getNextNode());
            current.setNextNode(newNode);
            if (this.tail == current){
                this.tail = newNode;
            }
        }else if(position < 0){
            insertToHead(data);
        }else{
            insertToTail(data);
        }
        /*
        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }else{
            Node<T> current = this.head;
            int index = 0;
            while(current != null){
                index++;
                if(position == length()){
                    insertToTail(data);
                }else if(index == position){
                    newNode.setNextNode(current.getNextNode());
                    current.setNextNode(newNode);
                }
                current = current.getNextNode();
            }
        }

         */
    }

    //xóa phần tử ở cuối danh sách
    public void deleteTail(){
        Node<T> current = this.head;
        int index = 0;
        int length = length();
        while (current != null){
            index++;
            if(index == length -1){
                this.tail = current;
            }else if(index > length -1){    //truong hop danh sach chi co mot phan tu.
                this.head = null;
                this.tail = null;
            }
            current = current.getNextNode();
        }
    }

    //xóa phần tử ở đầu danh sách
    public void deleteHead(){
        //Node<T> nodeDel = this.head;
        if(!isEmpty()) {
            this.head = this.head.getNextNode();
            if(isEmpty())
                this.tail = null;
        }
    }

    //xóa phần tử bất kỳ
    public boolean deleteElement(T data){
        Node<T> current = this.head;

        while(current != null){

            if(current.getData().equals(data)){
                deleteHead();
                return true;
            }
            Node<T> p = current.getNextNode();
            if(p!=null){
                if(p.getData().equals(data)){
                    if(this.tail == p)
                        this.tail = current;
                    p = p.getNextNode();
                    current.setNextNode(p);
                    return true;
                }
            }
            /*
            if (current.getNextNode().getData().equals(data)){
                if(this.tail == current.getNextNode()){
                    this.tail = current;
                }else {
                    Node<T> x = current.getNextNode();
                    x = x.getNextNode();
                    current.setNextNode(x);
                }
                break;
            }

             */
            current = current.getNextNode();
        }
        return false;
    }

    //hóa đổi giá trị
    public void swap(Node<T> x1, Node<T> x2){
        T a = x1.getData();
        x1.setData(x2.getData());
        x2.setData(a);
    }

    //xóa toàn bộ danh sách
    public void clear(){
        /*
        Node<T> current = this.head;
        while(current != null){
            deleteHead();
            current = head;
        }

         */
        this.head = null;
        this.tail = null;
    }

    //hiển thị các phần tử
    public void traverse() {
        Node<T> current = this.head;
        while(current != null){
            System.out.println(current.getData().toString());
            current = current.getNextNode();
        }
    }

    //hiển thị các phần tử
    //sử dụng đệ quy
    public String traverse(Node<T> head){
        if(head == null)
            return "";
        return head.getData() + "\n" + traverse(head.getNextNode());
    }

    //tìm phần tử theo id, trả về null nếu không tìm thấy
    public T findById(T x){
        Node<Product> current = (Node<Product>) this.head;
        //String result = "khong tim thay";
        String id = ((Product) x).getId();
        while (current != null){
            Product p = current.getData();
            if(p.getId().contains(id)){
                return (T) current.getData();
            }
            current = current.getNextNode();
        }
        return null;
    }

    //xoa phan tu bat ky sau current
    public void delete(){
        Node<T> current = this.head;
        while (current != null){
            Node<T> p = current.getNextNode();
            if(p != null){
                if(this.tail == p){
                    this.tail = current;
                }
                current.setNextNode(p.getNextNode());

            }
            current = current.getNextNode();
        }
    }

    //sắp xếp các phần tử trong danh sách theo id
    //dùng thuật toán sắp xếp chọn
    /*
    public void sortById(){
        Node<Product> i, j, min;
        for(i = (Node<Product>) this.head; i != null; i = i.getNextNode()){
            min = i;
            for(j = i.getNextNode(); j != null; j = j.getNextNode()){

                if(min.getData().getId().compareTo(j.getData().getId()) > 0){
                    min = j;
                }
            }
            swap((Node<T>) min, (Node<T>) i);
        }
    }
    */

    //sắp xếp các phần tử trong danh sách theo id
    //dùng thuật toán sắp xếp chọn
    //sử dụng đệ quy
    public void sortById(Node<Product> i){
        if(i == null)
            return;

        Node<Product> min = i;
        Node<Product> j = i.getNextNode();
        while( j != null){
            if(min.getData().getId().compareTo(j.getData().getId()) > 0){
                min = j;
            }
            j = j.getNextNode();
        }
        swap((Node<T>) min, (Node<T>) i);
        sortById(i.getNextNode());
    }
}
