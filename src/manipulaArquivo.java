import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class manipulaArquivo {
    public static String leitor(String path) throws IOException {
        String retorna = "";
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				retorna =  linha;

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
        return retorna;
	}
}
