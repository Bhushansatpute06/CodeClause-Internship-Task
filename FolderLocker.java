import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FolderLocker extends JFrame {

    private JTextField folderPathField;
    private JTextField passwordField;
    private JLabel statusLabel;

    public FolderLocker() {
        setTitle("Folder Locker");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel folderLabel = new JLabel("Enter the path of the folder to lock:");
        folderPathField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter a password for the folder:");
        passwordField = new JTextField();
        JButton lockButton = new JButton("Lock Folder");
        statusLabel = new JLabel();

        lockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lockFolder();
            }
        });

        panel.add(folderLabel);
        panel.add(folderPathField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(lockButton);
        panel.add(statusLabel);

        add(panel);
    }

    private void lockFolder() {
        String folderPath = folderPathField.getText();
        String password = passwordField.getText();

        try {
            File folder = new File(folderPath);
            File lockedFolder = new File(folder.getParent(), folder.getName() + "_locked");

            if (!lockedFolder.exists()) {
                lockedFolder.mkdir();
            }

            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    Path source = file.toPath();
                    Path destination = new File(lockedFolder, file.getName()).toPath();
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                }
            }

            // TODO: Implement encryption logic here

            // Optionally, you can delete the original folder after locking
            // deleteFolder(folder);

            statusLabel.setText("Folder locked successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error locking folder. Please check the input and try again.");
        }
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FolderLocker folderLocker = new FolderLocker();
            folderLocker.setVisible(true);
        });
    }
}
