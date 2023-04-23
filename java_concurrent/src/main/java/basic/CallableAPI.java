package basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableAPI {


    static class MatchCounter implements Callable<Integer> {


        private File directory;
        private String keyWork;

        public MatchCounter(File directory, String keyWork) {
            this.directory = directory;
            this.keyWork = keyWork;
        }

        @Override
        public Integer call() throws Exception {
            int count = 0;
            try {
                File[] files = directory.listFiles();
                List<Future<Integer>> results = new ArrayList<>();
                if (files != null) {


                    for (File file : files) {
                        if (file.isDirectory()) {
                            MatchCounter matchCounter = new MatchCounter(file, keyWork);
                            FutureTask<Integer> task = new FutureTask<>(matchCounter);
                            results.add(task);
                            Thread thread = new Thread(task);
                            thread.start();
                        } else {
                            if (search(file)) {
                                System.out.println(file.getAbsolutePath());
                                count++;
                            }
                        }
                    }

                    for (Future<Integer> result : results) {
                        try {
                            count += result.get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (InterruptedException interruptedException) {

            }
            return count;
        }

        public boolean search(File file) {
            try (Scanner in = new Scanner(file, "UTF-8")) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyWork)) {
                        found = true;
                    }
                }
                return found;
            } catch (IOException e) {
                //throw new RuntimeException(e);
                return false;
            }
        }
    }


    /**
     * E:\
     * hello
     * @param args
     */

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Entry directory.");
            String dirStr = in.nextLine();
            System.out.println("Entry keyword.");
            String keyword = in.nextLine();
            File directory = new File(dirStr);
            if (!directory.isDirectory()) {
                throw new RuntimeException("输入错误的地址信息");
            }

            MatchCounter matchCounter = new MatchCounter(directory, keyword);
            FutureTask<Integer> task = new FutureTask<>(matchCounter);
            Thread thread = new Thread(task);
            thread.start();

            try {
                System.out.println(task.get() + " match files");
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
