package ctrl;

import UI.*;
import data.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Func implements IFunc{
    IUserDAO data = new UserDAO();
    IUI ui = new TUI();
    //TODO load users


    @Override
    public void start() {
        ui.clearDisplay();
        while(true){
            ui.showMessage("Velkommen til\n");
            switch(ui.getInteger("1: Opret bruger\n2: Vis alle brugere\n3: Vis enkelt bruger\n4: Opdater bruger\n5: Slet bruger\n6: Afslut")) {
                case 1:
                    createUser();
                    break;
                case 2:
                    printAllUsers();
                    break;
                case 3:
                    printSpecificUser();
                    break;
                case 4:
                    updateSpecificUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    exit();
                    break;
                default:
                    break;
            }
        }
    }

    private void createUser() {
        ui.clearDisplay();

        UserDTO tempUser = new UserDTO();

        ui.showMessage("Opret bruger:\n");
        tempUser.setUserName(ui.getString("Indtast navn:\n"));
        tempUser.setPassword(ui.getPassword("Indtast kode:\n"));
        tempUser.setCpr(ui.getInteger("Indtast CPR:\n"));
        tempUser.setRoles(getRoles());
        tempUser.setUserId(++UserDAO.userIDIt);

        try {
            data.createUser(tempUser);
        } catch(IUserDAO.DALException e){
            ui.showMessage("Bruger eksisterer allerede!");
        }
    }

    private List<String> getRoles(){
        List<String> tempList = new ArrayList<>();
        int tempInt = 0;
        while (tempInt != 5) {
            tempInt = ui.getInteger("Hvilke roller skal denne bruger have?:\n1: Admin\n2: Pharmacist\n3: Foreman\n4: Operator\n5:Færdig med roller");
            ui.showMessage("Nuværende roller:"+tempList.toString());
            switch (tempInt) {
                case 1:
                    if (tempList.contains("Admin")) {
                        tempList.remove("Admin");
                    }else {
                        tempList.add("Admin");
                    }
                    break;
                case 2:
                    if (tempList.contains("Pharmacist")) {
                        tempList.remove("Pharmacist");
                    }else {
                        tempList.add("Pharmacist");
                    }
                    break;
                case 3:
                    if (tempList.contains("Foreman")) {
                        tempList.remove("Foreman");
                    }else {
                        tempList.add("Foreman");
                    }
                    break;
                case 4:
                    if (tempList.contains("Operator")) {
                        tempList.remove("Operator");
                    }else {
                        tempList.add("Operator");
                    }
                    break;
                default:
                    break;
            }
        }

        return tempList;
    }

    private void printAllUsers(){
        ui.clearDisplay();
        try {
            for (Object o : data.getUserList()) {
                ui.showMessage(o.toString());
            }
        } catch(IUserDAO.DALException e) {
            ui.showMessage("Der gik noget galt");
        }
    }

    private void printSpecificUser() {
        ui.clearDisplay();
        try {
            ui.showMessage(data.getUser(ui.getInteger("Indtast bruger ID\n")).toString());
        } catch(IUserDAO.DALException e) {
            ui.showMessage("Forkert bruger ID");
        }
    }

    private void updateSpecificUser() {
        ui.clearDisplay();
        //TODO
        try {
            data.getUser(ui.getInteger("Indtast bruger ID\n"));
        } catch(IUserDAO.DALException e) {
            ui.showMessage("Forkert bruger ID");
        }
    }

    private void deleteUser() {
        ui.clearDisplay();
        try {
            UserDTO tempuser = data.getUser(ui.getInteger("Indtast bruger ID\n"));
            String temp = "";
            while (true) {
                temp = ui.getString("Vil du slette? (Y/N): " + tempuser.toString() + "\n");
                if(temp == "Y"){
                    data.deleteUser(tempuser.getUserId());
                    break;
                } else if(temp == "N"){
                    break;
                } else {
                    ui.showMessage("Indtast Y eller N\n");
                }
            }
        } catch(IUserDAO.DALException e) {
            ui.showMessage("Forkert bruger ID");
        }
    }

    private void exit(){
        //TODO save users
        System.exit(0);
    }
}
