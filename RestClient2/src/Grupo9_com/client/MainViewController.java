package Grupo9_com.client;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

public class MainViewController {
	
	@FXML
	private void initialize() {

	}


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
    private Button getAllCategoriasButton;
    
    @FXML
    private Button addCategoriaButton;
    
    @FXML
    private Button removeCategoriaButton;
    
    @FXML
    private Button alterarGastoMaximoCatButton;
    
    @FXML
    private Button getListaGastosCatButton;
    
    @FXML
    private Label labelSubcategorias;
    
    @FXML
    private Label labelAddSubcategoria;
    
    @FXML
    private Button getSubcategoriasButton;
    
    @FXML
    private Button addSubcategoriaButton;
    
    @FXML
    private Button removeSubcategoriaButton;
    
    @FXML
    private Button alterarGastoMaxSubcButton;
    
    @FXML
    private Label labelMetas;

    @FXML
    private Label labelAddMeta;
    
    @FXML
    private Button getMetasButton;
    
    @FXML
    private Button addMetaButton;
    
    @FXML
    private Button removeMetaButton;
    
    @FXML
    private Button getMetasCumpridasButton;
    
    @FXML
    private Button getMetasNCumpridasButton;
    
    @FXML
    private Label labelTransacoes;
    
    @FXML
    private Button getTransacoesButton;
    
    private String pedirInput(String mensagem, Label label) {
        TextInputDialog dialog = new TextInputDialog();
        label.setText(mensagem);
        label.setVisible(true);
        return dialog.showAndWait().orElse("");
    }

    private Double pedirDouble(String mensagem, Label label) {
        String valorAnualTemp = pedirInput(mensagem, label);

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
        String dataTemp = pedirInput(mensagem, label);

        if (isValidDate(dataTemp)) {
            return dataTemp;
        } else {
            label.setText("Formato de data inválido! Use o formato dd/mm/yyyy");
        }

        return null; // Retorna null para indicar que houve um problema com a entrada
    }
    
    private String pedirString(String mensagem, Label label) {
        String input = pedirInput(mensagem, label);

        if (!input.isEmpty() && input.matches("[a-zA-Z0-9]*")) {
            return input;
        } else {
            label.setText("Entrada inválida! Use apenas letras e números, nenhum caracter especial ou espaço é permitido!");
            return null;
        }
    }

    private boolean isValidDate(String String) {
        // Verifica se a data está no formato "dd/mm/yyyy"
        return String.matches("^\\d{2}/\\d{2}/\\d{4}$");
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
    private void handleAddOuRemValorOrcamento() {
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
    private void handleGetCategorias() {
        // Lógica para obter as categorias
        String categoriasString = ClientTestHttpURLConnection.getCategorias();

        // Configurar o texto do Label com as categorias
        labelCategorias.setText(categoriasString);

        // Tornar o Label visível
        labelCategorias.setVisible(true);
    }
    
    @FXML
    private void handleGetAllCategorias() {
        // Lógica para obter as categorias
        String categoriasString = ClientTestHttpURLConnection.getAllCategorias();

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
            
            if (ClientTestHttpURLConnection.getCategoria(nomeC)) {
            	labelAddCategoria.setText("Já existe uma Categoria com esse nome!");
                return; 
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
           
            if (!ClientTestHttpURLConnection.getCategoria(nomeC)) {
            	labelAddCategoria.setText("Essa Categoria não existe!");
                return;
            }

            ClientTestHttpURLConnection.deleteCategoria(nomeC);
            labelAddCategoria.setText("Categoria "+nomeC+" eliminada!");
        }
    }
    
    @FXML
    private void handleAlterarGastoMaximoCat() {
        // Pedir o nome da Categoria que deseja alterar o gasto máximo
        String nomeCategoria = pedirString("Qual o nome da Categoria que pretende alterar o gasto máximo?", labelAddCategoria);

        if (nomeCategoria != null) {
            // Verificar se a Categoria existe
            if (!ClientTestHttpURLConnection.getCategoria(nomeCategoria)) {
                labelAddCategoria.setText("Essa Categoria não existe!");
                return; // Não permitir a alteração se a Categoria não existir
            }

            // Pedir o novo gasto máximo desejado
            Double novoGastoMaximo = pedirDouble("Qual o novo gasto máximo desejado?", labelAddCategoria);

            if (novoGastoMaximo != null) {
                // Chamar o método para atribuir o novo gasto máximo à Categoria
                ClientTestHttpURLConnection.alterarGastoMaximoCategoria(nomeCategoria, novoGastoMaximo);

                labelAddCategoria.setText("Alteração concluída!");
            }
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
    private void handleGetSubcategorias() {
        // Lógica para obter as subcategorias
        String subcategoriasString = ClientTestHttpURLConnection.getSubcategorias();

        // Configurar o texto do Label com as subcategorias
        labelSubcategorias.setText(subcategoriasString);

        // Tornar o Label visível
        labelSubcategorias.setVisible(true);
    }
    
    @FXML
    private void handleAddSubcategorias() {
        // Configurar o texto do Label com os orçamentos
        String nomeSubc = pedirString("Qual o nome desejado para a sua Subcategoria?", labelAddSubcategoria);

        if (nomeSubc != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (ClientTestHttpURLConnection.getCategoria(nomeSubc)) {
            	labelAddSubcategoria.setText("Já existe uma Categoria com esse nome!");
                return; // Não permitir a adição se já existe um orçamento
            }

            Double gastoMaximo = pedirDouble("Qual o gasto maximo desejado?", labelAddSubcategoria);

            String nomeC = pedirString("Qual a nome da Categoria onde a Subcategoria vai pertencer?", labelAddSubcategoria);
            if (nomeC != null) {
	            if (!ClientTestHttpURLConnection.getCategoria(nomeC)) {
	            	labelAddSubcategoria.setText("Nao foi encontrada nenhuma Categoria com esse nome!");
	                return; // Não permitir a adição se já existe um orçamento
	            }
	            ClientTestHttpURLConnection.addSubcategoria(nomeSubc, gastoMaximo);
	            ClientTestHttpURLConnection.atribuirCategoriaNaSubcategoria(nomeC, nomeSubc);
	            labelAddSubcategoria.setText("Adição concluída!");
            }
        }
    }
    
    @FXML
    private void handleRemoveSubcategoria() {
        // Configurar o texto do Label com os orçamentos
        String nomeSubc = pedirString("Qual o nome da Subcategoria que deseja eliminar?", labelAddSubcategoria);

        if (nomeSubc != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (!ClientTestHttpURLConnection.getSubcategoria(nomeSubc)) {
            	labelAddSubcategoria.setText("Essa Subcategoria não existe!");
                return; // Não permitir a adição se já existe um orçamento
            }

            ClientTestHttpURLConnection.deleteSubcategoria(nomeSubc);
            labelAddSubcategoria.setText("Subcategoria "+nomeSubc+" eliminada!");
        }
    }
    
    @FXML
    private void handleAlterarGastoMaxSubc() {
        // Pedir o nome da Categoria que deseja alterar o gasto máximo
        String nomeSubc = pedirString("Qual o nome da Subcategoria que pretende alterar o gasto máximo?", labelAddSubcategoria);

        if (nomeSubc != null) {
            // Verificar se a Categoria existe
            if (!ClientTestHttpURLConnection.getSubcategoria(nomeSubc)) {
            	labelAddSubcategoria.setText("Essa Categoria não existe!");
                return; // Não permitir a alteração se a Categoria não existir
            }

            // Pedir o novo gasto máximo desejado
            Double novoGastoMaximo = pedirDouble("Qual o novo gasto máximo desejado?", labelAddSubcategoria);

            if (novoGastoMaximo != null) {
                // Chamar o método para atribuir o novo gasto máximo à Categoria
                ClientTestHttpURLConnection.alterarGastoMaximoSubcategoria(nomeSubc, novoGastoMaximo);

                labelAddSubcategoria.setText("Alteração concluída!");
            }
        }
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
    private void handleAddMeta() {
        // Configurar o texto do Label com os orçamentos
        String nomeMeta = pedirString("Qual o nome desejado para a sua Meta?", labelAddMeta);

        if (nomeMeta != null) {
            
            if (ClientTestHttpURLConnection.getCategoria(nomeMeta)) {
            	labelAddMeta.setText("Já existe uma Categoria com esse nome!");
                return; 
            }

            String descricaoMeta = pedirString("Qual a descrição da sua meta?", labelAddMeta);
            
            if (descricaoMeta != null) {
            
	            Double valorMeta = pedirDouble("Qual o valor desejado?", labelAddMeta);
	
	            if (valorMeta != null) {
	            	String dataMeta = pedirData("Qual é a data que deseja definir para a Meta?", labelAddMeta);
	            	if (dataMeta != null) {
	    	            ClientTestHttpURLConnection.addMeta(nomeMeta, valorMeta, descricaoMeta, dataMeta);
	    	            labelAddMeta.setText("Adição concluída!");
	            	}
	            }
            }
        }
    }
    
    @FXML
    private void handleRemoveMeta() {
        // Configurar o texto do Label com os orçamentos
        String nomeMeta = pedirString("Qual o nome da Meta que deseja eliminar?", labelAddMeta);

        if (nomeMeta != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (!ClientTestHttpURLConnection.getMeta(nomeMeta)) {
            	labelAddMeta.setText("Essa Meta não existe!");
                return; // Não permitir a adição se já existe um orçamento
            }

            ClientTestHttpURLConnection.deleteMeta(nomeMeta);;
            labelAddMeta.setText("Meta "+nomeMeta+" eliminada!");
        }
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
    private void handleGetTransacoes() {
        // Lógica para obter as transações
        String transacoesString = ClientTestHttpURLConnection.getTransacoes();

        // Configurar o texto do Label com as transações
        labelTransacoes.setText(transacoesString);

        // Tornar o Label visível
        labelTransacoes.setVisible(true);
    }

}