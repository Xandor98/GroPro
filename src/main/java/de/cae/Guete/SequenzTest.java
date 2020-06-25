package de.cae.Guete;

import de.cae.Generator.IGenerator;
import de.cae.Output.IOut;
import de.cae.Output.NoOutput;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is an {@link IGuete Qualitiy} function to provide a quality factor of an given {@link IGenerator Random Number de.cae.Generator}.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class SequenzTest implements IGuete<Map<Integer, Double>> {

    private final int n;
    private IOut out;

    /**
     * Constructor that gets a Number of to generated Numbers and uses the {@link SequenzTest#SequenzTest(int, IOut)} with
     * {@link NoOutput} as standard for the {@link IOut} parameter.
     *
     * @param n Number of generated Random numbers
     */
    public SequenzTest(int n) {
        this(n, new NoOutput());
    }

    /**
     * Standard Constructor that gets a Number of to generated Numbers and an {@link IOut de.cae.Output} function to write down the steps.
     *
     * @param n   Number of generated Random numbers
     * @param out The {@link IOut de.cae.Output} function to write down the steps
     */
    public SequenzTest(int n, IOut out) {
        this.n = n;
        this.out = out;
    }

    /**
     * Checks the quality of the given {@link IGenerator Random Number generator} by looking up the direct neighbors and compare them.
     * If x[i] < x[i+1] it will be saved a 1 and else it will be saved 0. the number of the same bits are then counted and stored.
     * After this the distinct numbers of the saved Numbers will be used in a formula to generate quality Numbers of the distinct numbers.
     *
     * @param gen The generator to check on
     * @return A List of quality numbers
     */
    public Map<Integer, Double> control(IGenerator gen) {
        List<Double> z = gen.getRandomNumbers(n);

        out.write("------- Sequenz - Up-Down-Test -------");
        out.write("Parameter:");
        out.write("n = " + n);

        int[] bits = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            if (z.get(i) < z.get(i + 1)) {
                bits[i] = 1;
            } else {
                bits[i] = 0;
            }
        }
        List<Integer> list = new ArrayList<>();
        int count = 0;
        int currentBit = bits[0];

        for (int i = 0; i < n - 1; i++) {
            if (currentBit == bits[i]) {
                count++;
            } else {
                list.add(count);
                count = 1;
                currentBit = bits[i];
            }
        }
        list.add(count);
        List<Integer> distinct = list.stream().distinct().sorted().collect(Collectors.toList());

        out.write("Alle k's:");
        out.write(distinct);
        out.write("");

        Map<Integer, Double> ret = new HashMap<>();
        out.write("N(k):");
        for (int k : distinct) {
            double n = N(k);
            String tmp = "N(" + k + ") = " + n + " (" + list.stream().filter(integer -> integer == k).count() + ")";
            n = Math.abs(n - list.stream().filter(integer -> integer == k).count());
            out.write(tmp + " diff: " + n);
            ret.put(k, n);
        }

        out.write("Avg diff: " + ret.values().stream().reduce(Double::sum).orElse(0d)/ret.values().size());
        return ret;
    }

    /**
     * Function that is used in the algorithm to check the {@link IGenerator}
     *
     * @param k Just a digit used in the function like x
     * @return A Double as result of the function
     */
    private double N(long k) {
        double n1 = (k * k + 3 * k + 1) * n - (k * k * k + 3 * k * k - k - 4);
        double n2 = (1. * fac(k + 3)) / 2;
        return (1. * n1) / n2;
    }

    private long fac(long k) {
        if (k == 1) return k;
        return k * fac(k - 1);
    }
}
