package dto;

import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String email;
    private String passwordHash;
    private String displayName;
    private String preferences;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
