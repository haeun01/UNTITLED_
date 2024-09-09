package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="user")
public class User implements UserDetails {
    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="password")
    private String password;
    @Column(name="user_name")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "profile_img")
    private String profileImg = "https://cdn.pixabay.com/photo/2017/03/21/02/00/user-2160923_1280.png";
    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;
    @Column(name = "profile_text")
    private String profileText;
    @Column
    private int coin;
    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.getAuthorityName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    public String getUserName(){
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
