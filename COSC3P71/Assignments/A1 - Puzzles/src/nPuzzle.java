
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class nPuzzle {

 
    static final byte [] allTiles = {0,1,2,3,4,5,6,7,8}; // the n puzzle (8 puzzle)

    final PriorityQueue <State> queue = new PriorityQueue<State>(100, new Comparator<State>() { //A* priority queue
        @Override
        public int compare(State a, State b) { 
            return a.priority() - b.priority();
        }
    });

    final HashSet <State> closed = new HashSet <State>();  //closed state

    //state of the puzzle with its priority and chain to start state
    class State {
        final byte [] t; //array of the tiles
        final int i; //index of space needed
        final int s; //number of moves from start
        final int h; //heuristic value, number of steps ,uses brute force Breadth First Traversal when h=0
        //int h = 0;
        final State p; //previous state

        int priority() {  //A* priority function
            return s + h;
        }

        State(byte [] initial) {  //builds an initial state
            t = initial;
            i = index(t, 0);
            s = 0;
            h = heuristic(t);
            p = null;
        }

        State(State prev, int slideFromIndex) { //builds a successor to prev 
            t = Arrays.copyOf(prev.t, prev.t.length);
            t[prev.i] = t[slideFromIndex];
            t[slideFromIndex] = 0;
            i = slideFromIndex;
            s = prev.s + 1;
            h = heuristic(t);
            this.p = prev;
        }

        boolean goalState() { //if goal state then true
            return Arrays.equals(t, allTiles);
        }

        //state of successor moves s, n, w, e
        State south() { return i > 2 ? new State(this, i - 3) : null; }       
        State north() { return i < 6 ? new State(this, i + 3) : null; }       
        State east() { return i % 3 > 0 ? new State(this, i - 1) : null; }       
        State west() { return i % 3 < 2 ? new State(this, i + 1) : null; }

 
        void print() {  //prints the current state
            System.out.println("Priority = " + priority() + "\n# of Steps Left = " + h + "\n# of Tiles Moved = " + s);
            for (int i = 0; i < 9; i += 3)
                System.out.println(t[i] + " " + t[i+1] + " " + t[i+2]);
        }

        
        void printAll() { //prints all the states from the start
            if (p != null) p.printAll();
            System.out.println();
            print();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof State) {
                State other = (State)obj;
                return Arrays.equals(t, other.t);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(t);
        }
    }

    void addSuccessor(State successor) {  //adds successor to the A* queue
        if (successor != null && !closed.contains(successor)) 
            queue.add(successor);
    }


    void solve(byte [] begin) { //solve

        queue.clear();
        closed.clear();

        long start = System.currentTimeMillis(); //time taken 

        queue.add(new State(begin)); //add initial state to queue

        while (!queue.isEmpty()) {
            //gets lowest priority state 
            State state = queue.poll();
            //if at goal return 
            if (state.goalState()) {
                long time = System.currentTimeMillis() - start;
                state.printAll();
                System.out.println("Time Taken(ms) = " + time);
                return;
            }
            //exit
            closed.add(state);
            //adds successors to queue 
            addSuccessor(state.south());
            addSuccessor(state.north());
            addSuccessor(state.west());
            addSuccessor(state.east());
        }
    }

    //returns index of values in array 
    static int index(byte [] a, int val) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == val) return i;
        return -1;
    }

    //returns manhatten distance 
    static int mDistance(int a, int b) {
        return Math.abs(a / 3 - b / 3) + Math.abs(a % 3 - b % 3);
    }

    //A* = use sum of manhatten distance of all node 
    static int heuristic(byte [] tiles) {
        int h = 0;
        for (int i = 0; i < tiles.length; i++)
            if (tiles[i] != 0)
                h += mDistance(i, tiles[i]);
        return h;
    }

    public static void main(String[] args) { //main function can be 

        //1st example
        byte [] a = {0,1,3,4,2,5,7,8,6};

        //2nd example 
        //byte [] a = {1,2,3,0,4,6,7,5,8};
        
        //3rd example
        //byte [] a = {1,2,3,4,0,7,6,8,5};

        new nPuzzle().solve(a);
    }
}