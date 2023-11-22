package Week10;

public class LV3_광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = getTimeInt(play_time);
        int adv = getTimeInt(adv_time);

        int[] total = new int[300001];
        for(String log : logs) {
            String[] arr = log.split("-");
            int start = getTimeInt(arr[0]);
            int end = getTimeInt(arr[1]);

            for(int j = start; j < end; j++) {
                total[j]++;
            }
        }

        long sum = 0;
        for(int i = 0; i < adv; i++) {
            sum += total[i];
        }

        int maxTime = 0;
        long max = sum;
        for(int i = 0, j = adv; j < playSec; i++, j++) {
            sum = sum - total[i] + total[j];
            if(sum > max) {
                maxTime = i + 1; // 1초부터
                max = sum;
            }
        }
        return getTimeString(maxTime);
    }

    public int getTimeInt(String time) {
        String[] t = time.split(":");
        return (Integer.parseInt(t[0]) * 60 * 60)
                + (Integer.parseInt(t[1]) * 60) + Integer.parseInt(t[2]);
    }

    public String getTimeString(int time) {

        StringBuilder sb = new StringBuilder();
        int h = time / 3600;
        if(h < 10) sb.append("0").append(h);
        else sb.append(h);
        sb.append(":");

        time %= 3600;
        int m = time / 60;
        if(m < 10) sb.append("0").append(m);
        else sb.append(m);
        sb.append(":");

        time %= 60;
        int s = time;

        if(s < 10) sb.append("0").append(s);
        else sb.append(s);

        return sb.toString();
    }
}
