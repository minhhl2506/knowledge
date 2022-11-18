package com.example.knowledge.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.example.knowledge.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal extends AbstractUserPrincipal implements Serializable {
    private static final long serialVersionUID = 6960173949433045836L;

    private User user;

    private Collection<String> roles;

    /**
     * UserPrincipal
     *
     * @param user User
     * @param roles Collection<String>
     * @param authorities Collection<? extends GrantedAuthority>
     */
	public UserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), authorities);

		this.user = user;
	}

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
}
