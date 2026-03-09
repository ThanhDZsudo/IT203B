package Ex1.Bai5;

class User {
    private int age;

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Lỗi nghiệp vụ đăng ký: Tuổi người dùng không thể là số âm!");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
