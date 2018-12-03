package DZ34;


public class Main {

    static Object monitor = new Object();
    static volatile char currentChar = 'A';

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (monitor) {
                            while (currentChar != 'A') {
                                monitor.wait();
                            }Thread.sleep(1000);
                            System.out.print(currentChar + " ");
                            currentChar = 'B';
                            monitor.notifyAll();

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (monitor) {
                            while (currentChar != 'B') {
                                monitor.wait();
                            } Thread.sleep(500);
                            System.out.print(currentChar + " ");
                            currentChar = 'C';
                            monitor.notifyAll();

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (monitor) {
                            while (currentChar != 'C') {
                                monitor.wait();
                            }Thread.sleep(500);
                            System.out.println(currentChar + " ");
                            currentChar = 'A';
                            monitor.notifyAll();

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}











