/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfour;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  Main classım - Arkaplandaki ve guideki oyunum üzerinde gereken işlemleri yapıyor
 * @author Celal Can
 */
public class ConnectFour{

    /**
     * Kullanacagım tüm data memberları tanimladım
     */
    private final int width;
    private final int height;  
    private int size;
   
    private final JFrame window;
    private JFrame gamewindow;
    private final JPanel menu;
    private final JLabel header;
    private final JButton hint;
    private final JButton singleplayer;
    private final JButton multiplayer;
    private final JLabel instructions;
    private final JLabel instructions2;
    private final JLabel boardsize;
    private final JButton back;
    private final JTextField textfield;
    
    private final ImageIcon headericon;
    private final ImageIcon hinticon;
    private final ImageIcon butonicon;
    private final ImageIcon butonicon2;
    private final ImageIcon backicon;
    private final ImageIcon instructionsicon1;
    private final ImageIcon instructionsicon2;
    private final ImageIcon drawicon;
    private final ImageIcon player1wonicon;
    private final ImageIcon player2wonicon;
    private final ImageIcon windowicon;
    private final ImageIcon boardsizeicon;
    
    private Cell TABLE[][];
    private int turn;
    private int mouse;

    /**
     *  Default Constructor
     */
    public ConnectFour(){
        turn = 1;
        width=800;
        height=600;
        size=4;
        mouse = 1;
        gamewindow = new JFrame("Connect 4");
        headericon = new ImageIcon("resource/header.png");
        hinticon = new ImageIcon("resource/hint.png");
        butonicon = new ImageIcon("resource/buton.png");
        butonicon2 = new ImageIcon("resource/buton2.png");
        backicon = new ImageIcon("resource/back.png");
        drawicon = new ImageIcon("resource/draw.png");
        instructionsicon1 = new ImageIcon("resource/instructions.png");
        instructionsicon2 = new ImageIcon("resource/instructions2.png");
        windowicon = new ImageIcon("resource/frameicon.png");
        player1wonicon = new ImageIcon("resource/p1wonicon.png");
        player2wonicon = new ImageIcon("resource/p2wonicon.png");
        boardsizeicon = new ImageIcon("resource/boardsize.png");
        window = new JFrame("Connect 4!");
        menu = new JPanel();
        header = new JLabel();
        hint = new JButton();
        singleplayer = new JButton(butonicon);
        multiplayer = new JButton(butonicon2);
        instructions = new JLabel();
        instructions2 = new JLabel();
        back = new JButton();
        boardsize = new JLabel();
        textfield = new JTextField(1);
        
    }
    
     /**
     *  Yeni bir obje oluşturup oyunumun oynanması için gerekli 2 fonksiyon çağırıyorum
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        {
        ConnectFour obj = new ConnectFour();    // Yeni bir obje oluşturuyorum
        obj.windowDisplay();
        obj.menuWindow();
        }
    }

    /**
     *  Frame için gerekli ayarları yapan fonksiyon(location, size, icon, resizeable vs.)
     */
    public void windowDisplay(){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
        window.setIconImage(windowicon.getImage());
        window.setResizable(false);
        window.setLocation(200,100);
        window.setLayout(null); 
    }
    
    /**
     * Oyun ilk açıldığında karşımıza gelen menü ekranı. Bütün label, buton, textfield vs. panelime ekliyorum.Panelimi frameye ekleyip frameyi visible yapıyorum.
     * 
     */
    public void menuWindow(){
        
        // PANEL
        menu.setLayout(null);
        menu.setLocation(0,0);
        menu.setSize(800,600);
        menu.setBackground(Color.cyan);
        window.getContentPane().setBackground(Color.cyan);
        window.add(menu);
        
        // TEXT FIELD
        textfield.setLocation(267,480);
        textfield.setSize(266,20);
        menu.add(textfield);
        
        // TEXT FIELD USTUNDEKI YAZI
        boardsize.setIcon(boardsizeicon);
        boardsize.setLocation(250,370);
        boardsize.setSize(300,150);
        menu.add(boardsize);
        
        // CONNECT FOUR YAZISI
        header.setIcon(headericon);
        header.setLocation(200,20);
        header.setSize(400,100);
        menu.add(header);
        
        // SAĞ ÜSTTEKİ YARDIM BUTONU
        event hintevent = new event();
        hint.setIcon(hinticon);
        hint.setLocation(735,10);
        hint.setSize(50,50);
        hint.setContentAreaFilled(false);
        hint.setBorderPainted(false);
        
        hint.addActionListener(hintevent);
        
        menu.add(hint);

        // 1 PLAYER BUTONU
        singleplayer.setContentAreaFilled(false);
        singleplayer.setLocation(267,180);
        singleplayer.setSize(266,95);
        menu.add(singleplayer);       
        
        event4 singleplayerevent = new event4();
        singleplayer.addActionListener(singleplayerevent);      

        // 2 PLAYER BUTONU
        multiplayer.setLocation(267,300);
        multiplayer.setContentAreaFilled(false);
        multiplayer.setSize(266,95);
        menu.add(multiplayer);      
        
        event5 multiplayerevent = new event5();
        multiplayer.addActionListener(multiplayerevent); 

        // YARDIM METNİ OLAN LABEL
        instructions.setIcon(instructionsicon1);
        instructions.setLocation(30,0);
        instructions.setSize(400,175);
        instructions.setVisible(false);
        instructions2.setIcon(instructionsicon2);
        instructions2.setLocation(100,150);
        instructions2.setSize(600,250);
        instructions2.setVisible(false);
        menu.add(instructions);
        menu.add(instructions2);
        
        // SAĞ ÜSTTEKİ GERİ TUŞU
        back.setIcon(backicon);
        back.setLocation(735,10);
        back.setSize(50,50);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setVisible(false);
        menu.add(back);
        
        event3 backevent = new event3();
        back.addActionListener(backevent);
        
        window.setVisible(true);    // FRAMEYİ GÖRÜNÜR YAPTIM

}
    
    /**
     *  Arkaplanda oynattıgım boardı initialize eden fonksiyon
     */
    public void init_table(){  
    int i, j;
    for(i=0; i<size; i++){
        for(j=0; j<size; j++){
            Cell tmp = new Cell(0,i,j);
            TABLE[i][j] = tmp;  // Cell tipinde tuttugum için cell attım
       }
    }
}

    /**
     *  Arkaplandaki boardım üstünde verdiğim sütuna hamle yapan fonksiyon
     * @param x Hamle yapacagımız sütun
     * @return  Kaçıncı satıra hamle yaptığımı return ediyor.Eğer hamle yapamadıysa -1 return ediyor
     */
    public int playOnTable(int x){
    if(turn==-1){
        return -1;
    }
    for(int i=0; i<size; i++){
        if(TABLE[i][x].getValue()==0){
            if(turn==1){
                TABLE[i][x].setValue(1);
                return size-i-1;
            }
            else if(turn==2){
                TABLE[i][x].setValue(2);    
                return size-i-1;
            }
        }
    }
    return -1;
}

    /**
     *Verdiğim satır ve sütunun oyun tahtası üzerinde olup olmadıgını kontrol ediyorum
     * 
     * @param i Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return  Eğer alanın dışında ise 0 değilse 1 return ediyorum.
     */
    public int is_valid(int i, int j){    
    if(i<0 || i>=size || j<0 || j>=size){
        return 0;
    }
    return 1;
}

    /**
     *  Oyunun bitip bitmediğini kontrol eden fonksiyon
     * @return  Oyun bittiyse 1 bitmediyse 0 return ediyorum
     */
    public int is_game_finished(){         // Oyunun yatay, dikey yada çapraz olarak sonlanýp sonlanmadýðýný kontrol ediyorum. Eðer oyun sonlanmýþ ise hangilerinin bitirdiðini göstermek için lower caseye dönüþtürüyorum
    int i, j, k;
    for(i=0; i<size; i++){
        for(j=0; j<size; j++){
            if(is_valid(i,j)==1 && is_valid(i+1,j)==1 && is_valid(i+2, j)==1 && is_valid(i+3, j)==1){
                if(TABLE[i][j].getValue()==1 && TABLE[i+1][j].getValue()==1 && TABLE[i+2][j].getValue()==1 && TABLE[i+3][j].getValue()==1){
                    return 1;
                }
            }
            if((is_valid(i,j)==1 && is_valid(i,j+1)==1 && is_valid(i, j+2)==1 && is_valid(i, j+3)==1)){
                if(TABLE[i][j].getValue()==1 && TABLE[i][j+1].getValue()==1 && TABLE[i][j+2].getValue()==1 && TABLE[i][j+3].getValue()==1){
                    return 1;
                }
            }
            if((is_valid(i,j)==1 && is_valid(i+1,j+1)==1 && is_valid(i+2, j+2)==1 && is_valid(i+3, j+3)==1)){
                if(TABLE[i][j].getValue()==1 && TABLE[i+1][j+1].getValue()==1 && TABLE[i+2][j+2].getValue()==1 && TABLE[i+3][j+3].getValue()==1){
                    return 1;
                }
            }
            if((is_valid(i,j)==1 && is_valid(i-1,j+1)==1 && is_valid(i-2, j+2)==1 && is_valid(i-3, j+3)==1)){
                if(TABLE[i][j].getValue()==1 && TABLE[i-1][j+1].getValue()==1 && TABLE[i-2][j+2].getValue()==1 && TABLE[i-3][j+3].getValue()==1){
                    return 1;
                }
            }
            if(is_valid(i,j)==1 && is_valid(i+1,j)==1 && is_valid(i+2, j)==1 && is_valid(i+3, j)==1){
                if(TABLE[i][j].getValue()==2 && TABLE[i+1][j].getValue()==2 && TABLE[i+2][j].getValue()==2 && TABLE[i+3][j].getValue()==2){
                    return 1;
                }
            }
            if((is_valid(i,j)==1 && is_valid(i,j+1)==1 && is_valid(i, j+2)==1 && is_valid(i, j+3)==1)){
                if(TABLE[i][j].getValue()==2 && TABLE[i][j+1].getValue()==2 && TABLE[i][j+2].getValue()==2 && TABLE[i][j+3].getValue()==2){
                    return 1;
                }
            }
            if((is_valid(i,j)==1 && is_valid(i+1,j+1)==1 && is_valid(i+2, j+2)==1 && is_valid(i+3, j+3)==1)){
                if(TABLE[i][j].getValue()==2 && TABLE[i+1][j+1].getValue()==2 && TABLE[i+2][j+2].getValue()==2 && TABLE[i+3][j+3].getValue()==2){
                    return 1;
                }
            }
            if((is_valid(i,j)==1 && is_valid(i-1,j+1)==1 && is_valid(i-2, j+2)==1 && is_valid(i-3, j+3)==1)){
                if(TABLE[i][j].getValue()==2 && TABLE[i-1][j+1].getValue()==2 && TABLE[i-2][j+2].getValue()==2 && TABLE[i-3][j+3].getValue()==2){
                    return 1;
                }
            }
        }
    }
    return 0;
}

    /**
     *  Oyun alanının dolup dolmadıgını kontrol eden metod
     * @return  Oyun berabere bittiyse 1 bitmediyse 0 return eden fonksiyon
     */
    public int is_game_draw(){
    int i, j;
    for(i=0; i<size; i++){
        for(j=0; j<size; j++){
            if(TABLE[i][j].getValue()==0){
                return 0;
            }
        }
    }
    return 1;
}

    /**
     *  Bilgisayarın herhangi bir hamleyle 4 lü yapıp yapamayacağını kontrol eden metod
     * @param i Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return  Best moveu bulabilmesi için sayı return ediliyor
     */
    public int computer_4_in_a_row(int i, int j){       
    if(is_valid(i,j+1)==1 && is_valid(i,j+2)==1 && is_valid(i, j+3)==1){
        if(TABLE[i][j].getValue()==2 && TABLE[i][j].getValue()==2 && TABLE[i][j].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i-1,j)==1 && is_valid(i-2,j)==1 && is_valid(i-3, j)==1){
        if(TABLE[i-1][j].getValue()==2 && TABLE[i-2][j].getValue()==2 && TABLE[i-3][j].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i+2,j)==1 && is_valid(i+3, j)==1){
        if(TABLE[i+1][j].getValue()==2 && TABLE[i+2][j].getValue()==2 && TABLE[i+3][j].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i+2,j)==1 && is_valid(i-1, j)==1){
        if(TABLE[i+1][j].getValue()==2 && TABLE[i+2][j].getValue()==2 && TABLE[i-1][j].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i-2,j)==1 && is_valid(i-3, j)==1){
        if(TABLE[i+1][j].getValue()==2 && TABLE[i-2][j].getValue()==2 && TABLE[i-3][j].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i+2,j+2)==1 && is_valid(i+3, j+3)==1){
        if(TABLE[i+1][j+1].getValue()==2 && TABLE[i+2][j+2].getValue()==2 && TABLE[i+3][j+3].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i+2,j+2)==1 && is_valid(i-1, j-1)==1){
        if(TABLE[i+1][j+1].getValue()==2 && TABLE[i+2][j+2].getValue()==2 && TABLE[i-1][j-1].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i-1,j-1)==1 && is_valid(i-2, j-2)==1){
        if(TABLE[i+1][j+1].getValue()==2 && TABLE[i-1][j-1].getValue()==2 && TABLE[i-2][j-2].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i-1,j-1)==1 && is_valid(i-2,j-2)==1 && is_valid(i-3, j-3)==1){
        if(TABLE[i-1][j-1].getValue()==2 && TABLE[i-2][j-2].getValue()==2 && TABLE[i-3][j-3].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i+2,j-2)==1 && is_valid(i+3, j-3)==1){
        if(TABLE[i+1][j-1].getValue()==2 && TABLE[i+2][j-2].getValue()==2 && TABLE[i+3][j-3].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i+2,j-2)==1 && is_valid(i-1, j+1)==1){
        if(TABLE[i+1][j-1].getValue()==2 && TABLE[i+2][j-2].getValue()==2 && TABLE[i-1][j+1].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i-1,j+1)==1 && is_valid(i-2, j+2)==1){
        if(TABLE[i+1][j-1].getValue()==2 && TABLE[i-1][j+1].getValue()==2 && TABLE[i-2][j+2].getValue()==2){
            return 6;
        }
    }
    if(is_valid(i-1,j+1)==1 && is_valid(i-2,j+2)==1 && is_valid(i-3, j+3)==1){
        if(TABLE[i-1][j+1].getValue()==2 && TABLE[i-2][j+2].getValue()==2 && TABLE[i-3][j+3].getValue()==2){
            return 6;
        }
    }
    return 0;
}

    /**
     *  Oyuncunun herhangi bir hamleyle 4 lü yapıp yapamayacağını kontrol eden metod
     * @param i Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return Best moveu bulabilmesi için sayı return ediliyor
     */
    public int player_4_in_a_row(int i, int j){       
    if(is_valid(i,j+1)==1 && is_valid(i,j+2)==1 && is_valid(i, j+3)==1){    // Dikey kontrol
        if(TABLE[i][j].getValue()==1 && TABLE[i][j].getValue()==1 && TABLE[i][j].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i-1,j)==1 && is_valid(i-2,j)==1 && is_valid(i-3, j)==1){
        if(TABLE[i-1][j].getValue()==1 && TABLE[i-2][j].getValue()==1 && TABLE[i-3][j].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i+2,j)==1 && is_valid(i+3, j)==1){
        if(TABLE[i+1][j].getValue()==1 && TABLE[i+2][j].getValue()==1 && TABLE[i+3][j].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i+2,j)==1 && is_valid(i-1, j)==1){
        if(TABLE[i+1][j].getValue()==1 && TABLE[i+2][j].getValue()==1 && TABLE[i-1][j].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i-2,j)==1 && is_valid(i-3, j)==1){
        if(TABLE[i+1][j].getValue()==1 && TABLE[i-2][j].getValue()==1 && TABLE[i-3][j].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i+2,j+2)==1 && is_valid(i+3, j+3)==1){
        if(TABLE[i+1][j+1].getValue()==1 && TABLE[i+2][j+2].getValue()==1 && TABLE[i+3][j+3].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i+2,j+2)==1 && is_valid(i-1, j-1)==1){
        if(TABLE[i+1][j+1].getValue()==1 && TABLE[i+2][j+2].getValue()==1 && TABLE[i-1][j-1].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i-1,j-1)==1 && is_valid(i-2, j-2)==1){
        if(TABLE[i+1][j+1].getValue()==1 && TABLE[i-1][j-1].getValue()==1 && TABLE[i-2][j-2].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i-1,j-1)==1 && is_valid(i-2,j-2)==1 && is_valid(i-3, j-3)==1){
        if(TABLE[i-1][j-1].getValue()==1 && TABLE[i-2][j-2].getValue()==1 && TABLE[i-3][j-3].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i+2,j-2)==1 && is_valid(i+3, j-3)==1){
        if(TABLE[i+1][j-1].getValue()==1 && TABLE[i+2][j-2].getValue()==1 && TABLE[i+3][j-3].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i+2,j-2)==1 && is_valid(i-1, j+1)==1){
        if(TABLE[i+1][j-1].getValue()==1 && TABLE[i+2][j-2].getValue()==1 && TABLE[i-1][j+1].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i-1,j+1)==1 && is_valid(i-2, j+2)==1){
        if(TABLE[i+1][j-1].getValue()==1 && TABLE[i-1][j+1].getValue()==1 && TABLE[i-2][j+2].getValue()==1){
            return 5;
        }
    }
    if(is_valid(i-1,j+1)==1 && is_valid(i-2,j+2)==1 && is_valid(i-3, j+3)==1){
        if(TABLE[i-1][j+1].getValue()==1 && TABLE[i-2][j+2].getValue()==1 && TABLE[i-3][j+3].getValue()==1){
            return 5;
        }
    }
    return 0;
}

    /**
     *  Bilgisayarın herhangi bir hamleyle 3 lü yapıp yapamayacağını kontrol eden metod
     * @param i Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return Best moveu bulabilmesi için sayı return ediliyor
     */
    public int computer_3_in_a_row(int i, int j){       
    if(is_valid(i,j+1)==1 && is_valid(i,j+2)==1){    // Dikey kontrol
        if(TABLE[i][j].getValue()==2 && TABLE[i][j].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i-1,j)==1 && is_valid(i-2,j)==1){
        if(TABLE[i-1][j].getValue()==2 && TABLE[i-2][j].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i+2,j)==1){
        if(TABLE[i+1][j].getValue()==2 && TABLE[i+2][j].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i+1,j)==1  && is_valid(i-1, j)==1){
        if(TABLE[i+1][j].getValue()==2 && TABLE[i-1][j].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i+2,j+2)==1){
        if(TABLE[i+1][j+1].getValue()==2 && TABLE[i+2][j+2].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i+1,j+1)==1  && is_valid(i-1, j-1)==1){
        if(TABLE[i+1][j+1].getValue()==2 && TABLE[i-1][j-1].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i-1,j-1)==1 && is_valid(i-2, j-2)==1){
        if(TABLE[i-1][j-1].getValue()==2 && TABLE[i-2][j-2].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i+2,j-2)==1){
        if(TABLE[i+1][j-1].getValue()==2 && TABLE[i+2][j-2].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i-1, j+1)==1){
        if(TABLE[i+1][j-1].getValue()==2 && TABLE[i-1][j+1].getValue()==2){
            return 4;
        }
    }
    if(is_valid(i-1,j+1)==1 && is_valid(i-2,j+2)==1){
        if(TABLE[i-1][j+1].getValue()==2 && TABLE[i-2][j+2].getValue()==2){
            return 4;
        }
    }
    return 0;
}

    /**
     * Oyuncunun herhangi bir hamleyle 3 lü yapıp yapamayacağını kontrol eden metod
     * @param i  Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return Best moveu bulabilmesi için sayı return ediliyor
     */
    public int player_3_in_a_row(int i, int j){       
    if(is_valid(i,j+1)==1 && is_valid(i,j+2)==1){    // Dikey kontrol
        if(TABLE[i][j].getValue()==1 && TABLE[i][j].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i-1,j)==1 && is_valid(i-2,j)==1){
        if(TABLE[i-1][j].getValue()==1 && TABLE[i-2][j].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i+1,j)==1 && is_valid(i+2,j)==1){
        if(TABLE[i+1][j].getValue()==1 && TABLE[i+2][j].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i+1,j)==1  && is_valid(i-1, j)==1){
        if(TABLE[i+1][j].getValue()==1 && TABLE[i-1][j].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i+1,j+1)==1 && is_valid(i+2,j+2)==1){
        if(TABLE[i+1][j+1].getValue()==1 && TABLE[i+2][j+2].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i+1,j+1)==1  && is_valid(i-1, j-1)==1){
        if(TABLE[i+1][j+1].getValue()==1 && TABLE[i-1][j-1].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i-1,j-1)==1 && is_valid(i-2, j-2)==1){
        if(TABLE[i-1][j-1].getValue()==1 && TABLE[i-2][j-2].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i+2,j-2)==1){
        if(TABLE[i+1][j-1].getValue()==1 && TABLE[i+2][j-2].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i+1,j-1)==1 && is_valid(i-1, j+1)==1){
        if(TABLE[i+1][j-1].getValue()==1 && TABLE[i-1][j+1].getValue()==1){
            return 3;
        }
    }
    if(is_valid(i-1,j+1)==1 && is_valid(i-2,j+2)==1){
        if(TABLE[i-1][j+1].getValue()==1 && TABLE[i-2][j+2].getValue()==1){
            return 3;
        }
    }
    return 0;
}

    /**
     * Bilgisayarın herhangi bir hamleyle 2li yapıp yapamayacağını kontrol eden metod
     * @param i  Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return Best moveu bulabilmesi için sayı return ediliyor
     */
    public int computer_2_in_a_row(int i, int j){       
    if(is_valid(i,j+1)==1){    // Dikey kontrol
        if(TABLE[i][j].getValue()==2){
            return 2;
        }
    }
    if(is_valid(i-1,j)==1){
        if(TABLE[i-1][j].getValue()==2){
            return 2;
        }
    }
    if(is_valid(i+1,j)==1){
        if(TABLE[i+1][j].getValue()==2){
            return 2;
        }
    }
    if(is_valid(i+1,j+1)==1){
        if(TABLE[i+1][j+1].getValue()==2){
            return 2;
        }
    }
    if(is_valid(i-1,j-1)==1){
        if(TABLE[i-1][j-1].getValue()==2){
            return 2;
        }
    }
    if(is_valid(i+1,j-1)==1){
        if(TABLE[i+1][j-1].getValue()==2){
            return 2;
        }
    }
    if(is_valid(i-1,j+1)==1){
        if(TABLE[i-1][j+1].getValue()==2 ){
            return 2;
        }
    }
    return 0;
}

    /**
     * Oyuncunun herhangi bir hamleyle 2li yapıp yapamayacağını kontrol eden metod
     * @param i  Kontrol edilecek satır değeri
     * @param j Kontrol edilecek sütun değeri
     * @return Best moveu bulabilmesi için sayı return ediliyor
     */
    public int player_2_in_a_row(int i, int j){       
    if(is_valid(i,j+1)==1){    // Dikey kontrol
        if(TABLE[i][j].getValue()==1){
            return 1;
        }
    }
    if(is_valid(i-1,j)==1){
        if(TABLE[i-1][j].getValue()==1){
            return 1;
        }
    }
    if(is_valid(i+1,j)==1){
        if(TABLE[i+1][j].getValue()==1){
            return 1;
        }
    }
    if(is_valid(i+1,j+1)==1){
        if(TABLE[i+1][j+1].getValue()==1){
            return 1;
        }
    }
    if(is_valid(i-1,j-1)==1){
        if(TABLE[i-1][j-1].getValue()==1){
            return 1;
        }
    }
    if(is_valid(i+1,j-1)==1){
        if(TABLE[i+1][j-1].getValue()==1){
            return 1;
        }
    }
    if(is_valid(i-1,j+1)==1){
        if(TABLE[i-1][j+1].getValue()==1){
            return 1;
        }
    }
    return 0;
}

    /**
     *  Kontrol edilen değerlere göre yapılabilecek en iyi hamleyi bulup o hamleyi yapan metod.Ayrıca gui olarakta hamleyi board üstüne yapıyor
     */
    public void computer_move(){ 
      int i ,j, temp_i=0, temp_j=0, best_move=0;
      for(j=0; j<size; j++){
         for(i=0; i<size; i++){
            if(TABLE[i][j].getValue()==0){
               if(computer_4_in_a_row(i,j) > best_move){
                    best_move = computer_4_in_a_row(i,j);
                    temp_i=i;
                    temp_j=j;
               }
               if(player_4_in_a_row(i, j)>best_move){
                    best_move = player_4_in_a_row(i, j);
                    temp_i=i;
                    temp_j=j;
               }
               if(computer_3_in_a_row(i, j)>best_move){
                    best_move = computer_3_in_a_row(i, j);
                    temp_i=i;
                    temp_j=j;
               }
               if(player_3_in_a_row(i, j)>best_move){
                    best_move = player_3_in_a_row(i, j);
                    temp_i=i;
                    temp_j=j;
               }
               if(computer_2_in_a_row(i, j)>best_move){
                    best_move = computer_2_in_a_row(i, j);
                    temp_i=i;
                    temp_j=j;
               }
               if(player_2_in_a_row(i, j)>best_move){
                    player_2_in_a_row(i, j);
                    temp_i=i;
                    temp_j=j;
               }
                break;
            }
        }
    } 

      if(best_move>0){  // Deneme diye bir label oluşturup bunu frameye yerleştiriyorum bu sayede nereye hamle yaptiği görüyorum
        TABLE[temp_i][temp_j].setValue(2);
        JLabel deneme = new JLabel();
        deneme.setIcon(new ImageIcon("resource/boardr.png"));
        turn = 1;
        deneme.setLocation((temp_j)*100,(size-temp_i-1)*75);
        deneme.setSize(100,75);
        gamewindow.add(deneme);
        gamewindow.repaint();       // Ekranın güncellenmesi için repaint metodunu çağırdım 
      }
      else if(TABLE[0][size/2].getValue()==0){     // Eðer yukardaki koþullara uygun bir hamle bulamadýysa (Sadece Baþlangýç hamlesi için geçerli) avantajlý konuma geçebilmek için oyun tahtasýnýn ortasýna ilk hamleyi yapýyor
        TABLE[0][size/2].setValue(2);
        JLabel deneme = new JLabel();
        deneme.setIcon(new ImageIcon("resource/boardr.png"));
        turn = 1;
        deneme.setLocation((size/2)*100,(size-1)*75);
        deneme.setSize(100,75);
        gamewindow.add(deneme);
        gamewindow.repaint();       // Ekranın güncellenmesi için repaint metodunu çağırdım 
      }
      else if(TABLE[0][size/2].getValue()==1){
        TABLE[0][size/2-1].setValue(2);
        JLabel deneme = new JLabel();
        deneme.setIcon(new ImageIcon("resource/boardr.png"));
        turn = 1;
        deneme.setLocation((size/2-1)*100,(size-1)*75);
        deneme.setSize(100,75);
        gamewindow.add(deneme);
        gamewindow.repaint();        // Ekranın güncellenmesi için repaint metodunu çağırdım 
      }
    }

    /**
     *  Oyun sonunda Dialog box çıkıp oyunu tekrar oynamak isteyip istemediğimizi soruyor
     */
    public void rematch(){
    Object[] answers = {"Play Again", "Exit"};
    int choice = JOptionPane.showOptionDialog(gamewindow, "Do You Want To Play Again?", "Information",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, hinticon, answers, answers[1]);
    if(choice == JOptionPane.YES_OPTION){
        window.setVisible(true);
        gamewindow.dispose();   
    }
    else{
        System.exit(0);  // Eğer tekrar oynamak istemiyorsa oyunu kapatıyorum
    }
}
    
    /**
     *  Player vs computer oyunu başlatan metodum. İçerisinde mouselistener barındırıyor bu sayede herhangi bir yere bastıgımızda bastıgımız locationu alıp oraya hamleyi yapıyor ve oyunun bitip bitmediğini kontrol ediyor
     */
    public void playsingle(){
        TABLE=new Cell[size][size];
        init_table();
        for(int i=0; i<size; i++){  // Boardımı oluşturan döngüler
                for(int j=0; j<size; j++){
                    JLabel deneme = new JLabel();
                    deneme.setIcon(new ImageIcon("resource/board.png"));
                    deneme.setLocation(i*100,j*75);
                    deneme.setSize(100,75);
                    gamewindow.add(deneme);
                }
        }
        gamewindow.addMouseListener(new MouseAdapter(){
            int mousepos, temp, draw, finish;
            @Override
            public void mousePressed(MouseEvent e){
                mousepos = e.getX();
                if(mouse==1){   // Eğer oyun bitmişse mouse değişkenini 0 yapıyorum bu sayede bitmiş oyun üzerinde işlem yapamıyor
                for(int i=0; i<size; i++){
                    if(mousepos<(i+1)*(100) && mousepos>i*(100)){
                            temp = playOnTable(i);
                            draw = is_game_draw();
                            finish = is_game_finished();    
                            JLabel deneme = new JLabel();
                            deneme.setIcon(new ImageIcon("resource/boardb.png"));
                            turn = 2;
                            deneme.setLocation(i*100,temp*75);
                            deneme.setSize(100,75);
                            gamewindow.add(deneme);
                            gamewindow.repaint();
                            if(temp==-1){   // Eğer hamle yapabildiysek hamle sırasını karşıya veren if
                                if(turn==1){
                                    turn=2;
                                }
                                else if(turn==2){
                                    turn=1;
                                }
                            }
                            if(finish==1){
                                mouse = 0;
                                JOptionPane.showMessageDialog(gamewindow, "Player 1 Won!", "Information",JOptionPane.INFORMATION_MESSAGE, player1wonicon);                                
                                rematch();
                            }
                            else if(draw==1){
                                mouse = 0;
                                JOptionPane.showMessageDialog(gamewindow, "Game Draw!", "Information",JOptionPane.INFORMATION_MESSAGE, drawicon);
                                rematch();
                            }
                            if(temp!=-1 && mouse==1){
                                computer_move();    // Bilgisayar hamlesini yapan metod
                                draw = is_game_draw();
                                finish = is_game_finished();    
                                if(finish==1){
                                    mouse = 0;
                                    JOptionPane.showMessageDialog(gamewindow, "Computer Won!", "Information",JOptionPane.INFORMATION_MESSAGE, player2wonicon);
                                    rematch();
                                }   
                                else if(draw==1){
                                    mouse = 0;
                                    JOptionPane.showMessageDialog(gamewindow, "Game Draw!", "Information",JOptionPane.INFORMATION_MESSAGE, drawicon);
                                    rematch();
                                }      
                            }
                        }
                    }
                }
            }
        });       
    }

    /**
     *  Menü ekranındaki textfielddan size değerini alan metodum.Eğer geçersiz bir değer girilirse 4 olarak set ediliyor
     */
    public void getSize(){  
    String input = textfield.getText();
    String numb = "";
    String temp = "";
    for(int i=0; i<input.length(); i++){    // Tek tek karakterlere bakıyorum ve eğer numara girilmişse tostring ile başka bir stringte topluyorum
        if(input.charAt(i) > '0' && input.charAt(i) < '9'){
            temp =  "" + input.charAt(i);
            numb = numb + temp;  
        }
    }
    if(numb!=""){   // Eğer kullanıcıdan herhangi bir sayı alabildiysem integera parse edip sizeıma atıyorum
        size = Integer.parseInt(numb);
    }
    if(size<4){ // Size 4ten küçük olamayacagı için 4 e set ediyorum
        size=4;
    }   
}

    /**
     *  Player vs player oyun başlatan metodum.Singleplayerdaki gibi mouselistener barındırıyor ve mouse tıklanınca gerekli hamleleri yapıyor
     */
    public void playmulti(){
        TABLE=new Cell[size][size];
        init_table();
        for(int i=0; i<size; i++){      // Boardımı çizen for döngüsü
                for(int j=0; j<size; j++){
                    JLabel deneme = new JLabel();
                    deneme.setIcon(new ImageIcon("resource/board.png"));
                    deneme.setLocation(i*100,j*75);
                    deneme.setSize(100,75);
                    gamewindow.add(deneme);
                }

        } 
        gamewindow.addMouseListener(new MouseAdapter(){
            int mousepos, temp, draw, finish;
            @Override
            public void mousePressed(MouseEvent e){
                mousepos = e.getX();
                if(mouse==1){
                for(int i=0; i<size; i++){
                    if(mousepos<(i+1)*(100) && mousepos>i*(100)){
                        temp = playOnTable(i);
                        draw = is_game_draw();
                        finish = is_game_finished();    
                        JLabel deneme = new JLabel();
                        if(turn==1){        // Eğer turn 1 deyse mavi labeli load edip turn 2 yapıyorum değilse tam tersini
                            deneme.setIcon(new ImageIcon("resource/boardb.png"));
                            turn = 2;
                        }
                        else if(turn==2){
                            deneme.setIcon(new ImageIcon("resource/boardr.png"));
                            turn = 1;
                        }
                        deneme.setLocation(i*100,temp*75);
                        deneme.setSize(100,75);
                        gamewindow.add(deneme);
                        gamewindow.repaint();   // Framem güncellesin diye repaint yapıyorum
                        if(temp==-1){
                            if(turn==1){
                                turn=2;
                            }
                            else if(turn==2){
                                turn=1;
                            }
                        }
                        if(finish==1){
                            if(turn==2){
                                mouse = 0;
                                JOptionPane.showMessageDialog(gamewindow, "Player 1 Won!", "Information",JOptionPane.INFORMATION_MESSAGE, player1wonicon);
                                rematch();
                            }
                            else if(turn==1){
                                mouse = 0;
                                JOptionPane.showMessageDialog(gamewindow, "Player 2 Won!", "Information",JOptionPane.INFORMATION_MESSAGE, player2wonicon);
                                rematch();
                            }
                        }
                        else if(draw==1){
                            mouse = 0;
                            JOptionPane.showMessageDialog(gamewindow, "Game Draw!", "Information",JOptionPane.INFORMATION_MESSAGE, drawicon);
                            rematch();
                        }
                    }
                }
                }
            }
        });
    }
     
    /**
     *  Menüdeki sağ üst köşedeki yardım butonuna tıkladığımızda çağırılacak event. Panelim üzerindeki herşeyi görünmez yapıyor ve yeni açılan ekrandakileri görünür yapıyor
     */
    public class event implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent hintevent){
            menu.setVisible(true);
            boardsize.setVisible(false);
            textfield.setVisible(false);
            header.setVisible(false);
            singleplayer.setVisible(false);
            multiplayer.setVisible(false);
            hint.setVisible(false);
            instructions.setVisible(true);
            instructions2.setVisible(true);
            back.setVisible(true);
        }

    }
    
    /**
     *  Yardım ekranını açtığımızda geri tuşuna eklediğim event. Buna basarak tekrardan menüye dönüyoruz. Bütün görünmez yaptıklarımız görünür, görünür yaptıklarımız görünmez oluyor.
     */
    public class event3 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent hintevent){
            header.setVisible(true );
            boardsize.setVisible(true);
            textfield.setVisible(true);
            singleplayer.setVisible(true );
            multiplayer.setVisible(true );
            hint.setVisible(true);           
            instructions.setVisible(false);
            instructions2.setVisible(false);
            back.setVisible(false);
        }

    }
    
    /**
     *  Player vs computer butonuna tıkladıgında çağırılan eventim. gamewindow framemdeki ayarları yapıp playsingle() fonksiyonunu çağırıyorum
     */
    public class event4 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent singleplayerevent){
        getSize();
        window.setVisible(false);
        mouse = 1;
        gamewindow = new JFrame("Connect 4!");
        gamewindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamewindow.setSize(size*100, size*75+30);
        gamewindow.setResizable(false);
        gamewindow.setLocation(200,100);
        gamewindow.setLayout(null);    
        gamewindow.setVisible(true);
        gamewindow.setIconImage(windowicon.getImage());
        playsingle();
        }
    }
    
    /**
     *  Player vs player butonuna bastıgımda  çağırılan eventim. Singleplayer ile aynı işi yapıyor sadece en sonda multi fonksiyonunu çağırıyor
     */
    public class event5 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent multiplayerevent){
        getSize();
        window.setVisible(false);
        mouse = 1;
        gamewindow = new JFrame("Connect 4!");
        gamewindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamewindow.setSize(size*100, size*75+30);
        gamewindow.setResizable(false);
        gamewindow.setLocation(200,100);
        gamewindow.setLayout(null);    
        gamewindow.setVisible(true);
        gamewindow.setIconImage(windowicon.getImage());
        playmulti();
        }
    }
}
