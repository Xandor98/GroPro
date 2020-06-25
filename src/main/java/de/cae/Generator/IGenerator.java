package de.cae.Generator;

import java.util.ArrayList;
import java.util.List;

/**
 * This Interface provides a standardized de.cae.Generator that all Generators need to Implement.
 * <p>
 * The de.cae.Generator needs to have functions for:
 * - Generate new random Value
 * - Range of Values
 * - Generate a List of random Values
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public interface IGenerator {

    /**
     * Generates a random Value
     *
     * @return A random value
     */
    double generateRandomNumber();

    /**
     * Returns an Array of the length 2 with the Range of Values.
     *
     * @return The Range of Values for this generator
     */
    long[] getInterval();

    /**
     * Generates a List of n Random Values.
     *
     * @param n The number of Random Values that schould be generated
     * @return A {@link List<Double> List} of Random Values with a size of n
     */
    default List<Double> getRandomNumbers(int n) {
        List<Double> zahlen = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            zahlen.add(generateRandomNumber());
        }
        return zahlen;
    }
}
