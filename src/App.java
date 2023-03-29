import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static final String reset = "\u001B[0m";

    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String textoVerde = "\u001B[32m";
    public static final String textoAmarelo = "\u001B[33m";
    public static final String textoPreto = "\u001B[30m";
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";
    public static final String fundoAmarelo = "\u001B[43m";
    public static final String BLACK_UNDERLINED = "\033[4;30m";
    public static final String GREEN_BOLD = "\033[1;32m";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Fazer uma conexao HTTP e buscar os top 250 filmes parte1
        // extrair os dados que interessam (ti­tulo, poster, classificacao) parte2
        // exibir e manipular os dados parte3

        //parte1
        String url = "";

        String localURL = "url.txt"; //string com o local do arquivo que armazena o link do imdb
        url = manipulaArquivo.leitor(localURL); //metodo para ler o arquivo e retornar o que estiver escrito
        URI endereco = URI.create(url);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();

        //parte2
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 
        
        //parte3
        String estrelas = "";
        int qtdeEstrelas = 0;


        for (Map<String,String> filme : listaDeFilmes) {
            System.out.print(textoVerde +"Filme: " + reset);
            System.out.println(textoPreto + fundoAmarelo  + filme.get("title") + reset);
            System.out.print(textoVerde + "Capa: " + reset);
            System.out.println(filme.get("image"));
            qtdeEstrelas = (int)Float.parseFloat(filme.get("imDbRating"));
            System.out.print(textoVerde + "Nota: " + reset);
            System.out.print(qtdeEstrelas + " ");
            estrelas = retornaEstrelas(qtdeEstrelas);
            System.out.println(textoAmarelo + estrelas + reset);            
            System.out.println();
        }

    
    
    }

    public static String retornaEstrelas(int qtde){
        String estrelas = "";
        String caracter = "*";

        for (int x = 0; x < qtde; x++) {
            estrelas = estrelas + caracter;
        }

        return estrelas;
    }
}
