package cvv.project;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    private static void startTimer() {
        Thread timer = new Thread(() -> {
            try {
                for (int i = 0; i < 10_000_000; i++) {
                    System.out.println(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        timer.setDaemon(true);
        timer.start();
    }
}