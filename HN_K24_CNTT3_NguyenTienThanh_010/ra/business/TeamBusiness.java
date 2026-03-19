package HN_K24_CNTT3_NguyenTienThanh_010.ra.business;

import HN_K24_CNTT3_NguyenTienThanh_010.ra.entity.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamBusiness {
    private static TeamBusiness instance;
    private List<Team> teams;

    private TeamBusiness() {
        teams = new ArrayList<>();
    }

    public static TeamBusiness getInstance() {
        if (instance == null) {
            instance = new TeamBusiness();
        }
        return instance;
    }

    public List<Team> getAllTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public Optional<Team> findById(String teamId) {
        return teams.stream()
                .filter(t -> t.getTeamId().equalsIgnoreCase(teamId))
                .findFirst();
    }

    public boolean deleteTeam(String teamId) {
        int initialSize = teams.size();
        teams = teams.stream()
                .filter(t -> !t.getTeamId().equalsIgnoreCase(teamId))
                .collect(Collectors.toList());
        return teams.size() < initialSize;
    }

    public List<Team> searchByName(String keyword) {
        return teams.stream()
                .filter(t -> t.getTeamName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Team> findChampions() {
        return teams.stream()
                .filter(t -> t.getAverageWeight() >= 60.0)
                .collect(Collectors.toList());
    }

    public void sortTeamsByAverageWeightDesc() {
        teams.sort((t1, t2) -> Double.compare(t2.getAverageWeight(), t1.getAverageWeight()));
    }
}