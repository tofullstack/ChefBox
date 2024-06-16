package model.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeClientePanel extends JPanel {

    public HomeClientePanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Cor de fundo suave

        // Título
        JLabel titleLabel = new JLabel("Bem-vindo de volta!", JLabel.CENTER);
        titleLabel.setForeground(new Color(80, 80, 80)); // Cor de texto mais escura
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Fonte maior e em negrito
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Espaçamento interno
        add(titleLabel, BorderLayout.NORTH);

        // Painel para os produtos
        JPanel productsPanel = new JPanel();
        productsPanel.setBackground(new Color(245, 245, 245));
        productsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); // Espaçamento externo

        // Exemplo de produtos
        String[] nomes = {"Kit Fitness", "Kit Café da Manhã", "Kit Churrasco", "Kit Vegetariano", "Kit Lanches Saudáveis"};
        String[] categorias = {"Fitness", "Café da Manhã", "Churrasco", "Vegetariano", "Lanches"};
        double[] precos = {199.99, 49.99, 149.99, 129.99, 79.99};

        for (int i = 0; i < nomes.length; i++) {
            JPanel productItemPanel = new JPanel(new BorderLayout());
            productItemPanel.setBackground(Color.WHITE);
            productItemPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230))); // Borda sutil

            // Carregar a imagem original
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Imagens/produto" + (i + 1) + ".jpg"));
            Image originalImage = originalIcon.getImage();

            // Redimensionar a imagem mantendo a proporção para 200x200 pixels
            Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);

            // Criar um ImageIcon com a imagem redimensionada
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            // Criar o JLabel com o ImageIcon redimensionado
            JLabel imageLabel = new JLabel(resizedIcon);
            imageLabel.setHorizontalAlignment(JLabel.CENTER); // Centralizar a imagem no JLabel
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento interno
            productItemPanel.add(imageLabel, BorderLayout.CENTER);

            // Descrição do produto
            JPanel descriptionPanel = new JPanel();
            descriptionPanel.setBackground(Color.WHITE);
            descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
            descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaçamento interno

            JLabel nameLabel = new JLabel(nomes[i]);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte maior e em negrito
            descriptionPanel.add(nameLabel);

            JLabel categoryLabel = new JLabel(categorias[i]);
            categoryLabel.setForeground(new Color(102, 102, 102)); // Cor cinza para a categoria
            descriptionPanel.add(categoryLabel);

            JLabel priceLabel = new JLabel("R$ " + precos[i]);
            priceLabel.setForeground(new Color(0, 102, 0)); // Cor verde para o preço
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte do preço um pouco menor
            descriptionPanel.add(priceLabel);

            // Botão de ação (por exemplo, comprar)
            JButton buyButton = new JButton("Comprar");
            buyButton.setBackground(new Color(255, 153, 0)); // Cor de fundo laranja para o botão
            buyButton.setForeground(Color.WHITE); // Texto branco no botão
            buyButton.setFocusPainted(false); // Remove o efeito de foco
            buyButton.setBorderPainted(false); // Remove a borda do botão
            descriptionPanel.add(buyButton);

            productItemPanel.add(descriptionPanel, BorderLayout.SOUTH);

            productsPanel.add(productItemPanel);
            productsPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Espaçamento entre os produtos
        }

        // Adicionar o painel de produtos a este painel principal
        add(productsPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Exemplo de Tela de Produtos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adicionar o painel principal à janela
        HomeClientePanel panel = new HomeClientePanel();
        frame.getContentPane().add(panel);

        // Ajustar o tamanho da janela, centralizar e exibir
        frame.pack();
        frame.setLocationRelativeTo(null); // Centralizar na tela
        frame.setVisible(true);
    }
}
