import data_structure.*;
import java.util.Scanner;

class InfixPostfix {

    public int precedence(char ch)
    {
        if (ch=='-' || ch=='+')
            return 1;
        if (ch=='*' || ch=='/')
            return 2;
        
        if (ch=='^')    // exponent
            return 3;    // exponent
        else    // in case of "("
            return 0;
            
    }
    String char_to_string(char ch)
    {
        return Character.toString(ch);
    }

    public Queue<String> convert(String infix)
    {
        Stack<Character> op = new Stack<Character>();
        Queue<String> postfix = new Queue<String>();
        
        int start=0,end;
        boolean num_found=false;
        char ch;

        for (int i=0;i<infix.length();i++)
        {

            ch=infix.charAt(i);
            
            // System.out.print("\nop stack:");
            // op.reverse_display();    // if you wanna see operator stack
            // System.out.print("\t\tpostfix queue:");
            // postfix.display();      // if you wanna see postfix queue

            // scan number and load them to queue
            if (num_found)
            {
                if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^'||ch=='('||ch==')')
                {
                    if (infix.charAt(i+1)>=48 && infix.charAt(i+1)<=57) // check if is number
                    {
                        start=i+1;
                        num_found=false;
                    }
                }
                else    // if any number is found
                {
                    start=i;
                    num_found=false;
                }
            }
            if(!num_found)
            {
                if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^'||ch=='('||ch==')')
                {
                    if (infix.charAt(i-1)>=48 && infix.charAt(i-1)<=57) // check if is number
                    {
                        end=i;
                        postfix.add(infix.substring(start,end));
                        num_found=true;
                    }
                }
                else if(i==infix.length()-1)    // if any number is found and its the last number of the expression
                {
                    end=i+1;  
                    postfix.add(infix.substring(start,end));
                    num_found=true;
                }
            }

            if (ch=='(')
            {
                op.push(ch);
                continue;
            }
            else if (ch==')')
            {
                while(op.peek()!='(')
                {
                    postfix.add(char_to_string(op.pop()));
                }
                op.pop();   // to remove "(" from operation stack
                continue;
            }
            

            if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^')
            {

                // process operators
                
                if (op.size==0)
                    op.push(ch);
                else if(precedence(ch)>precedence(op.peek()))   // if this operator has more precedence than the operator in stack
                {
                    op.push(ch);
                }
                else if(precedence(ch)==precedence(op.peek()))   // if this operator has same precedence as the operator in stack
                {
                    postfix.add(char_to_string(op.pop()));
                    op.push(ch);
                }
                else    // if this operator has lower precedence than the operator in stack
                {
                    while(precedence(ch)<=precedence(op.peek()))
                    {
                        if (op.peek()=='(') break;
                        postfix.add(char_to_string(op.pop()));
                        
                        if (op.size==0) break;  // don't do anything if no operator is in stack
                    }
                    op.push(ch);
                }

                
            }


        }
        while(op.size!=0)
        {
            postfix.add(char_to_string(op.pop()));
        }
        return postfix;
    }

}

class PostfixEval
{
    String calculate(String a,String b,char op)
    {
        // double num1=Double.parseDouble(a),num2=Double.parseDouble(b),result;
        double num1=Double.parseDouble(b),num2=Double.parseDouble(a),result;
        result=(op=='+')? num1+num2 : ((op=='-')? num1-num2 : (op=='*')? num1*num2 : (op=='/')? num1/num2 : Math.pow(num1,num2));
        return Double.toString(result);
    }
    public float solve(Queue<String> postfix)
    {
        Stack<String> stack1=new Stack<String>();
        char ch;
        int i=0;
        while(postfix.size!=0)
        {
            ch=postfix.peek().charAt(0);
            if (ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='^')
            {
                ch=postfix.remove().charAt(0);
                // System.out.println("ch:"+ch+",i:"+i);
                stack1.push(calculate(stack1.pop(),stack1.pop(),ch));
            }
            else 
                stack1.push(postfix.remove());
            i++;
        }
        return Float.parseFloat(stack1.pop());
    }
}

public class Solver
{
    public static void main(String args[])
    {
        InfixPostfix obj=new InfixPostfix();
        
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Expression:");
        String infix=sc.nextLine();
        Queue<String> postfix=obj.convert(infix);
        
        System.out.print("\nPostfix: ");
        postfix.display();
        System.out.println();
        PostfixEval solver=new PostfixEval();
        float ans=solver.solve(postfix);
        System.out.println("answer:"+ans);
    }
}
