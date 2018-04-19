import java.util.Collections;

public class Operation {

    public Polinom multiply(Polinom first, Polinom second) {

        int maxGrad = first.getGrad() > second.getGrad() ? first.getGrad() : second.getGrad();
        Polinom polinom = new Polinom(maxGrad);

        for (int i = 0; i < first.getIntegerList().size() + second.getIntegerList().size() - 1; i++) {
            polinom.getIntegerList().add(0);
        }

        for (int i = 0; i < first.getIntegerList().size(); i++) {
            for (int j = 0; j < second.getIntegerList().size(); j++) {
                int current = polinom.getIntegerList().get(i + j);
                polinom.getIntegerList().set(i + j, first.getIntegerList().get(i) * second.getIntegerList().get(j) + current);
            }
        }
        polinom.setGrad(polinom.getIntegerList().size());
        Collections.reverse(polinom.getIntegerList());
        return polinom;

    }

    public Polinom addition(Polinom first, Polinom second) {
        int maxGrad = first.getGrad() > second.getGrad() ? first.getGrad() : second.getGrad();
        Polinom result = new Polinom(maxGrad);

        for (int i = 0; i <= maxGrad; i++) {
            result.getIntegerList().add(0);
        }
        for (int i = 0; i <= maxGrad; i++) {
            if (i <= first.getGrad() && i <= second.getGrad())
                result.getIntegerList().set(i, first.getIntegerList().get(i) + second.getIntegerList().get(i));
            else {
                if (i > first.getGrad())
                    result.getIntegerList().set(i, second.getIntegerList().get(i));
                else if (i > second.getGrad())
                    result.getIntegerList().set(i, first.getIntegerList().get(i));
            }
        }
        return result;
    }
}
