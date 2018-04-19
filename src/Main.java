import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static String SOURCE_FILE = "./src/input2.txt";
    private static String outFile = "out.txt";

    public static void main(String[] args) throws InterruptedException, IOException {
        MyFileReader myFileReader = new MyFileReader();


        Generator generator = new Generator();
        for(int i = 1; i <= 1600; i*=2) {
            writeResultToFile(outFile, "\n---Polynomial rank --- "+i+"\n");
            Polinom one = generator.generatePolinome(i);
            Polinom two = generator.generatePolinome(i);
            generator.writePolinome(one, two);
            writeResultToFile(outFile, "\nMultiplication\n----------------\n");
            for (int th = 1; th <= 64; th *= 2) {
                PolMultiplication realMultiplication = new PolMultiplication(one, two, th);
                writeResultToFile("out.txt", "Number of Threads: " + th + " Time: " + String.valueOf(realMultiplication.begin()) + "\n");
            }
        }
    }


    private static void writeResultToFile(String filename, String result){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(result);
            bw.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
