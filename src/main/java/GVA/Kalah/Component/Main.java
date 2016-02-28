//package GVA.Kalah.Service;
//
//import GVA.Kalah.Component.KalahGame;
//import GVA.Kalah.Exception.EmptyPitException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Random;
//
//public class Main {
//    public static void main(String[] args) {
//        KalahGame kalahGame = new KalahGame();
//        kalahGame.addPlayers("one", "two");
//        System.out.println(kalahGame);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        Random random = new Random();
//        while (! kalahGame.isFinished()) {
//            boolean flag = false;
//            do {
//                String line;
//                try {
//                    line = reader.readLine();
//                } catch (IOException ex) {
//                    System.out.println(ex);
//                    break;
//                }
//                try {
//                    int pit = Integer.valueOf(line);
//                    flag = kalahGame.makeMove(pit, "one");
//                    System.out.println("one move");
//                    System.out.println(kalahGame);
//                } catch (EmptyPitException ex){
//                    flag = true;
//                    System.out.println(ex);
//                }
//            }
//            while (flag);
//            do {
//                try {
//                    int pit = random.nextInt(5);
//                    flag = kalahGame.makeMove(pit, "two");
//                    System.out.println(pit);
//                    System.out.println("two move");
//                    System.out.println(kalahGame);
//                } catch (EmptyPitException ex){
//                    flag = true;
//                    System.out.println(ex);
//                }
//
//            }
//            while (flag);
//        }
//        System.out.println(kalahGame.winMessage());
//    }
//}
