package Structure;

import StructureInterface.SetInterface;

import java.util.HashSet;

public class S_HashSet<E> implements SetInterface<E> {

    public static final int DEFAULT_CAPACITY = 1 << 4;

    public static final float LOAD_FACTOR = 0.75f;

    S_Node<E>[] table;
    int size;

    @SuppressWarnings("unchecked")
    public S_HashSet() {
        table = (S_Node<E>[]) new S_Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private static final int hash(Object key) {
        int hash;
        if(key == null) {
            return 0;
        } else {
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        }
    }

    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    public E add(int hash, E key) {
        int idx = hash % table.length;

        if(table[idx] == null) {
            table[idx] = new S_Node<>(hash, key, null);
        } else {
            S_Node temp = table[idx];
            S_Node prev = null;

            while(temp != null) {
                if(temp.hash == hash && (temp.key == temp.key || temp.key.equals(key))) {
                    return key;
                }

                prev = temp;
                temp = temp.next;
            }

            prev.next = new S_Node<E>(hash, key, null);
        }

        size++;

        if(size > LOAD_FACTOR * table.length) {
            resize();
        }

        return null;
    }

    public void resize() {
        int newCapacity = table.length * 2;

        final S_Node<E>[] newTable = (S_Node<E>[]) new S_Node[newCapacity];

        for(int i=0; i< table.length; i++) {
            S_Node<E> value = table[i];

            if(value == null) continue;

            table[i] = null;

            S_Node<E> nextNode;

            while(value != null) {
                int idx = value.hash % newCapacity;

                if(newTable[idx] != null) {
                    S_Node<E> tail = newTable[idx];

                    while(tail.next != null) {
                        tail = tail.next;
                    }

                    nextNode = value.next;
                    value.next = null;
                    tail.next = value;
                } else {
                    nextNode = value.next;
                    value.next = null;
                    newTable[idx] = value;
                }

                value = nextNode;
            }
        }

        table = null;
        table = newTable;
    }

    @Override
    public boolean remove(Object o) {
        return remove(hash(o), o) != null;
    }

    public Object remove(int hash, Object key) {
        int idx = hash % table.length;

        if(table[idx] == null) return null;

        S_Node<E> value = table[idx];
        S_Node<E> removeNode = null;
        S_Node<E> prev = null;

        while(value != null) {
            if(value.hash == hash && (value.key == key || value.key.equals(key))) {
                removeNode = value;

                if(prev == null) {
                    table[idx] = value.next;
                } else {
                    prev.next = value.next;
                }

                value = null;
                size--;
                break;
            }

            prev = value;
            value = value.next;
        }

        return removeNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int idx = hash(o) % table.length;

        if(table[idx] == null) return false;

        S_Node<E> node = table[idx];

        while(node != null) {
            if(node.key == o || node.key.equals(o)) {
                return true;
            }

            node = node.next;
        }

        return false;
    }

    @Override
    public void clear() {
        if(table != null && size > 0) {
            for(int i=0; i< table.length; i++) {
                table[i] = null;
            }
            size = 0;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof HashSet)) {
            return false;
        }

        S_HashSet<E> oSet;

        try {
            oSet = (S_HashSet<E>) o;

            if(size != oSet.size()) return false;

            for(int i=0; i<oSet.table.length; i++) {
                S_Node<E> oTable = oSet.table[i];

                while(oTable != null) {
                    if(!contains(oTable)) {
                        return false;
                    }

                    oTable = oTable.next;
                }
            }
        } catch (ClassCastException e) {
            return false;
        }

        return true;
    }
}
