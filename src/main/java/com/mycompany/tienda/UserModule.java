/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tienda;

/**
 *
 * @author jpyntiquilla
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UserModule extends JFrame {
    
    private JTextField usernameField, searchField;
    private JPasswordField passwordField;
    private JCheckBox activeCheckBox;
    private JComboBox<String> roleComboBox;
    private JButton addButton, searchButton, updateButton, deactivateButton, logoutButton;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JLabel messageLabel;


    public UserModule() {
        // Configurar el JFrame
        setTitle("Módulo de Usuarios");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Campos de texto y etiquetas
        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setBounds(20, 20, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(120, 20, 150, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(20, 60, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(120, 60, 150, 25);
        panel.add(passwordField);

        JLabel activeLabel = new JLabel("Activo:");
        activeLabel.setBounds(20, 100, 100, 25);
        panel.add(activeLabel);

        activeCheckBox = new JCheckBox();
        activeCheckBox.setBounds(120, 100, 150, 25);
        panel.add(activeCheckBox);

        JLabel roleLabel = new JLabel("Perfil:");
        roleLabel.setBounds(20, 140, 100, 25);
        panel.add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"Administrador", "Vendedor"});
        roleComboBox.setBounds(120, 140, 150, 25);
        panel.add(roleComboBox);

        // Botones
        addButton = new JButton("Añadir Usuario");
        addButton.setBounds(20, 180, 150, 25);
        panel.add(addButton);

        updateButton = new JButton("Actualizar Usuario");
        updateButton.setBounds(180, 180, 150, 25);
        panel.add(updateButton);

        deactivateButton = new JButton("Inactivar Usuario");
        deactivateButton.setBounds(340, 180, 150, 25);
        panel.add(deactivateButton);

        searchField = new JTextField(20);
        searchField.setBounds(20, 220, 150, 25);
        panel.add(searchField);

        searchButton = new JButton("Buscar Usuario");
        searchButton.setBounds(180, 220, 150, 25);
        panel.add(searchButton);

        // Botón de logout
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(340, 220, 150, 25);
        panel.add(logoutButton);

        // Crear la tabla y su modelo
        String[] columnNames = {"Usuario", "Contraseña", "Activo", "Perfil"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(20, 260, 650, 200);
        panel.add(scrollPane);

        // Mensaje
        messageLabel = new JLabel("");
        messageLabel.setBounds(20, 470, 450, 25);
        panel.add(messageLabel);

        // Añadir ActionListeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deactivateUser();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUser();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginForm().setVisible(true);
            }
        });

        
    // Añadir el panel al JFrame
          add(panel);
          listUsers();
    }
    
    private void addUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean active = activeCheckBox.isSelected();
        String role = (String) roleComboBox.getSelectedItem();

        User user = new User(username, password, active, role);
        boolean success = UserManager.getInstance().addUser(user);

        if (success) {
            messageLabel.setText("Usuario añadido exitosamente");
            clearFields();
            listUsers();
        } else {
            messageLabel.setText("El usuario ya existe");
        }
    }

    private void updateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean active = activeCheckBox.isSelected();
        String role = (String) roleComboBox.getSelectedItem();

        User user = new User(username, password, active, role);
        boolean success = UserManager.getInstance().updateUser(user);

        if (success) {
            messageLabel.setText("Usuario actualizado exitosamente");
            clearFields();
            listUsers();
        } else {
            messageLabel.setText("El usuario no existe");
        }
    }

    private void deactivateUser() {
        String username = usernameField.getText();
        boolean success = UserManager.getInstance().deactivateUser(username);

        if (success) {
            messageLabel.setText("Usuario inactivado exitosamente");
            clearFields();
            listUsers();
        } else {
            messageLabel.setText("El usuario no existe");
        }
    }

    private void searchUser() {
        String username = searchField.getText();
        User user = UserManager.getInstance().getUser(username);

        if (user != null) {
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            activeCheckBox.setSelected(user.isActive());
            roleComboBox.setSelectedItem(user.getRole());
            messageLabel.setText("Usuario encontrado");
        } else {
            messageLabel.setText("Usuario no encontrado");
        }
    }

    private void listUsers() {
        HashMap<String, User> users = UserManager.getInstance().getUsers();
        tableModel.setRowCount(0); // Limpiar la tabla
        for (User user : users.values()) {
            Object[] row = {user.getUsername(), user.getPassword(), user.isActive(), user.getRole()};
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        activeCheckBox.setSelected(false);
        roleComboBox.setSelectedIndex(0);
        searchField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserModule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
