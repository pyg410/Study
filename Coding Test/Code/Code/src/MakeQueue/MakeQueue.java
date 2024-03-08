package MakeQueue;

import java.util.Stack;

public class MakeQueue<T> {

    private final Stack<T> oldStack;
    private final Stack<T> newStack;

    public MakeQueue(){
        this.oldStack = new Stack<>();
        this.newStack = new Stack<>();
    }

    public synchronized void enqueue(T a){
        oldStack.push(a);
    }

    public synchronized T dequeue(){
        T result = null;

        if(newStack.isEmpty()){
            while(!oldStack.isEmpty()){
                newStack.push(oldStack.pop());
            }
            result = newStack.isEmpty() ? null : newStack.pop();
        }

        if(!newStack.isEmpty()){
            while(!newStack.isEmpty()){
                oldStack.push(newStack.pop());
            }
        }

        return result;
    }
}
