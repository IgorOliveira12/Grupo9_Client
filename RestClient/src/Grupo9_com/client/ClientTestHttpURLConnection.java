package Grupo9_com.client;

import com.google.gson.Gson;
import grupo9_FinancasPessoais.Categoria;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ClientTestHttpURLConnection {

    public static void main(String[] args) {
        addCategoria();
        updateCategoria();
        getCategorias();
        getCategoria("nomeCategoria");
        alterarGastoMaximoCategoria("Alimentação", 4000.0);
        visualizarPercentagemGastosPorCategoriaNoOrcamento();
    }

    private static void addCategoria() {
        try {
            URL url = new URL("http://localhost:8080/RESTServer/categoria/addCategoria");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            Gson gson = new Gson();
            Categoria novaCategoria = new Categoria();
            novaCategoria.setNomeC("Alimentação");
            novaCategoria.setGastoMaximo(4000.0);

            String postData = gson.toJson(novaCategoria, Categoria.class);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getCategorias() {
        try {
            URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getCategoria(String nomeCategoria) {
        try {
            URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategoria/" + nomeCategoria);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateCategoria() {
        try {
            URL url = new URL("http://localhost:8080/RESTServer/categoria/updateCategoria");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            Gson gson = new Gson();
            Categoria categoriaAtualizada = new Categoria();
            categoriaAtualizada.setNomeC("Alimentação"); // Substitua pelo nome da categoria que você deseja atualizar
            categoriaAtualizada.setGastoMaximo(3800.0); // Novo valor máximo para a categoria

            String postData = gson.toJson(categoriaAtualizada, Categoria.class);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void alterarGastoMaximoCategoria(String nomeCategoria, double gastoMaximo) {
        try {
            URL url = new URL("http://localhost:8080/RESTServer/categoria/alterarGastoMaximoCategoria/"
                    + nomeCategoria + "/" + gastoMaximo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void visualizarPercentagemGastosPorCategoriaNoOrcamento() {
        try {
            URL url = new URL("http://localhost:8080/RESTServer/categoria/visualizarPercentagemGastosPorCategoriaNoOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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