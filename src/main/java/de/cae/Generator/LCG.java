package de.cae.Generator;

/**
 * This Class is an {@link IGenerator Random Number de.cae.Generator} that is called Linear Congruential-de.cae.Generator
 * <br>
 * This Class use a Multiplier, Shift, Modulator and a start Value to generate pseudo random Numbers.
 * The formula it uses is an iterative process and looks like:<br>
 * x[i+1] = (a * x[i] + c) mod m
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class LCG implements IGenerator {

    private final long a;
    private final long c;
    private final long m;
    private long currentX;

    /**
     * Creates an Linear Congruential-de.cae.Generator (LCG) with an Multiplier a, Shift c, Modulator m and start Value x[0]
     *
     * @param a     The Multiplier of the function
     * @param c     the Shift of the function
     * @param m     the modulator of the function
     * @param start the start Value of the function
     */
    LCG(long a, long c, long m, long start) {
        this.a = a;
        this.c = c;
        this.m = m;
        this.currentX = start;
    }

    /**
     * Generates a random Number with a iterative formula.<br>
     * <p>
     * x[i+1] = (a * x[i] + c) mod m
     */
    @Override
    public double generateZufallzahl() {
        currentX = (a * currentX + c) % m;
        return currentX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getInterval() {
        return new double[]{0, m};
    }

}
