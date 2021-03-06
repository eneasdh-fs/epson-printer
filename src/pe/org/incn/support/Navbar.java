package pe.org.incn.support;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Navbar
 *
 * @author enea <enea.so@live.com>
 */
public class Navbar {

    static protected TrayIcon trayIcon;

    public void loadMenu() {
        final PopupMenu popup = new PopupMenu();
        Image image = Toolkit.getDefaultToolkit().getImage(Helpers.resource("icon.gif"));
        Navbar.trayIcon = new TrayIcon(image, "Listo para imprimir");
        final SystemTray tray = SystemTray.getSystemTray();

        MenuItem restart = new MenuItem("Reiniciar");
        MenuItem exit = new MenuItem("Cerrar");

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int config = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar el servidor?", "Cuidado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (config == 0) {
                    System.exit(0);
                }
            }
        });

        popup.add(restart);
        popup.add(exit);
        popup.addSeparator();

        trayIcon.setPopupMenu(popup);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    public static void showNotification(String title, String message, TrayIcon.MessageType status) {
        trayIcon.displayMessage(title, message, status);
    }

    public static void showInfoNotification(String title, String message) {
        trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
    }

    public static void showErrorNotification(String title, String message) {
        trayIcon.displayMessage(title, message, TrayIcon.MessageType.ERROR);
    }
}
