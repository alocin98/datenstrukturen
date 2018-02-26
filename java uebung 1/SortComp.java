import java.util.*;

public class SortComp{

  public static void main (String[] args){
    System.out.println(createArray(10));
  }

  private static int[] createArray(int length){
    Random random = new Random();
    int[] array = new int[length];
    for(int i = 0; i < array.length; i++){
      array[i] = random.nextInt(100) + 1;
    }
    return array;
  }

  private static long insertTime(){
    
  }

  private static long mergeTime(){

  }

}
