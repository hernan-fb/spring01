package com.example.obspringsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncriptionTest {

    @Test
    void bcryptTest() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i<10; i++) {
            String hashedPassword = passwordEncoder.encode("admin");
            System.out.println(hashedPassword);

            boolean matches = passwordEncoder.matches("admin", hashedPassword);
            System.out.println(matches);
            // lo que se va a probar es que gracias al salt se genera aleatoriamente cada vez
            // si hackean un password de admin no van a poder hackear el otro porque está hasheado diferente.
        }
    }
    @Test
    void bcryptCheckMultiplePasswords() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
        // a pesar de ser la misma contraseña, el resultado de cifrarla es diferente.
        // no se pueden compromenter distintas cuentas con la misma contraseña gracias a esta característica.
    }
    @Test
    void pbkdf2() {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder
                                                    ("banana",
                                                    100,
                                                    500,
                                                    Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);

        for (int i = 0; i < 30; i++) {
            String hashedPassword = passwordEncoder.encode("admin");
            System.out.println(hashedPassword);
            boolean matches = passwordEncoder.matches("admin", hashedPassword);
            System.out.println(matches);
        }
    }
//implementan la misma interfaz, cada uno la hace a su manera matemáticamente.
// Entonces los strings cifrados tardan en
// generarse y tienen "otro aspecto" al implementar distinto algoritmo.

    @Test
    void argon() {
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(
                16,    // saltLength: Set the salt length to 16 bytes
                32,    // hashLength: Set the hash length to 32 bytes
                4,     // parallelism: Set the degree of parallelism to 4 (e.g., use 4 CPU cores)
                65536, // memory: Set the memory usage to 64 MB (65536 kilobytes)
                5      // iterations: Set the number of iterations to 5
        );
        for (int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
    }
    @Test
    void scrypt() {
        SCryptPasswordEncoder passwordEncoder = new SCryptPasswordEncoder(  16384,
                                                                            8,
                                                                            16,
                                                                            256,
                                                                            32);
        for (int i = 0; i < 30; i++) {
            System.out.println(passwordEncoder.encode("admin"));
        }
    }
    @Test
    void springFrameworkEncoders() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("banana",
                                                        100,
                                                        5000,
                                                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512));
        encoders.put("scrypt", new SCryptPasswordEncoder(  16384,
                                                        8,
                                                        16,
                                                        256,
                                                        32));
        encoders.put("argon", new Argon2PasswordEncoder(16,    // saltLength: Set the salt length to 16 bytes
                                                        32,    // hashLength: Set the hash length to 32 bytes
                                                        4,     // parallelism: Set the degree of parallelism to 4 (e.g., use 4 CPU cores)
                                                        65536, // memory: Set the memory usage to 64 MB (65536 kilobytes)
                                                        5      // iterations: Set the number of iterations to 5
                                                        ));
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("argon",encoders);
        for (int i = 0; i < 30; i++) {
            String hashedPassword = passwordEncoder.encode("admin");
            System.out.println(hashedPassword);
            boolean matches = passwordEncoder.matches("admin", hashedPassword);
            System.out.println(matches);
        }
    }
}
