package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDAO implements IUserDAO{
    private List<UserDTO> userList;
    public static int userIDIt = 0;
    
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
        tempUser.setCpr(user.getCpr());
        tempUser.setPassword(user.getPassword());
        userList.add(tempUser);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            UserDTO tempUser = (UserDTO) iterator.next();
            if (tempUser.getUserId() == user.getUserId()) {
                tempUser.setIni(user.getIni());
                tempUser.setRoles(user.getRoles());
                tempUser.setUserName(user.getUserName());
                tempUser.setCpr(user.getCpr());
                tempUser.setPassword(user.getPassword());
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
        if (!j) {
            throw new DALException("Fejl: Bruger ikke fundet!");
        }
    }
    
    public void saveUsers() throws DALException {
		ObjectOutputStream oOS =null;
		try {
			String fileName = "userDTOs.txt";
			FileOutputStream fOS = new FileOutputStream(fileName);
			oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(userList);
		} catch (FileNotFoundException e) {
			throw new DALException("Error locating file", e);
		} catch (IOException e) {
				throw new DALException("Error writing to disk", e);
		} finally {
			if (oOS!=null) {
				try {
					oOS.close();
				} catch (IOException e) {
					throw new DALException("Unable to close ObjectStream", e);
				}
			}
		}	
	}
    public List<UserDTO> loadUsers() throws DALException {
		List<UserDTO> userStore = new ArrayList<UserDTO>();
		ObjectInputStream oIS = null;
		try {
			String fileName = "UserDTOs.txt";
			FileInputStream fIS = new FileInputStream(fileName);
			oIS = new ObjectInputStream(fIS);
			Object inObj = oIS.readObject();
			if (inObj instanceof ArrayList){
				userStore = (ArrayList) inObj;
			} else {
				throw new DALException("Wrong object in file");
			}
		} catch (FileNotFoundException e) {
			//No problem - just returning empty userstore
		} catch (IOException e) {
			throw new DALException("Error while reading disk!", e);
		} catch (ClassNotFoundException e) {
			throw new DALException("Error while reading file - Class not found!", e);
		} finally {
			if (oIS!=null){
				try {
					oIS.close();
				} catch (IOException e) {
					throw new DALException("Error closing pObjectStream!", e);
				}
			}
		}

		//iterating on uID counter
        for (UserDTO u: userStore) {
            if(u.getUserId() > userIDIt){
                userIDIt = u.getUserId();
            }
        }
        userList = userStore;
		return userStore;
	}


    

}
