package other;

import java.util.*;

public class BTree<K, V> {


    private static final int DEFAULT_T = 2;

    /**
     * B树的根节点
     */
    private Node<K, V> root;
    /**
     * 根据B树的定义，B树的每个非根节点的关键字数n满足(t - 1) <= n <= (2t - 1)
     */
    private int t = DEFAULT_T;
    /**
     * 非根节点中最小的键值数
     */
    private int minKeySize = t - 1;
    /**
     * 非根节点中最大的键值数
     */
    private int maxKeySize = 2 * t - 1;
    /**
     * 键的比较函数对象
     */
    private Comparator<K> kComparator;

    public BTree() {
        root = new Node<K, V>();
        root.setLeaf(true);
    }

    public BTree(int t) {
        this();
        this.t = t;
        minKeySize = t - 1;
        maxKeySize = 2 * t - 1;
    }

    public BTree(Comparator<K> kComparator) {
        root = new Node<K, V>(kComparator);
        root.setLeaf(true);
        this.kComparator = kComparator;
    }

    int compare(K key1, K key2)
    {
        return kComparator == null ? ((Comparable<K>)key1).compareTo(key2) : kComparator.compare(key1, key2);
    }

    public V search(K key)
    {
        return search(root, key);
    }


    private V search(Node<K, V> node, K key)
    {
        Result<V> result = node.searchKey(key);
        if(result.isExist())
            return result.getValue();
        else
        {
            if(node.isLeaf())
                return null;
            else
                search(node.childAt(result.getIndex()), key);

        }
        return null;
    }

    private void splitNode(Node<K, V> parentNode, Node<K, V> childNode, int index)
    {
        assert childNode.size() == maxKeySize;

        Node<K, V> siblingNode = new Node<K, V>(kComparator);
        siblingNode.setLeaf(childNode.isLeaf());
        // 将满子节点中索引为[t, 2t - 2]的(t - 1)个项插入新的节点中
        for(int i = 0; i < minKeySize; ++ i)
            siblingNode.addEntry(childNode.entryAt(t + i));
        // 提取满子节点中的中间项，其索引为(t - 1)
        Entry<K, V> entry = childNode.entryAt(t - 1);
        // 删除满子节点中索引为[t - 1, 2t - 2]的t个项
        for(int i = maxKeySize - 1; i >= t - 1; -- i)
            childNode.removeEntry(i);
        if(!childNode.isLeaf()) // 如果满子节点不是叶节点，则还需要处理其子节点
        {
            // 将满子节点中索引为[t, 2t - 1]的t个子节点插入新的节点中
            for(int i = 0; i < minKeySize + 1; ++ i)
                siblingNode.addChild(childNode.childAt(t + i));
            // 删除满子节点中索引为[t, 2t - 1]的t个子节点
            for(int i = maxKeySize; i >= t; -- i)
                childNode.removeChild(i);
        }
        // 将entry插入父节点
        parentNode.insertEntry(entry, index);
        // 将新节点插入父节点
        parentNode.insertChild(siblingNode, index + 1);
    }


    private boolean insertNotFull(Node<K, V> node, Entry<K, V> entry)
    {
        assert node.size() < maxKeySize;

        if(node.isLeaf()) // 如果是叶子节点，直接插入
            return node.insertEntry(entry);
        else
        {
            /* 找到entry在给定节点应该插入的位置，那么entry应该插入
             * 该位置对应的子树中
             */
            Result<V> result = node.searchKey(entry.getKey());
            // 如果存在，则直接返回失败
            if(result.isExist())
                return false;
            Node<K, V> childNode = node.childAt(result.getIndex());
            if(childNode.size() == 2*t - 1) // 如果子节点是满节点
            {
                // 则先分裂
                splitNode(node, childNode, result.getIndex());
                /* 如果给定entry的键大于分裂之后新生成项的键，则需要插入该新项的右边，
                 * 否则左边。
                 */
                if(compare(entry.getKey(), node.entryAt(result.getIndex()).getKey()) > 0)
                    childNode = node.childAt(result.getIndex() + 1);
            }
            return insertNotFull(childNode, entry);
        }
    }

    public boolean insert(K key, V value)
    {
        if(root.size() == maxKeySize) // 如果根节点满了，则B树长高
        {
            Node<K, V> newRoot = new Node<K, V>(kComparator);
            newRoot.setLeaf(false);
            newRoot.addChild(root);
            splitNode(newRoot, root, 0);
            root = newRoot;
        }
        return insertNotFull(root, new Entry<K, V>(key, value));
    }

    private V putNotFull(Node<K, V> node, Entry<K, V> entry)
    {
        assert node.size() < maxKeySize;

        if(node.isLeaf()) // 如果是叶子节点，直接插入
            return node.putEntry(entry);
        else
        {
            /* 找到entry在给定节点应该插入的位置，那么entry应该插入
             * 该位置对应的子树中
             */
            Result<V> result = node.searchKey(entry.getKey());
            // 如果存在，则更新
            if(result.isExist())
                return node.putEntry(entry);
            Node<K, V> childNode = node.childAt(result.getIndex());
            if(childNode.size() == 2*t - 1) // 如果子节点是满节点
            {
                // 则先分裂
                splitNode(node, childNode, result.getIndex());
                /* 如果给定entry的键大于分裂之后新生成项的键，则需要插入该新项的右边，
                 * 否则左边。
                 */
                if(compare(entry.getKey(), node.entryAt(result.getIndex()).getKey()) > 0)
                    childNode = node.childAt(result.getIndex() + 1);
            }
            return putNotFull(childNode, entry);
        }
    }


    /**
     * 如果B树中存在给定的键，则更新值。
     * 否则插入。
     *
     * @param key - 键
     * @param value - 值
     * @return 如果B树中存在给定的键，则返回之前的值，否则null
     */
    public V put(K key, V value)
    {
        if(root.size() == maxKeySize) // 如果根节点满了，则B树长高
        {
            Node<K, V> newRoot = new Node<K, V>(kComparator);
            newRoot.setLeaf(false);
            newRoot.addChild(root);
            splitNode(newRoot, root, 0);
            root = newRoot;
        }
        return putNotFull(root, new Entry<K, V>(key, value));
    }

    /**
     * 从B树中删除一个与给定键关联的项。
     *
     * @param key - 给定的键
     * @return 如果B树中存在给定键关联的项，则返回删除的项，否则null
     */
    public Entry<K, V> delete(K key)
    {
        return delete(root, key);
    }

    /**
     * 从以给定<code>node</code>为根的子树中删除与给定键关联的项。
     * <p/>
     * 删除的实现思想请参考《算法导论》第二版的第18章。
     *
     * @param node - 给定的节点
     * @param key - 给定的键
     * @return 如果B树中存在给定键关联的项，则返回删除的项，否则null
     */
    private Entry<K, V> delete(Node<K, V> node, K key)
    {
        // 该过程需要保证，对非根节点执行删除操作时，其关键字个数至少为t。
        assert node.size() >= t || node == root;

        Result<V> result = node.searchKey(key);
        /*
         * 因为这是查找成功的情况，0 <= result.getIndex() <= (node.size() - 1)，
         * 因此(result.getIndex() + 1)不会溢出。
         */
        if(result.isExist())
        {
            // 1.如果关键字在节点node中，并且是叶节点，则直接删除。
            if(node.isLeaf())
                return node.removeEntry(result.getIndex());
            else
            {
                // 2.a 如果节点node中前于key的子节点包含至少t个项
                Node<K, V> leftChildNode = node.childAt(result.getIndex());
                if(leftChildNode.size() >= t)
                {
                    // 使用leftChildNode中的最后一个项代替node中需要删除的项
                    node.removeEntry(result.getIndex());
                    node.insertEntry(leftChildNode.entryAt(leftChildNode.size() - 1), result.getIndex());
                    // 递归删除左子节点中的最后一个项
                    return delete(leftChildNode, leftChildNode.entryAt(leftChildNode.size() - 1).getKey());
                }
                else
                {
                    // 2.b 如果节点node中后于key的子节点包含至少t个关键字
                    Node<K, V> rightChildNode = node.childAt(result.getIndex() + 1);
                    if(rightChildNode.size() >= t)
                    {
                        // 使用rightChildNode中的第一个项代替node中需要删除的项
                        node.removeEntry(result.getIndex());
                        node.insertEntry(rightChildNode.entryAt(0), result.getIndex());
                        // 递归删除右子节点中的第一个项
                        return delete(rightChildNode, rightChildNode.entryAt(0).getKey());
                    }
                    else // 2.c 前于key和后于key的子节点都只包含t-1个项
                    {
                        Entry<K, V> deletedEntry = node.removeEntry(result.getIndex());
                        node.removeChild(result.getIndex() + 1);
                        // 将node中与key关联的项和rightChildNode中的项合并进leftChildNode
                        leftChildNode.addEntry(deletedEntry);
                        for(int i = 0; i < rightChildNode.size(); ++ i)
                            leftChildNode.addEntry(rightChildNode.entryAt(i));
                        // 将rightChildNode中的子节点合并进leftChildNode，如果有的话
                        if(!rightChildNode.isLeaf())
                        {
                            for(int i = 0; i <= rightChildNode.size(); ++ i)
                                leftChildNode.addChild(rightChildNode.childAt(i));
                        }
                        return delete(leftChildNode, key);
                    }
                }
            }
        }
        else
        {
            /*
             * 因为这是查找失败的情况，0 <= result.getIndex() <= node.size()，
             * 因此(result.getIndex() + 1)会溢出。
             */
            if(node.isLeaf()) // 如果关键字不在节点node中，并且是叶节点，则什么都不做，因为该关键字不在该B树中
            {
                return null;
            }
            Node<K, V> childNode = node.childAt(result.getIndex());
            if(childNode.size() >= t) // // 如果子节点有不少于t个项，则递归删除
                return delete(childNode, key);
            else // 3
            {
                // 先查找右边的兄弟节点
                Node<K, V> siblingNode = null;
                int siblingIndex = -1;
                if(result.getIndex() < node.size()) // 存在右兄弟节点
                {
                    if(node.childAt(result.getIndex() + 1).size() >= t)
                    {
                        siblingNode = node.childAt(result.getIndex() + 1);
                        siblingIndex = result.getIndex() + 1;
                    }
                }
                // 如果右边的兄弟节点不符合条件，则试试左边的兄弟节点
                if(siblingNode == null)
                {
                    if(result.getIndex() > 0) // 存在左兄弟节点
                    {
                        if(node.childAt(result.getIndex() - 1).size() >= t)
                        {
                            siblingNode = node.childAt(result.getIndex() - 1);
                            siblingIndex = result.getIndex() - 1;
                        }
                    }
                }
                // 3.a 有一个相邻兄弟节点至少包含t个项
                if(siblingNode != null)
                {
                    if(siblingIndex < result.getIndex()) // 左兄弟节点满足条件
                    {
                        childNode.insertEntry(node.entryAt(siblingIndex), 0);
                        node.removeEntry(siblingIndex);
                        node.insertEntry(siblingNode.entryAt(siblingNode.size() - 1), siblingIndex);
                        siblingNode.removeEntry(siblingNode.size() - 1);
                        // 将左兄弟节点的最后一个孩子移到childNode
                        if(!siblingNode.isLeaf())
                        {
                            childNode.insertChild(siblingNode.childAt(siblingNode.size()), 0);
                            siblingNode.removeChild(siblingNode.size());
                        }
                    }
                    else // 右兄弟节点满足条件
                    {
                        childNode.insertEntry(node.entryAt(result.getIndex()), childNode.size() - 1);
                        node.removeEntry(result.getIndex());
                        node.insertEntry(siblingNode.entryAt(0), result.getIndex());
                        siblingNode.removeEntry(0);
                        // 将右兄弟节点的第一个孩子移到childNode
                        // childNode.insertChild(siblingNode.childAt(0), childNode.size() + 1);
                        if(!siblingNode.isLeaf())
                        {
                            childNode.addChild(siblingNode.childAt(0));
                            siblingNode.removeChild(0);
                        }
                    }
                    return delete(childNode, key);
                }
                else // 3.b 如果其相邻左右节点都包含t-1个项
                {
                    if(result.getIndex() < node.size()) // 存在右兄弟，直接在后面追加
                    {
                        Node<K, V> rightSiblingNode = node.childAt(result.getIndex() + 1);
                        childNode.addEntry(node.entryAt(result.getIndex()));
                        node.removeEntry(result.getIndex());
                        node.removeChild(result.getIndex() + 1);
                        for(int i = 0; i < rightSiblingNode.size(); ++ i)
                            childNode.addEntry(rightSiblingNode.entryAt(i));
                        if(!rightSiblingNode.isLeaf())
                        {
                            for(int i = 0; i <= rightSiblingNode.size(); ++ i)
                                childNode.addChild(rightSiblingNode.childAt(i));
                        }
                    }
                    else // 存在左节点，在前面插入
                    {
                        Node<K, V> leftSiblingNode = node.childAt(result.getIndex() - 1);
                        childNode.insertEntry(node.entryAt(result.getIndex() - 1), 0);
                        node.removeEntry(result.getIndex() - 1);
                        node.removeChild(result.getIndex() - 1);
                        for(int i = leftSiblingNode.size() - 1; i >= 0; -- i)
                            childNode.insertEntry(leftSiblingNode.entryAt(i), 0);
                        if(!leftSiblingNode.isLeaf())
                        {
                            for(int i = leftSiblingNode.size(); i >= 0; -- i)
                                childNode.insertChild(leftSiblingNode.childAt(i), 0);
                        }
                    }
                    // 如果node是root并且node不包含任何项了
                    if(node == root && node.size() == 0)
                        root = childNode;
                    return delete(childNode, key);
                }
            }
        }
    }

    /**
     * 一个简单的层次遍历B树实现，用于输出B树。
     */
    public void output()
    {
        
        Queue<Node<K, V>> queue = new LinkedList<Node<K, V>>();
        queue.offer(root);
        while(!queue.isEmpty())
        {
            Node<K, V> node = queue.poll();
            for(int i = 0; i < node.size(); ++ i)
                System.out.print(node.entryAt(i) + " ");
            System.out.println();
            if(!node.isLeaf())
            {
                for(int i = 0; i <= node.size(); ++ i)
                    queue.offer(node.childAt(i));
            }
        }
    }

    /////////////////////////////inline class
    private static class Node<K, V> {
        private List<Entry<K, V>> keys;

        private List<Node<K, V>> children;

        private boolean leaf;

        private Comparator<K> comparator;

        private Node() {
            keys = new ArrayList<Entry<K, V>>();
            children = new ArrayList<Node<K, V>>();
            leaf = false;
        }

        public Node(Comparator<K> kComparator) {
            this();
            this.comparator = kComparator;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public int size() {
            return keys.size();
        }

        int compare(K key1, K key2) {
            return comparator == null ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
        }


        public Result<V> searchKey(K key) {
            int low = 0;
            int high = size() - 1;
            int mid = 0;
            while (low <= high) {
                mid = (low + high) >> 1;
                Entry<K, V> entry = keys.get(mid);
                if (compare(entry.getKey(), key) == 0) break;
                else if (compare(entry.getKey(), key) > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            boolean result = false;
            int index = 0;
            V value = null;
            if (low <= high) {
                result = true;
                index = mid;
                value = keys.get(index).getValue();
            } else {
                result = false;
                index = low;
            }
            return new Result<V>(result, index, value);
        }


        public void addEntry(Entry<K, V> entry) {
            keys.add(entry);
        }

        public Entry<K, V> entryAt(int index) {
            return keys.get(index);
        }

        public Entry<K, V> removeEntry(int index) {
            return keys.remove(index);
        }

        public V putEntry(Entry<K, V> entry) {
            Result<V> result = searchKey(entry.getKey());
            if (result.isExist()) {
                V oldValue = keys.get(result.getIndex()).getValue();
                keys.get(result.getIndex()).setValue(entry.getValue());
                return oldValue;
            } else {
                insertEntry(entry, result.getIndex());
                return null;
            }
        }

        public boolean insertEntry(Entry<K, V> entry) {
            Result<V> result = searchKey(entry.getKey());
            if (result.isExist())
                return false;
            else {
                insertEntry(entry, result.getIndex());
                return true;
            }
        }

        public void insertEntry(Entry<K, V> entry, int index) {

            List<Entry<K, V>> newEntrys = new ArrayList<Entry<K, V>>();
            int i = 0;
            for (; i < index; ++i)
                newEntrys.add(keys.get(i));
            newEntrys.add(entry);
            for (; i < keys.size(); ++i)
                newEntrys.add(keys.get(i));
            keys.clear();
            keys = newEntrys;
        }

        public Node<K, V> childAt(int index) {
            if (isLeaf())
                throw new UnsupportedOperationException("Leaf node doesn't have children.");
            return children.get(index);
        }

        public void addChild(Node<K, V> child) {
            children.add(child);
        }

        public void removeChild(int index) {
            children.remove(index);
        }

        public void insertChild(Node<K, V> child, int index) {
            List<Node<K, V>> newChildren = new ArrayList<Node<K, V>>();
            int i = 0;
            for (; i < index; ++i)
                newChildren.add(children.get(i));
            newChildren.add(child);
            for (; i < children.size(); ++i)
                newChildren.add(children.get(i));
            children = newChildren;
        }

    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public String toString() {
            return key + ":" + value;
        }
    }

    private static class Result<V> {
        private boolean exist;
        private int index;
        private V value;

        public Result(boolean exist, int index) {
            this.exist = exist;
            this.index = index;
        }

        public Result(boolean exist, int index, V value) {
            this(exist, index);
            this.value = value;
        }

        public boolean isExist() {
            return exist;
        }

        public int getIndex() {
            return index;
        }

        public V getValue() {
            return value;
        }

    }
}
