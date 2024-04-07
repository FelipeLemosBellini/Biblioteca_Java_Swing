package Presentation.Screens;

import core.entities.Book;
import core.use_cases.BookUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibraryScreen extends JFrame {
    private JTable tabela;
    private DefaultTableModel model;
    private JTextField categoriaField;
    private JTextField nomeField;
    private JTextField autorField;
    private JTextField isbnField;
    private JComboBox menuBar;

    public LibraryScreen() {
        definirConfiguracoesDeJanela();

        setVisible(true);
    }

    private void definirConfiguracoesDeJanela() {
        setTitle("Agenda de Contatos");
        setSize(1200, 780);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        definirConfiguracoesDeTabela();
        definirConfiguracoesDeMenu();
    }

    private void definirConfiguracoesDeTabela() {
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Id");
        model.addColumn("Nome");
        model.addColumn("Autor");
        model.addColumn("Categoria");
        model.addColumn("ISBN");

        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void definirConfiguracoesDeMenu() {
        JPanel botoesPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        categoriaField = new JTextField(15);
        nomeField = new JTextField(15);
        autorField = new JTextField(15);
        isbnField = new JTextField(15);
        menuBar = new JComboBox();
        JButton adicionarButton = new JButton("Adicionar");
        JButton excluirButton = new JButton("Excluir");
        JButton verContatoButton = new JButton("Ver Livro");

        adicionarButton.addActionListener(this::adicionarLinha);
        excluirButton.addActionListener(this::excluirLinha);
        verContatoButton.addActionListener(this::verContato);

        botoesPanel.add(new JLabel("Nome:"));
        botoesPanel.add(nomeField);

        botoesPanel.add(new JLabel("Autor"));
        botoesPanel.add(autorField);

        botoesPanel.add(new JLabel("Categoria:"));
        botoesPanel.add(categoriaField);

        botoesPanel.add(new JLabel("ISBN"));
        botoesPanel.add(new JMenuItem());
        botoesPanel.add(isbnField);
//        botoesPanel.add(JComboBox);
        botoesPanel.add(adicionarButton);
        botoesPanel.add(excluirButton);
        botoesPanel.add(verContatoButton);

        getContentPane().add(botoesPanel, BorderLayout.SOUTH);
    }

    private void adicionarLinha(ActionEvent e) {
        try {
            preencherOuAtualizarTabela();
        } catch (NumberFormatException ex) {

        }
    }

    private void excluirLinha(ActionEvent e) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            long codigo = (long) model.getValueAt(linhaSelecionada, 0);

            preencherOuAtualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }

    private void verContato(ActionEvent e) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            long codigo = (long) model.getValueAt(linhaSelecionada, 0);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para ver o livro.");
        }
    }

    private void preencherOuAtualizarTabela() {
        model.setNumRows(0);

    }
}
