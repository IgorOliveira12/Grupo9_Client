package Grupo9_com.client;

import com.google.gson.Gson;
import grupo9_FinancasPessoais.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientTestHttpURLConnection {

    /**
     * Método principal que realiza chamadas aos diferentes métodos de teste da aplicação.
     * Cada chamada representa uma operação específica no sistema de Finanças Pessoais.
     */
    public static void main(String[] args) {
        // Operações relacionadas a Orcamentos
        getOrcamentos();
        getOrcamento(1000.0);
        addOrcamento(new Orcamento()); // Substitua pelo objeto Orcamento real
        updateOrcamento(new Orcamento()); // Substitua pelo objeto Orcamento real
        adicionarOuReduzirValorOrcamento(500.0);
        obterUltimoOrcamento();
        removeOrcamento(1000.0);
        imprimirHistoricoOrcamentos();
        calcularGastoRealizado(1000.0);
        mostrarStatusOrcamento();

        // Operações relacionadas a Categorias
        addCategoria();
        updateCategoria();
        getCategorias();
        getCategoria("nomeCategoria");
        alterarGastoMaximoCategoria("Alimentação", 4000.0);
        visualizarPercentagemGastosPorCategoriaNoOrcamento();

        // Operações relacionadas a Subcategorias
        addSubcategoria();
        getSubcategorias();
        getSubcategoria("nomeSubcategoria");
        updateSubcategoria();
        alterarGastoMaximoSubcategoria("nomeSubcategoria", 2500.0);
        removeSubcategoria("nomeSubcategoria");
        calcularPercentagemGastos("nomeSubcategoria", 1200.0);

        // Operações relacionadas a Transacoes
        addTransacao(new Transacao()); // Substitua pelo objeto Transacao real
        getTransacoes();
        getTransacao("descricaoTransacao"); // Substitua pela descrição da transação desejada
        updateTransacao(new Transacao()); // Substitua pelo objeto Transacao real
        removeTransacao("descricaoTransacao"); // Substitua pela descrição da transação a ser removida
        alterarCategoriaTransacao("descricaoTransacao", "novaCategoria"); // Substitua pela descrição da transação e nova categoria
        alterarSubcategoriaTransacao("descricaoTransacao", "novaSubcategoria"); // Substitua pela descrição da transação e nova subcategoria
        alterarDataTransacao("descricaoTransacao", "novaData"); // Substitua pela descrição da transação e nova data

        // Operações relacionadas a Metas
        addMeta(new Meta()); // Substitua pelo objeto Meta real
        getMetas();
        getMeta("nomeMeta"); // Substitua pelo nome da meta desejada
        updateMeta(new Meta()); // Substitua pelo objeto Meta real
        alterarValorMeta("nomeMeta", 1500.0); // Substitua pelo nome da meta e novo valor
        alterarPrazoMeta("nomeMeta", "novaData"); // Substitua pelo nome da meta e nova data
        verificarMetasCumpridas();
        listarMetasNaoCumpridas();
    }

    /**
     * Adiciona uma nova categoria no sistema através de uma requisição HTTP POST.
     */
    private static void addCategoria() {
        try {
            // Criação da URL para a operação de adição de categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/addCategoria");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Criação de uma nova categoria para ser adicionada
            Gson gson = new Gson();
            Categoria novaCategoria = new Categoria();
            novaCategoria.setNomeC("Alimentação");
            novaCategoria.setGastoMaximo(4000.0);

            // Conversão do objeto categoria para formato JSON e envio da requisição
            String postData = gson.toJson(novaCategoria, Categoria.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém a lista de categorias do sistema através de uma requisição HTTP GET.
     */
    private static void getCategorias() {
        try {
            // Criação da URL para a operação de obtenção de categorias
            URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma categoria específica do sistema através de uma requisição HTTP GET.
     *
     * @param nomeCategoria O nome da categoria desejada.
     */
    private static void getCategoria(String nomeCategoria) {
        try {
            // Criação da URL para a operação de obtenção de uma categoria específica
            URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategoria/" + nomeCategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza uma categoria no sistema através de uma requisição HTTP PUT.
     */
    private static void updateCategoria() {
        try {
            // Criação da URL para a operação de atualização de categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/updateCategoria");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Criação de uma categoria atualizada para substituir a existente
            Gson gson = new Gson();
            Categoria categoriaAtualizada = new Categoria();
            categoriaAtualizada.setNomeC("Alimentação"); // Substitua pelo nome da categoria que você deseja atualizar
            categoriaAtualizada.setGastoMaximo(3800.0); // Novo valor máximo para a categoria

            // Conversão do objeto categoria atualizada para formato JSON e envio da requisição
            String postData = gson.toJson(categoriaAtualizada, Categoria.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera o gasto máximo de uma categoria no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeCategoria O nome da categoria a ser alterada.
     * @param gastoMaximo   O novo valor máximo para a categoria.
     */
    private static void alterarGastoMaximoCategoria(String nomeCategoria, double gastoMaximo) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/alterarGastoMaximoCategoria/"
                    + nomeCategoria + "/" + gastoMaximo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visualiza a percentagem de gastos por categoria no orçamento através de uma requisição HTTP GET.
     */
    private static void visualizarPercentagemGastosPorCategoriaNoOrcamento() {
        try {
            // Criação da URL para a operação de visualização de percentagem de gastos por categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/visualizarPercentagemGastosPorCategoriaNoOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona uma nova subcategoria no sistema através de uma requisição HTTP POST.
     */
    private static void addSubcategoria() {
        try {
            // Criação da URL para a operação de adição de subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/addSubcategoria");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Criação de uma nova subcategoria para ser adicionada
            Gson gson = new Gson();
            Subcategoria novaSubcategoria = new Subcategoria();
            novaSubcategoria.setNomeSubc("Refeições fora de casa");
            novaSubcategoria.setGastoMaxSubc(1000.0);

            // Conversão do objeto subcategoria para formato JSON e envio da requisição
            String postData = gson.toJson(novaSubcategoria, Subcategoria.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Obtém a lista de subcategorias do sistema através de uma requisição HTTP GET.
     */
    private static void getSubcategorias() {
        try {
            // Criação da URL para a operação de obtenção de subcategorias
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/getSubcategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma subcategoria específica do sistema através de uma requisição HTTP GET.
     *
     * @param nomeSubcategoria O nome da subcategoria desejada.
     */
    private static void getSubcategoria(String nomeSubcategoria) {
        try {
            // Criação da URL para a operação de obtenção de uma subcategoria específica
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/getSubcategoria/" + nomeSubcategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza uma subcategoria no sistema através de uma requisição HTTP PUT.
     */
    private static void updateSubcategoria() {
        try {
            // Criação da URL para a operação de atualização de subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/updateSubcategoria");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Criação de uma subcategoria atualizada para substituir a existente
            Gson gson = new Gson();
            Subcategoria subcategoriaAtualizada = new Subcategoria();
            subcategoriaAtualizada.setNomeSubc("Refeições fora de casa"); // Substitua pelo nome da subcategoria que você deseja atualizar
            subcategoriaAtualizada.setGastoMaxSubc(1200.0); // Novo valor máximo para a subcategoria

            // Conversão do objeto subcategoria atualizada para formato JSON e envio da requisição
            String postData = gson.toJson(subcategoriaAtualizada, Subcategoria.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera o gasto máximo de uma subcategoria no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeSubcategoria O nome da subcategoria a ser alterada.
     * @param gastoMaximo      O novo valor máximo para a subcategoria.
     */
    private static void alterarGastoMaximoSubcategoria(String nomeSubcategoria, double gastoMaximo) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/alterarGastoMaximo/" +
                    nomeSubcategoria + "/" + gastoMaximo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove uma subcategoria do sistema através de uma requisição HTTP DELETE.
     *
     * @param nomeSubcategoria O nome da subcategoria a ser removida.
     */
    private static void removeSubcategoria(String nomeSubcategoria) {
        try {
            // Criação da URL para a operação de remoção de subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/removeSubcategoria/" + nomeSubcategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Calcula a percentagem de gastos para uma subcategoria específica em relação aos gastos totais da categoria,
     * através de uma requisição HTTP GET.
     *
     * @param nomeSubcategoria O nome da subcategoria para a qual a percentagem de gastos será calculada.
     * @param gastosCategoria  O total de gastos da categoria à qual a subcategoria pertence.
     */
    private static void calcularPercentagemGastos(String nomeSubcategoria, double gastosCategoria) {
        try {
            // Criação da URL para a operação de cálculo de percentagem de gastos
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/calcularPercentagemGastos/" +
                    nomeSubcategoria + "/" + gastosCategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém a lista de orçamentos do sistema através de uma requisição HTTP GET.
     */
    private static void getOrcamentos() {
        try {
            // Criação da URL para a operação de obtenção de orçamentos
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/getOrcamentos");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém um orçamento específico do sistema através de uma requisição HTTP GET.
     *
     * @param valorAnual O valor anual do orçamento desejado.
     */
    private static void getOrcamento(double valorAnual) {
        try {
            // Criação da URL para a operação de obtenção de um orçamento específico
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/getOrcamento/" + valorAnual);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um novo orçamento no sistema através de uma requisição HTTP POST.
     *
     * @param orcamento O objeto Orcamento a ser adicionado.
     */
    private static void addOrcamento(Orcamento orcamento) {
        try {
            // Criação da URL para a operação de adição de orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/addOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Conversão do objeto Orcamento para formato JSON e envio da requisição
            Gson gson = new Gson();
            String postData = gson.toJson(orcamento, Orcamento.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza um orçamento no sistema através de uma requisição HTTP PUT.
     *
     * @param orcamento O objeto Orcamento atualizado.
     */
    private static void updateOrcamento(Orcamento orcamento) {
        try {
            // Criação da URL para a operação de atualização de orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/updateOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Conversão do objeto Orcamento atualizado para formato JSON e envio da requisição
            Gson gson = new Gson();
            String postData = gson.toJson(orcamento, Orcamento.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona ou reduz o valor do orçamento atual através de uma requisição HTTP PUT.
     *
     * @param valorAlteracao O valor a ser adicionado ou reduzido no orçamento atual.
     */
    private static void adicionarOuReduzirValorOrcamento(double valorAlteracao) {
        try {
            // Criação da URL para a operação de adição ou redução de valor no orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/adicionarOuReduzirValorOrcamento/" + valorAlteracao);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém o último orçamento registrado no sistema através de uma requisição HTTP GET.
     */
    private static void obterUltimoOrcamento() {
        try {
            // Criação da URL para a operação de obtenção do último orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/obterUltimoOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Remove um orçamento do sistema através de uma requisição HTTP DELETE.
     *
     * @param valorAnual O valor anual do orçamento a ser removido.
     */
    private static void removeOrcamento(double valorAnual) {
        try {
            // Criação da URL para a operação de remoção de orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/removeOrcamento/" + valorAnual);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imprime o histórico de orçamentos do sistema através de uma requisição HTTP GET.
     */
    private static void imprimirHistoricoOrcamentos() {
        try {
            // Criação da URL para a operação de impressão do histórico de orçamentos
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/imprimirHistoricoOrcamentos");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calcula o gasto realizado para um orçamento específico através de uma requisição HTTP GET.
     *
     * @param valorAnual O valor anual do orçamento para o qual o gasto realizado será calculado.
     */
    private static void calcularGastoRealizado(double valorAnual) {
        try {
            // Criação da URL para a operação de cálculo de gasto realizado
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/calcularGastoRealizado/" + valorAnual);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra o status atual do orçamento através de uma requisição HTTP GET.
     */
    private static void mostrarStatusOrcamento() {
        try {
            // Criação da URL para a operação de obtenção do status do orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/mostrarStatusOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ... Outros métodos existentes ...

    /**
     * Adiciona uma nova transação no sistema através de uma requisição HTTP POST.
     *
     * @param transacao O objeto Transacao a ser adicionado.
     */
    private static void addTransacao(Transacao transacao) {
        try {
            // Criação da URL para a operação de adição de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/addTransacao");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Conversão do objeto Transacao para formato JSON e envio da requisição
            Gson gson = new Gson();
            String postData = gson.toJson(transacao, Transacao.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém a lista de transações do sistema através de uma requisição HTTP GET.
     */
    private static void getTransacoes() {
        try {
            // Criação da URL para a operação de obtenção de transações
            URL url = new URL("http://localhost:8080/RESTServer/transacao/getTransacoes");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma transação específica do sistema através de uma requisição HTTP GET.
     *
     * @param descricao A descrição da transação desejada.
     */
    private static void getTransacao(String descricao) {
        try {
            // Criação da URL para a operação de obtenção de uma transação específica
            URL url = new URL("http://localhost:8080/RESTServer/transacao/getTransacao/" + descricao);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza uma transação no sistema através de uma requisição HTTP PUT.
     *
     * @param transacao O objeto Transacao atualizado.
     */
    private static void updateTransacao(Transacao transacao) {
        try {
            // Criação da URL para a operação de atualização de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/updateTransacao");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Conversão do objeto Transacao atualizado para formato JSON e envio da requisição
            Gson gson = new Gson();
            String postData = gson.toJson(transacao, Transacao.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove uma transação do sistema através de uma requisição HTTP DELETE.
     *
     * @param descricao A descrição da transação a ser removida.
     */
    private static void removeTransacao(String descricao) {
        try {
            // Criação da URL para a operação de remoção de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/removeTransacao/" + descricao);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera a categoria de uma transação no sistema através de uma requisição HTTP PUT.
     *
     * @param descricao      A descrição da transação a ser alterada.
     * @param novaCategoria  A nova categoria da transação.
     */
    private static void alterarCategoriaTransacao(String descricao, String novaCategoria) {
        try {
            // Criação da URL para a operação de alteração de categoria de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarCategoria/" + descricao + "/" + novaCategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera a subcategoria de uma transação no sistema através de uma requisição HTTP PUT.
     *
     * @param descricao      A descrição da transação a ser alterada.
     * @param novaSubcategoria  A nova subcategoria da transação.
     */
    private static void alterarSubcategoriaTransacao(String descricao, String novaSubcategoria) {
        try {
            // Criação da URL para a operação de alteração de subcategoria de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarSubcategoria/" + descricao + "/" + novaSubcategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera a data de uma transação no sistema através de uma requisição HTTP PUT.
     *
     * @param descricao      A descrição da transação a ser alterada.
     * @param novaData  A nova data da transação.
     */
    private static void alterarDataTransacao(String descricao, String novaData) {
        try {
            // Criação da URL para a operação de alteração de data de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarData/" + descricao + "/" + novaData);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona uma nova meta no sistema através de uma requisição HTTP POST.
     *
     * @param meta O objeto Meta a ser adicionado.
     */
    private static void addMeta(Meta meta) {
        try {
            // Criação da URL para a operação de adição de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/addMeta");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Conversão do objeto Meta para formato JSON e envio da requisição
            Gson gson = new Gson();
            String postData = gson.toJson(meta, Meta.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém a lista de metas do sistema através de uma requisição HTTP GET.
     */
    private static void getMetas() {
        try {
            // Criação da URL para a operação de obtenção de metas
            URL url = new URL("http://localhost:8080/RESTServer/meta/getMetas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém uma meta específica do sistema através de uma requisição HTTP GET.
     *
     * @param nomeMeta O nome da meta desejada.
     */
    private static void getMeta(String nomeMeta) {
        try {
            // Criação da URL para a operação de obtenção de uma meta específica
            URL url = new URL("http://localhost:8080/RESTServer/meta/getMeta/" + nomeMeta);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza uma meta no sistema através de uma requisição HTTP PUT.
     *
     * @param meta O objeto Meta atualizado.
     */
    private static void updateMeta(Meta meta) {
        try {
            // Criação da URL para a operação de atualização de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/updateMeta");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            // Conversão do objeto Meta atualizado para formato JSON e envio da requisição
            Gson gson = new Gson();
            String postData = gson.toJson(meta, Meta.class);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera o valor de uma meta no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeMeta      O nome da meta a ser alterada.
     * @param novoValor  O novo valor da meta.
     */
    private static void alterarValorMeta(String nomeMeta, double novoValor) {
        try {
            // Criação da URL para a operação de alteração de valor de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/alterarValorMeta/" +
                    nomeMeta + "/" + novoValor);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Altera o prazo de uma meta no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeMeta      O nome da meta a ser alterada.
     * @param novaData  A nova data da meta.
     */
    private static void alterarPrazoMeta(String nomeMeta, String novaData) {
        try {
            // Criação da URL para a operação de alteração de prazo de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/alterarPrazoMeta/" +
                    nomeMeta + "/" + novaData);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica as metas cumpridas no sistema através de uma requisição HTTP GET.
     */
    private static void verificarMetasCumpridas() {
        try {
            // Criação da URL para a operação de verificação de metas cumpridas
            URL url = new URL("http://localhost:8080/RESTServer/meta/verificarMetasCumpridas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista as metas não cumpridas no sistema através de uma requisição HTTP GET.
     */
    private static void listarMetasNaoCumpridas() {
        try {
            // Criação da URL para a operação de listagem de metas não cumpridas
            URL url = new URL("http://localhost:8080/RESTServer/meta/listarMetasNaoCumpridas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processa a resposta da requisição HTTP, exibindo a resposta ou o código de erro.
     *
     * @param con A conexão HttpURLConnection.
     * @throws IOException Exceção de E/S.
     */
    private static void handleResponse(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Server Response:\n" + response.toString());
        } else {
            System.out.println("Failed : HTTP error code : " + responseCode);
        }
    }
}