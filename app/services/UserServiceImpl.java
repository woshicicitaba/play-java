package services;

/**
 * Created by admin on 2016/7/27.
 */
public class UserServiceImpl implements UserService {
    @Override
    public String save(String name) {
        return name + "-456";
    }
}
