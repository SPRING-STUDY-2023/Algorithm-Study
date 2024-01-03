import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class 추월_2002 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int size = scanner.nextInt();
    int cnt = 0;

    Map<String, Integer> inTunnel = new HashMap<>(); // 터널에 들어간 순서를 해시맵으로 저장해놓기
    Vector<String> outTunnel = new Vector<>(); // 터널에서 나온 순서 저장

    // 터널에 들어간 순서 입력 받기
    for (int i = 0; i < size; i++) {
      String s = scanner.next();
      inTunnel.put(s, i);
    }

    // 터널에서 나온 순서대로 입력 받기
    for (int i = 0; i < size; i++) {
      String temp = scanner.next();
      outTunnel.add(temp);
    }

    for (int i = 0; i < size; i++) {
      for (int j = i + 1; j < size; j++) {

        // 터널에서 나올 때 뒤에 있는 차들과 비교 => 들어온 순서와 다르면 추월한 차
        if (inTunnel.get(outTunnel.get(i)) > inTunnel.get(outTunnel.get(j))) {
          cnt += 1;
          break;
        }
      }
    }

    System.out.println(cnt);
  }
}
