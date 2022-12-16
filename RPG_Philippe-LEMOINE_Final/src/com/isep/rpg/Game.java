package com.isep.rpg;

import com.isep.utils.InputParser;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {
    private boolean VF;
    public Hero[] hero;
    public Ennemy[] ennemis;
    public Ennemy[] ennemiB = new Ennemy[1];
    public Potion[] potion;
    public Food[] food;
    public Sort[] sortM;
    public Sort[] sortG;
    public Sort[] sortC;
    Integer nH;
    static int nE;
    static int nbTour;
    int niveau;
    boolean Ko = false;
    boolean pasKo = true;

    public Game(InputParser inputParser) {

        this.inputParser = inputParser;
    }

    public void start() throws InterruptedException {
        System.out.println("""
                Bienvenue dans le meilleur RPG du monde
                """);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("""
                Précédemment dans le meilleur RPG au monde...\s
                """);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("""
                Dans une contrée lointaine, très lointaine...
                """);
        TimeUnit.SECONDS.sleep(5);
        System.out.println("""
                Le Maître du Mal convoitait le trône du roi d'Ago Bert, le souverain du monde.\s
                Celui-ci n'avait qu'une héritière pour faire prospérer sa lignée.\s
                À l'aube du 18ème anniversaire de la princesse Pitch, le roi décida d'organiser un grand tournoi pour marier sa fille au grand vainqueur.\s
                Mais lorsque le grand tournois du MORTAL COMBAT fut sur le point de commencer, un grand éclair apparut pour laisser place au terrible Maitre.\s
                Celui-ci enleva la princesse pour l'épouser dans son sinistre château et ainsi s'emparer de la couronne.\s
                """);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("""
                Pris de panique, le bon roi vient prier au-près des Dieux pour sauver sa magnifique fille.\s
                Les Dieux entendirent ses prière et s'empressèrent de créer un élu, capable de sauver la princesse.\s
                C'est à vous de jouer champion, si le Maitre du Mal récupère les pouvoirs conférés au bon roi, plus personne ne pourra l'arrêter.\s
                Mais la route sera longue et périlleuse, il vous faudra vous constituer une équipe.\s
                Serez vous le sauveur du monde ou un simple héro de pacotille ? Seule votre aventure nous le dira.\s
                """);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("""
                Bonne chance champion...\s
                """);
        TimeUnit.SECONDS.sleep(5);
        System.out.print("Création de votre équipe");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Voici l'équipe qui sauvera le monde:");
        nombreHero();
        afficheHero();
        System.out.println(" ");
        System.out.println("#########################################");
        System.out.println(" ");
        System.out.println("###### Description de votre sac de départ ######");
        System.out.println(" ");
        sac();
        TimeUnit.SECONDS.sleep(5);
        nbTour = nbTour();
        for (int i = 0; i < nbTour; i++){
            boolean etatH = KoA();
            if (etatH == pasKo){
                System.out.println(" ");
                System.out.println("#########################################");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Attention, il y a des mouvements dans les hautes herbes, vite allons voir...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Oh non, c'est une embuscade, les ennemis vous encerclent");
                TimeUnit.SECONDS.sleep(3);
                nbEnnemis();
                if (nE == 1){
                    System.out.println("Voici votre adversaire");
                }
                else{
                    System.out.println("Voici vos adversaires");
                }
                afficheEnnemies();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(" ");
                combat(ennemis);
                System.out.println(" ");
                System.out.println("Fin du Tour " + (i+1) + " / " + nbTour);
                finCombatH();
                entreCombat();
            }
        }
        boolean etatHF = KoA();
        if (etatHF == pasKo){
            System.out.println(" ");
            System.out.println("Vous arrivez au niveau du château, où la chose qui tire les ficelles dans l'ombre se terre avec la princesse kidnappée.");
            ennemiB[0] = new MaitreDuMal("Le Maître du Mal");
            combat(ennemiB);
            if (etatHF == pasKo){
                msgBien();
            }
        }
        finCombatH();
    }

    public void entreCombat(){
        Scanner choix = new Scanner(System.in);
        int c = 0;
        int c2;
        int rep = 0;
        while (c !=1 || rep == 1){
            rep = 0;
            try{
                if (c == 0){
                    System.out.println("""
                Voulez-vous utiliser un objet sur l'un de vos équipiez?
                
                1) Sac
                2) Prochaine étape
                """);
                    c = choix.nextInt() - 1;
                    afficheSac();
                    System.out.println("""

                    ####### Votre Équipe #######
                    
                    """);
                    afficheHero();
                    System.out.println("(entrer l'objet que vous voulez utiliser");
                    try {
                        c2 = choix.nextInt() - 1;
                        if (c2 == 0){
                            apresCV();
                        }
                        else if (c2 == 1){
                            apresCR();
                        }
                        else if (c2 == 2){
                            apresCG();
                        }
                        else if (c2 == 3){
                            apresCP();
                        }
                        else if (c2 == 4){
                            apresCRa();
                        }
                        else{
                            System.out.println("Aucune action possible, veuillez répéter");
                            rep = 1;
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Requête inconnue, veuillez répéter");
                        choix.nextLine();
                        continue;
                    }
                }
                else if (c > 1){
                    System.out.println("Commande introuvable, veuillez répéter");
                    rep = 1;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête inconnue, veuillez répéter");
                choix.nextLine();
                continue;
            }
        }
        if (rep == 1){
            entreCombat();
        }
    }
    public void apresCRa(){
        Scanner choix = new Scanner(System.in);
        int cH = 0;
        System.out.println("Sur qui voulez vous utiliser cette portion de raclette?");
        do{
            try{
                cH = choix.nextInt()-1;
                if (cH < nH){
                    if (potion[1].quantitee > 0){
                        do{
                            if (hero[cH].healthPoint > 0){
                                if (food[1].mana < hero[cH].manaMax - hero[cH].mana){
                                    hero[cH].mana += food[1].mana;
                                    System.out.println(hero[cH].name + " a récupéré " + food[1].mana + " mana.");
                                    food[1].quantitee-=1;
                                }
                                else if (food[1].mana > hero[cH].manaMax - hero[cH].mana){
                                    hero[cH].mana = hero[cH].manaMax;
                                    System.out.println(hero[cH].name + " a récupéré " + (hero[cH].mana = hero[cH].manaMax) + " mana.");
                                    food[1].quantitee-=1;
                                }
                                else{
                                    System.out.println(hero[cH].name + " a déjà tout son mana.");
                                }
                            }
                            else{
                                System.out.println(hero[cH].name + " est KO, veuillez sélectionner une nouvelle action.");
                                entreCombat();
                            }
                        } while (cH >= nH);
                    }
                    else{
                        System.out.println("Vous n'avez plus de réserve de portion de Raclette, veuillez sélectionner une nouvelle action.");
                        entreCombat();
                    }
                }
                else{
                    System.out.println("Héro inconnu, veuillez répéter");
                    entreCombat();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête inconnue, veuillez répéter");
                choix.nextLine();
            }
        }
        while(true);
    }
    public void apresCP(){
        Scanner choix = new Scanner(System.in);
        int cH = 0;
        System.out.println("Sur qui voulez vous utiliser cette cuisse de poulet?");
        do{
            try{
                cH = choix.nextInt()-1;
                if (cH < nH){
                    if (potion[0].quantitee > 0){
                        do{
                            if (hero[cH].healthPoint > 0){
                                if (food[0].mana < hero[cH].manaMax - hero[cH].mana){
                                    hero[cH].mana += food[0].mana;
                                    System.out.println(hero[cH].name + " a récupéré " + food[0].mana + " mana.");
                                    food[0].quantitee-=1;
                                }
                                else if (food[0].mana > hero[cH].manaMax - hero[cH].mana){
                                    hero[cH].mana = hero[cH].manaMax;
                                    System.out.println(hero[cH].name + " a récupéré " + (hero[cH].mana = hero[cH].manaMax) + " mana.");
                                    food[0].quantitee-=1;
                                }
                                else{
                                    System.out.println(hero[cH].name + " a déjà tout son mana.");
                                }
                            }
                            else{
                                System.out.println(hero[cH].name + " est KO, veuillez sélectionner une nouvelle action.");
                                entreCombat();
                            }
                        } while (cH >= nH);
                    }
                    else{
                        System.out.println("Vous n'avez plus de réserve de cuisse de Poulet, veuillez sélectionner une nouvelle action.");
                        entreCombat();
                    }
                }
                else{
                    System.out.println("Héro inconnu, veuillez répéter");
                    entreCombat();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête inconnue, veuillez répéter");
                choix.nextLine();
            }
        }
        while(true);
    }
    public void apresCG(){
        Scanner choix = new Scanner(System.in);
        int cH = 0;
        System.out.println("Sur qui voulez vous utiliser cette potion de Guérison?");
        do{
            try{
                cH = choix.nextInt()-1;
                if (cH < nH){
                    do{
                        if (potion[3].quantitee > 0){
                            if (hero[cH].healthPoint > 0){
                                if (hero[cH].effetS != 0){
                                    hero[cH].effetS = 0;
                                    System.out.println(hero[cH].name + " ne souffre de plus aucun maux.");
                                    potion[3].quantitee-=1;
                                }
                                else{
                                    System.out.println(hero[cH].name + " ne souffre d'aucun mal, veuillez sélectionner une nouvelle action.");
                                    entreCombat();
                                }
                            }
                            else{
                                System.out.println(hero[cH].name + " est KO, veuillez sélectionner une nouvelle action.");
                                entreCombat();
                            }
                        }
                        else{
                            System.out.println("Vous n'avez plus de réserve de potion de Guérison, veuillez sélectionner une nouvelle action.");
                            entreCombat();
                        }
                    } while (cH >= nH);
                }
                else{
                    System.out.println("Héro inconnu, veuillez répéter");
                    entreCombat();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête inconnue, veuillez répéter");
                choix.nextLine();
            }
        }
        while (true);
    }

    public void apresCR(){
        Scanner choix = new Scanner(System.in);
        int cH = 0;
        System.out.println("Sur qui voulez vous utiliser cette potion de Résistance?");
        do{
            try{
                cH = choix.nextInt()-1;
                if (cH < nH){
                    do{
                        if (potion[1].quantitee > 0){
                            if (hero[cH].healthPoint > 0){
                                hero[cH].resP += potion[1].resistance;
                                hero[cH].resS += potion[1].resistance;
                                System.out.println(hero[cH].name + " a augmenter ses défenses.");
                                potion[1].quantitee-=1;
                            }
                            else{
                                System.out.println(hero[cH].name + " est KO, veuillez sélectionner une nouvelle action.");
                                entreCombat();
                            }
                        }
                        else{
                            System.out.println("Vous n'avez plus de réserve de potion de Résistance, veuillez sélectionner une nouvelle action.");
                            entreCombat();
                        }
                    } while (cH >= nH);
                }
                else{
                    System.out.println("Héro inconnu, veuillez répéter");
                    entreCombat();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête inconnue, veuillez répéter");
                choix.nextLine();
            }
        }
        while (true);
    }
    public void apresCV(){
        Scanner choix = new Scanner(System.in);
        int cH = 0;
        System.out.println("Sur qui voulez vous utiliser cette potion de vie?");
        do{
            try{
                cH = choix.nextInt()-1;
                if (cH < nH){
                    if (potion[0].quantitee > 0){
                        do{
                            if (potion[0].recupVie < hero[cH].healthPointMax - hero[cH].healthPoint){
                                hero[cH].healthPoint += potion[0].recupVie;
                                System.out.println(hero[cH].name + " a récupéré " + potion[0].recupVie + " HP.");
                                potion[0].quantitee-=1;
                            }
                            else if (potion[0].recupVie > hero[cH].healthPointMax - hero[cH].healthPoint){
                                hero[cH].healthPoint = hero[cH].healthPointMax;
                                System.out.println(hero[cH].name + " a récupéré " + (hero[cH].healthPointMax - hero[cH].healthPoint) + " HP.");
                                potion[0].quantitee-=1;
                            }
                            else if (hero[cH].healthPoint == hero[cH].healthPointMax){
                                System.out.println(hero[cH].name + " a déjà toute sa vie.");
                            }
                            else{
                                System.out.println(hero[cH].name + " est KO, veuillez sélectionner une nouvelle action.");
                                entreCombat();
                            }
                        } while (cH >= nH);
                    }
                    else{
                        System.out.println("Vous n'avez plus de réserve de potion de Vie, veuillez sélectionner une nouvelle action.");
                        entreCombat();
                    }
                }
                else{
                    System.out.println("Héro inconnu, veuillez répéter");
                    entreCombat();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête inconnue, veuillez répéter");
                choix.nextLine();
            }
        }
        while(true);
    }

    public void afficheSac(){
        System.out.println("####### Votre Sac #######");
        System.out.println(" ");
        System.out.println(potion[0].name + " | Récupère " + potion[0].recupVie + " HP | " + potion[0].quantitee);
        System.out.println(potion[1].name + " | Augment la résistance de " + potion[1].resistance + " | " + potion[1].quantitee);
        System.out.println(potion[3].name + " | Annule tous les effets | " + potion[3].quantitee);
        System.out.println(food[0].name + " | Récupère " + food[0].mana + " mana | " + food[0].quantitee);
        System.out.println(food[1].name + " | Récupère " + food[1].mana + " mana | " + food[1].quantitee);
    }

    public void msgBien(){
        System.out.println("""
                Félicitation, vous avez tué le méchant et libéré la princesse...\s
                Pour vous remercier, la princesse vous fait un bisous sur la joue...""");
    }

    public Hero[] nombreHero() {
        String YN;
        String n;
        String cl;

        do {
            YN ="n";
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Veuillez saisir le nombre de héros dans votre équipe:");
            try{
                nH = scanner1.nextInt();
                VF = msgDebut(nH);
                if (VF == true){
                    System.out.println("Il y a " + nH + " membres dans votre équipe.");
                    System.out.println("Est-ce exact? (y pour oui, n pour non)");
                    YN = scanner1.next();
                }
                else{
                    System.out.println("Une équipe comporte 5 héros maximum.");
                    YN = "n";
                }
            }
            catch (InputMismatchException e){
                System.out.println("Requête incompréhensible, veuillez répéter");
                scanner1.nextLine();
                continue;
            }
        } while (!YN.equalsIgnoreCase("y"));
        hero = new Hero[nH];
        for (int i = 0; i < nH; i++) {
            int j = (i + 1);
            do {
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Veuillez saisir le nom du héros " + j + " de votre équipe:");
                n = scanner2.next();
                System.out.println("Le héro " + j + " de votre équipe s'appelle " + n);
                System.out.println("Est-ce exact? (y pour oui, n pour non)");
                YN = scanner2.next();
            } while (!YN.equalsIgnoreCase("y"));
            do {
                Scanner scanner3 = new Scanner(System.in);
                System.out.println("Veuillez saisir la classe du héros " + j + " de votre équipe (c pour chevalier, a pour archer, m pour mage, g pour guerisseur):");
                cl = scanner3.next();
                if (cl.equalsIgnoreCase("c")){
                    System.out.println("Le héro " + j + " de votre équipe est un chevalier");
                    System.out.println("Est-ce exact? (y pour oui, n pour non)");
                    YN = scanner3.next();
                }
                else if (cl.equalsIgnoreCase("a")){
                    System.out.println("Le héro " + j + " de votre équipe est un archer");
                    System.out.println("Est-ce exact? (y pour oui, n pour non)");
                    YN = scanner3.next();
                }
                else if (cl.equalsIgnoreCase("m")){
                    System.out.println("Le héro " + j + " de votre équipe est un mage");
                    System.out.println("Est-ce exact? (y pour oui, n pour non)");
                    YN = scanner3.next();
                }
                else if (cl.equalsIgnoreCase("g")){
                    System.out.println("Le héro " + j + " de votre équipe est un guérisseur");
                    System.out.println("Est-ce exact? (y pour oui, n pour non)");
                    YN = scanner3.next();
                }
                else{
                    YN.equalsIgnoreCase("n");
                }
            } while (!YN.equalsIgnoreCase("y"));
            switch (cl) {
                case "c":
                    hero[i] = new Warrior(n);
                    hero[i].take(new Weapon("Épée"));
                    break;
                case "a":
                    hero[i] = new Hunter(n);
                    hero[i].take(new Weapon("Arc"));
                    break;
                case "m":
                    hero[i] = new Mage(n);
                    hero[i].take(new Weapon("Bâton de sorcier"));
                    break;
                case "g":
                    hero[i] = new Healer(n);
                    hero[i].take(new Weapon("Baguette"));
                    break;
            }
        }
        return hero;
    }

    public void sac(){
        potion = new Potion[4];
        potion[0] = new PotionV("Potion de Vie");
        System.out.println("Potion de vie: régénère 70 HP");
        potion[1] = new PotionR("Potion de Résistance");
        System.out.println("Potion de résistance: réduit de 50% les dégâts ennemis");
        potion[2] = new PotionP("Potion de Poison");
        System.out.println("Potion de poison: empoisonne l'ennemi, celui-ci perdra 5 HP par tour tant qu'il ne sera pas guéri");
        potion[3] = new PotionG("Potion de Guérison");
        System.out.println("Potion de guérison: annule tous les effets");

        food = new Food[2];
        food[0] = new CuisseP("Cuisse de Poulet");
        food[1] = new Raclette("Raclette");
    }

    public Ennemy[] nbEnnemis(){
        int i;

        int typeEnnemi = typeEnnemi();
        niveau = niveau();

        if(typeEnnemi == 1){
            nE = nE(nH);
        }
        else if (typeEnnemi == 2){
            nE = nE(nH);
            if (nE > 1){
                nE--;
            }
        }
        else{
            if (nH == 1){
                nE = 1;
            }
            else{
                nE = nE(1);
                if (nE > nH){
                    nE = nH;
                }
            }
        }

        ennemis = new Ennemy[nE];

        if (typeEnnemi == 1){
            if (niveau == 2){
                ennemis[0] = new Gnome("Gnome (Alpha)", 2);
                for (i = 1; i < nE; i++){
                    ennemis[i] = new Gnome("Gnome " + i, 1);
                }
            }
            else{
                for (i = 0; i < nE; i++){
                    ennemis[i] = new Gnome("Gnome " + (i+1), 1);
                }
            }
        }
        else if (typeEnnemi == 2){
            if (niveau == 2){
                ennemis[0] = new Troll("Troll (Alpha)", 2);
                for (i = 1; i < nE; i++){
                    ennemis[i] = new Troll("Troll " + i, 1);
                }
            }
            else{
                for (i = 0; i < nE; i++){
                    ennemis[i] = new Troll("Troll " + (i+1), 1);
                }
            }
        }
        else{
            if (niveau == 2){
                ennemis[0] = new Dragon("Dragon (Alpha)", 2);
                for (i = 1; i < nE; i++){
                    ennemis[i] = new Dragon("Dragon " + i, 1);
                }
            }
            else{
                for (i = 0; i < nE; i++){
                    ennemis[i] = new Dragon("Dragon " + (i+1), 1);
                }
            }
        }
        return ennemis;
    }

    public boolean msgDebut(int nH){
        boolean VF;

        if (nH < 6){
            VF = true;
        }
        else{
            VF = false;
        }
        return VF;
    }

    public void afficheHero(){
        for (int i = 0; i < nH; i++){
            System.out.println(hero[i].name + " | " + hero[i].healthPoint + " HP | " + hero[i].mana + " mana");
        }
    }

    public void afficheEnnemies(){
        for (int i = 0; i<nE; i++){
            System.out.println(ennemis[i].name + " | " + ennemis[i].healthPoint + " HP");
        }
    }

    public int typeEnnemi(){
        Random rand = new Random();
        int type0;
        return type0 = 1 + rand.nextInt(3);
    }

    public int niveau(){
        Random rand = new Random();
        int niveau;
        return niveau = 1 + rand.nextInt(2);
    }

    public int nE (int nH){
        Random rand = new Random();
        return nE = 1 + rand.nextInt(nH + 2);
    }

    public int nbTour(){
        Random tour = new Random();
        return nbTour = 1 + tour.nextInt(5);
    }

    private InputParser inputParser;
    public void displayStatus() {
        System.out.println("#################################");
        System.out.println(" ");
        int i;
        int j;
        System.out.println("Votre équipe:");
        for (i = 0; i < this.nH; i++) {
            System.out.println(hero[i].name + " (" + hero[i].healthPoint + " HP, " + hero[i].mana + " mana) ");
        }
        System.out.println(" ");
        System.out.println("----------------VS-----------------");
        System.out.println(" ");
        System.out.println("L'équipe adverse:");
        for (j = 0; j < nE; j++) {
            System.out.println(ennemis[j].name + " (" + ennemis[j].healthPoint + " HP) ");
        }
        System.out.println();
    }

    public void finCombatH(){
        System.out.println("#################################");
        System.out.println(" ");
        int i;
        System.out.println("Votre équipe:");
        for (i = 0; i < this.nH; i++) {
            System.out.println(hero[i].name + " (" + hero[i].healthPoint + " HP) ");
        }
    }

    public void combat(Ennemy [] ennemisC) throws InterruptedException {
        int i = 0;
        boolean KoA = KoA();
        boolean KoE = KoE();
        while ((KoA == pasKo) & (KoE == pasKo)){   //KoA = true et KoE = true...
            i++;
            while(KoE == pasKo){
                tourAl(hero, ennemisC);

                if (KoE == pasKo){
                    for (i = 0; i < nE; i++){
                        if (ennemisC[i].healthPoint > 0) {
                            attaqueEn(ennemisC[i]);
                        }
                    }
                }
            }
            etatAl(hero);
            etatEn(ennemisC);
        }

        choix();
    }

    public void choix() throws InterruptedException {
        boolean KoE = KoE();
        String ON;
        String YN;

        if (KoE == Ko){
            System.out.println("L'équipe adverse est KO");
            System.out.println("Vous avez gagné");
            loot();
            experience();
        }

        else{
            System.out.println("Votre équipe est KO");
            System.out.println("Vous étiez notre dernier espoir");
            System.out.println("Le Monde va être annéanti");
            System.out.println("À moins qu'un nouvel espoir apparait");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Voulez-vous retenter l'aventure? (o pour oui, n pour non)");
            ON = scanner.next();

            if (ON.equalsIgnoreCase("o")){
                System.out.println("En êtes-vous sûr? (y pour oui, n pour non)");
                YN = scanner.next();

                if (YN.equalsIgnoreCase("y")){
                    start();
                }
                else if (YN.equalsIgnoreCase("n")){
                    finJeu();
                }
                else{
                    System.out.println("Requête non comprise...");
                    System.out.println("Arrêt des systèmes...");
                    finJeu();
                }
            }
            else if (ON.equalsIgnoreCase("n")){
                finJeu();
            }
            else{
                System.out.println("Requête non comprise...");
                System.out.println("Arrêt des systèmes...");
                finJeu();
            }
        }
    }

    private boolean KoA() {
        boolean KOA = pasKo;
        for (int i = 0; i < nH; i++){
            if (hero[i].healthPoint != 0){
                KOA = pasKo;
                break;
            }
            else{
                KOA = Ko;
            }
        }
        return KOA;
    }

    private boolean KoE() {
        boolean KOE = pasKo;
        for (int i = 0; i < nE; i++){
            if (ennemis[i].healthPoint != 0){
                KOE = pasKo;
                break;
            }
            else{
                KOE = Ko;
            }
        }
        return KOE;
    }

    public void tourAl(Hero[] hero0, Ennemy [] ennemi0){
        displayStatus();
        for (int i = 0; i < nH; i++){
            action(hero0[i],hero0,ennemi0);
            displayStatus();
        }
    }

    public void action(Hero hero01, Hero[] hero02, Ennemy [] ennemi01){
        int actR = 0;
        do{
            if (hero01.healthPoint != 0){
                actR = tourAl1(hero01);
                switch (actR){
                    case 10:
                        attaqueHe(hero01, ennemi01);
                        break;
                    case 20:
                        protegeHe(hero01);
                        break;
                    case 30:
                        sortilege(hero01, hero02, ennemi01);
                        break;
                    case 40:
                        nourritureH(hero01, hero02, ennemi01);
                        break;
                    case 41:
                        potionH(hero01, hero02, ennemi01);
                        break;
                    default:
                        break;
                }
            }
        }
        while (actR == 42);
    }

    public int tourAl1(Hero hero1) {
        String name = hero1.name;
        Scanner tour = new Scanner(System.in);
        System.out.println("Que va faire " + name + ":");
        System.out.println("1) Attaquer");
        System.out.println("2) Se protéger");
        System.out.println("3) Sortilège");
        System.out.println("4) Items");
        int action;
        int items=0;
        int act = 0;
        try{
            action = tour.nextInt();
            act += action * 10;
            switch (action) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Quel item va-t-il prendre?");
                    System.out.println("1) Nourriture");
                    System.out.println("2) Potion");
                    System.out.println("3) Retour");
                    do{
                        try{
                            items = tour.nextInt();
                            if (items > 0 & items <= 3){
                                items--;
                                act += items; //act = 10 (attaque), 20 (défense), 30 (sort), 40 (nourriture), 41 (potion), 42 (AL1)
                                break;
                            }
                            else{
                                System.out.println("action inconnu, veuillez répéter");
                                act = 42;
                                break;
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("Item inconnu, renvoie au choix des actions.");
                            act = 42;
                            break;
                        }
                    }
                    while(true);
                    break;
                default:
                    System.out.println("Action inconnu, renvoie au choix des actions.");
                    act = 42;
                    break;
            }
        }
        catch(InputMismatchException e){
            System.out.println("""
                    Action inconnu, veuillez recommencer\s
                    """);
            act = 42;
        }
        return act;
    }

    public void attaqueHe(Hero heroAt, Ennemy[] ennemiA){
        int getDamage = heroAt.degP * heroAt.weapon.getDamagePoints();
        Scanner attaquer = new Scanner(System.in);
        System.out.println("Qui voulez-vous attaquer?");
        afficheEnnemies();
        try{
            int attaque = attaquer.nextInt() - 1;
            if (attaque < nE){
                if (ennemiA[attaque].healthPoint > 0){
                    System.out.println(heroAt.name + " attaque l'ennemi " + ennemiA[attaque].name);
                    if (getDamage > ennemiA[attaque].healthPoint){
                        ennemiA[attaque].healthPoint = 0;
                    }
                    else {
                        ennemiA[attaque].healthPoint -= getDamage;
                    }
                }
                else{
                    System.out.println("Cet ennemi est déjà KO");
                    System.out.println("Veuillez choisir un nouvel ennemi à attaquer");
                    action(heroAt,hero,ennemiA);
                }
            }
            else{
                System.out.println("Ennemi introuvable, renvoie au choix des actions.");
                action(heroAt, hero, ennemiA);
            }
        }
        catch (InputMismatchException e){
            System.out.println("""
                    Commande inconnue, retour aux choix du héro.\s
                    """);
            action(heroAt, hero, ennemiA);
        }
    }

    public void protegeHe(Hero heroD){
        System.out.println(heroD.name + " se défend.");
        heroD.degP = heroD.degP * 2;
    }

    public void sortilege(Hero heroSo, Hero[] heroS, Ennemy[] ennemiSo){
        Scanner sorts = new Scanner(System.in);
        sortM = new Sort[5];
        sortM[0] = new Boul2Feu("Boule de feu");
        sortM[1] = new Soins("Soins");
        sortM[2] = new BrumeP("Brume de Poison");
        sortM[3] = new VentG("Vent Glacial");
        sortM[4] = new Retour("Retour");

        sortG = new Sort[5];
        sortG[0] = new Boul2Feu("Boule de feu");
        sortG[1] = new Soins("Soins");
        sortG[2] = new Resistance("Résistance");
        sortG[3] = new Resurrection("Résurrection");
        sortG[4] = new Retour("Retour");

        sortC = new Sort[3];
        sortC[0] = new Boul2Feu("Boule de feu");
        sortC[1] = new Soins("Soins");
        sortC[2] = new Retour("Retour");

        Integer sort = 0;
        Integer qui = 0;
        int ver = 0;
        int ver2 = 0;
        System.out.println("Quel sort " + heroSo.name + " va-t-il lancer?");
        if (heroSo.classe.equals("Mage")){
            do{
                ver = 0;
                afficheSortM();
                try{
                    sort = sorts.nextInt()-1;
                    if (sort < 5){
                        if (heroSo.mana < sortM[sort-1].coutS){
                            System.out.println(heroSo.name + " n'a pas assez de mana pour lancer ce sort");
                            System.out.println("Veuillez choisir un nouveau sort");
                        }
                        else{
                            ver = 1;
                        }
                    }
                    else{
                        System.out.println("Sort introuvable, veuillez sélectionner une nouvelle action.");
                        ver = 1;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Il faut taper un nombre...");
                    sorts.nextLine();
                    continue;
                }
            } while (heroSo.mana < sortM[sort].coutS || ver == 0);

            if (sort == 0){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    ver2 = 0;
                    afficheEnnemies();
                    System.out.println("Retour");
                    int ret = nE;
                    System.out.println("(insérer la position du monstre que vous voulez attaquer)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui > nE){
                            System.out.println("Il n'y a aucun monstre à attaquer ici, veuillez sélectionner un nouveau monstre");
                        }
                        else if (qui == ret){
                            ver2 = 1;
                        }
                        else{
                            if (ennemiSo[qui].healthPoint == 0){
                                System.out.println("Le monstre est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nE & ennemiSo[qui].healthPoint == 0) || (qui > nE) || ver2 == 1);
                if (qui != nE){
                    sortM[sort].effetS = 15;
                    System.out.println(heroSo.name + " lance une " + sortM[sort].name + " sur " + ennemiSo[qui].name);
                    if (ennemiSo[qui].healthPoint < sortM[sort].degat){
                        ennemiSo[qui].healthPoint = 0;
                    }
                    else{
                        ennemiSo[qui].healthPoint -= sortM[sort].degat;
                        ennemiSo[qui].effet += sortM[sort].effetS;
                    }
                    heroSo.mana -= sortM[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 1){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheHero();
                    System.out.println("Retour");
                    int ret = nH;
                    System.out.println("(insérer la position du héro que vous voulez soigner)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui >= nH){
                            if (qui == ret){
                                ver2 = 1;
                            }
                            else{
                                System.out.println("Il n'y a aucun héro à soigner ici, veuillez sélectionner un nouveau héro");
                            }
                        }
                        else{
                            if (heroS[qui].healthPoint == 0){
                                System.out.println("Le héro est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else if (heroS[qui].healthPoint == heroS[qui].healthPointMax){
                                System.out.println("Le héro a déjà toute sa vie au max, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nH & (heroS[qui].healthPoint == 0 || heroS[qui].healthPoint >= heroS[qui].healthPointMax))|| (qui > nH) || ver2 == 0);
                if (qui != nH){
                    sortM[sort].healthPointS = 50;
                    System.out.println(heroSo.name + " utilise un " + sortM[sort].name + " sur " + heroS[qui].name);
                    if (heroS[qui].healthPointMax - heroS[qui].healthPoint < sortM[sort].healthPointS){
                        heroS[qui].healthPoint = heroS[qui].healthPointMax;
                    }
                    else{
                        heroS[qui].healthPoint += sortM[sort].healthPointS;
                    }
                    heroSo.mana -= sortM[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 2){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheEnnemies();
                    System.out.println("Retour");
                    int ret = nE;
                    System.out.println("(insérer la position du monstre que vous voulez attaquer)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui > nE){
                            System.out.println("Il n'y a aucun monstre à attaquer ici, veuillez sélectionner un nouveau monstre");
                        }
                        else if (qui == ret){
                            ver2 = 1;
                        }
                        else{
                            if (ennemiSo[qui].healthPoint == 0){
                                System.out.println("Le monstre est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nE & ennemiSo[qui].healthPoint == 0) || (qui > nH) || ver2 == 0);
                if (qui != nE){
                    System.out.println(heroSo.name + " lance une " + sortM[sort].name + " sur " + ennemiSo[qui].name);
                    if (ennemiSo[qui].healthPoint < sortM[sort].degat){
                        ennemiSo[qui].healthPoint = 0;
                    }
                    else{
                        ennemiSo[qui].healthPoint -= sortM[sort].degat;
                        ennemiSo[qui].effet += sortM[sort].effetS;
                    }
                    heroSo.mana -= sortM[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 3){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheEnnemies();
                    System.out.println("Retour");
                    int ret = nE;
                    System.out.println("(insérer la position du monstre que vous voulez attaquer)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui > nE){
                            System.out.println("Il n'y a aucun monstre à attaquer ici, veuillez sélectionner un nouveau monstre");
                        }
                        else if (qui == ret){
                            ver2 = 1;
                        }
                        else{
                            if (ennemiSo[qui].healthPoint == 0){
                                System.out.println("Le monstre est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else {
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nE & ennemiSo[qui].healthPoint == 0) || (qui > nH) || ver2 == 0);
                if (qui != nE){
                    System.out.println(heroSo.name + " lance un " + sortM[sort-1].name + " sur " + ennemiSo[qui-1].name);
                    if (ennemiSo[qui].healthPoint < sortM[sort].degat){
                        ennemiSo[qui].healthPoint = 0;
                    }
                    else{
                        ennemiSo[qui].healthPoint -= sortM[sort].degat;
                        ennemiSo[qui].effet += sortM[sort].effetS;
                    }
                    heroSo.mana -= sortM[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else {
                action(heroSo,heroS,ennemiSo);
            }
        }

        else if (heroSo.classe.equals("Healer")){
            do{
                ver = 0;
                afficheSortG();
                try{
                    sort = sorts.nextInt()-1;
                    if (sort < 5){
                        if (heroSo.mana < sortM[sort].coutS){
                            System.out.println(heroSo.name + " n'a pas assez de mana pour lancer ce sort");
                            System.out.println("Veuillez choisir un nouveau sort");
                        }
                        else{
                            ver = 1;
                        }
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Il faut taper un nombre...");
                    sorts.nextLine();
                    continue;
                }
            } while (heroSo.mana < sortG[sort].coutS || ver == 0);

            if (sort == 0){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheEnnemies();
                    System.out.println("Retour");
                    int ret = nE;
                    System.out.println("(insérer la position du monstre que vous voulez attaquer)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui > nE){
                            System.out.println("Il n'y a aucun monstre à attaquer ici, veuillez sélectionner un nouveau monstre");
                        }
                        else if (qui == ret){
                            ver2 = 1;
                        }
                        else{
                            if (ennemiSo[qui-1].healthPoint == 0){
                                System.out.println("Le monstre est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nE & ennemiSo[qui].healthPoint == 0) || (qui > nE) || ver2 == 0);
                if (qui != nE){
                    sortG[sort].effetS = 5;
                    System.out.println(heroSo.name + " lance une " + sortG[sort].name + " sur " + ennemiSo[qui].name);
                    if (ennemiSo[qui].healthPoint < sortG[sort].degat){
                        ennemiSo[qui].healthPoint = 0;
                    }
                    else{
                        ennemiSo[qui].healthPoint -= sortG[sort].degat;
                        ennemiSo[qui].effet += sortG[sort].effetS;
                    }
                    heroSo.mana -= sortG[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 1){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheHero();
                    System.out.println("Retour");
                    int ret = nH;
                    System.out.println("(insérer la position du héro que vous voulez soigner)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui >= nH){
                            if (qui == ret){
                                ver2 = 1;
                            }
                            else{
                                System.out.println("Il n'y a aucun héro à soigner ici, veuillez sélectionner un nouveau héro");
                            }
                        }
                        else{
                            if (heroS[qui].healthPoint == 0){
                                System.out.println("Le héro est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else if (heroS[qui].healthPoint == heroS[qui].healthPointMax){
                                System.out.println("Le héro a déjà toute sa vie au max, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nH & (heroS[qui].healthPoint == 0 & heroS[qui].healthPoint == heroS[qui].healthPointMax)) || (qui > nH) || ver2 == 0);
                sortG[sort].healthPointS = 50;
                System.out.println(heroSo.name + " utilise un " + sortG[sort].name + " sur " + heroS[qui].name);
                if (qui != nH){
                    if (heroS[qui].healthPointMax - heroS[qui].healthPoint < sortG[sort].healthPointS){
                        heroS[qui].healthPoint = heroS[qui].healthPointMax;
                    }
                    else{
                        heroS[qui].healthPoint += sortG[sort].healthPointS;
                    }
                    heroSo.mana -= sortG[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 2){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheHero();
                    System.out.println("Retour");
                    int ret = nH;
                    System.out.println("(insérer la position du héro que vous voulez protéger)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui > nH){
                            System.out.println("Il n'y a aucun héro à protéger ici, veuillez sélectionner un nouveau héro");
                        }
                        else if (qui == ret){
                            ver2 = 1;
                        }
                        else{
                            if (heroS[qui].healthPoint == 0){
                                System.out.println("Le héro est KO, veuillez sélectionner un nouveau héro");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nH & heroS[qui].healthPoint == 0) || (qui > nH) || ver2 == 0);
                if (qui != nH){
                    heroS[qui].resP = heroS[qui].resP*2;
                    heroS[qui].resS = heroS[qui].resS*2;
                    heroSo.mana -= sortG[sort-1].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 3){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheHero();
                    System.out.println("Retour");
                    int ret = nH;
                    System.out.println("(insérer la position du héro que vous voulez ressusciter)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui >= nH){
                            if (qui == ret){
                                ver2 = 1;
                            }
                            else{
                                System.out.println("Il n'y a aucun héro à soigner ici, veuillez sélectionner un nouveau héro");
                            }
                        }
                        else{
                            if (heroS[qui].healthPoint != 0){
                                System.out.println("Le héro n'est pas KO, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nH & heroS[qui].healthPoint != 0) || (qui > nH) || ver2 == 0);
                if (qui != nH){
                    sortG[sort].healthPointS = 50;
                    System.out.println(heroSo.name + " utilise un " + sortG[sort].name + " sur " + heroS[qui].name);
                    heroS[qui].healthPoint = sortG[sort].healthPointS;
                    heroSo.mana -= sortG[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else{
                action(heroSo,heroS,ennemiSo);
            }
        }

        else {
            do{
                ver = 0;
                afficheSortC();
                try{
                    sort = sorts.nextInt()-1;
                    if (heroSo.mana < sortM[sort].coutS){
                        System.out.println(heroSo.name + " n'a pas assez de mana pour lancer ce sort");
                        System.out.println("Veuillez choisir un nouveau sort");
                    }
                    else{
                        ver = 1;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Il faut taper un nombre...");
                    sorts.nextLine();
                    continue;
                }
            } while (heroSo.mana < sortC[sort].coutS || ver == 0);

            if (sort == 0){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                afficheEnnemies();
                System.out.println("Retour");
                int ret = nE;
                do{
                    afficheEnnemies();
                    System.out.println("(insérer la position du monstre que vous voulez attaquer)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui > nE){
                            System.out.println("Il n'y a aucun monstre à attaquer ici, veuillez sélectionner un nouveau monstre");
                        }
                        else if (qui == ret){
                            ver2 = 1;
                        }
                        else{
                            if (ennemiSo[qui].healthPoint == 0){
                                System.out.println("Le monstre est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else{
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while ((qui <= nE & ennemiSo[qui].healthPoint == 0) || (qui > nE) || ver2 == 0);
                if (qui != nE){
                    sortC[sort].effetS = 5;
                    System.out.println(heroSo.name + " lance une " + sortC[sort].name + " sur " + ennemiSo[qui].name);
                    if (ennemiSo[qui].healthPoint < sortC[sort].degat){
                        ennemiSo[qui].healthPoint = 0;
                    }
                    else{
                        ennemiSo[qui].healthPoint -= sortC[sort].degat;
                        ennemiSo[qui].effet += sortC[sort].effetS;
                    }
                    heroSo.mana -= sortC[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else if (sort == 1){
                System.out.println("Sur qui voulez-vous lancer ce sort?");
                do{
                    afficheHero();
                    System.out.println("Retour");
                    int ret = nH;
                    System.out.println("(insérer la position du héro que vous voulez ressusciter)");
                    try{
                        qui = sorts.nextInt()-1;
                        if (qui >= nH){
                            if (qui == ret){
                                ver2 = 1;
                            }
                            else{
                                System.out.println("Il n'y a aucun héro à soigner ici, veuillez sélectionner un nouveau héro");
                            }
                        }
                        else{
                            if (heroS[qui].healthPoint == 0){
                                System.out.println("Le héro est KO, veuillez sélectionner un nouveau monstre");
                            }
                            else if (heroS[qui].healthPoint == heroS[qui].healthPointMax){
                                System.out.println("Le héro a déjà toute sa vie au max, veuillez sélectionner un nouveau monstre");
                            }
                            else {
                                ver2 = 1;
                            }
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Il faut taper un nombre...");
                        sorts.nextLine();
                        continue;
                    }
                } while (qui < nH & (heroS[qui].healthPoint == 0 || heroS[qui].healthPoint == heroS[qui].healthPointMax) || (qui > nH) || ver2 == 0);
                if (qui != nH){
                    sortC[sort].healthPointS = 20;
                    System.out.println(heroSo.name + " utilise un " + sortC[sort].name + " sur " + heroS[qui].name);
                    if (heroS[qui].healthPointMax - heroS[qui].healthPoint < sortC[sort].healthPointS){
                        heroS[qui].healthPoint = heroS[qui].healthPointMax;
                    }
                    else{
                        heroS[qui].healthPoint += sortC[sort].healthPointS;
                    }
                    heroSo.mana -= sortC[sort].coutS;
                }
                else{
                    action(heroSo,heroS,ennemiSo);
                }
            }

            else{
                action(heroSo,heroS,ennemiSo);
            }
        }
    }

    public void afficheSortM(){
        for (int i = 0; i < 4; i++){
            if (sortM[i].coutS == 0){
                System.out.println((i+1) + " " +sortM[i].name);
            }
            else{
                System.out.println((i+1) + " " +sortM[i].name + " | " + sortM[i].coutS + " mana");
            }
        }
    }

    public void afficheSortG(){
        for (int i = 0; i < 4; i++){
            if (sortG[i].coutS == 0){
                System.out.println((i+1) + " " +sortG[i].name);
            }
            else{
                System.out.println((i+1) + " " +sortG[i].name + " | " + sortG[i].coutS + " mana");
            }
        }
    }

    public void afficheSortC(){
        for (int i = 0; i < 2; i++){
            if (sortC[i].coutS == 0){
                System.out.println((i+1) + " " +sortC[i].name);
            }
            else{
                System.out.println((i+1) + " " +sortC[i].name + " | " + sortC[i].coutS + " mana");
            }
        }
    }

    public void nourritureH(Hero heroN, Hero[] heroN2, Ennemy[] ennemiN){
        Scanner manger = new Scanner(System.in);
        Scanner manger2 = new Scanner(System.in);
        System.out.println("Que voulez-vous manger?");
        System.out.println("1) Cuisse de poulet");
        System.out.println("2) Raclette");
        System.out.println("3) Retour");
        String CR = manger.next();
        afficheHero();
        int pos = 0;
        int rep;
        System.out.println("À qui voulez vous donner cette portion de nourriture?");
        if (CR.equals("1")) {
            if (food[0].quantitee != 0){
                do{
                    rep = 0;
                    try{
                        pos = manger2.nextInt() - 1;
                        if (pos < nH){
                            if (heroN2[pos].mana >= heroN2[pos].manaMax){
                                System.out.println(heroN2[pos].name + " a déjà tout son mana.");
                                System.out.println("Veuillez saisir un nouvel héros");
                                rep = 1;
                                break;
                            }
                            else if (food[0].mana > (heroN2[pos].manaMax - heroN2[pos].mana)){
                                System.out.println(heroN2[pos].name + " a récupéré " + (heroN2[pos].manaMax - heroN2[pos].mana) + " mana.");
                                heroN2[pos].mana = heroN2[pos].manaMax;
                                food[0].quantitee -= 1;
                            }
                            else{
                                System.out.println(heroN2[pos].name + " a récupéré " + food[0].mana + " mana.");
                                heroN2[pos].mana += food[0].mana;
                                food[0].quantitee -= 1;
                            }
                        }
                        else{
                            System.out.println("Héro introuvable, veuillez répéter");
                            rep = 1;
                            break;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Vous pouvez répéter la question?");
                        manger2.nextLine();
                    }
                }
                while(true);
                if (rep == 1){
                    action(heroN, heroN2, ennemiN);
                }
            }
            else{
                System.out.println("Il n'y a plus de cuisse de poulet, revenez une prochaine fois");
                action(heroN, heroN2, ennemiN);
            }
        }
        else if (CR == "2"){
            if (food[1].quantitee !=0){
                do{
                    rep = 0;
                    try{
                        pos = manger2.nextInt() - 1;
                        if (pos < nH){
                            if (heroN2[pos].mana == heroN2[pos].manaMax){
                                System.out.println(heroN2[pos].name + " a déjà tout son mana.");
                                System.out.println("Veuillez saisir un nouvel héros");
                                rep = 1;
                                break;
                            }
                            else if (food[1].mana > heroN2[pos].manaMax - heroN2[pos].mana){
                                System.out.println(heroN2[pos].name + " a récupéré " + (heroN2[pos].manaMax - heroN2[pos].mana) + " mana.");
                                heroN2[pos].mana = heroN2[pos].manaMax;
                                food[1].quantitee -= 1;
                            }
                            else{
                                System.out.println(heroN2[pos].name + " a récupéré " + food[1].mana + " mana.");
                                heroN2[pos].mana += food[1].mana;
                                food[1].quantitee -= 1;
                            }
                        }
                        else{
                            System.out.println("Héro introuvable, veuillez répéter");
                            rep = 1;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Vous pouvez répéter la question?");
                        manger2.nextLine();
                    }
                }
                while(true);
                if (rep == 1){
                    action(heroN, heroN2, ennemiN);
                }
            }
            else{
                System.out.println("Il n'y a plus de raclette, revenez une prochaine fois");
                action(heroN, heroN2, ennemiN);
            }
        }
        else if (CR == "3"){
            action(heroN, heroN2, ennemiN);
        }
        else{
            System.out.println("Vous pouvez répéter la question?");
            action(heroN, heroN2, ennemiN);
        }
    }

    public void potionH(Hero heroP, Hero[] heroP2, Ennemy[] ennemiP){
        int heal;
        int effet;
        int rep;
        int rep0 = 0;
        Scanner manger = new Scanner(System.in);
        System.out.println("Quelle potion voulez-vous utiliser?");
        System.out.println("1) Potion de vie");
        System.out.println("2) Potion de résistance");
        System.out.println("3) Potion de poison");
        System.out.println("4) Potion de guérison");
        System.out.println("5) Retour");

        try{
            Integer pot = manger.nextInt();
            if (pot == 1) {
                heal = 70;
                int al = nH;
                Integer al0;
                Scanner lance2 = new Scanner(System.in);
                System.out.println("Sur qui voulez vous utiliser cette potion?");
                do{
                    rep = 0;
                    try{
                        al0 = lance2.nextInt();
                        al = al0 - 1;
                        if (al < nH){
                            if (heroP2[al].healthPoint == 0){
                                System.out.println(heroP.name + " est KO, veuillez en sélectionner un autre");
                            }
                            else if (heroP2[al].healthPoint == heroP2[al].healthPointMax){
                                System.out.println(heroP2[al].name + " a toute sa vie, veuillez en sélectionner un autre");
                            }
                        }
                        else {
                            System.out.println("Héro introuvable, veuillez répéter");
                            rep = 1;
                            break;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Vous pouvez répéter la question?");
                        lance2.nextLine();
                        continue;
                    }
                } while ((al < nH & heroP2[al].healthPoint == 0) || (al < nH & heroP2[al].healthPoint == heroP2[al].healthPointMax));
                if (rep == 0){
                    System.out.println(heroP.name + " a lancé une potion de vie sur " + heroP2[al].name);
                    if (heal > heroP2[al].healthPointMax - heroP2[al].healthPoint){
                        System.out.println(heroP2[al].name + " a récupéré " + (heroP2[al].healthPointMax - heroP2[al].healthPoint) + " PV");
                        heroP2[al].healthPoint = heroP2[al].healthPointMax;
                    }
                    else{
                        System.out.println(heroP2[al].name + " a récupéré " + heal + " PV");
                        heroP2[al].healthPoint += heal;
                    }
                }
                else{
                    action(heroP,heroP2,ennemiP);
                }
            }

            else if (pot == 2){
                int al = nH;
                Integer al0;
                Scanner lance2 = new Scanner(System.in);
                System.out.println("Sur qui voulez vous utiliser cette potion?");
                do{
                    rep = 0;
                    try{
                        al0 = lance2.nextInt();
                        al = al0 - 1;
                        if (al < nH){
                            if (heroP2[al].healthPoint == 0){
                                System.out.println(heroP.name + " est KO, veuillez en sélectionner un autre");
                            }
                        }
                        else{
                            System.out.println("Héro introuvable, veuillez répéter");
                            rep = 1;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Vous pouvez répéter la question?");
                        lance2.nextLine();
                        continue;
                    }
                } while ((al < nH & heroP2[al].healthPoint == 0));
                if (rep == 0){
                    System.out.println(heroP.name + " a lancé une potion de résistance sur " + heroP2[al].name);
                    heroP2[al].resP = heroP2[al].resP *2;;
                    heroP2[al].resS = heroP2[al].resS *2;
                }
                else{
                    action(heroP,heroP2,ennemiP);
                }
            }

            else if (pot == 3) {
                effet = 5;
                int en = nE;
                Integer en0;
                Scanner lance = new Scanner(System.in);
                System.out.println("Qui voulez-vous attaquer?");
                do {
                    rep = 0;
                    try{
                        en0 = lance.nextInt();
                        en = en0 - 1;
                        if (en < nE) {
                            if (ennemiP[en].healthPoint == 0) {
                                System.out.println("Cet ennemi est KO, veuillez en sélectionner un autre");
                            }
                        }
                        else {
                            System.out.println("Il n'y a pas d'ennemi ici, veuillez répéter");
                            rep = 1;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Vous pouvez répéter la question?");
                        lance.nextLine();
                        continue;
                    }
                }while ((ennemiP[en].healthPoint == 0));
                if (rep == 0){
                    System.out.println(heroP.name + " lance une potion de poison sur " + ennemiP[en].name);
                    ennemiP[en].effet += effet;
                }
                else{
                    action(heroP,heroP2,ennemiP);
                }
            }

            else if (pot == 4){
                int al = nH;
                Integer al0;
                Scanner lance2 = new Scanner(System.in);
                System.out.println("Sur qui voulez vous utiliser cette potion?");
                do{
                    rep = 0;
                    try{
                        al0 = lance2.nextInt();
                        al = al0 - 1;
                        if (al < nH){
                            if (heroP2[al].healthPoint == 0){
                                System.out.println(heroP2[al].name + " est KO, veuillez en sélectionner un autre");
                            }
                            else if (heroP2[al].effetS == 0){
                                System.out.println(heroP2[al].name + " n'a aucun symptôme, veuillez en sélectionner un autre");
                            }
                        }
                        else {
                            System.out.println("Héro introuvable, veuillez répéter");
                            rep = 1;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Vous pouvez répéter la question?");
                        lance2.nextLine();
                    }
                } while ((al >= nH) || (al < nH & heroP2[al].healthPoint == 0) || (al < nH & heroP2[al].effetS == 0));
                if (rep == 0){
                    System.out.println(heroP.name + " lance une potion de guérison sur " + heroP2[al].name);
                    heroP2[al].effetS = 0;
                }
                else{
                    action(heroP,heroP2,ennemiP);
                }
            }

            else if (pot == 5){
                action(heroP,heroP2,ennemiP);
            }
            else{
                System.out.println("Potion introuvable, veuillez répéter");
                action(heroP,heroP2,ennemiP);
            }
        }
        catch(InputMismatchException e){
            System.out.println("Vous pouvez répéter la question?");
            manger.nextLine();
            rep0 = 1;
        }
        if (rep0 == 1){
            action(heroP,heroP2,ennemiP);
        }
    }

    public void etatAl(Hero[] heroEt){
        for (int i = 0; i < nH; i++){
            if (heroEt[i].effetP > heroEt[i].healthPoint){
                heroEt[i].healthPoint = 0;
            }
            else{
                heroEt[i].healthPoint -= heroEt[i].effetP;
            }
            heroEt[i].healthPoint -= heroEt[i].effetP;
        }
    }

    public void etatEn(Ennemy[] ennemiEt){
        if (ennemiEt == ennemiB){
            if (ennemiEt[0].etat > ennemiEt[0].healthPoint){
                ennemiEt[0].healthPoint = 0;
            }
            else{
                ennemiEt[0].healthPoint -= ennemiEt[0].etat;
            }
        }
        else{
            for (int i = 0; i < nE; i++){
                if (ennemiEt[i].etat > ennemiEt[i].healthPoint){
                    ennemiEt[i].healthPoint = 0;
                }
                else{
                    ennemiEt[i].healthPoint -= ennemiEt[i].etat;
                }
            }
        }
    }

    public void attaqueEn(Ennemy ennemiA){
        boolean verif;
        int qui;
        if (ennemiA == ennemiB[0]){
            if (ennemiA.healthPoint != 0){
                Random who = new Random();
                do {
                    qui = who.nextInt(nH);
                    verif = verifHero(qui);
                } while (verif == Ko);
                atEn(ennemiA, hero[qui]);
                if (hero[qui].healthPoint == 0){
                    System.out.println(hero[qui].name + " est KO");
                }
            }
        }
        else{
            if (ennemiA.healthPoint != 0){
                Random who = new Random();
                do {
                    qui = who.nextInt(nH);
                    verif = verifHero(qui);
                } while (verif == Ko); //Le héros est mort
                atEn(ennemiA, hero[qui]);
                if (hero[qui].healthPoint == 0){
                    System.out.println(hero[qui].name + " est KO");
                }
            }
        }
    }

    public void atEn(Ennemy ennemiA2, Hero heroA2){
        int damageP = 0;
        int damageS = 0;
        Random atE = new Random();
        int attaqueE = 1 + atE.nextInt(4);
        if (ennemiA2 == ennemiB[0]){
            switch (attaqueE){
                case 1:
                    System.out.println(ennemiA2.name + " assène un coup d'épée' à " + heroA2.name + ".");
                    damageP = 20;
                    damageS = 0;
                    break;
                case 2:
                    System.out.println(ennemiA2.name + " aspire l'âme de " + heroA2.name + ".");
                    damageP = 20;
                    damageS = 5;
                    heroA2.effetP += 5* heroA2.effetS;
                    break;
                case 3:
                    System.out.println(ennemiA2.name + " réduit à néant tout espoir dans le coeur de " + heroA2.name + ".");
                    damageP = 30;
                    damageS = 5;
                    heroA2.effetP += 5* heroA2.effetS;
                    break;
            }
        }
        else{
            if (ennemiA2.classe.equals("Dragon")){
                switch (attaqueE){
                    case 1:
                        System.out.println(ennemiA2.name + " assène un coup de griffes à " + heroA2.name + ".");
                        damageP = 15;
                        damageS = 0;
                        break;
                    case 2:
                        System.out.println(ennemiA2.name + " mord " + heroA2.name + ".");
                        damageP = 15;
                        damageS = 0;
                        break;
                    case 3:
                        System.out.println(ennemiA2.name + " brûle de ses flammes ardentes " + heroA2.name + ".");
                        damageP = 20;
                        damageS = 5;
                        heroA2.effetP += 5* heroA2.effetS;
                        break;
                }
            }
            else if (ennemiA2.classe.equals("Troll")){
                switch (attaqueE){
                    case 1:
                        System.out.println(ennemiA2.name + " assène un coup de poing à " + heroA2.name + ".");
                        damageP = 10;
                        damageS = 0;
                        break;
                    case 2:
                        System.out.println(ennemiA2.name + " assène un coup de massue à " + heroA2.name + ".");
                        damageP = 15;
                        damageS = 0;
                        break;
                    case 3:
                        System.out.println(ennemiA2.name + " créé un séisme qui destabilise " + heroA2.name + ".");
                        damageP = 15;
                        damageS = 5;
                        heroA2.effetP += 5* heroA2.effetS;
                        break;
                }
            }
            else{
                switch (attaqueE){
                    case 1:
                        System.out.println(ennemiA2.name + " lance une pioche sur " + heroA2.name + ".");
                        damageP = 5;
                        damageS = 0;
                        break;
                    case 2:
                        System.out.println(ennemiA2.name + " assène un coup de pioche à " + heroA2.name + ".");
                        damageP = 10;
                        damageS = 0;
                        break;
                    case 3:
                        System.out.println(ennemiA2.name + " creuse un tunnel et fais tomber " + heroA2.name + " dedans.");
                        damageP = 10;
                        damageS = 0;
                        heroA2.effetP += 5* heroA2.effetS;
                        break;
                }
            }
        }
        damageP = damageP*ennemiA2.damagePoints;
        damageS = damageS*ennemiA2.damagePoints;

        if(heroA2.healthPoint < ((damageP/ heroA2.resP) + (damageS/ heroA2.resS))){
            heroA2.healthPoint = 0;
        }
        else{
            heroA2.healthPoint -= ((damageP/ heroA2.resP) + (damageS/ heroA2.resS));
        }
    }

    public boolean verifHero(int qui){
        boolean VH;
        if (hero[qui].healthPoint == 0 ){
            VH = false;
        }
        else{
            VH = true;
        }
        return VH;
    }

    public void loot() throws InterruptedException {
        lootH();
        lootN();
        lootP();
        lootB();
    }

    public void lootH(){
        for (int i = 0; i < nH; i++){
            if (hero[i].classe == "Hunter"){
                hero[i].arrow =  hero[i].arrowMax;
                System.out.println(hero[i].name + " a récupéré les flèches qu'il a envoyé...");
                System.out.println("Il y a maintenant " + hero[i].arrow + " flèches dans son carquois");
            }
        }
    }

    public void lootP() throws InterruptedException {
        Random typeP = new Random();
        int type1 = 1 + typeP.nextInt(4);
        int type2 = 1 + typeP.nextInt(4);
        int total = type1 * 10 + type2;
        int use0 = potion[0].quantiteeM - potion[0].quantitee;
        int use1 = potion[1].quantiteeM - potion[1].quantitee;
        int use2 = potion[2].quantiteeM - potion[2].quantitee;
        int use3 = potion[3].quantiteeM - potion[3].quantitee;
        switch (total) {
            case 11:
                if (use0 > 2) {
                    System.out.println("Vous avez récupéré 2 potions de Vie");
                    potion[0].quantitee += 2;
                } else {
                    System.out.println("Vous avez trouvé 2 potions de Vie, malheureusement, vous n'avez pas assez de place");
                    System.out.println("Par conséquent, vous avez pu récupérer " + use0 + " potions de Vie");
                    potion[0].quantitee += use0;
                }
                break;

            case 12:
                System.out.println("Vous avez récupéré 1 potion de Vie et 1 potion de Résistance");
                if (use0 > 1 & use1 > 1) {
                    potion[0].quantitee += 1;
                    potion[1].quantitee += 1;
                } else if (use0 > 1 & use1 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Vie");
                    potion[0].quantitee += 1;
                } else if (use0 == 0 & use1 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Résistance");
                    potion[1].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 13:
                System.out.println("Vous avez récupéré 1 potion de Vie et 1 potion de Poison");
                if (use0 > 1 & use2 > 1) {
                    potion[0].quantitee += 1;
                    potion[2].quantitee += 1;
                } else if (use0 > 1 & use2 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Vie");
                    potion[0].quantitee += 1;
                } else if (use0 == 0 & use2 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Poison");
                    potion[2].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 14:
                System.out.println("Vous avez récupéré 1 potion de Vie et 1 potion de Guérison");
                if (use0 > 1 & use3 > 1) {
                    potion[0].quantitee += 1;
                    potion[3].quantitee += 1;
                } else if (use0 > 1 & use3 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Vie");
                    potion[0].quantitee += 1;
                } else if (use0 == 0 & use3 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Guérison");
                    potion[3].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;


            case 21:
                System.out.println("Vous avez récupéré 1 potion de Résistance et 1 potion de Vie");
                if (use0 > 1 & use1 > 1) {
                    potion[0].quantitee += 1;
                    potion[1].quantitee += 1;
                } else if (use0 > 1 & use1 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Vie");
                    potion[0].quantitee += 1;
                } else if (use0 == 0 & use1 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Résistance");
                    potion[1].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 22:
                if (use1 > 2) {
                    System.out.println("Vous avez récupéré 2 potions de Résistance");
                    potion[1].quantitee += 2;
                } else {
                    System.out.println("Vous avez trouvé 2 potions de Résistance, malheureusement, vous n'avez pas assez de place");
                    System.out.println("Par conséquent, vous avez pu récupérer " + use1 + " potions de Résistance");
                    potion[1].quantitee += use1;
                }
                break;

            case 23:
                System.out.println("Vous avez récupéré 1 potion de Résistance et 1 potion de Poison");
                if (use1 > 1 & use2 > 1) {
                    potion[1].quantitee += 1;
                    potion[2].quantitee += 1;
                } else if (use2 > 1 & use1 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Poison");
                    potion[2].quantitee += 1;
                } else if (use2 == 0 & use1 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Résistance");
                    potion[1].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 24:
                System.out.println("Vous avez récupéré 1 potion de Résistance et 1 potion de Guérison");
                if (use3 > 1 & use1 > 1) {
                    potion[3].quantitee += 1;
                    potion[1].quantitee += 1;
                } else if (use3 > 1 & use1 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Guérison");
                    potion[3].quantitee += 1;
                } else if (use3 == 0 & use1 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Résistance");
                    potion[1].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;


            case 31:
                System.out.println("Vous avez récupéré 1 potion de Poison et 1 potion de Vie");
                if (use0 > 1 & use2 > 1) {
                    potion[0].quantitee += 1;
                    potion[2].quantitee += 1;
                } else if (use0 > 1 & use2 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Vie");
                    potion[0].quantitee += 1;
                } else if (use0 == 0 & use2 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Poison");
                    potion[2].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 32:
                System.out.println("Vous avez récupéré 1 potion de Poison et 1 potion de Résistance");
                if (use1 > 1 & use2 > 1) {
                    potion[1].quantitee += 1;
                    potion[2].quantitee += 1;
                } else if (use2 > 1 & use1 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Poison");
                    potion[2].quantitee += 1;
                } else if (use2 == 0 & use1 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Résistance");
                    potion[1].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 33:
                if (use2 > 2) {
                    System.out.println("Vous avez récupéré 2 potions de Poison");
                    potion[2].quantitee += 2;
                } else {
                    System.out.println("Vous avez trouvé 2 potions de Résistance, malheureusement, vous n'avez pas assez de place");
                    System.out.println("Par conséquent, vous avez pu récupérer " + use2 + " potions de Résistance");
                    potion[2].quantitee += use2;
                }
                break;

            case 34:
                System.out.println("Vous avez récupéré 1 potion de Poison et 1 potion de Guérison");
                if (use3 > 1 & use2 > 1) {
                    potion[3].quantitee += 1;
                    potion[2].quantitee += 1;
                } else if (use2 > 1 & use3 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Poison");
                    potion[2].quantitee += 1;
                } else if (use2 == 0 & use3 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Guérison");
                    potion[3].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 41:
                System.out.println("Vous avez récupéré 1 potion de Guérison et 1 potion de Vie");
                if (use0 > 1 & use3 > 1) {
                    potion[0].quantitee += 1;
                    potion[3].quantitee += 1;
                } else if (use0 > 1 & use3 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Vie");
                    potion[0].quantitee += 1;
                } else if (use0 == 0 & use3 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Guérison");
                    potion[3].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 42:
                System.out.println("Vous avez récupéré 1 potion de Guérison et 1 potion de Résistance");
                if (use3 > 1 & use1 > 1) {
                    potion[3].quantitee += 1;
                    potion[1].quantitee += 1;
                } else if (use3 > 1 & use1 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Guérison");
                    potion[3].quantitee += 1;
                } else if (use3 == 0 & use1 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Résistance");
                    potion[1].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 43:
                System.out.println("Vous avez récupéré 1 potion de Guérison et 1 potion de Poison");
                if (use2 > 1 & use3 > 1) {
                    potion[2].quantitee += 1;
                    potion[3].quantitee += 1;
                } else if (use2 > 1 & use3 == 0) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Poison");
                    potion[2].quantitee += 1;
                } else if (use2 == 0 & use3 > 1) {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous avez donc récupéré la potion de Guérison");
                    potion[3].quantitee += 1;
                } else {
                    System.out.println("Malheureusement, vous n'avez pas assez de place, vous n'avez donc rien récupéré");
                }
                break;

            case 44:
                System.out.println("Vous avez récupéré 2 potions de Guérison");
                if (use3 > 2) {
                    System.out.println("Vous avez récupéré 2 potions de Poison");
                    potion[3].quantitee += 2;
                } else {
                    System.out.println("Vous avez trouvé 2 potions de Résistance, malheureusement, vous n'avez pas assez de place");
                    System.out.println("Par conséquent, vous avez pu récupérer " + use3 + " potions de Résistance");
                    potion[3].quantitee += use3;
                }
                break;
        }
    }

    public void lootN() throws InterruptedException {
        int cu = food[0].quantiteeM - food[0].quantitee;
        int ra = food[1].quantiteeM - food[1].quantitee;
        if (cu < 5){
            food[0].quantitee = food[0].quantiteeM;
            System.out.println("Vous avez récupéré " + cu + " cuisses de Poulet");
        }
        else{
            food[0].quantitee += 5;
            System.out.println("Vous avez récupéré 5 cuisses de Poulet");
        }

        if (ra < 2){
            food[1].quantitee = food[1].quantiteeM;
            System.out.println("Vous avez récupéré " + ra + " portion de Raclette");
        }
        else{
            food[0].quantitee += 5;
            System.out.println("Vous avez récupéré 2 portion de Raclette");
        }
    }

    public void lootB(){
        Random obj = new Random();
        int nbObjet = obj.nextInt(4);
        int typeO = obj.nextInt(5);
        for (int i = 0; i < nbObjet; i++){
            switch (nbObjet){
                case 0:
                    System.out.println("Vous fouillez un ennemi et vous avez trouvé une PokeBall... ");
                    System.out.println("il était sûrement à la recherche de Mew...");
                    break;
                case 1:
                    System.out.println("Vous fouillez un ennemi et vous avez trouvé une Toupie Beyblade... pas très utile en combat");
                    break;
                case 2:
                    System.out.println("Vous fouillez un ennemi et vous avez trouvé du papier toilette...");
                    System.out.println("il devait avoir une envie pressante...");
                    break;
                case 3:
                    System.out.println("Vous fouillez un ennemi et vous avez trouvé un Iphone...");
                    System.out.println("La dernière photo prise est une photo de lui vous attaquant... quel arrogance.");
                    break;
                case 4:
                    System.out.println("Vous fouillez un ennemi et vous avez trouvé la carte du Maraudeur...");
                    System.out.println("Il s'est cru dans Harry Potter ou quoi...");
                    break;
                case 5:
                    System.out.println("Vous fouillez un ennemi et vous avez trouvé une paire de lunette...");
                    System.out.println("Ça doit être pour ça qu'il n'a pas réussi à vous toucher...");
                    break;
            }
        }
    }

    public void experience(){
        for (int i = 0; i < nH; i++){
            Random exper = new Random();
            int exp = exper.nextInt(3);
            hero[i].degP += 3;
            System.out.println("Les HP de " + hero[i].name + " sont passées de " + (hero[i].degP - 3) + " à " + hero[i].degP);
            switch (exp){
                case 0:
                    hero[i].healthPointMax += 10;
                    System.out.println("Les HP de " + hero[i].name + " sont passées de " + (hero[i].healthPointMax - 10) + " à " + hero[i].healthPointMax);
                    if (hero[i].healthPoint == 0){
                        hero[i].healthPoint = 0;
                    }
                    else{
                        hero[i].healthPoint += 10;
                        System.out.println(hero[i].name + " a récupéré 10 HP");
                    }
                    break;
                case 1:
                    hero[i].manaMax += 2;
                    System.out.println("L'énergie de " + hero[i].name + " sont passées de " + (hero[i].manaMax - 2) + " à " + hero[i].manaMax);
                    if (hero[i].mana == 0){
                        hero[i].mana = 0;
                    }
                    else{
                        hero[i].mana += 2;
                        System.out.println(hero[i].name + " a récupéré 2 Mana");
                    }
                    break;
                case 2:
                    System.out.println("Les résistances de " + hero[i].name + " ont augmenté");
                    hero[i].resP += 1;
                    hero[i].resS += 1;
                    break;
            }
        }

    }

    public void finJeu() throws InterruptedException {
        System.out.println("Merci d'avoir joué à notre jeu.");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("J'espère vous revoir bientôt jeune aventurier.");
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }
}