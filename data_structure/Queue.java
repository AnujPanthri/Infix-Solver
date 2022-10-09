package data_structure;
import java.util.NoSuchElementException;


//generic class 
class Node<T>
{
    T data;
    Node<T> link=null;
    
    public Node(T val)
    {
        data=val;
    }
}

public class Queue<T>
{
    // reverse links front -> rear
    Node<T> front=null,rear=null,temp=null;
    public int size=0;
    T temp_val;
    public void add(T val)
    {
        if (front==null)
            this.rear=this.front=new Node<T>(val);
        else{
            temp=new Node<T>(val);
            rear.link=temp; // reverse linking from front to rear
            rear=temp;
        }
        size+=1;
    }
    public T remove() throws NoSuchElementException
    {
        if (front==null)
            throw new NoSuchElementException("Queue is Empty: queue has no elements left");
        else{
            size-=1;
            temp_val=front.data;
            front=front.link;
            return temp_val;
        }
    }
    public T peek() throws NoSuchElementException
    {
        if (front==null)
            throw new NoSuchElementException("Queue is Empty: no elements to look for");
        else{
            return front.data;
        }
    }
    void recursive_display(Node<T> start)
    {
        if (start==null)    return;
        recursive_display(start.link);
        System.out.print(start.data+",");
    }
    public void reverse_display()
    {
        temp=front;
        recursive_display(temp);
    }
    public void display()
    {
        temp=front;
        while(temp!=null)
        {
            System.out.print(temp.data+",");
            temp=temp.link;
        }

    }


    public static void main(String args[])
    {
        Queue<String> obj=new Queue<String>();
        obj.add("anuj");
        obj.add("hello");
        // obj.remove();
        // obj.remove();
        
        System.out.println("size:"+obj.size);
        obj.display();
        System.out.println();
        obj.reverse_display();
        System.out.println("\nsize:"+obj.size);
    }
}