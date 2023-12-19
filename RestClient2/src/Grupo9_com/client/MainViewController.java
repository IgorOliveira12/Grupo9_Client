package Grupo9_com.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class MainViewController {

    @FXML
    private Label labelOrcamentos;
    
    @FXML
    private Label labelAddOrcamento;

    @FXML
    private Button getOrcamentosButton;
    
    @FXML
    private Button addOrcamentoButton;
    
    @FXML
    private Button getHistoricoPassadoButton;
    
    @FXML
    private Button getEstadoAtualButton;
    
    @FXML
    private Label labelCategorias;
    
    @FXML
    private Button getCategoriasButton;
    
    @FXML
    private Button getListaGastosCatButton;
    
    @FXML
    private Label labelSubcategorias;
    
    @FXML
    private Button getSubcategoriasButton;
    
    @FXML
    private Label labelMetas;
    
    @FXML
    private Button getMetasButton;
    
    @FXML
    private Button getMetasCumpridasButton;
    
    @FXML
    private Button getMetasNCumpridasButton;
    
    @FXML
    private Label labelTransacoes;
    
    @FXML
    private Button getTransacoesButton;
    

    @FXML
    public void initialize() {
        // Código de inicialização aqui
        if (labelOrcamentos != null) {
            labelOrcamentos.setVisible(true);
        }
        
        if (labelAddOrcamento != null) {
            labelAddOrcamento.setVisible(true);
        }
        
        if (labelMetas != null) {
            labelMetas.setVisible(true);
        }

        if (labelSubcategorias != null) {
            labelSubcategorias.setVisible(true);
        }
        
        if (labelCategorias != null) {
            labelCategorias.setVisible(true);
        }
        
        if (labelTransacoes != null) {
            labelTransacoes.setVisible(true);
        }
    }

    @FXML
    private void handleGetOrcamentos() {
        // Aqui você pode realizar a lógica para obter os orçamentos
        String orcamentoString = ClientTestHttpURLConnection.getOrcamentos();

        // Configurar o texto do Label com os orçamentos
        labelOrcamentos.setText(orcamentoString);

        // Tornar o Label visível
        labelOrcamentos.setVisible(true);
    }
    
    @FXML
    private void handleAddOrcamento() {
        // Configurar o texto do Label com os orçamentos
        TextInputDialog dialog = new TextInputDialog();
        labelAddOrcamento.setText("Qual a data do seu orcamento?");
        labelAddOrcamento.setVisible(true);
        String dataCriacao = dialog.showAndWait().orElse("");
        labelAddOrcamento.setText("Qual o seu valor anual desejado para o seu orcamento?");
        String valorAnualTemp = dialog.showAndWait().orElse("");
        try {
            double valorAnual = Double.parseDouble(valorAnualTemp);
            ClientTestHttpURLConnection.addOrcamento(dataCriacao, valorAnual);
        } catch (NumberFormatException e) {
            System.out.println("Não inseriu um Double!");
        }
        
        labelAddOrcamento.setVisible(false);
    }
    
    @FXML
    private void handleGetHistoricoPassado() {
        // Aqui você pode realizar a lógica para obter os orçamentos
        String orcamentoString = ClientTestHttpURLConnection.imprimirHistoricoOrcamentos();

        // Configurar o texto do Label com os orçamentos
        labelOrcamentos.setText(orcamentoString);

        // Tornar o Label visível
        labelOrcamentos.setVisible(true);
    }
    
    @FXML
    private void handleGetEstadoAtual() {
        // Aqui você pode realizar a lógica para obter os orçamentos
        String orcamentoString = ClientTestHttpURLConnection.mostrarStatusOrcamento();

        // Configurar o texto do Label com os orçamentos
        labelOrcamentos.setText(orcamentoString);

        // Tornar o Label visível
        labelOrcamentos.setVisible(true);
    }
    
    @FXML
    private void handleGetMetas() {
        // Aqui você pode realizar a lógica para obter as metas
        String metasString = ClientTestHttpURLConnection.getMetas();

        // Configurar o texto do Label com as metas
        labelMetas.setText(metasString);

        // Tornar o Label visível
        labelMetas.setVisible(true);
    }
    
    @FXML
    private void handleGetMetasCumpridas() {
        // Aqui você pode realizar a lógica para obter as metas
        String metasString = ClientTestHttpURLConnection.verificarMetasCumpridas();

        // Configurar o texto do Label com as metas
        labelMetas.setText(metasString);

        // Tornar o Label visível
        labelMetas.setVisible(true);
    }
    
    @FXML
    private void handleGetMetasNCumpridas() {
        // Aqui você pode realizar a lógica para obter as metas
        String metasString = ClientTestHttpURLConnection.listarMetasNaoCumpridas();

        // Configurar o texto do Label com as metas
        labelMetas.setText(metasString);

        // Tornar o Label visível
        labelMetas.setVisible(true);
    }
    
    @FXML
    private void handleGetSubcategorias() {
        // Lógica para obter as subcategorias
        String subcategoriasString = ClientTestHttpURLConnection.getSubcategorias();

        // Configurar o texto do Label com as subcategorias
        labelSubcategorias.setText(subcategoriasString);

        // Tornar o Label visível
        labelSubcategorias.setVisible(true);
    }
    
    @FXML
    private void handleGetCategorias() {
        // Lógica para obter as categorias
        String categoriasString = ClientTestHttpURLConnection.getCategorias();

        // Configurar o texto do Label com as categorias
        labelCategorias.setText(categoriasString);

        // Tornar o Label visível
        labelCategorias.setVisible(true);
    }

    @FXML
    private void handleGetListaGastosCat() {
        // Lógica para obter as categorias
        String categoriasString = ClientTestHttpURLConnection.visualizarPercentagemGastosPorCategoriaNoOrcamento();

        // Configurar o texto do Label com as categorias
        labelCategorias.setText(categoriasString);

        // Tornar o Label visível
        labelCategorias.setVisible(true);
    }
    
    @FXML
    private void handleGetTransacoes() {
        // Lógica para obter as transações
        String transacoesString = ClientTestHttpURLConnection.getTransacoes();

        // Configurar o texto do Label com as transações
        labelTransacoes.setText(transacoesString);

        // Tornar o Label visível
        labelTransacoes.setVisible(true);
    }

}