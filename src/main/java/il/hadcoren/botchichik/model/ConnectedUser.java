package il.hadcoren.botchichik.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectedUser {

    @Id
    private String id;

    private String nikeName;

    private String shotDescription;

    private String longDescription;
}
