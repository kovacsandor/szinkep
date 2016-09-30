/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szinkep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Andor Kovács
 */
public class Szinkep {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
//1. Olvassa be a fájlból egy megfelelő adatszerkezetbe az egyes képpontok RGB kódját!
        System.out.println("1. feladat");

        BufferedReader bufferedReader = new BufferedReader(new FileReader("kep.txt"));
        ArrayList<Keppont> keppontok = new ArrayList<>();
        String beolvasas; // Miért kell beletenni változóba?

        int sor = 1;
        int oszlop = 0;
        while ((beolvasas = bufferedReader.readLine()) != null) {
            String rgb = beolvasas;
            if (sor > 50) {
                break;
            } else {
                if (oszlop > 49) {
                    sor++;
                    oszlop = 1;
                } else {
                    oszlop++;
                }
                String[] beolvasasSzetvagva = beolvasas.split(" ");
                int voros = Integer.parseInt(beolvasasSzetvagva[0]);
                int zold = Integer.parseInt(beolvasasSzetvagva[1]);
                int kep = Integer.parseInt(beolvasasSzetvagva[2]);
                keppontok.add(new Keppont(sor, oszlop, voros, zold, kep, rgb));
            }
        }

        for (int i = 0; i < keppontok.size(); i++) {
            System.out.println(
                    "sor: " + keppontok.get(i).sor
                    + ", oszlop: " + keppontok.get(i).oszlop
                    + ", voros: " + keppontok.get(i).voros
                    + ", zold: " + keppontok.get(i).zold
                    + ", kek: " + keppontok.get(i).kek
                    + ", RGB: " + keppontok.get(i).rgb
            );
        }
        System.out.println("A 'kep.txt' fájl beolvasva.");
//2. Kérjen be a felhasználótól egy RGB kódot! Állapítsa meg a program segítségével, hogy a bekért szín megtalálható-e a képen! A megállapítás eredményét írja ki a képernyőre!        
        System.out.println("2. feladat");
        String userInput = getInput("Adjon meg egy RGB színkódot! ").trim();
        boolean megtalalhato = false;
        for (int i = 0; i < keppontok.size(); i++) {
            if (keppontok.get(i).rgb.equals(userInput)) {
                megtalalhato = true;
                break;
            }
        }
        if (megtalalhato) {
            System.out.println("Megtalálható");
        } else {
            System.out.println("Nem megtalálható");
        }

//3. Határozza meg, hogy a kép 35. sor 8. képpontjának színe hányszor szerepel a 35. sorban, illetve a 8. oszlopban. Az értékeket írja ki a képernyőre az alábbi formában: Például: Sorban: 5 Oszlopban: 10
        System.out.println("3. feladat");
        String vizsgaltSzin = "";
        int vizsgaltSor = 35;
        int vizsgaltOszlop = 8;
        int hanyszorSzerepelSorban = 0;
        int hanyszorSzerepelOszlopban = 0;
        for (int i = 0; i < keppontok.size(); i++) {
            if (keppontok.get(i).sor == vizsgaltSor && keppontok.get(i).oszlop == vizsgaltOszlop) {
                vizsgaltSzin = keppontok.get(i).rgb;
                break;
            }
        }
        int hanyszorVoros = 0;
        int hanyszorZold = 0;
        int hanyszorKek = 0;
        for (int i = 0; i < keppontok.size(); i++) {
            if (keppontok.get(i).sor == vizsgaltSor && keppontok.get(i).rgb.equals(vizsgaltSzin)) {
                hanyszorSzerepelSorban++;
            }
            if (keppontok.get(i).oszlop == vizsgaltOszlop && keppontok.get(i).rgb.equals(vizsgaltSzin)) {
                hanyszorSzerepelOszlopban++;
            }
            if (keppontok.get(i).rgb.equals("255 0 0")) {
                hanyszorVoros++;
            }
            if (keppontok.get(i).rgb.equals("0 255 0")) {
                hanyszorZold++;
            }
            if (keppontok.get(i).rgb.equals("0 0 255")) {
                hanyszorKek++;
            }
        }
        System.out.println("A " + vizsgaltSzin + " színkódú szín " + hanyszorSzerepelSorban + " alkalommal szerepel a " + vizsgaltSor + " számú sorban és " + hanyszorSzerepelOszlopban + " alkalommal szerepel a " + vizsgaltOszlop + " számú oszlopban.");
//4. Állapítsa meg, hogy a vörös, kék és zöld színek közül melyik szín fordul elő legtöbbször a képen! Az (egyik) legtöbbször előforduló szín nevét írja ki a képernyőre! A színek kódjai: Vörös 255, 0, 0 Zöld 0, 255, 0 Kék 0, 0, 255
        System.out.println("4. feladat");
        ArrayList<Integer> szinek = new ArrayList<>();
        szinek.add(hanyszorVoros);
        szinek.add(hanyszorZold);
        szinek.add(hanyszorKek);
        int min = szinek.get(0);
        int max = szinek.get(0);
        for (Integer integer : szinek) {
            if (integer < min) {
                min = integer;
            }
            if (integer > max) {
                max = integer;
            }
        }
        if (max == hanyszorVoros) {
            System.out.println("Vörös");
        } else if (max == hanyszorZold) {
            System.out.println("Zöld");
        } else {
            System.out.println("Kék");
        }

//5. Készítsen 3 képpont széles, fekete színű keretet a képnek! A keretet úgy hozza létre, hogy a kép mérete ne változzon! A fekete szín kódja RGB (0, 0, 0).
        System.out.println("5. feladat");
        for (Keppont keppont : keppontok) {
            if (keppont.sor > 47 || keppont.sor < 4 || keppont.oszlop > 47 || keppont.oszlop < 4) {
                keppont.kek = 0;
                keppont.voros = 0;
                keppont.zold = 0;
                keppont.rgb = "0 0 0";
            }
        }
        for (int i = 0; i < keppontok.size(); i++) {
            System.out.println(
                    "sor: " + keppontok.get(i).sor
                    + ", oszlop: " + keppontok.get(i).oszlop
                    + ", voros: " + keppontok.get(i).voros
                    + ", zold: " + keppontok.get(i).zold
                    + ", kek: " + keppontok.get(i).kek
                    + ", RGB: " + keppontok.get(i).rgb
            );
        }
//6. A kép képpontjainak színét írja ki a keretes.txt nevű szövegfájlba a bemeneti fájl formátumával egyezően! A képet sorfolytonosan tárolja, minden képpontot új sorba, a képpontok RGB kódját szóközzel elválasztva írja ki! Például: ... 0 0 0 0 0 0 200 96 64 ... 
        System.out.println("6. feladat");
        PrintWriter output = new PrintWriter(new FileWriter("keretes.txt"));
        for (Keppont keppont : keppontok) {
            output.println(keppont.rgb);
        }
        output.close();
//7. Az 50×50-es képen a kerettől függetlenül egy sárga RGB (255, 255, 0) színű téglalap van. Határozza meg a program segítségével a bal felső és a jobb alsó sárga képpontnak a helyét (sor, oszlop), majd határozza meg, hogy a sárga téglalap hány képpontból áll! A képpontok helyét és a sárga alakzat méretét a következő formában írassa ki a képernyőre: Kezd: sor, oszlop Vége: sor, oszlop Képpontok száma: darab Például: Kezd: 18, 12 Vége: 25, 19 Képpontok száma: 64 
        System.out.println("7. feladat");
        int balFelsoSor = 0;
        int balFelsoOszlop = 0;
        boolean balFelsoMegvan = false;
        int jobbAlsoSor = 0;
        int jobbAlsoOszlop = 0;
        for (Keppont keppont : keppontok) {
            if (keppont.rgb.equals("255 255 0")) {
                if (balFelsoMegvan) {
                    jobbAlsoSor = keppont.sor;
                    jobbAlsoOszlop = keppont.oszlop;
                } else {
                    balFelsoSor = keppont.sor;
                    balFelsoOszlop = keppont.oszlop;
                    balFelsoMegvan = true;
                }
            }

        }
        System.out.println("Kezd: " + balFelsoSor + ", " + balFelsoOszlop);
        System.out.println("Vége: " + jobbAlsoSor + ", " + jobbAlsoOszlop);
    }

    private static String getInput(String prompt) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return stdin.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();

        }
    }

    private static class Keppont {

        int sor;
        int oszlop;
        int voros;
        int zold;
        int kek;
        String rgb;

        public Keppont(int sor, int oszlop, int voros, int zold, int kek, String rgb) {
            this.sor = sor;
            this.oszlop = oszlop;
            this.voros = voros;
            this.zold = zold;
            this.kek = kek;
            this.rgb = rgb;
        }
    }
}
