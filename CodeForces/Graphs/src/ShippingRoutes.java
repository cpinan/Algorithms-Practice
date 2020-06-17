import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Carlos Piñan
 */
public class ShippingRoutes {

    private static final String INPUT_FILE = "shipping_routes_case_001.txt";
    private static final String OUTPUT_FILE = "shipping_routes_results.txt";

    public static class Node {

        public String id;
        public int distance;
        private Set<Node> links;

        public Node(String id) {
            this.id = id;
            this.distance = 0;
            this.links = new HashSet<>();
        }

        public boolean hasLinks() {
            return getLinks() != null && !getLinks().isEmpty();
        }

        public Set<Node> getLinks() {
            return links;
        }

        public void add(Node n) {
            links.add(n);
        }

        public boolean contains(Node n) {
            return links != null && !links.isEmpty() && links.contains(n);
        }

    }

    private void resolve() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        PrintWriter writer = new PrintWriter(OUTPUT_FILE, "UTF-8");

        final String HEADER = "SHIPPING ROUTES OUTPUT";
        final String FOOTER = "END OF OUTPUT";
        final String NO_SHIPMENT_POSSIBLE = "NO SHIPMENT POSSIBLE";
        final String DATASET_FORMAT = "DATA SET  %d";
        final String PRICE_FORMAT = "$%d";
        final int BASE_PRICE = 100;

        writer.println(HEADER);

        int w;
        int dataSets = scanner.nextInt();
        for (int i = 1; i <= dataSets; i++) {
            int M = scanner.nextInt();
            int N = scanner.nextInt();
            int P = scanner.nextInt();
            Map<String, Node> map = new HashMap<>();
            for (w = 0; w < M; w++) {
                Node n = new Node(scanner.next());
                map.put(n.id, n);
            }
            for (w = 0; w < N; w++) {
                String codeLeft = scanner.next();
                String codeRight = scanner.next();
                if (map.containsKey(codeLeft) && map.containsKey(codeRight)) {
                    Node n1 = map.get(codeLeft);
                    Node n2 = map.get(codeRight);
                    n1.add(n2);
                    n2.add(n1);
                }
            }
            writer.println();
            writer.println(String.format(DATASET_FORMAT, i));
            writer.println();
            for (w = 0; w < P; w++) {
                int shipmentSize = scanner.nextInt();
                String codeLeft = scanner.next();
                String codeRight = scanner.next();

                if (map.containsKey(codeLeft) && map.containsKey(codeRight)) {
                    Node n1 = map.get(codeLeft);
                    Node n2 = map.get(codeRight);
                    if (n1.contains(n2)) {
                        writer.println(String.format(PRICE_FORMAT, BASE_PRICE * shipmentSize));
                    } else {
                        int extraSize = checkShipmentExtraSize(n1, n2);
                        if (extraSize == -1) {
                            writer.println(NO_SHIPMENT_POSSIBLE);
                        } else {
                            writer.println(String.format(PRICE_FORMAT, BASE_PRICE * shipmentSize * extraSize));
                        }
                    }
                } else {
                    writer.println(String.format(PRICE_FORMAT, 0));
                }
            }
        }

        scanner.close();

        writer.println();
        writer.println(FOOTER);

        writer.close();
    }

    private int checkShipmentExtraSize(Node n1, Node n2) {
        if (n1.hasLinks() && n2.hasLinks()) {
            Set<Node> visited = new HashSet<>();
            Queue<Node> bfs = new LinkedList<>();
            n1.distance = 0;
            visited.add(n1);
            bfs.add(n1);
            while (!bfs.isEmpty()) {
                Node n = bfs.remove();
                for (Node link : n.getLinks()) {
                    if (!visited.contains(link)) {
                        link.distance = n.distance + 1;
                        if (link.id.equals(n2.id)) {
                            return link.distance;
                        }
                        bfs.add(link);
                        visited.add(link);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShippingRoutes p = new ShippingRoutes();
        try {
            p.resolve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
