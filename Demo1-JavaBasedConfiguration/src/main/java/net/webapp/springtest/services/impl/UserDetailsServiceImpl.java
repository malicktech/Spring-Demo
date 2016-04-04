package net.webapp.springtest.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.webapp.springtest.models.Role;
import net.webapp.springtest.models.User;
import net.webapp.springtest.models.UserStatus;
import net.webapp.springtest.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// our own User model class
		User user = userRepository.findByUsername(username);

		if (user != null) {
			String password = user.getPassword();
			// additional information on the security object
			boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean credentialsNonExpired = user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = user.getStatus().equals(UserStatus.ACTIVE);

			// Let's populate user roles
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}

			// Now let's create Spring Security User object
			org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
					username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
					authorities);
			return securityUser;
		} else {
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

}
