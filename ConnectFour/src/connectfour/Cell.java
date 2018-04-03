/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfour;

/**
 *  Bir cellin içindeki değeri ve pozisyonunu tutan class
 * @author Celal Can
 */
public class Cell {
    private int value;
    private int posx;
    private int posy;

    /**
     *  Default constructor
     */
    public Cell(){
        posx=0;
        posy=0;
        value=0;
    }

    /**
     *
     * @param v Atanacak value değerim
     * @param x Atanacak x pozisyonu değerim
     * @param y Atanacak y pozisyonu değerim
     */
    public Cell(int v, int x, int y){
        value=v;
        posx=x;
        posy=y;
    }

    /**
     *  X pozisyonunu return eden getter ım
     * @return  X pozisyonunu return ediyorum
     */
    public int getX(){
        return posx;
    }

    /**
     *  Y pozisyonunu return eden getter ım
     * @return  Y pozisyonunu return ediyorum
     */
    public int getY(){
        return posy;
    }

    /**
     *  Value değerini return eden getter ım
     * @return  O cell içindeki value değerimi return ediyorum
     */
    public int getValue(){
        return value;
    }

    /**
     *  Value değerini set eden setter
     * @param x Set edilecek value
     */
    public void setValue(int x){
        value=x;
    }

    /**
     * X pozisyonunu set eden setterım 
     * @param x Set edilecek x pozisyonu
     */
    public void setX(int x){
        posx=x;
    }

    /**
     *  Y pozisyonu set eden setterım
     * @param x Set edilecek y pozisyonu
     */
    public void setY(int x){
        posy=x;
    }
}
