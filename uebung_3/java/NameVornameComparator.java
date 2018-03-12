/**
* Vergleicht Studenten anhand von Name und Vorname.
*/

public class NameVornameComparator implements java.util.comparator<StudentIn>{
  private String namevornameA, namevornameB;

  public NameVornameComparator(){
    namevornameA = combineNames(a);
    namevornameB = combineNames(b);
  }

  //test-main methode --> JUnit sucks
  public void main(String[] args){
    
  }

  /**
  * liefert:    -1  a ist vor b im Alphabet
  *             0   a ist gleich b
  *             1   b ist vor a im Alphabet
  * --> Name vor Vorname, also z.b Mueller Nicolas
  */
  public int compare(StudentIn a, StudentIn b) {
    if()
  }

  private String combineNames(StudentIn std){
    return std.getName().concat(std.getVorname).trim().toLowerCase();
  }
}
