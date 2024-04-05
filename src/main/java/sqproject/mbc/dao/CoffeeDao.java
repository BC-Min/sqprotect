package sqproject.mbc.dao;

import org.apache.ibatis.annotations.Mapper;
import sqproject.mbc.vo.CoffeeVo;

import java.util.List;

@Mapper
public interface CoffeeDao {
    List<CoffeeVo> coffeelist();
}
