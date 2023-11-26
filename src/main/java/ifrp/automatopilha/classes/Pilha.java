package ifrp.automatopilha.classes;

import java.util.ArrayList;

public class Pilha {
    private ArrayList<String> pilha;
    
    Pilha(){
        pilha = new ArrayList();
    }
    
    public void push(String elemento){
        pilha.add(elemento);
    }
    
    public void pop(){
        pilha.remove(pilha.size() - 1);
    }
    
    public boolean isEmpty(){
        return pilha.isEmpty();
    }
    
    public void imprimir(){
        for(int i = pilha.size() - 1; i >= 0; i--){
            System.out.println(pilha.get(i));
        }
    }
    
    public String get(){
        return pilha.get(pilha.size()-1);
    }
}
