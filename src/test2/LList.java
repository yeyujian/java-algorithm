package test2;

public interface LList<T> {

    boolean isEmpty();                           //判断线性表是否为空，若空返回true

    int size();                                  //返回线性表元素个数（长度）

    T get(int i);                                //返回第i个元素

    void set(int i, T x);                        //设置第i个元素为x

    String toString();                           //返回线性表所有元素的描述字符串

    //int insert(int i, T x);                      //插入x作为第i个元素，x!=null

    int insert(T x);                             //在线性表最后插入x元素，返回x序号

    T remove(int i);                             //删除第i个元素，返回被删除元素

    void clear();                                //删除线性表所有元素

    int search(T key);                           //查找首次出现的与key相等元素，返回元素序号i

    boolean contains(T key);                     //判断是否包含关键字为key元素

    T remove(T key);                             //删除首次出现的与key相等元素，返回被删除元素

    boolean equals(Object obj);                  //比较两个线性表所有元素是否对应相等

}
