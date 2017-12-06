import java.util.Vector;
import java.util.Iterator;
public class VectorStack<T> implements StackInterface<T> {
  private static final int DEFAULT_SIZE = 10;
  private Vector<T> stack;

  public VectorStack() { this(DEFAULT_SIZE); }
  
  public VectorStack(int size) {
    stack = new Vector(size);
  }

  public Iterator<T> getIterator() {
    return stack.iterator();
  }
  
  public void push(T newEntry){
    stack.add(newEntry);
  }
  
  public T pop(){
    int length = stack.size();
    if(length > 0) {
      T temp = stack.get(length-1);
      stack.remove(length-1);
      return temp;
    } else {
      throw new Error("tried to pop() an empty stack!");
    }
  }
  
  public T peek(){
    int length = stack.size();
    if(length > 0) {
      return stack.get(length-1);
    } else {
      throw new Error("tried to peek() an empty stack!");
    }
  }
  
  public boolean isEmpty(){
    return stack.isEmpty();
  }
  
  public void clear(){
    stack.clear();
  }
}