package ma.mhdsny.challengesservice.repository;


import ma.mhdsny.challengesservice.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    // You can add custom query methods if needed
}
