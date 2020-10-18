package WilliamFiset;

public class DoublyLinkedList<T> implements Iterable<T>{
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;
    
    
    
    private static class Node<T>{
        private T data;
        private Node<T> prev;
        private Node<T> next;
        
        public Node(T data,Node<T> prev,Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        
        @Override
		public String toString(){
            return this.data.toString();
        }
        
        
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return this.size;
    }
    
    public void insertHead(T data){
        if(isEmpty()){
            head = tail = new Node<T>(data,null,null);
            
        }else{
            head.prev = new Node<T>(data,null,head);
            
            head = head.prev;
            
        }
        size++;
    }
    
    public void insertTail(T data){
        
        if(isEmpty()){
            head = tail = new Node<T>(data,null,null);
            
        }else{
            tail.next = new Node<T>(data,tail,null);
            
            tail = tail.next;
        }
        size++;
        
    }
    public void insert(T data){
        insertTail(data);
    }
    
    
    public T peekFirst(){
        if(isEmpty()) throw new RuntimeException("peeking cannot be done");
        return head.data;
    }
    
    public T peekLast(){
        if(isEmpty()) throw new RuntimeException("peeking last cannot be done");
        return tail.data;
    }
    
    
    
    public void clear(){
        //pointer to first point
        Node<T> trav = head;
        while(trav != null){
            //store the next node before deleting the current node
            Node<T> nextNode = trav.next;
            //set the node's pointer and data as null
            trav.next = trav.prev = null;
            trav.data = null;
            trav = nextNode;
        }
        
    }
    
    
    
    public T removeHead(){
        
        if(isEmpty()) throw new RuntimeException("No new node data");
        T data = head.data;
        
        
        head = head.next;
        head.prev = null;
        size--;
        
        if(isEmpty()) tail = null;
        
        return data;
        
        
    }
    
    
    public T removeTail(){
        //if list is empty, then throw an exception
        if(isEmpty()) throw new RuntimeException("cannot remove data from empty list");
        //get the current tail's data
        T data = tail.data;
        //set tail's previous as new tail
        tail = tail.prev;
        tail.next = null;
        size--;
        if(isEmpty()) head = null;
        return data;
        
        
    }
    public T remove(Node<T> node){
        if(isEmpty())  throw new RuntimeException("cannot remove data from empty list");
        
        // suppose we want to remove either head or tail from the list
        if(node.prev == null) return removeHead();
        if(node.next == null) return removeTail();
                
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        T data = node.data;
        //clean up of memory
        node.data = null;
        
        node.prev = node.next = null;
        size--;
        return data; 
        
    }
    public T removeAt(int index){
        
    	int i;
        //if index < size/2 , i.e it is before the mid
        Node<T> trav;
        if(index < 0 || index >= size) throw new RuntimeException("cannot find such index in the list");
        
        if(index < size/2){
            for(i = 0 , trav = head ; i != index;i++){
                trav = trav.next;
            }
        }else{
            for(i = size - 1,trav = tail; i != index ; i--){
                trav = trav.prev;
            }
        }
        
        return remove(trav);
        
        
    }
    public boolean remove(Object obj){
        Node<T> trav = head;
        //support for null data also
        if(obj == null){
            for(trav = head ; trav != null ; trav = trav.next){
                if(trav.data == null){
                    remove(trav);
                    return true;
                }
                
            }
        }else{
            for(trav = head ; trav != null ; trav = trav.next){
                if(obj.equals(trav.data)){
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
        
    }
    
    public int indexOf(Object obj){
        Node<T> trav = head;
        //suppport for null;
        int index;
        if(obj == null){
            for(index = 0,trav = head ; trav != null ; trav = trav.next , index++){
                if((boolean) (trav.data = null)){
                   return index;
                }
            }
        }else{
            for(index = 0,trav = head ; trav != null ; trav = trav.next , index++){
                if(obj.equals(trav)){
                   return index;
                }
            }
            
        }
        return -1;
    }
    
    public boolean contains(Object obj){
        return indexOf(obj) == -1;
    }
    @Override
    public java.util.Iterator<T> iterator() {
      return new java.util.Iterator<T>() {
        private Node<T> trav = head;

        @Override
        public boolean hasNext() {
          return trav != null;
        }

        @Override
        public T next() {
          T data = trav.data;
          trav = trav.next;
          return data;
        }

        @Override
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[ ");
      Node<T> trav = head;
      while (trav != null) {
        sb.append(trav.data);
        if(trav.next != null){
        	sb.append("->");
        }
        trav = trav.next;
      }
      sb.append(" ]");
      return sb.toString();
    }

    
     
    public static void main(String[] args){
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
        dll.insert(10);
        dll.insert(20);
        System.out.println(dll.toString());
        dll.insertTail(30);
        System.out.println(dll.toString());
        
        System.out.println(dll.contains(20));
        System.out.println(dll.peekFirst());
        System.out.println(dll.peekLast());
        System.out.println(dll.removeAt(1));
        
    }
	
    
}
