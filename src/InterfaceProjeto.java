import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InterfaceProjeto extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private SistemaOCI sistema;
    private JTable tabelaPacientes;

    // Criacao das telas
    public InterfaceProjeto() {
        sistema = new SistemaOCI();
        criarExemplosTeste(); // Adiciona alguns dados de exemplo

        setSize(1000, 700);
        setTitle("MONITORA+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // CardLayout para trocar de telas
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        add(painelPrincipal);

        // Tela inicial
        JPanel telaInicial = criarTelaInicial();
        painelPrincipal.add(telaInicial, "TelaInicial");

        // Tela de lista de pacientes
        JPanel listaPacientesPanel = criarListaPacientesPanel();
        painelPrincipal.add(listaPacientesPanel, "ListaDePacientes");

        // Tela de cadastro
        JPanel cadastroPanel = criarCadastroPanel();
        painelPrincipal.add(cadastroPanel, "CadastroPaciente");

        // Tela de detalhes
        JPanel detalhesPanel = criarDetalhesPanel();
        painelPrincipal.add(detalhesPanel, "DetalhesPaciente");

        setVisible(true);
    }

    private void criarExemplosTeste() {
        OCI oci1 = new OCI("09.01.01.003-0", "Oncologia", "Oncologia", List.of("Consulta", "Exame"));
        oci1.setDataInicio(LocalDate.now().minusDays(10));

        OCI oci2 = new OCI("09.02.01.002-6", "Cardiologia", "Cardiologia", List.of("EKG", "Consulta"));
        oci2.setDataInicio(LocalDate.now().minusDays(5));

        sistema.cadastrarPaciente("João Silva", "123.456.789-00", "(83) 99999-9999", "Maria Souza", "UBS Centro","(83) 98888-8888", oci1);
        sistema.cadastrarPaciente("Ana Oliveira", "987.654.321-00", "(83) 98888-8888", "Carlos Andrade", "UBS Liberdade", "(83) 99999-9999",  oci2);
    }

    private JPanel criarTelaInicial() {
        JPanel telaInicial = new JPanel(null);
        telaInicial.setBackground(new Color(226, 226, 226));

        JLabel titulo = new JLabel("OFERTA DE CUIDADOS INTEGRADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 25));
        titulo.setBounds(120, 80, 800, 30);
        telaInicial.add(titulo);

        JLabel subtitulo = new JLabel("MONITORA+", SwingConstants.CENTER);
        subtitulo.setFont(new Font("SansSerif", Font.BOLD, 42));
        subtitulo.setBounds(100, 140, 800, 50);
        telaInicial.add(subtitulo);

        // Botao de Listar Pacientes e suas acoes
        JButton listaDePacientes = criarBotao("Lista de Pacientes", 250);
        listaDePacientes.addActionListener(this);
        telaInicial.add(listaDePacientes);

        // Botao de Registrar Nova OCI e suas acoes
        JButton registrarNovaOCI = criarBotao("Registrar Nova OCI", 350);
        registrarNovaOCI.addActionListener(e -> JOptionPane.showMessageDialog(null, "Em breve!", "Informação", JOptionPane.INFORMATION_MESSAGE));
        telaInicial.add(registrarNovaOCI);

        return telaInicial;
    }

    private JPanel criarListaPacientesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Lista de Pacientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titulo, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(5, 1, 5, 5));
        painelBotoes.setPreferredSize(new Dimension(200,60));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnCadastrar = criarBotaoLista("Cadastrar Paciente");
        btnCadastrar.addActionListener(e -> cardLayout.show(painelPrincipal, "CadastroPaciente"));
        //btnCadastrar.setBackground(new Color(245, 205, 51));

        JButton btnAtualizar = criarBotaoLista("Atualizar Processo");
        btnAtualizar.addActionListener(this);
        //btnAtualizar.setBackground(new Color(245, 205, 51));

        JButton btnDetalhes = criarBotaoLista("Detalhes do Paciente");
        btnDetalhes.addActionListener(e -> mostrarDetalhesPaciente());
        //btnDetalhes.setBackground(new Color(245, 205, 51));

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDetalhes);

        // Espaço extra antes do botão "Voltar"
        painelBotoes.add(Box.createVerticalGlue());

        JButton btnVoltar = criarBotaoLista("Voltar");
        btnVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "TelaInicial"));
        //btnVoltar.setBackground(new Color(245, 205, 51));

        painelBotoes.add(btnVoltar);

        panel.add(painelBotoes, BorderLayout.WEST);

        // Tabela de pacientes
        String[] colunas = {"ID", "Nome", "CPF", "Tipo OCI", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaPacientes = new JTable(model);
        atualizarTabelaPacientes();

        JScrollPane scrollPane = new JScrollPane(tabelaPacientes);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel criarCadastroPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField agenteField = new JTextField();
        JTextField ubsField = new JTextField();
        JTextField telUbsField = new JTextField();
        JComboBox<String> tipoOciCombo = new JComboBox<>(new String[]{"Oncologia", "Cardiologia", "Ortopedia", "Oftalmologia", "Otorrinolaringologia"});
        JTextField dataField = new JTextField(LocalDate.now().toString());

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Agente de Saúde:"));
        panel.add(agenteField);
        panel.add(new JLabel("Nome da UBS:"));
        panel.add(ubsField);
        panel.add(new JLabel("Telefone da UBS:"));
        panel.add(telUbsField);
        panel.add(new JLabel("Tipo OCI:"));
        panel.add(tipoOciCombo);
        panel.add(new JLabel("Data Início (AAAA-MM-DD):"));
        panel.add(dataField);

        dataField.addActionListener(e -> {
            try {
                // Validação da data ANTES de criar a OCI
                LocalDate dataInicio = TratadorExcecoes.validarData(dataField.getText());

                // Validação do CPF (exemplo)
                TratadorExcecoes.validarCPF(cpfField.getText());

                String tipo = (String) tipoOciCombo.getSelectedItem();
                OCI oci = new OCI(gerarCodigoOCI(), "Consulta " + tipo, tipo, List.of("Consulta", "Exame básico"));
                oci.setDataInicio(dataInicio); // Usa a data já validada

                // Cadastra o paciente (validações internas já feitas)
                sistema.cadastrarPaciente(
                        nomeField.getText(),
                        cpfField.getText(),
                        telefoneField.getText(),
                        agenteField.getText(),
                        ubsField.getText(),
                        telUbsField.getText(),
                        oci
                );

                // Atualiza a interface
                atualizarTabelaPacientes();
                cardLayout.show(painelPrincipal, "ListaDePacientes");
                JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (IllegalArgumentException ex) {
                // Erro específico de validação (data, CPF, etc.)
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Erro genérico (ex: banco de dados, etc.)
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> cardLayout.show(painelPrincipal, "ListaDePacientes"));

        panel.add(dataField);
        panel.add(btnCancelar);

        return panel;
    }

    private JPanel criarDetalhesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea detalhesArea = new JTextArea();
        detalhesArea.setEditable(false);
        detalhesArea.setFont(new Font("Monospaced", Font.PLAIN, 20));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "ListaDePacientes"));

        panel.add(new JScrollPane(detalhesArea), BorderLayout.CENTER);
        panel.add(btnVoltar, BorderLayout.SOUTH);

        return panel;
    }

    // Config dos botoes da tela inicial
    private JButton criarBotao(String texto, int y) {
        JButton btn = new JButton(texto);
        // Posicao dos botoes da tela inicial
        btn.setBounds(380, y, 230, 70);
        btn.setFont(new Font("Arial", Font.PLAIN, 20));
        btn.setBackground(new Color(245, 205, 51));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }

    private JButton criarBotaoLista(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        return btn;
    }

    private void atualizarTabelaPacientes() {
        DefaultTableModel model = (DefaultTableModel) tabelaPacientes.getModel();
        model.setRowCount(0); // Limpa a tabela

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Paciente p : sistema.listarPacientes()) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getCpf(),
                    p.getOci().getTipo(),
                    p.getOci().getStatus()
            });
        }
    }

    private void mostrarDetalhesPaciente() {
        int linhaSelecionada = tabelaPacientes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um paciente primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tabelaPacientes.getValueAt(linhaSelecionada, 0);
        Paciente paciente = sistema.buscarPaciente(id);

        if (paciente != null) {
            JTextArea detalhesArea = ((JTextArea)((JScrollPane)((JPanel)painelPrincipal.getComponent(3)).getComponent(0)).getViewport().getView());
            detalhesArea.setText(formatarDetalhesPaciente(paciente));
            cardLayout.show(painelPrincipal, "DetalhesPaciente");
        }
    }

    private String formatarDetalhesPaciente(Paciente p) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        sb.append("=== DADOS DO PACIENTE ===\n");
        sb.append(String.format("%-15s: %s\n", "ID", p.getId()));
        sb.append(String.format("%-15s: %s\n", "Nome", p.getNome()));
        sb.append(String.format("%-15s: %s\n", "CPF", p.getCpf()));
        sb.append(String.format("%-15s: %s\n", "Telefone", p.getTelefone()));
        sb.append(String.format("%-15s: %s\n", "Agente Saúde", p.getAgenteSaude()));
        sb.append(String.format("%-15s: %s\n", "UBS", p.getNomeUBS()));

        sb.append("\n=== DADOS DA OCI ===\n");
        sb.append(String.format("%-15s: %s\n", "Código", p.getOci().getCodigo()));
        sb.append(String.format("%-15s: %s\n", "Tipo", p.getOci().getTipo()));
        sb.append(String.format("%-15s: %s\n", "Data Início", p.getOci().getDataInicio().format(formatter)));
        sb.append(String.format("%-15s: %s\n", "Data Limite", p.getOci().getDataLimite().format(formatter)));
        sb.append(String.format("%-15s: %s\n", "Status", p.getOci().getStatus()));

        sb.append("\nProcedimentos:\n");
        for (String proc : p.getOci().getProcedimentos()) {
            sb.append("- ").append(proc).append("\n");
        }

        return sb.toString();
    }

    private String gerarCodigoOCI() {
        return "OCI-" + (sistema.listarPacientes().size() + 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Lista de Pacientes")) {
            cardLayout.show(painelPrincipal, "ListaDePacientes");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceProjeto());
    }
}