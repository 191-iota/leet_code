public class DefuseTheBomb {
    public static void main(String[] args) {

    }

    // Attempt 3 (works)

    // Defuse the Bomb
    // Create the array of twice the size
    // Then append the initial array to the first one
    // Determine in a loop which method to use
    // Create the sums using the circular array
    // Append the sums either from the start or from the bottom

    public int[] decrypt3(int[] code, int k) {
        int[] circular = new int[2 * code.length];
        int[] result = new int[code.length];
        int sum = 0;

        // fill up standard array two times
        for (int i = 0; i < code.length; i++) {
            circular[i] = code[i];
            circular[i + code.length] = code[i];
        }

        for (int currentIndex = 0; currentIndex < code.length; currentIndex++) {
            sum = 0;
            if(k > 0) {
                for (int i = currentIndex + 1; i <= k + currentIndex; i++) {
                    sum += circular[i];
                }
                result[currentIndex] = sum;
            } else if(k < 0) {
                int currentIteration = 0;
                for (int i = code.length - 1; i >= code.length - Math.abs(k); i--) {
                    sum += circular[circular.length - currentIteration - currentIndex - 2];
                    currentIteration++;
                }
                result[code.length - currentIndex - 1] = sum;
            }
        }

        return result;
    }

    // Attempt 2 (doesn't work)
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

    // Attempt 1 (doesn't work)
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
