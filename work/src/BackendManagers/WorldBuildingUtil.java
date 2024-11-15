package BackendManagers;

import BackendManagers.Interfaces.WorldBuildingUtilInterface;
import CommonUtils.MST;
import CommonUtils.UsefulContainers.iPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains helpful functions for dealing with our world building. Will not be integrated with
 *   production classes, as it is a tool for helping us with content creation.
 *
 * <bold>251 students: you may use any of the data structures you have previously created, but may not use
 *   any Java util library except List/ArrayList and java.util.stream.* .</bold>
 */
public class WorldBuildingUtil implements WorldBuildingUtilInterface {

    /**
     * Uses equation provided by the wiki: https://en.wikipedia.org/wiki/Euclidean_distance
     * @param p Coordinates of City X
     * @param q Coordinates of City Y
     * @return Distance between both cities
     */
    public double getEuclidDist(iPair p, iPair q) {
        // This is used to create the "weight" between each city
        double x = p.a - q.a;
        double y = p.b - q.b;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Selects roads per the specifications (minimum cost to connect all cities).
     *
     * @param filename file to read input from
     * @return list of roads, per the specifications
     */
    @Override
    public List<CityEdge> getMinimumConnectingRoads(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));

            int n = Integer.parseInt(bf.readLine());
            double[][] weights = new double[n][n];
            List<iPair> cities = new ArrayList<>();

            // Read and store x,y coordinates of each city
            for (int i = 0; i < n; i++) {
                String[] line = bf.readLine().split(" ");
                int xCoord = Integer.parseInt(line[0]);
                int yCoord = Integer.parseInt(line[1]);

                cities.add(new iPair(xCoord, yCoord));
            }

            // Calculate the distance between each city
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    weights[i][j] = getEuclidDist(cities.get(i), cities.get(j));
                }
            }

            // Find the shortest path to each city
            List<iPair> mst = MST.denseMST(weights);
            List<CityEdge> cityEdges = new ArrayList<>();

            if (mst == null) {
                // Shortest path could not be found.
                return null;
            }

            for (iPair cityPair : mst) {
                iPair cityX = cities.get(cityPair.a);
                iPair cityY = cities.get(cityPair.b);
                cityEdges.add(new CityEdge(cityX, cityY));
            }

            return cityEdges;

        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
