import java.io.*;
import java.util.*;

/**
 * @author Carlos Piñan
 */
public class NodeTooFar {

    public class Node {
        public int data;
        public int distance;
        private List<Node> edges;

        public Node(int data) {
            this.data = data;
            edges = new ArrayList<>();
        }

        public List<Node> getEdges() {
            if (edges == null) {
                edges = new ArrayList<>();
            }
            return edges;
        }

        public void add(Node node) {
            getEdges().add(node);
        }
    }

    public void resolve() throws FileNotFoundException, UnsupportedEncodingException {
        //PrintWriter writer = new PrintWriter("node_too_far_results.txt", "UTF-8");
        PrintStream writer = System.out;
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("node_too_far_002.txt"));
        final String format = "Case %d: %d nodes not reachable from node %d with TTL = %d.";
        int n, cases = 1;
        Map<Integer, Node> map;
        do {
            n = scanner.nextInt();
            if (n > 0) {
                map = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    int c1 = scanner.nextInt();
                    int c2 = scanner.nextInt();
                    Node n1 = map.get(c1);
                    if (n1 == null) {
                        n1 = new Node(c1);
                        map.put(c1, n1);
                    }
                    Node n2 = map.get(c2);
                    if (n2 == null) {
                        n2 = new Node(c2);
                        map.put(c2, n2);
                    }
                    n1.add(n2);
                    n2.add(n1);
                }
                int targetNode, distance;
                do {
                    targetNode = scanner.nextInt();
                    distance = scanner.nextInt();
                    Node node = map.get(targetNode);
                    if (targetNode != 0 || distance != 0) {
                        int result = notReachableNodesFrom(map, node, distance);
                        writer.println(String.format(format, cases, result, targetNode, distance));
                        cases++;
                    }
                } while (targetNode != 0 || distance != 0);
            }
        } while (n > 0);
        scanner.close();
        writer.close();
    }

    private int notReachableNodesFrom(Map<Integer, Node> map, Node node, int distance) {
        Set<Node> visited = new HashSet<>();
        if (node != null) {
            Queue<Node> bfs = new LinkedList<>();
            node.distance = distance;
            bfs.add(node);
            visited.add(node);
            while (!bfs.isEmpty()) {
                Node n = bfs.remove();
                for (Node tmp : n.getEdges()) {
                    if (!visited.contains(tmp)) {
                        if (n.distance - 1 >= 0) {
                            tmp.distance = n.distance - 1;
                            bfs.add(tmp);
                            visited.add(tmp);
                        }
                    }
                }
            }
            ;
        }
        return map.size() - visited.size();
    }

    public static void main(String[] args) {
        NodeTooFar nodeTooFar = new NodeTooFar();
        try {
            nodeTooFar.resolve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
