package HN_K24_CNTT3_NguyenTienThanh_010.ra.presentation;

import HN_K24_CNTT3_NguyenTienThanh_010.ra.entity.Team;
import HN_K24_CNTT3_NguyenTienThanh_010.ra.business.TeamBusiness;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TeamManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TeamBusiness teamBusiness = TeamBusiness.getInstance();

        while (true) {
            System.out.println("\n******************* QUẢN LÝ ĐỘI THI KÉO CO *******************");
            System.out.println("1. Hiển thị danh sách toàn bộ đội thi");
            System.out.println("2. Thêm mới đội thi");
            System.out.println("3. Cập nhật thông tin đội thi theo mã đội");
            System.out.println("4. Xóa theo mã đội thi");
            System.out.println("5. Tìm kiếm theo tên đội");
            System.out.println("6. Tìm ứng cử viên vô địch dựa trên cân nặng trung bình");
            System.out.println("7. Sắp xếp danh sách đội thi giảm dần theo cân nặng trung bình");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    displayAllTeams(teamBusiness.getAllTeams());
                    break;
                case 2:
                    addTeams(scanner, teamBusiness);
                    break;
                case 3:
                    updateTeam(scanner, teamBusiness);
                    break;
                case 4:
                    deleteTeam(scanner, teamBusiness);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    sortTeams(teamBusiness);
                    break;
                case 8:
                    System.out.println("Đã thoát chương trình!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1-8.");
            }
        }
    }

    private static void displayAllTeams(List<Team> list) {
        if (list.isEmpty()) {
            System.out.println("Danh sách đội thi đang rỗng!");
            return;
        }
        System.out.println("------------------------------------------------------------------------------------------");
        list.forEach(Team::displayData);
        System.out.println("----------------------------------------------------------------------------------------");
    }

    private static void addTeams(Scanner scanner, TeamBusiness teamBusiness) {
        do {
            System.out.println("\n--- Thêm Mới Đội Thi ---");
            Team team = new Team();
            team.inputData(scanner);
            teamBusiness.addTeam(team);
            System.out.println("Đã thêm thành công!");

            System.out.print("Bạn có muốn tiếp tục thêm độihay không? (Y/N): ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        } while (true);
    }

    private static void updateTeam(Scanner scanner, TeamBusiness teamBusiness) {
        System.out.print("Nhập mã đội thi cập nhật: ");
        String teamId = scanner.nextLine().trim();
        Optional<Team> optTeam = teamBusiness.findById(teamId);

        if (optTeam.isEmpty()) {
            System.out.println("Mã đội thi không tồn tại.");
        } else {
            Team team = optTeam.get();
            boolean isUpdating = true;
            while (isUpdating) {
                System.out.println("\nChọn thông tin cần cập nhật cho đội [" + team.getTeamName() + "]:");
                System.out.println("1. Tên đội thi");
                System.out.println("2. Số lượng thành viên");
                System.out.println("3. Cân nặng trung bình");
                System.out.println("4. Hoàn tất cập nhật");
                System.out.print("Lựa chọn: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số!");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên đội thi mới: ");
                        String newName = scanner.nextLine().trim();
                        if (!newName.isEmpty()) {
                            team.setTeamName(newName);
                            System.out.println("Cập nhật thành công!");
                        } else {
                            System.out.println("Tên không được trống!");
                        }
                        break;
                    case 2:
                        System.out.print("Nhập số lượng thành viên mới: ");
                        try {
                            int newCount = Integer.parseInt(scanner.nextLine().trim());
                            if (newCount >= 10) {
                                team.setMemberCount(newCount);
                                System.out.println("Cập nhật thành công!");
                            } else {
                                System.out.println("Số lượng phải lớn hơn hoặc bằng 10!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Nhập vào phải là số!");
                        }
                        break;
                    case 3:
                        System.out.print("Nhập cân nặng trung bình mới: ");
                        try {
                            double newWeight = Double.parseDouble(scanner.nextLine().trim());
                            if (newWeight > 0) {
                                team.setAverageWeight(newWeight);
                                System.out.println("Cập nhật thành công!");
                            } else {
                                System.out.println("Cân nặng phải lớn hơn 0!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Nhập vào phải là số!");
                        }
                        break;
                    case 4:
                        isUpdating = false;
                        System.out.println("Cập nhật thành cng");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            }
        }
    }

    private static void deleteTeam(Scanner scanner, TeamBusiness teamBusiness) {
        System.out.print("Nhập mã đội thi cần xóa: ");
        String teamId = scanner.nextLine().trim();
        boolean isDeleted = teamBusiness.deleteTeam(teamId);

        if (isDeleted) {
            System.out.println("Đã xóa đội thi thành công!");
        } else {
            System.out.println("Mã đội thi không tồn tại trong hệ thống.");
        }
    }

    private static void sortTeams(TeamBusiness teamBusiness) {
        teamBusiness.sortTeamsByAverageWeightDesc();
        System.out.println("Đã sắp xếp danh sách theo cân nặng trung bình giảm dần!");
        displayAllTeams(teamBusiness.getAllTeams());
    }
}