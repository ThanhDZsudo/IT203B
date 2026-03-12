package Ex4.Ex5;

class AccessControlService {

    public boolean canPerformAction(User user, Action action) {
        if (user == null || user.getRole() == null || action == null) {
            return false;
        }

        switch (user.getRole()) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action == Action.LOCK_USER || action == Action.VIEW_PROFILE;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}
