package ctrl;

import UI.*;
import data.*;

import java.util.ArrayList;
import java.util.List;

public class Func implements IFunc{
    IUserDAO data = new UserDAO();
    IUI ui = new TUI();

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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
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
}
