import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TreeHeight {

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Node {
        int value;
        public Node parent;
        public Set<Node> children;

        public Node(int value) {
            this.value = value;
            this.children = new HashSet<>();
        }

        public void add(Node node) {
            this.children.add(node);
        }
    }

    int n;
    int parent[];

    void read() throws IOException {
        FastScanner in = new FastScanner();
        n = in.nextInt();
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = in.nextInt();
        }
    }

    int height(Node node) {
        if (node == null) {
            return 0;
        }
        int max = 0;
        //System.out.println("Parent = " + node.value);
        for (Node n : node.children) {
            //System.out.println("\tChildren = " + n.value);
            max = Math.max(max, height(n));
        }
        return 1 + max;
    }

    int computeHeight() {
        int rootNode = -1;
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                rootNode = i;
            } else {
                Node par = map.get(parent[i]) != null ? map.get(parent[i]) : new Node(parent[i]);
                Node children = map.get(i) != null ? map.get(i) : new Node(i);

                par.add(children);
                children.parent = par;

                map.put(parent[i], par);
                map.put(i, children);
            }
        }
        return height(map.get(rootNode));
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new TreeHeight().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
