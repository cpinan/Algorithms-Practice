package part1.week1;

import common.IOTemplate;

/**
 * @author Carlos Piñan
 */
public class NaiveUF extends IOTemplate implements UF {

    private int N;

    public NaiveUF(final int N) {
        this.N = N;
    }

    @Override
    public void union(int p, int q) {

    }

    @Override
    public boolean connected(int p, int q) {
        return false;
    }

    @Override
    public int find(int p) {
        return 0;
    }

    @Override
    public int count() {
        return 0;
    }
}
