public class DefuseTheBomb {
    public static void main(String[] args) {

    }

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
}
