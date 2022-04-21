package ru.masta.user.usermodule.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.masta.entity.entitymodule.entity.UserData;


@Repository
public interface UserRepo extends JpaRepository<UserData, Long> {


    @Query("SELECT u FROM UserData u WHERE (" +
            "(:name='' OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND " +
            "(:lastName='' OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :lastName, '%'))))")
    Page<UserData> findByParam(@Param("name") String name,
                               @Param("lastName") String lastName,
                               Pageable pageable);

    UserData findByCard(String card);
}
