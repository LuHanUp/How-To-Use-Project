package top.luhancc.sharding.jdbc.data.fragment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.luhancc.sharding.jdbc.data.fragment.dao.StoreInfoDao;
import top.luhancc.sharding.jdbc.data.fragment.entity.StoreInfo;

import java.util.List;
import java.util.Map;

@Service
public class StoreInfoServiceImpl implements StoreInfoService {

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Override
    public Map<Integer, StoreInfo> queryStoreInfoByIds(List<Long> ids) {
        return storeInfoDao.selectStoreInfoByIds(ids);
    }
}
