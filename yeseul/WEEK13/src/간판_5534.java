import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 간판_5534 {
  public static void main(String[] args) throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(bufferedReader.readLine());
    String sign = bufferedReader.readLine();
    int count = 0;

    for (int i = 0; i < N; i++) {
      String oldSign = bufferedReader.readLine();
      Loop1 :
      for (int windowSize = 1; windowSize <= (oldSign.length() - 1) / (sign.length() - 1); windowSize++) {
        for (int startIdx = 0; startIdx < oldSign.length() - (sign.length() - 1) * windowSize; startIdx++) {
          for (int j = 0; j < sign.length(); j++) {
            if (sign.charAt(j) != oldSign.charAt((j * windowSize) + startIdx))
              break;
            else if (j == sign.length() - 1) {
              count++;
              break Loop1;
            }
          }
        }
      }
    }

    System.out.println(count);
  }
}
