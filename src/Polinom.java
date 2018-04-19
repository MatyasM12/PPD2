import java.util.ArrayList;
import java.util.List;

public class Polinom {
    private int grad;
    private List<Integer> integerList;

    public Polinom(int grad){
        this.grad = grad;
        integerList = new ArrayList<>();
    }
    public int getGrad() {
        return grad;
    }

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    @Override
    public String toString(){
        return grad + " " + integerList;
    }
}
