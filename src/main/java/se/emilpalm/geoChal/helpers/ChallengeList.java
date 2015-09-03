package se.emilpalm.geoChal.helpers;

import java.util.List;

/**
 * Created by emil on 2015-09-03.
 */
public class ChallengeList {

    private List<Challenge> challenges;

    public ChallengeList() {}

    public ChallengeList(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
}
