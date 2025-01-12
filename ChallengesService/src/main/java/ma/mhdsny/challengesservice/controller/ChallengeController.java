package ma.mhdsny.challengesservice.controller;


import ma.mhdsny.challengesservice.entity.Challenge;
import ma.mhdsny.challengesservice.entity.ChallengeParticipation;
import ma.mhdsny.challengesservice.repository.ChallengeParticipationRepository;
import ma.mhdsny.challengesservice.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeParticipationRepository participationRepository;

    // ==== CHALLENGE CRUD ====

    // Create a new challenge
    @PostMapping
    public Challenge createChallenge(@RequestBody Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    // Get all challenges
    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    // Get a single challenge by ID
    @GetMapping("/{id}")
    public Challenge getChallenge(@PathVariable Long id) {
        return challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found!"));
    }

    // Update a challenge
    @PutMapping("/{id}")
    public Challenge updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        return challengeRepository.findById(id).map(challenge -> {
            challenge.setName(updatedChallenge.getName());
            challenge.setDescription(updatedChallenge.getDescription());
            challenge.setGoalValue(updatedChallenge.getGoalValue());
            challenge.setStatus(updatedChallenge.getStatus());
            return challengeRepository.save(challenge);
        }).orElseThrow(() -> new RuntimeException("Challenge not found!"));
    }

    // Delete a challenge
    @DeleteMapping("/{id}")
    public void deleteChallenge(@PathVariable Long id) {
        challengeRepository.deleteById(id);
    }

    // ==== CHALLENGE PARTICIPATION ENDPOINTS ====

    // Join a challenge
    @PostMapping("/{challengeId}/join")
    public ChallengeParticipation joinChallenge(@PathVariable Long challengeId,
                                                @RequestParam Long userId) {
        // Check if user already joined
        ChallengeParticipation existing =
                participationRepository.findByUserIdAndChallengeId(userId, challengeId);
        if (existing != null) {
            return existing; // already participating
        }

        // Create new participation record
        ChallengeParticipation cp = ChallengeParticipation.builder()
                .userId(userId)
                .challengeId(challengeId)
                .currentProgress(0.0)
                .completed(false)
                .build();
        return participationRepository.save(cp);
    }

    // Get all participations for a user
    @GetMapping("/participation/user/{userId}")
    public List<ChallengeParticipation> getUserParticipations(@PathVariable Long userId) {
        return participationRepository.findByUserId(userId);
    }

    // Update progress on a challenge
    @PostMapping("/{challengeId}/progress")
    public ChallengeParticipation updateProgress(@PathVariable Long challengeId,
                                                 @RequestParam Long userId,
                                                 @RequestParam double progressIncrement) {
        ChallengeParticipation cp =
                participationRepository.findByUserIdAndChallengeId(userId, challengeId);
        if (cp == null) {
            throw new RuntimeException("User not joined this challenge!");
        }

        if (!cp.isCompleted()) {
            cp.setCurrentProgress(cp.getCurrentProgress() + progressIncrement);
            cp = participationRepository.save(cp);
        }
        return cp;
    }

    // Complete a challenge
    @PostMapping("/{challengeId}/complete")
    public ChallengeParticipation completeChallenge(@PathVariable Long challengeId,
                                                    @RequestParam Long userId) {
        ChallengeParticipation cp =
                participationRepository.findByUserIdAndChallengeId(userId, challengeId);
        if (cp == null) {
            throw new RuntimeException("User not joined this challenge!");
        }
        if (!cp.isCompleted()) {
            cp.setCompleted(true);
            cp.setCompletedAt(java.time.LocalDateTime.now());
            cp = participationRepository.save(cp);
        }
        return cp;
    }
}
