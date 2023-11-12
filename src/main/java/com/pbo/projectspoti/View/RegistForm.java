package com.pbo.projectspoti.View;

import com.formdev.flatlaf.FlatClientProperties;
import com.pbo.projectspoti.Controller.FormsManager;
import net.miginfocom.swing.MigLayout;
import com.pbo.projectspoti.View.Component.PasswordStrengthStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistForm extends JPanel {

    public static boolean isRegistered = false;

    public RegistForm() {
        init();
    }
    
        public static void main(String[] args) {
        RegistForm registForm = new RegistForm();
    }

    // GUI
    private void init() {
        // Mengatur layout kontainer utama
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));

        // Inisiasi komponen
        fullnameTextField = new JTextField();
        usernameTextField = new JTextField();
        passwordTextField = new JPasswordField();
        confirmPassword = new JPasswordField();
        registerButton = new JButton("Sign Up");
        passwordStrengthStatus = new PasswordStrengthStatus();

        // Membuat kontainer untuk form register
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        // Memberikan warna panel lebih terang dibanding background-nya sesuai tema Light dan Dark
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "[light]background:darken(@background,3%);"
                + "[dark]background:lighten(@background,3%)");

        // Membuat judul dan deksripsi form register
        JLabel lbTitle = new JLabel("Welcome to Sprotify");
        JLabel description = new JLabel("Sign up and enjoy a variety of your favorite music playlists");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);"
                + "[dark]foreground:darken(@foreground,30%)");

        // Styling field 
        fullnameTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First name");
        usernameTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
        passwordTextField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        confirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Re-enter your password");
        passwordTextField.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        confirmPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        // Styling tombol register
        registerButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        Color originalBackgroundColor = (Color) UIManager.get("Button.background");
        registerButton.putClientProperty("originalBackground", originalBackgroundColor);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Memanggil method untuk mengecek kekuatan password
        passwordStrengthStatus.initPasswordField(passwordTextField);

        // Hover tombol register
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Mouse masuk ke tombol
                registerButton.setBackground(new Color(14, 246, 68));
                registerButton.setForeground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Mouse keluar dari tombol, kembalikan ke warna awal
                registerButton.setBackground((Color) registerButton.getClientProperty("originalBackground"));
                registerButton.setForeground(new Color(255, 255, 255));
            }
        });

        // Pop-up
        JFrame frame = new JFrame("Form Example");

        // Reset field
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mengosongkan JTextField setelah tombol submit ditekan
                fullnameTextField.setText("");
                usernameTextField.setText("");
                passwordTextField.setText("");
                confirmPassword.setText("");

                checklist = new ImageIcon("src\\main\\resources\\icons\\checklist.png");
                JOptionPane.showMessageDialog(frame, "Successful Registration", "Success", JOptionPane.INFORMATION_MESSAGE, checklist);
            }
        });

        // Menambahkan komponen ke panel
        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Full Name"), "gapy 10");
        panel.add(fullnameTextField, "gapy 8");
        panel.add(new JLabel("Gender"), "gapy 8");
        panel.add(createGenderPanel());
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(new JLabel("Email"));
        panel.add(usernameTextField);
        panel.add(new JLabel("Password"), "gapy 8");
        panel.add(passwordTextField);
        panel.add(passwordStrengthStatus, "gapy 0");
        panel.add(new JLabel("Confirm Password"), "gapy 0");
        panel.add(confirmPassword);
        panel.add(registerButton, "gapy 20");
        panel.add(createLoginLabel(), "gapy 10");
        add(panel);
    }

    // Membuat checkbox gender options
    private Component createGenderPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
        jrMale = new JRadioButton("Male");
        jrFemale = new JRadioButton("Female");
        groupGender = new ButtonGroup();
        groupGender.add(jrMale);
        groupGender.add(jrFemale);
        jrMale.setSelected(true);
        panel.add(jrMale);
        panel.add(jrFemale);
        return panel;
    }

    // Membuat tombol login
    private Component createLoginLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
        JButton cmdLogin = new JButton("<html><a style='color: #0EF644;' href=\"#\">Sign in here</a></html>");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:3,3,3,3");
        cmdLogin.setContentAreaFilled(false);
        cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdLogin.addActionListener(e ->
        {
            FormsManager.getInstance().showForm(new LoginApp());
        });
        JLabel label = new JLabel("Already have an account ?");
        label.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);"
                + "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdLogin);
        return panel;
    }

    // Reset fields
    public void reset(boolean bln) {
        if (bln)
        {
            usernameTextField.setText("");
            fullnameTextField.setText("");
            passwordTextField.setText("");
        }
    }

    //Metode
    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getFullname() {
        return fullnameTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }

    public void submitUsers(ActionListener actionListener) {
        registerButton.addActionListener(actionListener);
    }

    // Deklarasi
    private JTextField fullnameTextField;
    private JRadioButton jrMale;
    private JRadioButton jrFemale;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JPasswordField confirmPassword;
    private ButtonGroup groupGender;
    private JButton registerButton;
    private PasswordStrengthStatus passwordStrengthStatus;
    private Icon checklist;
}
