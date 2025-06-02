import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceProjeto extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private JButton listaDePacientes;
    private JButton registrarNovaOCI;

    public InterfaceProjeto() {
        setSize(800, 500);
        setTitle("MONITORA+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // CardLayout para trocar de telas
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        add(painelPrincipal);

        // Tela inicial
        JPanel telaInicial = new JPanel(null);
        telaInicial.setBackground(new Color(226, 226, 226));
        painelPrincipal.add(telaInicial, "TelaInicial");

        // Título superior
        JLabel titulo = new JLabel("OFERTA DE CUIDADOS INTEGRADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBounds(0, 20, 800, 30);
        telaInicial.add(titulo);

        JLabel subtitulo = new JLabel("MONITORA+", SwingConstants.CENTER);
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        subtitulo.setBounds(0, 60, 800, 50);
        telaInicial.add(subtitulo);

        // Botões
        listaDePacientes = criarBotao("Lista de Pacientes", 180);
        telaInicial.add(listaDePacientes);
        listaDePacientes.addActionListener(this);

        registrarNovaOCI = criarBotao("Registrar Nova OCI", 250);
        telaInicial.add(registrarNovaOCI);
        registrarNovaOCI.addActionListener(this);


        // Tela de lista de pacientes
        ListaDePacientes listaPacientesPanel = new ListaDePacientes();
        painelPrincipal.add(listaPacientesPanel, "ListaDePacientes");

        setVisible(true);
    }

    private JButton criarBotao(String texto, int y) {
        JButton btn = new JButton(texto);
        btn.setBounds(280, y, 230, 50);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        btn.setBackground(new Color(245, 205, 51));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registrarNovaOCI) {
            JOptionPane.showMessageDialog(null, "Botão da resgistro funcionando", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == listaDePacientes) {
            cardLayout.show(painelPrincipal, "ListaDePacientes");
        }


    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    public static void main(String[] args) {
        new InterfaceProjeto();
    }
}
