package server.msaauth.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cyh.domain.entity.User;
import cyh.domain.repository.UserDao;
import server.msaauth.security.entity.UserInformation;

@Service
public class UserInformationService implements UserDetailsService {

    @Resource (name="UserDao")
    private UserDao UserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = UserDao.findByUserId(username);

        // 저장된 ID가 없을때 throw 시켜줍니다. 
        if(user == null) {
            throw new UsernameNotFoundException("wrongId"); // 저장된 ID 없음
        }
        return makeLoginUser(user);
    }
    
    // UserInformation 값 주입 해 줍니다.
    public UserDetails makeLoginUser(User user) {

        UserInformation loginUser  = new UserInformation();

        List<GrantedAuthority> authoritylist = new ArrayList<>();
        switch(user.getUserType()) {
            case 0 :
                // admin
                authoritylist.add(new SimpleGrantedAuthority("ADMIN"));
                break;
            case 1 :
                // user
                authoritylist.add(new SimpleGrantedAuthority("USER"));
                break;
        }

        loginUser.setUsername(user.getUserId());
        loginUser.setPassword(user.getPassword());
        loginUser.setAuthorities(authoritylist);
        
        return loginUser;
    }
}