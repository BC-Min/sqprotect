package sqproject.mbc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;
import sqproject.mbc.vo.CoffeeVo;

import java.util.List;

@Mapper
public interface CoffeeDao {
    List<CoffeeVo> coffeelist();

    void cartadd(@RequestParam CoffeeVo coffeevo);

    List<CoffeeVo> orderlist(@RequestParam String orderid);
}
