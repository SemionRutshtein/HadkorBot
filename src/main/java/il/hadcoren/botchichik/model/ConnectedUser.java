package il.hadcoren.botchichik.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class ConnectedUser {

    @Id
    private String id;

    private String nikeName;

    private String shotDescription;

    private String longDescription;
}
