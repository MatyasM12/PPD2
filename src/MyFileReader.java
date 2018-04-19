import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {

    public List<Polinom> readFromFile(String filename) throws IOException {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(new File(filename)));
            String rawPol = br.readLine();
            int grad_one = Integer.valueOf(rawPol.split("\\^")[1].split("[+-]")[0].trim());
            rawPol = br.readLine();
            int grad_two = Integer.valueOf(rawPol.split("\\^")[1].split("[+-]")[0].trim());
        }catch(IOException ex){
            ex.printStackTrace();
        } finally {
            br.close();
        }
        return null;
    }

    public List<Polinom> readPolFromFile(String filename) throws IOException {
        List<Polinom> polinoms = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String[] coefficients_one = br.readLine().split(" ");
            int grad_one = coefficients_one.length-1;
            Polinom first = new Polinom(grad_one);
            for(String s : coefficients_one){
                first.getIntegerList().add(Integer.valueOf(s));
            }
            String[] coefficients_two = br.readLine().split(" ");
            int grad_two = coefficients_two.length-1;
            Polinom second = new Polinom(grad_two);
            for(String s : coefficients_two){
                second.getIntegerList().add(Integer.valueOf(s));
            }
            polinoms.add(first);
            polinoms.add(second);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

        return polinoms;
    }

    public void writeToFile(String filename, Polinom polinom) {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(filename));
            br.write(polinom.getGrad()+"");
            br.newLine();
            for(int no : polinom.getIntegerList()){
                br.write(no+" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
