import java.util.Scanner;


import java.util.Random;
public class Game {

    int hp = 25;


    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);




        Hero hero = new Knight();


        clear();
        intro();
        System.out.println("Input r for rules, input anything else to continue\n");
        String input = scan.nextLine();
        if(input.charAt(0) == 'r')  {
            clear();
            rules();
            System.out.println("input anything to continue\n");
            scan.nextLine();
            clear();
            hero.status();
            System.out.println("input anything to continue\n");
            scan.nextLine();
        }
        clear();
        System.out.println("Ready to start your adventure? (y/n)\n");
        input = scan.nextLine();



        if (input.charAt(0) == 'y')   {
            clear();
            System.out.println("Your journey begins!\n\n");
            wait(2000);
            clear();
        }
        else if (input.charAt(0) == 'n')  {
            clear();
            System.out.println("Too bad! Your journey begins anyways!\n\n");
            wait(2000);
            clear();

        }
        else    {
            clear();
            System.out.println("I have no idea what that input was but ok. Your journey beings anyways!\n\n");
            wait(2000);
            clear();
        }



        for(int i = 0; i <(3+rand.nextInt(10)); i++){
            encounter(hero);
        }




        System.out.println("You have completed your journey!\n\nYou may never be the same again...\n");
    }







    public static void intro()  {
        System.out.println("Welcome to the Dankest Dungeon!\n");
        System.out.println("Your adventure awaits!\n\n");
        System.out.println();
    }


    public static boolean initiative()    {
        Random rand = new Random();
        int playerInitiative = rand.nextInt(20);
        int enemyInitiative = rand.nextInt(20);
        return playerInitiative >= enemyInitiative;
    }

    public static void rules()  {
        System.out.println("On your adventure, you will encounter enemies.");
        System.out.println("You must fight enemies to continue your journey.\n");
        System.out.println("When your HitPoints (HP) reach zero, you die.");
        System.out.println("You also slowly gain fatigue as you battle.\n");
        System.out.println("You deal less damage the more fatigue you have.");
        System.out.println("During battle, input 1-5 for your abilities.\n");
        System.out.println("Along your journey, you may encounter taverns to rest at.");
        System.out.println("[taverns have not been implemented yet so good luck lmao]\n");
    }




    public static void playerCombat(Hero hero, Enemy enemy)    {
        Scanner scan = new Scanner(System.in);
        String combatInput;
        System.out.println("What would you like to do?\n");
        combatInput = scan.nextLine();

        switch(combatInput) {
            default:
                System.out.println("You get confused and pull out your claymore.\n");
            case ("1"):
                hero.attack1(hero, enemy);
                break;
            case ("2"):
                hero.attack2(hero, enemy);
                break;
            case ("3"):
                hero.attack3(hero, enemy);
                break;
            case ("4"):
                hero.attack4(hero);
                break;
            case ("5"):
                hero.attack5(hero);
                break;
        }
    }


    public static void wait(int mili)  {

        try {
            Thread.sleep(mili);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

    }


    public static void enemyCombat(Hero hero, Enemy enemy)    {
        Random rand = new Random();
        int posNeg = rand.nextInt(2);
        int roll = rand.nextInt(10);
        if (roll == 0)  {
            System.out.println("The enemy " + enemy.name + " misses!\n");
        }
        else if(posNeg ==1)    {
            roll = rand.nextInt(5);
            System.out.println("The enemy " + enemy.name + " hits for " + (enemy.attack + roll) + " damage!\n");
            hero.hp = hero.hp - (enemy.attack + roll);
        }
        else    {
            roll = rand.nextInt(5);
            System.out.println("The enemy " + enemy.name + " hits for " + (enemy.attack - roll) + " damage!\n");
            hero.hp = hero.hp - (enemy.attack - roll);
        }
    }




    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void encounter(Hero hero)  {
        Random rand = new Random();
        int gold;
        int fatigue;


        Enemy enemy = encounterEnemy();
        if(initiative())    {
            while(enemy.hp > 0) {
                playerCombat(hero, enemy);
                wait(2000);
                if (enemy.hp > 0){
                    enemyCombat(hero, enemy);
                    wait(2000);
                }

            }
            System.out.println("The enemy " + enemy.name + " dies!\n");
        }
        else    {
            while(enemy.hp > 0) {
                enemyCombat(hero, enemy);
                wait(1000);
                playerCombat(hero, enemy);
                wait(1000);

            }
            System.out.println("The enemy " + enemy.name + " dies!\n");
        }
        wait(1000);
        fatigue = rand.nextInt(10);
        hero.fatigue = hero.fatigue + fatigue;
        System.out.println("gained "+ fatigue + " fatigue\n");
        gold = enemy.gold + rand.nextInt(5);
        hero.gold = hero.gold + gold;
        wait(1000);
        System.out.println("gained " + gold + " gold\n\n");
        wait(2500);
        clear();
        System.out.println("You continue on your journey...\n");
        wait(rand.nextInt(4)*1000);
    }

    public static void displayHud(Hero hero) {
        hero.current();
        System.out.println("-----------------------------------------------------\n");

    }

    public static String[] enemies = {"Bandit", "Cultist", "Undead", "Knight", "Demon",
            "Slime", "Big Slime", "King Slime", "Goblin",
            "Skeleton"};

    public static Enemy encounterEnemy() {
        Enemy enemy = new Enemy();
        Random rand = new Random();

        int roll = rand.nextInt(10);
        int upperbound = 10;
        int encounterRoll = rand.nextInt(upperbound);
        enemy.enemyType = encounterRoll;
        switch(enemy.enemyType)   {
            case(0):
                //bandit
                enemy.name = enemies[0];
                enemy.hp = 15;
                enemy.attack = 10;
                enemy.armor = 5;
                enemy.mr = 5;
                enemy.gold = 5;
                //cultist
            case(1):
                enemy.name = enemies[1];
                enemy.hp = 15;
                enemy.attack = 10;
                enemy.armor = 0;
                enemy.mr = 10;
                enemy.gold = 10;
                break;
            case(2):
                //undead
                enemy.name = enemies[2];
                enemy.hp = 10;
                enemy.attack = 10;
                enemy.armor = 0;
                enemy.mr = 0;
                enemy.gold = 2;
                break;
            case(3):
                //enemy hero
                enemy.name = enemies[3];
                enemy.hp = 50;
                enemy.attack = 5;
                enemy.armor = 10;
                enemy.mr = 0;
                enemy.gold = 20;
                break;
            case(4):
                //demon
                enemy.name = enemies[4];
                enemy.hp = 5;
                enemy.attack = 25;
                enemy.armor = 10;
                enemy.mr = 10;
                enemy.gold = 25;
                break;
            case(5):
                //slime
                enemy.name = enemies[5];
                enemy.hp = 50;
                enemy.attack = 5;
                enemy.armor = 15;
                enemy.mr = -10;
                enemy.gold = 5;
                break;
            case(6):
                //big slime
                enemy.name = enemies[6];
                enemy.hp = 100;
                enemy.attack = 10;
                enemy.armor = 30;
                enemy.mr = -20;
                enemy.gold = 10;
                break;
            case(7):
                //king slime
                enemy.name = enemies[7];
                enemy.hp = 200;
                enemy.attack = 20;
                enemy.armor = 60;
                enemy.mr = -40;
                enemy.gold = 20;
                break;
            case(8):
                //goblin
                enemy.name = enemies[8];
                enemy.hp = 20;
                enemy.attack = 10;
                enemy.armor = 10;
                enemy.mr = 10;
                enemy.gold = 50;
                break;
            case(9):
                //skeleton
                enemy.name = enemies[9];
                enemy.hp = 20;
                enemy.attack = 10;
                enemy.armor = -10;
                enemy.mr = -10;
                enemy.gold = 0;
                break;
        }

        System.out.println(encounterText(rand.nextInt(8))+"\n");
        wait(1000);
        System.out.println("An enemy " + enemy.name + " appears!\n");
        wait(1000);

        return enemy;
    }

    public static String encounterText(int roll)  {
        String encounter = "";
        switch (roll)   {
            case(0):
                encounter = "You hear a rustle among the bushes...";
                break;
            case(1):
                encounter = "You hear footsteps in the distance...";
                break;
            case(2):
                encounter = "A shadowy figure approaches...";
                break;
            case(3):
                encounter = "You feel a hostile presence...";
                break;
            case(4):
                encounter = "You realize there's someone in the trees...";
                break;
            case(5):
                encounter = "You realize you're not alone...";
                break;
            case(6):
                encounter = "There's trouble afoot!";
                break;
            case(7):
                encounter = "An enemy monster was summoned in attack position!";
                break;
        }
        return encounter;


    }





}
