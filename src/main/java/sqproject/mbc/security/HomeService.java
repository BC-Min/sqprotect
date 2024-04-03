package sqproject.mbc.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import sqproject.mbc.dao.HomeDao;
import sqproject.mbc.vo.MemberVo;
import java.util.List;

import java.util.Optional;

@Service
@Slf4j
public class HomeService {

    private final PasswordEncoder passwordEncoder;
    private final HomeDao homedao; // Autowired 대신?
    public HomeService(PasswordEncoder passwordEncoder, HomeDao homedao) {
//    public HomeService(HomeDao homedao) {
        this.passwordEncoder = passwordEncoder;
        this.homedao = homedao;
    }

    public boolean logged(Model model) {
        boolean i = homedao.logged(model);
        return i;
    }
    public void save(MemberVo memberVo) {
        log.info("pw encode before={}", memberVo.getMPw());
        memberVo.setMPw(passwordEncoder.encode(memberVo.getMPw()));
        log.info("pw encode after={}", memberVo.getMPw());

        log.info("roels before={}", memberVo.getRoles());
        memberVo.setRoles("USER");
        log.info("roles after={}", memberVo.getRoles());

        homedao.save(memberVo);
    }

    public Optional<MemberVo> findOne(String insertedUserId) {
        Optional<MemberVo> findOne = homedao.findOne(insertedUserId);
        return findOne;
    }


    public List<MemberVo> findAll(){
        List<MemberVo> memberList = homedao.findAll();
        return memberList;
    }
}
