import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchMaking {
    private List<Individual> individuals=  new ArrayList<>();
    private final String MATCH_TYPE_DISTANCE = "distance";
    private final String MATCH_TYPE_HABIT = "habit";
    private String type;
    private Map<Individual, Individual> individualMatchMap = new HashMap<>();

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setMatchType(String _type) {
        this.type = _type;
    }

    public Map<Individual, Individual> getIndividualMatchMap() {
        return individualMatchMap;
    }

    public void match(boolean isReverse) {
        for (Individual i : individuals) {
            individualMatchMap.put(i, findBestMatch(i));
        }
    }

    private Individual findBestMatch(Individual i) {
        Individual result = null;
        if (MATCH_TYPE_DISTANCE.equals(type)) {
            Map<Individual, Double> matchMap = new HashMap<>();
            for (Individual j : individuals) {
                if (i != j) {
                    double distance = i.getCoords().calcDistance(j.getCoords());
                    matchMap.put(j, distance);
                }
            }
            Stream<Map.Entry<Individual,Double>> sorted = matchMap.entrySet().stream().sorted(Map.Entry.comparingByValue());
            result = Objects.requireNonNull(sorted.findFirst().orElse(null)).getKey();
        } else {
            Map<Individual, Integer> matchMap = new HashMap<>();
            for (Individual j : individuals) {
                if (i != j) {
                    List<String> matchList = i.getHabits().stream()
                            .filter(j.getHabits()::contains)
                            .toList();
                    int count = matchList.size();
                    matchMap.put(j, count);
                }
            }
            Stream<Map.Entry<Individual,Integer>> sorted = matchMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            result = Objects.requireNonNull(sorted.findFirst().orElse(null)).getKey();
        }
        return result;
    }
}
