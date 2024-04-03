package sqproject.mbc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;
import sqproject.mbc.vo.MemberVo;
import java.util.List;

import java.util.Optional;

@Mapper
public interface HomeDao {

    boolean logged(Model model);

    void save(MemberVo memberVo);

    Optional<MemberVo> findOne(String insertedUserId);

    List<MemberVo> findAll();
}
