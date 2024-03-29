package cvoigt.gropro2020.Generator;

/**
 * This Class is an {@link IGenerator Random Number de.cae.Generator} that is called Polar de.cae.Generator. It uses the polar method
 * that uses two independent, equally distributed random numbers
 * and convert these into independent, normally distributed random numbers
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class PolarGenerator implements IGenerator {

    private IGenerator gen;
    private Double next;

    /**
     * Creates an Polar de.cae.Generator with an other type of {@link IGenerator}. <br>
     * If the de.cae.Generator can not create a random number between -1 and 1 it will throw an {@link ArithmeticException}
     *
     * @param gen An {@link IGenerator} that is used to generate a set of random Numbers
     * @throws ArithmeticException When the Range of Values is less then -1 or greater then 1
     */
    PolarGenerator(IGenerator gen) {
        this.gen = gen;
        if (gen.getInterval()[0] < -1 || gen.getInterval()[1] > 1) {
            throw new ArithmeticException("The Range of Numbers musst be beetween -1 & 1");
        }
        this.next = null;
    }

    /**
     * Genertes a pseudo random Number by using random numbers between -1 and 1. Also it uses the unit circle to generate
     * normally distributed random numbers.
     * <br>
     * q = u^2 + v^2 <br>
     * p = sqrt((-2 * ln(q))/q) <br>
     * x1 = u * p \& x2 = v * p
     */
    @Override
    public double generateRandomNumber() {
        if (next != null) {
            double ret = next;
            next = null;
            return ret;
        }
        double q = 0;
        double v = 0;
        double u = 0;
        do {
            v = gen.generateRandomNumber();
            u = gen.generateRandomNumber();

            q = u * u + v * v;
        } while (q == 0 || q >= 1);
        double p = Math.sqrt((-2 * Math.log(q))/q);
        next = v * p;
        return u * p;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getInterval() {
        return new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
    }
}
