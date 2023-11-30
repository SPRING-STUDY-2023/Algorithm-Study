public class 프로그래머스_문자열압축_60057 {
    public static void main(String[] args) throws Exception {
    }

    public int solution(String s) {
        int answer = s.length();
        for (int sliceSize = 1; sliceSize <= s.length()/2; sliceSize++) {
            StringBuilder newString = new StringBuilder();
            String beforeSliceString = "";
            int duplicateNum = 1;

            for (int idx = 0; idx < s.length(); idx += sliceSize) {
                String sliceString = s.substring(idx, Math.min(idx + sliceSize, s.length()));

                if (!beforeSliceString.equals("")) {
                    if (beforeSliceString.equals(sliceString)) {
                        duplicateNum++;
                    } else {
                        if (duplicateNum != 1)
                            newString.append(duplicateNum);
                        newString.append(beforeSliceString);
                        duplicateNum = 1;
                    }
                }

                if (idx + sliceSize >= s.length()) {
                    if (duplicateNum != 1)
                        newString.append(duplicateNum);
                    newString.append(sliceString);
                }

                beforeSliceString = sliceString;
            }

            if (answer > newString.length()) {
                answer = newString.length();
            }
        }

        return answer;
    }
}
