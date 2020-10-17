public class DoublyLinkedList<T> implements Iterator<T>{
    
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;
    
    private static class Node<T>{
        private T data;
        
        private Node<T> prev,next;
        
        public Node(T data,Node<T> prev,Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        
        @Override String toString(){
            return this.data.toString();
        }
    }

    public void clear(){
        Node<T> temp = head;
        while(temp != null){
            //setting all the node prev, next as null
            Node<T> nextNode = temp.next;
            temp.data = null;
            temp.prev = null;
            temp.next = null;
            temp = nextNode;
        }
    }
    
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size() == 0;
    }
    public void add(T elem){
        
        addLast(elem);
        
    }
    public void addLast(T elem){
        
        if(isEmpty()){
            head = tail = new Node<T>(elem,null,null);
        }else{
            tail.next = newNode<T>(elem,tail,null);
        }
        
    }
    public void addFirst(T elem){
        if(isEmpty()){
            head = tail = new Node<T>(elem,null,null);
            
        }
        else{
            head.prev = new Node<T>(elem,null,head);
            head = head.prev;
        }
        size++;
    }
    
    public T peekFirst(){
      //cannot return from empty list    
      if(isEmpty()) throw new RuntimeException("Empty List");
      
      return head.data;
      
    }
    
    public T peekLast(){
        //cant return from empty list
        if(isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }
    
    public T removeFirst(){
        //cant remove from empty list
        if(isEmpty()) throw new RuntimeException("Empty list cannot remove");
        
        T data = head.data;
        
        //setting next of head as new head
        head = head.next;
        head.prev = null;
        size--;
        
        //setting tail as null , if list is empty
        if(isEmpty()) tail = null;
        
        return data;
        
    }
    public T removeLast(){
        //if list is empty , return exception
        if(isEmpty) throw new RuntimeException("Empty list cannot remove");
        
        T data = tail.data;
        
        tail = tail.prev;
        size--;
        if(isEmpty()) head = null;
        else tail.next = null;
        return data;
        
    }
    public T remove(Node<T> node){
        
        //if node is either head || tail
        if(node.prev == null) return removeFirst();
        if(node.next == null) return removeLast();
        
        //make pointers point to right index when removed
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        T data = node.data;
        
        //memory clean up
        node.next = null;
        node.prev = null;
        node.data = null;
        
        size--;
        
        return data;
        
    }
    public T removeAt(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("index specified for removal does not exist");
        }
        
        Node<T> trav;
        
        //if node is in the beginning few
        if(index < size/2){
            
        for(int i = 0 , trav = head , i != index ; i++){
            trav =  trav.next;
        }
        }else{
            
            
        //if node is in the ending half
        for(int i = size - 1 , trav = tail ; i != index ;i--){
            trav = trav.prev;
        }
            
        }
        
        
        remove(trav);
    }
    public boolean remove(Object obj){
        
        Node<T> trav = head;
        
        //support for null data
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
        
        int index = 0;
        Node<T> trav = head;
        
        //support for null object
        if(obj == null){
            
            for(; trav != null ; trav = trav.next , index++){
            if(trav.data == null){
                return index;
            }
        }
            
        }
        else{
            for(;trav != null ; trav = trav.next,index++){
                if(obj.equals(trav)){
                    return index;
                }
            }
        }
        return -1;
        
    }
    public boolean contains(Object obj){
        
        return indexOf(obj) != -1;
        
    }
    
    @Override
    public java.util.Iterator<T>() iterator{
        return new java.util.Iterator<T>(){
            
            private Node<T> trav = head;
            
            @Override
            public boolean hasNext(){
                return trav == null;
            }
            
            @Override
            public T next(){
                T data = trav.data;
                trav = trav.next;
                return data;
            }
            
            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
            
            @Override
            public String toString(){
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                Node<T> trav = head;
                while(trav != null){
                    sb.append(trav.data + " , ");
                    trav = trav.next;
                }
                sb.append("]");
                return sb.toString();
            }
            
        }
    }
    
}
