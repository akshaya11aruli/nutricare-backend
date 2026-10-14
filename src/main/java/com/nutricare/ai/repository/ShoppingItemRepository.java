package com.nutricare.ai.repository;

import com.nutricare.ai.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {
    List<ShoppingItem> findByUserId(Long userId);
    Optional<ShoppingItem> findByUserIdAndItemKey(Long userId, String itemKey);
    void deleteByUserIdAndItemKey(Long userId, String itemKey);
    void deleteByUserIdAndIsCustomTrue(Long userId);
    void deleteByUserIdAndCheckedTrue(Long userId);
}
