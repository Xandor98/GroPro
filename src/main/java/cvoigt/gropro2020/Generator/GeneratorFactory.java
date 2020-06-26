package cvoigt.gropro2020.Generator;

/**
 * This Factory provides Generators that are pre Generated or stubs to generate your own one.
 * The provided pre generated Generators are:
 * <ul>
 *     <li>ANSI-C</li>
 *     <li>Minimal Standard</li>
 *     <li>RANDU</li>
 *     <li>SIMSCRIPT</li>
 *     <li>NAG's LCG</li>
 *     <li>Maple's LCG</li>
 * </ul>
 * <p>
 * The used Generators are:
 * <ul>
 * <li>{@link LCG}</li>
 * <li>{@link PolarGenerator}</li>
 * <li>{@link SinusGenerator}</li>
 * </ul>
 * @author Cevin Voigt
 * @version 1.0
 */
public class GeneratorFactory {

    /**
     * Generates a {@link LCG Linear Congurential de.cae.Generator} with the given Multiplier, Shift, Modulator and Start Value.
     *
     * @param a     the Multiplier of the function
     * @param c     the shift of the function
     * @param m     the modulator of the function
     * @param first the start value of the function (x[0])
     * @return An {@link LCG}
     */
    public static LCG getLCG(long a, long c, long m, long first) {
        return new LCG(a, c, m, first);
    }

    /**
     * Generates a {@link PolarGenerator Polar de.cae.Generator} with a given {@link IGenerator}.
     *
     * @param generator The generator used in the polar function
     * @return An {@link PolarGenerator}
     */
    public static PolarGenerator getPolarGenerator(IGenerator generator) {
        return new PolarGenerator(generator);
    }

    /**
     * Generates a {@link SinusGenerator Sinus de.cae.Generator}.
     *
     * @return An {@link SinusGenerator}
     */
    public static SinusGenerator getSinusGenerator() {
        return new SinusGenerator();
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:
     * <br>
     * - Multiplier = 1103515245<br>
     * - Shift = 12345<br>
     * - Modulator = 2^31<br>
     * - First Value = 12345<br>
     *
     * @return An {@link LCG}
     */
    public static LCG AnsiC() {
        return getLCG(1103515245, 12345, (long) Math.pow(2, 31), 12345);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:<br>
     * - Multiplier = 16807 <br>
     * - Shift = 0<br>
     * - Modulator = 2^31 - 1<br>
     * - First Value = 1<br>
     *
     * @return An {@link LCG}
     */
    public static LCG MinimalStandard() {
        return getLCG(16807, 0, ((long) Math.pow(2, 31)) - 1, 1);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:<br>
     * - Multiplier = 65539<br>
     * - Shift = 0<br>
     * - Modulator = 2^31<br>
     * - First Value = 1<br>
     *
     * @return An {@link LCG}
     */
    public static LCG RANDU() {
        return getLCG(65539, 0, ((long) Math.pow(2, 31)), 1);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:<br>
     * - Multiplier = 630360016<br>
     * - Shift = 0<br>
     * - Modulator = 2^31 - 1<br>
     * - First Value = 1<br>
     *
     * @return An {@link LCG}
     */
    public static LCG SIMSCRIPT() {
        return getLCG(630360016, 0, ((long) Math.pow(2, 31)) - 1, 1);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values: <br>
     * - Multiplier = 13^13 <br>
     * - Shift = 0 <br>
     * - Modulator = 2^59 <br>
     * - First Value = 123456789 <br>
     *
     * @return An {@link LCG}
     */
    public static LCG NAGsLCG() {
        return getLCG((long) Math.pow(13L, 13L), 0, ((long) Math.pow(2, 59)), 123456789);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values: <br>
     * - Multiplier = 427419669081 <br>
     * - Shift = 0 <br>
     * - Modulator = 10^12 - 11 <br>
     * - First Value = 1 <br>
     *
     * @return An {@link LCG}
     */
    public static LCG MaplesLCG() {
        return getLCG(427419669081L, 0, ((long) Math.pow(10, 12)) - 11, 1);
    }

}
