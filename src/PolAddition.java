import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolAddition extends Thread {

    private Polinom first, second, result;
    private int start, end, numberOfThreads;

    public PolAddition(Polinom first, Polinom second, Polinom result, int start, int end){
        super();
        this.start = start;
        this.end = end;
        this.first = first;
        this.second = second;
        this.result = result;
    }

    public PolAddition(Polinom first, Polinom second, int numberOfThreads){
        super();
        this.first = first;
        this.second = second;
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public void run() {
        for(int i = start; i < end; i++){
            if (i <= second.getGrad() && i <= first.getGrad())
                result.getIntegerList().set(i, first.getIntegerList().get(i) + second.getIntegerList().get(i));
            else {
                if (i > first.getGrad()) {
                    result.getIntegerList().set(i, second.getIntegerList().get(i));
                }
                else if (i > second.getGrad())
                    result.getIntegerList().set(i, first.getIntegerList().get(i));
            }
//            result.getIntegerList().set(i, first.getIntegerList().get(i) + second.getIntegerList().get(i));
        }
    }

    public double begin() throws InterruptedException {
        Thread[] th = new Thread[numberOfThreads];
        int nrLinesThread = first.getIntegerList().size() / numberOfThreads;
        int copyNrLinesThread = nrLinesThread;
        int remained = first.getIntegerList().size() % numberOfThreads;
        start = 0;
        end = 0;
        //C = new T[A.length][A[0].length];
        result = new Polinom(first.getGrad()+second.getGrad()+1);
        int maxGrad = first.getGrad() > second.getGrad() ? first.getGrad() : second.getGrad();
        List<Integer> coeff = new ArrayList<Integer>(Collections.nCopies(maxGrad+1, 0));
        result.setIntegerList(coeff);
        double startTime = System.nanoTime();
        for (int i = 0; i < numberOfThreads; i++) {
            if (remained != 0) {
                nrLinesThread = copyNrLinesThread + 1;
                remained--;
            } else {
                nrLinesThread = copyNrLinesThread;
            }
            end += nrLinesThread;
            th[i] = new PolAddition(first, second, result, start, end);
            th[i].start();
            start += nrLinesThread;
        }
        for(int i = 0; i < numberOfThreads ; i++){
            th[i].join();
        }
//        System.out.println(result.getIntegerList());
        return (System.nanoTime() - startTime)/1000000;
    }

    public Polinom getResult() {
        return result;
    }
}
