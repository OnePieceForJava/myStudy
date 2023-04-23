package util.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinAPI {

    public static void main(String[] args) {
        forkjoinTest();
    }

    private static void forkjoinTest(){
        /*ForkJoinPool forkJoinPool = new ForkJoinPool();
        SplitBigTask splitBigTask = new SplitBigTask(1,100);
        Integer i = forkJoinPool.invoke(splitBigTask);
        System.out.println(i);*/
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        DirPrintAction dirPrintAction = new DirPrintAction("E:\\");
        //forkJoinPool.invoke(dirPrintAction);
        /*forkJoinPool.execute(dirPrintAction);
        dirPrintAction.join();*/
        forkJoinPool.submit(dirPrintAction);
    }

    //拆分


    private static class SplitBigTask extends RecursiveTask<Integer>{
        private static int compute_size = 10;
        private int start;
        private int end;
        SplitBigTask(int start,int end){
            if(start>=end){
                throw new RuntimeException("开始累加数字要小于累加结束数字");
            }
            this.start = start;
            this.end = end;
        }


        @Override
        protected Integer compute() {
            if(end-start<compute_size){
                int sum = 0;
                for(int i =start;i<=end;i++){
                    sum += i;
                }
                return sum;
            }else{
               int middle = (end+start)/2;
               SplitBigTask left = new SplitBigTask(start,middle);
               SplitBigTask right = new SplitBigTask(middle+1,end);
               left.fork();
               right.fork();
               return left.join()+right.join();
            }
        }
    }


    private static class DirPrintAction extends RecursiveAction{

        private String dirPath;
        public DirPrintAction(String dirPath){
            this.dirPath = dirPath;
            File file = new File(dirPath);
            if(!file.isDirectory()){
                throw new RuntimeException("请输入正确的文件目录");
            }
        }

        @Override
        protected void compute() {
            List<DirPrintAction> subTasks = new ArrayList<>();
            File rootFile = new File(dirPath);
            File[] files = rootFile.listFiles();
            if(files==null){
                return;
            }
            for(File file :files){
                if(file.isDirectory()){
                    DirPrintAction dirPrintAction = new DirPrintAction(file.getAbsolutePath());
                    subTasks.add(dirPrintAction);
                }else{
                    if(file.getName().endsWith(".exe")){
                        System.out.println(file.getAbsolutePath());
                    }
                }
            }
            for(DirPrintAction dirPrintAction :invokeAll(subTasks)){
                dirPrintAction.join();
            }
        }
    }
}
