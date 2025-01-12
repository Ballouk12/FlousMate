package ma.mhdsny.challengesservice.service;

import ma.mhdsny.challengesservice.entity.Challenge;
import ma.mhdsny.challengesservice.entity.ChallengeParticipation;
import ma.mhdsny.challengesservice.repository.ChallengeParticipationRepository;
import ma.mhdsny.challengesservice.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    @Autowired
    private  ChallengeRepository challengeRepository;

    @Autowired
    private  ChallengeParticipationRepository participationRepository;

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public ChallengeParticipation joinChallenge(Long userId, Long challengeId) {
        ChallengeParticipation existing = participationRepository.findByUserIdAndChallengeId(userId, challengeId);
        if (existing != null) {
            return existing; // Already joined
        }
        ChallengeParticipation cp = ChallengeParticipation.builder()
                .userId(userId)
                .challengeId(challengeId)
                .currentProgress(0.0)
                .completed(false)
                .build();
        return participationRepository.save(cp);
    }

    public List<ChallengeParticipation> getUserParticipations(Long userId) {
        return participationRepository.findByUserId(userId);
    }

    public void markProgress(Long userId, Long challengeId, double progressIncrement) {
        ChallengeParticipation cp = participationRepository.findByUserIdAndChallengeId(userId, challengeId);
        if (cp != null && !cp.isCompleted()) {
            cp.setCurrentProgress(cp.getCurrentProgress() + progressIncrement);
            participationRepository.save(cp);
        }
    }

    public void completeChallenge(Long userId, Long challengeId) {
        ChallengeParticipation cp = participationRepository.findByUserIdAndChallengeId(userId, challengeId);
        if (cp != null && !cp.isCompleted()) {
            cp.setCompleted(true);
            cp.setCompletedAt(java.time.LocalDateTime.now());
            participationRepository.save(cp);
        }
    }
}
