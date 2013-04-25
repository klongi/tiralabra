package tiralabra.datastructures;

/**
 * BitQueue that stores boolean values (bits).
 * @author Krista
 */
public class BitQueue {

    private boolean[] table;
    private int head;
    private int tail;
    private int size;
    /**
     * The maximum size needed for the queue by packer and unpacker is 512 or less.
     */
    private static final int MAXSIZE = 512;

    public BitQueue() {
        head = 0;
        tail = 0;
        size = 0;
        table = new boolean[MAXSIZE];
    }

    public boolean isEmpty() {
        return head == tail;
    }


    public void add(boolean b) {
        if (size < MAXSIZE) {
            table[tail] = b;
            tail++;
            size++;
            if (tail == MAXSIZE) {
                tail = 0;
            }
        }
    }

    public boolean remove() {
        boolean b = table[head];
        head++;
        size--;
        if (head == MAXSIZE) {
            head = 0;
        }
        return b;
    }
    
    public boolean removeLast() {
        boolean b = table[tail];
        tail--;
        size--;
        if (tail == -1) {
            head = MAXSIZE-1;
        }
        return b;
    }
    
    public int getSize() {
        return size;
    }
}
