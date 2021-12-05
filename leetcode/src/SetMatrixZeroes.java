import java.util.*;

public class SetMatrixZeroes {

    private int rowLen;
    private int colLen;

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        new SetMatrixZeroes().setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    public enum LineType
    {
        ROW, COLUMN;

        public LineType getCorrespondingLineType()
        {
            switch (this)
            {

                case ROW -> {
                    return COLUMN;
                }
                case COLUMN -> {
                    return ROW;
                }
                default -> throw new IllegalStateException("Unexpected value: " + this);
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        Map<LineType, Set<Integer>> coveredLines = new HashMap<>();
        coveredLines.put(LineType.ROW, new HashSet<>());
        coveredLines.put(LineType.COLUMN, new HashSet<>());

        rowLen = matrix.length;
        for (int i = 0; i < rowLen; i++)
        {
            colLen = matrix[i].length;
            for (int j = 0; j < colLen; j++)
            {
//                System.out.println(i + "," + j);
//                System.out.println(coveredLines);
//                System.out.println(Arrays.deepToString(matrix));

                if (coveredLines.get(LineType.ROW).contains(i) || coveredLines.get(LineType.COLUMN).contains(j))
                {
                    continue;
                }

                if (matrix[i][j] == 0)
                {
                    handleLine(LineType.ROW, matrix, i, coveredLines, j+1);

                    if (!coveredLines.get(LineType.COLUMN).contains(j))
                    {
                        handleLine(LineType.COLUMN, matrix, j, coveredLines, i+1);
                    }
                }
            }
        }
    }

    private void handleLine(LineType lineType, int[][] matrix, int lineIdx, Map<LineType, Set<Integer>> coveredLines, int startIdx)
    {
//        System.out.println(lineType.toString() + lineIdx);
        coveredLines.get(lineType).add(lineIdx);

        for (int i = 0; i < startIdx; i++)
        {
            switch (lineType)
            {
                case ROW -> matrix[lineIdx][i] = 0;
                case COLUMN -> matrix[i][lineIdx] = 0;
            }
        }

        int lineLen = lineType == LineType.ROW ? colLen : rowLen;
        for (int i = startIdx; i < lineLen; i++)
        {
            int cell = lineType == LineType.ROW ? matrix[lineIdx][i] : matrix[i][lineIdx];
            if (cell == 0)
            {
                LineType correspondingLineType = lineType.getCorrespondingLineType();
                if (!coveredLines.get(correspondingLineType).contains(i)) {
                    handleLine(correspondingLineType, matrix, i, coveredLines, 0);
                }
            }
            else
            {
                switch (lineType)
                {
                    case ROW -> matrix[lineIdx][i] = 0;
                    case COLUMN -> matrix[i][lineIdx] = 0;
                }
            }
        }
    }
}
