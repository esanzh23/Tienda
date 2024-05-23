/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author jpyntiquilla
 */
public class MainFrame extends JFrame {
    private JButton logoutButton;
    private JButton productModuleButton;

    public MainFrame() {
        setTitle("Sistema de Gestión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Crear el menú "Módulos"
        JMenu moduloMenu = new JMenu("Módulos");

        // Crear la opción de menú "ProductModule"
        JMenuItem productModuleItem = new JMenuItem("Product Module");

        // Configurar el ActionListener para la opción de menú "ProductModule"
        productModuleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProductModule();
            }
        });

        // Agregar la opción de menú "ProductModule" al menú "Módulos"
        moduloMenu.add(productModuleItem);

        // Agregar el menú "Módulos" a la barra de menú
        menuBar.add(moduloMenu);

        // Crear la opción de menú "Cerrar Sesión"
        JMenuItem cerrarSesionItem = new JMenuItem("Cerrar Sesión");

        // Configurar el ActionListener para la opción de menú "Cerrar Sesión"
        cerrarSesionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        // Agregar la opción de menú "Cerrar Sesión" al menú "Módulos"
        moduloMenu.add(cerrarSesionItem);

        // Establecer la barra de menú en el JFrame
        setJMenuBar(menuBar);
    }

    private void openProductModule() {
        // Aquí puedes abrir el ProductModule
        ProductModule productModule = new ProductModule();
        productModule.setVisible(true);
    }

    private void cerrarSesion() {
        // Cerrar la ventana actual del MainFrame
        dispose();

        // Abrir una nueva instancia de la ventana de inicio de sesión (LoginForm)
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}
