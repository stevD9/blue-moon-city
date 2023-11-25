package me.stef.bluemooncity.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import me.stef.bluemooncity.model.UserRole;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "state")
    @Convert(converter = StateConverter.class)
    private State state;

    /////////
    public static class State {
        @JsonProperty("e")
        private boolean enabled = false;
        @JsonProperty("l")
        private boolean locked = false;
        @JsonProperty("pt")
        private Token passwordToken;
        @JsonProperty("et")
        private Token emailToken;

        public boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean getLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public Token getPasswordToken() {
            return passwordToken;
        }

        public void setPasswordToken(Token passwordToken) {
            this.passwordToken = passwordToken;
        }

        public Token getEmailToken() {
            return emailToken;
        }

        public void setEmailToken(Token emailToken) {
            this.emailToken = emailToken;
        }

        //////////
        public static class Token {
            private String id;
            @JsonProperty("ex")
            private Date expiry;

            public Token() {
            }

            public Token(String id, Date expiry) {
                this.id = id;
                this.expiry = expiry;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Date getExpiry() {
                return expiry;
            }

            public void setExpiry(Date expiry) {
                this.expiry = expiry;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Token token = (Token) o;
                return Objects.equals(id, token.id) && Objects.equals(expiry, token.expiry);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, expiry);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return enabled == state.enabled && locked == state.locked && Objects.equals(passwordToken, state.passwordToken) && Objects.equals(emailToken, state.emailToken);
        }

        @Override
        public int hashCode() {
            return Objects.hash(enabled, locked, passwordToken, emailToken);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    //////////
    public static class StateConverter extends JsonColumnConverter<State> {}
}
