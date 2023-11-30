public class 프로그래머스_K진수에서소수개수구하기_92335 {
    public int solution(int n, int k) {
        int answer = 0;
        String convertedNum = convertToBaseK(n, k);

        int j = 0;
        for (int i = 0; i < convertedNum.length() - 1; i = j) {
            for (j = i + 1; j < convertedNum.length() && convertedNum.charAt(j) != '0'; j++) ;
            if (isPrime(Long.parseLong(convertedNum.substring(i, j))))
                answer++;
        }
        return answer;
    }

    private static boolean isPrime(long num) {
        if (num <= 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static String convertToBaseK(int n, int k) {
        StringBuilder str = new StringBuilder();

        while (n > 0) {
            str.append(n % k);
            n /= k;
        }

        str = str.reverse();
        return str.toString();
    }
}
