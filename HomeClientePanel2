package model.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.controller.AdminController;
import model.controller.CarrinhoController;
import model.controller.EntregaProgramadaController;
import model.controller.KitController;
import model.controller.PedidoController;
import model.controller.TipoPagamentoController;
import model.controller.VendaController;
import model.entities.Admin;
import model.entities.Carrinho;
import model.entities.EntregaProgramada;
import model.entities.Kit;
import model.entities.Pedido;
import model.entities.TipoPagamento;
import model.entities.Venda;

public class HomeClientePanel extends JPanel {

	private static Long idClienteLogado;
    private List<Kit> kitsDisponiveis;

    public HomeClientePanel(Long idClienteLogado) {
    	this.idClienteLogado = idClienteLogado;
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
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));

        // Carregar os kits disponíveis
        KitController kitController = new KitController();
        kitsDisponiveis = kitController.obterTodosKits();

        for (Kit kit : kitsDisponiveis) {
            JPanel productItemPanel = new JPanel(new BorderLayout());
            productItemPanel.setBackground(Color.WHITE);
            productItemPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230))); // Borda sutil

            // Carregar a imagem original
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Imagens/produto" + kit.getId() + ".jpg"));
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

            JLabel nameLabel = new JLabel(kit.getNome());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte maior e em negrito
            descriptionPanel.add(nameLabel);

            JLabel categoryLabel = new JLabel(kit.getCategoria());
            categoryLabel.setForeground(new Color(102, 102, 102)); // Cor cinza para a categoria
            descriptionPanel.add(categoryLabel);

            JLabel priceLabel = new JLabel("R$ " + kit.getPreco());
            priceLabel.setForeground(new Color(0, 102, 0)); // Cor verde para o preço
            priceLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Fonte do preço um pouco menor
            descriptionPanel.add(priceLabel);

            // Botão de ação (por exemplo, comprar)
            JButton buyButton = new JButton("Comprar");
            buyButton.setBackground(new Color(255, 153, 0)); // Cor de fundo laranja para o botão
            buyButton.setForeground(Color.WHITE); // Texto branco no botão
            buyButton.setFocusPainted(false); // Remove o efeito de foco
            buyButton.setBorderPainted(false); // Remove a borda do botão
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarPedido(kit);
                }
            });
            descriptionPanel.add(buyButton);

            productItemPanel.add(descriptionPanel, BorderLayout.SOUTH);

            productsPanel.add(productItemPanel);
            productsPanel.add(Box.createRigidArea(new Dimension(20, 20))); // Espaçamento entre os produtos
        }

        // Adicionar o painel de produtos a este painel principal
        add(productsPanel, BorderLayout.CENTER);
    }

    private void adicionarPedido(Kit kitSelecionado) {
        Pedido pedido = new Pedido(null, idClienteLogado, kitSelecionado.getId()); // TODO
        PedidoController pedidoController = new PedidoController();
        pedido = pedidoController.adicionarPedido(pedido);

        if (pedido != null) {
            encaminharPedidoParaVenda(idClienteLogado, pedido);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o pedido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void encaminharPedidoParaVenda(Long idCliente, Pedido pedido) {
        int confirmar = JOptionPane.showConfirmDialog(this, "Deseja confirmar o pedido com o kit selecionado?", "Confirmar Pedido", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String dataEntregaStr = JOptionPane.showInputDialog(this, "Digite a data de entrega programada (dd/MM/yyyy):");
            Date dataEntregaProgramada = null;

            try {
                dataEntregaProgramada = new SimpleDateFormat("dd/MM/yyyy").parse(dataEntregaStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Data inválida. Por favor, tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EntregaProgramada entregaProgramada = new EntregaProgramada();
            EntregaProgramadaController entregaProgramadaController = new EntregaProgramadaController();
            entregaProgramada.setDataEntrega(dataEntregaProgramada);
            entregaProgramada = entregaProgramadaController.adicionarEntregaProgramada(entregaProgramada);

            TipoPagamentoController tipoPagamentoController = new TipoPagamentoController();
            List<TipoPagamento> tiposPagamento = tipoPagamentoController.obterTodosTipoPagamentos();
            Object[] options = tiposPagamento.stream().map(tp -> tp.getIdPagamento() + ". " + tp.getDescricao()).toArray();
            String selecionado = (String) JOptionPane.showInputDialog(this, "Selecione o método de pagamento:", "Método de Pagamento", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (selecionado != null) {
                Long idTipoPagamento = Long.parseLong(selecionado.split("\\.")[0]);

                AdminController adminController = new AdminController();
                List<Admin> admins = adminController.obterTodosAdmins();
                if (!admins.isEmpty()) {
                    Admin admin = admins.get(0);
                    Venda venda = new Venda();
                    venda.setCliente(idCliente);
                    venda.setDataAdmissao(new Date());
                    venda.setVendaRealizada(true);
                    venda.setEntregaProgramada(entregaProgramada);
                    venda.setAdminUser(admin.getUsuario().getId());
                    venda.setTipoPagamento(idTipoPagamento);
                    venda.setDataConclusao(new Date());
                    venda.setDataPagamento(new Date());

                    VendaController vendaController = new VendaController();
                    vendaController.adicionarVenda(venda);

                    CarrinhoController carrinhoController = new CarrinhoController();
                    Carrinho carrinho = new Carrinho(venda.getId(), pedido.getId());
                    carrinhoController.adicionarCarrinho(carrinho);

                    JOptionPane.showMessageDialog(this, "Pedido confirmado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: nenhum administrador disponível.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Método de pagamento inválido. Pedido não confirmado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pedido não confirmado. Voltando ao menu anterior...", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
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
        HomeClientePanel panel = new HomeClientePanel(idClienteLogado);
        frame.getContentPane().add(panel);

        // Ajustar o tamanho da janela, centralizar e exibir
        frame.pack();
        frame.setLocationRelativeTo(null); // Centralizar na tela
        frame.setVisible(true);
    }
}
