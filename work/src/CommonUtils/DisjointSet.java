package CommonUtils;

import CommonUtils.Interfaces.DisjointSetInterface;

import java.awt.*;

/**
 * Implements our DisjointSetInterface and adds a constructor
 *
 * @apiNote uses union by size so that more queries hit closer to the top of the tree (even if
 *   a few queries are made longer because of it -- the majority will hit closer to the top).
 */
public class DisjointSet implements DisjointSetInterface {

    private final int[] parent;
    private final int[] size;
    private int total;

    /**
     * Initializes a disjoint set of size n
     * @param n size of disjoint set
     * @throws IllegalArgumentException if passed an invalid size (<0)
     */
    public DisjointSet(int n) throws IllegalArgumentException {
        parent = new int[n];
        size = new int[n];
        total = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Finds the root of x.
     * <p>
     * <bold>251 students: it is recommended to use path compression (i.e. setting the parent of
     *   every node on the path from x to root(x) to be root(x))</bold>
     *
     * @param x node to find the root of
     * @return root of x
     * @throws IndexOutOfBoundsException if x is out of bounds
     */
    @Override
    public int find(int x) throws IndexOutOfBoundsException {

        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    /**
     * Unions the two sets containing x and y.  Joins the smaller size to the larger size,
     *   or if they are the same size, joins x to y (makes y the parent of x, etc.).  Does
     *   not change information about any node except possibly the roots of each set.
     *
     * @param x node in set 1
     * @param y node in set 2
     * @throws IndexOutOfBoundsException if x or y are out of bounds
     */
    @Override
    public void union(int x, int y) throws IndexOutOfBoundsException {
        //todo
        int p1 = find(x);
        int p2 = find(y);

        if (p1 == p2) return; // both are already in same set

        if (size[p1] < size[p2]) {
            parent[p1] = p2;
            size[p2] += size[p1];
            total += size[p2];
            return;
        }

        parent[p2] = p1;
        size[p1] += size[p2];
        total += size[p1];
    }

    /**
     * Returns the size of the set that node x is contained in
     *
     * @param x node to identify the desired set
     * @return size of the set containing x
     * @throws IndexOutOfBoundsException if x is out of bounds
     */
    @Override
    public int getSetSize(int x) throws IndexOutOfBoundsException {
        //todo
        return size[find(x)];
    }

    /**
     * Returns the size of the disjoint set (total number of elements)
     *
     * @return size of disjoint set
     */
    @Override
    public int getDSSize() {
        //todo
        return total;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the disjoint set how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void visualize(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: visualization is to be time-based -- how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
