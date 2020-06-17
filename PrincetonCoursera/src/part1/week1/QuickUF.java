package part1.week1;

import common.IOTemplate;

/**
 * @author Carlos Piñan
 */
public class QuickUF extends IOTemplate implements UF {

    private int N;
    private int[] arr;

    public QuickUF(final int N) {
        this.N = N;
        this.arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        if (arr[p] != arr[q]) {
            arr[p] = arr[q];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        while (arr[p] != p) {
            p = arr[p];
        }
        return p;
    }

    @Override
    public int count() {
        return N;
    }
}
