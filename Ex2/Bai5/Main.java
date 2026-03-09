package Ex2.Bai5;

interface UserActions {
    default void logActivity(String activity) {
        System.out.println("[UserActions] Ghi log người dùng thường: " + activity);
    }
}

interface AdminActions {
    default void logActivity(String activity) {
        System.out.println("[AdminActions] Ghi log quản trị viên: " + activity);
    }
}

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        System.out.println("--- Bắt đầu ghi log cho SuperAdmin ---");
        AdminActions.super.logActivity(activity);
        UserActions.super.logActivity(activity);
        System.out.println("--- Hoàn tất ghi log ---");
    }
}

public class Main {
    public static void main(String[] args) {
        SuperAdmin superadmin = new SuperAdmin();
        System.out.println("Thực hiện hành động: Khôi phục dữ liệu hệ thống");
        superadmin.logActivity("Khôi phục dữ liệu hệ thống");
    }
}