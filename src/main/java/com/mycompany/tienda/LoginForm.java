/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tienda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author jpyntiquilla
 */
public class LoginForm extends JFrame {
    // Componentes del formulario
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, openMainFrameButton;
    private JLabel messageLabel;

    public LoginForm() {
        // Configurar el JFrame
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Campos de texto y etiquetas
        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setBounds(50, 50, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(150, 50, 150, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(50, 100, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 100, 150, 25);
        panel.add(passwordField);

        // Botones
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 100, 25);
        panel.add(loginButton);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(200, 150, 100, 25);
        panel.add(registerButton);

        // Botón para abrir MainFrame
        openMainFrameButton = new JButton("Abrir MainFrame");
        openMainFrameButton.setBounds(50, 190, 150, 25);
        panel.add(openMainFrameButton);

        // Mensaje
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 230, 300, 25);
        panel.add(messageLabel);

        // Añadir ActionListeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserModule();
            }
        });

        openMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainFrame();
            }
        });
    // Añadir el panel al JFrame
            add(panel);
    }
    
    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = UserManager.getInstance().getUser(username);

        if (user != null && user.getPassword().equals(password) && user.isActive()) {
            messageLabel.setText("Login exitoso");
            dispose();
            new MainFrame().setVisible(true);
        } else if (user != null && !user.isActive()) {
            messageLabel.setText("El usuario está inactivo");
        } else {
            messageLabel.setText("Credenciales incorrectas");
        }
    }

    private void openUserModule() {
        // Cierra el LoginForm actual
        this.dispose();

        // Abre el UserModule
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserModule().setVisible(true);
            }
        });
    }

    private void openMainFrame() {
        // Cierra el LoginForm actual
        this.dispose();

        // Abre el MainFrame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
