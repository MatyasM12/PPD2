import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;

public class Generator {

    public Polinom generatePolinome(int size){
        Random random = new Random();
        Polinom polinom = new Polinom(size);
        for(int i = 0; i < size; i++){
            polinom.getIntegerList().add(new Integer(random.nextInt(150)));
        }
        return polinom;
    }

    public void writePolinome(Polinom first, Polinom second){
        String filename = "input.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            writeM(first, bw);
            bw.newLine();

            writeM(second, bw);
            bw.flush();

        } catch (IOException e) {}
    }

    private void writeM(Polinom first, BufferedWriter bw) throws IOException {
        for (int i = 0; i < first.getIntegerList().size(); i++) {
                bw.write( first.getIntegerList().get(i) + ",");
            }
        bw.newLine();
    }

}
