package com.feu.lab4.gui;

import com.feu.lab4.actions.PasswordManager;
import com.feu.lab4.exceptions.PasswordException;
import com.feu.lab4.model.Password;
import com.feu.lab4.utils.ImageUtil;

import javax.swing.*;
import java.awt.*;

public class PasswordInterface {

    private final JFrame frame;
    private final JPasswordField newPasswordField;
    private final JPasswordField confirmPasswordField;
    private final JButton saveButton;
    private final JButton resetButton;
    private final JButton exitButton;
    private final JButton showPasswordButton;
    private boolean isShowing = false;

    public PasswordInterface() {
        
        ImageIcon icon = new ImageIcon("C:\\Users\\rapha\\IdeaProjects\\CS0053-TN36-LAB-4\\CS0053-TN36-LAB-4\\src\\com\\feu\\lab4\\images\\lockIcon.png");
        frame = new JFrame("Create Password");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240)); 

        
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        newPasswordField = new JPasswordField(15);
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        
        saveButton = createButton("Save");
        resetButton = createButton("Reset");
        exitButton = createButton("Exit");

        
        showPasswordButton = new JButton();
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setContentAreaFilled(false);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.addActionListener(e -> togglePasswordVisibility());

       
        updateShowPasswordIcon();

       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(newPasswordLabel, gbc);

        gbc.gridx = 1;
        frame.add(newPasswordField, gbc);

        gbc.gridx = 2;
        frame.add(showPasswordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        frame.add(confirmPasswordField, gbc);

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        frame.add(buttonPanel, gbc);

        
        saveButton.addActionListener(e -> savePassword());
        resetButton.addActionListener(e -> resetFields());
        exitButton.addActionListener(e -> frame.dispose());

       
        frame.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237)); 
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(80, 30));
        button.setToolTipText(text + " the password");
        return button;
    }

    private void togglePasswordVisibility() {
        if (isShowing) {
            newPasswordField.setEchoChar('*');
        } else {
            newPasswordField.setEchoChar((char) 0);
        }
        isShowing = !isShowing;
        updateShowPasswordIcon();
    }

    private void updateShowPasswordIcon() {
        ImageIcon icon = isShowing 
                ? ImageUtil.getScaledIcon("CS0053-TN36-LAB-4\\src\\com\\feu\\lab4\\images\\eyeIconnotHide.png", 15, 15) 
                : ImageUtil.getScaledIcon("CS0053-TN36-LAB-4\\src\\com\\feu\\lab4\\images\\eyeIcon.png", 15, 15);
        showPasswordButton.setIcon(icon);
    }

    private void resetFields() {
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    private void savePassword() {
        String newPassword = String.valueOf(newPasswordField.getPassword());
        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
        Password password = new Password(newPassword, confirmPassword);
        PasswordManager passwordManager = new PasswordManager();

        try {
            passwordManager.addPassword(password);
            JOptionPane.showMessageDialog(frame, "Password saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            resetFields();
        } catch (PasswordException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
