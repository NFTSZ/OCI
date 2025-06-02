import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaDePacientes extends JPanel implements ActionListener {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    JButton btnCadastrar = new JButton("cadastrar");
    JButton btnRemover = new JButton("Remover");
    JButton btnAtualizar = new JButton("Atualizar");
    JButton btnDetalhes = new JButton("Detalhes");
    JButton btnVoltar = new JButton("Voltar");


    private InterfaceProjeto interfaceP;

    public ListaDePacientes() {
        setLayout(new BorderLayout(10, 10));// layout principal


        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        add(painelPrincipal);

        // Tela inicial
        JPanel telaInicial = new JPanel(null);
        telaInicial.setBackground(new Color(226, 226, 226));
        painelPrincipal.add(telaInicial, "TelaInicial");


        // Título no topo (NORTH)
        JLabel titulo = new JLabel("Lista de Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Painel WEST com botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 1, 5, 5)); // 5 linhas, 1 coluna, espaçamento



        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDetalhes);
        painelBotoes.add(btnVoltar);
        btnVoltar.addActionListener(this);

        add(painelBotoes, BorderLayout.WEST);

        // Painel EAST com tabela (grid) dos pacientes
        String[] colunas = {"CPF", "Código OCI", "Data Início OCI"};
        Object[][] dados = {
                {"123.456.789-00", "OCI001", "01/01/2024"},
                {"987.654.321-00", "OCI002", "15/02/2024"},
                {"111.222.333-44", "OCI003", "10/03/2024"},
        };

        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            // impedir edição das células
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabelaPacientes = new JTable(model);
        tabelaPacientes.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabelaPacientes);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        add(scrollPane, BorderLayout.CENTER);

        // Ajuste da largura do painel de botões
        painelBotoes.setPreferredSize(new Dimension(150, getHeight()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnVoltar) {
            cardLayout.show(painelPrincipal, "InterfaceProjeto");
        }
    }

    // Para testar o painel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Exemplo Lista de Pacientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        frame.add(new ListaDePacientes());

        frame.setVisible(true);
    }


}
