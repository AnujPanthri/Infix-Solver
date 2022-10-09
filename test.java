import data_structure.*;
public class test {
    public static void main(String args[])
    {
        // Queue<String> obj=new Queue<String>();
        // obj.add("anuj");
        // obj.add("hello");
        // obj.remove();
        // obj.remove();
        
        Stack<Object> obj=new Stack<Object>();
        obj.push(4);
        obj.push("anuj");
        obj.push(2.456);
        obj.push(1);
        // Stack<Integer> obj=new Stack<Integer>();
        // obj.push(4);
        // obj.push(3);
        // obj.push(2);
        // obj.push(1);
        // obj.pop();
        // obj.pop();
        
        System.out.println("size:"+obj.size);
        obj.display();
        System.out.println();
        obj.reverse_display();
        System.out.println("\nsize:"+obj.size);
    }
    
}
