/**
 * @author Carlos Piñan
 */
public class BitsOn {

    public void printBits(final int x) {
        StringBuilder b = new StringBuilder();
        System.out.println("Reading " + x);
        for (int i = 0; i <= 30; i++) {
            if ((x & (1 << i)) != 0) { // Get bit at i
                System.out.print(i + " ");
                b.append(1);
            } else {
                b.append(0);
            }
        }
        System.out.println();
        System.out.println(b.reverse().toString());
        System.out.println();
    }

    public static void main(String[] args) {
        BitsOn m = new BitsOn();
        m.printBits(16);
        m.printBits(15);
        m.printBits(1);
    }

}
