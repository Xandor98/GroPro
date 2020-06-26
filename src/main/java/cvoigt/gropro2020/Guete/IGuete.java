package cvoigt.gropro2020.Guete;

import cvoigt.gropro2020.Generator.IGenerator;

/**
 * An functional interface to evaluate {@link IGenerator Random Number Generators} and return a Value/Object that defines the quality
 * of the de.cae.Generator
 *
 * @param <T> The return type
 * @author Cevin Voigt
 * @version 1.0
 */
public interface IGuete<T> {

    /**
     * Checks a {@link IGenerator Rabdom Number de.cae.Generator} and returns an Value/Object that defines the quality of the generator
     *
     * @param gen The generator to check on
     * @return The quality of the de.cae.Generator
     */
    T control(IGenerator gen);
}
