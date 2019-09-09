package com.zdv.shop.mapper;

import com.zdv.shop.common.dao.MyMapper;
import com.zdv.shop.model.UtFinancelog;

import java.util.List;
import java.util.Map;

public interface UtFinancelogMapper extends MyMapper<UtFinancelog> {
    int saveFinaceLog(UtFinancelog utFinancelog);

    List<UtFinancelog> queryFinancelogList(UtFinancelog financelog);

    int updateByUfinancelogid(UtFinancelog userFinancelog);

    int queryFinancelogCount(UtFinancelog ufinancelog);

    List<Map<String,Object>> queryFinancelogListMap(UtFinancelog ufinancelog);
}
