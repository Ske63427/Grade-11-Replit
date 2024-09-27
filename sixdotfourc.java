import java.util.Random;


public class sixdotfourc {
    public static void main(String[] args) {
    Random rand = new Random();

    int distance1 = 0;
    int distance2 = 0;
    int distance3 = 0;
    int distance4 = 0;
    int seconds = 1;

    int[] speed = new int[4];
    int[] distance = new int[4];

    System.out.println("Time  | R1 | R2 | R3 | R4");

    while ((distance1 <= 100) || (distance2 <= 100) || (distance3 <= 100) || (distance4 <= 100)){
      
      int num1 = rand.nextInt(2,8);
      int num2 = rand.nextInt(2,8);
      int num3 = rand.nextInt(2,8);
      int num4 = rand.nextInt(2,8);

      if (distance1 <= 100){
        distance1+=num1;
      }
      if (distance2 <= 100){
        distance2+=num2;
      }
      if (distance3 <= 100){
        distance3+=num3;
      }
      if (distance4 <= 100){
        distance4+=num4;
      }

      String distance1str = Integer.toString(distance1);
      String distance2str = Integer.toString(distance2);
      String distance3str = Integer.toString(distance3);
      String distance4str = Integer.toString(distance4);

      if (distance1 < 100){
        distance1str += "m";
      } else if (distance1 >= 100) {
        distance1str = "   ";
        if (speed[0] == 0){
          speed[0] = seconds;
          distance[0] = distance1;
        }
      }
      
      if (distance2 < 100){
        distance2str += "m";
      } else if (distance2 >= 100) {
        distance2str = "   ";
        if (speed[1] == 0){
          speed[1] = seconds;
          distance[1] = distance2;
        }
      }
      
      if (distance3 < 100){
        distance3str += "m";
      } else if (distance3 >= 100) {
        distance3str = "   ";
        if (speed[2] == 0){
          speed[2] = seconds;
          distance[2] = distance3;
        }
      }
      
      if (distance4 < 100){
        distance4str += "m";
      } else if (distance4 >= 100) {
        distance4str = "   ";
        if (speed[3] == 0){
          speed[3] = seconds;
          distance[3] = distance4;
        }
      }

      System.out.println("t = "+seconds+" | "+distance1str+"   "+distance2str+"   "+distance3str+"   "+distance4str);
      seconds++;
    }
    double d1 = distance[0];
    double s1 = speed[0];
    double avgSpeed1 = d1/s1;

    double d2 = distance[1];
    double s2 = speed[1];
    double avgSpeed2 = d2/s2;
    
    double d3 = distance[2];
    double s3 = speed[2];
    double avgSpeed3 = d3/s3;
    
    double d4 = distance[3];
    double s4 = speed[3];
    double avgSpeed4 = d4/s4;
        
    int[] order = {1, 2, 3, 4};
    for (int i = 0; i < 4; i++) {
      for (int j = i + 1; j < 4; j++) {
        if (speed[j] < speed[i]) {
          int idk = speed[i];
          speed[i] = speed[j];
          speed[j] = idk;
          idk = distance[i];
          distance[i] = distance[j];
          distance[j] = idk;
          idk = order[i];
          order[i] = order[j];
          order[j] = idk;
        }
      }
    }

    System.out.print("            @-"+order[0]+"-@\n"+
                     "            | @ |\n"+
                     "      @-"+order[1]+"-@ | | |\n"+
                     "      | @ | | | |\n"+
                     "      | | | | | | @-"+order[2]+"-@\n"+
                     "@-"+order[3]+"-@ | | | | | | | @ |\n"+
                     "| @ | | | | | | | | | |\n"+
                     "|4th| |2nd| |1st| |3rd| (Do I get extra points for art)");
    System.out.println("\n");
    System.out.println("Runner 1 Average Speed: "+String.format("%.2f", avgSpeed1)+" m/s");
    System.out.println("Runner 2 Average Speed: "+String.format("%.2f", avgSpeed2)+" m/s");
    System.out.println("Runner 3 Average Speed: "+String.format("%.2f", avgSpeed3)+" m/s");
    System.out.println("Runner 4 Average Speed: "+String.format("%.2f", avgSpeed4)+" m/s");
  }
}