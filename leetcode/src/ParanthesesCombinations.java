import java.util.*;
import java.util.stream.Collectors;

public class ParanthesesCombinations {

    public static void main(String[] args) {
        System.out.println(new ParanthesesCombinations().diffWaysToCompute("2-1-1"));
    }

    public List<Integer> diffWaysToCompute(String expression) {

        int strLen = expression.length();
        int startOperatorsLen = strLen / 2;
        int startNodeLen = startOperatorsLen + 1;

        List<Operation> operators = new ArrayList<>(startOperatorsLen);
        List<Integer> nodes = new ArrayList<>(startNodeLen);

        getNodesAndOperators(expression, strLen, operators, nodes);

        int nodeLen = nodes.size();
        List<Integer>[][] resMem = new List[nodeLen][nodeLen];

        return calculate(nodes, operators, resMem, 0, nodeLen - 1);
    }

    private void getNodesAndOperators(String expression, int strLen, List<Operation> operators, List<Integer> nodes) {
        StringBuilder str = new StringBuilder();
        List<Character> legalOperators = Arrays.asList('+', '-', '*');
        for (int i = 0; i < strLen; i++) {
            Character _char = expression.charAt(i);
            if (!legalOperators.contains(_char))
            {
                str.append(_char);
            }
            else
            {
                nodes.add(Integer.valueOf(str.toString()));
                str = new StringBuilder();
                operators.add(Operation.get(_char));
            }
        }

        nodes.add(Integer.valueOf(str.toString()));
    }

    private List<Integer> calculate(List<Integer> nodes,
                                    List<Operation> operators,
                                    List<Integer>[][] resultList,
                                    int startIdx,
                                    int endIdx) {
        
        for (int currrentBlockEndIdx = startIdx; currrentBlockEndIdx <= endIdx; currrentBlockEndIdx++) {

            List<Integer> results = new ArrayList<>();
            resultList[startIdx][currrentBlockEndIdx] = results;

            // block of size one
            if (currrentBlockEndIdx == startIdx) {
                results.add(nodes.get(currrentBlockEndIdx));
            } else {

                // knownBlockEndIdx goes from startIdx to currrentBlockEndIdx computing various grouping possibilities
                for (int knownBlockEndIdx = startIdx; knownBlockEndIdx < currrentBlockEndIdx; knownBlockEndIdx++) {

                    // known block
                    List<Integer> knownResults = resultList[startIdx][knownBlockEndIdx];

                    Operation operation = operators.get(knownBlockEndIdx);

                    // unknown hence computed
                    List<Integer> possibleResults = calculate(nodes, operators, resultList, knownBlockEndIdx + 1, currrentBlockEndIdx);

                    possibleResults
                            .forEach(res ->
                                    knownResults.stream()
                                            .map(kres -> doOperation(operation, kres, res))
                                            .forEach(results::add));
                }
            }
        }

        return resultList[startIdx][endIdx];
    }

    private enum Operation
    {
        ADD,
        SUBTRACT,
        MULTIPLY;

        static Operation get(char operator)
        {
            switch (operator)
            {
                case '+':
                    return ADD;
                case '-':
                    return SUBTRACT;
                case '*':
                    return MULTIPLY;
            }

            return null;
        }
    }

    private int doOperation(Operation operation, int n1, int n2) {

        switch (operation)
        {

            case ADD -> {
                return n1 + n2;
            }
            case SUBTRACT -> {
                return n1 - n2;
            }
            case MULTIPLY -> {
                return n1 * n2;
            }
        }

        return -1;
    }
}
