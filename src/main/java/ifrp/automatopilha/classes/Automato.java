package ifrp.automatopilha.classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automato {
    /*
    Removi:
        MatrizTransicao matriz
        Lista<Integer> estadosFinais;
    
    Adicionei:
        Matriz matrizTransicao;
        MatrizString matrizRetiraPilha;
        MatrizString matrizInserePilha;    
    */ 
    private int estadoInicial;    
    private String sentenca;
    private MatrizTransicao matriz;
    private Matriz retiraPilha;
    private Matriz inserePilha;
    private Pilha p;

    /*
    Metodo construtor
        
    --> p é instanciado como uma nova pilha
    --> sentenca é iniciada como ums String vazia.
    --> estadoInicial é iniciado como 0 por padrão.
    --> Realiza a chamada do metodo preencheMatrizes(enderecoArquivo), para
        que o arquivo seja lido, e as informações sejam colocadas corretamente
        nas matrizes.
    */
    public Automato(String enderecoArquivo) {
        p = new Pilha();
        this.sentenca = "";
        this.estadoInicial = 0;         
        this.preencheMatrizes(enderecoArquivo);
    }
    
    /*
        Metodo criado para ser utilizado pelo preencheMatrizes, ele instancia 
        as matrizes, do tamanho dado pelos parametros.
    */
    private void iniciaMatrizes(int rows, int cols){
        this.matriz = new MatrizTransicao(rows, cols);
        this.retiraPilha = new Matriz(rows, cols);
        this.inserePilha = new Matriz(rows, cols);
    }
    
    /*
        Metodo que ler o arquivo e preenche as matrizes.
    */
    private void preencheMatrizes(String enderecoArquivo){
        try{
            
            FileReader file = new FileReader(enderecoArquivo);
            Scanner arquivo = new Scanner(file);
            
            arquivo.useDelimiter("\n");
            String dimensoesMatrizes = arquivo.next();
            
            /*
                Pega a primeira linha, divide os valores em um vetor, e atribui 
                nas dimensoes das matrizes.
            */
            String[] vet = dimensoesMatrizes.split(";");
            int rows = Integer.parseInt(vet[0]);
            int cols = Integer.parseInt(vet[1]);
            
            this.iniciaMatrizes(rows, cols);
            
            String linhaCorrente;
            int linha, coluna;
            
            /*
                Enquanto houver a proxima linha, o loop vai ser executado
            */
            while (arquivo.hasNext()){
                linhaCorrente = arquivo.next();
                vet = linhaCorrente.split(";");
                
                /*
                    linha = primeiro valor de cada linha do arquivo.
                */
                linha = Integer.parseInt(vet[0]);
                /*
                    Uso de conversão inplicita para obter o indice da coluna,
                    ao fazer operações com CHAR, é pego seu valor na tabela ASCII(eu acho)
                */
                coluna = vet[1].charAt(0) - 'a';
                
                //Atribui na matriz de transição, o proximo estado que devemos ir.
                this.matriz.setCelula(linha, coluna, Integer.parseInt(vet[2]));
                
                //As matrizes por padrao vem toda preenchida por -1.
                /*
                    Caso seja diferente de "e", atribuimos um valor a Matriz que vai
                    retirar da pilha
                */
                if(vet[3].charAt(0) != 'e'){
                    this.retiraPilha.setCelula(linha, coluna, vet[3]);
                }
                
                
                /*
                    Caso seja diferente de "e", atribuimos um valor a Matriz que vai
                    inserir na pilha
                */
                if(vet[4].charAt(0) != 'e'){
                    this.inserePilha.setCelula(linha, coluna, vet[4]);
                }
                
            }
            /*
                Caso queira ver as matrizes geradas para esse automato
            */
            
            System.out.println("-=-=-=-=-=-= Matrizes =-=-=-=-=-=-\n");
            System.out.println("matriz de transição");
            this.matriz.imprimir();
            System.out.println("matriz de Ritirar pilha");
            this.retiraPilha.imprimir();
            System.out.println("matriz de colocar pilha");
            this.inserePilha.imprimir();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Automato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
        Metodo que vai verificar a validade da senteca dada
    */
    public boolean verificaSentenca(String sentenca) {

        this.sentenca = sentenca;
        int estadoAtual = this.estadoInicial;
        
        /*
            Metodo que convete as letras para numeros, usado para facilitar a 
            logica do programa.
        */
        String nova = this.converterSentenca();
        
        /*
            Vai ser percorrida toda a sentenca, letra por letra, essa que vai ser
            definida pelo indice do loop. O loop vai continuar caso tenha caracteres
            na sentença ou caso o estado atual não seja um estado invalido (-1).
        */
        int i = 0;
        while (i <= this.sentenca.length() - 1 && estadoAtual != -1) {
            
            /*
                A letra lida, agora é um numero, este que se refere ao indice 
                da coluna nas matrizes.
                E o estado atual se refere ao indice da linha nas matrizes.
            */
            int col = Integer.parseInt(nova.charAt(i)+"");
            /*
                pegando os valores que irão ser(ou não) adicionados ou retirados da pilha.
            */
            String retirar = this.retiraPilha.getCelula(estadoAtual, col);
            String inserir = this.inserePilha.getCelula(estadoAtual, col);

            /*
                Caso tente-se remover algo da pilha com ela vazia, então a senteça,
                vai ser considerada invalida(False).
                (-1 significa não fazer nada, por isso >= 0)
            */
            if(!retirar.equals("") && p.isEmpty()){
               return false;
            } 
            
            /*
               O elemento só é retirado da pilha se ela possuir elemento para 
               tirar e o elemento a ser tirado corresponde ao topo da pilha;
            */
            if(!retirar.equals("") && !p.isEmpty()){
                for(int j = 0; j < retirar.length(); j++){
                    if(retirar.charAt(0) == p.get().charAt(0)){
                        this.p.pop();
                    }else{
                        //tentou tirar um elemento, que não estava no topo da pilha
                        return false;
                    }
                }                
            }
            
            if(!inserir.equals("")){
                for(int j = 0; j < inserir.length(); j++){
                    this.p.push(inserir.charAt(j) + "");
                }
            }

            /*
                Caso queira ver a pilha em execução.
            */
            
            p.imprimir();
            System.out.println("------------");
            estadoAtual = this.matriz.getCelula(estadoAtual, col);
            
            i++;
        }
        // Verifica se o motivo de sair loop, foi pois ela era invalida.
        if (estadoAtual == -1) {
            return false;
        
        // Caso seja valida, e a Pilha esteja vazia, a sentenca é aceita.
        } else if (p.isEmpty()) {
            return true;
        
        // Caso tenha valor na Pilha, ela vai ser rejeitada
        } else {
            return false;
        }
    }
    
    public String converterSentenca(){
        String aux = "";
        
        for(int i=0; i<=this.sentenca.length()-1;i++){
            aux = aux + this.alfabe2Index(this.sentenca.charAt(i));
        }
        return aux;
    }            
    private char alfabe2Index(char m) {
        char aux = '-';
        switch (m) {
            case 'a':
                aux = '0';
                break;
            case 'b':
                aux = '1';
                break;
            case 'c':
                aux = '2';
                break;
            default:
                aux = '-';
                break;
        }
        return aux;
    }    
}

