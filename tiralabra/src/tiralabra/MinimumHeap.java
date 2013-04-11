package tiralabra;

/**
 * Binary heap implementation with an array
 *
 * @author Krista
 */
public class MinimumHeap {

    private int heapSize;
    private Node[] heap;
    /**
     * The maximum number of nodes that are added to the heap is 256.
     *
     * Only as much nodes as there are different characters in the file to be
     * packed are added to the heap.
     */
    private final int HEAP_MAXSIZE = 256;

    public MinimumHeap() {
        this.heap = new Node[HEAP_MAXSIZE];
        this.heapSize = 0;
    }

    /**
     * Inserts the given node in to the heap
     *
     * @param node
     */
    public void insert(Node node) {
        heapSize++;
        int i = heapSize - 1;
        while (i > 0 && heap[getParent(i)].compareTo(node) > 0) {
            heap[i] = heap[getParent(i)];
            i = getParent(i);
        }
        heap[i] = node;
    }

    /**
     * Removes and returns the smallest node from the heap
     *
     * @return smallest node
     */
    public Node deleteMinimum() {
        Node min = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;
        heapify(0);
        return min;
    }

    /**
     * Fixes the heap property if it is broken for the given index
     *
     * @param index
     */
    private void heapify(int index) {
        int left = getLeft(index);
        int right = getRight(index);
        int smallest = index;
        if (heapSize > left && heap[left].compareTo(heap[index]) < 0) {
            smallest = left;
        }
        if (heapSize > right && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != index) {
            switchTwo(index, smallest);
            heapify(smallest);
        }
    }

    /**
     * Gives the index for the left child of the parent node.
     *
     * @param index of the parent node
     * @return index of the left child
     */
    private int getLeft(int index) {
        return 2 * index;
    }

    /**
     * Gives the index for the right child of the parent node.
     *
     * @param index of the parent node
     * @return index of the right child
     */
    private int getRight(int index) {
        return 2 * index + 1;
    }

    /**
     * Returns the index of the parent of the node in the given index
     *
     * @param index
     * @return index of parent node
     */
    private int getParent(int index) {
        return index / 2;
    }

    /**
     * Switches the content of the array in the given indexes
     *
     * @param index1
     * @param index2
     */
    private void switchTwo(int index1, int index2) {
        Node temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * Method tells whether the heap is empty or not
     *
     * @return true if heap is empty
     */
    public boolean isEmpty() {
        if (this.heapSize == 0) {
            return true;
        } else {
            return false;
        }
    }
}
