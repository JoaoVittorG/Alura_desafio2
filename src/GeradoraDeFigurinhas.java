import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

// a classe geradora de figurinhas recebe uma URL ou um arquivo juntamente com o nome do arquivo que sera gravado
// a partir disso ela faz uma junção de duas imagens 
public class GeradoraDeFigurinhas {
    public void cria(InputStream inputStream, String nomeArquivo, int notaFilme) throws Exception{
        String nomePC = "joaov";
        String caminhoProjeto = "C:/Users/" + nomePC + "/Desktop/imersao/segundoProjeto";
        
        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memoria com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);


        //copia a imagem original pra novo imagem (em memoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);


        //configurar a fonte        
        Font fonteImpact = Font.createFont(Font.TRUETYPE_FONT, new File(caminhoProjeto +"/fonte/impact.ttf")).deriveFont(Font.PLAIN, 120);
        FontMetrics fm = novaImagem.getGraphics().getFontMetrics(fonteImpact);
        graphics.setFont(fonteImpact);


        //colocar foto junto da imagem
        String textoPNG = retornaTextoImagem(notaFilme);
        BufferedImage imagemJoao = ImageIO.read(new File(caminhoProjeto + "/entrada/"+ textoPNG + ".png"));
        graphics.drawImage(imagemJoao, 0, novaAltura - imagemJoao.getHeight(), null);
        

        // escrever uma frase na nova imagem 

        int x = (largura - fm.stringWidth(textoPNG))/2;
        int y = novaAltura - 70;

        graphics.setColor(Color.blue);
        graphics.drawString(textoPNG, x+3, y+3);
        graphics.drawString(textoPNG, x+1, y-1);
        graphics.drawString(textoPNG, x-1, y+1);
        graphics.drawString(textoPNG, x-1, y-1);
        graphics.setColor(Color.yellow);
        graphics.drawString(textoPNG, x , y );

        // verificar se a pasta saida esta criada, caso nao estiver ele a cria
        File file = new File(caminhoProjeto + "/saida");  
            
            if (!file.exists()) {
                file.mkdirs();  
        }
        

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/"+ nomeArquivo));
 
    }


    public String retornaTextoImagem(int notaFilme){
        String texto = "";

        if (notaFilme < 5){
            texto = "RUIM";
        }
        else if(notaFilme >= 9){
            texto = "TOPZERA";
        }
        else{
            texto = "MAIS OU MENOS";
        }

        return texto;


    }
}
