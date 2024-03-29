package net.ausiasmarch.foxforumserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import net.ausiasmarch.foxforumserver.entity.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    Page<ReplyEntity> findByUserId(Long id, Pageable pageable);

    Page<ReplyEntity> findByThreadId(Long id, Pageable pageable);

    @Modifying
    @Query(value = "ALTER TABLE reply AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    @Query(value = "SELECT * FROM reply WHERE LENGTH(?1) >= 3 AND (title LIKE %?1% OR body LIKE %?1%)", nativeQuery = true)
Page<ReplyEntity> findByTitleOrBodyContainingIgnoreCase(String searchText, Pageable pageable);

}
