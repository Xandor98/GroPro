package de.cae.Output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

/**
 * This is an {@link IOut de.cae.Output} function that provides to write a text in a file.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class FileWriter implements IOut {

    private File f;

    /**
     * Constructor that takes a path to a File where the text should be written down
     * <p>
     * The File needs to exist.
     *
     * @param path The Path to the file where to write
     */
    public FileWriter(String path) {
        this(new File(path));
    }

    /**
     * Constructor that takes a File where the text should be written down
     * <p>
     * The File needs to exist.
     *
     * @param f The File where to write
     */
    public FileWriter(File f) {
        this.f = f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(Object s) {

        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }

            Writer output;
            output = new BufferedWriter(new java.io.FileWriter(f, true));  //clears file every time
            output.append(s.toString() + System.lineSeparator());
            output.close();
        } catch (IOException e) {
        }
    }
}
