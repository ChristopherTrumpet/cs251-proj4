package BackendManagers;

import BackendManagers.Interfaces.ServerConnectionsUtilInterface;
import CommonUtils.MST;
import CommonUtils.UsefulContainers.Edge;
import CommonUtils.UsefulContainers.iPair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains helpful functions for dealing with our server connections. Will not be integrated with
 *   production classes, as it is a tool for helping us with backend setup.
 *
 * <bold>251 students: you may use any of the data structures you have previously created, but may not use
 *   any Java util library except List/ArrayList and java.util.stream.* .</bold>
 */
public class ServerConnectionsUtil implements ServerConnectionsUtilInterface {
    /**
     * Selects server connections per the specifications (minimum cost to connect all servers).  Assumes
     * all servers can be connected.
     *
     * @param filename file to read input from
     * @return list of server connections, per the specifications
     */
    @Override
    public List<ServerConnection> getMinimumServerConnections(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));

            // Read initial values
            String[] firstLine = bf.readLine().split(" ");
            int numOfServers = Integer.parseInt(firstLine[0]);
            int numOfConnections = Integer.parseInt(firstLine[1]);

            List<Edge> connections = new ArrayList<>();

            // Loop through each connection
            for (int i = 0; i < numOfConnections; i++) {
                String[] line = bf.readLine().split(" ");
                connections.add(new Edge(Integer.parseInt(line[0]),
                                         Integer.parseInt(line[1]),
                                         Double.parseDouble(line[2])));
            }

            // Find shortest connections
            List<iPair> shortestConnections = MST.sparseMST(connections, numOfServers);
            List<ServerConnection> serverConnections = new ArrayList<>();

            // Convert from iPair to ServerConnection class
            for (iPair pair : shortestConnections) {
                serverConnections.add(new ServerConnection(pair.a, pair.b));
            }

            return serverConnections;


        } catch (IOException e) {
            //This should never happen... uh oh o.o
            System.err.println("ATTENTION TAs: Couldn't find test file: \"" + filename + "\":: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
