package UI;

public interface IUI {
    public void clearDisplay();
    public void showMessage(String msg);
    public int getInteger(String msg);
    public String getString(String msg);
    public char[] getPassword(String msg);
}
