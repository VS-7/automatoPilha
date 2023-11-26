package ifrp.automatopilha.classes;


public class Matriz {
    
        public String [][] matriz;
        private int cols, rows;
        
    public Matriz(int l, int c) {
        this.rows = l;  
        this.cols = c;
        this.matriz = new String[rows][cols];
        this.preencheVazio();
    }
    
    private void preencheVazio(){
        for(int i= 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                this.matriz[i][j] = "";
            }
        }
    }
    
    public String getCelula(int l, int c){        
        return this.matriz[l][c];                    
    }
    
    public void setCelula(int l, int c, String v){
        this.matriz[l][c] = v;
    }
    
    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
    
    public void imprimir(){
        for(int i= 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                System.out.print("\t" + this.matriz[i][j]);
            }
            System.out.println(" ");
        }
    }
        
}

