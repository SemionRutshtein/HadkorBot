package il.hadcoren.botchichik.repository;

import il.hadcoren.botchichik.model.ConnectedUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotUsersRepository extends MongoRepository<ConnectedUser, String> {
}
