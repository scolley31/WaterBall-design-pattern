import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void whenStartMatchSystem_thenGenerateTwoIndividual() {
        MatchMaking matchmaking = new MatchMaking();
        matchmaking.setIndividuals(initIndividuals());
        List<Individual> c = matchmaking.getIndividuals();
        assertEquals(2, c.size());
    }

    @Test
    void givenTwoCoords00And34_whenCalcDistance_thenCalculateIndividualDistancesWillBe_5() {
        MatchMaking matchmaking = new MatchMaking();
        matchmaking.setMatchType("Distance");
        matchmaking.setIndividuals(initIndividuals());

        List<Individual> individuals = matchmaking.getIndividuals();
        Individual boy = individuals.get(0);
        Individual girl = individuals.get(1);
        double distance1 = boy.getCoords().calcDistance(girl.getCoords());
        double distance2 = girl.getCoords().calcDistance(boy.getCoords());
        assertEquals(5, distance1);
        assertEquals(5, distance2);
    }

    @Test
    void givenIndividualsID123AndEachCoordsAre00_34_010_whenMatchTypeAreDistanceAndNotReverse_thenId1MatchmakingResultIsId2() {
        MatchMaking matchmaking = new MatchMaking();
        matchmaking.setMatchType("distance");
        matchmaking.setIndividuals(initIndividuals());

        List<Individual> individuals = matchmaking.getIndividuals();
        matchmaking.match(false);
        Map<Individual, Individual> map = matchmaking.getIndividualMatchMap();
        Individual matchResult = map.get(individuals.get(0));
        assertEquals(matchResult.getId(), 2);
    }

    @Test
    void givenIndividualsID123AndSomeHabits_whenMatchTypeAreHabitsAndNotReverse_thenId1MatchmakingResultIsId3() {
        MatchMaking matchmaking = new MatchMaking();
        matchmaking.setMatchType("habit");
        matchmaking.setIndividuals(initIndividuals());

        List<Individual> individuals = matchmaking.getIndividuals();
        matchmaking.match(false);
        Map<Individual, Individual> map = matchmaking.getIndividualMatchMap();
        Individual matchResult = map.get(individuals.get(0));
        assertEquals(matchResult.getId(), 3);
    }

    public List<Individual> initIndividuals() {
        List<Individual> result = new ArrayList<>();
        Individual a = new IndividualBuilder()
                .setCoords(new Coords(0, 0))
                .setId(1)
                .setHabits(new String[]{"gym", "netflix"})
                .createIndividual();
        Individual b = new IndividualBuilder()
                .setCoords(new Coords(3, 4))
                .setHabits(new String[]{"hiking", "netflix"})
                .setId(2)
                .createIndividual();
        Individual c = new IndividualBuilder()
                .setCoords(new Coords(0, 10))
                .setHabits(new String[]{"coding", "netflix", "gym"})
                .setId(3)
                .createIndividual();
        result.add(a);
        result.add(b);
        result.add(c);
        return result;
    }
}