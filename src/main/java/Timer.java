public class Timer {
    private volatile int vol = 0;
    private final Object observer = new Object();

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        Thread t1 = new Thread(() -> {
            timer.displayTime();
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            timer.showMessage();
        });
        t2.start();
    }

    public void displayTime() {
        while (true) {
            synchronized (observer) {
                try {
                    Thread.sleep(1000);
                    observer.notifyAll();
                    vol++;
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            System.out.println("Started: " + vol + "secs");
        }
    }

    public void showMessage() {
        while (true) {
            if (vol != 0 && vol % 5 == 0) {
                synchronized (observer) {
                    try {
                        System.out.println(">>>5 seconds passed");
                        observer.wait();
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }
                }
            }
        }
    }


}
