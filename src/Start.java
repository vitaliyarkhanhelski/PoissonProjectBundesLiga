import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Start {

    public static List<String> getAllTeamsList(String csvFile, String cvsSplitBy) {
        //print a list of teams
        Set<String> allTeamsSet = new TreeSet<>();
        List<String> allTeamsList = new LinkedList<>();
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] split2 = line.split(cvsSplitBy);
                allTeamsSet.add(split2[3]);
            }
            allTeamsList = new LinkedList<>(allTeamsSet);
            System.out.println("List of Teams is as follows:");
            Format.printDash();
            for (int i = 1; i < allTeamsList.size(); i++) {
                if ((i != 1) && (i - 1) % 5 == 0) System.out.println();
                if (i == allTeamsSet.size() - 1) System.out.printf("%-18s\n", allTeamsList.get(i));
                else System.out.printf("%-18s", allTeamsList.get(i) + ",");
            }
            Format.printDash();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allTeamsList;
    }


    public static FootballData enterHomeAwayTeams(FootballData data, List<String> allTeamsList) {
        // check letters spelling
        String homeTeam;
        String awayTeam;
        List<String> allTeamsListToLowerCase = new LinkedList<>();
        for (int i = 0; i < allTeamsList.size(); i++) {
            allTeamsListToLowerCase.add(allTeamsList.get(i).toLowerCase());
        }

        //Provide Home Team and Away Team
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter Home Team name:");
            homeTeam = scanner.nextLine();
            if (!allTeamsListToLowerCase.contains(homeTeam.toLowerCase()))
                System.out.println("No such Team, Try again!");
        } while (!allTeamsListToLowerCase.contains(homeTeam.toLowerCase()));

        data.setHomeTeam(allTeamsList.get(allTeamsListToLowerCase.indexOf(homeTeam.toLowerCase())));

        do {
            System.out.println("Enter Away Team name:");
            awayTeam = scanner.nextLine();
            if (!allTeamsListToLowerCase.contains(awayTeam.toLowerCase()))
                System.out.println("No such Team, Try again!");
        } while (!allTeamsListToLowerCase.contains(awayTeam.toLowerCase()));

        data.setAwayTeam(allTeamsList.get(allTeamsListToLowerCase.indexOf(awayTeam.toLowerCase())));

        return data;
    }


    public static FootballData printFileAndCollectData(FootballData data, String csvFile, String cvsSplitBy) {
        BufferedReader br = null;
        String line = "";
        boolean flag = true;
        // Collect data
        try {
            br = new BufferedReader(new FileReader(csvFile));
            System.out.println();
            System.out.println("Data From Fil, Season 2019/2020:");
            Format.printDash();
            while ((line = br.readLine()) != null) {
                String[] split = line.split(cvsSplitBy);

                //print the header
                if (flag) {
                    System.out.printf("Home Team   %-20s| Away Team   %-20s|   Score= %s : %s\n", "", "", split[5], split[6]);
                    flag = false;
                    System.out.println("----------------------------------------------------------------------------------------");
                }
                //print the rest of table
                else {
                    System.out.printf("Home Team = %-20s| Away Team = %-20s|   Score=   %-2s :   %-2s\n", split[3], split[4], split[5], split[6]);
                    data.setAllMatchesCount(data.getAllMatchesCount() + 1);
                    data.setAllHomeGoals(data.getAllHomeGoals() + Integer.valueOf(split[5]));
                    data.setAllAwayGoals(data.getAllAwayGoals() + Integer.valueOf(split[6]));

                    //collect HOME DATA
                    if (split[3].equals(data.getHomeTeam())) {
                        //making list of all the matches of home team
                        data.setHomeTeamMatchesList(new FootballTeam(split[3], split[4], split[5], split[6]));
                        data.setCountHomeMatches(data.getCountHomeMatches() + 1);
                        data.setSummaryHomeGoals(data.getSummaryHomeGoals() + Integer.valueOf(split[5]));
                        data.setSummaryHomeConceded(data.getSummaryHomeConceded() + Integer.valueOf(split[6]));
                    }

                    //collect AWAY DATA
                    if (split[4].equals(data.getAwayTeam())) {
                        //making list of all the matches of away team
                        data.setAwayTeamMatchesList(new FootballTeam(split[3], split[4], split[5], split[6]));
                        data.setCountAwayMatches(data.getCountAwayMatches() + 1);
                        data.setSummaryAwayGoals(data.getSummaryAwayGoals() + Integer.valueOf(split[6]));
                        data.setSummaryAwayConceded(data.getSummaryAwayConceded() + Integer.valueOf(split[5]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
