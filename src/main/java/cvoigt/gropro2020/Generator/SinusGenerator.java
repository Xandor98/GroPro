package cvoigt.gropro2020.Generator;

/**
 * This Class is an {@link IGenerator Random Number de.cae.Generator} that uses the current time in nano seconds and the sinus
 * function to generate a pseudo random number.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class SinusGenerator implements IGenerator {

    /**
     * Standard Constructor
     */
    SinusGenerator() {
    }

    /**
     * Generates a pseudo random number by using the last 3 digits of the current time in nano seconds and modulate
     * them with 360 and then convert the degree in radians and then take the absolute sinus value.
     *
     * @return A pseudo random Number
     */
    @Override
    public double generateRandomNumber() {
        String time = "" + System.nanoTime();
        int grad = Integer.parseInt((time).substring(time.length() - 4));
        grad %= 360;
        double rad = (grad * Math.PI) / 180;
        return Math.abs(Math.sin(rad));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long[] getInterval() {
        return new long[]{0, 1};
    }
}
