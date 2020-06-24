package de.cae;

import de.cae.Generator.GeneratorFactory;
import de.cae.Generator.IGenerator;
import de.cae.Generator.JavaGenerator;
import de.cae.Generator.LCG;
import de.cae.Guete.Autokorrelation;
import de.cae.Guete.EigeneGuete;
import de.cae.Guete.SequenzTest;
import de.cae.Output.Console;
import de.cae.Output.FileWriter;
import de.cae.Output.IOut;
import de.cae.Output.NoOutput;
import de.cae.misc.Table;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tests {

    public static void main(String[] args) {
        //Test 1
        //LCG
        //m = 10, a = 1...m, c = 1...m, x0 = 0...m

        Table test = new Table();
        test.addHeader("a");
        test.addHeader("c");
        test.addHeader("x0");
        test.addHeader("Autokorrelation");
        test.addHeader("Sequenz Up-Down");
        test.addHeader("Eigene Qualitätsfunktion");
        for (int a = 1; a < 10; a++){
            for (int c = 0; c < 10; c++){
                for(int x0 = 0; x0 < 10; x0++){
                    test.addValue("a", "" + a);
                    test.addValue("c", "" + c);
                    test.addValue("x0", "" + x0);

                    test.addValue("Autokorrelation", "" + AutokorellationTest(GeneratorFactory.getLCG(a,c,10,x0), 100, 99, new NoOutput()));
                    test.addValue("Sequenz Up-Down", "" + SequenzUpDownTest(GeneratorFactory.getLCG(a,c,10,x0), 100, new NoOutput()));
                    test.addValue("Eigene Qualitätsfunktion", "" + EigenerTest(GeneratorFactory.getLCG(a,c,10,x0), 100, new NoOutput()));
                }
            }
        }

        FileWriter fw = new FileWriter(new File("lcg_test_1.csv"));
        fw.write(test);

        //Test 2
        //Vor implementierte LCG'S
        int count = 50000000;

        //Autokorellation
        AutokorellationTest(GeneratorFactory.AnsiC(), count, 10, new Console());
        AutokorellationTest(GeneratorFactory.MinimalStandard(), count, 10, new Console());
        AutokorellationTest(GeneratorFactory.RANDU(), count, 10, new Console());
        AutokorellationTest(GeneratorFactory.SIMSCRIPT(), count, 10, new Console());
        AutokorellationTest(GeneratorFactory.NAGsLCG(), count, 10, new Console());
        AutokorellationTest(GeneratorFactory.MaplesLCG(), count, 10, new Console());

        System.out.println("-----------------------------------------------------------");

        //Sequenz
        SequenzUpDownTest(GeneratorFactory.AnsiC(), count, new Console());
        SequenzUpDownTest(GeneratorFactory.MinimalStandard(), count, new Console());
        SequenzUpDownTest(GeneratorFactory.RANDU(), count, new Console());
        SequenzUpDownTest(GeneratorFactory.SIMSCRIPT(), count, new Console());
        SequenzUpDownTest(GeneratorFactory.NAGsLCG(), count, new Console());
        SequenzUpDownTest(GeneratorFactory.MaplesLCG(), count, new Console());

        System.out.println("-----------------------------------------------------------");

        //Eigene
        EigenerTest(GeneratorFactory.AnsiC(), count, new Console());
        EigenerTest(GeneratorFactory.MinimalStandard(), count, new Console());
        EigenerTest(GeneratorFactory.RANDU(), count, new Console());
        EigenerTest(GeneratorFactory.SIMSCRIPT(), count, new Console());
        EigenerTest(GeneratorFactory.NAGsLCG(), count, new Console());
        EigenerTest(GeneratorFactory.MaplesLCG(), count, new Console());
    }

    public static double AutokorellationTest(IGenerator gen, int n, int anzK, IOut out){
        List<Double> erg = new ArrayList<>();

        out.write("Autokorrelations Test - " + gen.getClass().getSimpleName());
        out.write("");
        out.write("Parameter:");
        out.write("n = " + n);
        out.write("k = 1..." + anzK );

        for (int i = 1; i <= anzK; i++){
            Autokorrelation atu = new Autokorrelation(n, i);
            erg.add(atu.control(gen));
        }
        out.write("");
        out.write("Mittelwert: " + (erg.stream().reduce(Double::sum).orElse(Double.MIN_VALUE))/erg.size());
        out.write("");
        return (erg.stream().reduce(Double::sum).orElse(Double.MIN_VALUE))/erg.size();
    }

    public static double SequenzUpDownTest(IGenerator gen, int n, IOut out){
        out.write("Sequenz Up Down Test - " + gen.getClass().getSimpleName());
        out.write("");
        out.write("Parameter:");
        out.write("n = " + n);

        out.write("");
        SequenzTest sq = new SequenzTest(n);
        Map<Integer, Double> erg = sq.control(gen);
        for (Integer integer : erg.keySet()) {
            out.write("Für N(" + integer +") : " + erg.get(integer));
        }

        out.write("");
        out.write("-------------");
        out.write("Mittelwert: " + (erg.values().stream().reduce(Double::sum).orElse(Double.MIN_VALUE))/erg.values().size());
        out.write("");

        return (erg.values().stream().reduce(Double::sum).orElse(Double.MIN_VALUE))/erg.values().size();
    }

    public static double EigenerTest(IGenerator gen, int n, IOut out){
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
