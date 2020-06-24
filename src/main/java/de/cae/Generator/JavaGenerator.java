package de.cae.Generator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This Class is an {@link IGenerator Random Number de.cae.Generator} the use the standard implementation of Java.
 * See {@link ThreadLocalRandom}
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class JavaGenerator implements IGenerator {

    private final double a;
    private final double b;

    /**
     * Creates a de.cae.Generator with an Range of Values from a to b.
     * <p>
     * if a is greater than b the Values will swap.
     *
     * @param a The minimum Bound
     * @param b The maximum Bound
     */
    public JavaGenerator(double a, double b) {
        if (a > b) {
            double tmp = a;
            this.a = b;
            this.b = tmp;
        } else {
            this.a = a;
            this.b = b;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double generateRandomNumber() {
        return ThreadLocalRandom.current().nextDouble(a, b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getInterval() {
        return new double[]{a, b};
    }
}
