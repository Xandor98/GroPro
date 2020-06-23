package de.cae.Generator;

/**
 * This Factory provides Generators that are pre Generated or stubs to generate your own one.
 * The provided pre generated Generators are:
 * - ANSI-C
 * - Minimal Standard
 * - RANDU
 * - SIMSCRIPT
 * - NAG's LCG
 * - Maple's LCG
 * <p>
 * The used Generators are:
 * - {@link LCG}
 * - {@link PolarGenerator}
 * - {@link SinusGenerator}
 *
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
     * - Multiplier = 1103515245
     * - Shift = 12345
     * - Modulator = 2^31
     * - First Value = 12345
     *
     * @return An {@link LCG}
     */
    public static LCG AnsiC() {
        return getLCG(1103515245, 12345, (int) Math.pow(2, 31), 12345);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:
     * - Multiplier = 16807
     * - Shift = 0
     * - Modulator = 2^31 - 1
     * - First Value = 1
     *
     * @return An {@link LCG}
     */
    public static LCG MinimalStandard() {
        return getLCG(16807, 0, ((int) Math.pow(2, 31)) - 1, 1);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:
     * - Multiplier = 65539
     * - Shift = 0
     * - Modulator = 2^31
     * - First Value = 1
     *
     * @return An {@link LCG}
     */
    public static LCG RANDU() {
        return getLCG(65539, 0, ((int) Math.pow(2, 31)), 1);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:
     * - Multiplier = 630360016
     * - Shift = 0
     * - Modulator = 2^31 - 1
     * - First Value = 1
     *
     * @return An {@link LCG}
     */
    public static LCG SIMSCRIPT() {
        return getLCG(630360016, 0, ((int) Math.pow(2, 31)) - 1, 1);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:
     * - Multiplier = 13^13
     * - Shift = 0
     * - Modulator = 2^59
     * - First Value = 123456789
     *
     * @return An {@link LCG}
     */
    public static LCG NAGsLCG() {
        return getLCG((int) Math.pow(13, 13), 0, ((int) Math.pow(2, 59)), 123456789);
    }

    /**
     * Generates an {@link LCG Linaer Congruential de.cae.Generator} with the Values:
     * - Multiplier = 427419669081L
     * - Shift = 0
     * - Modulator = 10^12 - 11
     * - First Value = 1
     *
     * @return An {@link LCG}
     */
    public static LCG MaplesLCG() {
        return getLCG(427419669081L, 0, ((int) Math.pow(10, 12)) - 11, 1);
    }

}
