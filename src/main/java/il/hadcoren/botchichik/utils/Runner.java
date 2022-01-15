package il.hadcoren.botchichik.utils;

import il.hadcoren.botchichik.dao.UserDao;
import il.hadcoren.botchichik.model.ConnectedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final UserDao userDao;

    private static final String ID = createRandomID();

    private static String createRandomID() {
        return String.valueOf(new Random().nextInt());
    }

    private static final String SHORT_DESCRIPTION = "SHORT_DESCRIPTION";
    private static final String LONG_DESCRIPTION = "LONG_DESCRIPTION";
    private static final String NICK_MANE = "NICK_MANE";

    @Override
    public void run(String... args) throws Exception {
        userDao.addNewConnectedUser(ConnectedUser.builder()
                .id(ID)
                .shortDescription(SHORT_DESCRIPTION)
                .longDescription(LONG_DESCRIPTION)
                .nikeName(NICK_MANE)
                .build());
    }
}
