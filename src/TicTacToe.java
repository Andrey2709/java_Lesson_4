import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class TicTacToe {

    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;
    private static String[][] map;
    private static final String HUM_STEP = "X";
    private static final String PC_STEP = "O";
    private static final String PROBEL = "*";
    private static int gooHeight;
    private static int gooWidth;
    private static final BufferedReader redaer = new BufferedReader(new InputStreamReader(System.in));
    private static final int WIN_LEN = 3;

    public static void main(String[] args) throws IOException {

        hello();
        mapBoard(WIDTH, HEIGHT);
        draw(map);
        while (true) {

            humanGo();
            if (checkWin(HUM_STEP)) {
                System.out.println("Вы выйграли");
                break;
            }

            if (haveAprobel(map)) {
                System.out.println("Ничья");
                break;
            }
            draw(map);

            pcGo(map);
            if (checkWin(PC_STEP)) {
                System.out.println("Выйграл МегаМозг228");
                break;
            }
            if (haveAprobel(map)) {
                System.out.println("Ничья");
                break;
            }
            draw(map);
        }
    }

    private static void hello() throws IOException {
        System.out.println("Приветствую вас на арене \"Крестик и Нолик\"");
        System.out.println("Как вас зовут?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = reader.readLine();
            if (name.equals("")) {
                System.out.println("Такого имени быть не может. \n Ты будешь Вася " + 0 + (int) (Math.random() * 1000));
                System.out.println("Сегодня мы узнаем кто у мнее. Ты ли МегаМозг228 \n ДА НАЧНЕТСЯ БИТВА!!!");
            } else
                System.out.println("Чтож " + name + " Сегодня мы узнаем кто у мнее. Ты ли МегаМозг228 \n ДА НАЧНЕТСЯ БИТВА!!!");
        } catch (IOException e) {
        }


    }// Приветствует и устанавливает имена противников

    private static String[][] mapBoard(int HEIGHT, int WIDTH) {
        map = new String[HEIGHT][WIDTH];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = PROBEL;
                if (i == 0) map[i][j] = String.valueOf(j);
                else if (j == 0) map[i][j] = String.valueOf(i);
            }

        }
        return map;

    }//Создает карту

    private static void draw(String[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }//Отрисовывает карту


    private static void humanGo() throws IOException {

        System.out.println("Ваш ход. Введите координаты");
        boolean n = true;
        while (n) {
            System.out.println("Строка: ");
            gooHeight = Integer.parseInt(redaer.readLine());
            System.out.println("Столбец: ");
            gooWidth = Integer.parseInt(redaer.readLine());
            if (gooHeight < 1 || gooHeight >= map.length || gooWidth < 1 || gooWidth >= map.length||map[gooHeight][gooWidth]==PC_STEP) {
                System.out.println("Не верное значение");
                n = true;

            } else {
                map[gooHeight][gooWidth] = HUM_STEP;
                n = false;
            }

        }
    }//Ход человека

    private static boolean checkWin(String s){
        for (int i = 1; i < map.length -1; i++) {
            for (int j = 1; j <map.length -1 ; j++) {
                if(checkLIne(i,j,1,0,WIN_LEN, s)) return true;
                if(checkLIne(i,j,1,1,WIN_LEN, s)) return true;
                if(checkLIne(i,j,0,1,WIN_LEN, s)) return true;
                if(checkLIne(i,j,1,-1,WIN_LEN, s)) return true;

            }
        }
        return false;
    }

    private static boolean checkLIne(int x , int y, int vx, int vy, int len, String s ) {
            int far_x = x+(len-1)*vx;
            int far_y = y+(len-1)*vy;
            if (far_x < 1 || far_x >= map.length || far_y < 1 || far_y >= map.length) return  false;
        for (int i = 1; i < map.length-1 ; i++) {
            if(map[y+i*vy][x+i*vx]!=s)return false;
        }
        return true;
    }//проверка победы человека
        private static void pcGo (String[][] map) {

                Random random = new Random();
                int horizontal, vertical;

                do {
                    horizontal = random.nextInt(map.length);
                    vertical = random.nextInt(map.length);
                } while (isNotEmptyCell(map, horizontal, vertical));

                map[horizontal][vertical] = PC_STEP;



        }
   private static boolean isEmptyCell(String[][] s, int horizontal, int vertical) {
        return s[horizontal][vertical] == PROBEL;
    }
   private static boolean isNotEmptyCell(String[][] s, int horizontal, int vertical) {
        return !isEmptyCell(s, horizontal, vertical);
    }
        //ход пк

   /* private  static boolean chekEquals()*/

        private static boolean haveAprobel (String[][]map){
            for (int i = 1; i < map.length; i++) {
                for (int j = 1; j < map.length; j++) {
                    if (map[i][j].equals(PROBEL)) {
                        return false;
                    }

                }
            }
            return true;
        }//проверка наличия свободных для хода мест



}
