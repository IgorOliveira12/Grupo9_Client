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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ClientTestHttpURLConnection extends Application  {

    /**
     * Método principal que realiza chamadas aos diferentes métodos de teste da aplicação.
     * Cada chamada representa uma operação específica no sistema de Finanças Pessoais.
     */
    public static void main(String[] args) {
    	launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        
    }

    
    
    /**
     * Adiciona uma nova categoria no sistema através de uma requisição HTTP POST.
     */
    public static void addCategoria(String nomeC, double gastoMaximo) {
		HttpURLConnection conn = null;
		Gson gson = new Gson();

		try {

			URL url = new URL("http://localhost:8080/RESTServer/categoria/addCategoria");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			con.setDoOutput(true);
			con.setDoInput(true);

			String postData = gson.toJson(new Categoria((nomeC), gastoMaximo), Categoria.class);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.println("Categoria "+nomeC+" adicionada com sucesso!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
    
    public static void deleteCategoria(String nomeC) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/categoria/deleteCategoria/" + (nomeC));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.print("removido!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
    
    public static void deleteMeta(String nomeMeta) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/meta/deleteMeta/" + (nomeMeta));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.print("removido!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

    /**
     * Obtém a lista de categorias do sistema através de uma requisição HTTP GET.
     * @return 
     */
    public static String getCategorias() {
        try {
            // Criação da URL para a operação de obtenção de categorias
            URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static String getAllCategorias() {
        try {
            // Criação da URL para a operação de obtenção de categorias
            URL url = new URL("http://localhost:8080/RESTServer/categoria/getAllCategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static String getAllSubcategorias() {
        try {
            // Criação da URL para a operação de obtenção de categorias
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/getAllSubcategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static String getAllMetas() {
        try {
            // Criação da URL para a operação de obtenção de categorias
            URL url = new URL("http://localhost:8080/RESTServer/meta/getAllMetas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static String getAllTransacoes() {
        try {
            // Criação da URL para a operação de obtenção de categorias
            URL url = new URL("http://localhost:8080/RESTServer/transacao/getAllTransacoes");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    public static boolean getCategoriaTransacao(String descricao) {
        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://localhost:8080/RESTServer/transacao/procurarCategoria/" + (descricao));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            Gson gson = new Gson();
            Categoria c = gson.fromJson(br, Categoria.class);

            if (c != null) {
                return true;
            } else {
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public static boolean getSubcategoriaTransacao(String descricao) {
        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://localhost:8080/RESTServer/transacao/procurarSubcategoria/" + (descricao));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            Gson gson = new Gson();
            Categoria c = gson.fromJson(br, Categoria.class);

            if (c != null) {
                return true;
            } else {
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }	
            }
        }
        return false;
    }
    
    /**
     * Obtém uma categoria específica do sistema através de uma requisição HTTP GET.
     *
     * @param nomeCategoria O nome da categoria desejada.
     * @return 
     */
    public static boolean getCategoria(String nomeC) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategoria/" + (nomeC));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.connect();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			Gson gson = new Gson();
			Categoria c = gson.fromJson(br, Categoria.class);
			System.out.println("Output JSON from Server .... \n");
			System.out.println(c);
			return c != null;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}

    /**
     * Altera o gasto máximo de uma categoria no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeCategoria O nome da categoria a ser alterada.
     * @param gastoMaximo   O novo valor máximo para a categoria.
     */
    public static void alterarGastoMaximoCategoria(String nomeCategoria, double gastoMaximo) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/alterarGastoMaximo/"
                    + (nomeCategoria) + "/" + gastoMaximo);
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
     * @return 
     */
    public static String visualizarPercentagemGastosPorCategoriaNoOrcamento() {
        try {
            // Criação da URL para a operação de visualização de percentagem de gastos por categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/visualizarPercentagemGastosPorCategoriaNoOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Adiciona uma nova subcategoria no sistema através de uma requisição HTTP POST.
     */
    public static void addSubcategoria(String nomeSubc, double gastoMaxSubc) {
		HttpURLConnection conn = null;
		Gson gson = new Gson();

		try {

			URL url = new URL("http://localhost:8080/RESTServer/subcategoria/addSubcategoria");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			con.setDoOutput(true);
			con.setDoInput(true);

			String postData = gson.toJson(new Subcategoria((nomeSubc), gastoMaxSubc), Subcategoria.class);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.println("Subcategoria "+nomeSubc+" adicionada com sucesso!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
    /**
     * Obtém a lista de subcategorias do sistema através de uma requisição HTTP GET.
     * @return 
     */
    public static String getSubcategorias() {
        try {
            // Criação da URL para a operação de obtenção de subcategorias
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/getSubcategorias");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static boolean getSubcategoria(String nomeSubc) {
		HttpURLConnection conn = null;

		try {

            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/getSubcategoria/" + (nomeSubc));
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.connect();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			Gson gson = new Gson();
			Subcategoria s = gson.fromJson(br, Subcategoria.class);
			System.out.println("Output JSON from Server .... \n");
			return s != null;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}

    /**
     * Altera o gasto máximo de uma subcategoria no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeSubcategoria O nome da subcategoria a ser alterada.
     * @param gastoMaximo      O novo valor máximo para a subcategoria.
     */
    public static void alterarGastoMaximoSubcategoria(String nomeSubcategoria, double gastoMaximo) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/alterarGastoMaximo/" +
            		(nomeSubcategoria) + "/" + gastoMaximo);
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
    public static void deleteSubcategoria(String nomeSubc) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/subcategoria/deleteSubcategoria/" + (nomeSubc));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.print("removido!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
    
    public static void atribuirCategoriaNaSubcategoria(String nomeC, String nomeSubc) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/atribuirCategoriaNaSubcategoria/" +
            		(nomeC) + "/" + (nomeSubc));
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
    
    public static void atribuirTransacaoEmCategoria(String nomeC, String descricao) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/transacao/atribuirTransacaoEmCategoria/" +
            		(nomeC) + "/" + (descricao));
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
    
    public static void atribuirTransacaoEmSubcategoria(String nomeSubc, String descricao) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/transacao/atribuirTransacaoEmSubcategoria/" +
            		(nomeSubc) + "/" + (descricao));
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
    
    public static void atribuirTransacaoEmMeta(String nomeMeta, String descricao) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/transacao/atribuirTransacaoEmMeta/" +
            		(nomeMeta) + "/" + (descricao));
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
     * Calcula a percentagem de gastos para uma subcategoria específica em relação aos gastos totais da categoria,
     * através de uma requisição HTTP GET.
     *
     * @param nomeSubcategoria O nome da subcategoria para a qual a percentagem de gastos será calculada.
     * @param gastosCategoria  O total de gastos da categoria à qual a subcategoria pertence.
     */
    public static void calcularPercentagemGastos(String nomeSubc, double gastosCategoria) {
        try {
            // Criação da URL para a operação de cálculo de percentagem de gastos
            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/calcularPercentagemGastos/" +
                    nomeSubc + "/" + gastosCategoria);
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
    public static String getOrcamentos() {
        try {
            // Criação da URL para a operação de obtenção de orçamentos
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/getOrcamentos");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição e retorna como String
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Em caso de erro, você pode retornar null ou algum valor padrão, dependendo do seu caso.
        return null;
    }

    /**
     * Obtém um orçamento específico do sistema através de uma requisição HTTP GET.
     *
     * @param dataCriacao O valor anual do orçamento desejado.
     * @return 
     */
    public static boolean getOrcamento(String dataCriacao) {
        try {
            // Criação da URL para a operação de obtenção de um orçamento específico
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/getOrcamento/" + dataCriacao);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
            return con != null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
    }

    /**
     * Adiciona um novo orçamento no sistema através de uma requisição HTTP POST.
     *
     * @param orcamento O objeto Orcamento a ser adicionado.
     */
    public static void addOrcamento(String dataCriacao, double valorAnual) {
		HttpURLConnection conn = null;
		Gson gson = new Gson();

		try {

			URL url = new URL("http://localhost:8080/RESTServer/orcamento/addOrcamento");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			con.setDoOutput(true);
			con.setDoInput(true);

			String postData = gson.toJson(new Orcamento(dataCriacao, valorAnual), Orcamento.class);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.println("Orcamento "+valorAnual+" adicionada com sucesso!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}


    /**
     * Adiciona ou reduz o valor do orçamento atual através de uma requisição HTTP PUT.
     *
     * @param valorAlteracao O valor a ser adicionado ou reduzido no orçamento atual.
     */
    public static void adicionarOuReduzirValorOrcamento(double valorAlteracao) {
        try {
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
    public static void obterUltimoOrcamento() {
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
     * Imprime o histórico de orçamentos do sistema através de uma requisição HTTP GET.
     * @return 
     */
    public static String imprimirHistoricoOrcamentos() {
        try {
            // Criação da URL para a operação de impressão do histórico de orçamentos
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/imprimirHistoricoOrcamentos");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Mostra o status atual do orçamento através de uma requisição HTTP GET.
     * @return 
     */
    public static String mostrarStatusOrcamento() {
        try {
            // Criação da URL para a operação de obtenção do status do orçamento
            URL url = new URL("http://localhost:8080/RESTServer/orcamento/mostrarStatusOrcamento");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    // ... Outros métodos existentes ...

    /**
     * Adiciona uma nova transação no sistema através de uma requisição HTTP POST.
     *
     * @param transacao O objeto Transacao a ser adicionado.
     */
    public static void addTransacao(String data, double valor, String descricao) {
		HttpURLConnection conn = null;
		Gson gson = new Gson();

		try {

			URL url = new URL("http://localhost:8080/RESTServer/transacao/addTransacao");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			con.setDoOutput(true);
			con.setDoInput(true);

			String postData = gson.toJson(new Transacao(descricao, valor, data), Transacao.class);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.println("Transacao "+descricao+" adicionada com sucesso!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

    /**
     * Obtém a lista de transações do sistema através de uma requisição HTTP GET.
     * @return 
     */
    public static String getTransacoes() {
        try {
            // Criação da URL para a operação de obtenção de transações
            URL url = new URL("http://localhost:8080/RESTServer/transacao/getTransacoes");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Obtém uma transação específica do sistema através de uma requisição HTTP GET.
     *
     * @param descricao A descrição da transação desejada.
     * @return 
     */
    public static boolean getTransacao(String descricao) {
        try {
            // Criação da URL para a operação de obtenção de uma transação específica
            URL url = new URL("http://localhost:8080/RESTServer/transacao/getTransacao/" + descricao);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
            return con != null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
    }
    
    public static void deleteTransacao(String descricao) {
		HttpURLConnection conn = null;

		try {
			
            URL url = new URL("http://localhost:8080/RESTServer/transacao/deleteTransacao/" + descricao);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.print("removido!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

    /**
     * Altera a data de uma transação no sistema através de uma requisição HTTP PUT.
     *
     * @param descricao      A descrição da transação a ser alterada.
     * @param novaData  A nova data da transação.
     */
    public static void alterarDataTransacao(String descricao, String novaData) {
        try {
            // Criação da URL para a operação de alteração de data de transação
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarData/" + (descricao) + "/" + (novaData));
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
    public static void addMeta(String nome, double valor, String data, String descricao) {
		HttpURLConnection conn = null;
		Gson gson = new Gson();

		try {

			URL url = new URL("http://localhost:8080/RESTServer/meta/addMeta");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			con.setDoOutput(true);
			con.setDoInput(true);

			String postData = gson.toJson(new Meta((nome), descricao, valor, data), Meta.class);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postData);
			wr.flush();
			wr.close();

			if (con.getResponseCode() < 200 && con.getResponseCode() > 299) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			} else {
				System.out.println("Meta "+nome+" adicionada com sucesso!");
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

    /**
     * Obtém a lista de metas do sistema através de uma requisição HTTP GET.
     * @return 
     */
    public static String getMetas() {
        try {
            // Criação da URL para a operação de obtenção de metas
            URL url = new URL("http://localhost:8080/RESTServer/meta/getMetas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Obtém uma meta específica do sistema através de uma requisição HTTP GET.
     *
     * @param nomeMeta O nome da meta desejada.
     * @return 
     */
    public static boolean getMeta(String nome) {
        try {
            // Criação da URL para a operação de obtenção de uma meta específica
            URL url = new URL("http://localhost:8080/RESTServer/meta/getMeta/" + (nome));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Processa a resposta da requisição
            handleResponse(con);
            return con != null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return false;
    }

    /**
     * Altera o valor de uma meta no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeMeta      O nome da meta a ser alterada.
     * @param novoValor  O novo valor da meta.
     */
    public static void alterarValorMeta(String nomeMeta, double novoValor) {
        try {
            // Criação da URL para a operação de alteração de valor de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/alterarValorMeta/" +
            		(nomeMeta) + "/" + novoValor);
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
    public static void alterarPrazoMeta(String nomeMeta, String novaData) {
        try {
            // Criação da URL para a operação de alteração de prazo de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/alterarPrazoMeta/" +
            		(nomeMeta) + "/" + novaData);
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
     * @return 
     */
    public static String verificarMetasCumpridas() {
        try {
            // Criação da URL para a operação de verificação de metas cumpridas
            URL url = new URL("http://localhost:8080/RESTServer/meta/verificarMetasCumpridas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

    /**
     * Lista as metas não cumpridas no sistema através de uma requisição HTTP GET.
     * @return 
     */
    public static String listarMetasNaoCumpridas() {
        try {
            // Criação da URL para a operação de listagem de metas não cumpridas
            URL url = new URL("http://localhost:8080/RESTServer/meta/listarMetasNaoCumpridas");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Processa a resposta da requisição
            return handleResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }

	/*
	 * public static void handleResponse(HttpURLConnection con) throws IOException {
	 * int responseCode = con.getResponseCode(); if (responseCode ==
	 * HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED)
	 * { BufferedReader in = new BufferedReader(new
	 * InputStreamReader(con.getInputStream())); String inputLine; StringBuilder
	 * response = new StringBuilder();
	 * 
	 * while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
	 * in.close();
	 * 
	 * System.out.println("Server Response:\n" + response.toString()); } else {
	 * System.out.println("Failed : HTTP error code : " + responseCode); } }
	 */
    
 // Método para processar a resposta e retornar como String
    private static String handleResponse(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } else {
            // Se a resposta não for bem-sucedida, pode lançar uma exceção ou retornar um valor de erro
            throw new IOException("Falha na requisição: " + responseCode);
        }
    }
}