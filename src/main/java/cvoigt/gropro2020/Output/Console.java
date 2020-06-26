package cvoigt.gropro2020.Output;

/**
 * This class is an {@link IOut de.cae.Output} function that provides writing in the console.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class Console implements IOut {

    /**
     * Writes the given Object in the Console, by using the ToString Method.
     *
     * @param s The object that should be outputted
     */
    public void write(Object s) {
        System.out.println(s.toString());
    }
}
