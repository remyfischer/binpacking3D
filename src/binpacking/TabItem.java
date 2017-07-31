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

// classe TabItem permettant de contenir des Item et de faire des opérations sur ce tableau


public class TabItem {
    
    private int nbItem;
    private ArrayList<Item> tabItem;
    
    
    public TabItem(){
        
        nbItem = 0;
        tabItem = new ArrayList<Item>();
        
    }
    
    public void AjoutItem(Item _item){
        
        tabItem.add(_item);
        nbItem++;
        
    }
    
    public Item getItem(int i){
        
        return tabItem.get(i);
        
    }

    public void SupprItem(int indice){
        
        if (tabItem.get(indice) != null ){
            
            tabItem.remove(indice);
            nbItem--;           
            
        }
        
    }
    
    // fonction permettant d'ajouter des items dans le tableau
    
    public void initTabItem(){
        
        Scanner sc = new Scanner(System.in);
        int nbItemAAjouter;
        int tailleXItem;
        int tailleYItem;
        int tailleZItem;
        
        System.out.println("Combien d'item souhaitez vous ajouter ?");
        nbItemAAjouter = sc.nextInt();
        
        for (int i = 0 ; i < nbItemAAjouter ; i++ ){
            
            System.out.println("Saisissez une taille X pour votre item");
            tailleXItem = sc.nextInt();
            System.out.println("Saisissez une taille Y pour votre item");
            tailleYItem = sc.nextInt();
            System.out.println("Saisissez une taille Z pour votre item");
            tailleZItem = sc.nextInt();
            tabItem.add(new Item(tailleXItem, tailleYItem, tailleZItem));
            nbItem++;
        }
        
    }
    
    public void afficherContenu(){
        
        System.out.println("-------------------------------");
        
        for(int i = 0 ; i < nbItem ; i++){
            
            System.out.println("Taille item " + i + " : " + (tabItem.get(i)).getTailleX()+" ; "+(tabItem.get(i)).getTailleY()+" ; "+(tabItem.get(i).getTailleZ()));
        
        }
        
        System.out.println("-------------------------------");
        
    }
            
            
         
    
    public int getNbItem(){
        
        return nbItem;
        
    }
    
    // fonction permettant de retourner la plus grande dimension X de tous les items présents dans le tableau
    
    public int getPlusGrandeDimensionX(){
        
        int maxX = -1;
        
        for(int i=0 ; i < nbItem ; i++){
            
            if(tabItem.get(i).getTailleX() > maxX ){
                
                maxX = tabItem.get(i).getTailleX();
                
            }
            
        }
        
        return maxX;
        
    }
    
     // fonction permettant de retourner la plus grande dimension Y de tous les items présents dans le tableau
    
    public int getPlusGrandeDimensionY(){
        
        int maxY = -1;
        
        for(int i=0 ; i < nbItem ; i++){
            
            if(tabItem.get(i).getTailleY() > maxY ){
                
                maxY = tabItem.get(i).getTailleY();
                
            }
            
        }
        
        return maxY;
        
    }
    
    public int getPlusGrandeDimensionZ(){
        
        int maxZ = -1;
        
        for(int i=0 ; i < nbItem ; i++){
            
            if(tabItem.get(i).getTailleZ() > maxZ){
                
                maxZ = tabItem.get(i).getTailleZ();
                
            }
            
        }
        
        return maxZ;
        
    }
    
    // fonction permettant de remplir avec des items aléatoires le tableau d'item
    public void randomize(){
        
        Scanner sc = new Scanner(System.in);
        int nbItemAAjouter;
        int xMax, yMax, zMax;
        
        System.out.println("Combien d'item voulez vous créer ?");
        nbItemAAjouter = sc.nextInt();
        System.out.println("Donnez la dimension x maximale");
        xMax = sc.nextInt();
        System.out.println("Donnez la dimension y maximale");
        yMax = sc.nextInt();
        System.out.println("Donnez la dimension z maximale");
        zMax = sc.nextInt();
        
        int x,y,z;
        Item[] item = new Item[nbItemAAjouter];
        
        for(int i = 0 ; i < nbItemAAjouter ; i++){
            
            x = (int)((Math.random() * xMax)+1);
            y = (int)((Math.random() * yMax)+1);
            z = (int)((Math.random() * zMax)+1);
            
            item[i] = new Item(x, y, z);
            
            this.AjoutItem(item[i]);
            
        }
        
        this.afficherContenu();
        
        
        
    }
    
    public void randomize(int _nbItemAAjouter, int _xMax, int _yMax, int _zMax){
        
        int x,y,z;
        Item[] item = new Item[_nbItemAAjouter];
        
        for(int i = 0 ; i < _nbItemAAjouter ; i++){
            
            x = (int)((Math.random() * _xMax)+1);
            y = (int)((Math.random() * _yMax)+1);
            z = (int)((Math.random() * _zMax)+1);
            
            item[i] = new Item(x, y, z);
            
            this.AjoutItem(item[i]);
            
        }
        
        this.afficherContenu();       
        
        
    }
    
    // fonction permettant de trier les items par ordre décroissant de surface
    // on utilise la méthode du tri par selection
    
    public void triSelectionDecroissant(){
        
        int i, j, k, max;
        Item _max;
        i=0;
        while (i < this.getNbItem() - 1 ){
            
            k = i;
            max = this.getItem(k).getTailleX() * this.getItem(k).getTailleY() * this.getItem(k).getTailleZ();
            _max = this.getItem(k);
            j=i+1;
            while(j <= this.getNbItem() -1){
                
                if(this.getItem(j).getTailleX() * this.getItem(j).getTailleY() * this.getItem(j).getTailleZ() > max){
                    
                    k = j;
                    max = this.getItem(k).getTailleX() * this.getItem(k).getTailleY() * this.getItem(k).getTailleZ();
                    _max = this.getItem(k);
                    
                }
                j++;
                
            }
            tabItem.set(k, this.getItem(i));
            tabItem.set(i, _max);
            i++;
            
        }
        
    }
    
    
    
}
