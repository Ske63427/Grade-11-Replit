import java.util.Scanner;

public class fivedotfour {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.print("What symbol would you like to print?: ");
    String x = input.nextLine();
    
    int[] columns = new int[5];
    for (int i = 0; i<5; i++){
      System.out.print("How many times would you like to see it?: ");
      columns[i] = input.nextInt();
      input.close();
    }
    
    int biggestNumber = columns[0];
    for (int i=1; i<5; i++){
      if (columns[i] > biggestNumber){
        biggestNumber = columns[i];
      }
    }

    System.out.println();
    
    for (int i = biggestNumber; i >= 1; i--) {
      for (int j = 0; j < 5; j++) {
          if (columns[j] >= i) {
              System.out.print(x+ " ");
          } else {
              System.out.print("  "); 
            }
      }
      System.out.println();
    }

  }
}