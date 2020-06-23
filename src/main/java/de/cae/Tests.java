package de.cae;

import de.cae.Generator.GeneratorFactory;
import de.cae.Generator.IGenerator;
import de.cae.Generator.JavaGenerator;
import de.cae.Guete.Autokorrelation;
import de.cae.Guete.EigeneGuete;
import de.cae.Guete.SequenzTest;
import de.cae.Output.FileWriter;
import de.cae.Output.IOut;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tests {

    public static final int ANZ = 5;

    public static void main(String[] args) {
        //LCG's
        IGenerator ansic = GeneratorFactory.AnsiC();
        IGenerator minSt = GeneratorFactory.MinimalStandard();
        IGenerator randu = GeneratorFactory.RANDU();
        IGenerator simscript = GeneratorFactory.SIMSCRIPT();
        IGenerator nag = GeneratorFactory.NAGsLCG();
        IGenerator maple = GeneratorFactory.MaplesLCG();

        test(ansic, "ansi_c", ANZ);
        test(minSt, "minimal_standard", ANZ);
        test(randu, "rendu", ANZ);
        test(simscript, "simscript", ANZ);
        test(nag, "nag", ANZ);
        test(maple, "maple", ANZ);

        //Polar de.cae.Generator
        IGenerator polar = GeneratorFactory.getPolarGenerator(new JavaGenerator(-1, 1));

        test(polar, "polar", ANZ);

        //Own
        IGenerator sin = GeneratorFactory.getSinusGenerator();

        test(sin, "sinus_gen", ANZ);
    }

    public static void test(IGenerator gen, String name, int count) {
        System.out.println("tests" + File.separator + name);
        File f = new File("tests" + File.separator + name + File.separator + "autokorrelation.test");
        File ges_a = new File("tests" + File.separator + name + File.separator + "autokorrelation_erg.test");
        if (f.exists()) {
            f.delete();
        }
        if (ges_a.exists()) {
            ges_a.delete();
        }
        IOut out = new FileWriter(f);
        IOut out_erg = new FileWriter(ges_a);

        List<Double> korrelation = new ArrayList<>();
        for (int n = 10 * 100; n <= 10 * 100 + count; n++) {
            for (int k = 1; k <= 5; k++) {
                Autokorrelation autokorrelation = new Autokorrelation(n, k, out);
                double kor = autokorrelation.control(gen);
                out_erg.write("-----------------");
                out_erg.write("n = " + (n));
                out_erg.write("k = " + k);
                out_erg.write("erg = " + kor);
                out_erg.write("");
                korrelation.add(kor);
            }
        }
        double m = korrelation.stream().reduce(Double::sum).get() / korrelation.size();

        out_erg.write("Mittelwert: " + m);
        out_erg.write("");

        File f2 = new File("tests" + File.separator + name + File.separator + "sequenz.test");
        File ges_s = new File("tests" + File.separator + name + File.separator + "sequenz_erg.test");
        if (f2.exists()) {
            f2.delete();
        }
        if (ges_s.exists()) {
            ges_s.delete();
        }

        IOut out2 = new FileWriter(f2);
        IOut out2_erg = new FileWriter(ges_s);

        for (int n = 10 * 100; n <= 10 * 100 + count; n++) {
            SequenzTest st = new SequenzTest(n, out2);
            out2_erg.write("--------------");
            out2_erg.write("n = " + (n));
            out2_erg.write("erg: ");
            out2_erg.write(st.control(gen));
        }

        File f3 = new File("tests" + File.separator + name + File.separator + "eigeneGuete.test");
        File ges_e = new File("tests" + File.separator + name + File.separator + "eigeneGuete_erg.test");

        if (f3.exists()) {
            f3.delete();
        }
        if (ges_e.exists()) {
            ges_e.delete();
        }

        IOut out3 = new FileWriter(f3);
        IOut out3_erg = new FileWriter(ges_e);

        List<Double> eigen = new ArrayList<>();
        for (int n = 10 * 100; n <= 10 * 100 + count; n++) {
            EigeneGuete eg = new EigeneGuete(n, out3);
            double e = eg.control(gen);
            out3_erg.write("--------------");
            out3_erg.write("n = " + (n));
            out3_erg.write("erg = " + e);
            out3_erg.write("");
            eigen.add(e);
        }
        m = eigen.stream().reduce(Double::sum).get() / eigen.size();

        out3_erg.write("Mittelwert = " + m);
    }

}
