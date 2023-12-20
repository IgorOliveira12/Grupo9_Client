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
    private Button getAllSubcategoriasButton;
    
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
    private Button getAllMetasButton;
    
    @FXML
    private Button addMetaButton;
    
    @FXML
    private Button removeMetaButton;
    
    @FXML
    private Button alterarValorMetaButton;
    
    @FXML
    private Button alterarPrazoMeta;
    
    @FXML
    private Button getMetasCumpridasButton;
    
    @FXML
    private Button getMetasNCumpridasButton;
    
    @FXML
    private Label labelTransacoes;
    
    @FXML
    private Label labelAddTransacao;
    
    @FXML
    private Button getTransacoesButton;
    
    @FXML
    private Button addTransacaoButton;
    
    @FXML
    private Button removerTransacao;
    
    @FXML
    private Button alterarDataTransacaoButton;
    
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
                label.setText("Não inseriu um double!  Use o formato 00.0");
            }
        } else {
            label.setText("Valor não fornecido!");
        }

        return null; // Retorna null para indicar que houve um problema com a entrada
    }
    
    private String pedirData(String mensagem, Label label) {
        String dataTemp = pedirInput(mensagem, label);

        if (!isValidDate(dataTemp)) {
            label.setText("Formato de data inválido! Use o formato dd-mm-yyyy");
            return null;
        } else {
            return dataTemp;
        }
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

    private boolean isValidDate(String dateString) {
        // Validates date format "dd-mm-yyyy"
        return dateString.matches("^(\\d{2})-(\\d{2})-(\\d{4})$");
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

            Double valorAnual = pedirDouble("Qual o seu valor anual desejado para o seu orcamento?", labelAddOrcamento);
            if (valorAnual < 0) {
            if (valorAnual != null) {
                ClientTestHttpURLConnection.addOrcamento(dataCriacao, valorAnual);
                labelAddOrcamento.setText("Adição concluída!");
            }
            } else {
            	labelAddSubcategoria.setText("Valor anual não pode ser negativo!");
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
            if (gastoMaximo > 0) {
	            if (gastoMaximo != null) {
	                ClientTestHttpURLConnection.addCategoria(nomeC, gastoMaximo);
	                labelAddCategoria.setText("Adição concluída!");
	            }
            }
            else {
            	labelAddCategoria.setText("Gasto máximo não pode ser negativo!");
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
            if (novoGastoMaximo <= 0) {
            	labelAddCategoria.setText("Gasto máximo não pode ser negativo!");
            	return;
            }
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
    private void handleGetAllSubcategorias() {
        // Lógica para obter as categorias
        String subcategoriasString = ClientTestHttpURLConnection.getAllCategorias();

        // Configurar o texto do Label com as categorias
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
            if (gastoMaximo <= 0) {
            	labelAddSubcategoria.setText("Gasto máximo não pode ser negativo!");
            	return;
            }
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
            if (novoGastoMaximo <= 0) {
            	labelAddSubcategoria.setText("O valor não pode ser negativo!");
            	return;
            }
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
    private void handleGetAllMetas() {
        // Aqui você pode realizar a lógica para obter as metas
        String metasString = ClientTestHttpURLConnection.getAllMetas();

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
	            if (valorMeta <= 0) {
	            	labelAddMeta.setText("O valor não pode ser negativo!");
	            	return;
	            }
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
    private void handleAlterarValorMeta() {
        // Pedir o nome da Categoria que deseja alterar o gasto máximo
        String nomeMeta = pedirString("Qual o nome da Meta que pretende alterar o valor?", labelAddMeta);

        if (nomeMeta != null) {
            // Verificar se a Categoria existe
            if (!ClientTestHttpURLConnection.getMeta(nomeMeta)) {
            	labelAddMeta.setText("Essa Meta não existe!");
                return; // Não permitir a alteração se a Categoria não existir
            }

            // Pedir o novo gasto máximo desejado
            Double novoValor = pedirDouble("Qual o novo valor desejado?", labelAddMeta);

            if (novoValor <= 0) {
            	labelAddMeta.setText("O valor não pode ser negativo!");
            	return;
            }
            
            if (novoValor != null) {
                // Chamar o método para atribuir o novo gasto máximo à Categoria
                ClientTestHttpURLConnection.alterarValorMeta(nomeMeta, novoValor);

                labelAddMeta.setText("Alteração concluída!");
            }
        }
    }
    
    @FXML
    private void handleAlterarPrazoMeta() {
        // Pedir o nome da Categoria que deseja alterar o gasto máximo
        String nomeMeta = pedirString("Qual o nome da Meta que pretende alterar a data?", labelAddMeta);

        if (nomeMeta != null) {
            // Verificar se a Categoria existe
            if (!ClientTestHttpURLConnection.getMeta(nomeMeta)) {
            	labelAddMeta.setText("Essa Meta não existe!");
                return; // Não permitir a alteração se a Categoria não existir
            }

            // Pedir o novo gasto máximo desejado
            String novaData = pedirData("Qual a nova data desejada?", labelAddMeta);

            if (novaData != null) {
                // Chamar o método para atribuir o novo gasto máximo à Categoria
                ClientTestHttpURLConnection.alterarPrazoMeta(nomeMeta, novaData);

                labelAddMeta.setText("Alteração concluída!");
            }
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
    
    @FXML
    private void handleGetAllTransacoes() {
        // Lógica para obter as transações
        String transacoesString = ClientTestHttpURLConnection.getAllTransacoes();

        // Configurar o texto do Label com as transações
        labelTransacoes.setText(transacoesString);

        // Tornar o Label visível
        labelTransacoes.setVisible(true);
    }
    
    @FXML
    private void handleAddTransacao() {
        // Configurar o texto do Label com os orçamentos
        String descricao = pedirString("Qual a descrição da sua Transação?", labelAddTransacao);

        if (descricao != null) {
            
            if (ClientTestHttpURLConnection.getCategoria(descricao)) {
            	labelAddTransacao.setText("Já existe uma Transação com essa descrição!");
                return; 
            }

            Double valor = pedirDouble("Qual o valor da sua Transação?", labelAddTransacao);
            
            if (valor != null) {
            
	            String data = pedirData("Qual a data da sua Transação?", labelAddTransacao);
	
	            if (data != null) {
	            	String selecionar = pedirString("Onde deseja adicionar a sua Transação? (CATEGORIA, SUBCATEGORIA, META)?", labelAddTransacao);
	            	if (selecionar != null) {
	            		if (selecionar.equalsIgnoreCase("categoria")) {
	            			String categoria = pedirString("Qual o nome da Categoria onde deseja adicionar?", labelAddTransacao);
	            			if (ClientTestHttpURLConnection.getCategoria(categoria)) {
			    	            ClientTestHttpURLConnection.addTransacao(descricao, valor, data);
			    	            ClientTestHttpURLConnection.atribuirTransacaoEmCategoria(categoria, descricao);
			    	            labelAddTransacao.setText("Adição concluída!");
	            			}
	            			else labelAddTransacao.setText("Essa Categoria não existe!");
	            		}
	            		else if(selecionar.equalsIgnoreCase("subcategoria")) {
	            			String subcategoria = pedirString("Qual o nome da Subcategoria onde deseja adicionar?", labelAddTransacao);
	            			if (ClientTestHttpURLConnection.getSubcategoria(subcategoria)) {
			    	            ClientTestHttpURLConnection.addTransacao(descricao, valor, data);
			    	            ClientTestHttpURLConnection.atribuirTransacaoEmCategoria(subcategoria, descricao);
			    	            labelAddTransacao.setText("Adição concluída!");
	            			}
	            			else labelAddTransacao.setText("Essa Subcategoria não existe!");
	            		}
	            		else if(selecionar.equalsIgnoreCase("meta")) {
	            			String meta = pedirString("Qual o nome da Meta onde deseja adicionar?", labelAddTransacao);
	            			if (ClientTestHttpURLConnection.getMeta(meta))  {
			    	            ClientTestHttpURLConnection.addTransacao(descricao, valor, data);
			    	            ClientTestHttpURLConnection.atribuirTransacaoEmCategoria(meta, descricao);
			    	            labelAddTransacao.setText("Adição concluída!");
	            			}
	            			else labelAddTransacao.setText("Essa Meta não existe!");
	            		}
	            		else {
	            			labelAddTransacao.setText("Não inseriu nenhuma das opções!");
	            		}
	            	}
	            }
            }
        }
    }
    
    @FXML
    private void handleRemoverTransacao() {
        // Configurar o texto do Label com os orçamentos
        String descricao = pedirString("Qual a descrição da Transação que deseja eliminar?", labelAddTransacao);

        if (descricao != null) {
            // Verificar se o orçamento já existe para a data fornecida
            if (!ClientTestHttpURLConnection.getTransacao(descricao)) {
            	labelAddTransacao.setText("Essa Transação não existe!");
                return; // Não permitir a adição se já existe um orçamento
            }

            ClientTestHttpURLConnection.deleteTransacao(descricao);;
            labelAddTransacao.setText("Transacao "+descricao+" eliminada!");
        }
    } 

    @FXML
    private void handleAlterarDataTransacao() {
        // Pedir o nome da Categoria que deseja alterar o gasto máximo
        String descricao = pedirString("Qual a descrição da Transação que pretende alterar a data?", labelAddTransacao);

        if (descricao != null) {
            // Verificar se a Categoria existe
            if (!ClientTestHttpURLConnection.getTransacao(descricao)) {
            	labelAddTransacao.setText("Essa Transação não existe!");
                return; // Não permitir a alteração se a Categoria não existir
            }

            // Pedir o novo gasto máximo desejado
            String novaData = pedirData("Qual a nova data desejada?", labelAddTransacao);

            if (novaData != null) {
                // Chamar o método para atribuir o novo gasto máximo à Categoria
                ClientTestHttpURLConnection.alterarDataTransacao(descricao, novaData);

                labelAddTransacao.setText("Alteração concluída!");
            }
        }
    }
    
}