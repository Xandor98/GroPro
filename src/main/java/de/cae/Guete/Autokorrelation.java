package de.cae.Guete;

import de.cae.Generator.IGenerator;
import de.cae.Output.IOut;
import de.cae.Output.NoOutput;

import java.util.List;

/**
 * This class is a {@link IGuete Quality function} that checks a given {@link IGenerator} of his quality.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class Autokorrelation implements IGuete<Double> {

    private final int n;
    private final int k;

    private final IOut out;

    /**
     * Constructor that uses {@link Autokorrelation#Autokorrelation(int, int, IOut)} with giving it the n, k and as
     * {@link IOut output} the {@link NoOutput} class
     *
     * @param n The number of random generated Numbers
     * @param k The Neighbor to look at
     */
    public Autokorrelation(int n, int k) {
        this(n, k, new NoOutput());
    }

    /**
     * Standard Constructor that provides a quality function with an Number of Random generated Values (n) and the neighbors to look at (k)
     * <br>
     * It also has an parameter for the {@link IOut de.cae.Output} function to proovide the steps as written down.
     *
     * @param n   The number of random generated Numbers
     * @param k   The Neighbor to look at
     * @param out The {@link IOut} function to use to print the steps
     */
    public Autokorrelation(int n, int k, IOut out) {
        this.n = n;
        this.k = k;
        this.out = out;
    }

    /**
     * Checks the quality of the given {@link IGenerator Random Number generator} by Generating n Numbers and check each
     * number with the expected Value. If the qualtiy of this checker is 0 it is a random number generator. As far as it gets
     * from the 0 it is not an random Number generator.
     *
     * @param gen The generator to check on
     * @return A Double that represents the quality.
     */
    public Double control(IGenerator gen) {

        out.write("---------------- Autokorrelation ---------------");
        out.write("Parameter:");
        out.write("n = " + n);
        out.write("k = " + k);
        out.write("");
        double mid = gen.getInterval()[0] - (gen.getInterval()[1] - gen.getInterval()[0]) / 2;
        double pk1 = 0;
        double pk2 = 0;

        out.write("u = " + mid);
        out.write("");

        List<Double> z = gen.getRandomNumbers(n);
        out.write("Zufallszahlen:");
        if (!(out instanceof NoOutput)) {
            for (double d : z) {
                out.write(d);
            }
        }
        for (int i = 0; i < n - k; i++) {
            pk1 += (z.get(i) - mid) * (z.get(i + k) - mid);
            pk2 += Math.pow(z.get(i) - mid, 2);
        }

        out.write("");
        out.write("pk = " + (pk1 / pk2));

        return pk1 / pk2;
    }
}
