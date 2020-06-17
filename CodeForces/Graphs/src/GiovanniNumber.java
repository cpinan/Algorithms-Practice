import java.io.*;
import java.util.*;

/**
 * Created by cpinan on 6/16/17.
 */
public class GiovanniNumber {

    private static final String INPUT_FILE = "giovanni_case.txt";
    private static final String OUTPUT_FILE = "giovanni_results.txt";

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    static class Node {
        public int id;
        public int w;
        private Set<Node> edges;

        public Node(int id) {
            this.id = id;
            this.edges = new HashSet<>();
        }

        public Set<Node> getEdges() {
            return edges;
        }

        public void add(Node n) {
            getEdges().add(n);
        }
    }

    private void resolve() throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(INPUT_FILE);
        InputStream inputStream = new FileInputStream(file);
        Main.InputReader in = new Main.InputReader(inputStream);

        //InputStream inputStream = System.in;

        PrintWriter out = new PrintWriter(OUTPUT_FILE, "UTF-8");
        // PrintStream out = System.out;

        Map<Integer, Node> map = new HashMap<>();

        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int P = in.nextInt();
            int D = in.nextInt();
            for (int p = 0; p < P; p++) {
                map.put(p, new Node(p));
            }
            for (int j = 0; j < D; j++) {
                int left = in.nextInt();
                int right = in.nextInt();
                if (map.get(left) != null && map.get(right) != null) {
                    map.get(left).add(map.get(right));
                    map.get(right).add(map.get(left));
                }
            }
            bfs(map);
            for (int p = 1; p < P; p++) {
                if (map.get(p) != null) {
                    out.println(map.get(p).w);
                }
            }
            if (i < cases - 1) {
                out.println();
            }
        }

        out.close();
    }

    private void bfs(Map<Integer, Node> map) {
        Node root = map.get(0);
        if (root != null) {
            Set<Node> visited = new HashSet<>();
            visited.add(root);
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node n = queue.remove();
                for (Node edge : n.getEdges()) {
                    if (!visited.contains(edge)) {
                        edge.w = n.w + 1;
                        visited.add(edge);
                        queue.add(edge);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GiovanniNumber p = new GiovanniNumber();
        try {
            p.resolve();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
