/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacking;
import java.util.*;

/**
 *
 * @author remy.fischer
 */

//Binpacking resolution algorithm project

public class BinPacking {
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        Scanner sc = new Scanner(System.in);

        int maxDimItemX, maxDimItemY;
        int nbSousConteneur;
        
        Conteneur avionTest = new Conteneur(5,8);
        TabItem tabItemTest = new TabItem();
        
        avionTest.init();
        
        
        Item item0 = new Item(3,2);
        Item item1 = new Item(2,1);
        Item item2 = new Item(3,3);
        Item item3 = new Item(1,2);
        Item item4 = new Item(1,1);
        Item item5 = new Item(1,3);
        Item item6 = new Item(2,2);
        Item item7 = new Item(3,1);
        
        tabItemTest.AjoutItem(item0);
        tabItemTest.AjoutItem(item1);
        tabItemTest.AjoutItem(item2);
        tabItemTest.AjoutItem(item3);
        tabItemTest.AjoutItem(item4);
        tabItemTest.AjoutItem(item5);
        tabItemTest.AjoutItem(item6);
        tabItemTest.AjoutItem(item7);
        
        

        for (int i = 0 ; i < tabItemTest.getNbItem() ; i++){
            
            System.out.println("id item "+i+ " : "+tabItemTest.getItem(i).getID()+" "+tabItemTest.getItem(i).getTailleX()+" "+tabItemTest.getItem(i).getTailleY());
            
        }
        
        maxDimItemX = tabItemTest.getPlusGrandeDimensionX();
        maxDimItemY = tabItemTest.getPlusGrandeDimensionY();
        
        System.out.println("maxDimX = "+ maxDimItemX + "maxDimY = "+maxDimItemY);
        
        nbSousConteneur = avionTest.split(maxDimItemX, maxDimItemY);
        
        avionTest.afficherID();
        
        avionTest.afficherValues();
        
        testOptimisationAlgorithmeTri(tabItemTest, avionTest, nbSousConteneur);
        
    }
    
    // fonction permettant de lancer les deux algorithmes de tri et de retenir le plus optimisé    
    public static void testOptimisationAlgorithmeTri(TabItem _tabItem, Conteneur _conteneur, int _nbSousconteneur){
        
        Conteneur cFirstFit = new Conteneur();
        Conteneur cFirstFitDecreasing = new Conteneur();
        
        cFirstFit.clone(_conteneur);
        cFirstFitDecreasing.clone(_conteneur);
        
        double firstFit;
        double firstFitDecreasing;
        
        firstFit = firstFit(_tabItem, cFirstFit, _nbSousconteneur);
        firstFitDecreasing = firstFitDecreasing(_tabItem, cFirstFitDecreasing, _nbSousconteneur);
        
        System.out.println("Methode First Fit : "+firstFit+"% de remplissage");
        System.out.println("Methode First Fit Decreasing : "+firstFitDecreasing+"% de remplissage");
        
        if (firstFit >= firstFitDecreasing){
            
            System.out.println("La solution firstfit est la plus optimisée, voici le résultat");
            _conteneur.clone(cFirstFit);
            _conteneur.afficherValues();
            
        }
        
        if (firstFit < firstFitDecreasing){
            
            System.out.println("La solution firstfit decreasing est la plus optimisée, voici le résultat");
            _conteneur.clone(cFirstFitDecreasing);
            _conteneur.afficherValues();
        }
        
        
    }
    
    // algorithme en firstfit
    // firstfit : vérifie si l'item rentre, si il rentre on le place dans le conteneur, si il ne rentre pas on passe à l'item suivant
    // et ainsi de suite jusqu'à avoir essayé de placer tous les item
    
    // Pour ce faire, on vérifie si un item rentre dans un sous conteneur, si il trouve un sous conteneur dans lequel il rentre
    public static double firstFit(TabItem _tabItem, Conteneur _conteneur, int _nbSousConteneur){
        
        int compteurX;
        int compteurY;
        boolean sousConteneurLibre;
        int cptSousConteneur;
        double remplissage = 0;
        
        for(int k = 0 ; k < _tabItem.getNbItem() ; k++){
        
            int ii = 0;
            int jj = 0;
            sousConteneurLibre = false;
            cptSousConteneur = 0;
            
            
            // boucle permettant de vérifier si il y a un sous conteneur permettant d'accueillir l'item
            // sousConteneurLibre prend la valeur true si c'est le cas
            do{
                
                cptSousConteneur++;
                ii=0;
                compteurX=0;
                compteurY=0;
                
                while( ii < _conteneur.getTailleX() && sousConteneurLibre == false){

                    compteurY = 0;

                    while ( jj < _conteneur.getTailleY() && compteurY != _tabItem.getItem(k).getTailleY() ){

                        if(_conteneur.getXY(ii, jj) == -1 && cptSousConteneur == _conteneur.getID(ii, jj)){
                            
                            compteurY++;
                            
                        }
                        jj++;

                    }
                    
                    if(compteurY == _tabItem.getItem(k).getTailleY()) compteurX++;
                    if(compteurX == _tabItem.getItem(k).getTailleX()) sousConteneurLibre = true;
                    ii++;
                    jj=0;

                }


            } while (cptSousConteneur <= _nbSousConteneur && sousConteneurLibre==false);
            
            
            // si un sous conteneur est libre (sousConteneurLibre == true), on le place dans ce sous-conteneur
            if(sousConteneurLibre == true){
            
            compteurX=0;
            compteurY=0;
            int iii=0;
            int jjj=0;
            
                while(iii < _conteneur.getTailleX() && compteurX < _tabItem.getItem(k).getTailleX()){

                    while(jjj < _conteneur.getTailleY() && compteurY < _tabItem.getItem(k).getTailleY()){

                        if(cptSousConteneur == _conteneur.getID(iii, jjj) && _conteneur.getXY(iii, jjj)==-1){

                            _conteneur.setXY(iii, jjj, _tabItem.getItem(k).getID());
                            compteurY++;
                            if(compteurY == _tabItem.getItem(k).getTailleY()) compteurX++;
                            

                        }
                        jjj++;

                    }
                    if(compteurY != _tabItem.getItem(k).getTailleY()){
                                
                        for(int i=0 ; i<_conteneur.getTailleY() ; i++){

                            if(_conteneur.getXY(iii, i) == _tabItem.getItem(k).getID())

                                _conteneur.setXY(iii, i, -1);

                        }

                    }
                    iii++;
                    jjj=0;
                    compteurY=0;

                }
                
            }
            
        }
        
        // cette boucle permet à la fin du traitement de tous les item de définir le pourcentage de surface remplie du conteneur
        // (plus on s'approche de 100%, plus l'algorithme aura été efficace)
        
        for(int i = 0 ; i < _conteneur.getTailleX() ; i++){
            
            for(int j = 0 ; j < _conteneur.getTailleY() ; j++){
                
                if(_conteneur.getXY(i, j) != -1) remplissage++;
                
            }
            
        }
        
        remplissage = (remplissage/(_conteneur.getTailleX()*_conteneur.getTailleY()))*100;
        
        return remplissage;
        
    }
     
    // algorithme first fit decreasing
    // même principe que le first fit sauf que nous aurons au préalable classé les items du plus grand au plus petit
    
    public static double firstFitDecreasing(TabItem _tabItem, Conteneur _conteneur, int _nbSousConteneur){
        
        double remplissage;
        
        _tabItem.triSelectionDecroissant();
        remplissage = firstFit(_tabItem, _conteneur, _nbSousConteneur);
        
        return remplissage;
        
    }
    
}
