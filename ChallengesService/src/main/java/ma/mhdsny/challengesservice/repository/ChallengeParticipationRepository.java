package ma.mhdsny.challengesservice.repository;

import ma.mhdsny.challengesservice.entity.ChallengeParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeParticipationRepository extends JpaRepository<ChallengeParticipation, Long> {
    List<ChallengeParticipation> findByUserId(Long userId);
    List<ChallengeParticipation> findByChallengeId(Long challengeId);
    ChallengeParticipation findByUserIdAndChallengeId(Long userId, Long challengeId);
}
