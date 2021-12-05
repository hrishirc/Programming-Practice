import java.util.Arrays;

public class MaxRainWater {

    public static void main(String[] args) {
        System.out.println(new MaxRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap(int[] height) {
        int totalWater = 0;

        int[] biggestBlockToTheRight = new int[height.length];
        int currMaxBlock = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            biggestBlockToTheRight[i] = currMaxBlock;
            currMaxBlock = Math.max(height[i], currMaxBlock);
        }

        int maxCurrPoolHeight = 0;
        for (int i = 0; i < height.length; i++) {

            int currBlockHeight = height[i];

            if (maxCurrPoolHeight == 0)
            {
                /*
                    1) Ignore unbounded areas at the start
                    2) If no pool exists, create pool and continue
                */

                if (currBlockHeight != 0)
                {
                    int biggestBlockToTheRightCurr = biggestBlockToTheRight[i];
                    maxCurrPoolHeight = Math.min(currBlockHeight, biggestBlockToTheRightCurr);
                }

                continue;
            }

            /*
                if pool exists, add trapped water
             */
            int addedWater = maxCurrPoolHeight - currBlockHeight;
            if (addedWater > 0) {
                totalWater += addedWater;
            }
            else
            {
                int biggestBlockToTheRightCurr = biggestBlockToTheRight[i];
                maxCurrPoolHeight = Math.min(currBlockHeight, biggestBlockToTheRightCurr);
            }
        }

        return totalWater;
    }
}
