import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {

    public static void main(String[] args) {
        new CircularArrayLoop().circularArrayLoop(new int[] {2,-1,1,-2,-2});
    }

    public boolean circularArrayLoop(int[] nums) {
        Set<Integer> seenIndices = new HashSet<>();

        int cycleLength = 1;

        int startIdx = getNextIdx(nums, 0);
        if (startIdx == 0)
        {
            return false;
        }

        int currIdx = startIdx;
        while(currIdx != 0)
        {
            System.out.println(currIdx);
            if (seenIndices.contains(currIdx))
            {
                return false;
            }

            seenIndices.add(currIdx);
            cycleLength++;
            currIdx = getNextIdx(nums, currIdx);
        }

        return true;
    }

    private int getNextIdx(int[] nums, int currIdx)
    {
        int arrLen = nums.length;
        int nextIdx;
        int jump = nums[currIdx];
        int actualJump;
        if (jump > 0)
        {
            if (jump == arrLen)
            {
                actualJump = 0;
            }
            else if (jump < arrLen)
            {
                actualJump = jump;
            }
            else
            {
                actualJump = jump % arrLen;
            }
        }
        else
        {
            if (-1*jump == arrLen)
            {
                actualJump = 0;
            }
            else if (-1*jump < arrLen)
            {
                actualJump = arrLen + jump;
            }
            else
            {
                actualJump = arrLen + ((-1*jump) % arrLen);
            }
        }

        nextIdx = (currIdx + actualJump) % arrLen;

        return nextIdx;
    }
}
