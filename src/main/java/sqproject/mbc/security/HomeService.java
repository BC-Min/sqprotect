package sqproject.mbc.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import sqproject.mbc.dao.HomeDao;

@Service
@Slf4j
public class HomeService {

    @Autowired
    HomeDao homedao;

    public boolean logged(Model model) {
        boolean i = homedao.logged(model);
        return i;
    }
}
