import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Hashed Admin Password: " + passwordEncoder.encode("admin123"));
        System.out.println("Hashed Commercial Password: " + passwordEncoder.encode("commercial123"));
    }
}
