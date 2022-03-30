package ru.masta.user.usermodule.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.masta.user.usermodule.entity.UserData;


@Repository
public interface UserRepo extends JpaRepository<UserData, Long> {
}
