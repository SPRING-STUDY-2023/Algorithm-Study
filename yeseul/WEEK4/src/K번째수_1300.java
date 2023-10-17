import java.util.*;

public class K번째수_1300 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int k = scan.nextInt();

        int l = 1;
        int r = k;
        int result = 0;
        while(l <= r) {
            int mid = (l + r) / 2;
            long count = 0;

            for(int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }

            if(count < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
                result = mid;
            }
        }
        System.out.println(result);
    }
}