package services;

import com.google.inject.ImplementedBy;

/**
 * Created by admin on 2016/7/27.
 */
@ImplementedBy(UserServiceImpl.class)
public interface UserService {
    String save(String name);
}
