package com.yzz.thread.kno;

public class KTest {

    static class T extends Thread {
        private Knock knock;
        private int need;

        public T(Knock knock, int need) {
            this.knock = knock;
            this.need = need;
        }

        @Override
        public void run() {
            knock.doKnock(need);
        }
    }

    /**
     * 2： 1020
     * @param args
     */
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long s = System.currentTimeMillis();
        System.out.println(availableProcessors);
        Knock knock = Knock.getInstance(1100, 100);
        for (int i = 0; i < 2000; i++) {
            T t = new T(knock, 1);
            try {
                t.start();
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println(knock.getTotal() + "======================"+(e-s));
    }
}
