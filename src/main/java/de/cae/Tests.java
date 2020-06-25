package de.cae;

import de.cae.Generator.*;
import de.cae.Guete.Autokorrelation;
import de.cae.Guete.EigeneGuete;
import de.cae.Guete.SequenzTest;
import de.cae.Output.Console;
import de.cae.Output.FileWriter;
import de.cae.Output.IOut;
import de.cae.Output.NoOutput;
import de.cae.misc.Table;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tests {

    public static void main(String[] args) {
        LCG_Tests();
        System.out.println();
        Polar_Test();
        System.out.println();
        Sinus_Test();
    }

    private static void Sinus_Test() {
        System.out.println("Start Simple Polar Test");

        IGenerator sinus = GeneratorFactory.getSinusGenerator();

        File f = new File("tests" + File.separator + "sinus_test_1.txt");
        IOut out = new FileWriter(f);

        out.write("Autokorrelation in 10^n bei n = 1...5");
        for (int i = 1; i <= 7; i++) {
            out.write("n = " + (int) Math.pow(10, i));
            out.write("erg = " + AutokorellationTest(sinus, (int) Math.pow(10, i), 5, new NoOutput()));
        }

        out.write("");
        out.write("Sequenztest in 10^n für n = 1...5");
        for (int i = 1; i <= 7; i++) {
            out.write("n = " + (int) Math.pow(10, i));
            SequenzUpDownTest(sinus, (int) Math.pow(10, i), out);
        }

        out.write("");
        out.write("Mittelwert Test in 10^n für n = 1...5");
        for (int i = 1; i <= 7; i++) {
            out.write("n = " + (int) Math.pow(10, i));
            out.write("erg = " + EigenerTest(sinus, (int) Math.pow(10, i), new NoOutput()));
        }

        System.out.println("Finish Simple Polar Test - File: " + f.getAbsolutePath());
    }

    private static void Polar_Test() {
        System.out.println("Start Simple Polar Test");

        IGenerator gen = new JavaGenerator(-1, 1);
        IGenerator polar = GeneratorFactory.getPolarGenerator(gen);

        File f = new File("tests" + File.separator + "polar_test_1.txt");
        IOut out = new FileWriter(f);

        out.write("Autokorrelation in 10^n bei n = 1...5");
        for (int i = 1; i <= 7; i++) {
            out.write("n = " + (int) Math.pow(10, i));
            out.write("erg = " + AutokorellationTest(polar, (int) Math.pow(10, i), 5, new NoOutput()));
        }

        out.write("");
        out.write("Sequenztest in 10^n für n = 1...5");
        for (int i = 1; i <= 7; i++) {
            out.write("n = " + (int) Math.pow(10, i));
            SequenzUpDownTest(polar, (int) Math.pow(10, i), out);
        }

        out.write("");
        out.write("Mittelwert Test in 10^n für n = 1...5");
        for (int i = 1; i <= 7; i++) {
            out.write("n = " + (int) Math.pow(10, i));
            out.write("erg = " + EigenerTest(polar, (int) Math.pow(10, i), new NoOutput()));
        }

        System.out.println("Finish Simple Polar Test - File: " + f.getAbsolutePath());
    }

    private static void LCG_Tests() {
        //Test 1
        //LCG
        //m = 10, a = 1...m, c = 1...m, x0 = 0...m

        System.out.println("Start of simple LCG Test");
        Table test = new Table();
        test.addHeader("a");
        test.addHeader("c");
        test.addHeader("x0");
        test.addHeader("Autokorrelation");
        test.addHeader("Sequenz Up-Down");
        test.addHeader("Eigene Qualitätsfunktion");
        for (int a = 1; a < 10; a++) {
            for (int c = 0; c < 10; c++) {
                for (int x0 = 0; x0 < 10; x0++) {
                    test.addValue("a", "" + a);
                    test.addValue("c", "" + c);
                    test.addValue("x0", "" + x0);

                    test.addValue("Autokorrelation", "" + AutokorellationTest(GeneratorFactory.getLCG(a, c, 10, x0), 100, 99, new NoOutput()));
                    test.addValue("Sequenz Up-Down", "" + SequenzUpDownTest(GeneratorFactory.getLCG(a, c, 10, x0), 100, new NoOutput()));
                    test.addValue("Eigene Qualitätsfunktion", "" + EigenerTest(GeneratorFactory.getLCG(a, c, 10, x0), 100, new NoOutput()));
                }
            }
        }

        File LCG_test_1 = new File("tests" + File.separator + "lcg_test_1.csv");
        FileWriter fw = new FileWriter(LCG_test_1);
        fw.write(test);
        System.out.println("Finish of simple LCG Test - File: " + LCG_test_1.getAbsolutePath());
        System.out.println();

        //Test 2
        //Vor implementierte LCG'S
        int count = 50000000;
        System.out.println("Start of pre-implemented LCG Test");
        File LCG_test = new File("tests" + File.separator + "lcg_test_2.txt");
        IOut test_out = new FileWriter(LCG_test);
        //Autokorellation
        test_out.write("\nANSIC");
        AutokorellationTest(GeneratorFactory.AnsiC(), count, 10, test_out);
        test_out.write("\nMinimal Standard");
        AutokorellationTest(GeneratorFactory.MinimalStandard(), count, 10, test_out);
        test_out.write("\nRANDU");
        AutokorellationTest(GeneratorFactory.RANDU(), count, 10, test_out);
        test_out.write("\nSIMSCRIPT");
        AutokorellationTest(GeneratorFactory.SIMSCRIPT(), count, 10, test_out);
        test_out.write("\nNAG's LCG");
        AutokorellationTest(GeneratorFactory.NAGsLCG(), count, 10, test_out);
        test_out.write("\nMaple's LCG");
        AutokorellationTest(GeneratorFactory.MaplesLCG(), count, 10, test_out);

        test_out.write("-----------------------------------------------------------");

        //Sequenz
        test_out.write("\nANSIC");
        SequenzUpDownTest(GeneratorFactory.AnsiC(), count, test_out);
        test_out.write("\nMinimal Standard");
        SequenzUpDownTest(GeneratorFactory.MinimalStandard(), count, test_out);
        test_out.write("\nRANDU");
        SequenzUpDownTest(GeneratorFactory.RANDU(), count, test_out);
        test_out.write("\nSIMSCRIPT");
        SequenzUpDownTest(GeneratorFactory.SIMSCRIPT(), count, test_out);
        test_out.write("\nNAG's LCG");
        SequenzUpDownTest(GeneratorFactory.NAGsLCG(), count, test_out);
        test_out.write("\nMaple's LCG");
        SequenzUpDownTest(GeneratorFactory.MaplesLCG(), count, test_out);

        test_out.write("-----------------------------------------------------------");

        //Eigene
        test_out.write("\nANSIC");
        EigenerTest(GeneratorFactory.AnsiC(), count, test_out);
        test_out.write("\nMinimal Standard");
        EigenerTest(GeneratorFactory.MinimalStandard(), count, test_out);
        test_out.write("\nRANDU");
        EigenerTest(GeneratorFactory.RANDU(), count, test_out);
        test_out.write("\nSIMSCRIPT");
        EigenerTest(GeneratorFactory.SIMSCRIPT(), count, test_out);
        test_out.write("\nNAG's LCG");
        EigenerTest(GeneratorFactory.NAGsLCG(), count, test_out);
        test_out.write("\nMaple's LCG");
        EigenerTest(GeneratorFactory.MaplesLCG(), count, test_out);
        System.out.println("Finish of pre-implemented LCG Test - File: " + LCG_test.getAbsolutePath());


        File f = new File("tests" + File.separator + "LCG_parameter_test.csv");
        FileWriter out = new FileWriter(f);

        System.out.println();
        System.out.println("Start of Parameter Test");
        Table parameter = new Table();
        parameter.addHeader("m");
        parameter.addHeader("a");
        parameter.addHeader("c");
        parameter.addHeader("x0");
        parameter.addHeader("Periodic Length");
        parameter.addHeader("Autokorrelation");
        parameter.addHeader("Sequenz Up-Down");
        parameter.addHeader("Mittelwert Test");

        for (int m = 1; m <= 10; m++) {
            for (int a = 1; a < m; a++) {
                for (int c = 0; c < m; c++) {
                    for (int x0 = 1; x0 < m; x0++) {
                        parameter.addValue("m", "" + m);
                        parameter.addValue("a", "" + a);
                        parameter.addValue("c", "" + c);
                        parameter.addValue("x0", "" + x0);
                        parameter.addValue("Periodic Length", "" + GeneratorFactory.getLCG(a, c, m, x0).calculatePeriod());
                        parameter.addValue("Autokorrelation", "" + AutokorellationTest(GeneratorFactory.getLCG(a, c, m, x0), 1000, 1, new NoOutput()));
                        parameter.addValue("Sequenz Up-Down", "" + SequenzUpDownTest(GeneratorFactory.getLCG(a, c, m, x0), 1000, new NoOutput()));
                        parameter.addValue("Mittelwert Test", "" + EigenerTest(GeneratorFactory.getLCG(a, c, m, x0), 1000, new NoOutput()));

                    }
                }
            }
        }

        out.write(parameter.toString());
        System.out.println("Start of Parameter Test - File: " + f.getAbsolutePath());
    }

    private static double AutokorellationTest(IGenerator gen, int n, int anzK, IOut out) {
        List<Double> erg = new ArrayList<>();

        out.write("Autokorrelations Test - " + gen.getClass().getSimpleName());
        out.write("");
        out.write("Parameter:");
        out.write("n = " + n);
        out.write("k = 1..." + anzK);

        for (int i = 1; i <= anzK; i++) {
            Autokorrelation atu = new Autokorrelation(n, i);
            erg.add(atu.control(gen));
        }
        out.write("");
        out.write("Mittelwert: " + (erg.stream().reduce(Double::sum).orElse(Double.MIN_VALUE)) / erg.size());
        out.write("");
        return (erg.stream().reduce(Double::sum).orElse(Double.MIN_VALUE)) / erg.size();
    }

    private static double SequenzUpDownTest(IGenerator gen, int n, IOut out) {

        SequenzTest sq = new SequenzTest(n, out);
        Map<Integer, Double> erg = sq.control(gen);

        return (erg.values().stream().reduce(Double::sum).orElse(0d)) / erg.values().size();
    }

    private static double EigenerTest(IGenerator gen, int n, IOut out) {
        out.write("Mittelwert Test - " + gen.getClass().getSimpleName());
        out.write("");
        out.write("Parameter:");
        out.write("n = " + n);

        out.write("");
        EigeneGuete sq = new EigeneGuete(n);
        double erg = sq.control(gen);

        out.write("Erg: " + erg);
        out.write("");

        return erg;
    }
}
