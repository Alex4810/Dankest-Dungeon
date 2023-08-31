import java.util.Random;
public class Knight extends Hero {
    public Random rand = new Random();
    public int hp = 100;
    public int fatigue = 0;
    public int attack = 25;
    public int gold;
    public String ability1 = "Claymore Slash - standard physical attack";
    public String ability2 = "Holy Smite = standard magic attack";
    public String ability3 = "Blessed Blade - physical and magic split attack";
    public String ability4 = "Patch Wounds - recover some HP";
    public String ability5 = "Battle Meditation - relieve some Fatigue";


    public void status()    {
        System.out.println("You are playing as Knight:");
        System.out.println("HP: " + hp);
        System.out.println("Fatigue: " + fatigue);
        System.out.println("1 ability: " + ability1);
        System.out.println("2 ability: "+ability2);
        System.out.println("3 ability: "+ability3);
        System.out.println("4 ability: "+ability4);
        System.out.println("5 ability: "+ability5 + "\n\n");
    }

    public void current()   {
        System.out.println("HP: " + hp + "\t Fatigue: " + fatigue + "\t Gold: " + gold);
    }

    public int damageTotal = attack;
    public int roll;
    public int posNeg;
    public int damageMod;

    //claymore (phy)
    public void attack1(Hero hero, Enemy enemy)   {
        roll = rand.nextInt(10);
        posNeg = rand.nextInt(2);
        damageMod = rand.nextInt(5);
        if(posNeg == 0) {
            damageTotal = attack - enemy.armor - damageMod;
            if(damageTotal < 0 )
                damageTotal = 0;
        }
        else    {
            damageTotal = attack - enemy.armor + damageMod;
            if(damageTotal < 0 )
                damageTotal = 0;
        }
        if (roll == 0)  {
            System.out.println("Your attack misses!\n");
        }
        else    {
            damageTotal = damageTotal - (fatigue/10);
            enemy.hp = enemy.hp - damageTotal;
            System.out.println("Your claymore strikes the enemy " + enemy.name + ", dealing " + damageTotal + " damage!\n");
        }
    }
    //smite(magic)
    public void attack2(Hero hero, Enemy enemy)   {
        damageTotal = attack;
        roll = rand.nextInt(10);
        roll = rand.nextInt(10);
        posNeg = rand.nextInt(2);
        damageMod = rand.nextInt(5);
        if(posNeg == 0) {
            damageTotal = attack - enemy.mr - damageMod;
            if(damageTotal < 0 )
                damageTotal = 0;
        }
        else    {
            damageTotal = attack - enemy.mr + damageMod;
            if(damageTotal < 0 )
                damageTotal = 0;
        }
        if (roll == 0)  {
            System.out.println("Your attack misses!\n");
        }
        else    {
            damageTotal = damageTotal - (fatigue/10);
            enemy.hp = enemy.hp - damageTotal;
            System.out.println("You smite the enemy " + enemy.name + ", dealing " + damageTotal + " damage!\n");
        }

    }

    //bb (split)
    public void attack3(Hero hero, Enemy enemy)   {
        roll = rand.nextInt(10);
        roll = rand.nextInt(10);
        posNeg = rand.nextInt(2);
        damageMod = rand.nextInt(5);
        if(posNeg == 0) {
            damageTotal = ((attack/2)+3) - enemy.mr + ((attack/2)+3) - enemy.armor - damageMod;
            if(damageTotal < 0 )
                damageTotal = 0;
        }
        else    {
            damageTotal = ((attack/2)+3) - enemy.mr + ((attack/2)+3) - enemy.armor + damageMod;
            if(damageTotal < 0 )
                damageTotal = 0;
        }
        if (roll == 0)  {
            System.out.println("Your attack misses!\n");
        }
        else    {
            damageTotal = damageTotal - (fatigue/10);
            enemy.hp = enemy.hp - damageTotal;
            System.out.println("Your blessing-infused Zweihänder clobbers the enemy " + enemy.name + " upside the head, dealing " + damageTotal + " damage!\n");
        }
    }

    public void attack4(Hero hero)   {
        int healing = rand.nextInt(5) + 5;
        hero.hp = hero.hp + healing;
        System.out.println("You patch your wounds, healing for " + healing + " hp.\n");
    }

    public void attack5(Hero hero)   {
        int recovery = rand.nextInt(5) + 7;
        hero.fatigue = hero.fatigue - recovery;
        if(hero.fatigue < 0)   {
            hero.fatigue = 0;
        }
        System.out.println("You meditate for a short period, reducing your fatigue by " + recovery + "\n");
    }



}
