public class 콜라문제_132267 {
  public int solution(int a, int b, int n) {
    int answer = 0;

    while (true) {
      if (n < a) { // 가지고 있는 병이 a보다 작으면 못 받음
        break;
      }

      answer += ( n / a ) * b;
      int remain = n % a ;

      n = ( n / a ) * b + remain;
    }

    return answer;
  }

}
