package HN_K24_CNTT3_NguyenTienThanh_010.ra.entity;

import HN_K24_CNTT3_NguyenTienThanh_010.ra.business.TeamBusiness;

import java.util.Scanner;

public class Team {
    private String teamId;
    private String teamName;
    private int memberCount;
    private double averageWeight;

    public Team() {
    }

    public Team(String teamId, String teamName, int memberCount, double averageWeight) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.memberCount = memberCount;
        this.averageWeight = averageWeight;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public double getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(double averageWeight) {
        this.averageWeight = averageWeight;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập mã đội thi : ");
            this.teamId = scanner.nextLine().trim();
            if (this.teamId.isEmpty()) {
                System.out.println("Mã đội không được trống!");
                continue;
            }
            boolean isExist = TeamBusiness.getInstance().getAllTeams().stream()
                    .anyMatch(t -> t.getTeamId().equalsIgnoreCase(this.teamId));
            if (isExist) {
                System.out.println("Mã đội đã tồn tại!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Nhập tên đội thi: ");
            this.teamName = scanner.nextLine().trim();
            if (this.teamName.isEmpty()) {
                System.out.println("Tên đội thi không được để trống!");
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("Nhập số lượng thành viên: ");
            try {
                this.memberCount = Integer.parseInt(scanner.nextLine().trim());
                if (this.memberCount >= 10) {
                    break;
                } else {
                    System.out.println("Số lượng thành viên phải từ 10 trở lên!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng thành viên phải là số nguyên!");
            }
        }

        while (true) {
            System.out.print("Nhập cân nặng trung bình: ");
            try {
                this.averageWeight = Double.parseDouble(scanner.nextLine().trim());
                if (this.averageWeight > 0) {
                    break;
                } else {
                    System.out.println("Cân nặng trung bình phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Cân nặng phải là số!");
            }
        }
    }

    public void displayData() {
        System.out.printf("| %-10s | %-25s | %-20d | %-20.2f |\n",
                teamId, teamName, memberCount, averageWeight);
    }
}




