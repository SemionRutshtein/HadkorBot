package il.hadcoren.botchichik.service;

import il.hadcoren.botchichik.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HadBotService {
    private final UserDao userDao;


}

