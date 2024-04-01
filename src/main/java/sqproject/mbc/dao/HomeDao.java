package sqproject.mbc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

@Mapper
public interface HomeDao {

    boolean logged(Model model);
}
