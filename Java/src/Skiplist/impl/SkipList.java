

public class SkipList<T> {



    private class Node {
        private T data;
        private int numLevels;


        private Node(T dataIn) {
            data = dataIn;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private static final int MEL = 4;       // maximum enforced level for any given node
    private Node head;
    private Node[] nexts;

    private double probability;

    public SkipList(double probIn) {
        head = null;
        probability = probIn;
    }

    public SkipList() {
        this(0.5f);
    }

    @Override
    public String toString() {
        return null;
    }

}