package CommonUtils;

import CommonUtils.UsefulContainers.Edge;
import CommonUtils.UsefulContainers.iPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing Minimum Spanning Tree (MST) utils.  No interface provided because functions are static.
 *
 * <bold>251 students: You may only use java.util.List and java.util.ArrayList from the standard library.
 *   Any other containers used must be ones you created.</bold>
 */
public class MST {

    // track visited nodes
    static boolean[] visited;

    static MinHeap<Edge> heap;

    static void addEdges(int nodeIndex, double[][] weights) {
        visited[nodeIndex] = true;

        int edges = weights[nodeIndex].length;
        for (int i = 0; i < edges; i++) {
            if (!visited[i]) {
                heap.add(new Edge(nodeIndex, i, weights[nodeIndex][i]));
            }
        }
    }

    /**
     * Returns the MST of the given graph, optimized for a dense graph.  Assumes a connected graph.
     *
     * @param weights square matrix representing positive edge weights between every vertex
     * @return MST: list of pairs of indices each indicating an edge between those two indices
     * @throws IllegalArgumentException if weights is not square or edges are not positive
     */
    public static List<iPair> denseMST(double[][] weights) throws IllegalArgumentException {
        //validate weighs matrix (already done)
        int n = weights.length;

        int m = n - 1;
        int edgeCount = 0;
        heap = new MinHeap<>();

        List<iPair> mstEdges = new ArrayList<>();

        // track visited nodes
        visited = new boolean[n];

        for(int i=0; i<n; i++){
            if(weights[i].length != n)
                throw new IllegalArgumentException("Weights graph not square in row " +
                        i + ", expected " + n + ", actual is " + weights[i].length);
            for(int j=0; j<n; j++){
                if(weights[i][j] < 0)
                    throw new IllegalArgumentException("Edge weight < 0 (" +
                            weights[i][j] + ") at y, x=" + i + ", " + j);
            }
        }



        addEdges(0, weights);

        while (heap.size() > 0 && edgeCount != m) {
            Edge edge = heap.removeMin();
            int nodeIndex = edge.b;

            if (visited[nodeIndex]) continue;

            // Path between city X and city Y
            mstEdges.add(new iPair(edge.a, edge.b));
            edgeCount++;

            addEdges(nodeIndex, weights);
        }

        return (edgeCount != m) ? null : mstEdges;
    }

    /**
     * Returns the MST of the given graph, optimized for a sparse graph.  Assumes a connected graph.
     *
     * @param edgeList edge list
     * @param n number of vertices
     * @return MST: list of pairs of indices each indicating an edge between those two indices
     * @throws IllegalArgumentException if edges are not positive
     */
    public static List<iPair> sparseMST(List<Edge> edgeList, int n) throws IllegalArgumentException {
        //validate edge weighs (already done)
        for(var e : edgeList){
            if(e.w < 0)
                throw new IllegalArgumentException("Edge weight < 0 (" +
                        e.w + ") between " + e.a + " and " + e.b);
        }

        MinHeap<Edge> minHeap = new MinHeap<>();
        for (Edge e : edgeList) {
            minHeap.add(new Edge(e.a, e.b, e.w));
        }

        List<iPair> mst = new ArrayList<>();
        DisjointSet ds = new DisjointSet(n);

        while (minHeap.size() > 0 && mst.size() < n - 1) {
            Edge edge = minHeap.removeMin();

            if (ds.find(edge.a) != ds.find(edge.b)) {
                mst.add(new iPair(edge.a, edge.b));
                ds.union(edge.a, edge.b);
            }
        }

        return mst;
    }
}
