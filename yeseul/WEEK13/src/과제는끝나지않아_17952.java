import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.Stack;

class HomeworkInfo {
  int score;
  int time;

  public HomeworkInfo(int score, int time) {
    this.score = score;
    this.time = time;
  }

  public void minusTime() {
    this.time--;
  }
}

public class 과제는끝나지않아_17952 {
  public static void main(String[] args) throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    int totalTime = Integer.parseInt(bufferedReader.readLine());
    Stack<HomeworkInfo> stack = new Stack<>();
    int result = 0;

    for (int i = 0; i < totalTime ; i++) {
      String[] homework = bufferedReader.readLine().split(" ");

      if ( Integer.parseInt(homework[0]) == 1 ) {
          HomeworkInfo homeworkInfo = new HomeworkInfo(Integer.parseInt(homework[1]), Integer.parseInt(homework[2]) -1);
          if (homeworkInfo.time == 0 ) {
            result += homeworkInfo.score;
            continue;
          }
          stack.push(homeworkInfo);

      } else {
        if (!stack.empty()) {
          HomeworkInfo currentHomework = stack.pop();
          currentHomework.minusTime();
          if (currentHomework.time == 0) {
            result += currentHomework.score;
          } else {
            stack.push(currentHomework);
          }
        }
      }
    }

    System.out.println(result);


  }

}
