package features.user.presentation.views;

import features.user.datasources.IUserListener;
import features.user.entities.UserEntity;
import features.user.presentation.controllers.UsersController;
import infraestructure.PresentationManager;
import features.commonViewComponents.NotSelectedRowException;
import features.commonViewComponents.MenuBarComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UsersView extends JFrame implements IUserListener {
    private final UsersController _usersController;
    private final PresentationManager _presentationManager;

    private JTable table;
    private DefaultTableModel model;
    private JTextField searchStringField;

    public UsersView(UsersController usersController, PresentationManager presentationManager) {
        _usersController = usersController;
        _usersController.addListener(this);

        _presentationManager = presentationManager;

        initComponents();

        updateTable();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void initComponents() {
        setTitle("Gestão de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });

        defineMenuBar();
        defineLayout();
    }

    private void defineMenuBar() {
        JMenuBar menuBar = MenuBarComponent.createMenuBar(
                this,
                _presentationManager,
                e -> closeWindow()
        );
        setJMenuBar(menuBar);
    }

    private void defineLayout() {
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Pesquisar na Tabela:");
        searchStringField = new JTextField(15);
        JButton searchButton = new JButton("Pesquisar");
        searchButton.addActionListener(this::searchBook);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Id");
        model.addColumn("Login");
        model.addColumn("Profile");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Adicionar/Editar");
        JButton removeButton = new JButton("Excluir");
        JButton changePasswordButton = new JButton("Alterar Senha");

        addButton.addActionListener(this::openSaveScreen);
        removeButton.addActionListener(this::removeRow);
        changePasswordButton.addActionListener(this::openEditPassScreen);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addComponent(searchStringField)
                                .addComponent(searchButton))
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(addButton)
                                .addComponent(removeButton)
                                .addComponent(changePasswordButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(searchStringField)
                                .addComponent(searchButton))
                        .addComponent(scrollPane)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(removeButton)
                                .addComponent(changePasswordButton))
        );

        getContentPane().add(mainPanel);
    }

    private void closeWindow() {
        _usersController.closeWindow();
    }

    private int getUserIdFromTable() throws NotSelectedRowException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) model.getValueAt(selectedRow, 0);
        } else {
            throw new NotSelectedRowException();
        }
    }

    private void openSaveScreen(ActionEvent event) {
        try {
            int id = getUserIdFromTable();
            UserEntity userEntity = _usersController.getUser(id);
            _usersController.openEditWindow(userEntity);
        } catch (NotSelectedRowException e) {
            UserEntity userEntity = null;
            _usersController.openEditWindow(userEntity);
        }
    }

    private void openEditPassScreen(ActionEvent event) {
        try {
            int id = getUserIdFromTable();
            UserEntity userEntity = _usersController.getUser(id);
            _usersController.openEditPassWindow(userEntity);
        } catch (NotSelectedRowException e) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!");
        }
    }

    private void removeRow(ActionEvent event) {
        try {
            int id = getUserIdFromTable();
            UserEntity user = _usersController.getUser(id);
            _usersController.deleteUser(user);
            updateTable();
        } catch (NotSelectedRowException e) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir");
        }
    }

    private void searchBook(ActionEvent event) {
        updateTable();
    }

    private void updateTable() {
        model.setRowCount(0);

        String searchString = searchStringField.getText();
        List<UserEntity> booksList = _usersController.getUsers(searchString);

        for (UserEntity userEntity : booksList) {
            model.addRow(new Object[]{userEntity.getId(), userEntity.getLogin(), userEntity.getProfile()});
        }
    }

    @Override
    public void updateUsersChanged() {
        updateTable();
    }
}
