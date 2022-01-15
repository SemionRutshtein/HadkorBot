package il.hadcoren.botchichik.controller.v1;

import il.hadcoren.botchichik.dao.UserDao;
import il.hadcoren.botchichik.model.ConnectedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
public class UserController {
    private final UserDao userDao;

    @GetMapping("/all")
    public  List<ConnectedUser> getAllUser() {
        List<ConnectedUser> allConnectedUsers = userDao.getAllConnectedUsers();
        allConnectedUsers.forEach(System.out::print);
        return allConnectedUsers;
    }
}
