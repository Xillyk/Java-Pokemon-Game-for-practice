
public class Caterpie extends Pokemon {
    public Caterpie(String nickName, int level, int typeCreature) {
        super("Caterpie", nickName, "Bug", level, typeCreature);
    }
    public void attack(Pokemon p) {
        int ap = getAP();
        int cri = 4;  // 100 / cri
        int rand = (int) (Math.random() * cri);
        if (rand == 0) {
            // get critical X 2
            ap *= 2;
        }
        p.getDamage(ap);
    
        lossHugryPoint(10);   
        lossSleepPoint(5);
        System.out.println(getNickName() + " AP : " + ap);
    }
}