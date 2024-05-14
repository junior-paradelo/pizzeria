package k3ras.pizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public static final String ADMIN = "ADMIN";
    public static final String CUSTOMER = "CUSTOMER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customRequest -> customRequest
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole(ADMIN, CUSTOMER)
                        .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT).hasRole(ADMIN)
                        .requestMatchers("/api/orders/**").hasRole(ADMIN)
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService memoryUsers() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles(ADMIN)
                .build();
        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles(CUSTOMER)
                .build();
        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
