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

public class Stack<T> {
    Node<T> top=null,temp=null;
    public int size=0;
    T temp_val;
    public void push(T val)
    {
        if (top==null)
        {
            top=new Node<T>(val);
        }
        else
        {
            temp=new Node<T>(val);
            temp.link=top;
            top=temp;
            temp=null;
        }
        size+=1;
    }
    public T pop() throws NoSuchElementException
    {
        if (top==null)
        {
            throw new NoSuchElementException("Stack is Empty: Stack has no elements left");
        }
        else
        {
            size-=1;
            temp_val=top.data;
            top=top.link;
            return temp_val;
        }
    }
    public T peek() throws NoSuchElementException
    {
        if (top==null)
        {
            throw new NoSuchElementException("Stack is Empty: no elements to look for");
        }
        else
        {
            temp_val=top.data;
            return temp_val;
        }
    }
    void resursive_display(Node<T> start)
    {
        if (start==null) return;
        else
        {
            resursive_display(start.link);
            System.out.print(start.data+",");
        }
    }
    public void reverse_display()
    {
        resursive_display(top);
    }
    public void display()
    {
        temp=top;
        while(temp!=null)
        {
            System.out.print(temp.data+",");
            temp=temp.link;
        }
    }
    public static void main(String args[])
    {
        Stack<Integer> obj=new Stack<Integer>();
        obj.push(4);
        obj.push(3);
        obj.push(2);
        obj.push(1);
        obj.pop();
        obj.pop();
        obj.pop();
        obj.pop();
        // System.out.println(obj.peek());
        System.out.println(obj.size);
        System.out.println();
        obj.display();
        System.out.println();
        obj.reverse_display();
    }
}
