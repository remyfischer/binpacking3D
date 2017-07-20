/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacking;

/**
 *
 * @author remy.fischer
 */

// classe conteneur correspondant à l'avion 
// l'avion est considéré comme étant à deux dimensions
// des fonctions permettent d'effectuers des opérations sur le conteneur.

// on crée un conteneur qui est un tableau à trois dimensions :
// une dimension pour l'axe X
// une dimension pour l'axe Y
// une troisième dimension permettant d'avoir une vision sur les sous conteneurs et sur les item présent dans le conteneur

public class Conteneur {
    
    private int tailleX;
    private int tailleY;
    private int[][][] conteneur;
    
    public Conteneur(){
        
        tailleX = 1;
        tailleY = 1;
        conteneur = new int[tailleX][tailleY][2];
        
    }
    
    public Conteneur(int _x, int _y){
        
        tailleX = _x;
        tailleY = _y;
        conteneur = new int[tailleX][tailleY][2];
        
    }
    
    public int getTailleX(){
        
        return tailleX;
        
    }
    
    public int getTailleY(){
        
        return tailleY;
        
    }
    
    public int getXY(int _x, int _y){
        
        return conteneur[_x][_y][0];
        
    }
    
    public int getID(int _x, int _y){
        
        return conteneur[_x][_y][1];
    }
      
    
    public void setTailleXY(int _x, int _y){
        
        tailleX = _x;
        tailleY = _y;
        conteneur = new int[tailleX][tailleY][2];
        
    }
    
    public void setXY(int _x, int _y, int _val){
        
        conteneur[_x][_y][0] = _val;
        
    }
    
    public void setID(int _x, int _y, int _ID){
        
        conteneur[_x][_y][1] = _ID;
        
    }

    
    // cette fonction permet de définir tous les items à -1 (correspond à vide)
    // cette fonction permet de définir tous les sous conteneurs à 0
    public void init(){
        
        for(int i = 0 ; i < tailleX ; i++){
            
            for(int j = 0 ; j < tailleY ; j++)
                
                for(int k = 0 ; k < 2 ; k++){
                    
                    if(k==0) conteneur[i][j][k] = -1;
                    else conteneur[i][j][k] = 0;
                    
                }
                
            }
        
    }
    
    
    // permet de cloner un conteneur
    public void clone(Conteneur _source){
        
        this.setTailleXY(_source.getTailleX(), _source.getTailleY());
        for(int i = 0 ; i < this.getTailleX() ; i++){
            
            for(int j = 0 ; j < this.getTailleY() ; j++){
                
                this.setXY(i, j, _source.getXY(i,j));
                this.setID(i, j, _source.getID(i,j));
                
            }
            
            
        }
        
    }
    
    public void afficherContenu(){
        
        System.out.println("-------------------------------");
        
        for(int i = 0 ; i < tailleX ; i++){
            
            for(int j = 0 ; j < tailleY ; j++){
                
                System.out.println("Conteneur[" + i + "]["+j+"][0] = " + conteneur[i][j][0] + "     [" + i + "]["+j+"][1] = " + conteneur[i][j][1]);
                
            }
            
        }
        
        System.out.println("-------------------------------");
        
        
    }
    
    public void afficherID(){
        
        System.out.println("-------------------------------");
        
        for(int i = 0 ; i < tailleX ; i++){
            
            
            
            for(int j = 0 ; j < tailleY ; j++){
                
                System.out.print(conteneur[i][j][1]+" ");
                
            }
            
            System.out.println();
            
        }
        
        System.out.println("-------------------------------");
        
    }
    
    public void afficherValues(){
        
         System.out.println("-------------------------------");
        
        for(int i = 0 ; i < tailleX ; i++){
            
            
            
            for(int j = 0 ; j < tailleY ; j++){
                
                System.out.print(conteneur[i][j][0]+" ");
                
            }
            
            System.out.println();
            
        }
        
        System.out.println("-------------------------------");
        
    }
    
    // permet de séparer un conteneur en plusieurs sous conteneurs ce qui permet d'optimiser le tri ensuite
    public int split(int maxSplitX, int maxSplitY){
        
        int[] tailleRestante = new int[tailleY];
        for (int i = 0 ; i < tailleY ; i++){
            
            tailleRestante[i] = tailleX;
            
        }
        int id = 1;
        int compteur = 0;   
        int indice = 0;
        boolean pasDePlace = true;
        int j = 0;
        int maxSplitYOrig = maxSplitY;
        
        if (maxSplitX > this.getTailleX() || maxSplitY > this.getTailleY()){
            
            System.out.println("Le plus grand objet de votre liste est plus grand que ce conteneur, veuillez réessayer.");
            return -1;
            
        } else {
            
            do {
                
                compteur = 0;
                indice=0;
                pasDePlace = true;
                
                for(int i = 0 ; i < tailleY ; i++){
                    
                    
                    if(tailleRestante[i] >= maxSplitX){
                        
                        compteur++;
                        
                    }
                    else {
                        
                        compteur = 0;
                        indice = i+1;
         
                    }
                    if(compteur == maxSplitY){
                        
                        pasDePlace=false;
                        break;
                        
                        
                        
                    }
                    
                }
                
                compteur = 0;
                
                if(pasDePlace==false){
                    
                    for(int i = indice ; i < indice+maxSplitY ; i++){
                        
                        compteur = 0;
                        j=0;
                        tailleRestante[i] = tailleRestante[i] - maxSplitX;
                        do{
                            
                            if(conteneur[j][i][1] == 0){

                                if(compteur > maxSplitX) break;
                                this.setID(j, i, id);
                                compteur++;
                                
                            }
                            j++;
                            
                        }while(compteur != maxSplitX);
                        
                    }
                    
                    id++;
                    
                } else {
                    
                    if (maxSplitX == 1 && maxSplitY == 1) break;
                    if(maxSplitY == 1){
                        
                        maxSplitY = maxSplitYOrig;
                        maxSplitX--;
                        
                    } else maxSplitY--;
                    
                }
                
            } while(maxSplitX > 0 || maxSplitY > 0);
            
            return id;
            
        }

    }
    
}




