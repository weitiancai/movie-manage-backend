import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wmg
 * @date 2024/6/26
 */
public class bcryptDemo {

    public static void main(String[] args) {
        String password = "123456"; // 这里替换为你的密码

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
    }
}
