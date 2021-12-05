import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortedCharacterFrequency {
        public String frequencySort(String s) {

            Map<Character, Long> freqMap = s.chars()
                    .mapToObj(i -> (char) i)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            StringBuilder stringBuilder = new StringBuilder();

            Comparator<Map.Entry<Character, Long>> entryComparator = Map.Entry.comparingByValue();

            freqMap.entrySet().stream()
                    .sorted(entryComparator.reversed())
                    .forEach(entry ->
                    {
                        for (int i = 0; i < entry.getValue(); i++)
                        {
                            stringBuilder.append(entry.getKey());
                        }
                    });

            return stringBuilder.toString();
        }
}
