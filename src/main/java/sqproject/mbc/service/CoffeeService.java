package sqproject.mbc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sqproject.mbc.dao.CoffeeDao;
import sqproject.mbc.vo.CoffeeVo;

import java.util.List;

@Service
@Slf4j
public class CoffeeService {
    private final CoffeeDao coffeedao;

    public CoffeeService(CoffeeDao coffeedao) {
        this.coffeedao = coffeedao;
    }


    public List<CoffeeVo> coffeelist() {
        List<CoffeeVo> list = coffeedao.coffeelist();

        return list;
    }

    public void docartadd(CoffeeVo coffeevo) {
        coffeedao.cartadd(coffeevo);
    }

    public List<CoffeeVo> doorderlist(String orderid) {
        List<CoffeeVo> list = coffeedao.orderlist(orderid);
        return list;
    }

    public void doordernoinput(List<CoffeeVo> orderlist, String orderno) {
            // 여기까지 작업함
    }
}
