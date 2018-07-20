package httpIvk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DEllComputer on 2018/4/27.
 */
public class RunnableTest implements Runnable{
    private  List<Integer> indexList = new ArrayList<Integer>();


    public RunnableTest(List<Integer> indexList){
        this.indexList = indexList;
    }

    public void run() {
        System.out.println();
    }
}
