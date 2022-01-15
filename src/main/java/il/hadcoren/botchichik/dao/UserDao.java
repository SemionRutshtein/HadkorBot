package il.hadcoren.botchichik.dao;

import il.hadcoren.botchichik.model.ConnectedUser;
import il.hadcoren.botchichik.repository.BotUsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static il.hadcoren.botchichik.exception.constants.ExceptionCustomMessages.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDao {

    private final BotUsersRepository botUsersRepository;

    public void addNewConnectedUser(ConnectedUser connectedUser){
        try{
            botUsersRepository.save(connectedUser);
        } catch (DataAccessException a) {
            log.error(DATA_BASE_CREATION_EXCEPTION);
        }
    }

    public Optional<ConnectedUser> getConnectedUser(String id) {
       return botUsersRepository.findById(id);
    }

    public List<ConnectedUser> getAllConnectedUsers() {
        List<ConnectedUser> allConnectedUsers = botUsersRepository.findAll();
        if (allConnectedUsers.size() == 0) log.error(EMPTY_DATA_BASE_EXCEPTION);
        return allConnectedUsers;
    }

    public void removeConnectedUser (String id) {
        try {
            botUsersRepository.deleteById(id);
        } catch (DataAccessException a) {
            log.error(DATA_BASE_DELETING_EXCEPTION);
        }
    }


}
