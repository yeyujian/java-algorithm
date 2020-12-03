package test3;

/**
 * （2）分别用循环单链表、循环双链表结构设计队列，并讨论他们之间的差别。
 * @param <T>
 */
public interface Queue<T> {

    public abstract int size();                  //返回队列长度
    public abstract boolean isEmpty();           //判断队列是否空

    public abstract boolean add(T x);            //元素x入队

    public abstract T peek();                    //返回队头元素

    public abstract T poll();                    //出队，返回队头元素。
}
