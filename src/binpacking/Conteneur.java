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
    private int tailleZ;
    private int[][][][] conteneur;
    
    public Conteneur(){
        
        tailleX = 1;
        tailleY = 1;
        tailleZ = 1;
        conteneur = new int[tailleX][tailleY][tailleZ][2];
        
    }
    
    public Conteneur(int _x, int _y, int _z){
        
        tailleX = _x;
        tailleY = _y;
        tailleZ = _z;
        conteneur = new int[tailleX][tailleY][tailleZ][2];
        
    }
    
    public int getTailleX(){
        
        return tailleX;
        
    }
    
    public int getTailleY(){
        
        return tailleY;
        
    }
    
    public int getTailleZ(){
        
        return tailleZ;
        
    }
    
    public int getXYZ(int _x, int _y, int _z){
        
        return conteneur[_x][_y][_z][0];
        
    }
    
    public int getID(int _x, int _y, int _z){
        
        return conteneur[_x][_y][_z][1];
    }
      
    
    public void setTailleXYZ(int _x, int _y, int _z){
        
        tailleX = _x;
        tailleY = _y;
        tailleZ = _z;
        conteneur = new int[tailleX][tailleY][tailleZ][2];
        
    }
    
    public void setXYZ(int _x, int _y, int _z, int _val){
        
        conteneur[_x][_y][_z][0] = _val;
        
    }
    
    public void setID(int _x, int _y, int _z, int _ID){
        
        conteneur[_x][_y][_z][1] = _ID;
        
    }

    
    // cette fonction permet de définir tous les items à -1 (correspond à vide)
    // cette fonction permet de définir tous les sous conteneurs à 0
    public void init(){
        
        for(int i = 0 ; i < tailleX ; i++){
            
            for(int j = 0 ; j < tailleY ; j++){
                
                for(int l = 0 ; l < tailleZ ; l++){
                    
                   for(int k = 0 ; k < 2 ; k++){
                    
                        if(k==0) conteneur[i][j][l][k] = -1;
                        else conteneur[i][j][l][k] = 0;
                    
                    } 
                    
                }
            
            }
        }        
    }
    
    
    // permet de cloner un conteneur
    public void clone(Conteneur _source){
        
        this.setTailleXYZ(_source.getTailleX(), _source.getTailleY(), _source.getTailleZ());
        for(int i = 0 ; i < this.getTailleX() ; i++){
            
            for(int j = 0 ; j < this.getTailleY() ; j++){
                
                for(int k = 0 ; k < this.getTailleZ() ; k++){
                    
                    this.setXYZ(i, j, k, _source.getXYZ(i,j,k));
                    this.setID(i, j, k, _source.getID(i,j,k));
                    
                }
        
            }
       
        } 
    }
    
    public void afficherContenu(){
        
        System.out.println("-------------------------------");
        
        for(int i = 0 ; i < tailleX ; i++){
            
            for(int j = 0 ; j < tailleY ; j++){
                
                for(int k = 0 ; k < tailleZ ; k++){
                    
                    System.out.println("Conteneur[" + i + "]["+j+"]["+k+"][0] = " + conteneur[i][j][k][0] + "     [" + i + "]["+j+"]["+k+"][1] = " + conteneur[i][j][k][1]);
                    
                }
                
                
                
            }
            
        }
        
        System.out.println("-------------------------------");
        
        
    }
    
    public void afficherID(){
        
        System.out.println("-------------------------------");
        
        for(int i = 0 ; i < tailleX ; i++){
            
            
            
            for(int j = 0 ; j < tailleY ; j++){
                
                for(int k = 0 ; k < tailleZ ; k++){
                    
                    System.out.print(conteneur[i][j][1]+" ");
                    
                }
                
                System.out.println();
                
            }

        }
        
        System.out.println("-------------------------------");
        
    }
    
    public void afficherValues(){
        
         System.out.println("-------------------------------");
        
         
         
        for(int i = 0 ; i < tailleZ ; i++){
            
            
            
            for(int j = 0 ; j < tailleX ; j++){
                
                for(int k = 0 ; k < tailleY ; k++){
                    
                    System.out.print(conteneur[j][k][i][0]+" ");
                    
                }
                
                System.out.println();
                
            }

        }
        
        System.out.println("-------------------------------");
        
    }
    
    // permet de séparer un conteneur en plusieurs sous conteneurs ce qui permet d'optimiser le tri ensuite
    public int split(int maxSplitX, int maxSplitY, int maxSplitZ){
        
        int[][] tailleRestante = new int[tailleX][tailleY];
        for (int i = 0 ; i < tailleX ; i++){
            
            for (int j = 0 ; j < tailleY ; j++){
            
                tailleRestante[i][j] = tailleZ;
            
            
            }
            
            
        }
        int id = 1;
        int compteurX = 0;
        int compteurY = 0;
        int indiceX = 0;
        int indiceY = 0;
        boolean pasDePlaceY = true;
        boolean pasDePlace = true;
        int j = 0;
        int k = 0;
        int i = 0;
        int maxSplitYOrig = maxSplitY;
        int compteur;
        
        if (maxSplitX > this.getTailleX() || maxSplitY > this.getTailleY() || maxSplitZ > this.getTailleZ()){
            
            System.out.println("Le plus grand objet de votre liste ne rentre pas dans ce conteneur, veuillez réessayer.");
            return -1;
            
        } else {
            
            do {
                
                compteurX = 0;
                indiceX=0;
                pasDePlace = true;
                i=0;
                
                while( i < tailleY && pasDePlace == true){
                    
                    k = 0;
                    while( k < tailleY && pasDePlaceY == true){
                    
                                           
                        if(tailleRestante[i][k] >= maxSplitZ){
                        compteurY++;
                        
                        }
                        else {

                            compteurY = 0;
                            indiceY = k+1;

                        }
                        if(compteurY == maxSplitY){

                            pasDePlaceY = false;                                            

                        }
                        k++;
                        
                    }
                    
                    if(pasDePlaceY == false) compteurX++;
                    else{
                        
                        compteurX=0;
                        indiceX = i+1;
                        
                    }
                    if(compteurX == maxSplitX) pasDePlace = false;
                    i++;
                    compteurY=0;
                    pasDePlaceY=true;
                    indiceY = 0;
                                       
                                       
                                       
                }
                
                compteurX = 0;
                i=0;
                k=0;
                
                if(pasDePlace==false){
                    
                    for(i = indiceX ; i < indiceX+maxSplitX ; i++){
                        
                        for(j = indiceY ; j < indiceY+maxSplitY ; j++){
                            
                            compteur = 0;
                            tailleRestante[i][j] = tailleRestante[i][j] - maxSplitZ;
                            do{
                                
                                if(i > this.getTailleX()-1 || j > this.getTailleY()-1 || k > this.getTailleZ()-1 ) break;
                                else{
                                    
                                    
                                    if(conteneur[i][j][k][1] == 0){
                                    
                                        if(compteur > maxSplitZ) break;
                                        this.setID(i, j, k, id);
                                        compteur++;
                                    
                                    }
                                    k++;
                                    System.out.println("i : "+i+" j : "+j+" k : "+k);
                                    System.out.println("compteur : "+compteur+" maxSplitZ : "+maxSplitZ);
                                }
                            }while(compteur != maxSplitZ);
                            
                            System.out.println("on sort");
                            System.out.println("id : "+id);
                        }                                              
                        
                    }
                    
                    id++;
                    
                    System.out.println("maxX : "+maxSplitX+" maxY : "+maxSplitY+" maxZ : "+maxSplitZ);
                    
                } else {
                    
                    boolean xPlusGrand;
                    boolean yPlusGrand;
                    boolean zPlusGrand;
                    
                    xPlusGrand = yPlusGrand = zPlusGrand = false;
                                                           
                    if(maxSplitZ > maxSplitX && maxSplitZ > maxSplitY) zPlusGrand = true;
                    if(maxSplitY > maxSplitX && maxSplitY > maxSplitZ) yPlusGrand = true;
                    if(maxSplitX > maxSplitY && maxSplitX > maxSplitZ) xPlusGrand = true;
                    
                    System.out.println("maxSplitX : "+xPlusGrand+" maxSplitY : "+yPlusGrand+" maxSplitZ : "+zPlusGrand);
                    
                    if(zPlusGrand == true) maxSplitZ--;
                    else if(yPlusGrand == true) maxSplitY--;
                    else if(xPlusGrand == true) maxSplitX--;
                    else {
                        
                        if(maxSplitZ < maxSplitX && maxSplitZ < maxSplitY) maxSplitY--;
                        else maxSplitZ--;
                        
                    }
                    
                }
                
            } while(maxSplitX > 0 || maxSplitY > 0 || maxSplitZ > 0);
            
            return id;
            
        }

    }
    
}




