import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FizzBuzz {

    public int l;
    public int count;
    public StringBuilder result = new StringBuilder(" ");
    List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizBuz = new FizzBuzz(20);
        Thread threadA = new Thread(() -> {
            try {
                fizBuz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            try {
                fizBuz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadB.start();

        Thread threadC = new Thread(() -> {
            try {
                fizBuz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadC.start();

        Thread threadD = new Thread(() -> {
            try {
                fizBuz.mumber();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadD.start();
        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
        System.out.println(fizBuz.show(fizBuz.list));
    }

    public StringBuilder getResult() {
        return result;
    }

    public FizzBuzz(int l) {
        this.l = l;
        this.count = 1;
    }

    synchronized public void fizz() throws InterruptedException {
        while (count <= l) {
            if (count % 3 == 0 && count % 5 != 0) {
                count++;
                list.add("fizz");
                notifyAll();
            } else {
                wait();
            }
        }
    }

    synchronized public void buzz() throws InterruptedException {
        while (count <= l) {
            if (count % 5 == 0 && count % 3 != 0) {
                count++;
                list.add("buzz");
                notifyAll();
            } else {
                wait();
            }
        }
    }

    synchronized public void fizzbuzz() throws InterruptedException {
        while (count <= l) {
            if (count % 15 == 0) {
                count++;
                list.add("FizzBuzz");
                notifyAll();
            } else {
                wait();
            }
        }
    }

    synchronized public void mumber() throws InterruptedException {
        while (count <= l) {
            if (count % 3 == 0 || count % 5 == 0) {
                wait();
            } else {
                list.add(count + "");
                count++;
                notifyAll();
            }
        }
    }

    public String show(List<String> list) {
        return list.stream().collect(Collectors.joining(","));
    }
}
