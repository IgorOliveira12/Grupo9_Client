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
    	
        // 100% funcional
    	//getOrcamentos();
    	//addOrcamento("10/12/2022", 18000.0);
    	//adicionarOuReduzirValorOrcamento(500.0);
    	//imprimirHistoricoOrcamentos();
    	//mostrarStatusOrcamento();


        // 100% funcional
        //addCategoria("Moradia", 9000.0);
    	//getCategorias();
        //alterarGastoMaximoCategoria("Moradia",3890.0);
        //deleteCategoria("Moradia");
        //getCategoria("Moradia");
        //visualizarPercentagemGastosPorCategoriaNoOrcamento();
        
        
        // 100% funcional
        //addSubcategoria("Comidalegumes",1000.0);
        //getSubcategorias();
        //getSubcategoria("Refeicoes fora de casa");
        //alterarGastoMaximoSubcategoria("Comidalegumes", 2500.0);
        //deleteSubcategoria("Comidalegumes");

        
        // 100% funcional
        //addTransacao("10/10/2005", 100.0, "Pagar comida ao cao");
        //getTransacoes();
        //getTransacao("Alimentacao"); 
        //deleteTransacao("Alimentacao");
        //alterarCategoriaTransacao("Alimentacao", "Transporte"); 
        //alterarSubcategoriaTransacao("Autocarro", "Supermercado"); 
        //alterarDataTransacao("Alimentacao", "10/10/2030");

        // 100% funcional
        //addMeta("Carro de sonho",15000.0,"15/12/2026","Quero alcancar a Ferrari o mais rapido possivel");
        //getMetas();
        //getMeta("Carro");
        //alterarValorMeta("Carro", 15000.0);
        //alterarPrazoMeta("Carro", "05/12/2028"); 
        //verificarMetasCumpridas();
        //listarMetasNaoCumpridas();

    }
    
    public static String replaceS(String input) {
        return input.replaceAll(" ", "_");
    }
    
    public static String replaceDate(String input) {
        return input.replaceAll("/", "_");
    }


    /**
     * Adiciona uma nova categoria no sistema através de uma requisição HTTP POST.
     */
    private static void addCategoria(String nomeC, double gastoMaximo) {
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

			String postData = gson.toJson(new Categoria(replaceS(nomeC), gastoMaximo), Categoria.class);

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
    
    private static void deleteCategoria(String nomeC) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/categoria/deleteCategoria/" + replaceS(nomeC));
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
    private static void getCategoria(String nomeC) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/categoria/getCategoria/" + replaceS(nomeC));
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
     * Altera o gasto máximo de uma categoria no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeCategoria O nome da categoria a ser alterada.
     * @param gastoMaximo   O novo valor máximo para a categoria.
     */
    private static void alterarGastoMaximoCategoria(String nomeCategoria, double gastoMaximo) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da categoria
            URL url = new URL("http://localhost:8080/RESTServer/categoria/alterarGastoMaximo/"
                    + replaceS(nomeCategoria) + "/" + gastoMaximo);
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
    private static void addSubcategoria(String nomeSubc, double gastoMaxSubc) {
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

			String postData = gson.toJson(new Subcategoria(replaceS(nomeSubc), gastoMaxSubc), Subcategoria.class);

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
    
    private static void getSubcategoria(String nomeSubc) {
		HttpURLConnection conn = null;

		try {

            URL url = new URL("http://localhost:8080/RESTServer/subcategoria/getSubcategoria/" + replaceS(nomeSubc));
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
			System.out.println(s);

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
            		replaceS(nomeSubcategoria) + "/" + gastoMaximo);
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
    private static void deleteSubcategoria(String nomeSubc) {
		HttpURLConnection conn = null;

		try {

			URL url = new URL("http://localhost:8080/RESTServer/subcategoria/deleteSubcategoria/" + replaceS(nomeSubc));
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
     * Calcula a percentagem de gastos para uma subcategoria específica em relação aos gastos totais da categoria,
     * através de uma requisição HTTP GET.
     *
     * @param nomeSubcategoria O nome da subcategoria para a qual a percentagem de gastos será calculada.
     * @param gastosCategoria  O total de gastos da categoria à qual a subcategoria pertence.
     */
    private static void calcularPercentagemGastos(String nomeSubc, double gastosCategoria) {
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
    private static void addOrcamento(String dataCriacao, double valorAnual) {
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
    private static void adicionarOuReduzirValorOrcamento(double valorAlteracao) {
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
    private static void addTransacao(String data, double valor, String descricao) {
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
            URL url = new URL("http://localhost:8080/RESTServer/transacao/getTransacaoData/" + descricao);
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
    
    private static void deleteTransacao(String descricao) {
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

    private static void alterarCategoriaTransacao(String descricao, String novaCategoria) {
        try {
            // Criação da URL para a operação de alteração de gasto máximo da subcategoria
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarCategoria/" + replaceS(descricao) + "/" + replaceS(novaCategoria));
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
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarSubcategoria/" + replaceS(descricao) + "/" + replaceS(novaSubcategoria));
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
            URL url = new URL("http://localhost:8080/RESTServer/transacao/alterarData/" + replaceS(descricao) + "/" + replaceDate(novaData));
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
    private static void addMeta(String nome, double valor, String data, String descricao) {
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

			String postData = gson.toJson(new Meta(replaceS(nome), descricao, valor, data), Meta.class);

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
    private static void getMeta(String nome) {
        try {
            // Criação da URL para a operação de obtenção de uma meta específica
            URL url = new URL("http://localhost:8080/RESTServer/meta/getMeta/" + replaceS(nome));
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
     * Altera o valor de uma meta no sistema através de uma requisição HTTP PUT.
     *
     * @param nomeMeta      O nome da meta a ser alterada.
     * @param novoValor  O novo valor da meta.
     */
    private static void alterarValorMeta(String nomeMeta, double novoValor) {
        try {
            // Criação da URL para a operação de alteração de valor de meta
            URL url = new URL("http://localhost:8080/RESTServer/meta/alterarValorMeta/" +
            		replaceS(nomeMeta) + "/" + novoValor);
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
            		replaceS(nomeMeta) + "/" + replaceDate(novaData));
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