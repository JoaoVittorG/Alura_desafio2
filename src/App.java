import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLACK_UNDERLINED = "\033[4;30m";
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Fazer uma conexao HTTP e buscar os top 250 filmes parte1
        // extrair sÃ³ os dados que interessam (tÃ­tulo, poster, classificaÃ§Ã£o) parte2
        // exibir e manipular os dados parte3

        //parte1
        String url = "";

        String localURL = "url.txt";
        url = manipulaArquivo.leitor(localURL);
        URI endereco = URI.create(url);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();

        //parte2
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body); 
        
        //System.out.println(ANSI_RESET +"teste" );
        //parte3
        String estrela = "?";
        System.out.println(estrela);
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(BLACK_UNDERLINED + ANSI_YELLOW_BACKGROUND  + filme.get("title") + RESET);
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating") + estrela);
            System.out.println();
        }

    
    
    }


}
