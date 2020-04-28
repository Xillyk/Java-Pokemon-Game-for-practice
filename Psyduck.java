
public class Psyduck extends Pokemon {
    public Psyduck(String nickName, int level, int typeCreature) {
        super("Psyduck", nickName, "Water", level, typeCreature);
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