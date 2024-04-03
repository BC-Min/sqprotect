package sqproject.mbc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sqproject.mbc.security.HomeService;
import sqproject.mbc.vo.MemberVo;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final HomeService homeservice;

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        log.info("login loadUserByUsername = {}", insertedUserId);
        Optional<MemberVo> findOne = homeservice.findOne(insertedUserId);
        MemberVo member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));
        log.info("findOne = {}", member);

        return User.builder()
                .username(member.getMName())
                .password(member.getMPw())
                .roles(member.getRoles())
                .build();
    }
}
