package Ex1.Bai3;

class User {
    private int age;

    // Phương thức Setter bảo vệ dữ liệu
    public void setAge(int age) {
        // Chủ động kiểm tra quy tắc nghiệp vụ
        if (age < 0) {
            // Dùng từ khóa 'throw' để ném ra một ngoại lệ ngay lập tức
            throw new IllegalArgumentException("Tuổi không thể âm!");
        }

        // Nếu qua được vòng kiểm tra trên, tiến hành gán giá trị hợp lệ
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

