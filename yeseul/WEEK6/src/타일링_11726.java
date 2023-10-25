import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 타일링_11726 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> dp = new ArrayList<>();
        dp.add(1);
        dp.add(2);
        for (int i = 2; i < n; i++) {
            dp.add((dp.get(i - 1) + dp.get(i - 2)) % 10007);
        }

        System.out.println(dp.get(n - 1));
    }
}
