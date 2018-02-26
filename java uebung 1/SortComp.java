import java.util.*;

public class SortComp{

  public static void main (String[] args){
    System.out.println(insertionTime());
  }

  private static int[] createArray(int length){
    Random random = new Random();
    int[] array = new int[length];
    for(int i = 0; i < array.length; i++){
      array[i] = random.nextInt(100) + 1;
    }
    return array;
  }

  private static long insertionTime(){
    int[] array = createArray(100000);
    Timer time = new Timer();
    time.reset();
    Sorting.insertionSort(array);
    return time.timeElapsed();
  }

/*  private static long mergeTime(){

  }
*/
}
