public class DefuseTheBomb {
    public static void main(String[] args) {

    }

    // Attempt 1 (doesn't dork)
    public int[] decrypt(int[] code, int k) {
        int[] circular = new int[2 * code.length];
        int[] result = new int[code.length];
        int currentIndex = 0;
        int sum = 0;

        while (currentIndex < code.length) {
            if(k > 0) {

                for (int i = currentIndex + 1; i <= k; i++) {
                    sum += circular[i];
                }

            } else if(k < 0) {

                for (int i = code.length - 1; i >= code.length - Math.abs(k); i--) {
                    sum -= circular[i];
                }
            }
            result[currentIndex] = sum;
            currentIndex++;
        }
        return result;
    }

    // Attempt 2 (doesn't dork)
    public int[] decrypt2(int[] code, int k) {
        int[] circular = new int[2 * code.length];
        int[] result = new int[code.length];
        int currentIndex = 0;
        int sum = 0;

        // fill up circular two times
        for (int i = 0; i < code.length; i++) {
            circular[i] = code[i];
            circular[i + code.length] = code[i];
        }

        while (currentIndex < code.length) {
            if(k > 0) {

                for (int i = currentIndex + 1; i <= k + currentIndex; i++) {
                    sum += circular[i];
                }

            } else if(k < 0) {
                int currentIteration = 0;
                for (int i = code.length - 1; i >= code.length - Math.abs(k); i--) {
                    sum += circular[circular.length - currentIteration - currentIndex - 1];
                    currentIteration++;
                }
            }
            result[currentIndex] = sum;
            currentIndex++;
            sum = 0;
        }
        return result;
    }
}
