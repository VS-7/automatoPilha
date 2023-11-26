package ifrp.automatopilha.classes;

public class MatrizTransicao {
        public int [][] matriz;
        private int cols, rows;
        
    public MatrizTransicao(int l, int c) {
        this.rows = l;  
        this.cols = c;
        this.matriz = new int[rows][cols];
        this.preencheMenosUm();
    }
    
    private void preencheMenosUm(){
        for(int i= 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                this.matriz[i][j] = -1;
            }
        }
    }
    
    public int getCelula(int l, int c){        
        return this.matriz[l][c];                    
    }
    
    public void setCelula(int l, int c, int v){
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
