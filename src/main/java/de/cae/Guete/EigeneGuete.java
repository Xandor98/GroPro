package de.cae.Guete;

import de.cae.Generator.IGenerator;
import de.cae.Output.IOut;
import de.cae.Output.NoOutput;

import java.util.List;

/**
 * This class is a {@link IGuete Quality function} that checks a given {@link IGenerator} of his quality.
 *
 * @author Cevin Voigt
 * @version 1.0
 */
public class EigeneGuete implements IGuete<Double> {

    private final int n;
    private final IOut out;

    public EigeneGuete(int n) {
        this(n, new NoOutput());
    }

    /**
     * Standard Constructor with a given Number to generate Random Numbers (n) and an {@link IOut de.cae.Output} function to write down the steps.
     *
     * @param n   The number of Generated Random Numbers
     * @param out The {@link IOut de.cae.Output} function that is used to write down the steps
     */
    public EigeneGuete(int n, IOut out) {
        this.n = n;
        this.out = out;
    }

    /**
     * Checks the quality of the given {@link IGenerator Random Number generator} by looking at the differenz between the
     * arithmetic half and the expected Value and returns this number as a quality check.
     *
     * @param gen The generator to check on
     * @return the quality of the given {@link IGenerator Random Number de.cae.Generator}
     */
    public Double control(IGenerator gen) {
        out.write("----------- Eigene Güte ----------");
        double u = gen.getInterval()[0] - (gen.getInterval()[1] - gen.getInterval()[0]) / 2;

        out.write("Parameter:");
        out.write("u = " + u);

        out.write("");
        List<Double> z = gen.getRandomNumbers(n);

        out.write("Zufallszahlen:");
        if (!(out instanceof NoOutput)) {
            for (double d : z) {
                out.write(d);
            }
        }

        double count = 0;
        for (int i = 0; i < n; i++) {
            count += z.get(i);
        }

        out.write("");
        out.write("erg: " + (u - count / n));

        return u - count / n;
    }
}
