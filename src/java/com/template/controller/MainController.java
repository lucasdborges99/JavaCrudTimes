package com.template.controller;

import com.template.model.dao.TimesDAO;
import com.template.model.dto.TimesDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

import static com.template.util.DialogUtil.showConfirmation;

public class MainController
{
    @FXML private Button btnEditar;
    @FXML private Button btnAdicionar;
    @FXML private Button btnExcluir;
    @FXML private Button btnPesquisar;
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtFund;
    @FXML private TextField txtEstado;
    @FXML private TextField txtBrasileiros;
    @FXML private TableView<TimesDTO> tblTimes;
    @FXML private TableColumn<TimesDTO, Integer> colId;
    @FXML private TableColumn<TimesDTO, String> colNome;
    @FXML private TableColumn<TimesDTO, Integer> colFund;
    @FXML private TableColumn<TimesDTO, String> colEstado;
    @FXML private TableColumn<TimesDTO, Integer> colBrasileiros;

    private ObservableList<TimesDTO> ListaParaFiltrar = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFund.setCellValueFactory(new PropertyValueFactory<>("anoFundacao"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colBrasileiros.setCellValueFactory(new PropertyValueFactory<>("titulosBrasileiros"));

        atualizarTabela();
        gerenciarBotoes(false);

        System.out.println("FXML loaded successfully!");
    }

    private void atualizarTabela() {
        TimesDAO listaDeTimesAgrupada = new TimesDAO();
        ArrayList<TimesDTO> listaTimes = listaDeTimesAgrupada.listarTimes();

        ListaParaFiltrar.setAll(listaTimes);
        tblTimes.setItems(ListaParaFiltrar);
    }

    @FXML
    private void carregarCampos(MouseEvent event) {
        TimesDTO linhaTime = tblTimes.getSelectionModel().getSelectedItem();

        if (linhaTime != null) {
            txtId.setText(String.valueOf(linhaTime.getId()));
            txtNome.setText(linhaTime.getNome());
            txtFund.setText(String.valueOf(linhaTime.getAnoFundacao()));
            txtEstado.setText(linhaTime.getEstado());
            txtBrasileiros.setText(String.valueOf(linhaTime.getTitulosBrasileiros()));
            gerenciarBotoes(true);
        }
    }

    @FXML
    private void btnAdicionarAction(ActionEvent event) {
        String nome = txtNome.getText();
        String estado = txtEstado.getText();
        int anoFund = Integer.parseInt(txtFund.getText());
        int brasileiros = Integer.parseInt(txtBrasileiros.getText());

        TimesDTO linhaTime = new TimesDTO();
        linhaTime.setNome(nome);
        linhaTime.setAnoFundacao(anoFund);
        linhaTime.setEstado(estado);
        linhaTime.setTitulosBrasileiros(brasileiros);

        TimesDAO timeNovo = new TimesDAO();
        timeNovo.cadastrarTime(linhaTime);

        atualizarTabela();
        limparCampos();
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        int anoFund = Integer.parseInt(txtFund.getText());
        int brasileiros = Integer.parseInt(txtBrasileiros.getText());
        String nome = txtNome.getText();
        String estado = txtEstado.getText();

        TimesDTO linhaTime = new TimesDTO();
        linhaTime.setId(id);
        linhaTime.setNome(nome);
        linhaTime.setAnoFundacao(anoFund);
        linhaTime.setEstado(estado);
        linhaTime.setTitulosBrasileiros(brasileiros);

        TimesDAO timeEditado = new TimesDAO();
        timeEditado.alterarTime(linhaTime);

        atualizarTabela();
        limparCampos();
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());

        TimesDAO timeExcluir = new TimesDAO();
        timeExcluir.excluirTime(id);

        if(showConfirmation("Você realmente deseja excluir?")){
            atualizarTabela();
            limparCampos();
        }
        else{
            return;
        }
    }

    @FXML
    private void btnPesquisarAction(ActionEvent event) {
        FilteredList<TimesDTO> listaFiltrada = new FilteredList<>(ListaParaFiltrar, p -> true);
        listaFiltrada.setPredicate(time -> {
            if (!txtId.getText().isEmpty()) {
                String filtroId = txtId.getText().trim();
                if (!String.valueOf(time.getId()).contains(filtroId)) {
                    return false;
                }
            }
            return true;
        });
        tblTimes.setItems(listaFiltrada);
    }

    private void limparCampos() {
        txtId.clear();
        txtNome.clear();
        txtFund.clear();
        txtEstado.clear();
        txtBrasileiros.clear();

        txtNome.requestFocus();
        gerenciarBotoes(false);
    }

    private void gerenciarBotoes(boolean itemSelecionado) {
        btnEditar.setDisable(!itemSelecionado);
        btnExcluir.setDisable(!itemSelecionado);
    }
}