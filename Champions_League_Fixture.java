import java.util.*;

public class Champions_League_Fixture {

    /* EXPLANATION 
     Rules for fixtures allocation of teams in Champions League
     #1 Each team should play exactly 8 Games in league
     #2 Each team plays 2 matches in a matchday
     #3 Teams are divided into 4 pots of 9 teams each
     #4 Teams must play 2 teams from each pool
     #5 Team Should not play against a team from their own leagues
         ex:- Barcelona vs Real Madrid / Liverpool vs Arsenal is not allowed
     #6 There should not be a repeated Fixture
     #7 The League phase must have exactly 144 Matches 
    */

    public static int t = 1;
    public static Boolean ClubCheck(String st, String st2) {
        if (st.equals(st2)) {
            return true;
        }
        if (st.equals("Real Madrid") || st.equals("Atletico Madrid") || st.equals("Barcelona") || st.equals("Girona")) {
            return st2.equals("Real Madrid") || st2.equals("Atletico Madrid") || st2.equals("Barcelona")
                    || st2.equals("Girona");
        }
        if (st.equals("ManCity") || st.equals("Liverpool") || st.equals("Aston Villa") || st.equals("Arsenal")) {
            return st2.equals("ManCity") || st2.equals("Liverpool") || st2.equals("Aston Villa")
                    || st2.equals("Arsenal");
        }
        if (st.equals("Inter Milan") || st.equals("Juventus") || st.equals("AC Milan") || st.equals("Atalanta")
                || st.equals("Bologna")) {
            return st2.equals("Inter Milan") || st2.equals("Juventus") || st2.equals("AC Milan")
                    || st2.equals("Atalanta") || st2.equals("Bologna");
        }
        if (st.equals("RB Leipzig") || st.equals("VfB Stuttgart") || st.equals("Bayer Leverkusen")
                || st.equals("Dortmund") || st.equals("Bayern Munich")) {
            return st2.equals("RB Leipzig") || st2.equals("VfB Stuttgart") || st2.equals("Bayer Leverkusen")
                    || st2.equals("Dortmund") || st2.equals("Bayern Munich");
        }
        if (st.equals("Paris Saint-Germain") || st.equals("Monaco") || st.equals("Brest")) {
            return st2.equals("Paris Saint-Germain") || st2.equals("Monaco") || st2.equals("Brest");
        }
        if (st.equals("Benfica") || st.equals("Sporting CP")) {
            return st2.equals("Benfica") || st2.equals("Sporting CP");
        }
        if (st.equals("Feyenoord") || st.equals("PSV")) {
            return st2.equals("Feyenoord") || st2.equals("PSV");
        }
        if (st.equals("Sturm Graz") || st.equals("Salzburg")) {
            return st2.equals("Sturm Graz") || st2.equals("Salzburg");
        }
        return false;
    }

    // Improved shuffle method using Fisher-Yates algorithm
    public static void shuffle(String[] s1) {
        Random r = new Random();
        for (int i = 0; i < s1.length; i++) {
            int t = r.nextInt(s1.length);
            String temp = s1[i];
            s1[i] = s1[t];
            s1[t] = temp;
        }
    }

    public static boolean fixtures(HashSet<String> temp, String[] pot, HashSet<String> Matches,String[] st, int i) {
        // When all teams in the pot have been matched
        if (i >= pot.length) {
            for (int j = 0; j < st.length; j++) {
                Matches.add(st[j]);
                // Matches.add(Rst[j]);
            }
            return true;
        }

        if (temp.isEmpty()) {
            return false;
        }

        // Create a list to avoid concurrent modification issues
        List<String> tempList = new ArrayList<>(temp);
        for (String s : tempList) {
            // Skip if clubs are from the same group or if the fixture already exists
            // already exists
            if (ClubCheck(pot[i], s) || Matches.contains(pot[i] + " vs " + s ) || Matches.contains(s + " vs " + pot[i])) {
                continue;
            }

            st[i] = pot[i] + " vs " + s;
            // Rst[i] = s + " vs " + pot[i];
            temp.remove(s);

            if (fixtures(temp, pot, Matches, st, i + 1)) {
                return true;
            }

            // Backtracking: add the team back in the set if further matching fails
            temp.add(s);
        }
        return false;
    }

    public static void AllocateFixtures(int matchday, String[] p1, String[] p2, String[] p3, String[] p4,
            HashSet<String> Matches) {
        System.out.println("MATCHDAY " + (matchday + 1));
        String[] str = new String[p1.length];
        // String[] Rstr = new String[p1.length];

        // For each pairing, create a fresh temporary set.
        HashSet<String> temp1 = new HashSet<>(Arrays.asList(p1));
        if (fixtures(temp1, p1, Matches, str, 0)) {
            for (int k = 0; k < str.length; k++) {
                System.out.println("Match " + t + " -> " + str[k]);
                t++;
            }
        }
        HashSet<String> temp2 = new HashSet<>(Arrays.asList(p2));
        if (fixtures(temp2, p3, Matches, str, 0)) {
            for (int k = 0; k < str.length; k++) {
                System.out.println("Match " + t + " -> " + str[k]);
                t++;
            }
        }
        HashSet<String> temp3 = new HashSet<>(Arrays.asList(p3));
        if (fixtures(temp3, p4, Matches, str, 0)) {
            for (int k = 0; k < str.length; k++) {
                System.out.println("Match " + t + " -> " + str[k]);
                t++;
            }
        }
        HashSet<String> temp4 = new HashSet<>(Arrays.asList(p4));
        if (fixtures(temp4, p2, Matches, str, 0)) {
            for (int k = 0; k < str.length; k++) {
                System.out.println("Match " + t + " -> " + str[k]);
                t++;
            }
        }
    }

    public static void GetFixtures(String team, HashSet<String> Matches) {
        Iterator<String> it = Matches.iterator();
        while (it.hasNext()) {
            String t = it.next();
            if (t.contains(team)) {
                System.out.println(t);
            }
        }
    }

    public static void main(String[] args) {
        String[] pot1 = { "Real Madrid", "Bayern Munich", "Paris Saint-Germain", "ManCity", "Liverpool", "Inter Milan",
                "Dortmund", "RB Leipzig", "Barcelona" };
        String[] pot2 = { "Bayer Leverkusen", "Atletico Madrid", "Atalanta", "Juventus", "Benfica", "Arsenal",
                "Club Brugge", "Shakhtar Donetsk", "AC Milan" };
        String[] pot3 = { "Feyenoord", "Sporting CP", "PSV", "Dinamo Zagreb", "Salzburg", "Lille", "Red Star Belgrade",
                "Young Boys", "Celtic" };
        String[] pot4 = { "Slovan Bratislava", "Monaco", "Sparta Prague", "Aston Villa", "Bologna", "Girona",
                "VfB Stuttgart", "Sturm Graz", "Brest" };

        HashSet<String> Matches = new HashSet<>();
        shuffle(pot1);
        shuffle(pot2);
        shuffle(pot3);
        shuffle(pot4);
        AllocateFixtures(0, pot1, pot2, pot3, pot4, Matches);
        shuffle(pot1);
        shuffle(pot2);
        shuffle(pot3);
        shuffle(pot4);
        AllocateFixtures(1, pot4, pot1, pot2, pot3, Matches);
        shuffle(pot1);
        shuffle(pot2);
        shuffle(pot3);
        shuffle(pot4);
        AllocateFixtures(2, pot3, pot4, pot1, pot2, Matches);
        shuffle(pot1);
        shuffle(pot2);
        shuffle(pot3);
        shuffle(pot4);
        AllocateFixtures(3, pot2, pot3, pot4, pot1, Matches);
        System.out.println("---------------");
        GetFixtures("Real Madrid", Matches);
        // System.out.println("---------------");
        // GetFixtures("Liverpool", Matches);
    }
}