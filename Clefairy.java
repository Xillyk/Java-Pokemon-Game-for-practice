
public class Clefairy extends Pokemon {
    public Clefairy(String nickName, int level, int typeCreature) {
        super("Clefairy", nickName, "Fairy", level, typeCreature);
    }
    public void attack(Pokemon p) {
        int ap = getAP();
        int cri = 6;            // 100 / cri
        int rand = (int) (Math.random() * cri);
        if (rand == 0) {
            // get critical X 2
            ap *= 2;
        }
        p.getDamage(ap);

        int hgBuff = 3;         // 100 / hgBuff 
        rand = (int) (Math.random() * hgBuff);
        if (rand == 0) 
            p.lossHugryPoint(30);
    
        lossHugryPoint(10);   
        lossSleepPoint(5);
        System.out.println(getNickName() + " AP : " + ap);
    }
}