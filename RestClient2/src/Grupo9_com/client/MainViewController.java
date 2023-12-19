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
    private Button addOuRemValorOrcamentoButton;
    
    @FXML
    private Button getHistoricoPassadoButton;
    
    @FXML
    private Button getEstadoAtualButton;
    
    @FXML
    private Label labelCategorias;
    
    @FXML
    private Label labelAddCategoria;
    
    @FXML
    private Button getCategoriasButton;
    
    @FXML
    private Button addCategoriaButton;
    
    @FXML
    private Button removeCategoriaButton;
    
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
    
    private String pedirString(String mensagem, Label label) {
        TextInputDialog dialog = new TextInputDialog();
        label.setText(mensagem);
        label.setVisible(true);
        return dialog.showAndWait().orElse("");
    }

    private Double pedirDouble(String mensagem, Label label) {
        String valorAnualTemp = pedirString(mensagem, label);

        if (!valorAnualTemp.isEmpty()) {
            try {
                return Double.parseDouble(valorAnualTemp);
            } catch (NumberFormatException e) {
                label.setText("Não inseriu um double!  Use o formato 999.9");
            }
        } else {
            label.setText("Valor não fornecido!");
        }

        return null; // Retorna null para indicar que houve um problema com a entrada
    }
    
    private String pedirData(String mensagem, Label label) {
        String dataTemp = pedirString(mensagem, label);

        if (isValidDate(dataTemp)) {
            return dataTemp;
        } else {
            label.setText("Formato de data inválido! Use o formato dd/mm/yyyy");
        }

        return null; // Retorna null para indicar que houve um problema com a entrada
    }

    private boolean isValidDate(String String) {
        // Verifica se a data está no formato "dd/mm/yyyy"
        return String.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

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
        String dataCriacao = pedirData("Qual a data do seu orcamento?", labelAddOrcamento);

        if (dataCriacao != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (ClientTestHttpURLConnection.getOrcamento(dataCriacao)) {
                labelAddOrcamento.setText("Já existe um orçamento para esta data!");
                return; // Não permitir a adição se já existe um orçamento
            }

            Double valorAnual = pedirDouble("Qual o seu valor anual desejado para o seu orcamento?", labelAddOrcamento);

            if (valorAnual != null) {
                ClientTestHttpURLConnection.addOrcamento(dataCriacao, valorAnual);
                labelAddOrcamento.setText("Adição concluída!");
            }
        }
    }

    
    @FXML
    private void handleaddOuRemValorOrcamento() {
        // Configurar o texto do Label com os orçamentos
        Double valorAnual = pedirDouble("Qual o seu valor que deseja adicionar/remover ao Orcamento?", labelAddOrcamento);
        
        if (valorAnual != null) {
            ClientTestHttpURLConnection.adicionarOuReduzirValorOrcamento(valorAnual);
            labelAddOrcamento.setText("Adição/remoção concluída!");
        }
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
    private void handleAddCategoria() {
        // Configurar o texto do Label com os orçamentos
        String nomeC = pedirString("Qual o nome desejado para a sua Categoria?", labelAddCategoria);

        if (nomeC != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (ClientTestHttpURLConnection.getCategoria(nomeC)) {
            	labelAddCategoria.setText("Já existe uma Categoria com esse nome!");
                return; // Não permitir a adição se já existe um orçamento
            }

            Double gastoMaximo = pedirDouble("Qual o gasto maximo desejado?", labelAddCategoria);

            if (gastoMaximo != null) {
                ClientTestHttpURLConnection.addCategoria(nomeC, gastoMaximo);
                labelAddCategoria.setText("Adição concluída!");
            }
        }
    }
    
    @FXML
    private void handleRemoveCategoria() {
        // Configurar o texto do Label com os orçamentos
        String nomeC = pedirString("Qual o nome da Categoria que deseja eliminar?", labelAddCategoria);

        if (nomeC != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (!ClientTestHttpURLConnection.getCategoria(nomeC)) {
            	labelAddCategoria.setText("Essa Categoria não existe!");
                return; // Não permitir a adição se já existe um orçamento
            }

            ClientTestHttpURLConnection.deleteCategoria(nomeC);
            labelAddCategoria.setText("Categoria "+nomeC+" eliminada!");
        }
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