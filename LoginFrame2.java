
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.controller.KitController;
import model.controller.UsuarioController;
import model.entities.Kit;
import model.entities.Usuario;
import model.view.HomeClientePanel;
import model.view.MenuAdminDashboard;
import model.view.MenuClienteDashboard;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private UsuarioController usuarioController = new UsuarioController();

    public LoginFrame() {
        setTitle("CHEFBOX - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Define a janela para tela cheia

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titulo = new JLabel("CHEF");
        titulo.setForeground(new Color(13, 255, 13)); // verde especificado
        titulo.setFont(new Font("Century Gothic", Font.BOLD, 60));
        titulo.setBounds(535, 145, 200, 70); // Posição ajustada para "CHEF"
        contentPane.add(titulo);

        JLabel titulo2 = new JLabel("BOX");
        titulo2.setFont(new Font("MV Boli", Font.BOLD, 90));
        titulo2.setBounds(680, 160, 200, 100); // Posição ajustada para "BOX"
        contentPane.add(titulo2);

        JLabel lblSubtitulo = new JLabel("Acesse sua conta");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblSubtitulo.setBounds(590, 250, 200, 30); // Posição ajustada para o subtítulo
        contentPane.add(lblSubtitulo);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(510, 290, 50, 20); // Posição ajustada para o label de Email
        contentPane.add(lblEmail);

        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setBounds(570, 290, 300, 25); // Posição ajustada para o campo de texto de Email
        contentPane.add(textField);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSenha.setBounds(510, 340, 50, 20); // Posição ajustada para o label de Senha
        contentPane.add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBounds(570, 340, 300, 25); // Posição ajustada para o campo de texto de Senha
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(255, 215, 0)); // Amarelo especificado
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLogin.setBounds(510, 390, 130, 40); // Posição ajustada para o botão de Login
        contentPane.add(btnLogin);

        JButton btnLoginAdmin = new JButton("Login Funcionário");
        btnLoginAdmin.setBackground(new Color(154, 205, 50)); // Verde amarelado especificado
        btnLoginAdmin.setForeground(Color.WHITE);
        btnLoginAdmin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLoginAdmin.setBounds(670, 390, 210, 40); // Posição ajustada para o botão de Login Funcionário
        contentPane.add(btnLoginAdmin);

        JButton btnRegistrar = new JButton("Não possui uma conta? Registrar aqui.");
        btnRegistrar.setForeground(new Color(105, 105, 105));
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setBorderPainted(false); // Remove a borda do botão
        btnRegistrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRegistrar.setBounds(510, 450, 350, 25); // Posição ajustada para o botão de Registrar
        contentPane.add(btnRegistrar);

        // Ação do botão Login
        btnLogin.addActionListener(e -> {
            String email = textField.getText();
            String senha = new String(passwordField.getPassword());
            Usuario usuario = usuarioController.loginUsuario(email, senha);
            if (usuario != null && usuario.getRoleUser().equals("cliente")) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login bem sucedido como cliente! Abrindo Menu Cliente Dashboard.");
                Long idClienteLogado = usuario.getId();
                
                // Abrir MenuClienteDashboard com o usuário logado
                EventQueue.invokeLater(() -> {
                    MenuClienteDashboard menuClienteDashboard = new MenuClienteDashboard(idClienteLogado);
                    menuClienteDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    menuClienteDashboard.setVisible(true);
                    menuClienteDashboard.setLocationRelativeTo(null); // Centralizar na tela
                });

                dispose(); // Fechar o LoginFrame
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Usuário ou senha inválidos.");
            }
        });

        // Ação do botão Login Funcionário
        btnLoginAdmin.addActionListener(e -> {
            String email = textField.getText();
            String senha = new String(passwordField.getPassword());
            Usuario usuario = usuarioController.loginUsuario(email, senha);
            if (usuario != null && usuario.getRoleUser().equals("admin")) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login bem sucedido como admin! Olá!");
                abrirMenuAdmin();
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Usuário ou senha inválidos.");
            }
        });

    }

    private void abrirMenuAdmin() {
        MenuAdminDashboard menuAdminDashboard = new MenuAdminDashboard();
        menuAdminDashboard.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuAdminDashboard.setVisible(true);
        menuAdminDashboard.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0); // Fecha a aplicação ao fechar o MenuAdminFrame
            }
        });
        dispose(); // Fecha a janela de login após abrir o menu admin
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
