package model.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuClienteDashboard extends JFrame {
	
	private static Long idCLienteLogado;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private List<JPanel> buttons = new ArrayList<>();
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuClienteDashboard frame = new MenuClienteDashboard(idCLienteLogado);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuClienteDashboard(Long idClienteLogado) {
    	this.idCLienteLogado = idClienteLogado;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1299, 822);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout()); // Usar BorderLayout para contentPane
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new java.awt.Dimension(179, 797));
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);
        contentPane.add(panel, BorderLayout.WEST); // Adicionar painel de menu à esquerda

        // Adiciona os botões ao painel
        JPanel Button1 = createButton(panel, "Home", 180, "home");
        JPanel ButtonCarrinho = createButton(panel, "Carrinho", 221, "carrinho");
        JPanel ButtonConfiguracao = createButton(panel, "Configuração", 262, "configuracao");
      /*  JPanel ButtonPagamento = createButton(panel, "Pagamento", 303, "pagamento");
        JPanel ButtonCliente = createButton(panel, "Clientes", 344, "clientes");
        JPanel ButtonUsuario = createButton(panel, "Usuários", 385, "usuarios");
        JPanel ButtonAdmin = createButton(panel, "Administradores", 426, "administradores");*/

        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        contentPane.add(contentPanel, BorderLayout.CENTER); // Adicionar painel de conteúdo ao centro

        // adiciona os diferentes painéis de conteúdo ao CardLayout
        contentPanel.add(new HomeClientePanel(idClienteLogado), "home");
        contentPanel.add(new CarrinhoPanel(), "carrinho");
        contentPanel.add(new ConfiguracaoPanel(null, null, null), "configuracao");
       /* contentPanel.add(new PagamentoPanel(), "pagamento");
        contentPanel.add(new ClientesPanel(), "clientes");
        contentPanel.add(new UsuariosPanel(), "usuarios");
        contentPanel.add(new AdministradoresPanel(), "administradores");*/

        cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "home"); // mostra o painel inicial
    }

	private JPanel createButton(JPanel parentPanel, String text, int yPosition, String cardName) {
        JPanel button = new JPanel();
        button.setLayout(null);
        button.setBackground(Color.WHITE);
        button.setBounds(0, yPosition, 177, 31);
        parentPanel.add(button);

        JPanel indicator = new JPanel();
        indicator.setOpaque(false);
        indicator.setBackground(new Color(188, 231, 177));
        indicator.setBounds(0, 0, 10, 31);
        button.add(indicator);

        JLabel label = new JLabel(text);
        label.setForeground(new Color(188, 231, 177));
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setBounds(38, 10, 116, 13);
        button.add(label);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick(button);
                onLeaveClickOtherButtons(button);
                cardLayout.show(contentPanel, cardName);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                onHover(button);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.getBackground().equals(new Color(245, 245, 245))) {
                    button.setBackground(Color.WHITE);
                }
            }
        });

        buttons.add(button);
        return button;
    }

    private void onHover(JPanel panel) {
        panel.setBackground(new Color(205, 136, 205));
    }

    private void onClick(JPanel panel) {
        panel.setBackground(new Color(245, 245, 245));
    }

    private void onLeaveClickOtherButtons(JPanel clickedPanel) {
        for (JPanel button : buttons) {
            if (button != clickedPanel) {
                button.setBackground(Color.WHITE);
                ((JComponent) button.getComponent(0)).setOpaque(false);  // reseta o indicador
            } else {
                ((JComponent) button.getComponent(0)).setOpaque(true);  // ativa o indicador do botão clicado
            }
        }
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
