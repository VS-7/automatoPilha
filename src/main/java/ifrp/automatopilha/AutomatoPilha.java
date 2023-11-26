package ifrp.automatopilha;

import ifrp.automatopilha.classes.Automato;

/**
 *
 * @author vitorsergio
 */
public class AutomatoPilha {

    public static void main(String[] args) {
/*
Ao instanciar o automato, passe o endereço do arquivo que ele esta contido,
    com a seguinte ordem dos dodos:
(Num de estados[Sem o final]);(se usou apenas a = 1, se usou b = 2 e se usou c = 3);
(Estado atual);(Letra lida);(Proximo estado);(Retira da pilha);(Insere na Pilha);
.
.
.
ex:
    2;3;
    0;a;0;e;AA;
    0;c;1;AA;e;
    1;c;1;AA;e;
*/
        Automato linguagemAABB = new Automato("anbkcnplusk.txt");        

/*
    Caso a sentença passada no paramentro for valida, ira retornar True (Aceita),
        se não vai retornar False (Rejeita).
*/
        if (linguagemAABB.verificaSentenca("abcc")) {
            System.out.println("Aceita");
        }else{
            System.out.println("Rejeita");
        }
    }
}