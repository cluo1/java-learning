package cn.mariojd.service;

import cn.mariojd.base.BaseService;
import cn.mariojd.dto.MessageResult;
import cn.mariojd.entity.Order;
import cn.mariojd.enums.MessageEnum;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mario
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends BaseService {

    @CacheEvict(value = "orderPages", allEntries = true)
    @Transactional
    public MessageResult update(String oid, Integer state) {
        Order order = orderRepository.findByOid(oid);
        if (null != order) {
            if (state.equals(-1)) {
                order.setState(0);
            }
            if (state.equals(0)) {
                //TODO
                return new MessageResult(MessageEnum.USER_OPERATION_TIPSUCCESS);
            }
            if (state.equals(1)) {
                order.setState(2);
            }
            orderRepository.saveAndFlush(order);
            return new MessageResult(MessageEnum.UPDATESUCCESS);
        } else {
            return new MessageResult(MessageEnum.UPDATEFAILURE);
        }
    }

    @Cacheable(value = "orderPages", key = "#pageNumber")
    public Page<Order> findAll(Integer pageNumber) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(pageNumber - 1, 5, sort);
        return orderRepository.findAll(pageRequest);
    }

    @Cacheable(value = "order_report")
    public int[] report() {
        List<Integer> stateList = orderRepository.findAllstate();
        int s1 = 0, s2 = 0, s3 = 0, s4 = 0;
        for (Integer i : stateList) {
            if (i.equals(-1)) {
                s1++;
            } else if (i.equals(0)) {
                s2++;
            } else if (i.equals(1)) {
                s3++;
            } else {
                s4++;
            }
        }
        return new int[]{s1, s2, s3, s4};
    }
}

