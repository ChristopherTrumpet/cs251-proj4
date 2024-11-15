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
    private final int total;

    /**
     * Initializes a disjoint set of size n
     * @param n size of disjoint set
     * @throws IllegalArgumentException if passed an invalid size (<0)
     */
    public DisjointSet(int n) throws IllegalArgumentException {

        if (n < 0) {
            throw new IllegalArgumentException("Negative size >:(");
        }

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
        if (x < 0 || x >= total) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + x);
        }

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

        if (x < 0 || x >= total || y < 0 || y >= total) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        //todo
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return; // both are already in same set

        if (size[rootX] <= size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
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
        if (x < 0 || x >= total) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return size[find(x)];
    }

    /**
     * Returns the size of the disjoint set (total number of elements)
     *
     * @return size of disjoint set
     */
    @Override
    public int getDSSize() {
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
