package data;

import java.util.Iterator;
import java.util.List;

public class UserDAO implements IUserDAO{
    List<UserDTO> userList;

    @Override
    public UserDTO getUser(int userId) throws DALException {
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            UserDTO tempUser = (UserDTO) iterator.next();
            if (tempUser.getUserId() == userId) {
                return tempUser;
            }
        }

        throw new DALException("Fejl: Bruger ikke fundet!");
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return userList;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        userList.add(user);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            if (((UserDTO) iterator.next()).getUserId() == user.getUserId()) {
                break;
            }
        }
        ((UserDTO) iterator).setUserName(user.getUserName());
        ((UserDTO) iterator).setIni(user.getIni());
        ((UserDTO) iterator).setRoles(user.getRoles());

    }

    @Override
    public void deleteUser(int userId) throws DALException {
        Iterator iterator = userList.iterator();
        boolean j = false;
        while (iterator.hasNext()) {
            if (((UserDTO) iterator.next()).getUserId() == userId) {
                iterator.remove();
                j = true;
            }
        }
        if (j == false) {
            throw new DALException("Fejl: Bruger ikke fundet!");
        }
    }
}
