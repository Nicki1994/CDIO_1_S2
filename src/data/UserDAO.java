package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO implements IUserDAO{
    List<UserDTO> userList;
    
    public UserDAO(){
    	userList = new ArrayList<UserDTO>();
    }

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
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            if (((UserDTO) iterator.next()).getUserId() == user.getUserId()) {
                throw new DALException("Fejl: Bruger eksisterer allerede!");
            }
        }
        UserDTO tempUser = new UserDTO();
        tempUser.setUserId(user.getUserId());
        tempUser.setIni(user.getIni());
        tempUser.setRoles(user.getRoles());
        tempUser.setUserName(user.getUserName());
        userList.add(tempUser);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            UserDTO tempUser = (UserDTO) iterator.next();
            if (tempUser.getUserId() == user.getUserId()) {
                tempUser.setUserName(user.getUserName());
                tempUser.setIni(user.getIni());
                tempUser.setRoles(user.getRoles());
                return;
            }
        }

        throw new DALException("Fejl: Bruger ikke fundet!");
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
