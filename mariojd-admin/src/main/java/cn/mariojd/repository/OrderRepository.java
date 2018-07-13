package cn.mariojd.repository;

import cn.mariojd.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Mario
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o.state from Order o")
    List<Integer> findAllstate();

    Order findByOid(String oid);

}
