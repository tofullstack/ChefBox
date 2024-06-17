package model.view;

import javax.swing.*;

import model.controller.ClienteController;
import model.controller.DietaController;
import model.controller.EnderecoController;
import model.controller.UsuarioController;
import model.entities.Cliente;
import model.entities.Dieta;
import model.entities.Endereco;
import model.entities.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JTextField txtRua;
    private JTextField txtNumero;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtEstado;
    private JTextField txtNomeDieta;
    private JTextField txtNome;
    private JTextField txtCPF;
    private JTextField txtTelefone;

    public RegisterFrame() {
        setTitle("Registrar Nova Conta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500); // Tamanho da janela de registro
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout nulo para posicionamento absoluto

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 30, 100, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(160, 30, 300, 25);
        panel.add(txtEmail);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 70, 100, 25);
        panel.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(160, 70, 300, 25);
        panel.add(txtSenha);

        JLabel lblRua = new JLabel("Rua:");
        lblRua.setBounds(50, 110, 100, 25);
        panel.add(lblRua);

        txtRua = new JTextField();
        txtRua.setBounds(160, 110, 300, 25);
        panel.add(txtRua);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(50, 150, 100, 25);
        panel.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(160, 150, 100, 25);
        panel.add(txtNumero);

        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setBounds(50, 190, 100, 25);
        panel.add(lblBairro);

        txtBairro = new JTextField();
        txtBairro.setBounds(160, 190, 300, 25);
        panel.add(txtBairro);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(50, 230, 100, 25);
        panel.add(lblCidade);

        txtCidade = new JTextField();
        txtCidade.setBounds(160, 230, 300, 25);
        panel.add(txtCidade);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(50, 270, 100, 25);
        panel.add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setBounds(160, 270, 100, 25);
        panel.add(txtEstado);

        JLabel lblNomeDieta = new JLabel("Nome da Dieta:");
        lblNomeDieta.setBounds(50, 310, 100, 25);
        panel.add(lblNomeDieta);

        txtNomeDieta = new JTextField();
        txtNomeDieta.setBounds(160, 310, 300, 25);
        panel.add(txtNomeDieta);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 350, 100, 25);
        panel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(160, 350, 300, 25);
        panel.add(txtNome);

        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(50, 390, 100, 25);
        panel.add(lblCPF);

        txtCPF = new JTextField();
        txtCPF.setBounds(160, 390, 150, 25);
        panel.add(txtCPF);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(320, 390, 100, 25);
        panel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(400, 390, 150, 25);
        panel.add(txtTelefone);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(154, 205, 50)); // Cor de fundo verde amarelado
        btnRegistrar.setForeground(Color.WHITE); // Cor do texto branco
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegistrar.setBounds(220, 430, 150, 35);
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarNovoUsuario();
            }
        });
        panel.add(btnRegistrar);

        add(panel, BorderLayout.CENTER);
    }

    private void registrarNovoUsuario() {
        // Coleta das informações dos campos
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        String rua = txtRua.getText();
        String numeroStr = txtNumero.getText();
        String bairro = txtBairro.getText();
        String cidade = txtCidade.getText();
        String estado = txtEstado.getText();
        String nomeDieta = txtNomeDieta.getText();
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        String telefone = txtTelefone.getText();

        // Validar se algum campo obrigatório está vazio
        if (email.isEmpty() || senha.isEmpty() || rua.isEmpty() || numeroStr.isEmpty() ||
            bairro.isEmpty() || cidade.isEmpty() || estado.isEmpty() || nomeDieta.isEmpty() ||
            nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(RegisterFrame.this,
                    "Todos os campos devem ser preenchidos!",
                    "Erro de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar se o número é um valor numérico
        int numero;
        try {
            numero = Integer.parseInt(numeroStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(RegisterFrame.this,
                    "Número da residência deve ser um valor numérico!",
                    "Erro de Registro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criação dos objetos necessários
        Usuario novoUsuario = new Usuario(email, senha, "cliente");
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado);
        Dieta dieta = new Dieta(null, nomeDieta);
        Cliente clienteNovo = new Cliente(novoUsuario, nome, cpf, telefone, endereco, dieta, true);

        // Adicionar os objetos aos respectivos controladores
        UsuarioController usuarioController = new UsuarioController();
        EnderecoController enderecoController = new EnderecoController();
        DietaController dietaController = new DietaController();
        ClienteController clienteController = new ClienteController();
        usuarioController.adicionarUsuario(novoUsuario);
        enderecoController.adicionarEndereco(endereco);
        dietaController.adicionarDieta(dieta);
        clienteController.adicionarCliente(clienteNovo);

        // Exibir mensagem de sucesso
        JOptionPane.showMessageDialog(RegisterFrame.this, "Registro realizado com sucesso!");

        // Fechar a janela após o registro
        dispose();
    }
}
