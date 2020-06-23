package de.cae.Output;

/**
 * This Class provides an {@link IOut de.cae.Output} function that dont output anything.
 * <p>
 * It is used when no de.cae.Output from a class is needed.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class NoOutput implements IOut {

    /**
     * This function dont do anything cause of no de.cae.Output needed
     *
     * @param s The object that should be outputted
     */
    public void write(Object s) {
    }
}
