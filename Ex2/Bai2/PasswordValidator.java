package Ex2.Bai2;

@FunctionalInterface
interface PasswordValidator {
    boolean isValid(String password);
}