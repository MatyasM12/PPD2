import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolMultiplication<T> extends Thread{

    private Polinom first, second, result;
    private int start, end, numberOfThreads;

    public PolMultiplication(Polinom first, Polinom second, Polinom result, int start, int end){
        this.first = first;
        this.second = second;
        this.result = result;
        this.start = start;
        this.end = end;
    }

    public PolMultiplication(Polinom first, Polinom second, int numberOfThreads){
        this.first = first;
        this.second = second;
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public void run(){
        int realFirstEnd = end > first.getIntegerList().size() ? first.getIntegerList().size() : end;
        int realSecondEnd = end > second.getIntegerList().size() ? second.getIntegerList().size() : end;
        for(int i = start; i < realFirstEnd; i++){
            for(int j = start; j < realSecondEnd; j++){
                int current = result.getIntegerList().get(i+j);
                result.getIntegerList().set(i + j, first.getIntegerList().get(i) * second.getIntegerList().get(j) + current);
            }
        }
    }

    public double begin() throws InterruptedException {
        Thread[] th = new Thread[numberOfThreads];
        int highestGrad = first.getGrad() > second.getGrad() ? first.getGrad()+1 : second.getGrad()+1;
        int maxGrad = first.getGrad()* second.getGrad();
        int nrLinesThread = highestGrad / numberOfThreads;
        int copyNrLinesThread = nrLinesThread;
        int remained = highestGrad % numberOfThreads;
        start = 0;
        end = 0;
        result = new Polinom(first.getGrad()*second.getGrad()+1);
        List<Integer> coeff = new ArrayList<Integer>(Collections.nCopies(maxGrad, 0));
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
            th[i] = new PolMultiplication(first, second, result, start, end);
            th[i].start();
            start += nrLinesThread;
        }
        for(int i = 0; i < numberOfThreads ; i++){
            th[i].join();
        }
        Collections.reverse(result.getIntegerList());
        return (System.nanoTime() - startTime)/1000000;
    }
}
